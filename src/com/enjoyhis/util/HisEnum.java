package com.enjoyhis.util;

public enum HisEnum {

	// 利用构造函数传参(1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号)
	BOOKING(1), REGISTER(2), UNPAY(3), COMPLETED(4), NON_ARRIVAL(5), CANCEL_BOOKING(6), EXIT(7);

	// 定义私有变量
	public int key;
	public String value;

	// 构造函数，枚举类型只能为私有
	private HisEnum(int key) {
		this.key = key;
	}

	public static HisEnum get(int key) {
		HisEnum[] values = HisEnum.values();
		for (HisEnum object : values) {
			if (object.key == key) {
				return object;
			}
		}
		return null;
	}

	/**
	 * 业务ID转名称
	 * 
	 * @param key
	 */
	public static String getName(int key) {
		HisEnum[] values = HisEnum.values();
		for (HisEnum object : values) {
			if (object.key == key) {
				return object.value;
			}
		}
		return "";
	}

	@Override
	public String toString() {
		return String.valueOf(this.key);
	}

}