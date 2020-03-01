package puresport.mvc.t10examgrd;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;

import puresport.constant.ConstantInitMy;
import puresport.mvc.t1usrbsc.T1usrBsc;


/**
 * XXX 管理	
 * 描述：
 * 
 * /jf/puresport/t10ExamGrd
 * /jf/puresport/t10ExamGrd/save
 * /jf/puresport/t10ExamGrd/edit
 * /jf/puresport/t10ExamGrd/update
 * /jf/puresport/t10ExamGrd/view
 * /jf/puresport/t10ExamGrd/delete
 * /puresport/t10ExamGrd/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t10ExamGrd")
public class T10ExamGrdController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T10ExamGrdController.class);

	public static final String pthc = "/jf/puresport/t10ExamGrd/";
	public static final String pthv = "/puresport/t10ExamGrd/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T10ExamGrd.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T10ExamGrdValidator.class)
	public void save() {
		T10ExamGrd t10ExamGrd = getModel(T10ExamGrd.class);
		//other set 
		
		//t10ExamGrd.save();		//guiid
		t10ExamGrd.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T10ExamGrd t10ExamGrd = T10ExamGrd.dao.findById(getPara());	//guuid
		T10ExamGrd t10ExamGrd = T10ExamGrdService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t10ExamGrd", t10ExamGrd);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T10ExamGrdValidator.class)
	public void update() {
		getModel(T10ExamGrd.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T10ExamGrd t10ExamGrd = T10ExamGrd.dao.findById(getPara());	//guuid
		T10ExamGrd t10ExamGrd = T10ExamGrdService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t10ExamGrd", t10ExamGrd);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T10ExamGrdService.service.delete("t10_exam_grd", getPara() == null ? ids : getPara());	//guuid
		T10ExamGrdService.service.deleteById("t10_exam_grd", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
	
}
