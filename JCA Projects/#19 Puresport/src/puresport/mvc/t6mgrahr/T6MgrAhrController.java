package puresport.mvc.t6mgrahr;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import csuduc.platform.util.ComOutMdl;
import csuduc.platform.util.ComUtil;
import csuduc.platform.util.JsonUtils;
import csuduc.platform.util.RegexUtils;
import csuduc.platform.util.StringUtil;
import csuduc.platform.util.encrypt.DESUtil;
import csuduc.platform.util.tuple.Tuple2;
import puresport.applicat.ExcelParseTool;
import puresport.applicat.MdlExcelRow;
import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.constant.EnumStatus;
import puresport.constant.EnumTypeLevel;
import puresport.mvc.comm.AuthCodeMdl;
import puresport.mvc.comm.CommFun;
import puresport.mvc.comm.PageViewSta;
import puresport.mvc.comm.ParamComm;
import puresport.mvc.comm.ResTips;
import puresport.mvc.t1usrbsc.T1usrBsc;
import puresport.mvc.t1usrbsc.T1usrBscController;

/**
 * XXX 管理 描述：
 * 
 * /jf/puresport/t6MgrAhr /jf/puresport/t6MgrAhr/save
 * /jf/puresport/t6MgrAhr/edit /jf/puresport/t6MgrAhr/update
 * /jf/puresport/t6MgrAhr/view /jf/puresport/t6MgrAhr/delete
 * /puresport/t6MgrAhr/add.html
 * 
 */
