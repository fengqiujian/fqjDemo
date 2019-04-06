package com.enjoyhis.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @class: JSONUtils
 * @description:
 * @version: V1.0
 */
public class JSONUtils {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/*
	 * DisableCheckSpecialChar：一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false
	 * QuoteFieldNames———-输出key时是否使用双引号,默认为true WriteMapNullValue——–是否输出值为null的字段,默认为false
	 * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
	 * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
	 * WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
	 * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
	 */

	// 将日期转化为 yyyy-MM-dd HH:mm:ss的格式
	public static SerializerFeature[] serializerFeature = { SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect };

	/***
	 * 将对象序列化为JSON文本
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object, serializerFeature);
	}

	/***
	 * 将对象转换为JSON对象
	 * 
	 * @param object
	 * @return
	 */
	public static JSONObject toJSONObject(String text) {
		return JSON.parseObject(text);
	}

	/***
	 * 将字符串转换为JSON对象数组
	 * 
	 * @param object
	 * @return
	 */
	public static JSONArray toJSONArray(String text) {
		return JSON.parseArray(text);
	}

	/***
	 * 将JSON串转换为传入类型的对象
	 * 
	 * @param <T>
	 * @param jsonString
	 * @param beanClass
	 * @return
	 */
	public static <T> T toBean(String jsonString, Class<T> beanClass) {
		return (T) JSON.parseObject(jsonString, beanClass);
	}

	public static <T> List<T> toList(String jsonString, Class<T> beanClass) {
		return JSON.parseArray(jsonString, beanClass);
	}

	public static void main(String[] args) {
		/*
		 * Map<String,Object> map = new HashMap<String, Object>(); map.put("name","大灰狼");
		 * map.put("age", 25); map.put("birthday",new Date()); map.put("msg", null); String
		 * s1=toJSONString(map); System.out.println(s1); System.out.println("===========");
		 * JSONObject jsonObj =toJSONObject(s1); System.out.println(jsonObj);
		 * 
		 * User user = new User(); user.setCity("北京"); user.setNickname("喜洋洋"); String s2=
		 * toJSONString(user); System.out.println("============"); System.out.println(s2);
		 * 
		 * User u=toBean(s2, User.class); System.out.println(u.getCity()); List<Map<String,Object>>
		 * list=new ArrayList<Map<String,Object>>(); list.add(map); String json=toJSONString(list);
		 * System.out.println(json);
		 * 
		 * System.out.println(toList(json,Map.class));
		 */
	}

}
