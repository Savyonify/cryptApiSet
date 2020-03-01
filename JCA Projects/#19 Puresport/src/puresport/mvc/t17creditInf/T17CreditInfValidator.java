package puresport.mvc.t17creditInf;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T17CreditInfValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T17CreditInfValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T17CreditInfController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T17CreditInfController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T17CreditInf.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T17CreditInfController.pthc+"save")){
			controller.render(T17CreditInfController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T17CreditInfController.pthc+"update")){
			controller.render(T17CreditInfController.pthv+"xxx.html");
		
		}
	}
	
}
