package com.zhidi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ajaxDemo.do")
public class ajaxServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("content-type", "text/html;charset=utf-8");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		
		PrintWriter writer = resp.getWriter();
		writer.println("姓名="+name+"性别="+sex);
		writer.flush();
		writer.close();
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		//获取请求参数
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		
		//获取输出流， 用流输出响应信息
		PrintWriter writer = resp.getWriter();
		writer.write("这里是post请求，姓名：" +  name + ",性别：" + sex);
		writer.flush();
		writer.close();
	}


		
}
