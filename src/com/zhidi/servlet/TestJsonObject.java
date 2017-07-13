package com.zhidi.servlet;

import java.util.HashMap;
import java.util.Map;

import com.zhidi.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestJsonObject {
	public static void test1() {
		//��һ������ת����json��ʽ������
		User user = new User("����", "123456");
		Map<String, String> map = new HashMap<String, String>();
		map.put("hobby1", "����");
		map.put("hobby2", "��ţ");
		
		user.setHobby(map);
		JSONObject jObject = JSONObject.fromObject(user);
		System.out.println(jObject.toString());
		//ͨ��������ȡֵ
		Map<String, String> m = (Map<String, String>)jObject.get("hobby");
		System.out.println(m);
		System.out.println(m.get("hobby1"));
		
	}
	
	public static void test2() {
		//���Ҫת������һ�����飬��Ҫ��JSONArray����ת��
		User[] user = new User[]{new User("����", "123"), new User("����", "22525"), new User("����", "12125")};
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
