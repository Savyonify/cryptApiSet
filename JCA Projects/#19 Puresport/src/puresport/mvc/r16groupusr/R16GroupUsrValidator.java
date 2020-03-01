package puresport.mvc.r16groupusr;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class R16GroupUsrValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(R16GroupUsrValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(R16GroupUsrController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(R16GroupUsrController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(R16GroupUsr.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(R16GroupUsrController.pthc+"save")){
			controller.render(R16GroupUsrController.pthv+"xxx.html");
		
		} else if (actionKey.equals(R16GroupUsrController.pthc+"update")){
			controller.render(R16GroupUsrController.pthv+"xxx.html");
		
		}
	}
	
}
