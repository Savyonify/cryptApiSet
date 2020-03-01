package puresport.mvc.t10examgrd;

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
//@Table(tableName = "t10_exam_grd")
public class T10ExamGrd extends BaseModel<T10ExamGrd> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T10ExamGrd.class);
	
	public static final T10ExamGrd dao = new T10ExamGrd();
	
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：用户id 
	 * 字段类型：int  长度：null
	 */
	public static final String column_usrid = "usrid";
	
	/**
	 * 字段描述：考试id 
	 * 字段类型：int  长度：null
	 */
	public static final String column_examid = "examid";
	
	/**
	 * 字段描述：考试成绩 
	 * 字段类型：int  长度：null
	 */
	public static final String column_exam_grd = "exam_grd";
	
	/**
	 * 字段描述：考试状态 
	 * 字段类型：char  长度：1
	 */
	public static final String column_exam_st = "exam_st";
	
	/**
	 * 字段描述：试题id 
	 * 字段类型：int  长度：null
	 */
	public static final String column_prblmid = "prblmid";
	
	/**
	 * 字段描述：用户答案 
	 * 字段类型：char  长度：1
	 */
	public static final String column_usr_aswr = "usr_aswr";
	
	/**
	 * 字段描述：试题答案 
	 * 字段类型：char  长度：1
	 */
	public static final String column_prblm_aswr = "prblm_aswr";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	/**
	 * 字段描述：试题编号 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_prblmno = "prblmno";
	/**
	 * 字段描述：答题开始时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_exam_st_tm = "exam_st_tm";
	/**
	 * 字段描述：答题结束时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_exam_end_tm = "exam_end_tm";
	
	/**
	 * 字段描述：答题结果：正确|错误
	 * 字段类型：varchar  长度：16
	 */
	public static final String column_result = "result";
	
	/**
	 * 字段描述：赛事类型
	 * 字段类型：String 
	 * "":通用赛事; "4"东京奥运会
	 */
	public static final String column_type = "type";
	
	/**
	 * 字段描述：科目
	 * 字段类型：String 
	 * "01":科目1;"02":科目2;"03":科目3;"04":科目4;"05":科目5;"06":科目6;
	 */
	public static final String column_category = "category";
	
	/**
	 * sqlId : puresport.t10ExamGrd.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t10ExamGrd.splitPageFrom";

	private Long id;
	private Integer usrid;
	private Integer examid;
	private Integer exam_grd;
	private String exam_st;
	private Integer prblmid;
	private String usr_aswr;
	private String prblm_aswr;
	private Timestamp tms;
	// 答题开始时间
	private Timestamp exam_st_tm;
	// 答题结束时间
	private Timestamp exam_end_tm;
	// 题号
	private Integer prblmno;
	
	private String result;
	private String type;
	private String category;
	
/*	// 题目
	private String ttl;
	// 答题结果对错描述
	private String rltDesc;
	// 试题类型
	private String prblm_tp;
	// 选项
	private String opt;*/

/*	public String getRltDesc() {
		return rltDesc;
	}
	public void setRltDesc(String rltDesc) {
		this.rltDesc = rltDesc;
	}
	public String getTtl() {
		return ttl;
	}
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}*/
	public <T> T  getType() {
		return get(column_type);
	}
	public void setType(String type) {
		set(column_type, type);
	}
	public <T> T  getCategory() {
		return get(column_category);
	}
	public void setCategory(String category) {
		set(column_category, category);
	}
	
	public void setPrblmno(Integer prblmno){
		set(column_prblmno, prblmno);
	}
	public <T> T getPrblmno() {
		return get(column_prblmno);
	}
	public void setExamStTm(Timestamp exam_st_tm){
		set(column_exam_st_tm, exam_st_tm);
	}
	public <T> T getExamStTm() {
		return get(column_exam_st_tm);
	}
	public void setExamEndTm(Timestamp exam_end_tm){
		set(column_exam_end_tm, exam_end_tm);
	}
	public <T> T getExamEndTm() {
		return get(column_exam_end_tm);
	}

	public void setId(Long id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setUsrid(Integer usrid){
		set(column_usrid, usrid);
	}
	public <T> T getUsrid() {
		return get(column_usrid);
	}
	public void setExamid(Integer examid){
		set(column_examid, examid);
	}
	public <T> T getExamid() {
		return get(column_examid);
	}
	public void setExam_grd(Integer exam_grd){
		set(column_exam_grd, exam_grd);
	}
	public <T> T getExam_grd() {
		return get(column_exam_grd);
	}
	public void setExam_st(String exam_st){
		set(column_exam_st, exam_st);
	}
	public <T> T getExam_st() {
		return get(column_exam_st);
	}
	public void setPrblmid(Integer prblmid){
		set(column_prblmid, prblmid);
	}
	public <T> T getPrblmid() {
		return get(column_prblmid);
	}
	public void setUsr_aswr(String usr_aswr){
		set(column_usr_aswr, usr_aswr);
	}
	public <T> T getUsr_aswr() {
		return get(column_usr_aswr);
	}
	public void setPrblm_aswr(String prblm_aswr){
		set(column_prblm_aswr, prblm_aswr);
	}
	public <T> T getPrblm_aswr() {
		return get(column_prblm_aswr);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	
	public void setResult(String result){
		set(column_result, result);
	}
	public <T> T getResult() {
		return get(column_result);
	}
	
	
}
