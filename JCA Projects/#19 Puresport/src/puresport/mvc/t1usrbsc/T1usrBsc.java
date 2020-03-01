package puresport.mvc.t1usrbsc;

import com.platform.annotation.Table;
import com.platform.constant.ConstantInit;
import com.platform.mvc.base.BaseModel;

import java.sql.Timestamp; 

import org.apache.log4j.Logger;

/**
 * @description：
 * @author ZW
 */
@SuppressWarnings("unused")
//@Table(tableName = "t1_usr_bsc")
public class T1usrBsc extends BaseModel<T1usrBsc> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T1usrBsc.class);
	
	public static final T1usrBsc dao = new T1usrBsc();
	
	public static final String  tableName = "t1_usr_bsc";
	/**
	 * 字段描述：用户id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_usrid = "usrid";
	
	/**
	 * 字段描述：用户类型 
	 * 字段类型：int  长度：null
	 */
	public static final String column_usr_tp = "usr_tp";
	
	/**
	 * 字段描述：用户名 
	 * 字段类型：varchar  长度：32
	 */
	public static final String column_usr_nm = "usr_nm";
	
	/**
	 * 字段描述：姓名 
	 * 字段类型：varchar  长度：32
	 */
	public static final String column_nm = "nm";
	
	public static final String column_nm_char = "nm_char";
	
	/**
	 * 字段描述：证件类型 
	 * 字段类型：char  长度：2
	 */
	public static final String column_crdt_tp = "crdt_tp";
	
	/**
	 * 字段描述：运动项目 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_spt_prj = "spt_prj";
	
	/**
	 * 字段描述：证件号 
	 * 字段类型：varchar  长度：256
	 */
	public static final String column_crdt_no = "crdt_no";
	
	/**
	 * 字段描述：性别 
	 * 字段类型：char  长度：1
	 */
	public static final String column_gnd = "gnd";
	
	/**
	 * 字段描述：密码 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_pswd = "pswd";
	
	/**
	 * 字段描述：出生日期 
	 * 字段类型：char  长度：8
	 */
	public static final String column_brth_dt = "brth_dt";
	
	/**
	 * 字段描述：行政区划代码 
	 * 字段类型：char  长度：6
	 */
	public static final String column_adiv_cd = "adiv_cd";
	
	/**
	 * 字段描述：协会id 
	 * 字段类型：char  长度：8
	 */
	public static final String column_asscid = "asscid";
	
	/**
	 * 字段描述：手机号 
	 * 字段类型：varchar  长度：256
	 */
	public static final String column_mblph_no = "mblph_no";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	/**
	 * 字段描述：国家省市管理员id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_cty_prov_city_mgrid = "cty_prov_city_mgrid";
	
	/**
	 * 字段描述：备注 
	 * 字段类型：varchar  长度：2048
	 */
	public static final String column_rmrk = "rmrk";
	
	/**
	 * 字段描述：协会管理员用id 
	 * 字段类型：int  长度：null
	 */
	public static final String column_assc_mgrid = "assc_mgrid";
	
	/**
	 * 字段描述：邮箱 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_email = "email";
	
	/**
	 * 字段描述：血型 
	 * 字段类型：char  长度：1
	 */
	public static final String column_bloodtp = "bloodtp";
	
	/**
	 * 字段描述：民族 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_ethnct = "ethnct";
	
	/**
	 * 字段描述：备注 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_remark = "remark";
	
	/**
	 * 字段描述：类型级别 
	 * 字段类型：varchar  长度：8
	 */
	public static final String column_typelevel = "typelevel";
	
	/**
	 * 字段描述：省份名称 
	 * 字段类型：varchar  长度：128
	 */
	public static final String column_province = "province";
	
	/**
	 * 字段描述：城市名称 
	 * 字段类型：varchar  长度：128
	 */
	public static final String column_city = "city";
	
	/**
	 * 字段描述：协会名称 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_institute = "institute";
	
	/**
	 * 字段描述：工作单位 
	 * 字段类型：varchar  长度：256
	 */
	public static final String column_department = "department";
	
	/**
	 * 字段描述：职务 
	 * 字段类型：varchar  长度：128
	 */
	public static final String column_post = "post";

	/**
	 * 字段描述：省级别 0不可见 1可见 
	 * 字段类型：int  长度：null
	 */
	public static final String column_levelprovince = "levelprovince";
	
	/**
	 * 字段描述：市级别0不可见1可见 
	 * 字段类型：int  长度：null
	 */
	public static final String column_levelcity = "levelcity";
	
	/**
	 * 字段描述：协会级别0可见1不可见 
	 * 字段类型：int  长度：null
	 */
	public static final String column_levelinstitute = "levelinstitute";
	
	/**
	 * 字段描述：邮箱是否验证 0未验证 1验证成功 2验证失败 
	 * 字段类型：int  长度：null
	 */
	public static final String column_email_val = "email_val";
	
	/**
	 * 字段描述：手机是否验证 0未验证 1验证成功 2验证失败 
	 * 字段类型：int  长度：null
	 */
	public static final String column_mblph_val = "mblph_val";
	
	/**
	 * sqlId : puresport.t1usrBsc.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t1usrBsc.splitPageFrom";

	private Long usrid;
	protected String usr_tp;
	protected String usr_nm;
	protected String nm;
	protected String nm_char;
	protected String crdt_tp;
	protected String spt_prj;
	protected String crdt_no;
	protected String gnd;
	private String pswd;
	protected String brth_dt;
	protected String adiv_cd;
	protected String asscid;
	protected String mblph_no;
	protected Timestamp tms;
	protected Long cty_prov_city_mgrid;
	protected String rmrk;
	protected Integer assc_mgrid;
	protected String email;
	protected String bloodtp;
	protected String ethnct;
	protected String remark;
	protected String typelevel;
	protected String province;
	protected String city;
	protected String institute;
	protected String department;
	protected String post;
	protected Integer levelprovince;
	protected Integer levelcity;
	protected Integer levelinstitute;
	protected Integer email_val;
	protected Integer mblph_val;

	public void setUsrid(Long usrid){
		set(column_usrid, usrid);
	}
	public <T> T getUsrid() {
		return get(column_usrid);
	}
	public void setUsr_tp(String usr_tp){
		set(column_usr_tp, usr_tp);
	}
	public <T> T getUsr_tp() {
		return get(column_usr_tp);
	}
	public void setUsr_nm(String usr_nm){
		set(column_usr_nm, usr_nm);
	}
	public <T> T getUsr_nm() {
		return get(column_usr_nm);
	}
	public void setNm(String nm){
		set(column_nm, nm);
	}
	public <T> T getNm() {
		return get(column_nm);
	}
	
	public void setNmChar(String nmChar){
		set(column_nm_char, nmChar);
	}
	public <T> T getNmChar() {
		return get(column_nm_char);
	}
	
	public void setCrdt_tp(String crdt_tp){
		set(column_crdt_tp, crdt_tp);
	}
	public <T> T getCrdt_tp() {
		return get(column_crdt_tp);
	}
	public void setSpt_prj(String spt_prj){
		set(column_spt_prj, spt_prj);
	}
	public <T> T getSpt_prj() {
		return get(column_spt_prj);
	}
	public void setCrdt_no(String crdt_no){
		set(column_crdt_no, crdt_no);
	}
	public <T> T getCrdt_no() {
		return get(column_crdt_no);
	}
	public void setGnd(String gnd){
		set(column_gnd, gnd);
	}
	public <T> T getGnd() {
		return get(column_gnd);
	}
	public void setPswd(String pswd){
		set(column_pswd, pswd);
	}
	public <T> T getPswd() {
		return get(column_pswd);
	}
	public void setBrth_dt(String brth_dt){
		set(column_brth_dt, brth_dt);
	}
	public <T> T getBrth_dt() {
		return get(column_brth_dt);
	}
	public void setAdiv_cd(String adiv_cd){
		set(column_adiv_cd, adiv_cd);
	}
	public <T> T getAdiv_cd() {
		return get(column_adiv_cd);
	}
	public void setAsscid(String asscid){
		set(column_asscid, asscid);
	}
	public <T> T getAsscid() {
		return get(column_asscid);
	}
	public void setMblph_no(String mblph_no){
		set(column_mblph_no, mblph_no);
	}
	public <T> T getMblph_no() {
		return get(column_mblph_no);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	public void setCty_prov_city_mgrid(Long cty_prov_city_mgrid){
		set(column_cty_prov_city_mgrid, cty_prov_city_mgrid);
	}
	public <T> T getCty_prov_city_mgrid() {
		return get(column_cty_prov_city_mgrid);
	}
	public void setRmrk(String rmrk){
		set(column_rmrk, rmrk);
	}
	public <T> T getRmrk() {
		return get(column_rmrk);
	}
	public void setAssc_mgrid(Integer assc_mgrid){
		set(column_assc_mgrid, assc_mgrid);
	}
	public <T> T getAssc_mgrid() {
		return get(column_assc_mgrid);
	}
	public void setEmail(String email){
		set(column_email, email);
	}
	public <T> T getEmail() {
		return get(column_email);
	}
	public void setBloodtp(String bloodtp){
		set(column_bloodtp, bloodtp);
	}
	public <T> T getBloodtp() {
		return get(column_bloodtp);
	}
	public void setEthnct(String ethnct){
		set(column_ethnct, ethnct);
	}
	public <T> T getEthnct() {
		return get(column_ethnct);
	}
	public void setRemark(String remark){
		set(column_remark, remark);
	}
	public <T> T getRemark() {
		return get(column_remark);
	}
	public void setTypelevel(String typelevel){
		set(column_typelevel, typelevel);
	}
	public <T> T getTypelevel() {
		return get(column_typelevel);
	}
	public void setProvince(String province){
		set(column_province, province);
	}
	public <T> T getProvince() {
		return get(column_province);
	}
	public void setCity(String city){
		set(column_city, city);
	}
	public <T> T getCity() {
		return get(column_city);
	}
	public void setInstitute(String institute){
		set(column_institute, institute);
	}
	public <T> T getInstitute() {
		return get(column_institute);
	}
	public void setDepartment(String department){
		set(column_department, department);
	}
	public <T> T getDepartment() {
		return get(column_department);
	}
	public void setPost(String post){
		set(column_post, post);
	}
	public <T> T getPost() {
		return get(column_post);
	}
	public void setLevelprovince(Integer levelprovince){
		set(column_levelprovince, levelprovince);
	}
	public <T> T getLevelprovince() {
		return get(column_levelprovince);
	}
	public void setLevelcity(Integer levelcity){
		set(column_levelcity, levelcity);
	}
	public <T> T getLevelcity() {
		return get(column_levelcity);
	}
	public void setLevelinstitute(Integer levelinstitute){
		set(column_levelinstitute, levelinstitute);
	}
	public <T> T getLevelinstitute() {
		return get(column_levelinstitute);
	}
	
	public void setEmailVal(Integer emailVal){
		set(column_email_val, emailVal);
	}
	public <T> T getEmailVal() {
		return getByType(column_email_val);
	}
	
	public void setMblPhVal(Integer mblphVal){
		set(column_mblph_val, mblphVal);
	}
	public Integer getMblPhVal() {
		return getByType(column_mblph_val);
	}
}
