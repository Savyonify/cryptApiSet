package puresport.entity;

import java.sql.Timestamp;

import puresport.mvc.t9tstlib.T9Tstlib;

public class ExamEntity {
	private Integer prblmid;
	private String prblm_ppl;
	private String prblm_tp;
	private String opt;
	private String ttl;
	private String prblm_aswr;
	private Integer scor;
	private Timestamp tms;
	// 答题开始时间
	private Timestamp exam_st_tm;
	// 答题结束时间
	private Timestamp exam_end_tm;
	// 题号
	private Integer prblmno;
	
	// 选项
	private String optA;
	private String optB;
	private String optC;
	private String optD;
	private String optE;
	private String optF;
	private String optG;
	private String optH;
	
	// 答题选项
	private String optASelct;
	private String optBSelct;
	private String optCSelct;
	private String optDSelct;
	private String optESelct;
	private String optFSelct;
	private String optGSelct;
	private String optHSelct;
	
	public String getOptASelct() {
		return optASelct;
	}
	public void setOptASelct(String optASelct) {
		this.optASelct = optASelct;
	}
	public String getOptBSelct() {
		return optBSelct;
	}
	public void setOptBSelct(String optBSelct) {
		this.optBSelct = optBSelct;
	}
	public String getOptCSelct() {
		return optCSelct;
	}
	public void setOptCSelct(String optCSelct) {
		this.optCSelct = optCSelct;
	}
	public String getOptDSelct() {
		return optDSelct;
	}
	public void setOptDSelct(String optDSelct) {
		this.optDSelct = optDSelct;
	}
	public String getOptESelct() {
		return optESelct;
	}
	public void setOptESelct(String optESelct) {
		this.optESelct = optESelct;
	}
	public String getOptFSelct() {
		return optFSelct;
	}
	public void setOptFSelct(String optFSelct) {
		this.optFSelct = optFSelct;
	}
	public String getOptGSelct() {
		return optGSelct;
	}
	public void setOptGSelct(String optGSelct) {
		this.optGSelct = optGSelct;
	}
	public String getOptHSelct() {
		return optHSelct;
	}
	public void setOptHSelct(String optHSelct) {
		this.optHSelct = optHSelct;
	}
	// 答题结果对错描述
	private String rltDesc;	
	private String usr_aswr;
	private Integer usrid;
	private Integer examid;
	private Integer exam_grd;

	public String getRltDesc() {
		return rltDesc;
	}
	public void setRltDesc(String rltDesc) {
		this.rltDesc = rltDesc;
	}
	public String getUsr_aswr() {
		return usr_aswr;
	}
	public void setUsr_aswr(String usr_aswr) {
		this.usr_aswr = usr_aswr;
	}
	public Integer getUsrid() {
		return usrid;
	}
	public void setUsrid(Integer usrid) {
		this.usrid = usrid;
	}
	public Integer getExamid() {
		return examid;
	}
	public void setExamid(Integer examid) {
		this.examid = examid;
	}
	public Integer getExam_grd() {
		return exam_grd;
	}
	public void setExam_grd(Integer exam_grd) {
		this.exam_grd = exam_grd;
	}
		public Timestamp getExam_st_tm() {
		return exam_st_tm;
	}
	public void setExam_st_tm(Timestamp exam_st_tm) {
		this.exam_st_tm = exam_st_tm;
	}
	public Timestamp getExam_end_tm() {
		return exam_end_tm;
	}
	public void setExam_end_tm(Timestamp exam_end_tm) {
		this.exam_end_tm = exam_end_tm;
	}
	public Integer getPrblmno() {
		return prblmno;
	}
	public void setPrblmno(Integer prblmno) {
		this.prblmno = prblmno;
	}
	public Integer getPrblmid() {
		return prblmid;
	}
	public void setPrblmid(Integer prblmid) {
		this.prblmid = prblmid;
	}
	public String getPrblm_ppl() {
		return prblm_ppl;
	}
	public void setPrblm_ppl(String prblm_ppl) {
		this.prblm_ppl = prblm_ppl;
	}
	public String getPrblm_tp() {
		return prblm_tp;
	}
	public void setPrblm_tp(String prblm_tp) {
		this.prblm_tp = prblm_tp;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getTtl() {
		return ttl;
	}
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
	public String getPrblm_aswr() {
		return prblm_aswr;
	}
	public void setPrblm_aswr(String prblm_aswr) {
		this.prblm_aswr = prblm_aswr;
	}
	public Integer getScor() {
		return scor;
	}
	public void setScor(Integer scor) {
		this.scor = scor;
	}
	public Timestamp getTms() {
		return tms;
	}
	public void setTms(Timestamp tms) {
		this.tms = tms;
	}
	public String getOptA() {
		return optA;
	}
	public void setOptA(String optA) {
		this.optA = optA;
	}
	public String getOptB() {
		return optB;
	}
	public void setOptB(String optB) {
		this.optB = optB;
	}
	public String getOptC() {
		return optC;
	}
	public void setOptC(String optC) {
		this.optC = optC;
	}
	public String getOptD() {
		return optD;
	}
	public void setOptD(String optD) {
		this.optD = optD;
	}
	public String getOptE() {
		return optE;
	}
	public void setOptE(String optE) {
		this.optE = optE;
	}
	public String getOptF() {
		return optF;
	}
	public void setOptF(String optF) {
		this.optF = optF;
	}
	public String getOptG() {
		return optG;
	}
	public void setOptG(String optG) {
		this.optG = optG;
	}
	public String getOptH() {
		return optH;
	}
	public void setOptH(String optH) {
		this.optH = optH;
	}	
}
