package puresport.mvc.t4assc;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T4AsscService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T4AsscService.class);
	
	public static final T4AsscService service = Enhancer.enhance(T4AsscService.class);
	
	public T4Assc SelectById(Integer id){
		
		T4Assc mdl = T4Assc.dao.findFirst("select * from t4Assc where id=?", id);
		return mdl;
	}
}
