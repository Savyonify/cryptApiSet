/**
 * PureSport
 * create by zw at 2018年4月28日
 * version: v1.0
 */
package puresport.mvc.comm;

import java.io.Serializable;

import csuduc.platform.util.lyf.EmailUtils;

/**
 * @author zw
 *
 */
public class AuthCodeMdl implements Serializable{
	public static final String keyPhoneCode = "keyPhoneCode";
	public static final String keyEmailCode = "keyEmailCode";
	
	private String addr;
	private String code;
	private long timeBeg;
	
	public AuthCodeMdl(){}

	public static AuthCodeMdl createOne(String addr) {
		
		AuthCodeMdl mdl = new AuthCodeMdl();
		mdl.addr = addr;
		mdl.code = EmailUtils.getRadCode();
		System.out.println("code:"+mdl.code);
		mdl.timeBeg = System.currentTimeMillis();
		
		return mdl;
	}
	
	public boolean checkAuthCodeSuc(String userAddr, String userCode, int seconds) {
	
		if (hasTimeOut(seconds)) {
			System.out.println("hasTimeOut");
			return false;
		}
		if (!addr.equals(userAddr)) {
			System.out.println("!addr.equals");
			return false;
		}
		if (!this.code.equals(userCode)) {
			System.out.println("!code.equals");
			return false;
		}
		return true;
//		return (!hasTimeOut(seconds)) && (addr.equals(userAddr)) && (this.code.equals(userCode));
	}
	
	public boolean checkAuthCodeFail(String userAddr, String userCode, int seconds) {
		return !checkAuthCodeSuc(userAddr, userCode, seconds);
	}
	
	public boolean hasTimeOut(int seconds) {
		
		long cur = System.currentTimeMillis();
		long milliLen = seconds*1000 ;
		
		return (cur - timeBeg) > milliLen;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getTimeBeg() {
		return timeBeg;
	}

	public void setTimeBeg(Long timeBeg) {
		this.timeBeg = timeBeg;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setTimeBeg(long timeBeg) {
		this.timeBeg = timeBeg;
	}
}
