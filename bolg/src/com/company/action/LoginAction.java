package com.company.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.bean.User;
import com.company.service.IUserService;
import com.company.service.impl.UserServiceImpl;

/**
 * @author yang
 * @category 登录控制器
 */
@WebServlet("/login.action")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 创建业务逻辑对象
		IUserService userService = new UserServiceImpl();
		// 接收用户输入的验证码
		String captcha = request.getParameter("captcha");
		// 判断用户输入的验证码是否正确
		String simpleCaptcha = request.getSession().getAttribute("simpleCaptcha").toString();
		// 判断用户输入的验证码是否正确
		if (captcha.equals(simpleCaptcha)) {
			// 得到用户名的名字和密码
			String name = request.getParameter("name");
			String pass = request.getParameter("pass");
			// 调用业务逻辑方法得到业务对象
			User user = userService.findByName(name, pass);
			// 得到返回集
			if (user != null) {
				System.out.println("登录成功");
				// 吧对象放在作用域里面
				HttpSession session = request.getSession();
				session.setAttribute("current_user", user);
				session.setMaxInactiveInterval(60 * 10);
				// 转发到首页
				request.getRequestDispatcher("index.html").forward(request, response);
				// response.sendRedirect("index.html");
			} else {
				System.out.println("失败");
				request.setAttribute("mes", "密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else {
			System.out.println("验证码错误");
			request.setAttribute("mes", "验证码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
