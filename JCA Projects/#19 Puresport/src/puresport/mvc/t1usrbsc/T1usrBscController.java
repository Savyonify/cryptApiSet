package puresport.mvc.t1usrbsc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;

import csuduc.platform.util.ComOutMdl;
import csuduc.platform.util.ComUtil;
import csuduc.platform.util.RegexUtils;
import csuduc.platform.util.StringUtil;
import csuduc.platform.util.encrypt.DESUtil;
import csuduc.platform.util.lyf.EmailUtils;
import csuduc.platform.util.tuple.Tuple2;
import puresport.applicat.ExcelParseTool;
import puresport.applicat.MdlExcelRow;
import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.constant.EnumStatus;
import puresport.mvc.comm.AuthCodeMdl;
import puresport.mvc.comm.CommFun;
import puresport.mvc.comm.ExportData2Excel;
import puresport.mvc.comm.PageViewSta;
import puresport.mvc.comm.ParamComm;
import puresport.mvc.comm.ResTips;
import puresport.mvc.t6mgrahr.T6MgrAhr;
import puresport.mvc.t6mgrahr.T6MgrSession;

/**
 * XXX 管理 描述：
 * 
 * /jf/puresport/t1usrBsc /jf/puresport/t1usrBsc/save
 * /jf/puresport/t1usrBsc/edit /jf/puresport/t1usrBsc/update
 * /jf/puresport/t1usrBsc/view /jf/puresport/t1usrBsc/delete
 * /puresport/t1usrBsc/add.html
 * 
 */
