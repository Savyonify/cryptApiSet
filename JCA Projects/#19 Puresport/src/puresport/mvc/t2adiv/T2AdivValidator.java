package puresport.mvc.t2adiv;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T2AdivValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T2AdivValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T2AdivController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T2AdivController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T2Adiv.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T2AdivController.pthc+"save")){
			controller.render(T2AdivController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T2AdivController.pthc+"update")){
			controller.render(T2AdivController.pthv+"xxx.html");
		
		}
	}
	
}
