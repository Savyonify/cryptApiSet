package puresport.mvc.area;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class AreaValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AreaValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(AreaController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(AreaController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Area.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(AreaController.pthc+"save")){
			controller.render(AreaController.pthv+"xxx.html");
		
		} else if (actionKey.equals(AreaController.pthc+"update")){
			controller.render(AreaController.pthv+"xxx.html");
		
		}
	}
	
}
