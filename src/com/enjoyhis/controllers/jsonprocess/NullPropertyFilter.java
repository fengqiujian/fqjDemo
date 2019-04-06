package com.enjoyhis.controllers.jsonprocess;

public class NullPropertyFilter {

	public boolean apply(Object obj, String name, Object value) {
		if (value == null) {
			return true;
		} else {
			return false;
		}
	}

}
