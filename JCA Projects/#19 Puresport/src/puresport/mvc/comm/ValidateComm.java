/**
 * PureSport
 * create by zw at 2018年6月14日
 * version: v1.0
 */
package puresport.mvc.comm;

import java.util.Arrays;
import java.util.List;

import net.sf.ehcache.hibernate.strategy.ReadOnlyEhcacheEntityRegionAccessStrategy;
import puresport.constant.EnumStatus;
import puresport.constant.EnumTypeLevel;
import puresport.mvc.area.Area;
import puresport.mvc.t1usrbsc.T1usrBsc;
import puresport.mvc.t6mgrahr.T6MgrAhr;
import puresport.mvc.t6mgrahr.T6MgrSession;

/**
 * @author zw
 *
 */
public class ValidateComm {

	private static List<String> crdt_typeList = Arrays.asList("身份证", "护照", "军官证", "注册证");
	private static List<String> gndList = Arrays.asList("男", "女");
	private static List<String> typeleveList = Arrays.asList("国家级", "省级", "市级");

	public static boolean v_column_crdt_tp(String nm) {
		if (crdt_typeList.contains(nm)) {
			return true;
		}
		return false;
	}

	public static boolean inv_column_crdt_tp(String nm) {
		return !v_column_crdt_tp(nm);
	}

	public static boolean v_column_gnd(String gnd) {
		if (gndList.contains(gnd)) {
			return true;
		}
		return false;
	}

	public static boolean inv_column_gnd(String gnd) {
		return !v_column_gnd(gnd);
	}

	public static boolean v_column_typeleve(T6MgrSession mgrSession, String column_typeleve) {
		if (mgrSession.getTypeleve().equals(EnumTypeLevel.Country.getName())) {
			if (column_typeleve.equals(EnumTypeLevel.Country.getName())
					||column_typeleve.equals(EnumTypeLevel.CenterInstitute.getName())||column_typeleve.equals(EnumTypeLevel.Province.getName())) {
				return true;
			}
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.Province.getName())) {
			if (column_typeleve.equals(EnumTypeLevel.City.getName())) {
				return true;
			}
		}
		return false;
	}

	public static boolean inv_column_typeleve(T6MgrSession mgrSession, String column_typeleve) {
		return !v_column_typeleve(mgrSession, column_typeleve);
	}

	private static String province_cache_name = "provinceCityCaching";

	public static boolean v_column_province(T6MgrSession mgrSession, String column_province) {
		// 如果省级管理员 只能导入本省下的管理员
		if (mgrSession.getTypeleve().equals(EnumTypeLevel.Province.getName())) {
			if (mgrSession.getProvince().equals(column_province)) {
				return true;
			}
			return false;
		}
		Area province = Area.dao.findFirstByCache(province_cache_name, column_province,
				String.format("select * from dt_area where name = '%s' limit 1", column_province));
		if (null == province) {
			return false;
		}
		return true;
	}

	public static boolean inv_column_province(T6MgrSession mgrSession, String column_province) {
		return !v_column_province(mgrSession, column_province);
	}

	public static boolean v_column_city(String column_province, String column_city) {
		Area province = Area.dao.findFirstByCache(province_cache_name, column_province,
				String.format("select * from dt_area where name = '%s' limit 1", column_province));
		if (null == province) {
			return false;
		}
		Area city = Area.dao.findFirstByCache(province_cache_name, column_province + column_city,
				String.format("select * from dt_area where parent_id=%s and name = '%s' limit 1",
						 province.getId(), column_city));
		if (null == city) {
			return false;
		}
		return true;
	}

	public static boolean inv_column_city(String province, String column_city) {
		return !v_column_city(province, column_city);
	}

	public static boolean inv_deleteProvince(T6MgrSession mgrSession, T6MgrAhr t6MgrAhr) {
		// 国家级 都可以删除
		if (mgrSession.getTypeleve().equals(EnumTypeLevel.Country.getName())) {
			return false;
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.Province.getName())) {
			// 省级只可删除其下的市的管理员
			if (!mgrSession.getProvince().equals(t6MgrAhr.getProvince())) {
				return true;
			}
			if (inv_column_city(mgrSession.getProvince(), t6MgrAhr.getCity())) {
				return 	true;
			}
			return false;
		}
		return true;
	}
	

	public static boolean inv_deleteProvince_sporter(T6MgrSession mgrSession, T1usrBsc sporter) {
		// 国家级 都可以删除
		if (mgrSession.getTypeleve().equals(EnumTypeLevel.Country.getName())) {
			sporter.setTypelevel(EnumStatus.LevelDeleted.getIdStr());
			return false;
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.Province.getName())) {
			// 省级只可删除其省级的
			if (!mgrSession.getProvince().equals(sporter.getProvince())) {
				return true;
			} else {
				sporter.setLevelprovince(EnumStatus.LevelDeleted.getId());
				return false;
			}
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.City.getName())) {
			if (!mgrSession.getProvince().equals(sporter.getProvince())) {
				return true;
			} 
			if (inv_column_city(mgrSession.getProvince(), sporter.getCity())) {
				return 	true;
			}
			sporter.setLevelcity(EnumStatus.LevelDeleted.getId());
			return false;
		}
		else if(mgrSession.getTypeleve().equals(EnumTypeLevel.CenterInstitute.getName()))
		{
			if(mgrSession.getInstitute().equals(sporter.getInstitute()))
			{
				sporter.setTypelevel(String.valueOf(EnumStatus.LevelDeleted.getId()));
				sporter.setLevelinstitute(EnumStatus.LevelDeleted.getId());
				return false;
			}
		}
		return true;
	}
	

	public static void main(String[] args) {
		System.out.println(inv_column_crdt_tp(new String("身份证")));
	}
}
