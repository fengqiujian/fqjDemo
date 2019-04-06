package com.enjoyhis.controllers.interceptor;

import java.lang.annotation.Annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SpringMethodInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Annotation[] ann = invocation.getMethod().getAnnotations();
		for (int i = 0; i < ann.length; i++) {
		}
		invocation.getArguments();

		return invocation.proceed();
	}

}
