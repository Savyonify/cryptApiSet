package puresport.mvc.area;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.jfinal.aop.Enhancer;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.platform.mvc.base.BaseService;

import csuduc.platform.util.StringUtil;
import puresport.config.ConfMain;
import puresport.constant.ConstantInitMy;
import puresport.constant.EnumTypeLevel;
import puresport.mvc.comm.ParamComm;
import puresport.mvc.t6mgrahr.T6MgrSession;

public class AreaService extends BaseService {

	private final static String tableName = "dt_area";
	private final static String tableKey = "id";
	
	private final static String CacheName_Provinces= "CacheName_Provinces";
	private final static String CacheName_ProvinceCities= "CacheName_ProvinceCities";
	
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(AreaService.class);
	
	public static final AreaService service = Enhancer.enhance(AreaService.class);
	
	public Area SelectById(Integer id){
		
		Area mdl = Area.dao.findFirst("select * from dt_area where id=?", id);
		return mdl;
	}
	
	public List<Area> fetchProvinces(){
		return Area.dao.findByCache(CacheName_Provinces, CacheName_Provinces, "select * from dt_area where parent_id=0 limit 100");
	}
	
	public List<Area> fetchCities(Integer provinceId){
		return Area.dao.findByCache(CacheName_ProvinceCities, provinceId, "select * from dt_area where parent_id=? limit 100",provinceId);
	}
	
	public Area findProvinceByName(String name) {
		return Area.dao.findFirstByCache(CacheName_Provinces, name, "select * from dt_area where name=? and parent_id=0 limit 1",name);
	}
	
	public List<Area> fetchCities(String provinceName){
		Area area = findProvinceByName(provinceName);
		if (null == area) {
			return Collections.EMPTY_LIST;
		}
		return Area.dao.findByCache(CacheName_ProvinceCities, provinceName, "select * from dt_area where parent_id=? limit 100",area.getId());
	}
	
	public List<Area> getProvince(T6MgrSession mgrSession){
		String whereStr = "1 = 2 ";
		if (mgrSession.getTypeleve().equals(EnumTypeLevel.Country.getName())) {
			// 国家级 全部可见
			whereStr = " parent_id=0 ";
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.Province.getName())
				|| mgrSession.getTypeleve().equals(EnumTypeLevel.City.getName())) {
			// 省市级 只可见属于该省的
			whereStr = String.format(" name='%s' ", mgrSession.getProvince());
		} 
		return  Area.dao.find(String.format("select id, name from %s where %s ", tableName, whereStr));
	}
	
	public List<Area> getCityByProvince(T6MgrSession mgrSession, Integer provinceId){
		String whereStr = "1 = 2 ";
		if (mgrSession.getTypeleve().equals(EnumTypeLevel.Country.getName())
				|| mgrSession.getTypeleve().equals(EnumTypeLevel.Province.getName())) {
			// 国家、省级 全部可见
			whereStr = String.format(" parent_id= %d ", provinceId);
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.City.getName())) {
			// 市级 只可见属于该市的
			whereStr = String.format(" name='%s' and parent_id= %d", mgrSession.getCity(), provinceId);
		} 
		return  Area.dao.find(String.format("select id, name from %s where %s ", tableName, whereStr));
	}
	
	public List<Record> getInstitute(){
		return ConfMain.db().find(String.format("select institute from t1_usr_bsc group by institute "));
	}
	
	public static String getProvinceWhere(T6MgrSession mgrSession) {
		StringBuilder whereStr = new StringBuilder();
		if (mgrSession.getTypeleve().equals(EnumTypeLevel.Country.getName())) {
			// 国家级 全部可见
			whereStr.append(" 1=1 ");
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.Province.getName())) {
			// 省级 只可见属于该省的
			whereStr.append(String.format(" province like '%s%%'  and levelprovince =1 ", mgrSession.getProvince()));
		} else if (mgrSession.getTypeleve().equals(EnumTypeLevel.City.getName())) {
			// 市级 只可见属于该市的
			whereStr.append(String.format(" city like '%s%%'   and province like '%s%%'  and levelcity =1 ",mgrSession.getCity(), mgrSession.getProvince()));
		}else{
			whereStr.append(" 1= 2 ");
		}
		return whereStr.toString();
	}
	public List<Record> getProject(T6MgrSession mgrSession){
		return ConfMain.db().find(String.format("select spt_prj from t1_usr_bsc where  %s and spt_prj is not null and spt_prj<>'' group by spt_prj ", getProvinceWhere(mgrSession)));
	}

	public List<Record> getQuestionType(){
		return ConfMain.db().find(String.format("select prblm_tp from t9_tstlib group by prblm_tp "));
	}
}
