package com.enjoyhis.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoadICCard implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean idLoad;
	private BigDecimal cardMoney;

	public boolean isIdLoad() {
		return idLoad;
	}

	public void setIdLoad(boolean idLoad) {
		this.idLoad = idLoad;
	}

	public BigDecimal getCardMoney() {
		return cardMoney;
	}

	public void setCardMoney(BigDecimal cardMoney) {
		this.cardMoney = cardMoney;
	}

}
