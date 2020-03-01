package puresport.mvc.t15group;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Record;

import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.mvc.comm.ResTips;
import puresport.mvc.t6mgrahr.T6MgrSession;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/puresport/t15Group
 * /jf/puresport/t15Group/save
 * /jf/puresport/t15Group/edit
 * /jf/puresport/t15Group/update
 * /jf/puresport/t15Group/view
 * /jf/puresport/t15Group/delete
 * /puresport/t15Group/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t15Group")
public class T15GroupController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T15GroupController.class);

	public static final String pthc = "/jf/puresport/t15Group/";
	public static final String pthv = "/puresport/t15Group/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T15Group.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	@Clear
	public void fetchGroup() {
		
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderWithPath(pthv + "login.html");
			return;
		}
		renderJson(T15GroupService.service.fetchGroups(mgrSession.getUsrid()));
	}
	
	@Clear
	public void addGroup() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderWithPath(pthv + "login.html");
			return;
		}
		
		String title = getPara("title");
		if (StringUtils.isBlank(title) || title.length() > 100) {
			renderTextJson(ResTips.createFailRes("标题格式不正确"));
			return ;
		}
		
		Record group = new Record()
				.set(T15Group.column_title, title)
				.set(T15Group.column_mgr_id, mgrSession.getUsrid());
		
		boolean res = ConfMain.db().save(T15Group.tableName, "id", group);
		
		if (res) {
			renderJson(ResTips.createSuccRes(group));
		} else {
			renderTextJson(ResTips.createFailRes());
		}
		return ;
	}

	@Clear
	public void updateGroup() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
			return;
		}
		
		Long id = getParaToLong("id");
		String title = getPara("title");
		
		if (null == id || StringUtils.isBlank(title) || title.length() > 100) {
			renderTextJson(ResTips.createFailRes("格式不正确"));
			return ;
		}
		
		T15Group group = T15Group.dao.findById(id);
		if (null == group || !group.getMgr_id().equals(mgrSession.getUsrid())) {
			renderTextJson(ResTips.createFailRes("格式不正确"));
			return ;
		}
		group.setTitle(title);
		boolean res = group.update();
		
		if (res) {
			renderJson(ResTips.createSuccRes(group));
		} else {
			renderTextJson(ResTips.createFailRes());
		}
		return ;
	}
	
	@Clear
	public void delGroup() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
			return;
		}
		
		Long id = getParaToLong("id");

		if (null == id ) {
			renderTextJson(ResTips.createFailRes("格式不正确"));
			return ;
		}
		
		T15Group group = T15Group.dao.findById(id);
		if (null == group || !group.getMgr_id().equals(mgrSession.getUsrid())) {
			renderTextJson(ResTips.createFailRes("格式不正确"));
			return ;
		}
		// 放入一个事务中
		
		boolean res;
		try {
			res = T15GroupService.service.delGroup(group);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		
		if (res) {
			renderTextJson(ResTips.createSuccRes());
		} else {
			renderTextJson(ResTips.createFailRes());
		}
		return ;
	}
	
	
	/**
	 * 保存
	 */
	@Before(T15GroupValidator.class)
	public void save() {
		T15Group t15Group = getModel(T15Group.class);
		//other set 
		
		//t15Group.save();		//guiid
		t15Group.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T15Group t15Group = T15Group.dao.findById(getPara());	//guuid
		T15Group t15Group = T15GroupService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t15Group", t15Group);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T15GroupValidator.class)
	public void update() {
		getModel(T15Group.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T15Group t15Group = T15Group.dao.findById(getPara());	//guuid
		T15Group t15Group = T15GroupService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t15Group", t15Group);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T15GroupService.service.delete("t15_group", getPara() == null ? ids : getPara());	//guuid
		T15GroupService.service.deleteById("t15_group", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
