package puresport.mvc.sport_item;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import org.apache.log4j.Logger;

/**
 * @description：
 * @author ZW
 */
@SuppressWarnings("unused")
//@Table(tableName = "sport_item")
public class Sport_Item extends BaseModel<Sport_Item> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(Sport_Item.class);
	
	public static final Sport_Item dao = new Sport_Item();
	
	public static final String  tableName = "sport_item";
	
	/**
	 * 字段描述： 
	 * 字段类型：int  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：项目名称 
	 * 字段类型：varchar  长度：null
	 */
	public static final String column_name = "name";
	
	/**
	 * 字段描述： 
	 * 字段类型：int  长度：null
	 */
	public static final String column_parentid = "parentid";
	
	
	/**
	 * sqlId : puresport.sport_Item.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.sport_Item.splitPageFrom";

	private Integer id;
	private String name;
	private Integer parentid;

	public void setId(Integer id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setName(String name){
		set(column_name, name);
	}
	public <T> T getName() {
		return get(column_name);
	}
	public void setParentid(Integer parentid){
		set(column_parentid, parentid);
	}
	public <T> T getParentid() {
		return get(column_parentid);
	}
	
}
