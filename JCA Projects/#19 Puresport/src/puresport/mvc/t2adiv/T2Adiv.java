package puresport.mvc.t2adiv;

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
//@Table(tableName = "t2_adiv")
public class T2Adiv extends BaseModel<T2Adiv> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T2Adiv.class);
	
	public static final T2Adiv dao = new T2Adiv();
	
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：行政区划代码 
	 * 字段类型：int  长度：null
	 */
	public static final String column_adiv_cd = "adiv_cd";
	
	/**
	 * 字段描述：单位名称 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_unit_nm = "unit_nm";
	
	/**
	 * 字段描述：上一级行政区划代码 
	 * 字段类型：char  长度：6
	 */
	public static final String column_plvl_adiv_cd = "plvl_adiv_cd";
	
	/**
	 * 字段描述：行政区划层级 
	 * 字段类型：char  长度：1
	 */
	public static final String column_adiv_hier = "adiv_hier";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	
	/**
	 * sqlId : puresport.t2Adiv.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t2Adiv.splitPageFrom";

	private Long id;
	private Integer adiv_cd;
	private String unit_nm;
	private String plvl_adiv_cd;
	private String adiv_hier;
	private Timestamp tms;

	public void setId(Long id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setAdiv_cd(Integer adiv_cd){
		set(column_adiv_cd, adiv_cd);
	}
	public <T> T getAdiv_cd() {
		return get(column_adiv_cd);
	}
	public void setUnit_nm(String unit_nm){
		set(column_unit_nm, unit_nm);
	}
	public <T> T getUnit_nm() {
		return get(column_unit_nm);
	}
	public void setPlvl_adiv_cd(String plvl_adiv_cd){
		set(column_plvl_adiv_cd, plvl_adiv_cd);
	}
	public <T> T getPlvl_adiv_cd() {
		return get(column_plvl_adiv_cd);
	}
	public void setAdiv_hier(String adiv_hier){
		set(column_adiv_hier, adiv_hier);
	}
	public <T> T getAdiv_hier() {
		return get(column_adiv_hier);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	
}
