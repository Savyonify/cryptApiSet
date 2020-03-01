package puresport.mvc.t8exam;

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
 * /jf/puresport/t8Exam
 * /jf/puresport/t8Exam/save
 * /jf/puresport/t8Exam/edit
 * /jf/puresport/t8Exam/update
 * /jf/puresport/t8Exam/view
 * /jf/puresport/t8Exam/delete
 * /puresport/t8Exam/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t8Exam")
public class T8ExamController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T8ExamController.class);

	public static final String pthc = "/jf/puresport/t8Exam/";
	public static final String pthv = "/puresport/t8Exam/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T8Exam.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T8ExamValidator.class)
	public void save() {
		T8Exam t8Exam = getModel(T8Exam.class);
		//other set 
		
		//t8Exam.save();		//guiid
		t8Exam.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T8Exam t8Exam = T8Exam.dao.findById(getPara());	//guuid
		T8Exam t8Exam = T8ExamService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t8Exam", t8Exam);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T8ExamValidator.class)
	public void update() {
		getModel(T8Exam.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T8Exam t8Exam = T8Exam.dao.findById(getPara());	//guuid
		T8Exam t8Exam = T8ExamService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t8Exam", t8Exam);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T8ExamService.service.delete("t8_exam", getPara() == null ? ids : getPara());	//guuid
		T8ExamService.service.deleteById("t8_exam", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
