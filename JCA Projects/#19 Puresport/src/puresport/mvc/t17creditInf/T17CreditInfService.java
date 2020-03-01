package puresport.mvc.t17creditInf;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T17CreditInfService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T17CreditInfService.class);
	
	public static final T17CreditInfService service = Enhancer.enhance(T17CreditInfService.class);
	
	public T17CreditInf SelectById(Integer id){
		
		T17CreditInf mdl = T17CreditInf.dao.findFirst("select * from t15CreditInf where id=?", id);
		return mdl;
	}
}
