package puresport.mvc.t1usrbsc;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T1usrBscValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T1usrBscValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T1usrBscController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T1usrBscController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T1usrBsc.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T1usrBscController.pthc+"save")){
			controller.render(T1usrBscController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T1usrBscController.pthc+"update")){
			controller.render(T1usrBscController.pthv+"xxx.html");
		
		}
	}
	
}
