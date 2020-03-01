package puresport.mvc.t14invitationcode;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T14InvitationCodeService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T14InvitationCodeService.class);
	
	public static final T14InvitationCodeService service = Enhancer.enhance(T14InvitationCodeService.class);
	
	public T14InvitationCode SelectById(Integer id){
		
		T14InvitationCode mdl = T14InvitationCode.dao.findFirst("select * from t14InvitationCode where id=?", id);
		return mdl;
	}
}
