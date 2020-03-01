package puresport.mvc.t5crclstdy;

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
//@Table(tableName = "t5_crcl_stdy")
public class T5CrclStdy extends BaseModel<T5CrclStdy> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T5CrclStdy.class);
	
	public static final T5CrclStdy dao = new T5CrclStdy();
	
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
	 * 字段描述：课程id 
	 * 字段类型：int  长度：null
	 */
	public static final String column_crclid = "crclid";
	
	/**
	 * 字段描述：学习状态 
	 * 字段类型：char  长度：1
	 */
	public static final String column_stdy_st = "stdy_st";
	
	/**
	 * 字段描述：学分 
	 * 字段类型：float  长度：null
	 */
	public static final String column_ty_grd = "ty_grd";
	
	/**
	 * 字段描述：赛事类型
	 * 字段类型：String 
	 * "0","省运会","1","亚运会","2","青奥会","3","军运会","4","东京奥运会","6","冬青奥会","7","十四冬会"
	 */
	public static final String column_type = "type";
	
	/**
	 * 字段描述：科目
	 * 字段类型：String 
	 * "1":科目1;"2":科目2;"3":科目3;"4":科目4;"5":科目5;"6":科目6;
	 */
	public static final String column_category = "category";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	
	/**
	 * sqlId : puresport.t5CrclStdy.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t5CrclStdy.splitPageFrom";

	private Long id;
	private Integer usrid;
	private Integer crclid;
	private String stdy_st;
	private Float ty_grd;
	private Timestamp tms;
	private String type;
	private String category;

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
	public void setCrclid(Integer crclid){
		set(column_crclid, crclid);
	}
	public <T> T getCrclid() {
		return get(column_crclid);
	}
	public void setStdy_st(String stdy_st){
		set(column_stdy_st, stdy_st);
	}
	public <T> T getStdy_st() {
		return get(column_stdy_st);
	}
	public void setTy_grd(Float ty_grd){
		set(column_ty_grd, ty_grd);
	}
	public <T> T getTy_grd() {
		return get(column_ty_grd);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	
}
