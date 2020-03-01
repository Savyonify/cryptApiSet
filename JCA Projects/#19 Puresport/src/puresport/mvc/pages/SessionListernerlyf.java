package puresport.mvc.pages;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.jfinal.core.Controller;
import com.platform.mvc.base.BaseController;

@WebListener
public class SessionListernerlyf implements HttpSessionListener{
	private static Logger LOG = Logger.getLogger(SessionListernerlyf.class);
//	private int userCounts = 0;
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
//		userCounts++;
		Object userCounts = arg0.getSession().getServletContext().getAttribute("userCounts");
		if(userCounts==null)
		{
			userCounts = "0";
		}
		Integer count = 0;
		count  =Integer.valueOf(userCounts.toString());
		count++;
		arg0.getSession().getServletContext().setAttribute("userCounts", count);
//		arg0.getSession().setMaxInactiveInterval(30);
		
//		
//		LOG.debug("sessionCreated---"+arg0.getSession().getServletContext().getAttribute("userCounts"));
//		LOG.debug("sessionCreated---"+count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
//		userCounts--;
		Object userCounts = arg0.getSession().getServletContext().getAttribute("userCounts");
		Integer count = Integer.valueOf(userCounts.toString());
		count--;
		arg0.getSession().getServletContext().setAttribute("userCounts", count);
		
//		LOG.debug("sessionDestroyed---"+count);
	}

}
