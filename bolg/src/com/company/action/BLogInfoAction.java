package com.company.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.bean.Blog;
import com.company.service.IBlogService;
import com.company.service.impl.BlogServiceImpl;

/**
 * @author 杨宏亮
 * @category 博客详情控制器
 */
@WebServlet("/showbloginfo.action")
public class BLogInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BLogInfoAction() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建业务逻辑对象
		IBlogService blogService = new BlogServiceImpl();
		//接收博客id
		String idStr = request.getParameter("id");
		if(idStr!=null&&!idStr.equals("")){
			int id = Integer.valueOf(idStr);
			//调用业务逻辑方法，得到博客对象
			Blog blog = blogService.find(id);
			//将博客对象存入作用域中
			request.setAttribute("blog", blog);
			//转发到页面
			request.getRequestDispatcher("bloginfo.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("404.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

