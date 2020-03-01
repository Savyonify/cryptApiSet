package puresport.mvc.t4assc;

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
 * /jf/puresport/t4Assc
 * /jf/puresport/t4Assc/save
 * /jf/puresport/t4Assc/edit
 * /jf/puresport/t4Assc/update
 * /jf/puresport/t4Assc/view
 * /jf/puresport/t4Assc/delete
 * /puresport/t4Assc/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t4Assc")
public class T4AsscController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T4AsscController.class);

	public static final String pthc = "/jf/puresport/t4Assc/";
	public static final String pthv = "/puresport/t4Assc/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T4Assc.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T4AsscValidator.class)
	public void save() {
		T4Assc t4Assc = getModel(T4Assc.class);
		//other set 
		
		//t4Assc.save();		//guiid
		t4Assc.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T4Assc t4Assc = T4Assc.dao.findById(getPara());	//guuid
		T4Assc t4Assc = T4AsscService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t4Assc", t4Assc);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T4AsscValidator.class)
	public void update() {
		getModel(T4Assc.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T4Assc t4Assc = T4Assc.dao.findById(getPara());	//guuid
		T4Assc t4Assc = T4AsscService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t4Assc", t4Assc);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T4AsscService.service.delete("t4_assc", getPara() == null ? ids : getPara());	//guuid
		T4AsscService.service.deleteById("t4_assc", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
