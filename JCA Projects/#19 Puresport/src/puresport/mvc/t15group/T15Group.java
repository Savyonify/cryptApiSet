package puresport.mvc.t15group;

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
//@Table(tableName = "t15_group")
public class T15Group extends BaseModel<T15Group> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T15Group.class);
	
	public static final T15Group dao = new T15Group();
	
	public static final String  tableName = "t15_group";
	
	/**
	 * 字段描述： 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：管理者id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_mgr_id = "mgr_id";
	
	/**
	 * 字段描述：标题 
	 * 字段类型：varchar  长度：null
	 */
	public static final String column_title = "title";
	
	/**
	 * 字段描述： 
	 * 字段类型：varchar  长度：null
	 */
	public static final String column_content = "content";
	
	/**
	 * 字段描述：类型 
	 * 字段类型：int  长度：null
	 */
	public static final String column_type = "type";
	
	/**
	 * 字段描述：状态 
	 * 字段类型：int  长度：null
	 */
	public static final String column_status = "status";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	
	/**
	 * sqlId : puresport.t15Group.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t15Group.splitPageFrom";

	private Long id;
	private Long mgr_id;
	private String title;
	private String content;
	private Integer type;
	private Integer status;
	private Timestamp tms;

	public void setId(Long id){
		set(column_id, id);
	}
	public Long getId() {
		return getLong(column_id);
	}
	public void setMgr_id(Long mgr_id){
		set(column_mgr_id, mgr_id);
	}
	public Long getMgr_id() {
		return getLong(column_mgr_id);
	}
	public void setTitle(String title){
		set(column_title, title);
	}
	public <T> T getTitle() {
		return get(column_title);
	}
	public void setContent(String content){
		set(column_content, content);
	}
	public <T> T getContent() {
		return get(column_content);
	}
	public void setType(Integer type){
		set(column_type, type);
	}
	public <T> T getType() {
		return get(column_type);
	}
	public void setStatus(Integer status){
		set(column_status, status);
	}
	public <T> T getStatus() {
		return get(column_status);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	
}
