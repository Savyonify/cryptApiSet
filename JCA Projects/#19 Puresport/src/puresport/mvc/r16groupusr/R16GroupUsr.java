package puresport.mvc.r16groupusr;

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
//@Table(tableName = "r16_group_usr")
public class R16GroupUsr extends BaseModel<R16GroupUsr> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(R16GroupUsr.class);
	
	public static final R16GroupUsr dao = new R16GroupUsr();
	
	public static final String  tableName = "r16_group_usr";
	
	/**
	 * 字段描述： 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述： 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_group_id = "group_id";
	
	/**
	 * 字段描述： 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_user_id = "user_id";
	
	/**
	 * 字段描述： 
	 * 字段类型：int  长度：null
	 */
	public static final String column_type = "type";
	
	/**
	 * 字段描述： 
	 * 字段类型：int  长度：null
	 */
	public static final String column_status = "status";
	
	/**
	 * 字段描述：扩展 
	 * 字段类型：varchar  长度：null
	 */
	public static final String column_ext = "ext";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	
	/**
	 * sqlId : puresport.r16GroupUsr.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.r16GroupUsr.splitPageFrom";

	private Long id;
	private Long group_id;
	private Long user_id;
	private Integer type;
	private Integer status;
	private String ext;
	private Timestamp tms;

	public void setId(Long id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setGroup_id(Long group_id){
		set(column_group_id, group_id);
	}
	public Long getGroup_id() {
		return getLong(column_group_id);
	}
	public void setUser_id(Long user_id){
		set(column_user_id, user_id);
	}
	public Long getUser_id() {
		return getLong(column_user_id);
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
	public void setExt(String ext){
		set(column_ext, ext);
	}
	public <T> T getExt() {
		return get(column_ext);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	
}
