package puresport.mvc.t14invitationcode;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;


import org.apache.log4j.Logger;

/**
 * @description：
 * @author ZW
 */
@SuppressWarnings("unused")
//@Table(tableName = "t14_invitation_code")
public class T14InvitationCode extends BaseModel<T14InvitationCode> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T14InvitationCode.class);
	
	public static final T14InvitationCode dao = new T14InvitationCode();
	
	public static final String  tableName = "t14_invitation_code";
	
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：邀请码 
	 * 字段类型：varchar  长度：null
	 */
	public static final String column_invitation_code = "invitation_code";
	
	/**
	 * 字段描述：已验证用户列表 
	 * 字段类型：text  长度：null
	 */
	public static final String column_invited_user_list = "invited_user_list";
	
	/**
	 * 字段描述：赛事类型 
	 * 字段类型：char  长度：null
	 */
	public static final String column_type = "type";
	
	
	/**
	 * sqlId : puresport.t14InvitationCode.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t14InvitationCode.splitPageFrom";

	private Long id;
	private String invitation_code;
	private String invited_user_list;
	private String type;

	public void setId(Long id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setInvitation_code(String invitation_code){
		set(column_invitation_code, invitation_code);
	}
	public <T> T getInvitation_code() {
		return get(column_invitation_code);
	}
	public void setInvited_user_list(String invited_user_list){
		set(column_invited_user_list, invited_user_list);
	}
	public <T> T getInvited_user_list() {
		return get(column_invited_user_list);
	}
	public void setType(String type){
		set(column_type, type);
	}
	public <T> T getType() {
		return get(column_type);
	}
	
}
