package puresport.mvc.t18extraspoints;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T18ExtrasPointsService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T18ExtrasPointsService.class);
	
	public static final T18ExtrasPointsService service = Enhancer.enhance(T18ExtrasPointsService.class);
	
	public T18ExtrasPoints SelectById(Integer id){
		
		T18ExtrasPoints mdl = T18ExtrasPoints.dao.findFirst("select * from t18ExtrasPoints where id=?", id);
		return mdl;
	}
}
