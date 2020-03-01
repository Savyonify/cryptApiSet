package puresport.mvc.t12highestscore;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T12HighestScoreValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T12HighestScoreValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T12HighestScoreController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T12HighestScoreController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T12HighestScore.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T12HighestScoreController.pthc+"save")){
			controller.render(T12HighestScoreController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T12HighestScoreController.pthc+"update")){
			controller.render(T12HighestScoreController.pthv+"xxx.html");
		
		}
	}
	
}
