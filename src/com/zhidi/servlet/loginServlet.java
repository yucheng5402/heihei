package com.zhidi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidi.entity.User;

import net.sf.json.JSONObject;
@WebServlet("/login.do")
public class loginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

}
		
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");
		String name = req.getParameter("username");
		String pwd = req.getParameter("password");
		Map<String,Object> result = new HashMap<String,Object>();
		PrintWriter writer = resp.getWriter();
		if("����".equals(name)&&"123456".equals(pwd)){
//			String jsonStr = "{\"name:\"С��\",\"password\":\"123456\", \"code\":true}";
			User user = new User(name, pwd);
			result.put("user", user);
		JSONObject jObject =  JSONObject.fromObject(result);
		System.out.println(jObject);
		
		result.put("code", true);
		result.put("msg", "��¼�ɹ�");
		
		//��map����ת��Ϊjson��ʽ�Ķ���
		JSONObject jObject2 = JSONObject.fromObject(result);
		writer.print(jObject2.toString());
	} else {
		String jsonStr = "{\"msg\":\"�û�������\", \"code\":false}";
		
		result.put("code", false);
		result.put("msg", "��¼ʧ��");
		JSONObject jsonObject = JSONObject.fromObject(result);
		writer.print(jsonObject.toString());
	}
	writer.flush();
	writer.close();
	}
	
	
}
