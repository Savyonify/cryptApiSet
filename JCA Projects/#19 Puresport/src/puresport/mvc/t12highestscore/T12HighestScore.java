package puresport.mvc.t12highestscore;

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
//@Table(tableName = "t12_highest_score")
public class T12HighestScore extends BaseModel<T12HighestScore> {

	private static final long serialVersionUID = 6761767368352810428L;

	private static Logger log = Logger.getLogger(T12HighestScore.class);
	
	public static final T12HighestScore dao = new T12HighestScore();
	
	public static final String  tableName = "t12_highest_score";
	
	/**
	 * 字段描述：id 
	 * 字段类型：bigint  长度：null
	 */
	public static final String column_id = "id";
	
	/**
	 * 字段描述：用户id 
	 * 字段类型：int  长度：null
	 */
	public static final String column_usrid = "usrid";
	
	/**
	 * 字段描述：考试id 
	 * 字段类型：int  长度：null
	 */
	public static final String column_examid = "examid";
	
	/**
	 * 字段描述：考试成绩 
	 * 字段类型：int  长度：null
	 */
	public static final String column_exam_grd = "exam_grd";
	
	/**
	 * 字段描述：考试状态 
	 * 字段类型：char  长度：null
	 */
	public static final String column_exam_st = "exam_st";
	
	/**
	 * 字段描述：考试渠道 
	 * 字段类型：char  长度：null
	 */
	public static final String column_exam_channel = "exam_channel";
	
	/**
	 * 字段描述：考试次数 
	 * 字段类型：int  长度：null
	 */
	public static final String column_exam_num = "exam_num";
	
	/**
	 * 字段描述：维护时间 
	 * 字段类型：timestamp  长度：null
	 */
	public static final String column_tms = "tms";
	
	/**
	 * 字段描述：考试名称 
	 * 字段类型：varchar  长度：null
	 */
	public static final String column_exam_nm = "exam_nm";
	
	/**
	 * 字段描述：赛事类型
	 * 字段类型：String 
	 * "":通用赛事; "05"东京奥运会
	 */
	public static final String column_type = "type";
		
	/**
	 * sqlId : puresport.t12HighestScore.splitPageFrom
	 * 描述：分页from
	 */
	public static final String sqlId_splitPage_from = "puresport.t12HighestScore.splitPageFrom";
	
	/**
	 * 字段描述：省份名称 
	 * 字段类型：varchar  长度：128
	 */
	public static final String column_file_path = "file_path";
	
	/**
	 * 字段描述：姓名 
	 * 字段类型：varchar  长度：32
	 */
	public static final String column_nm = "nm";
		
	/**
	 * 字段描述：运动项目 
	 * 字段类型：varchar  长度：512
	 */
	public static final String column_spt_prj = "spt_prj";
	
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
	 * 字段描述：科目
	 * 字段类型：String 
	 * "01":科目1;"02":科目2;"03":科目3;"04":科目4;"05":科目5;"06":科目6;
	 */
	public static final String column_category = "category";

	private Long id;
	private Integer usrid;
	private Integer examid;
	private Integer exam_grd;
	private String exam_st;
	private String exam_channel;
	private Integer exam_num;
	private Timestamp tms;
	private String exam_nm;
	private String type;
	// 是否已经有证书
	private String file_path;
	
	//zhuchaobin
	protected String nm;
	protected String spt_prj;
	protected String province;
	protected String city;
	protected String rankImg;
	protected String rank;

	
	// zhuchaobin, 20191025, 二期
	private String category;

	
	// zhuchaobin
		public void setNm(String nm){
			set(column_nm, nm);
		}
		public <T> T getNm() {
			return get(column_nm);
		}
		public void setSpt_prj(String spt_prj){
			set(column_spt_prj, spt_prj);
		}
		public <T> T getSpt_prj() {
			return get(column_spt_prj);
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


	public String getRankImg() {
		return rankImg;
	}
	public void setRankImg(String rankImg) {
		this.rankImg = rankImg;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setFile_path(String file_path){
		set(column_file_path, file_path);
	}
	public <T> T getFile_path() {
		return get(column_file_path);
	}

	public <T> T  getType() {
		return get(column_type);
	}
	public void setType(String type) {
		set(column_type, type);
	}

	public void setId(Long id){
		set(column_id, id);
	}
	public <T> T getId() {
		return get(column_id);
	}
	public void setUsrid(Integer usrid){
		set(column_usrid, usrid);
	}
	public <T> T getUsrid() {
		return get(column_usrid);
	}
	public void setExamid(Integer examid){
		set(column_examid, examid);
	}
	public <T> T getExamid() {
		return get(column_examid);
	}
	public void setExam_grd(Integer exam_grd){
		set(column_exam_grd, exam_grd);
	}
	public <T> T getExam_grd() {
		return get(column_exam_grd);
	}
	public void setExam_st(String exam_st){
		set(column_exam_st, exam_st);
	}
	public <T> T getExam_st() {
		return get(column_exam_st);
	}
	public void setExam_channel(String exam_channel){
		set(column_exam_channel, exam_channel);
	}
	public <T> T getExam_channel() {
		return get(column_exam_channel);
	}
	public void setExam_num(Integer exam_num){
		set(column_exam_num, exam_num);
	}
	public <T> T getExam_num() {
		return get(column_exam_num);
	}
	public void setTms(Timestamp tms){
		set(column_tms, tms);
	}
	public <T> T getTms() {
		return get(column_tms);
	}
	public void setExam_nm(String exam_nm){
		set(column_exam_nm, exam_nm);
	}
	public <T> T getExam_nm() {
		return get(column_exam_nm);
	}
	
}
