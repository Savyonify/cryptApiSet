package puresport.mvc.t5crclstdy;

import com.platform.constant.ConstantRender;
import com.platform.mvc.base.BaseController;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;

import puresport.constant.ConstantInitMy;
import puresport.entity.ResultEntity;
import puresport.mvc.t9tstlib.T9Tstlib;

/**
 * XXX 管理 描述：
 * 
 * /jf/puresport/t5CrclStdy /jf/puresport/t5CrclStdy/save
 * /jf/puresport/t5CrclStdy/edit /jf/puresport/t5CrclStdy/update
 * /jf/puresport/t5CrclStdy/view /jf/puresport/t5CrclStdy/delete
 * /puresport/t5CrclStdy/add.html
 * 
 */
// @Controller(controllerKey = "/jf/puresport/t5CrclStdy")
public class T5CrclStdyController extends BaseController {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(T5CrclStdyController.class);
	public static final String pthc = "/jf/puresport/t5CrclStdy/";
	public static final String pthv = "/puresport/t5CrclStdy/";
	/**
	 * 列表
	 */
	public void index() {
		// paging(ConstantInitMy.db_dataSource_main, splitPage,
		// BaseModel.sqlId_splitPage_select, T5CrclStdy.sqlId_splitPage_from);
		renderWithPath("/f/study.html");
	}

	/**
	 * 保存
	 */
	@Before(T5CrclStdyValidator.class)
	public void save() {
		T5CrclStdy t5CrclStdy = getModel(T5CrclStdy.class);
		t5CrclStdy.saveGenIntId(); // serial int id
		renderWithPath(pthv + "add.html");
	}

	/**
	 * 准备更新
	 */
	public void edit() {
		// T5CrclStdy t5CrclStdy = T5CrclStdy.dao.findById(getPara()); //guuid
		T5CrclStdy t5CrclStdy = T5CrclStdyService.service.SelectById(getParaToInt()); // serial int id
		setAttr("t5CrclStdy", t5CrclStdy);
		renderWithPath(pthv + "update.html");

	}

	/**
	 * 描述： 更新课程学习记录
	 * @author zhuchaobin
	 * 2018-05-09
	 */
	public void update() {
		ResultEntity res = null;
		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		String crclid = getPara("crclid");
		T5CrclStdy t5 = new T5CrclStdy();
		String sql = "select * from t5_crcl_stdy t where t.usrid ='" + usrid + "' and t.crclid = '" + crclid + "'";
		List<T5CrclStdy> t5List = T5CrclStdy.dao.find(sql);
		if ((t5List != null) && (t5List.size() > 0)) {
			// 更新;
			t5 = t5List.get(0);
			t5.setUsrid(usrid);
			t5.setCrclid(Integer.parseInt(crclid));
			t5.setStdy_st("1");
			t5.setTms(new Timestamp(System.currentTimeMillis()));
			t5.update();
		} else {
			// 插入
			t5.setUsrid(usrid);
			t5.setCrclid(Integer.parseInt(crclid));
			t5.setStdy_st("1");
			t5.setTms(new Timestamp(System.currentTimeMillis()));
			t5.saveGenIntId();
		}
		res = new ResultEntity("0000", "课程学习记录成功.");
		renderJson(res);
	}
	/**
	 * 描述： 查询课程学习记录，判断是否具备考试资格
	 * @author zhuchaobin
	 * 2018-05-09
	 */
	public boolean isCanTest() {
		return true;
/*		Integer usrid = Integer.parseInt((String) getSession().getAttribute("usrid"));
		ResultEntity res = null;
		StringBuilder desc = new StringBuilder("");
		boolean isCorse1Fnsh = false;
		boolean isCorse2Fnsh = false;
		boolean isCorse3Fnsh = false;
		// 必修课程1
		String crclid = "1";
		String sql = "select * from t5_crcl_stdy t where t.usrid ='" + usrid + "' and t.crclid = '" + crclid
				+ "' and t.stdy_st='1'";
		List<T5CrclStdy> t5List = T5CrclStdy.dao.find(sql);
		if ((t5List != null) && (t5List.size() > 0)) {
			// 课程已修;
			isCorse1Fnsh = true;
		}
		// 必修课程2
		crclid = "'21', '22', '23', '24','25'";
		sql = "select * from t5_crcl_stdy t where t.usrid ='" + usrid + "' and t.crclid in(" + crclid
				+ ") and t.stdy_st='1'";
		t5List = T5CrclStdy.dao.find(sql);
		if ((t5List != null) && (t5List.size() > 0)) {
			// 课程已修;
			isCorse2Fnsh = true;
		}
		// 必修课程3
		crclid = "'31', '32', '33', '34','35', '36', '37'";
		sql = "select * from t5_crcl_stdy t where t.usrid ='" + usrid + "' and t.crclid in(" + crclid
				+ ") and t.stdy_st='1'";
		t5List = T5CrclStdy.dao.find(sql);
		if ((t5List != null) && (t5List.size() > 0)) {
			// 课程已修;
			isCorse3Fnsh = true;
		}
		if (isCorse1Fnsh && isCorse2Fnsh && isCorse3Fnsh) {
			res = new ResultEntity("0000", "课程学习完毕，可以参加考试!");
		} else {
			if (!isCorse1Fnsh)
				desc.append("‘必修课程1’");
			if (!isCorse2Fnsh)
				desc.append("‘必修课程2’");
			if (!isCorse3Fnsh)
				desc.append("‘必修课程3’");
			desc.append("没有完成学习，请完成后再参加考试！");
			res = new ResultEntity("0001", desc.toString());
		}
		renderJson(res);
		if(isCorse1Fnsh && isCorse2Fnsh && isCorse3Fnsh)
			return true;
		else
			return false;*/
	}

	/**
	 * 查看
	 */
	public void view() {
		// T5CrclStdy t5CrclStdy = T5CrclStdy.dao.findById(getPara()); //guuid
		T5CrclStdy t5CrclStdy = T5CrclStdyService.service.SelectById(getParaToInt()); // serial int id
		setAttr("t5CrclStdy", t5CrclStdy);
		renderWithPath(pthv + "view.html");
	}

	/**
	 * 删除
	 */
	public void delete() {
		// T5CrclStdyService.service.delete("t5_crcl_stdy", getPara() == null ? ids :
		// getPara()); //guuid
		
		T5CrclStdyService.service.deleteById("t5_crcl_stdy", getPara() == null ? ids : getPara()); // serial int id
		redirect(pthc);
	}

	public void setViewPath() {
		setAttr(ConstantRender.PATH_CTL_NAME, pthc);
		setAttr(ConstantRender.PATH_VIEW_NAME, pthv);
	}

}
