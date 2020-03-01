package puresport.mvc.t5crclstdy;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T5CrclStdyService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T5CrclStdyService.class);
	
	public static final T5CrclStdyService service = Enhancer.enhance(T5CrclStdyService.class);
	
	public T5CrclStdy SelectById(Integer id){
		
		T5CrclStdy mdl = T5CrclStdy.dao.findFirst("select * from t5CrclStdy where id=?", id);
		return mdl;
	}
}
