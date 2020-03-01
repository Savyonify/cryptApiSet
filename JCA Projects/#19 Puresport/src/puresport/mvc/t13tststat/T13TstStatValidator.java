package puresport.mvc.t13tststat;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T13TstStatValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T13TstStatValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T13TstStatController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T13TstStatController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T13TstStat.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T13TstStatController.pthc+"save")){
			controller.render(T13TstStatController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T13TstStatController.pthc+"update")){
			controller.render(T13TstStatController.pthv+"xxx.html");
		
		}
	}
	
}
