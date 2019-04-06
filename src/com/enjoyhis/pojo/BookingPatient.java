package com.enjoyhis.pojo;

public class BookingPatient {
	private String id;
	private int num;
	private boolean color;
	private String column;
	private int group; // 同一个预约的人，group相同
	private Long docId;

	private int x;
	private int y;
	private String serviceItems;
	private boolean first;// 分组后是否是第一个（只有第一个才输入文字，其他不用输入）
	private String tdcolor;// td颜色class
	private int sortNum;// 分组后第几个

	private int beginNum;// 开始时间编号
	private int endNum;// 结束时间编号
	private String begin;// 开始时间（eg: 07:00）

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getServiceItems() {
		return serviceItems;
	}

	public void setServiceItems(String serviceItems) {
		this.serviceItems = serviceItems;
	}

	public String getTdcolor() {
		return tdcolor;
	}

	public void setTdcolor(String tdcolor) {
		this.tdcolor = tdcolor;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public int getBeginNum() {
		return beginNum;
	}

	public void setBeginNum(int beginNum) {
		this.beginNum = beginNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

}
