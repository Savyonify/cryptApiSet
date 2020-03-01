package puresport.mvc.t4assc;

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
//@Table(tableName = "t4_assc")
public class T4Assc extends BaseModel<T4Assc> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T4Assc.class);
	
	public static final T4Assc dao = new T4Assc();
	
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：协会id 
	 * 字段类型：char  长度：6
	 */
	public static final String column_asscid = "asscid";
	
	/**
	 * 字段描述：协会名称 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_assc_nm = "assc_nm";
	
	/**
	 * 字段描述：上一级协会id 
	 * 字段类型：char  长度：6
	 */
	public static final String column_plvl_asscid = "plvl_asscid";
	
	/**
	 * 字段描述：协会层级 
	 * 字段类型：char  长度：1
	 */
	public static final String column_assc_hier = "assc_hier";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	
	/**
	 * sqlId : puresport.t4Assc.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t4Assc.splitPageFrom";

	private Long id;
	private String asscid;
	private String assc_nm;
	private String plvl_asscid;
	private String assc_hier;
	private Timestamp tms;

	public void setId(Long id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setAsscid(String asscid){
		set(column_asscid, asscid);
	}
	public <T> T getAsscid() {
		return get(column_asscid);
	}
	public void setAssc_nm(String assc_nm){
		set(column_assc_nm, assc_nm);
	}
	public <T> T getAssc_nm() {
		return get(column_assc_nm);
	}
	public void setPlvl_asscid(String plvl_asscid){
		set(column_plvl_asscid, plvl_asscid);
	}
	public <T> T getPlvl_asscid() {
		return get(column_plvl_asscid);
	}
	public void setAssc_hier(String assc_hier){
		set(column_assc_hier, assc_hier);
	}
	public <T> T getAssc_hier() {
		return get(column_assc_hier);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	
}
