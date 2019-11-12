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
 * @author 杨宏亮
 * @category 用户编辑控制器
 * @category type：1 删除 2 编辑前 3 保存编辑
 */
@WebServlet("/edituser.action")
public class EditUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditUserAction() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用业务逻辑对象
		IUserService userService = new UserServiceImpl();
		// 从session中获取到当前用户
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("current_user");
		if (obj != null) {
			// 判断是不是管理员
			User user = (User) obj;// 强行转换
			if (user.getId() == 1) {
				String type = request.getParameter("type");
				if (type.equals("1")) {
					// 调用业务逻辑，返回结果
					String idStr = request.getParameter("uid");
					boolean flag = userService.delete(idStr);
					if (flag) {
						response.sendRedirect("showuserlist.action");
					} else {
						request.getRequestDispatcher("404.jsp").forward(request, response);
					}

				} else if (type.equals("2")) {

				} else {

				}
			}
		}else{
			request.getRequestDispatcher("404.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
