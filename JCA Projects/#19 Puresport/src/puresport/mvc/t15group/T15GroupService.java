package puresport.mvc.t15group;

import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Before;
import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.platform.mvc.base.BaseService;

import puresport.mvc.r16groupusr.R16GroupUsrService;

public class T15GroupService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T15GroupService.class);
	
	public static final T15GroupService service = Enhancer.enhance(T15GroupService.class);
	
	public T15Group SelectById(Integer id){
		
		T15Group mdl = T15Group.dao.findFirst("select * from t15_group where id=?", id);
		return mdl;
	}
	
	public List<T15Group> fetchGroups(Long mgrId){
		return T15Group.dao.find("select * from t15_group where mgr_id=?", mgrId);
	}
	
	@Before(Tx.class)
	public boolean delGroup(T15Group group) throws Exception {
		boolean res1 = group.delete();
		if (!res1) {
			return false;
		}
		boolean res2 = R16GroupUsrService.service.deleteByGroupId(group.getId());
//		if (!res2) {
//			throw new Exception("R16GroupUsrService deleteByGroupId failed");
//		}
		
		return true;
	}
}
