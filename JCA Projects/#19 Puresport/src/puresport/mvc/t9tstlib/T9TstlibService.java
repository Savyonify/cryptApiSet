package puresport.mvc.t9tstlib;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T9TstlibService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T9TstlibService.class);
	
	public static final T9TstlibService service = Enhancer.enhance(T9TstlibService.class);
	
	public T9Tstlib SelectById(Integer id){
		
		T9Tstlib mdl = T9Tstlib.dao.findFirst("select * from t9Tstlib where id=?", id);
		return mdl;
	}
}
