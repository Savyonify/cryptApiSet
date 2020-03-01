package puresport.mvc.t3statl;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import org.apache.log4j.Logger;
import com.jfinal.aop.Before;

import puresport.constant.ConstantInitMy;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/puresport/t3Statl
 * /jf/puresport/t3Statl/save
 * /jf/puresport/t3Statl/edit
 * /jf/puresport/t3Statl/update
 * /jf/puresport/t3Statl/view
 * /jf/puresport/t3Statl/delete
 * /puresport/t3Statl/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t3Statl")
public class T3StatlController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T3StatlController.class);

	public static final String pthc = "/jf/puresport/t3Statl/";
	public static final String pthv = "/puresport/t3Statl/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T3Statl.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T3StatlValidator.class)
	public void save() {
		T3Statl t3Statl = getModel(T3Statl.class);
		//other set 
		
		//t3Statl.save();		//guiid
		t3Statl.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T3Statl t3Statl = T3Statl.dao.findById(getPara());	//guuid
		T3Statl t3Statl = T3StatlService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t3Statl", t3Statl);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T3StatlValidator.class)
	public void update() {
		getModel(T3Statl.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T3Statl t3Statl = T3Statl.dao.findById(getPara());	//guuid
		T3Statl t3Statl = T3StatlService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t3Statl", t3Statl);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T3StatlService.service.delete("t3_stat", getPara() == null ? ids : getPara());	//guuid
		T3StatlService.service.deleteById("t3_stat", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
