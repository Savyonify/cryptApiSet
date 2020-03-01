package puresport.mvc.t7crcl;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

public class T7CrclService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T7CrclService.class);
	
	public static final T7CrclService service = Enhancer.enhance(T7CrclService.class);
	
	public T7Crcl SelectById(Integer id){
		
		T7Crcl mdl = T7Crcl.dao.findFirst("select * from t7_Crcl where crclid=?", id);
		return mdl;
	}
	public T7Crcl SelectByVideoID(String videoid){
		
		T7Crcl mdl = T7Crcl.dao.findFirst("select * from t7_Crcl where crcl_file_rte=?", videoid);
		return mdl;
	}
	public List<T7Crcl> SelectBycrcl_attr(String crcl_attr){
		
		List<T7Crcl> mdl = T7Crcl.dao.find("select * from t7_Crcl where crcl_attr=?", crcl_attr);
		return mdl;
	}
}
