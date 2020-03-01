package puresport.mvc.t2adiv;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T2AdivService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T2AdivService.class);
	
	public static final T2AdivService service = Enhancer.enhance(T2AdivService.class);
	
	public T2Adiv SelectById(Integer id){
		
		T2Adiv mdl = T2Adiv.dao.findFirst("select * from t2Adiv where id=?", id);
		return mdl;
	}
}
