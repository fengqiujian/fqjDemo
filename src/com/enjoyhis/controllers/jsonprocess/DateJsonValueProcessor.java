package com.enjoyhis.controllers.jsonprocess;

import java.text.SimpleDateFormat;

public class DateJsonValueProcessor {

	private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Object processArrayValue(Object value) {
		return null;
	}

	public Object processObjectValue(String key, Object value) {
		return process(value);
	}

	private Object process(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return sd.format(obj);
		}
	}

}
