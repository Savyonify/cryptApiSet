/**
 * PureSport
 * create by zw at 2018年6月3日
 * version: v1.0
 */
package puresport.applicat;

import java.io.IOException;

import com.jfinal.log.Logger;
import com.platform.config.run.ConfigCore;

/**
 * @author zw
 *
 */
public class ToolsTemp {
	private static Logger log = Logger.getLogger(ToolsTemp.class);

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		log.info("启动ConfigCore start ......");
		new ConfigCore();
		log.info("启动ConfigCore end ......");
		
	}

}
