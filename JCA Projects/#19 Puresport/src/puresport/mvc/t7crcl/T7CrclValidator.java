package puresport.mvc.t7crcl;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T7CrclValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T7CrclValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T7CrclController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T7CrclController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T7Crcl.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T7CrclController.pthc+"save")){
			controller.render(T7CrclController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T7CrclController.pthc+"update")){
			controller.render(T7CrclController.pthv+"xxx.html");
		
		}
	}
	
}
