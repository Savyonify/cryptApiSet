package com.winway.android.edcollection.adding.viewholder;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.winway.android.edcollection.R;
import com.winway.android.edcollection.base.BaseViewHolder;
import com.winway.android.edcollection.base.widgets.HeadComponent;
import com.winway.android.ewidgets.input.InputComponent;
import com.winway.android.ewidgets.input.InputSelectComponent;
import com.winway.android.ewidgets.attachment.AttachmentView;

public class HwgViewHolder extends BaseViewHolder {
	@ViewInject(R.id.hc_hwg_head)
	private HeadComponent hcHead;// head组件
	@ViewInject(R.id.inCon_hwg_sbid)
	private InputComponent inconSbid;// 设备标识
	@ViewInject(R.id.inCon_hwg_sbmc)
	private InputComponent inconSbmc;// 设备名称
	@ViewInject(R.id.inCon_hwg_yxbh)
	private InputComponent inconYxbh;// 运行编号
	@ViewInject(R.id.inCon_hwg_dxmpid)
	private InputComponent inconDxmpid;// 电系铭牌ID
	@ViewInject(R.id.insc_hwg_dldj)
	private InputSelectComponent inscDldj;// 电压等级
	@ViewInject(R.id.inCon_hwg_ssds)
	private InputComponent inconSsds;// 所属地市
	@ViewInject(R.id.insc_hwg_ywdw)
	private InputSelectComponent inscYwdw;// 运维单位
	@ViewInject(R.id.insc_hwg_whbz)
	private InputSelectComponent inscWhbz;// 维护班组
	@ViewInject(R.id.insc_hwg_sbzt)
	private InputSelectComponent inscSbzt;// 设备状态
	@ViewInject(R.id.insc_hwg_sfdw)
	private InputSelectComponent inscSfdw;// 是否代维
	@ViewInject(R.id.insc_hwg_sfnw)
	private InputSelectComponent inscSfnw;// 是否农网
	@ViewInject(R.id.inCon_hwg_xh)
	private InputComponent inconXh;// 型号
	@ViewInject(R.id.inCon_hwg_sccj)
	private InputComponent inconSccj;// 生产厂家
	@ViewInject(R.id.inCon_hwg_ccbh)
	private InputComponent inconCcbh;// 出厂编号
	@ViewInject(R.id.inCon_hwg_ccrq)
	private InputComponent inconCcrq;// 出厂日期
	@ViewInject(R.id.inCon_hwg_tyrq)
	private InputComponent inconTyrq;// 投运日期
	@ViewInject(R.id.inCon_hwg_jddz)
	private InputComponent inconJddz;// 接地电阻(ω)
	@ViewInject(R.id.inCon_hwg_byjcxjgs)
	private InputComponent inconByjcxjgs;// 备用进出线间隔数
	@ViewInject(R.id.inCon_hwg_zz)
	private InputComponent inconZz;// 站址
	@ViewInject(R.id.insc_hwg_dqtz)
	private InputSelectComponent inscDqtz;// 地区特征
	@ViewInject(R.id.insc_hwg_zycd)
	private InputSelectComponent inscZycd;// 重要程度
	@ViewInject(R.id.insc_hwg_zcxz)
	private InputSelectComponent inscZcxz;// 资产性质
	@ViewInject(R.id.inCon_hwg_zcdw)
	private InputComponent inconZcdw;// 资产单位
	@ViewInject(R.id.inCon_hwg_gcbh)
	private InputComponent inconGcbh;// 工程编号
	@ViewInject(R.id.inCon_hwg_gcmc)
	private InputComponent inconGcmc;// 工程名称
	@ViewInject(R.id.insc_hwg_sbzjfs)
	private InputSelectComponent inscSbzjfs;// 设备增加方式
	@ViewInject(R.id.inCon_hwg_bz)
	private InputComponent inconBz;// 备注
	@ViewInject(R.id.av_hwg_attachment)
	private AttachmentView avAttachment;// 附件

	@ViewInject(R.id.iv_link_device_enter)
	private ImageView ivLinkDeviceEnter;// 关联

	@ViewInject(R.id.btn_link_device_link)
	private Button btnLinkDeviceLink;// 关联

	@ViewInject(R.id.rl_link_device)
	private RelativeLayout rlLinkDevice;// 关联布局

	public InputComponent getInconDxmpid() {
		return inconDxmpid;
	}

	public void setInconDxmpid(InputComponent inconDxmpid) {
		this.inconDxmpid = inconDxmpid;
	}

	public RelativeLayout getRlLinkDevice() {
		return rlLinkDevice;
	}

	public void setRlLinkDevice(RelativeLayout rlLinkDevice) {
		this.rlLinkDevice = rlLinkDevice;
	}

	public ImageView getIvLinkDeviceEnter() {
		return ivLinkDeviceEnter;
	}

	public void setIvLinkDeviceEnter(ImageView ivLinkDeviceEnter) {
		this.ivLinkDeviceEnter = ivLinkDeviceEnter;
	}

	public Button getBtnLinkDeviceLink() {
		return btnLinkDeviceLink;
	}

	public void setBtnLinkDeviceLink(Button btnLinkDeviceLink) {
		this.btnLinkDeviceLink = btnLinkDeviceLink;
	}

	public HeadComponent getHcHead() {
		return hcHead;
	}

