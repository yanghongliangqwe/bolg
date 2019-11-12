package com.company.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.service.IBlogService;
import com.company.service.impl.BlogServiceImpl;
import com.company.vo.BlogVO;

/**
 * @author Johnny
 * @category 博客列表控制器
 * @category kidStr 博客类型id ： 如果没有，则表示查询所有
 */
@WebServlet("/bloglist.action")
public class BlogListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BlogListAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建业务逻辑对象
		IBlogService blogService = new BlogServiceImpl();
		//接收博客类型的字符串
		String kidStr = request.getParameter("kid");
		//定义集合
		List<BlogVO> list = null;
		//判断是否为空
		if(kidStr==null){
			//调用业务逻辑查询所有博客的方法，返回博客列表（VO）
			list = blogService.findList();
		}else{
			int kid = Integer.valueOf(kidStr);
			list = blogService.findKindList(kid);
		}
		//将集合列表放入到作用域中
		request.setAttribute("list", list);
		//转发到对应的页面中
		request.getRequestDispatcher("bloglist.jsp").forward(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}























