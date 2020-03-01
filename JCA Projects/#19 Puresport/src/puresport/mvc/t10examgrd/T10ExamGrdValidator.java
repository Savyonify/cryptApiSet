package puresport.mvc.t10examgrd;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class T10ExamGrdValidator extends Validator {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T10ExamGrdValidator.class);
	
	protected void validate(Controller controller) {
		String actionKey = getActionKey();
		if (actionKey.equals(T10ExamGrdController.pthc+"save")){
			// validateString("username", 6, 30, "usernameMsg", "请输入登录账号!");
			
		} else if (actionKey.equals(T10ExamGrdController.pthc+"update")){
			
		}
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(T10ExamGrd.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals(T10ExamGrdController.pthc+"save")){
			controller.render(T10ExamGrdController.pthv+"xxx.html");
		
		} else if (actionKey.equals(T10ExamGrdController.pthc+"update")){
			controller.render(T10ExamGrdController.pthv+"xxx.html");
		
		}
	}
	
}
