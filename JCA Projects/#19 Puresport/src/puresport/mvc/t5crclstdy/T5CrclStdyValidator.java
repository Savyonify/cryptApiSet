package puresport.mvc.t5crclstdy;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T5CrclStdyValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T5CrclStdyValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T5CrclStdyController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T5CrclStdyController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T5CrclStdy.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T5CrclStdyController.pthc+"save")){
			controller.render(T5CrclStdyController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T5CrclStdyController.pthc+"update")){
			controller.render(T5CrclStdyController.pthv+"xxx.html");
		
		}
	}
	
}
