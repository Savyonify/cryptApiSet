package puresport.mvc.t17creditInf;

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
 * /jf/puresport/t15CreditInf
 * /jf/puresport/t15CreditInf/save
 * /jf/puresport/t15CreditInf/edit
 * /jf/puresport/t15CreditInf/update
 * /jf/puresport/t15CreditInf/view
 * /jf/puresport/t15CreditInf/delete
 * /puresport/t15CreditInf/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t15CreditInf")
public class T17CreditInfController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T17CreditInfController.class);

	public static final String pthc = "/jf/puresport/t15CreditInf/";
	public static final String pthv = "/puresport/t15CreditInf/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T17CreditInf.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T17CreditInfValidator.class)
	public void save() {
		T17CreditInf t15CreditInf = getModel(T17CreditInf.class);
		//other set 
		
		//t15CreditInf.save();		//guiid
		t15CreditInf.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T15CreditInf t15CreditInf = T15CreditInf.dao.findById(getPara());	//guuid
		T17CreditInf t15CreditInf = T17CreditInfService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t15CreditInf", t15CreditInf);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T17CreditInfValidator.class)
	public void update() {
		getModel(T17CreditInf.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T15CreditInf t15CreditInf = T15CreditInf.dao.findById(getPara());	//guuid
		T17CreditInf t15CreditInf = T17CreditInfService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t15CreditInf", t15CreditInf);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T15CreditInfService.service.delete("t15_credit_inf", getPara() == null ? ids : getPara());	//guuid
		T17CreditInfService.service.deleteById("t15_credit_inf", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
