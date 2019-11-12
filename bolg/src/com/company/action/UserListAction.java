package com.company.action;

import java.io.IOException;
import java.util.List;

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
 * @author Johnny
 * @category 用户列表控制器
 */
@WebServlet("/showuserlist.action")
public class UserListAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 增加安全验证
		HttpSession session = request.getSession();
		// 从session中取出当前登录人
		Object obj  = session.getAttribute("current_user");
		if(obj!=null){
			User user = (User)obj;
			if(user.getId()==1){
				// 创建业务逻辑对象
				IUserService userService = new UserServiceImpl();
				// 调用业务逻辑对象方法得到用户列表
				List<User> userList = userService.findUserList();
				// 将集合放入request作用域中
				request.setAttribute("userList", userList);
				// 转发到：用户列表页面
				request.getRequestDispatcher("userlist.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("404.jsp").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("404.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
