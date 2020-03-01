package puresport.mvc.t3statl;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T3StatlValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T3StatlValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T3StatlController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T3StatlController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T3Statl.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T3StatlController.pthc+"save")){
			controller.render(T3StatlController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T3StatlController.pthc+"update")){
			controller.render(T3StatlController.pthv+"xxx.html");
		
		}
	}
	
}
