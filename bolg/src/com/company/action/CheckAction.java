package com.company.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.service.IUserService;
import com.company.service.impl.UserServiceImpl;

@WebServlet("/check.action")
public class CheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckAction() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 创建业务逻辑
		IUserService userService = new UserServiceImpl();
		// 接收ajax
		String name = request.getParameter("name");
		// 调用业务逻辑
		boolean flag = userService.findByUserName(name);
		// 输出
		response.getWriter().print(flag);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
