package puresport.mvc.t14invitationcode;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T14InvitationCodeValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T14InvitationCodeValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T14InvitationCodeController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T14InvitationCodeController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T14InvitationCode.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T14InvitationCodeController.pthc+"save")){
			controller.render(T14InvitationCodeController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T14InvitationCodeController.pthc+"update")){
			controller.render(T14InvitationCodeController.pthv+"xxx.html");
		
		}
	}
	
}
