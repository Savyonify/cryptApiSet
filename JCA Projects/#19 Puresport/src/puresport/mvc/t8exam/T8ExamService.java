package puresport.mvc.t8exam;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T8ExamService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T8ExamService.class);
	
	public static final T8ExamService service = Enhancer.enhance(T8ExamService.class);
	
	public T8Exam SelectById(Integer id){
		
		T8Exam mdl = T8Exam.dao.findFirst("select * from t8Exam where id=?", id);
		return mdl;
	}
}
