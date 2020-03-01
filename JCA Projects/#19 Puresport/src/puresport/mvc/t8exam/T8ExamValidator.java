package puresport.mvc.t8exam;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T8ExamValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T8ExamValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T8ExamController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T8ExamController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T8Exam.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T8ExamController.pthc+"save")){
			controller.render(T8ExamController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T8ExamController.pthc+"update")){
			controller.render(T8ExamController.pthv+"xxx.html");
		
		}
	}
	
}
