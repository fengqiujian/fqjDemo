package com.enjoyhis.util;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

public class Constants {
	
	//从配置文件config读取的配置
	public static Configuration config = null;
	public static String IMGYUNDOMAIN = "http://res.dryork.cn/";


	public static String jsonResultCode = "jsonResultCode";
	public static String jsonResultDesc = "jsonResultDesc";
	public static String jsonResultData = "jsonResultData";
	public static String pageResultData = "pageResultData";
	public static String jsonResultSize = "jsonResultSize";
	public static String jsonHasMore= "jsonHasMore";
	
	public static String clientInfo= "clientInfo";
	
	//管理系统分页，每页显示的数目
	public static int pageSize = 10;

	
	//返回状态码对应表
	public static final Map<Integer,String> codeMap=new HashMap<Integer,String>(){
		private static final long serialVersionUID = 1L;
		{
			//系统级错误
			put(0,"请求成功");
			put(-1,"系统错误");
			put(-2,"参数不正确（请检查必填项）");
			put(-3,"旧密码错误");
			put(-4,"云端无数据");
			put(-5,"目标有欠款");
			put(-6,"该条目已申请退费，正在等待集团审核");
			put(-7,"用户名或密码错误");
			put(-8,"无此卡号");
			put(-9,"不可转给自己");
			put(-10,"您的处置项不符合当前活动");
			put(-20,"网络错误");
			put(-100,"权限验证失败，已经登出");
			put(-110,"不能重复提交！");
			put(-120,"该挂号单状态发生改变，请返回刷新后再进行操作！");
			put(-130,"该预约状态发生改变，请返回刷新后再进行操作！");
		}
	};
	
	public static String getConfig(String key){
		if(config == null)
			return null;
		else
			return config.getString(key);
	}
	
	static {
		Configurations configs = new Configurations();

		try {
			URL path = Constants.class.getClassLoader().getResource("");
			System.out.println("Properties path:"+path.getPath()+"config/config.properties");
			config = configs.properties(new File(path.getPath()+"config/config.properties"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
