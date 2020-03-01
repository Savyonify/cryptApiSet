package puresport.mvc.t14invitationcode;

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
 * /jf/puresport/t14InvitationCode
 * /jf/puresport/t14InvitationCode/save
 * /jf/puresport/t14InvitationCode/edit
 * /jf/puresport/t14InvitationCode/update
 * /jf/puresport/t14InvitationCode/view
 * /jf/puresport/t14InvitationCode/delete
 * /puresport/t14InvitationCode/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t14InvitationCode")
public class T14InvitationCodeController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T14InvitationCodeController.class);

	public static final String pthc = "/jf/puresport/t14InvitationCode/";
	public static final String pthv = "/puresport/t14InvitationCode/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select, T14InvitationCode.sqlId_splitPage_from);
		renderWithPath(pthv+"list.html");
	}
	
	/**
	 * 保存
	 */
	@Before(T14InvitationCodeValidator.class)
	public void save() {
		T14InvitationCode t14InvitationCode = getModel(T14InvitationCode.class);
		//other set 
		
		//t14InvitationCode.save();		//guiid
		t14InvitationCode.saveGenIntId();	//serial int id
		renderWithPath(pthv+"add.html");
	}
	
	/**
	 * 准备更新
	 */
	public void edit() {
		//T14InvitationCode t14InvitationCode = T14InvitationCode.dao.findById(getPara());	//guuid
		T14InvitationCode t14InvitationCode = T14InvitationCodeService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t14InvitationCode", t14InvitationCode);
		renderWithPath(pthv+"update.html");

	}
	
	/**
	 * 更新
	 */
	@Before(T14InvitationCodeValidator.class)
	public void update() {
		getModel(T14InvitationCode.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		//T14InvitationCode t14InvitationCode = T14InvitationCode.dao.findById(getPara());	//guuid
		T14InvitationCode t14InvitationCode = T14InvitationCodeService.service.SelectById(getParaToInt());		//serial int id
		setAttr("t14InvitationCode", t14InvitationCode);
		renderWithPath(pthv+"view.html");
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		//T14InvitationCodeService.service.delete("t14_invitation_code", getPara() == null ? ids : getPara());	//guuid
		T14InvitationCodeService.service.deleteById("t14_invitation_code", getPara() == null ? ids : getPara());	//serial int id
		redirect(pthc);
	}
	
	public void setViewPath(){
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}
	
}