//@Controller(controllerKey = "/jf/puresport/t1usrBsc")
public class T1usrBscController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T1usrBscController.class);

	public static final String pthc = "/jf/puresport/t1usrBsc/";
	public static final String pthv = "/f/";

	public static final String aboutsporterDir = "files/querydata/aboutsporter/";
	public static final String aboutsporterFileName = "运动员.xls";

	public static final String keyPhoneCode = "keyPhoneCode";
	public static final String keyEmailCode = "keyEmailCode";

	public static final String messageTitle = "反兴奋剂在线教育平台";
	public static final String messageContent = "【反兴奋剂教育平台】您的验证码是%s，如非本人操作，请忽略，谢谢！";

	@Clear
	public void index() {
		setAttr("username", "test");
		renderWithPath(pthv + "admin.html");
	}

	@Clear
	public void getData() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		renderJsonForTable(T1usrBscService.service.selectByPage(mgrSession, getParamWithServerPage()));
	}

	@Clear
	public void getAllData() throws Exception {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		List<T1usrBsc> resList = T1usrBscService.service.selectByPage(mgrSession, getParamWithServerPage());
		log.debug("getAllData-----" + resList.size() + "");
		String usrid = getSessionAttr("usrid");

		boolean result = ExportData2Excel.downLoadSporterExcel(resList, aboutsporterDir, usrid, aboutsporterFileName);
		JSONObject res = new JSONObject();
		res.put("flag", result);
		String fileUrl = getCxt() + "/" + aboutsporterDir + usrid + "/" + aboutsporterFileName;
		res.put("fileUrl", fileUrl);
		renderJson(res);
	}

	public void addSporter() {
//		T1usrBsc mdl = getModel(T1usrBsc.class);
//	    // 检查手机号的用户是否存在
//		if (T1usrBscService.service.isExist(mdl)) {
//			ResTips errorTips = ResTips.getFailRes()
//					.addErroFiled(T1usrBsc.column_crdt_no, "该证件号用户已存在");
//			renderJson(errorTips);
//			return ;
//		}
//		// 不存在则添加
//		mdl.set(T1usrBsc.column_usr_nm, mdl.get(T1usrBsc.column_nm));
//		if (mdl.saveGenIntId()) {
//			renderJson(ResTips.getSuccRes());
//		} else {
//			renderJson(ResTips.getFailRes());
//		}
	}

	@Clear
	public void editSporter() {
		T1usrBsc mdl = getModel(T1usrBsc.class);
		// 检查手机号的用户是否存在
		if (!T1usrBscService.service.isExist(mdl)) {
			// 不存在则不可以更新
			ResTips errorTips = ResTips.createFailRes().addErroFiled(T1usrBsc.column_crdt_no, "该证件号用户已存在");
			renderJson(errorTips);
			return;
		}
		mdl.set(T1usrBsc.column_usr_nm, mdl.get(T1usrBsc.column_nm));
		if (mdl.update()) {
			renderJson(ResTips.createSuccRes());
		} else {
			renderJson(ResTips.createFailRes());
		}
	}

	public void delSporter() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
			return;
		}
		ParamComm paramComm = getParamCommDef();
		if (Objects.isNull(paramComm)) {
			log.error("delSporter 参数解析失败");
			renderText("参数解析失败!");
			return;
		}
		renderText(T1usrBscService.service.delete(mgrSession, paramComm).second);
	}

	@Clear
	public void getDataScore() throws InterruptedException {
		// 成绩统计
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		renderJsonForTable(T1usrBscService.service.selectScoreByPage(mgrSession, getParamWithServerPage()));
	}

	@Clear
	public void getDataPrjStatis() {
		// 项目合格率统计
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		renderJsonForTable(T1usrBscService.service.selectPassedPercent(mgrSession, getParamWithServerPage()));
	}

	@Clear
	public void getDataExamQues() {
		// 试题错误率统计
		renderJsonForTable(T1usrBscService.service.selectExamQuestion(getParamWithServerPage()));
	}

	@Clear
	public void inload() {
		T6MgrSession mgrSession = getSessionAttr(T6MgrSession.KeyName);
		if (null == mgrSession) {
			renderText("页面信息已过期，请刷新页面!");
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			renderText("文件内容解析有误,请检查内容及格式!");
			return;
		}
		// 存入数据库
		Tuple2<Boolean, String> rt = T1usrBscService.service.insertFromExcel(mgrSession, table);

		if (rt.first) {
			renderText(EnumStatus.Success.getIdText());
		} else {
			log.error(rt.second);
			renderText(rt.second);
		}
		return;
	}

	/**
	 * 列表
	 */
	@Clear
	public void login() {
		boolean flag = false;
		boolean needImproveInfoOrNot = false;
		boolean belongToInstitute = false;
		String msg = "";
		String userType = "";
		JSONObject json = new JSONObject();
/*		boolean authCode = authCode();
		if (!authCode) {
			renderJson(CommFun.resJsonFail(EnumStatus.Illegal_AuthCode));
			return;
		}*/
		String crdt_no = getPara("account").trim();// 获取表单数据，这里的参数就是页面表单中的name属性值
		String password = getPara("pwd").trim();
		
		// added by zhuchaobin, 2020-02-25, 系统登录白名单校验
		if(ConstantInitMy.ENTRANCE_WHITE_LIST_SWITCH && StringUtils.isNotBlank(ConstantInitMy.ENTRANCE_WHITE_LIST)) {
			log.info("系统登录白名单开启，账号白名单列表:" + ConstantInitMy.ENTRANCE_WHITE_LIST);
			String[] entranceWhiteList = ConstantInitMy.ENTRANCE_WHITE_LIST.split(","); 
			boolean isInWhiteList = false;
			for(String acct : entranceWhiteList){ 
				if(crdt_no.equals(acct)) {
					isInWhiteList = true;
					log.info("账号" + crdt_no + "在白名单中。");
					break;
				}
			}
			if(!isInWhiteList) {
				log.info("账号" + crdt_no + "不在白名单中， 禁止登录。");
				renderJson(CommFun.resJsonFail(EnumStatus.Entrance_Forbidden));
				return;
			} else {
				log.info("账号" + crdt_no + "在白名单中，允许进行验证登录。");
			}
		} else {
			log.info("系统登录白名单关闭.");
		}

		if (ComUtil.haveEmpty(crdt_no, password)) {
			renderIllegal();
			return;
		}

		Integer accountType = RegexUtils.checkPhoneOrEmailOrID(crdt_no);
		if (accountType == 0) {
			renderIllegal();
			return;
		}

		try {

			String accountFieldName = accountType == 1 ? "mblph_no" : (accountType == 2 ? "email" : "crdt_no");
			T1usrBsc item = T1usrBsc.dao
					.findFirst(String.format("select * from t1_usr_bsc where %s=? limit 1", accountFieldName), crdt_no);
			if (null == item) {
				renderJson(CommFun.resJsonFail(EnumStatus.Null_Account));
				return;
			}

			String encryptpassword = DESUtil.encrypt(password, ConstantInitMy.SPKEY);
			String pwddd = item.getPswd();
			if (!encryptpassword.equals(pwddd)) {// 判断数据库中的密码与用户输入的密码是否一致
				renderJson(CommFun.resJsonFail(EnumStatus.Illegal_Pwd));
				return;
			}

			PageViewSta.StaLoginPeopleCountByDay();
			flag = true;
			userType = item.getUsr_tp();
			setSessionAttr("usrid", item.getUsrid());
			setSessionAttr("crdt_no", item.getCrdt_no());
			setSessionAttr("pwd", pwddd);
			setSessionAttr("usr_tp", item.getUsr_tp());

			String sptPrj = item.getSpt_prj();
			Integer emailVal = item.getEmailVal();
			Integer phoneVal = item.getMblPhVal();
			Boolean needValEmail = (null == emailVal) || (emailVal == 0);
			Boolean needValPhone = (null == phoneVal) || (phoneVal == 0);
			Boolean needValSptPrj = StringUtils.isBlank(sptPrj);
			Boolean needValDepart = StringUtils.isBlank((String)item.getDepartment()) || StringUtils.isBlank(item.getPost());
			belongToInstitute = StringUtils.isNotBlank((String)item.getInstitute()); 
			Boolean needValNmChar = StringUtils.isBlank((String)item.getNmChar());
			
			json.put("belongToInstitute", belongToInstitute);
			json.put("needValEmail", needValEmail);
			json.put("needValPhone", needValPhone);
			json.put("needValSptPrj", needValSptPrj);
			json.put("needValDepart", needValDepart);
			json.put("needValNmChar", needValNmChar);

			setSessionAttr("belongToInstitute", belongToInstitute);
			setSessionAttr("needValEmail", needValEmail);
			setSessionAttr("needValPhone", needValPhone);
			setSessionAttr("needValSptPrj", needValSptPrj);
			setSessionAttr("needValDepart", needValDepart);
			setSessionAttr("needValNmChar", needValNmChar);

			if (userType.equals("运动员"))// 运动员表 这个字段的初始值为运动员！
			{
				needImproveInfoOrNot = needValSptPrj || needValPhone||needValNmChar;
			} else {
				if (belongToInstitute) {
					needImproveInfoOrNot = needValSptPrj || needValPhone||needValNmChar|| needValDepart;
				} else {
					needImproveInfoOrNot = needValPhone|| needValNmChar|| needValDepart;
				}
			}

			json.put("needImproveInfoOrNot", needImproveInfoOrNot);
			json.put("flag", flag);
			json.put("userType", userType);
			json.put("msg", msg);

			if (getSessionAttr("RequestURL") != null) {
				json.put("url", (String) getSessionAttr("RequestURL"));
			} else {
				json.put("url", getCxt() + "/jf/puresport/pagesController");
			}
			renderJson(json);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			renderJson(CommFun.resJsonFail(EnumStatus.Error_Exception));
		}

	}

	@Clear
	public void sendEmailCode() {

		String email = getPara("email");
		if (StringUtils.isBlank(email) || !RegexUtils.checkEmail(email)) {
			renderTextJson(ResTips.createFailRes("error, illegal email"));
			return;
		}

		AuthCodeMdl authCodeMdl = getSessionAttr(keyEmailCode);
		if (Objects.nonNull(authCodeMdl)) {
			if (!authCodeMdl.hasTimeOut(ConstantInitMy.AuthCode_TimeOut_Send)) {
				renderTextJson(ResTips.createFailRes("error, not timeOut"));
				return;
			}
		}

		String type = getPara("type");
		String module = getPara("module");
		if (null == type) {
			// athlete
			if (!StringUtils.isBlank(module)) { // regist module
				Object item = T1usrBsc.dao.findFirst("select email from t1_usr_bsc where email=? limit 1 ", email);
				if (item != null) {
					renderTextJson(ResTips.createFailRes("此邮件地址已存在，请换一个"));
					return;
				}
			}

		} else {
			// admin
			if (!StringUtils.isBlank(module)) { // regist module
				Object item = T6MgrAhr.dao.findFirst("select email from t6_mgr_ahr where email=? limit 1 ", email);
				if (item != null) {
					renderTextJson(ResTips.createFailRes("此邮件地址已存在，请换一个"));
					return;
				}
			}
		}

		authCodeMdl = AuthCodeMdl.createOne(email);

		try {
			boolean flag = EmailUtils.sendTextMail(email, messageTitle, String.format(messageContent, authCodeMdl.getCode()));
			if (flag) {
				setSessionAttr(keyEmailCode, authCodeMdl);
			} else {
				renderTextJson(ResTips.createFailRes("邮件发送失败,请您稍后重试"));
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("--------validateEmail 发送验证码到邮箱失败！！");
			e.printStackTrace();
			renderTextJson(ResTips.createFailRes("邮件发送失败,请您联系管理员"));
			return;
		}

		renderTextJson(ResTips.SUCCESS);
	}

	@Clear
	public void sendPhoneCode() {

		String phone = getPara("phone");
		if (StringUtils.isBlank(phone) || !RegexUtils.checkMobile(phone)) {
			renderTextJson(ResTips.createFailRes("error, illegal phone"));
			return;
		}

		AuthCodeMdl authCodeMdl = (AuthCodeMdl) getSessionAttr(keyPhoneCode);
		if (Objects.nonNull(authCodeMdl)) {
			if (!authCodeMdl.hasTimeOut(ConstantInitMy.AuthCode_TimeOut_Send)) {
				renderTextJson(ResTips.createFailRes("error, not timeOut"));
				return;
			}
		}

		String type = getPara("type");
		String module = getPara("module");
		if (null == type) {
			// athlete
			if (!StringUtils.isBlank(module)) { // regist module
				T1usrBsc usr = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where mblph_no=? limit 1 ", phone);
				if (usr != null) {
					renderTextJson(ResTips.createFailRes("此手机号已存在，请换一个"));
					return;
				}
			}

		} else {
			// admin
			if (!StringUtils.isBlank(module)) { // regist module
				Object item = T6MgrAhr.dao.findFirst("select mblph_no from t6_mgr_ahr where mblph_no=? limit 1 ", phone);
				if (item != null) {
					renderTextJson(ResTips.createFailRes("此手机号已存在，请换一个"));
					return;
				}
			}
		}

		authCodeMdl = AuthCodeMdl.createOne(phone);
		// todo send code to phone
		try {
			boolean flag = CommFun.sendPhoneMsg(phone, authCodeMdl.getCode());
			if (flag) {
				setSessionAttr(keyEmailCode, authCodeMdl);
			} else {
				renderTextJson(ResTips.createFailRes("邮件发送失败,请您稍后重试"));
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("--------validateEmail 发送验证码到邮箱失败！！");
			e.printStackTrace();
			renderTextJson(ResTips.createFailRes("邮件发送失败,请您联系管理员"));
			return;
		}

		setSessionAttr(keyPhoneCode, authCodeMdl);

		renderTextJson(ResTips.SUCCESS);
	}

	/**
	 * 注册主入口
	 */
	@Clear
	public void regist() {

		AuthCodeMdl authCodeMdlPhone = (AuthCodeMdl) getSessionAttr(keyPhoneCode);
//		AuthCodeMdl authCodeMdlEmail = (AuthCodeMdl) getSessionAttr(keyEmailCode);
//		if (Objects.isNull(authCodeMdlPhone) && Objects.isNull(authCodeMdlEmail)) {
		if (Objects.isNull(authCodeMdlPhone)) {
			renderTextJson(ResTips.createFailRes("验证码不正确或已过期，请重新获取验证码"));
			return;
		}
		// 入参 校验
		T1userBscDTO t1userBscDTO = getParamWithClass(T1userBscDTO.class);
		if (Objects.isNull(t1userBscDTO)) {
			renderTextJson(ResTips.createFailRes());
			return;
		}
		// 验证输入
//		if (!t1userBscDTO.validate(authCodeMdlPhone, authCodeMdlEmail)) {
		if (!t1userBscDTO.validate(authCodeMdlPhone)) {
			renderTextJson(ResTips.createFailRes(t1userBscDTO.getTipList()));
			return;
		}

		// 数据库是否已经存在该用户
		T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where crdt_no=? limit 1 ",
				t1userBscDTO.getCrdt_no());
		if (item != null) {
			renderTextJson(ResTips.createFailRes(String.format("该证件号【%s】已注册过，请登录！", t1userBscDTO.getCrdt_no())));
			return;
		}
		// 入库
		if (T1usrBscService.service.addUserBsc(t1userBscDTO)) {
			renderTextJson(ResTips.createSuccRes());
			return;
		} else {
			renderTextJson(ResTips.createFailRes());
			return;
		}
	}

	private final static List<String> SportRoles = Arrays.asList("运动员", "辅助人员");
	@Clear
	public void ImproveUserInfo() {
		
		Long userID = (Long) getSessionAttrForStr("usrid", Long.class);
		if (null == userID) {
			renderJson(CommFun.resJsonFail("登录信息已过期，请刷新页面重试！"));
			return;
		}
		
		boolean flag = false;
		JSONObject json = new JSONObject();
		
		String msg = "";
		String usertype = getPara("usertype");
		if (StringUtils.isBlank(usertype)|| !SportRoles.contains(usertype)) {
			renderJson(CommFun.resJsonFail("illegal"));
			return;
		}
		
		T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=? limit 1", userID);// 根据用户名查询数据库中的用户
		if (item != null) {
			if (usertype.equals("运动员"))// 运动员
			{
//				Boolean needValEmail = (Boolean) getSessionAttr("needValEmail");
				
				StringBuilder sqlUpdate = new StringBuilder("update t1_usr_bsc set usr_tp=?");
				List<Object> argList = new LinkedList<Object>();
				argList.add(usertype);

				boolean haveUpdate = false;

//				if (needValEmail != null && needValEmail == Boolean.TRUE) {
//					String email = getPara("email");
//					String emailValCode = getPara("emailValCode");
//					if (StringUtils.isBlank(email) || StringUtils.isBlank(emailValCode)
//							|| !RegexUtils.checkEmail(email)) {
//						renderJson(CommFun.resJsonFail("邮箱或验证码不正确或为空"));
//						return;
//					}
//					AuthCodeMdl authCodeMdlEmail = (AuthCodeMdl) getSessionAttr(keyEmailCode);
//					if (Objects.isNull(authCodeMdlEmail) || authCodeMdlEmail.checkAuthCodeFail(email, emailValCode,
//							ConstantInitMy.AuthCode_TimeOut)) {
//						renderTextJson(CommFun.resJsonFail("邮箱验证码错误或已过期，请重新获取验证"));
//						return;
//					}
//					sqlUpdate.append(", email = ?, email_val=? ");
//					argList.add(email);
//					argList.add(1);
//					haveUpdate = true;
//				}
				Boolean needValPhone = (Boolean) getSessionAttr("needValPhone");
				if (needValPhone != null && needValPhone == Boolean.TRUE) {
					String phone = getPara("phone");
					String mblphValCode = getPara("mblphValCode");
					if (StringUtils.isBlank(phone) || StringUtils.isBlank(mblphValCode)
							|| !RegexUtils.checkMobile(phone)) {
						renderJson(CommFun.resJsonFail("手机或验证码不正确或为空"));
						return;
					}
					AuthCodeMdl authCodeMdlPhone = (AuthCodeMdl) getSessionAttr(keyPhoneCode);
					if (Objects.isNull(authCodeMdlPhone) || authCodeMdlPhone.checkAuthCodeFail(phone, mblphValCode,
							ConstantInitMy.AuthCode_TimeOut)) {
						renderTextJson(CommFun.resJsonFail("手机验证码错误或已过期，请重新获取验证"));
						return;
					}
					sqlUpdate.append(", mblph_no = ?, mblph_val=? ");
					argList.add(phone);
					argList.add(1);
					haveUpdate = true;
				}
				
				Boolean needValSptPrj = (Boolean) getSessionAttr("needValSptPrj");
				if (needValSptPrj != null && needValSptPrj == Boolean.TRUE) {
					String sptPrj = getPara("sptPrj");
					if (StringUtils.isBlank(sptPrj) || sptPrj.length() > 30) {
						renderJson(CommFun.resJsonFail("运动项目为空或不正确"));
						return;
					}
					sqlUpdate.append(", spt_prj = ? ");
					argList.add(sptPrj);
					haveUpdate = true;
				}
				
				Boolean needValNmChar = (Boolean) getSessionAttr("needValNmChar");
				if (needValNmChar != null && needValNmChar == Boolean.TRUE) {
					String nmChar = getPara("nmChar");
					if (StringUtils.isBlank(nmChar) || nmChar.length() > 60) {
						renderJson(CommFun.resJsonFail("姓名拼音为空或格式不正确"));
						return;
					}
					sqlUpdate.append(", nm_char = ? ");
					argList.add(nmChar);
					haveUpdate = true;
				}
				
				if (!haveUpdate) {
					renderText("!haveUpdate");
					return;
				}

				sqlUpdate.append(" where usrid=?");
				argList.add(userID);

				int res = ConfMain.db().update(sqlUpdate.toString(), argList.toArray());
				if (res > 0) {
					flag = true;
					setSessionAttr("usr_tp", usertype);
				}
			} else {// 辅助人员
				
				StringBuilder sqlUpdate = new StringBuilder("update t1_usr_bsc set usr_tp=?");
				List<Object> argList = new LinkedList<Object>();
				argList.add(usertype);

				boolean haveUpdate = false;
				Boolean needValPhone = (Boolean) getSessionAttr("needValPhone");
				if (needValPhone != null && needValPhone == Boolean.TRUE) {
					String phone = getPara("phone");
					String mblphValCode = getPara("mblphValCode");
					if (StringUtils.isBlank(phone) || StringUtils.isBlank(mblphValCode)
							|| !RegexUtils.checkMobile(phone)) {
						renderJson(CommFun.resJsonFail("手机或验证码不正确或为空"));
						return;
					}
					AuthCodeMdl authCodeMdlPhone = (AuthCodeMdl) getSessionAttr(keyPhoneCode);
					if (Objects.isNull(authCodeMdlPhone) || authCodeMdlPhone.checkAuthCodeFail(phone, mblphValCode,
							ConstantInitMy.AuthCode_TimeOut)) {
						renderTextJson(CommFun.resJsonFail("手机验证码错误或已过期，请重新获取验证"));
						return;
					}
					sqlUpdate.append(", mblph_no = ?, mblph_val=? ");
					argList.add(phone);
					argList.add(1);
					haveUpdate = true;
				}
				Boolean belongToInstitute = (Boolean)getSessionAttr("belongToInstitute");
				if (belongToInstitute != null && belongToInstitute == Boolean.TRUE) {
					Boolean needValSptPrj = (Boolean) getSessionAttr("needValSptPrj");
					if (needValSptPrj != null && needValSptPrj == Boolean.TRUE) {
						String sptPrj = getPara("sptPrj");
						if (StringUtils.isBlank(sptPrj) || sptPrj.length() > 30) {
							renderJson(CommFun.resJsonFail("运动项目为空或格式不正确"));
							return;
						}
						sqlUpdate.append(", spt_prj = ? ");
						argList.add(sptPrj);
						haveUpdate = true;
					} 
				}
				
				Boolean needValNmChar = (Boolean) getSessionAttr("needValNmChar");
				if (needValNmChar != null && needValNmChar == Boolean.TRUE) {
					String nmChar = getPara("nmChar");
					if (StringUtils.isBlank(nmChar) || nmChar.length() > 60) {
						renderJson(CommFun.resJsonFail("姓名拼音为空或格式不正确"));
						return;
					}
					sqlUpdate.append(", nm_char = ? ");
					argList.add(nmChar);
					haveUpdate = true;
				}
				
				Boolean needValDepart = (Boolean) getSessionAttr("needValDepart");
				if (needValDepart != null && needValDepart == Boolean.TRUE) {
					String company = getPara("company");
					String position = getPara("position");
					if (StringUtils.isBlank(company) || StringUtils.isBlank(position) 
							|| company.length() > 60 || position.length() > 30) {
						renderJson(CommFun.resJsonFail("工作单位或职位为空或格式不正确"));
						return;
					}
					sqlUpdate.append(",department=?,post=? ");
					argList.add(company);
					argList.add(position);
					haveUpdate = true;
				}
				
				if (!haveUpdate) {
					renderText("!haveUpdate");
					return;
				}

				sqlUpdate.append(" where usrid=?");
				argList.add(userID);

				int res = ConfMain.db().update(sqlUpdate.toString(), argList.toArray());
				if (res > 0) {
					flag = true;
					setSessionAttr("usr_tp", usertype);
				}
				
			}
		} else {
			msg = "更新失败，请刷新页面！";
		}
		json.put("flag", flag);
		json.put("msg", msg);
		json.put("url", getCxt() + "/jf/puresport/pagesController/selfcenter");
		renderJson(json);

	}

	/**
	 * 个人中心 更新个人信息
	 */
	@Clear
	public void updateSelfCenterInfo() {
		Long userID = getSessionAttrForStr("usrid", Long.class);
		String crdt_no = getSessionAttr("crdt_no");
		if (null == userID || null == crdt_no) {
			renderWithPath(pthv + "login.html");
			return;
		}

		// 入参 校验
		T1userBscDTO t1userBscDTO = getParamWithClass(T1userBscDTO.class);
		if (Objects.isNull(t1userBscDTO)) {
			renderTextJson(ResTips.createFailRes());
			return;
		}
		AuthCodeMdl authCodeMdlPhone = getSessionAttr(keyPhoneCode);
		AuthCodeMdl authCodeMdlEmail = getSessionAttr(keyEmailCode);
		if (!t1userBscDTO.validateForUpdate(authCodeMdlPhone, authCodeMdlEmail)) {
			renderTextJson(ResTips.createFailRes(t1userBscDTO.getTipList()));
			return;
		}

		T1usrBsc userBsc = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=? limit 1", userID);
		if (null == userBsc) {
			renderWithPath(pthv + "login.html");
			return;
		}

		if (T1usrBscService.service.updateUserBsc(t1userBscDTO, userBsc)) {
			renderTextJson(ResTips.createSuccRes());
			return;
		} else {
			renderTextJson(ResTips.createFailRes());
			return;
		}
	}

	@Clear
	public void selfCenterInfo() {

		Long userID = getSessionAttrForStr("usrid", Long.class);
		if (null == userID) {
			renderWithPath(pthv + "login.html");
			return;
		}
		T1usrBsc t1usrBsc = T1usrBscService.service.SelectById(userID);
		if (null == t1usrBsc) {
			renderWithPath(pthv + "login.html");
			return;
		}
		T1userBscDTO dto = new T1userBscDTO(t1usrBsc);
		renderJson(dto);
	}

	/**
	 * 保存
	 */
	@Before(T1usrBscValidator.class)
	public void save() {
		T1usrBsc t1usrBsc = getModel(T1usrBsc.class);
		// other set

		// t1usrBsc.save(); //guiid
		t1usrBsc.saveGenIntId(); // serial int id
		renderWithPath(pthv + "add.html");
	}

	/**
	 * 准备更新
	 */
	public void edit() {
		// T1usrBsc t1usrBsc = T1usrBsc.dao.findById(getPara()); //guuid
		T1usrBsc t1usrBsc = T1usrBscService.service.SelectById(getParaToLong()); // serial int id
		setAttr("t1usrBsc", t1usrBsc);
		renderWithPath(pthv + "update.html");

	}

	/**
	 * 更新
	 */
	@Before(T1usrBscValidator.class)
	public void update() {
		getModel(T1usrBsc.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		// T1usrBsc t1usrBsc = T1usrBsc.dao.findById(getPara()); //guuid
		T1usrBsc t1usrBsc = T1usrBscService.service.SelectById(getParaToLong()); // serial int id
		setAttr("t1usrBsc", t1usrBsc);
		renderWithPath(pthv + "view.html");
	}

	/**
	 * 删除
	 */
	public void delete() {
		// T1usrBscService.service.delete("t1_usr_bsc", getPara() == null ? ids :
		// getPara()); //guuid
		T1usrBscService.service.deleteById("t1_usr_bsc", getPara() == null ? ids : getPara()); // serial int id
		redirect(pthc);
	}

	public void setViewPath() {
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}

}
