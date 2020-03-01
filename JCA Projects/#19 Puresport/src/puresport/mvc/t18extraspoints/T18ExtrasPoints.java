package puresport.mvc.t18extraspoints;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import org.apache.log4j.Logger;

/**
 * @description：
 * @author ZW
 */
@SuppressWarnings("unused")
//@Table(tableName = "t18_extras_points")
public class T18ExtrasPoints extends BaseModel<T18ExtrasPoints> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T18ExtrasPoints.class);
	
	public static final T18ExtrasPoints dao = new T18ExtrasPoints();
	
	public static final String  tableName = "t18_extras_points";
	
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：邀请码 
	 * 字段类型：int  长度：null
	 */
	public static final String column_scor = "scor";
	
	/**
	 * 字段描述：赛事类型 
	 * 字段类型：char  长度：null
	 */
	public static final String column_type = "type";
	
	/**
	 * 字段描述：用户id 
	 * 字段类型：int  长度：null
	 */
	public static final String column_usrid = "usrid";
	
	/**
	 * 字段描述：科目 
	 * 字段类型：char  长度：null
	 */
	public static final String column_category = "category";
	
	
	/**
	 * sqlId : puresport.t18ExtrasPoints.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t18ExtrasPoints.splitPageFrom";

	private Long id;
	private Integer scor;
	private String type;
	private Integer usrid;
	private String category;

	public void setId(Long id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setScor(Integer scor){
		set(column_scor, scor);
	}
	public <T> T getScor() {
		return get(column_scor);
	}
	public void setType(String type){
		set(column_type, type);
	}
	public <T> T getType() {
		return get(column_type);
	}
	public void setUsrid(Integer usrid){
		set(column_usrid, usrid);
	}
	public <T> T getUsrid() {
		return get(column_usrid);
	}
	public void setCategory(String category){
		set(column_category, category);
	}
	public <T> T getCategory() {
		return get(column_category);
	}
	
}
