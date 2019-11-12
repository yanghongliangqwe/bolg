package com.company.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.bean.User;
import com.company.service.IUserService;
import com.company.service.impl.UserServiceImpl;

/**
 * @author 杨宏亮
 * @category 用户注册服务器
 */
@WebServlet("/regist.action")
public class RegistAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistAction() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 创建业务逻辑对象
		IUserService userService = new UserServiceImpl();
		// 接收用户信息
		String ageStr = request.getParameter("age");
		String areaId = request.getParameter("areaId");
		String cityId = request.getParameter("cityId");
		String inputdate = request.getParameter("inputdate");
		String ip = request.getLocalAddr();
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String provinceId = request.getParameter("provinceId");
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel");
		int age = Integer.valueOf(ageStr);// 拆开
		// 调用方法返回结果
		User user = new User(name, pass, sex, age, tel, provinceId, cityId, areaId,
				new Timestamp(System.currentTimeMillis()), ip);
		boolean falg = userService.add(user);
		// 判断
		if (falg) {
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("404.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
