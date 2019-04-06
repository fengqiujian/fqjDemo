package com.enjoyhis.pojo;

import java.math.BigDecimal;

public class ReportSumPaymentTable {
	private String time;
	private BigDecimal zgyh = BigDecimal.ZERO;
	private BigDecimal zsyh = BigDecimal.ZERO;
	private BigDecimal jsyh = BigDecimal.ZERO;
	private BigDecimal gsyh = BigDecimal.ZERO;
	private BigDecimal lkl = BigDecimal.ZERO;
	private BigDecimal msyh = BigDecimal.ZERO;
	private BigDecimal pfyh = BigDecimal.ZERO;
	private BigDecimal tl = BigDecimal.ZERO;
	private BigDecimal bjyh = BigDecimal.ZERO;
	private BigDecimal yhjjk = BigDecimal.ZERO;
	private BigDecimal ylsw = BigDecimal.ZERO;
	private BigDecimal wx = BigDecimal.ZERO;
	private BigDecimal zfb = BigDecimal.ZERO;
	private BigDecimal ykpt = BigDecimal.ZERO;
	private BigDecimal ykyywx = BigDecimal.ZERO;
	private BigDecimal zp = BigDecimal.ZERO;
	private BigDecimal xj = BigDecimal.ZERO;
	private BigDecimal hj = BigDecimal.ZERO;

	
	
	public BigDecimal getYkpt() {
		return ykpt;
	}

	public void setYkpt(BigDecimal ykpt) {
		this.ykpt = ykpt;
		setHj(ykpt);
	}

	public BigDecimal getYkyywx() {
		return ykyywx;
	}

	public void setYkyywx(BigDecimal ykyywx) {
		this.ykyywx = ykyywx;
		setHj(ykyywx);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public BigDecimal getZgyh() {
		return zgyh;
	}

	public void setZgyh(BigDecimal zgyh) {
		this.zgyh = zgyh;
		setHj(zgyh);
	}

	public BigDecimal getZsyh() {
		return zsyh;
	}

	public void setZsyh(BigDecimal zsyh) {
		this.zsyh = zsyh;
		setHj(zsyh);
	}

	public BigDecimal getGsyh() {
		return gsyh;
	}

	public void setGsyh(BigDecimal gsyh) {
		this.gsyh = gsyh;
		setHj(gsyh);
	}

	public BigDecimal getLkl() {
		return lkl;
	}

	public void setLkl(BigDecimal lkl) {
		this.lkl = lkl;
		setHj(lkl);
	}

	public BigDecimal getMsyh() {
		return msyh;
	}

	public void setMsyh(BigDecimal msyh) {
		this.msyh = msyh;
		setHj(msyh);
	}

	public BigDecimal getPfyh() {
		return pfyh;
	}

	public void setPfyh(BigDecimal pfyh) {
		this.pfyh = pfyh;
		setHj(pfyh);
	}

	public BigDecimal getTl() {
		return tl;
	}

	public void setTl(BigDecimal tl) {
		this.tl = tl;
		setHj(tl);
	}

	public BigDecimal getBjyh() {
		return bjyh;
	}

	public void setBjyh(BigDecimal bjyh) {
		this.bjyh = bjyh;
		setHj(bjyh);
	}

	public BigDecimal getYhjjk() {
		return yhjjk;
	}

	public void setYhjjk(BigDecimal yhjjk) {
		this.yhjjk = yhjjk;
		setHj(yhjjk);
	}

	public BigDecimal getYlsw() {
		return ylsw;
	}

	public void setYlsw(BigDecimal ylsw) {
		this.ylsw = ylsw;
		setHj(ylsw);
	}

	public BigDecimal getWx() {
		return wx;
	}

	public void setWx(BigDecimal wx) {
		this.wx = wx;
		setHj(wx);
	}

	public BigDecimal getZfb() {
		return zfb;
	}

	public void setZfb(BigDecimal zfb) {
		this.zfb = zfb;
		setHj(zfb);
	}

	public BigDecimal getZp() {
		return zp;
	}

	public void setZp(BigDecimal zp) {
		this.zp = zp;
		setHj(zp);
	}

	public BigDecimal getXj() {
		return xj;
	}

	public void setXj(BigDecimal xj) {
		this.xj = xj;
		setHj(xj);
	}

	public BigDecimal getHj() {
		return hj;
	}

	private void setHj(BigDecimal a) {
		hj = hj.add(a);
	}

	public BigDecimal getJsyh() {
		return jsyh;
	}

	public void setJsyh(BigDecimal jsyh) {
		this.jsyh = jsyh;
		setHj(jsyh);
	}

}