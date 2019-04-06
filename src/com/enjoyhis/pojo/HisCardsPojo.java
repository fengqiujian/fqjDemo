package com.enjoyhis.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HisCardsPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String disccardno;

	private String kindkey;

	private Date expiration;

	private Date releasedt;

	private String hisCardscol;

	private Long operator;

	private BigDecimal price;

	private BigDecimal maxdmoney;

	private Integer needmaxdm;

	private Date duptdate;

	private String operator2;

	private Date selldt;

	private String sellperson;

	private Integer pricecard;

	private Integer cardType;

	private Integer unitCode;

	private String unitName;

	private BigDecimal cardMoney;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisccardno() {
		return disccardno;
	}

	public void setDisccardno(String disccardno) {
		this.disccardno = disccardno;
	}

	public String getKindkey() {
		return kindkey;
	}

	public void setKindkey(String kindkey) {
		this.kindkey = kindkey;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Date getReleasedt() {
		return releasedt;
	}

	public void setReleasedt(Date releasedt) {
		this.releasedt = releasedt;
	}

	public String getHisCardscol() {
		return hisCardscol;
	}

	public void setHisCardscol(String hisCardscol) {
		this.hisCardscol = hisCardscol;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMaxdmoney() {
		return maxdmoney;
	}

	public void setMaxdmoney(BigDecimal maxdmoney) {
		this.maxdmoney = maxdmoney;
	}

	public Integer getNeedmaxdm() {
		return needmaxdm;
	}

	public void setNeedmaxdm(Integer needmaxdm) {
		this.needmaxdm = needmaxdm;
	}

	public Date getDuptdate() {
		return duptdate;
	}

	public void setDuptdate(Date duptdate) {
		this.duptdate = duptdate;
	}

	public String getOperator2() {
		return operator2;
	}

	public void setOperator2(String operator2) {
		this.operator2 = operator2;
	}

	public Date getSelldt() {
		return selldt;
	}

	public void setSelldt(Date selldt) {
		this.selldt = selldt;
	}

	public String getSellperson() {
		return sellperson;
	}

	public void setSellperson(String sellperson) {
		this.sellperson = sellperson;
	}

	public Integer getPricecard() {
		return pricecard;
	}

	public void setPricecard(Integer pricecard) {
		this.pricecard = pricecard;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public Integer getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(Integer unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public BigDecimal getCardMoney() {
		return cardMoney;
	}

	public void setCardMoney(BigDecimal cardMoney) {
		this.cardMoney = cardMoney;
	}

}