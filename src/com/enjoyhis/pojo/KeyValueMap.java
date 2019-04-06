package com.enjoyhis.pojo;

/*
 * 键-值映射，为下拉服务
 */
public class KeyValueMap extends BaseEntity {

	private String id;// 主键
	private String name;// 值

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public KeyValueMap(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public KeyValueMap() {
	}
}
