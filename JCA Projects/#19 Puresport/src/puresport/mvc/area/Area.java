package puresport.mvc.area;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import org.apache.log4j.Logger;

/**
 * @description：
 * @author ZW
 */
@SuppressWarnings("unused")
//@Table(tableName = "dt_area")
public class Area extends BaseModel<Area> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(Area.class);
	
	public static final Area dao = new Area();
	
	/**
	 * 字段描述：区域主键 
	 * 字段类型：int  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：区域名称 
	 * 字段类型：varchar  长度：16
	 */
	public static final String column_name = "name";
	
	/**
	 * 字段描述：区域代码 
	 * 字段类型：varchar  长度：128
	 */
	public static final String column_code = "code";
	
	/**
	 * 字段描述：上级主键 
	 * 字段类型：int  长度：null
	 */
	public static final String column_parent_id = "parent_id";
	
	
	/**
	 * sqlId : puresport.area.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.area.splitPageFrom";

	private Integer id;
	private String name;
	private String code;
	private Integer parent_id;

	public void setId(Integer id){
		set(column_id, id);
	}
	public Integer getId() {
		return getInt(column_id);
	}
	public void setName(String name){
		set(column_name, name);
	}
	public <T> T getName() {
		return get(column_name);
	}
	public void setCode(String code){
		set(column_code, code);
	}
	public <T> T getCode() {
		return get(column_code);
	}
	public void setParent_id(Integer parent_id){
		set(column_parent_id, parent_id);
	}
	public Integer getParent_id() {
		return getInt(column_parent_id);
	}
	
}
