package com.enjoyhis.pojo;

import com.enjoyhis.persistence.his.po.HisStatementItemDetail;

public class StatementItemDetail extends HisStatementItemDetail {

	private static final long serialVersionUID = 1L;
	public String itemName;
	public String itemNameFl;
	public String itemNameXm;
	public String docName;

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemNameFl() {
		return itemNameFl;
	}

	public void setItemNameFl(String itemNameFl) {
		this.itemNameFl = itemNameFl;
	}

	public String getItemNameXm() {
		return itemNameXm;
	}

	public void setItemNameXm(String itemNameXm) {
		this.itemNameXm = itemNameXm;
	}

}
