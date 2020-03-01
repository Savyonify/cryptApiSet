package puresport.mvc.t11examstat;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;

import puresport.constant.ConstantInitMy;
import puresport.mvc.t10examgrd.T10ExamGrd;
import puresport.mvc.t12highestscore.T12HighestScore;

/**
 * XXX 管理 描述：
 * 
 * /jf/puresport/t11ExamStat /jf/puresport/t11ExamStat/save
 * /jf/puresport/t11ExamStat/edit /jf/puresport/t11ExamStat/update
 * /jf/puresport/t11ExamStat/view /jf/puresport/t11ExamStat/delete
 * /puresport/t11ExamStat/add.html
 * 
 */
// @Controller(controllerKey = "/jf/puresport/t11ExamStat")
public class T11ExamStatController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T11ExamStatController.class);

	public static final String pthc = "/jf/puresport/t11ExamStat/";
	public static final String pthv = "/puresport/t11ExamStat/";

	/**
	 * 列表
	 */
	public void index() {
		paging(ConstantInitMy.db_dataSource_main, splitPage, BaseModel.sqlId_splitPage_select,
				T11ExamStat.sqlId_splitPage_from);
		renderWithPath(pthv + "list.html");
	}

	/**
	 * 保存
	 */
	@Before(T11ExamStatValidator.class)
	public void save() {
		T11ExamStat t11ExamStat = getModel(T11ExamStat.class);
		// other set

		// t11ExamStat.save(); //guiid
		t11ExamStat.saveGenIntId(); // serial int id
		renderWithPath(pthv + "add.html");
	}

	/**
	 * 准备更新
	 */
	public void edit() {
		// T11ExamStat t11ExamStat = T11ExamStat.dao.findById(getPara()); //guuid
		T11ExamStat t11ExamStat = T11ExamStatService.service.SelectById(getParaToInt()); // serial int id
		setAttr("t11ExamStat", t11ExamStat);
		renderWithPath(pthv + "update.html");

	}

	/**
	 * 更新
	 */
	@Before(T11ExamStatValidator.class)
	public void update() {
		getModel(T11ExamStat.class).update();
		redirect(pthc);
	}

	/**
	 * 查看
	 */
	public void view() {
		// T11ExamStat t11ExamStat = T11ExamStat.dao.findById(getPara()); //guuid
		T11ExamStat t11ExamStat = T11ExamStatService.service.SelectById(getParaToInt()); // serial int id
		setAttr("t11ExamStat", t11ExamStat);
		renderWithPath(pthv + "view.html");
	}

	/**
	 * 删除
	 */
	public void delete() {
		// T11ExamStatService.service.delete("t11_exam_stat", getPara() == null ? ids :
		// getPara()); //guuid
		T11ExamStatService.service.deleteById("t11_exam_stat", getPara() == null ? ids : getPara()); // serial int id
		redirect(pthc);
	}

	public void setViewPath() {
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}

	@Clear
	public void get_exam_grd() {
		boolean flag = false;
		JSONObject resjson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		String userID = getPara("userID");// 获取表单数据，这里的参数就是页面表单中的name属性值
		List<T11ExamStat> itemlist = T11ExamStat.dao.find(
				"select t.*,s.id as file_path, date_FORMAT(t.tms, '%Y-%m-%d %H:%i:%s') as tms,"
				+ "case t.exam_grd when t.exam_grd >= 80 then '5' else '3' end as exam_st from t11_exam_stat t left join t17_credit_inf s ON t.usrid=s.usrid and t.type = s.type where t.usrid=? and t.exam_st = '1' and t.type != '4' order by t.tms desc",
				userID);
		if (itemlist != null) {
			for (T11ExamStat item : itemlist) {
				JSONObject json = new JSONObject();
				json.put("exam_grd", item.getExam_grd());
				json.put("exam_name", item.getExam_nm());
				json.put("tms", item.getTms());
				json.put("examid", item.getExamid());
				json.put("usrid", item.getUsrid());
				json.put("type", item.getType());
				json.put("file_path", item.getFile_path());
				json.put("exam_st", item.getExam_st());
				jsonArray.add(json);
			}
		}
		// 积分制部分的成绩
		List<T12HighestScore> itemlist2 = T12HighestScore.dao.find(
				"select t.*,s.id as file_path, date_FORMAT(t.tms, '%Y-%m-%d %H:%i:%s') as tms from t12_highest_score t left join t17_credit_inf s ON t.usrid=s.usrid and t.type = s.type where t.usrid=? and t.type = '4' order by t.tms desc",
				userID);
		if (itemlist2 != null) {
			for (T12HighestScore item : itemlist2) {
				JSONObject json = new JSONObject();
				json.put("exam_grd", item.getExam_grd());
				json.put("exam_name", item.getExam_nm());
				json.put("tms", item.getTms());
				json.put("examid", item.getExamid());
				json.put("usrid", item.getUsrid());
				json.put("type", item.getType());
				json.put("file_path", item.getFile_path());
				json.put("exam_st", item.getExam_st());
				jsonArray.add(json);
			}
		}
		System.out.println("get_exam_grd=" +JSON.toJSONString(jsonArray));
		if (itemlist2 != null || itemlist != null) {
			resjson.put("itemlist", jsonArray);
			flag=true;
			resjson.put("flag", flag);
			renderJson(resjson);
		} else {
			JSONObject json = new JSONObject();
			json.put("flag", flag);
			renderJson(json);
		}
	}

	// 查询积分制课程考试情况列表
