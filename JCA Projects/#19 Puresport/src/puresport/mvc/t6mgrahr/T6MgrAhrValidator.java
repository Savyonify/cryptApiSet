package puresport.mvc.t6mgrahr;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T6MgrAhrValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6MgrAhrValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T6MgrAhrController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T6MgrAhrController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T6MgrAhr.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T6MgrAhrController.pthc+"save")){
			controller.render(T6MgrAhrController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T6MgrAhrController.pthc+"update")){
			controller.render(T6MgrAhrController.pthv+"xxx.html");
		
		}
	}
	
}
