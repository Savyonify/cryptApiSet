package puresport.mvc.t9tstlib;

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
//@Table(tableName = "t9_tstlib")
public class T9Tstlib extends BaseModel<T9Tstlib> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T9Tstlib.class);
	
	public static final T9Tstlib dao = new T9Tstlib();
	
	/**
	 * 字段描述：试题id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_prblmid = "prblmid";
	
	/**
	 * 字段描述：出题者 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_prblm_ppl = "prblm_ppl";
	
	/**
	 * 字段描述：试题类型 
	 * 字段类型：char  长度：2
	 */
	public static final String column_prblm_tp = "prblm_tp";
	
	/**
	 * 字段描述：选项 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_opt = "opt";
	
	/**
	 * 字段描述：题目 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_ttl = "ttl";
	
	/**
	 * 字段描述：试题答案 
	 * 字段类型：char  长度：1
	 */
	public static final String column_prblm_aswr = "prblm_aswr";
	
	/**
	 * 字段描述：分数 
	 * 字段类型：int  长度：null
	 */
	public static final String column_scor = "scor";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
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
	 * sqlId : puresport.t9Tstlib.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t9Tstlib.splitPageFrom";

	private Integer prblmid;
	private String prblm_ppl;
	private String prblm_tp;
	private String opt;
	private String ttl;
	private String prblm_aswr;
	private Integer scor;
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
	public void setPrblmid(Integer prblmid){
		set(column_prblmid, prblmid);
	}
	public <T> T getPrblmid() {
		return get(column_prblmid);
	}
	public void setPrblm_ppl(String prblm_ppl){
		set(column_prblm_ppl, prblm_ppl);
	}
	public <T> T getPrblm_ppl() {
		return get(column_prblm_ppl);
	}
	public void setPrblm_tp(String prblm_tp){
		set(column_prblm_tp, prblm_tp);
	}
	public <T> T getPrblm_tp() {
		return get(column_prblm_tp);
	}
	public void setOpt(String opt){
		set(column_opt, opt);
	}
	public <T> T getOpt() {
		return get(column_opt);
	}
	public void setTtl(String ttl){
		set(column_ttl, ttl);
	}
	public <T> T getTtl() {
		return get(column_ttl);
	}
	public void setPrblm_aswr(String prblm_aswr){
		set(column_prblm_aswr, prblm_aswr);
	}
	public <T> T getPrblm_aswr() {
		return get(column_prblm_aswr);
	}
	public void setScor(Integer scor){
		set(column_scor, scor);
	}
	public <T> T getScor() {
		return get(column_scor);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	
}
