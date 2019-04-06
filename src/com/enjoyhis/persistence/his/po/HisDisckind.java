package com.enjoyhis.persistence.his.po;
import java.math.BigDecimal;
import java.util.Date;

import com.enjoyhis.persistence.base.DBRecord;

public class HisDisckind extends DBRecord  {

	private static final long serialVersionUID = 1L;

	private String id;

	private String kindname;

	private Integer needint;

	private Integer checkpat;

	private Date expiration;

	private String hospnolist;

	private Integer isdele;

	private Date startdate;

	private Integer limitpeop;

	private BigDecimal maxdmoney;

	private Integer hospNo;

	private Date duptdate;

	private BigDecimal sellmny;

	private BigDecimal addmny;

	private Integer exdnum;

	private Integer quantity;

	private String paymentCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKindname() {
		return kindname;
	}

	public void setKindname(String kindname) {
		this.kindname = kindname;
	}

	public Integer getNeedint() {
		return needint;
	}

	public void setNeedint(Integer needint) {
		this.needint = needint;
	}

	public Integer getCheckpat() {
		return checkpat;
	}

	public void setCheckpat(Integer checkpat) {
		this.checkpat = checkpat;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getHospnolist() {
		return hospnolist;
	}

	public void setHospnolist(String hospnolist) {
		this.hospnolist = hospnolist;
	}

	public Integer getIsdele() {
		return isdele;
	}

	public void setIsdele(Integer isdele) {
		this.isdele = isdele;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Integer getLimitpeop() {
		return limitpeop;
	}

	public void setLimitpeop(Integer limitpeop) {
		this.limitpeop = limitpeop;
	}

	public BigDecimal getMaxdmoney() {
		return maxdmoney;
	}

	public void setMaxdmoney(BigDecimal maxdmoney) {
		this.maxdmoney = maxdmoney;
	}

	public Integer getHospNo() {
		return hospNo;
	}

	public void setHospNo(Integer hospNo) {
		this.hospNo = hospNo;
	}

	public Date getDuptdate() {
		return duptdate;
	}

	public void setDuptdate(Date duptdate) {
		this.duptdate = duptdate;
	}

	public BigDecimal getSellmny() {
		return sellmny;
	}

	public void setSellmny(BigDecimal sellmny) {
		this.sellmny = sellmny;
	}

	public BigDecimal getAddmny() {
		return addmny;
	}

	public void setAddmny(BigDecimal addmny) {
		this.addmny = addmny;
	}

	public Integer getExdnum() {
		return exdnum;
	}

	public void setExdnum(Integer exdnum) {
		this.exdnum = exdnum;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	
	
}