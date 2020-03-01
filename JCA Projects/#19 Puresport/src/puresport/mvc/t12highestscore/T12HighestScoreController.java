package puresport.mvc.t12highestscore;

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
 * /jf/puresport/t12HighestScore
 * /jf/puresport/t12HighestScore/save
 * /jf/puresport/t12HighestScore/edit
 * /jf/puresport/t12HighestScore/update
 * /jf/puresport/t12HighestScore/view
 * /jf/puresport/t12HighestScore/delete
 * /puresport/t12HighestScore/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t12HighestScore")
public class T12HighestScoreController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T12HighestScoreController.class);

	public static final String pthc = "/jf/puresport/t12HighestScore/";
	public static final String pthv = "/puresport/t12HighestScore/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T12HighestScore.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T12HighestScoreValidator.class)
	public void save() {
		T12HighestScore t12HighestScore = getModel(T12HighestScore.class);
		//other set 
		
		//t12HighestScore.save();		//guiid
		t12HighestScore.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T12HighestScore t12HighestScore = T12HighestScore.dao.findById(getPara());	//guuid
		T12HighestScore t12HighestScore = T12HighestScoreService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t12HighestScore", t12HighestScore);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T12HighestScoreValidator.class)
	public void update() {
		getModel(T12HighestScore.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T12HighestScore t12HighestScore = T12HighestScore.dao.findById(getPara());	//guuid
		T12HighestScore t12HighestScore = T12HighestScoreService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t12HighestScore", t12HighestScore);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T12HighestScoreService.service.delete("t12_highest_score", getPara() == null ? ids : getPara());	//guuid
		T12HighestScoreService.service.deleteById("t12_highest_score", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
