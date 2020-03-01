package puresport.mvc.t8exam;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp; 

import org.apache.log4j.Logger;

/**
 * @description：
 * @author ZW
 */
@SuppressWarnings("unused")
//@Table(tableName = "t8_exam")
public class T8Exam extends BaseModel<T8Exam> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T8Exam.class);
	
	public static final T8Exam dao = new T8Exam();
	
	/**
	 * 字段描述：考试id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_examid = "examid";
	
	/**
	 * 字段描述：考试简介 
	 * 字段类型：varchar  长度：2048
	 */
	public static final String column_exam_brf = "exam_brf";
	
	/**
	 * 字段描述：行政区划代码 
	 * 字段类型：int  长度：null
	 */
	public static final String column_adiv_cd = "adiv_cd";
	
	/**
	 * 字段描述：考试名称 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_exam_nm = "exam_nm";
	
	/**
	 * 字段描述：考试属性（是否必考） 
	 * 字段类型：char  长度：1
	 */
	public static final String column_exam_attr = "exam_attr";
	
	/**
	 * 字段描述：学分 
	 * 字段类型：int  长度：null
	 */
	public static final String column_ty_grd = "ty_grd";
	
	/**
	 * 字段描述：试题id列表 
	 * 字段类型：varchar  长度：2048
	 */
	public static final String column_prblmid_list = "prblmid_list";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	
	/**
	 * sqlId : puresport.t8Exam.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t8Exam.splitPageFrom";

	private Long examid;
	private String exam_brf;
	private Integer adiv_cd;
	private String exam_nm;
	private String exam_attr;
	private Integer ty_grd;
	private String prblmid_list;
	private Timestamp tms;

	public void setExamid(Long examid){
		set(column_examid, examid);
	}
	public <T> T getExamid() {
		return get(column_examid);
	}
	public void setExam_brf(String exam_brf){
		set(column_exam_brf, exam_brf);
	}
	public <T> T getExam_brf() {
		return get(column_exam_brf);
	}
	public void setAdiv_cd(Integer adiv_cd){
		set(column_adiv_cd, adiv_cd);
	}
	public <T> T getAdiv_cd() {
		return get(column_adiv_cd);
	}
	public void setExam_nm(String exam_nm){
		set(column_exam_nm, exam_nm);
	}
	public <T> T getExam_nm() {
		return get(column_exam_nm);
	}
	public void setExam_attr(String exam_attr){
		set(column_exam_attr, exam_attr);
	}
	public <T> T getExam_attr() {
		return get(column_exam_attr);
	}
	public void setTy_grd(Integer ty_grd){
		set(column_ty_grd, ty_grd);
	}
	public <T> T getTy_grd() {
		return get(column_ty_grd);
	}
	public void setPrblmid_list(String prblmid_list){
		set(column_prblmid_list, prblmid_list);
	}
	public <T> T getPrblmid_list() {
		return get(column_prblmid_list);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	
}
