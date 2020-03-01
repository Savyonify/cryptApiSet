package puresport.mvc.pages;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class FunctionInterceptor implements Interceptor{
	private static Logger LOG = Logger.getLogger(FunctionInterceptor.class);
	public static final String pthc = "/jf/puresport/pagesController/";
	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		HttpSession session = inv.getController().getSession();
		if(session.getAttribute("lastTime")!=null)
		{
			long currentTime = System.currentTimeMillis();
			long lastTime = (Long)(session.getAttribute("lastTime"));
			long time = currentTime - lastTime;
			
			session.setAttribute("lastTime", currentTime);
			
			if(time <3000)
			{
				LOG.debug("频繁访问");
				inv.getController().redirect(pthc+"tips");
			}
			else {
				inv.invoke();
			}
		}
		else {
			long currentTime = System.currentTimeMillis();
			session.setAttribute("lastTime", currentTime);
			LOG.debug("FunctionInterceptor---"+currentTime);
			inv.invoke();
		}
	}

}
