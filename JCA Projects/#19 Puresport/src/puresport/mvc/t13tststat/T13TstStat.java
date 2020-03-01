package puresport.mvc.t13tststat;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp; 
import java.math.BigInteger; 

import org.apache.log4j.Logger;

/**
 * @description：
 * @author ZW
 */
@SuppressWarnings("unused")
//@Table(tableName = "t13_tst_stat")
public class T13TstStat extends BaseModel<T13TstStat> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T13TstStat.class);
	
	public static final T13TstStat dao = new T13TstStat();
	
	public static final String  tableName = "t13_tst_stat";
	
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：试题id 
	 * 字段类型：int  长度：null
	 */
	public static final String column_prblmid = "prblmid";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	/**
	 * 字段描述：正确答题次数 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_right_num = "right_num";
	
	/**
	 * 字段描述：错误答题次数 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_wrong_num = "wrong_num";
	
	
	/**
	 * sqlId : puresport.t13TstStat.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t13TstStat.splitPageFrom";

	private Long id;
	private Integer prblmid;
	private Timestamp tms;
	private Long right_num;
	private Long wrong_num;

	public void setId(Long id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setPrblmid(Integer prblmid){
		set(column_prblmid, prblmid);
	}
	public <T> T getPrblmid() {
		return get(column_prblmid);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	public void setRight_num(Long right_num){
		set(column_right_num, right_num);
	}
	public <T> T getRight_num() {
		return get(column_right_num);
	}
	public void setWrong_num(Long wrong_num){
		set(column_wrong_num, wrong_num);
	}
	public <T> T getWrong_num() {
		return get(column_wrong_num);
	}
	
}
