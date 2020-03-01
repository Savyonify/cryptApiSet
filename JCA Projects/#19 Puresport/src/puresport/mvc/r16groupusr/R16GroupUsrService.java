package puresport.mvc.r16groupusr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;

import com.platform.mvc.base.BaseService;

import puresport.config.ConfMain;
import puresport.mvc.comm.CommFun;
import puresport.mvc.comm.ParamComm;

public class R16GroupUsrService extends BaseService {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(R16GroupUsrService.class);
	
	public static final R16GroupUsrService service = Enhancer.enhance(R16GroupUsrService.class);
	
	public R16GroupUsr SelectById(Integer id){
		
		R16GroupUsr mdl = R16GroupUsr.dao.findFirst("select * from r16_group_usr where id=?", id);
		return mdl;
	}
	
	public List<R16GroupUsr> selectByGroupIds(List<Long> groupIds, ParamComm paramMdl){
		
		if (CollectionUtils.isEmpty(groupIds)) {
			return Collections.EMPTY_LIST;
		}
		
		Long countTotal = ConfMain.db().queryLong("select count(1) from r16_group_usr where group_id in "+CommFun.sqlWhereIn(groupIds), groupIds.toArray());
		paramMdl.setTotal(countTotal);
		
		List<Object> params = new ArrayList<Object>();
		params.addAll(groupIds);
		params.add(paramMdl.getPageIndex());
		params.add(paramMdl.getPageSize());
		
		List<R16GroupUsr> list = R16GroupUsr.dao.find(
				String.format("select * from r16_group_usr where group_id in %s limit ?, ? "
						, CommFun.sqlWhereIn(groupIds)), params.toArray());
		
		return CollectionUtils.isEmpty(list)? Collections.EMPTY_LIST:list;
	}
	
	public boolean deleteByGroupId(Long groupId) {
		return ConfMain.db().deleteById(R16GroupUsr.tableName, "group_id", groupId);
	}
	
	public boolean removeUserFromGroup(Long groupId, List<Long> userIds) {
		String sql = String.format("delete from %s where group_id=? and user_id in %s "
				,R16GroupUsr.tableName, CommFun.sqlWhereIn(userIds));
		List<Object> params = new ArrayList<Object>();
		params.add(groupId);
		params.addAll(userIds);
		
		ConfMain.db().update(sql, params.toArray());
		
		return true;
	}
}
