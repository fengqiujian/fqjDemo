package com.enjoyhis.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 收欠款列表
 * 
 * @author fqj
 */

public class Debts {

	private BigDecimal amount;
	private List<HisStatementPojo> list;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<HisStatementPojo> getList() {
		return list;
	}

	public void setList(List<HisStatementPojo> list) {
		this.list = list;
	}

}
