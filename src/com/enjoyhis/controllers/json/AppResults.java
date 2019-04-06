package com.enjoyhis.controllers.json;

public class AppResults implements Results {

	private Status status;

	private Object returndata;

	private Integer totalNum;

	private Integer hasMore;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Object getReturndata() {
		return returndata;
	}

	public void setReturndata(Object returndata) {
		this.returndata = returndata;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getHasMore() {
		return hasMore;
	}

	public void setHasMore(Integer hasMore) {
		this.hasMore = hasMore;
	}

}
