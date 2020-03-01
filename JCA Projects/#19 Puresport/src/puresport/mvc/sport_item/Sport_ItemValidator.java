package puresport.mvc.sport_item;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class Sport_ItemValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(Sport_ItemValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(Sport_ItemController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(Sport_ItemController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Sport_Item.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(Sport_ItemController.pthc+"save")){
			controller.render(Sport_ItemController.pthv+"xxx.html");
		
		} else if (actionKey.equals(Sport_ItemController.pthc+"update")){
			controller.render(Sport_ItemController.pthv+"xxx.html");
		
		}
	}
	
}
