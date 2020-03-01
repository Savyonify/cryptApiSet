package puresport.mvc.t18extraspoints;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T18ExtrasPointsValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T18ExtrasPointsValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T18ExtrasPointsController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T18ExtrasPointsController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T18ExtrasPoints.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T18ExtrasPointsController.pthc+"save")){
			controller.render(T18ExtrasPointsController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T18ExtrasPointsController.pthc+"update")){
			controller.render(T18ExtrasPointsController.pthv+"xxx.html");
		
		}
	}
	
}
