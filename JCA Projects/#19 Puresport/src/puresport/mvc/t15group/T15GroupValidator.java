package puresport.mvc.t15group;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T15GroupValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T15GroupValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T15GroupController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T15GroupController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T15Group.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T15GroupController.pthc+"save")){
			controller.render(T15GroupController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T15GroupController.pthc+"update")){
			controller.render(T15GroupController.pthv+"xxx.html");
		
		}
	}
	
}
