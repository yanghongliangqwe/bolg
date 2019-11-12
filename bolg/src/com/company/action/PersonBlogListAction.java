package com.company.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.bean.User;
import com.company.service.IBlogService;
import com.company.service.IUserService;
import com.company.service.impl.BlogServiceImpl;
import com.company.service.impl.UserServiceImpl;
import com.company.vo.BlogVO;

/**
 * @author 杨宏亮
 * @category 个人博客列表控制器
 * @category 展示他人的博客，展示自己的博客
 * @category uid：要展示的用户id
 */
@WebServlet("/personbloglist.action")
public class PersonBlogListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PersonBlogListAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建用户业务逻辑对象，创建博客业务逻辑对象
		IUserService userService = new UserServiceImpl();
		IBlogService blogService = new BlogServiceImpl();
		//接收用户id
		String uidStr = request.getParameter("uid");
		if(uidStr!=null&&!uidStr.equals("")){
			int uid = Integer.valueOf(uidStr);
			//获得用户
			User user = userService.find(uidStr);
			//获得该用户的博客列表
			List<BlogVO> list = blogService.findList(uid);
			//将用户对象放入作用域中，将博客列表放入作用域中
			request.setAttribute("user", user);
			request.setAttribute("list", list);
			//转发
			request.getRequestDispatcher("personblogschemalist.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("404.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
