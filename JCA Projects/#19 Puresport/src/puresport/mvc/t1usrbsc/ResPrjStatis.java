/**
 * PureSport
 * create by zw at 2018年4月21日
 * version: v1.0
 */
package puresport.mvc.t1usrbsc;

import java.io.Serializable;

/**
 * @author zw
 *
 */
public class ResPrjStatis implements Serializable{
	
	private String spt_prj;
	private String province;
	private String city;
	private String institute;

	private String answered;// 答题率
	private String passed;  // 通过率
	
	public ResPrjStatis(){}

	/**
	 * @return the spt_prj
	 */
	public String getSpt_prj() {
		return spt_prj;
	}

	/**
	 * @param spt_prj the spt_prj to set
	 */
	public void setSpt_prj(String spt_prj) {
		this.spt_prj = spt_prj;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
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
	 * @param city the city to set
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
	 * @param institute the institute to set
	 */
	public void setInstitute(String institute) {
		this.institute = institute;
	}

	/**
	 * @return the passed
	 */
	public String getPassed() {
		return passed;
	}

	/**
	 * @param passed the passed to set
	 */
	public void setPassed(String passed) {
		this.passed = passed;
	}

	/**
	 * @return the answered
	 */
	public String getAnswered() {
		return answered;
	}

	/**
	 * @param answered the answered to set
	 */
	public void setAnswered(String answered) {
		this.answered = answered;
	}
	
}
