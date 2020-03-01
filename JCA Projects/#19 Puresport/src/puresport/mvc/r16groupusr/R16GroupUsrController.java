package puresport.mvc.r16groupusr;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Record;

import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.mvc.comm.CommWrapper;
import puresport.mvc.comm.ParamComm;
import puresport.mvc.comm.ResTips;
import puresport.mvc.t15group.T15Group;
import puresport.mvc.t1usrbsc.T1usrBsc;
import puresport.mvc.t1usrbsc.T1usrBscService;
import puresport.mvc.t6mgrahr.T6MgrSession;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/puresport/r16GroupUsr
 * /jf/puresport/r16GroupUsr/save
 * /jf/puresport/r16GroupUsr/edit
 * /jf/puresport/r16GroupUsr/update
 * /jf/puresport/r16GroupUsr/view
 * /jf/puresport/r16GroupUsr/delete
 * /puresport/r16GroupUsr/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/r16GroupUsr")
public class R16GroupUsrController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(R16GroupUsrController.class);

	public static final String pthc = "/jf/puresport/r16GroupUsr/";
	public static final String pthv = "/puresport/r16GroupUsr/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, R16GroupUsr.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	@Clear
	public void getGroupUsers() {
		
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderTextJson(ResTips.createFailRes("页面信息已过期，请刷新页面!"));
			return;
		}
		Long mgrId = mgrSession.getUsrid();
		
		ParamComm param = getParamWithServerPage();
		
		Long groupId = param.getGroupId();
		if (null == groupId) {
			renderTextJson(ResTips.createFailRes("格式不正确"));
			return ;
		}
		List<Long> groupIds = new ArrayList<Long>();
		if (groupId.equals(0L)) {// 全部分组
			List<T15Group> groups = T15Group.dao.find("select * from t15_group where mgr_id = ? limit 1000 ", mgrSession.getUsrid());
			if (CollectionUtils.isNotEmpty(groups)) {
				groups.forEach(g->groupIds.add(g.getId()));
			}
		} else {
			T15Group group = T15Group.dao.findById(groupId);
			if (null == group || !group.getMgr_id().equals(mgrId)) {
				renderTextJson(ResTips.createFailRes("格式不正确"));
				return ;
			}
			groupIds.add(groupId);
		}
		List<R16GroupUsr> groupUserIds = R16GroupUsrService.service.selectByGroupIds(groupIds, getParamWithServerPage());
		List<Long> userIds = groupUserIds.stream().map(R16GroupUsr::getUser_id).collect(Collectors.toList());
		List<T1usrBsc> users = T1usrBscService.service.selectByIds(userIds)	;
		
		renderJsonForTable(users);
	}
	
	/**
	 * 修改人员的分组
	 */
	@Clear
	public void updateUserGroup() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderTextJson(ResTips.createFailRes("页面信息已过期，请刷新页面!"));
			return;
		}
		
		R16GroupUsrDTO dto = getParamWithClass(R16GroupUsrDTO.class);
		if (null == dto) {
			renderTextJson(ResTips.createFailRes("格式不正确"));
			return ;
		}
		
		Long groupId = dto.getId();
//		String title = dto.getTitle();
		List<Long> userIds = dto.getUserIds();
		
		if (null == groupId || CollectionUtils.isEmpty(userIds)) {
			renderTextJson(ResTips.createFailRes("格式不正确"));
			return ;
		}
		
		T15Group group = T15Group.dao.findById(groupId);
		if (null == group || !group.getMgr_id().equals(mgrSession.getUsrid())) {
			renderTextJson(ResTips.createFailRes("格式不正确"));
			return ;
		}

		CommWrapper<Boolean> hasFailed = new CommWrapper<Boolean>(false);
		userIds.forEach(userId->{
			
			String uniqueKey = R16GroupUsr.column_group_id+","+R16GroupUsr.column_user_id;
			
			Record existRow = ConfMain.db().findById(R16GroupUsr.tableName, uniqueKey, groupId, userId);
			if (existRow == null) {
				Record row = new Record()
						.set(R16GroupUsr.column_group_id, groupId)
						.set(R16GroupUsr.column_user_id, userId);
				
				if(!ConfMain.db().save(R16GroupUsr.tableName, "id", row)) {
					hasFailed.setObj(true);
				}
			}
			
		});
		
		if (hasFailed.getObj().equals(true)) {
			renderTextJson(ResTips.createFailRes("系统繁忙，请稍后重试"));
		} else {
			renderTextJson(ResTips.createSuccRes());
		}
		
		return ;
	}

	/**
	 * 从分组中移出人员
	 */
	public void removeFromGroup() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderTextJson(ResTips.createFailRes("页面信息已过期，请刷新页面!"));
			return;
		}
		R16GroupUsrDTO dto = getParamWithClass(R16GroupUsrDTO.class);
		if (null == dto) {
			renderTextJson(ResTips.createFailRes("格式不正确"));
			return ;
		}
		
		Long groupId = dto.getId();
//		String title = dto.getTitle();
		List<Long> userIds = dto.getUserIds();
		
		if (null == groupId || CollectionUtils.isEmpty(userIds)) {
			renderTextJson(ResTips.createFailRes("格式不正确"));
			return ;
		}
		
		T15Group group = T15Group.dao.findById(groupId);
		if (null == group || !group.getMgr_id().equals(mgrSession.getUsrid())) {
			renderTextJson(ResTips.createFailRes("格式不正确"));
			return ;
		}
		
		R16GroupUsrService.service.removeUserFromGroup(groupId, userIds);
		
		renderTextJson(ResTips.createSuccRes());
		return ;
	}
	/**
	 * 保存
	 */
	@Before(R16GroupUsrValidator.class)
	public void save() {
		R16GroupUsr r16GroupUsr = getModel(R16GroupUsr.class);
		//other set 
		
		//r16GroupUsr.save();		//guiid
		r16GroupUsr.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//R16GroupUsr r16GroupUsr = R16GroupUsr.dao.findById(getPara());	//guuid
		R16GroupUsr r16GroupUsr = R16GroupUsrService.service.SelectById(getParaToInt());		//serial int id
		setAttr("r16GroupUsr", r16GroupUsr);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(R16GroupUsrValidator.class)
	public void update() {
		getModel(R16GroupUsr.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//R16GroupUsr r16GroupUsr = R16GroupUsr.dao.findById(getPara());	//guuid
		R16GroupUsr r16GroupUsr = R16GroupUsrService.service.SelectById(getParaToInt());		//serial int id
		setAttr("r16GroupUsr", r16GroupUsr);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//R16GroupUsrService.service.delete("r16_group_usr", getPara() == null ? ids : getPara());	//guuid
		R16GroupUsrService.service.deleteById("r16_group_usr", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
