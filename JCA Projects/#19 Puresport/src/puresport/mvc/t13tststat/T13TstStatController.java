package puresport.mvc.t13tststat;

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
 * /jf/puresport/t13TstStat
 * /jf/puresport/t13TstStat/save
 * /jf/puresport/t13TstStat/edit
 * /jf/puresport/t13TstStat/update
 * /jf/puresport/t13TstStat/view
 * /jf/puresport/t13TstStat/delete
 * /puresport/t13TstStat/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t13TstStat")
public class T13TstStatController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T13TstStatController.class);

	public static final String pthc = "/jf/puresport/t13TstStat/";
	public static final String pthv = "/puresport/t13TstStat/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T13TstStat.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T13TstStatValidator.class)
	public void save() {
		T13TstStat t13TstStat = getModel(T13TstStat.class);
		//other set 
		
		//t13TstStat.save();		//guiid
		t13TstStat.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T13TstStat t13TstStat = T13TstStat.dao.findById(getPara());	//guuid
		T13TstStat t13TstStat = T13TstStatService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t13TstStat", t13TstStat);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T13TstStatValidator.class)
	public void update() {
		getModel(T13TstStat.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T13TstStat t13TstStat = T13TstStat.dao.findById(getPara());	//guuid
		T13TstStat t13TstStat = T13TstStatService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t13TstStat", t13TstStat);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T13TstStatService.service.delete("t13_tst_stat", getPara() == null ? ids : getPara());	//guuid
		T13TstStatService.service.deleteById("t13_tst_stat", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
