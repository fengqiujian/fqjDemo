package com.enjoyhis.service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.enjoyhis.rmiclient.AccountService;

public class HisPatientClient {

	// 获取云端用户信息

	// 更新云端预收款，欠款

	// 更新云端用户信息

	public static void main(String[] args) {
		try {
			String url = "http://localhost:8081/enjoyhisjt/accountservice.hessian";
			HessianProxyFactory factory = new HessianProxyFactory();
			AccountService helloService = (AccountService) factory.create(AccountService.class, url);

			helloService.sayHello("测试11111111");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
