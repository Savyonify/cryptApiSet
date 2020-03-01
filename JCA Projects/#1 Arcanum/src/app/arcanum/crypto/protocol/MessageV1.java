package app.arcanum.crypto.protocol;

import android.util.Base64;
import app.arcanum.AppSettings;
import app.arcanum.crypto.exceptions.MessageProtocolException;

public final class MessageV1 implements IMessage {
	public final int VERSION = 1;
	
	public byte[] From = new byte[32];
	public byte[] To = new byte[32];
	public byte[] IV = new byte[16];
	public byte[] Key = new byte[512]; // RSA-4096 encrypted
	public byte[] Content;
		
	@Override
	public final IMessage fromBytes(byte[] msg) throws MessageProtocolException {
		if(msg.length < 4 + 32 + 32 + 16 + 512 + 4 + 1)
			throw new MessageProtocolException("Message to short.");
		
		InputMessageStream stream  = new InputMessageStream(msg);
		try {
			if(this.VERSION != stream.readInt())
				throw new MessageProtocolException("No matching message version.");
			
			/*
			 * stream.read(From, 0, From.length);
			 * stream.read(To , 0, To.length);
			 * stream.read(IV , 0, IV.length);
			 * stream.read(Key , 0, Key.length);
			 */
			stream.read(From, From.length);
			stream.read(To, To.length);
			stream.read(IV, IV.length);
			stream.read(Key, Key.length);
			
			int length = stream.readInt();
			if(length < 0) length = stream.remainingSize();

			Content = new byte[length];
			stream.read(Content, 0, length);
		} catch(Exception ex) {
			throw new MessageProtocolException("Message is corrupt.", ex);
		} finally {
			stream.close();
		}
		return this;
	}

	@Override
	public final byte[] toBytes() throws MessageProtocolException {		
		OutputMessageStream stream = new OutputMessageStream();
		try {
			stream.writeInt(VERSION);
			stream.write(From);
			stream.write(To);
			stream.write(IV);
			stream.write(Key);
			stream.writeInt(Content.length);
			stream.write(Content);
		} catch(java.io.IOException ex) {
			throw new MessageProtocolException("Message writing failed.", ex);
		} finally {
			stream.close();
		}
		return stream.toByteArray();
	}

	@Override
	public final String getSender() {
		return Base64.encodeToString(From, AppSettings.BASE64_FLAGS);
	}

	@Override
	public final String getRecipient() {
		return Base64.encodeToString(To, AppSettings.BASE64_FLAGS);
	}

	@Override
	public final byte[] getContent() {
		return Content;
	}	
}
