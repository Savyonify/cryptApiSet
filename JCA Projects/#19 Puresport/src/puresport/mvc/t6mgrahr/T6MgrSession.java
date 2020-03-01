/**
 * PureSport
 * create by zw at 2018年6月2日
 * version: v1.0
 */
package puresport.mvc.t6mgrahr;

import java.io.Serializable;

import puresport.constant.EnumTypeLevel;

/**
 * @author zw
 *
 */
public class T6MgrSession implements Serializable {

	private static final long serialVersionUID = 2305013944330477412L;

	public final static String KeyName = "_T6MgrSession_";
	public final static String Def_NullStr = "--";

	private Long usrid;
	private String typeleve;
	private String province;
	private String city;
	private String institute;

	public T6MgrSession() {
	}

	public T6MgrSession(Long ausrid, String atypeleve, String aprovince, String acity, String ainstitute) {
		usrid = ausrid;
		typeleve = atypeleve;
		province = aprovince;
		city = acity;
		institute = ainstitute;
	}

	public T6MgrSession(T6MgrAhr mdl) {
		usrid = Long.valueOf((String) mdl.getUsrid());
		typeleve = mdl.getTypeleve();
		province = mdl.getProvince();
		city = mdl.getCity();
		institute = mdl.getInstitute();
	}

	public String selectRoleStr() {
		if (typeleve.equals(EnumTypeLevel.Country.getName())) {
			// 国家级 全部可见
			return " 1=1 ";
		} else if (typeleve.equals(EnumTypeLevel.Province.getName())) {
			// 省级 只可见属于该省的
			return String.format(" province='%s' and typeleve in ('省级', '市级') ", province);
		} else if (typeleve.equals(EnumTypeLevel.City.getName())) {
			// 市级 只可见属于该市的
			return String.format(" city='%s' and province='%s'  and typeleve in ('市级') ", city, province);
		}
		// 未知的都不可见
		return " 1=2 ";
	}
	

	public String selectRoleStr_UserBasic() {
		if (typeleve.equals(EnumTypeLevel.Country.getName())) {
			// 国家级 全部可见
			return " (levelinstitute >0 or typelevel > '0') ";
		} else if(typeleve.equals(EnumTypeLevel.CenterInstitute.getName())) {
			return String.format(" institute='%s' and levelinstitute >0  ", institute);
		}
		else if (typeleve.equals(EnumTypeLevel.Province.getName())) {
			// 省级 只可见属于该省的
			return String.format(" province='%s' and levelprovince>0 ", province);
		} else if (typeleve.equals(EnumTypeLevel.City.getName())) {
			// 市级 只可见属于该市的
			return String.format(" province='%s' and  city='%s' and levelcity>0 ", province, city);
		}
		// todo 暂未考虑协会管理员
		
		// 未知的都不可见
		return " 1=2 ";
	}

	public String ggProvince() {
		if (typeleve.equals(EnumTypeLevel.Country.getName())) {
			// 国家级 全部可见
			return Def_NullStr;
		}
		return province;
	}

	public String ggCity() {
		if (typeleve.equals(EnumTypeLevel.Country.getName()) || typeleve.equals(EnumTypeLevel.Province.getName())) {
			// 国家级 全部可见
			return Def_NullStr;
		}

		return city;
	}

	/**
	 * @return the usrid
	 */
	public Long getUsrid() {
		return usrid;
	}

	/**
	 * @param usrid
	 *            the usrid to set
	 */
	public void setUsrid(Long usrid) {
		this.usrid = usrid;
	}

	/**
	 * @return the typeleve
	 */
	public String getTypeleve() {
		return typeleve;
	}

	/**
	 * @param typeleve
	 *            the typeleve to set
	 */
	public void setTypeleve(String typeleve) {
		this.typeleve = typeleve;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the institute
	 */
	public String getInstitute() {
		return institute;
	}

	/**
	 * @param institute
	 *            the institute to set
	 */
	public void setInstitute(String institute) {
		this.institute = institute;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "T6MgrSession [usrid=" + usrid + ", typeleve=" + typeleve + ", province=" + province + ", city=" + city
				+ ", institute=" + institute + "]";
	}


}
