package puresport.mvc.t10examgrd;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T10ExamGrdService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T10ExamGrdService.class);
	
	public static final T10ExamGrdService service = Enhancer.enhance(T10ExamGrdService.class);
	
	public T10ExamGrd SelectById(Integer id){
		
		T10ExamGrd mdl = T10ExamGrd.dao.findFirst("select * from t10ExamGrd where id=?", id);
		return mdl;
	}
}
