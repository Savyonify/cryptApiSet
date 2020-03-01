package puresport.config;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.jfinal.plugin.activerecord.DbPro;
import com.platform.config.run.BaseConfMain;
import com.platform.constant.ConstantInit;

import puresport.constant.ConstantInitMy;

/**  
 * 创建时间：2016年1月26日 上午11:13:45  
 * 项目名称：DUCPlatFormWeb   
 * 文件名称：ConfMain.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年1月26日     Zhongweng       1.0         1.0 Version   
 */

public class ConfMain extends BaseConfMain{
	private static Logger log = Logger.getLogger(ConfMain.class);
	private final static ConfMain single = new ConfMain();
	
	private static String phoneCodeId = "";
	private static String phoneCodeSec = "";
	
	public final static String getSecId() {
		if (StringUtils.isBlank(phoneCodeId)) {
			phoneCodeId = (String)single.getProperty().getparamMapMy(ConstantInit.phone_sec_id);
		}
		return phoneCodeId;
	}
	
	public final static String getSecKey() {
		if (StringUtils.isBlank(phoneCodeSec)) {
			phoneCodeSec = (String)single.getProperty().getparamMapMy(ConstantInit.phone_sec_key);
		}
		return phoneCodeSec;
	}
	
	public static ConfMain getInstance(){
		return single;
	}
	
	public static DbPro db() {
		return DbPro.use(ConstantInitMy.db_dataSource_main);
	}
}
