/**
 * PureSport
 * create by zw at 2018年5月1日
 * version: v1.0
 */
package puresport.mvc.comm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import puresport.constant.EnumStatus;

/**
 * @author zw
 *
 */
public class ResTips implements Serializable{
	
	public final static ResTips SUCCESS = createSuccRes();
	public final static ResTips FAIL = createFailRes();
	
	private boolean hasSuc;
	private TipMdl status = new TipMdl();
	private List<TipMdl> fieldErrors;
	private List<String> tipStrings;
	private Object data;
	
	public ResTips(){}
	
	public ResTips(EnumStatus astatus){
		hasSuc = astatus == EnumStatus.Success?true:false;
		status.setName(astatus.getName());
		status.setStatus(astatus.getIdStr());
	}
	
	public ResTips(EnumStatus astatus, List<String> tips){
		hasSuc = astatus == EnumStatus.Success?true:false;
		tipStrings = tips;
	}
	 
	public ResTips addErroFiled(String name, String status){
		if (CollectionUtils.isEmpty(fieldErrors)) {
			fieldErrors = new LinkedList<ResTips.TipMdl>();
		}
		fieldErrors.add(new TipMdl(name, status));
		return this;
	}
	
	public static ResTips createSuccRes() {
		return new ResTips(EnumStatus.Success);
	}
	
	public static ResTips createSuccRes(Object data) {
		ResTips res = new ResTips(EnumStatus.Success);
		res.setData(data);
		return res;
	}
	
	public static ResTips createFailRes() {
		return new ResTips(EnumStatus.Failed);
	}
	
	public static ResTips createFailRes(String ... tips) {
		ResTips tip = new ResTips(EnumStatus.Failed);
		if (Objects.nonNull(tips) && tips.length > 0) {
			tip.tipStrings = new LinkedList<>();
			for (int i = 0; i < tips.length; i++) {
				tip.tipStrings.add(tips[i]);
			}
		}
		
		return tip;
	}
	
	public static ResTips createIllegalRes() {
		return new ResTips(EnumStatus.Illegal);
	}
	
	public static ResTips createFailRes(List<String> tips) {
		return new ResTips(EnumStatus.Failed, tips);
	}
	
	public static ResTips createSimpleTips(String status, String tips) {
		return new ResTips().setStatus(new TipMdl(status, tips));
	}

	public boolean isHasSuc() {
		return hasSuc;
	}

	public void setHasSuc(boolean hasSuc) {
		this.hasSuc = hasSuc;
	}

	/**
	 * @return the status
	 */
	public TipMdl getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public ResTips setStatus(TipMdl status) {
		this.status = status;
		return this;
	}

	/**
	 * @return the fieldErrors
	 */
	public List<TipMdl> getFieldErrors() {
		return fieldErrors;
	}

	/**
	 * @param fieldErrors the fieldErrors to set
	 */
	public void setFieldErrors(List<TipMdl> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public List<String> getTipStrings() {
		return tipStrings;
	}

	public void setTipStrings(List<String> tipStrings) {
		this.tipStrings = tipStrings;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static class TipMdl implements Serializable{
		private String name;
		private String status;
		
		public TipMdl(){}
		
		public TipMdl(String name, String status){
			this.name = name;
			this.status = status;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}
	}
}
