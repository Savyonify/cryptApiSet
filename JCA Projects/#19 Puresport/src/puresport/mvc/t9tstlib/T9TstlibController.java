package puresport.mvc.t9tstlib;

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
 * /jf/puresport/t9Tstlib
 * /jf/puresport/t9Tstlib/save
 * /jf/puresport/t9Tstlib/edit
 * /jf/puresport/t9Tstlib/update
 * /jf/puresport/t9Tstlib/view
 * /jf/puresport/t9Tstlib/delete
 * /puresport/t9Tstlib/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t9Tstlib")
public class T9TstlibController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T9TstlibController.class);

	public static final String pthc = "/jf/puresport/t9Tstlib/";
	public static final String pthv = "/puresport/t9Tstlib/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T9Tstlib.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T9TstlibValidator.class)
	public void save() {
		T9Tstlib t9Tstlib = getModel(T9Tstlib.class);
		//other set 
		
		//t9Tstlib.save();		//guiid
		t9Tstlib.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T9Tstlib t9Tstlib = T9Tstlib.dao.findById(getPara());	//guuid
		T9Tstlib t9Tstlib = T9TstlibService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t9Tstlib", t9Tstlib);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T9TstlibValidator.class)
	public void update() {
		getModel(T9Tstlib.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T9Tstlib t9Tstlib = T9Tstlib.dao.findById(getPara());	//guuid
		T9Tstlib t9Tstlib = T9TstlibService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t9Tstlib", t9Tstlib);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T9TstlibService.service.delete("t9_tstlib", getPara() == null ? ids : getPara());	//guuid
		T9TstlibService.service.deleteById("t9_tstlib", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
