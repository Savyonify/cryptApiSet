package puresport.mvc.pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import puresport.mvc.t1usrbsc.T1usrBsc;
import puresport.mvc.t6mgrahr.T6MgrAhr;
import puresport.mvc.t7crcl.T7CrclController;

public class loginInterceptorlyf implements Interceptor {
	private static Logger LOG = Logger.getLogger(loginInterceptorlyf.class);
	public static final String pthc = "/jf/puresport/pagesController/";

	@Override
	public void intercept(Invocation inv) {

		HttpServletRequest request = inv.getController().getRequest();
		String ip = request.getRemoteAddr();
		LOG.debug("intercept---" + ip);
		String RequestURL = request.getRequestURL().toString();
		// TODO Auto-generated method stub
		HttpSession session = inv.getController().getSession();

		session.setAttribute("RequestURL", RequestURL);

		if (session.getAttribute("usrid") != null) {
			Long userID = Long.valueOf((String) session.getAttribute("usrid"));

			if ((session.getAttribute("usr_tp").equals("管理员"))) {
				T6MgrAhr item = T6MgrAhr.dao.findFirst("select * from t6_mgr_ahr where usrid=? limit 1", userID);// 根据用户名查询数据库中的用户
				if (item.getWrk_unit() != null && item.getPost() != null) {
					inv.invoke();
				} else {
					inv.getController().redirect(pthc + "login");
				}
			} else {// 远动员或者辅助人员

				T1usrBsc item = T1usrBsc.dao.findFirst("select * from t1_usr_bsc where usrid=? limit 1", userID);// 根据用户名查询数据库中的用户
				
				if (null == item.getMblPhVal() || 0 == item.getMblPhVal()) {
					inv.getController().redirect(pthc + "login");
					return ;
				}
				if ((item.getSpt_prj() != null) || (item.getDepartment() != null && item.getPost() != null)) {
					inv.invoke();
				} else {
					inv.getController().redirect(pthc + "login");
				}
			}
		} else {
			inv.getController().redirect(pthc + "login");
		}
	}

}
