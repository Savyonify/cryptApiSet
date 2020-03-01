/**
 * <p>title:routePlugins.java<／p>
 * <p>Description: <／p>
 * @date:2016年1月28日下午2:15:23
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package puresport.config;

import com.jfinal.config.Routes;

import puresport.mvc.area.AreaController;

/**
 * 创建时间：2016年1月28日 下午2:15:23
 * 项目名称：DUCPlatFromWeb
 * 文件类型：RoutePlugins.java
 * 类说明：
 *
 *  
 *修改日志：
 * Date			Author		Version		Description
 *---------------------------------------------------
 *2016年1月28日		Zhongweng	1.0			1.0Version
 */


import puresport.mvc.pages.pagesController;
import puresport.mvc.r16groupusr.R16GroupUsrController;
import puresport.mvc.sport_item.Sport_ItemController;
import puresport.mvc.t10examgrd.T10ExamGrdController;
import puresport.mvc.t11examstat.T11ExamStatController;
import puresport.mvc.t12highestscore.T12HighestScoreController;
import puresport.mvc.t13tststat.T13TstStatController;
import puresport.mvc.t15group.T15GroupController;
import puresport.mvc.t1usrbsc.T1usrBscController;
import puresport.mvc.t2adiv.T2AdivController;
import puresport.mvc.t3statl.T3StatlController;
import puresport.mvc.t4assc.T4AsscController;
import puresport.mvc.t5crclstdy.T5CrclStdyController;
import puresport.mvc.t6mgrahr.T6MgrAhrController;
import puresport.mvc.t7crcl.T7CrclController;
import puresport.mvc.t8exam.T8ExamController;
import puresport.mvc.t9tstlib.T9TstlibController;


/**
 * <p>
 * Title: RoutePlugins<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2016年1月28日
 */
public class RoutePlugins extends Routes {
	@Override
	public void config() {
//		add("/", pagesController.class);
		add("/jf/puresport/pagesController", pagesController.class);
		add("/jf/puresport/area", AreaController.class);
		add("/jf/puresport/sport_Item", Sport_ItemController.class);
		add("/jf/puresport/t1usrBsc", T1usrBscController.class);
		add("/jf/puresport/t2Adiv", T2AdivController.class);
		add("/jf/puresport/t3Statl", T3StatlController.class);
		add("/jf/puresport/t4Assc", T4AsscController.class);
		add("/jf/puresport/t5CrclStdy", T5CrclStdyController.class);
		add("/jf/puresport/t6MgrAhr", T6MgrAhrController.class);
		add("/jf/puresport/t7Crcl", T7CrclController.class);
		add("/jf/puresport/t8Exam", T8ExamController.class);
		add("/jf/puresport/t9Tstlib", T9TstlibController.class);
		add("/jf/puresport/t10ExamGrd", T10ExamGrdController.class);
		add("/jf/puresport/T11ExamStat", T11ExamStatController.class);
		add("/jf/puresport/T12HighestScore", T12HighestScoreController.class);
		add("/jf/puresport/T13TstStat", T13TstStatController.class);
		add("/jf/puresport/t15Group", T15GroupController.class);
		add("/jf/puresport/r16GroupUsr", R16GroupUsrController.class);
	}
}