// @Controller(controllerKey = "/jf/puresport/t6MgrAhr")
public class T6MgrAhrController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T6MgrAhrController.class);

	public static final String pthc = "/jf/puresport/t6MgrAhr/";
	public static final String pthv = "/f/";

	/**
	 * 列表
	 */
	@Clear
	public void login() {
		boolean flag = false;
		boolean needImproveInfo = false;
		String msg = "";
		JSONObject json = new JSONObject();
		boolean authCode = authCode();
		if (!authCode) {
			renderJson(CommFun.resJsonFail("验证码不正确"));
			return;
		}
		String account = getPara("account");
		String password = getPara("pwd");

		if (ComUtil.haveEmpty(account, password)) {
			renderIllegal();
			return;
		}

		Integer accountType = RegexUtils.checkPhoneOrEmailOrID(account);
		if (accountType == 0) {
			renderIllegal();
			return;
		}

		try {

			String accountFieldName = accountType == 1 ? "mblph_no" : (accountType == 2 ? "email" : "crdt_no");
			T6MgrAhr item = T6MgrAhr.dao
					.findFirst(String.format("select * from t6_mgr_ahr where %s=? limit 1", accountFieldName), account);
			if (null == item) {
				renderJson(CommFun.resJsonFail(EnumStatus.Null_Account));
				return;
			}

			String encryptpassword = DESUtil.encrypt(password, ConstantInitMy.SPKEY);
			String pwddd = item.getPswd();
			if (!encryptpassword.equals(pwddd)) {
				renderJson(CommFun.resJsonFail(EnumStatus.Illegal_Pwd));
				return;
			}

			PageViewSta.StaLoginPeopleCountByDay();
			setSessionAttr("usrid", item.getUsrid());// 设置session，保存登录用户的昵称
			setSessionAttr("crdt_no", item.getCrdt_no());// 设置session，保存登录用户的昵称
			setSessionAttr("pwd", item.getPswd());// 设置session，保存登录用户的昵称
			setSessionAttr("usr_tp", item.getUsr_tp());// 设置session，保存登录用户的昵称
			setSessionAttr("typeleve", item.getTypeleve());// 设置session，保存登录用户的昵称
			T6MgrSession mgrSession = new T6MgrSession(item);
			setSessionAttr(T6MgrSession.KeyName, mgrSession);// 管理员session对象

			String institute = item.getInstitute();
			Integer emailVal = item.getEmailVal();
			Integer phoneVal = item.getMblPhVal();
			Boolean needValEmail = (null == emailVal) || (emailVal == 0);
			Boolean needValPhone = (null == phoneVal) || (phoneVal == 0);
			Boolean needValInstitute = item.getTypeleve().equals("中心协会级") && StringUtils.isBlank(institute);
			needImproveInfo = needValEmail || needValPhone || needValInstitute;

			json.put("needValEmail", needValEmail);
			json.put("needValPhone", needValPhone);
			json.put("needValInstitute", needValInstitute);
			json.put("needImproveInfo", needImproveInfo);

			setSessionAttr("needValEmail", needValEmail);
			setSessionAttr("needValPhone", needValPhone);
			setSessionAttr("needValInstitute", needValInstitute);

			json.put("flag", true);
			json.put("msg", msg);
			json.put("url", getCxt() + "/jf/puresport/pagesController/admin");
			renderJson(json);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			renderJson(CommFun.resJsonFail(EnumStatus.Error_Exception));
		}

	}

	@Clear
	public void ImproveAdminInfo() {

		Long userID = (Long) getSessionAttrForStr("usrid", Long.class);
		if (null == userID) {
			renderJson(CommFun.resJsonFail("登录信息已过期，请刷新页面重试！"));
			return;
		}

		boolean flag = false;
		String msg = "";
		JSONObject json = new JSONObject();

		T6MgrAhr item = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where usrid=?", userID);// 根据用户名查询数据库中的用户
		if (null == item) {
			renderJson(CommFun.resJsonFail("更新失败"));
			return;
		}
		
		Boolean needValEmail = (Boolean) getSessionAttr("needValEmail");
		Boolean needValPhone = (Boolean) getSessionAttr("needValPhone");
		Boolean needValInstitute = (Boolean) getSessionAttr("needValInstitute");
		
		StringBuilder sqlUpdate = new StringBuilder("update t6_mgr_ahr set nm=? ");
		List<Object> argList = new LinkedList<Object>();
		argList.add(item.getNm());
		
		boolean haveUpdate = false;
		
		if (needValEmail != null && needValEmail == Boolean.TRUE) {
			String email = getPara("email");
			String emailValCode = getPara("emailValCode");
			if (StringUtils.isBlank(email) || StringUtils.isBlank(emailValCode)
					|| !RegexUtils.checkEmail(email)) {
				renderJson(CommFun.resJsonFail("邮箱或验证码不正确或为空"));
				return;
			}
			AuthCodeMdl authCodeMdlEmail = (AuthCodeMdl) getSessionAttr(T1usrBscController.keyEmailCode);
			if (Objects.isNull(authCodeMdlEmail) 
					|| authCodeMdlEmail.checkAuthCodeFail(email, emailValCode,ConstantInitMy.AuthCode_TimeOut)) {
				renderTextJson(CommFun.resJsonFail("邮箱验证码错误或已过期，请重新获取验证"));
				return;
			}
			sqlUpdate.append(", email = ?, email_val=? ");
			argList.add(email);
			argList.add(1);
			haveUpdate = true;
		}
		
		if (needValPhone != null && needValPhone == Boolean.TRUE) {
			String phone = getPara("phone");
			String mblphValCode = getPara("mblphValCode");
			if (StringUtils.isBlank(phone) || StringUtils.isBlank(mblphValCode)
					|| !RegexUtils.checkMobile(phone)) {
				renderJson(CommFun.resJsonFail("手机或验证码不正确或为空"));
				return;
			}
			AuthCodeMdl authCodeMdlPhone = (AuthCodeMdl) getSessionAttr(T1usrBscController.keyPhoneCode);
			if (Objects.isNull(authCodeMdlPhone) 
					|| authCodeMdlPhone.checkAuthCodeFail(phone, mblphValCode, ConstantInitMy.AuthCode_TimeOut)) {
				renderTextJson(CommFun.resJsonFail("手机验证码错误或已过期，请重新获取验证"));
				return;
			}
			sqlUpdate.append(", mblph_no = ?, mblph_val=? ");
			argList.add(phone);
			argList.add(1);
			haveUpdate = true;
		}
		
		if (needValInstitute != null && needValInstitute == Boolean.TRUE) {
			String xiehuiItemName = getPara("xiehuiItemName");
			if (StringUtils.isBlank(xiehuiItemName)) {
				renderJson(CommFun.resJsonFail("协会信息不能为空"));
				return;
			}
			sqlUpdate.append(", institute=? ");
			argList.add(xiehuiItemName);
			haveUpdate = true;
		}
		
		if (!haveUpdate) {
			renderJson(CommFun.resJsonFail(EnumStatus.Illegal));
			return;
		}

		sqlUpdate.append(" where usrid=?");
		argList.add(userID);

		int res = ConfMain.db().update(sqlUpdate.toString(), argList.toArray());
		if (res > 0) {
			flag = true;
			T6MgrAhr newitem = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where usrid=?", userID);// 根据用户名查询数据库中的用户
			T6MgrSession mgrSession = new T6MgrSession(newitem);
			setSessionAttr(T6MgrSession.KeyName, mgrSession);// 管理员session对象
		}

		json.put("flag", flag);
		json.put("msg", msg);
		json.put("url", getCxt() + "/jf/puresport/pagesController/admin");
		renderJson(json);

	}

	@Clear
	public void index() {
		setAttr("username", "test");
		renderWithPath(pthv + "admin.html");
	}

	@Clear
	public void getData() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
			return;
		}
		renderJsonForTable(T6MgrAhrService.service.selectByPage(mgrSession, getParamWithServerPage()));
	}

	public void deleteManager() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
			return;
		}
		ParamComm paramComm = getParamCommDef();
		if (Objects.isNull(paramComm)) {
			log.error("deleteManager 参数解析失败");
			renderText("参数解析失败!");
			return;
		}
		renderText(T6MgrAhrService.service.delete(mgrSession, paramComm).second);
	}

	public void addTable() {
//		T6MgrAhr mdl = getModel(T6MgrAhr.class);
//	    // 检查手机号的用户是否存在
//		if (T6MgrAhrService.service.isExist(mdl)) {
//			ResTips errorTips = ResTips.getFailRes().addErroFiled(T6MgrAhr.column_crdt_no,"该证件号用户已存在");
//			renderJson(errorTips);
//			return ;
//		}
//		// 不存在则添加
//		mdl.set(T6MgrAhr.column_usr_nm, mdl.get(T6MgrAhr.column_nm));
//		
//		if (mdl.saveGenIntId()) {
//			renderJson(ResTips.getSuccRes());
//		} else {
//			renderJson(ResTips.getFailRes());
//		}
	}

	@Clear
	public void editTable() {
		T6MgrAhr mdl = getModel(T6MgrAhr.class);
		// 检查手机号的用户是否存在
		if (!T6MgrAhrService.service.isExist(mdl)) {
			// 不存在则不可以更新
			ResTips errorTips = ResTips.createFailRes().addErroFiled(T6MgrAhr.column_crdt_no, "该证件号用户不存在");
			renderJson(errorTips);
			return;
		}
		mdl.set(T6MgrAhr.column_usr_nm, mdl.get(T6MgrAhr.column_nm));
		if (mdl.update()) {
			renderJson(ResTips.createSuccRes());
		} else {
			renderJson(ResTips.createFailRes());
		}
	}

	@Clear
	public void inload() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
			return;
		}
		// 市级管理员不可导入管理员
		if (mgrSession.getTypeleve().equals(EnumTypeLevel.City.getName())) {
			renderText("您没有该权限!");
			return;
		}
		// 获取上传的excel文件
		String path = "files/upload/".trim();
		String base = this.getRequest().getContextPath().trim();// 应用路径
		UploadFile picFile = getFile("fileexcel");// 得到 文件对象
		String fileName = picFile.getFileName();
		String mimeType = picFile.getContentType();// 得到 上传文件的MIME类型:audio/mpeg

		String mimeTypeSuffix = fileName.substring(fileName.length() - 4);
		String mimeTypeSuffix2 = fileName.substring(fileName.length() - 5);

		log.info(mimeTypeSuffix + " " + mimeTypeSuffix2);

		if (!ExcelParseTool.SUFFIX_2003.equals(mimeTypeSuffix) && !ExcelParseTool.SUFFIX_2007.equals(mimeTypeSuffix2)) {
			log.error("message:上传文件类型错误！！！" + fileName);
			renderText("上传文件类型错误!");
			return;
		}

		List<MdlExcelRow> table = null;
		try {
			table = ExcelParseTool.getWorkBookTable(picFile.getFile());
		} catch (IllegalArgumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			renderText("文件内容解析有误,请检查内容及格式!");
			return;
		}
		// 存入数据库
		Tuple2<Boolean, String> rt = T6MgrAhrService.service.insertAdmin(mgrSession, table);
		if (rt.first) {
			renderText(EnumStatus.Success.getIdText());
		} else {
			log.error(rt.second);
			renderText(rt.second);
		}
		return;
	}
	
	

	/**
	 * 保存
	 */
	@Before(T6MgrAhrValidator.class)
	public void save() {
		T6MgrAhr t6MgrAhr = getModel(T6MgrAhr.class);
		// other set

		// t6MgrAhr.save(); //guiid
		t6MgrAhr.saveGenIntId(); // serial int id
		renderWithPath(pthv + "add.html");
	}

	/**
	 * 准备更新
	 */
	public void edit() {
		// T6MgrAhr t6MgrAhr = T6MgrAhr.dao.findById(getPara()); //guuid
		T6MgrAhr t6MgrAhr = T6MgrAhrService.service.SelectById(getParaToInt()); // serial
																				// int
																				// id
		setAttr("t6MgrAhr", t6MgrAhr);
		renderWithPath(pthv + "update.html");

	}

	/**
	 * 更新
	 */
	@Before(T6MgrAhrValidator.class)
	public void update() {
		getModel(T6MgrAhr.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		// T6MgrAhr t6MgrAhr = T6MgrAhr.dao.findById(getPara()); //guuid
		T6MgrAhr t6MgrAhr = T6MgrAhrService.service.SelectById(getParaToInt()); // serial
																				// int
																				// id
		setAttr("t6MgrAhr", t6MgrAhr);
		renderWithPath(pthv + "view.html");
	}

	/**
	 * 删除
	 */
	public void delete() {
		// T6MgrAhrService.service.delete("t6_mgr_ahr", getPara() == null ? ids
		// : getPara()); //guuid
		T6MgrAhrService.service.deleteById("t6_mgr_ahr", getPara() == null ? ids : getPara()); // serial
																								// int
																								// id
		redirect(pthc);
	}

	public void setViewPath() {
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}

}
