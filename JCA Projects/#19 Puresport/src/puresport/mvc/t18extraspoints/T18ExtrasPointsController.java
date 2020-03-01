package puresport.mvc.t18extraspoints;

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
 * /jf/puresport/t18ExtrasPoints
 * /jf/puresport/t18ExtrasPoints/save
 * /jf/puresport/t18ExtrasPoints/edit
 * /jf/puresport/t18ExtrasPoints/update
 * /jf/puresport/t18ExtrasPoints/view
 * /jf/puresport/t18ExtrasPoints/delete
 * /puresport/t18ExtrasPoints/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t18ExtrasPoints")
public class T18ExtrasPointsController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T18ExtrasPointsController.class);

	public static final String pthc = "/jf/puresport/t18ExtrasPoints/";
	public static final String pthv = "/puresport/t18ExtrasPoints/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T18ExtrasPoints.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T18ExtrasPointsValidator.class)
	public void save() {
		T18ExtrasPoints t18ExtrasPoints = getModel(T18ExtrasPoints.class);
		//other set 
		
		//t18ExtrasPoints.save();		//guiid
		t18ExtrasPoints.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T18ExtrasPoints t18ExtrasPoints = T18ExtrasPoints.dao.findById(getPara());	//guuid
		T18ExtrasPoints t18ExtrasPoints = T18ExtrasPointsService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t18ExtrasPoints", t18ExtrasPoints);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T18ExtrasPointsValidator.class)
	public void update() {
		getModel(T18ExtrasPoints.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T18ExtrasPoints t18ExtrasPoints = T18ExtrasPoints.dao.findById(getPara());	//guuid
		T18ExtrasPoints t18ExtrasPoints = T18ExtrasPointsService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t18ExtrasPoints", t18ExtrasPoints);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T18ExtrasPointsService.service.delete("t18_extras_points", getPara() == null ? ids : getPara());	//guuid
		T18ExtrasPointsService.service.deleteById("t18_extras_points", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
