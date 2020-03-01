package puresport.mvc.comm;

import java.util.Date;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import puresport.config.ConfMain;
import puresport.mvc.t3statl.T3Statl;
import puresport.mvc.t3statl.T3StatlService;

public class PageViewSta {
	private final static String tableName = "t3_stat";
	private final static String tableKey = "id";
	public  static boolean StaLoginPeopleCountByDay()
	{
		try {
			T3Statl mdl = T3StatlService.service.SelectByDate();
			if(mdl!=null)
			{
//				int count = mdl.getLogin_cnt();
//				Long id = mdl.getId();
				int count = Integer.valueOf(mdl.getLogin_cnt());
				Long id = Long.valueOf(mdl.getId());
				int res = ConfMain.db().update("update t3_stat set login_cnt=? where id=?",count+1,id);
			}
			else {
				Date dt  = new Date();
				Record newRecord =new Record()
//						.set(T3Statl.column_tms, dt)
						.set(T3Statl.column_login_cnt, 1);
				ConfMain.db().save(tableName,tableKey,newRecord);
//				Db.save("puresport.t3_stat",newRecord);
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
