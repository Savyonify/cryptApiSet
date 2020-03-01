package puresport.mvc.t13tststat;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T13TstStatService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T13TstStatService.class);
	
	public static final T13TstStatService service = Enhancer.enhance(T13TstStatService.class);
	
	public T13TstStat SelectById(Integer id){
		
		T13TstStat mdl = T13TstStat.dao.findFirst("select * from t13TstStat where id=?", id);
		return mdl;
	}
}
