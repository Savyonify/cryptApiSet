package puresport.mvc.t12highestscore;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T12HighestScoreService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T12HighestScoreService.class);
	
	public static final T12HighestScoreService service = Enhancer.enhance(T12HighestScoreService.class);
	
	public T12HighestScore SelectById(Integer id){
		
		T12HighestScore mdl = T12HighestScore.dao.findFirst("select * from t12HighestScore where id=?", id);
		return mdl;
	}
}
