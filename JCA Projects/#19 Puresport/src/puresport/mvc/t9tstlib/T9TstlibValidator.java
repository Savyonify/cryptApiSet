package puresport.mvc.t9tstlib;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T9TstlibValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T9TstlibValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T9TstlibController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T9TstlibController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T9Tstlib.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T9TstlibController.pthc+"save")){
			controller.render(T9TstlibController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T9TstlibController.pthc+"update")){
			controller.render(T9TstlibController.pthv+"xxx.html");
		
		}
	}
	
}
