package puresport.constant;

import com.platform.constant.ConstantInit;

/**  
 * 创建时间：2016年6月16日 下午9:19:14  
 * 项目名称：DUCPlatFormWeb   
 * 文件名称：ConstantInitMy.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年6月16日     Zhongweng       1.0         1.0 Version   
 */

public interface ConstantInitMy extends ConstantInit{
	public static final String db_dataSource_main = "db.dataSource.puresport";
	public static final String SPKEY = "A1B2C3D4E5F60708";
	
	public static final String validateKeyCode_Account = "validateKeyCode_Account";
	public static final String validateKey_UserId = "validateKey_UserId";
	
	public static final Integer AuthCode_TimeOut_Send = 61; // 单位秒 即1分钟
	public static final Integer AuthCode_TimeOut = 61*10; // 单位秒 即10分钟
	
	public static final Boolean ENTRANCE_WHITE_LIST_SWITCH = true; // 系统登录白名单开关
	public static final String ENTRANCE_WHITE_LIST = "410423198307274911,340881199103221237,420602199110050514,130727198707082438,370902197611170912,370602199302062320,110108198303299715,130727198707082434"; // 系统登录账号白名单列表，半角逗号分隔
	
}
