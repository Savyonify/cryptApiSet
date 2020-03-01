package app.arcanum.contacts;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Base64;
import android.util.Log;

import app.arcanum.AppSettings;
import app.arcanum.contracts.PhoneNumber;
import app.arcanum.tasks.HttpSendContactsTask;
import app.arcanum.tasks.contracts.ServerContactRequest;
import app.arcanum.tasks.contracts.ServerContactResponse;

public class ArcanumContactManager {
	public final static String TAG = "ArcanumContactManager";
	public final static String ACCOUNT_TYPE = AppSettings.APP_NAME + ".contact";
	public final static String MIME_TYPE = "Arcanum/PublicKey";
	
	private final static ArcanumContactCollection _contacts = new ArcanumContactCollection();
	private final ContentResolver _resolver;
	
	private ServerContactRequest[] _contactsRaw;
	
	
	public ArcanumContactManager(ContentResolver contentResolver) {
		_resolver = contentResolver;
	}
	
	public void Sync() {
		ArrayList<ServerContactRequest> rawContacts = getAll_asRequest();
		
		_contactsRaw = rawContacts.toArray(new ServerContactRequest[rawContacts.size()]);
		AsyncTask<ServerContactRequest,Void,ServerContactResponse[]> task = new HttpSendContactsTask()
			.execute(_contactsRaw);
				
		try {
			ServerContactResponse[] contacts = task.get();	
			for(ServerContactResponse c : contacts) {
				// Find response contact.
				ArcanumContact contact = _contacts.findByKey(c.LookupKey);
				if(contact == null) {
					// Add contact.
					contact = loadByLookup(c.LookupKey);
					_contacts.add(contact);
				}
				
				// Update contact
				contact.updatePubkey(c.Pubkey);
				contact.updateToken(c.PhoneHash);
			}
		} catch (Exception ex) {
			Log.e("ArcanumContactManager", "Sync failed!", ex);
		}
	}
	
	private ArrayList<ServerContactRequest> getAll_asRequest() {
		HashMap<String, ServerContactRequest> result = new HashMap<String, ServerContactRequest>();
		
		Cursor phones = _resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[] {
			ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY,
			ContactsContract.CommonDataKinds.Phone.NUMBER
		}, null, null, ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
		
		final int index_key 	= phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY);
		final int index_phone 	= phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);	
		
		while (phones.moveToNext()) {			
			final String key 	= phones.getString(index_key);
			final String phone 	= phones.getString(index_phone);
			
			ServerContactRequest contact;
			if(result.containsKey(key)) {
				contact = result.remove(key);
			} else {
				contact = new ServerContactRequest();
				contact.LookupKey = key;
			}
			
			contact.addPhone(new PhoneNumber(phone));
			result.put(key, contact);
		}
		phones.close();
		
		return new ArrayList<ServerContactRequest>(result.values());
	}
	
	public ArcanumContactCollection getAll() {
		return _contacts;
	}
	
	public ArcanumContactCollection getByHash(String phone) {
		return _contacts.findByHash(phone);
	}
	
	public void insert(ArcanumContact contact) throws RemoteException, OperationApplicationException {
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
		
		int rawContactInsertIndex = ops.size();
		ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
			.withValue(RawContacts.ACCOUNT_TYPE, ArcanumContactManager.ACCOUNT_TYPE)
			.withValue(RawContacts.ACCOUNT_NAME, contact.AccName)
			//.withValue(RawContacts.RAW_CONTACT_IS_READ_ONLY, "1")
			.build());
		
		String decodedPublicKey = Base64.encodeToString(contact.getPublicKey().getEncoded(), AppSettings.BASE64_FLAGS); 
		ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
			.withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
			.withValue(Data.MIMETYPE, ArcanumContactManager.MIME_TYPE)
			.withValue(ArcanumContactManager.MIME_TYPE, decodedPublicKey)
			.build());
		
		_resolver.applyBatch(ContactsContract.AUTHORITY, ops);
	}
	
	private ArcanumContact loadByLookup(String lookupKey) {
		ArrayList<ArcanumContact> result = new ArrayList<ArcanumContact>();
		
		Cursor phones = _resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[] {
				ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY,
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER
			}, 
			ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY + "=?", 
			new String[] { lookupKey },
			null);
		
		final int index_key 	= phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY);
		final int index_display	= phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		final int index_phone 	= phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);	
		
		while (phones.moveToNext()) {			
			final String key 				= phones.getString(index_key);
			final String display			= phones.getString(index_display);
			final String phoneNumber 		= phones.getString(index_phone);
			
			ArcanumContact contact = new ArcanumContact();
			contact.LookupKey 	= key;
			contact.DisplayName = display;
			contact.PhoneNumbers.add(new PhoneNumber(phoneNumber));
			result.add(contact);
		}
		phones.close();
		
		if(result.size() > 1)
			Log.w(TAG, String.format("loadByLookup has more than one result for %s!", lookupKey));
		
		return result.get(0);
	}
	
	/*
	private Bitmap getPhoto(int photoId) {
		final Cursor photo = _resolver.query(
				Data.CONTENT_URI,
				new String[] { ContactsContract.CommonDataKinds.Photo.PHOTO },	// column for the blob
				Data._ID + "=?",												// select row by id
				new String[]{ String.valueOf(photoId) },						// filter by photoId
				null);
		
		Bitmap result = null;
		if(photo.moveToFirst()) {
			final int index_photo = photo.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO);
			byte[] photoBlob = photo.getBlob(index_photo);
			
			final Bitmap photoBitmap = BitmapFactory.decodeByteArray(photoBlob, 0, photoBlob.length);
			result = photoBitmap;
		}
		photo.close();
		return result;
	}
	*/	
}