	public void setHcHead(HeadComponent hcHead) {
		this.hcHead = hcHead;
	}

	public InputComponent getInconSbid() {
		return inconSbid;
	}

	public void setInconSbid(InputComponent inconSbid) {
		this.inconSbid = inconSbid;
	}

	public InputComponent getInconSbmc() {
		return inconSbmc;
	}

	public void setInconSbmc(InputComponent inconSbmc) {
		this.inconSbmc = inconSbmc;
	}

	public InputComponent getInconYxbh() {
		return inconYxbh;
	}

	public void setInconYxbh(InputComponent inconYxbh) {
		this.inconYxbh = inconYxbh;
	}

	public InputSelectComponent getInscDldj() {
		return inscDldj;
	}

	public void setInscDldj(InputSelectComponent inscDldj) {
		this.inscDldj = inscDldj;
	}

	public InputComponent getInconSsds() {
		return inconSsds;
	}

	public void setInconSsds(InputComponent inconSsds) {
		this.inconSsds = inconSsds;
	}

	public InputSelectComponent getInscYwdw() {
		return inscYwdw;
	}

	public void setInscYwdw(InputSelectComponent inscYwdw) {
		this.inscYwdw = inscYwdw;
	}

	public InputSelectComponent getInscWhbz() {
		return inscWhbz;
	}

	public void setInscWhbz(InputSelectComponent inscWhbz) {
		this.inscWhbz = inscWhbz;
	}

	public InputSelectComponent getInscSbzt() {
		return inscSbzt;
	}

	public void setInscSbzt(InputSelectComponent inscSbzt) {
		this.inscSbzt = inscSbzt;
	}

	public InputSelectComponent getInscSfdw() {
		return inscSfdw;
	}

	public void setInscSfdw(InputSelectComponent inscSfdw) {
		this.inscSfdw = inscSfdw;
	}

	public InputSelectComponent getInscSfnw() {
		return inscSfnw;
	}

	public void setInscSfnw(InputSelectComponent inscSfnw) {
		this.inscSfnw = inscSfnw;
	}

	public InputComponent getInconXh() {
		return inconXh;
	}

	public void setInconXh(InputComponent inconXh) {
		this.inconXh = inconXh;
	}

	public InputComponent getInconSccj() {
		return inconSccj;
	}

	public void setInconSccj(InputComponent inconSccj) {
		this.inconSccj = inconSccj;
	}

	public InputComponent getInconCcbh() {
		return inconCcbh;
	}

	public void setInconCcbh(InputComponent inconCcbh) {
		this.inconCcbh = inconCcbh;
	}

	public InputComponent getInconCcrq() {
		return inconCcrq;
	}

	public void setInconCcrq(InputComponent inconCcrq) {
		this.inconCcrq = inconCcrq;
	}

	public InputComponent getInconTyrq() {
		return inconTyrq;
	}

	public void setInconTyrq(InputComponent inconTyrq) {
		this.inconTyrq = inconTyrq;
	}

	public InputComponent getInconJddz() {
		return inconJddz;
	}

	public void setInconJddz(InputComponent inconJddz) {
		this.inconJddz = inconJddz;
	}

	public InputComponent getInconByjcxjgs() {
		return inconByjcxjgs;
	}

	public void setInconByjcxjgs(InputComponent inconByjcxjgs) {
		this.inconByjcxjgs = inconByjcxjgs;
	}

	public InputComponent getInconZz() {
		return inconZz;
	}

	public void setInconZz(InputComponent inconZz) {
		this.inconZz = inconZz;
	}

	public InputSelectComponent getInscDqtz() {
		return inscDqtz;
	}

	public void setInscDqtz(InputSelectComponent inscDqtz) {
		this.inscDqtz = inscDqtz;
	}

	public InputSelectComponent getInscZycd() {
		return inscZycd;
	}

	public void setInscZycd(InputSelectComponent inscZycd) {
		this.inscZycd = inscZycd;
	}

	public InputSelectComponent getInscZcxz() {
		return inscZcxz;
	}

	public void setInscZcxz(InputSelectComponent inscZcxz) {
		this.inscZcxz = inscZcxz;
	}

	public InputComponent getInconZcdw() {
		return inconZcdw;
	}

	public void setInconZcdw(InputComponent inconZcdw) {
		this.inconZcdw = inconZcdw;
	}

	public InputComponent getInconGcbh() {
		return inconGcbh;
	}

	public void setInconGcbh(InputComponent inconGcbh) {
		this.inconGcbh = inconGcbh;
	}

	public InputComponent getInconGcmc() {
		return inconGcmc;
	}

	public void setInconGcmc(InputComponent inconGcmc) {
		this.inconGcmc = inconGcmc;
	}

	public InputSelectComponent getInscSbzjfs() {
		return inscSbzjfs;
	}

	public void setInscSbzjfs(InputSelectComponent inscSbzjfs) {
		this.inscSbzjfs = inscSbzjfs;
	}

	public InputComponent getInconBz() {
		return inconBz;
	}

	public void setInconBz(InputComponent inconBz) {
		this.inconBz = inconBz;
	}

	public AttachmentView getAvAttachment() {
		return avAttachment;
	}

	public void setAvAttachment(AttachmentView avAttachment) {
		this.avAttachment = avAttachment;
	}

}
