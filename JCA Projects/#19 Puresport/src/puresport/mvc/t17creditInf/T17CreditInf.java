package puresport.mvc.t17creditInf;

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
//@Table(tableName = "t17_credit_inf")
public class T17CreditInf extends BaseModel<T17CreditInf> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T17CreditInf.class);
	
	public static final T17CreditInf dao = new T17CreditInf();
	
	public static final String  tableName = "t17_credit_inf";
	
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：赛事类型 
	 * 字段类型：char  长度：null
	 */
	public static final String column_type = "type";
	
	/**
	 * 字段描述：姓名 
	 * 字段类型：varchar  长度：null
	 */
	public static final String column_nm = "nm";
	
	/**
	 * 字段描述：证件类型 
	 * 字段类型：char  长度：null
	 */
	public static final String column_crdt_tp = "crdt_tp";
	
	/**
	 * 字段描述：证件号 
	 * 字段类型：varchar  长度：null
	 */
	public static final String column_crdt_no = "crdt_no";
	
	/**
	 * 字段描述：用户id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_usrid = "usrid";
	
	/**
	 * 字段描述：生成时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	/**
	 * 字段描述：类型 
	 * 字段类型：char  长度：null
	 */
	public static final String column_flag = "flag";
	
	/**
	 * 字段描述：文件路径 
	 * 字段类型：varchar  长度：null
	 */
	public static final String column_file_path = "file_path";
	
	/**
	 * 字段描述：证书编号 
	 * 字段类型：char  长度：null
	 */
	public static final String column_credit_no = "credit_no";
	
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
 * 字段描述：证书名称
 * 字段类型：int  长度：null
 */
public static final String column_name = "name";
	
	
	/**
	 * sqlId : puresport.t15CreditInf.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t15CreditInf.splitPageFrom";

	private Long id;
	private String type;
	private String nm;
	private String crdt_tp;
	private String crdt_no;
	private Long usrid;
	private Timestamp tms;
	private String flag;
	private String file_path;
	private String credit_no;
	
	private Integer examid;
	private Integer exam_grd;
	private String name;

	public void setName(String name){
		set(column_name, name);
	}
	public <T> T getName() {
		return get(column_name);
	}
	
	public void setId(Long id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setType(String type){
		set(column_type, type);
	}
	public <T> T getType() {
		return get(column_type);
	}
	public void setNm(String nm){
		set(column_nm, nm);
	}
	public <T> T getNm() {
		return get(column_nm);
	}
	public void setCrdt_tp(String crdt_tp){
		set(column_crdt_tp, crdt_tp);
	}
	public <T> T getCrdt_tp() {
		return get(column_crdt_tp);
	}
	public void setCrdt_no(String crdt_no){
		set(column_crdt_no, crdt_no);
	}
	public <T> T getCrdt_no() {
		return get(column_crdt_no);
	}
	public void setUsrid(Long usrid){
		set(column_usrid, usrid);
	}
	public <T> T getUsrid() {
		return get(column_usrid);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	public void setFlag(String flag){
		set(column_flag, flag);
	}
	public <T> T getFlag() {
		return get(column_flag);
	}
	public void setFile_path(String file_path){
		set(column_file_path, file_path);
	}
	public <T> T getFile_path() {
		return get(column_file_path);
	}
	public void setCredit_no(String credit_no){
		set(column_credit_no, credit_no);
	}
	public <T> T getCredit_no() {
		return get(column_credit_no);
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
}
