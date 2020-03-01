package puresport.mvc.t11examstat;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T11ExamStatValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T11ExamStatValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T11ExamStatController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T11ExamStatController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T11ExamStat.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T11ExamStatController.pthc+"save")){
			controller.render(T11ExamStatController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T11ExamStatController.pthc+"update")){
			controller.render(T11ExamStatController.pthv+"xxx.html");
		
		}
	}
	
}
