package puresport.mvc.t2adiv;

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
 * /jf/puresport/t2Adiv
 * /jf/puresport/t2Adiv/save
 * /jf/puresport/t2Adiv/edit
 * /jf/puresport/t2Adiv/update
 * /jf/puresport/t2Adiv/view
 * /jf/puresport/t2Adiv/delete
 * /puresport/t2Adiv/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t2Adiv")
public class T2AdivController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T2AdivController.class);

	public static final String pthc = "/jf/puresport/t2Adiv/";
	public static final String pthv = "/puresport/t2Adiv/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T2Adiv.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T2AdivValidator.class)
	public void save() {
		T2Adiv t2Adiv = getModel(T2Adiv.class);
		//other set 
		
		//t2Adiv.save();		//guiid
		t2Adiv.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T2Adiv t2Adiv = T2Adiv.dao.findById(getPara());	//guuid
		T2Adiv t2Adiv = T2AdivService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t2Adiv", t2Adiv);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T2AdivValidator.class)
	public void update() {
		getModel(T2Adiv.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T2Adiv t2Adiv = T2Adiv.dao.findById(getPara());	//guuid
		T2Adiv t2Adiv = T2AdivService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t2Adiv", t2Adiv);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T2AdivService.service.delete("t2_adiv", getPara() == null ? ids : getPara());	//guuid
		T2AdivService.service.deleteById("t2_adiv", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
