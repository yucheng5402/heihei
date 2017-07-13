package com.zhidi.servlet;

import java.util.HashMap;
import java.util.Map;

import com.zhidi.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestJsonObject {
	public static void test1() {
		//将一个对象转化成json格式的数据
		User user = new User("张三", "123456");
		Map<String, String> map = new HashMap<String, String>();
		map.put("hobby1", "篮球");
		map.put("hobby2", "吹牛");
		
		user.setHobby(map);
		JSONObject jObject = JSONObject.fromObject(user);
		System.out.println(jObject.toString());
		//通过键名获取值
		Map<String, String> m = (Map<String, String>)jObject.get("hobby");
		System.out.println(m);
		System.out.println(m.get("hobby1"));
		
	}
	
	public static void test2() {
		//如果要转化的是一个数组，就要用JSONArray对象转化
		User[] user = new User[]{new User("张三", "123"), new User("李四", "22525"), new User("王五", "12125")};
		JSONArray js = JSONArray.fromObject(user);
		js.add("123");
		System.out.println(js.toString());
		Object obj = js.get(0);
		System.out.println(obj);
	}
	
	public static void main(String[] args) {
//		test1();
		test2();
	}

}
