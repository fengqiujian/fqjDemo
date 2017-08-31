package com.enjoyhis.util;

import com.caucho.hessian.client.HessianProxyFactory;

public class HessianFactoryUtil {

	public static Object getHessianObj(Class<?> classname) {
		try {
			// String url = "http://localhost:8080/enjoyhisfy/helloservice.hessian";
			HessianProxyFactory factory = new HessianProxyFactory();
			Object obj = factory.create(classname, Constants.getConfig(classname.getName()));
			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