/*	@Clear
	public void get_exam_grd_category() {
		boolean flag = false;
		String useridStr = (String) getSession().getAttribute("usrid");
		String type = getPara("type");// 获取表单数据，这里的参数就是页面表单中的name属性值
		List<T11ExamStat> itemlist = T11ExamStat.dao.find(
				"select t.* from t11_exam_stat t where t.usrid=? and t.exam_st = '1' and t.type='4' order by t.tms desc",
				useridStr);
		if (itemlist != null) {
			flag = true;
			JSONObject resjson = new JSONObject();
			resjson.put("flag", flag);
			JSONArray exam_grd_category_list = new JSONArray();
			for (T11ExamStat item : itemlist) {
				JSONObject json = new JSONObject();
				json.put("exam_grd", item.getExam_grd());
				json.put("exam_name", item.getExam_nm());
				json.put("tms", item.getTms());
				json.put("examid", item.getExamid());
				json.put("usrid", item.getUsrid());
				json.put("type", item.getType());
				json.put("file_path", item.getFile_path());
				exam_grd_category_list.add(json);
			}
			resjson.put("itemlist", exam_grd_category_list);
			renderJson(resjson);
		} else {
			JSONObject json = new JSONObject();
			json.put("flag", flag);
			renderJson(json);
		}
	}
	*/

	// 2020-2-6 added by zhuchaobin
	// 查询积分制课程考试情况列表
	public void get_exam_grd_category() {
		JSONObject jsonRlt = new JSONObject();
		JSONArray jsonArray = new JSONArray();		
		
		try {
			String useridStr = getPara("usrid");
			if(StringUtils.isBlank(useridStr)) {
				useridStr = getSession().getAttribute("usrid") + "";
			}
			if(StringUtils.isBlank(useridStr)) {
				jsonRlt.put("code", "0002");
				jsonRlt.put("desc", "获取用户ID失败!");
				renderJson(jsonRlt);
			}
			String type = getPara("type");
			if(StringUtils.isBlank(type)) {
				jsonRlt.put("code", "0003");
				jsonRlt.put("desc", "获取赛事类别失败!");
				renderJson(jsonRlt);
			}
			String sql = "select t.*, date_FORMAT(t.tms, '%Y-%m-%d %H:%i:%s') as tms, s.crcl_nm as category  from t11_exam_stat t left JOIN " + 
					"t7_crcl s " + 
					"ON " + 
					"t.category=s.category " + 
					"and s.type='4' "
					+ "where t.type = '"+ type +"' and usrid = '" + useridStr + "' and t.exam_st = '1'";
			List<T11ExamStat> tll_list = T11ExamStat.dao.find(sql);
			if (null != tll_list && tll_list.size() > 0) {
				for(T11ExamStat t11 : tll_list) {
					JSONObject json = new JSONObject();
					json.put("exam_grd", t11.getExam_grd());
					json.put("exam_name", t11.getExam_nm());
					json.put("examid", t11.getExamid());
					json.put("type", t11.getType());
					json.put("category", t11.getCategory());
					json.put("usrid", t11.getUsrid());
					json.put("tms", t11.getTms());
					jsonArray.add(json);
				}
				jsonRlt.put("exam_grd_category_list", jsonArray);
				jsonRlt.put("code", "0000");
				renderJson(jsonRlt);
			} else {
				jsonRlt.put("code", "0002");
				jsonRlt.put("desc", "没有查询到考试信息.");
				renderJson(jsonRlt);
			}
		} catch (Exception e) {
			jsonRlt.put("code", "0001");
			jsonRlt.put("desc", e.getStackTrace());
			renderJson(jsonRlt);
		}	
	}
}
