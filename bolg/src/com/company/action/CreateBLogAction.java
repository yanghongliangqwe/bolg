package com.company.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.bean.BlogKind;
import com.company.bean.User;
import com.company.service.IBlogKindService;
import com.company.service.IBlogService;
import com.company.service.impl.BlogKindServiceImpl;
import com.company.service.impl.BlogServiceImpl;

/**
 * @author 杨宏亮
 * @category 创建博客的控制器
 * @category type 1:准备创建	2：保存博客
 */
@WebServlet("/createblog.action")
public class CreateBLogAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("current_user");
		if(obj!=null){
			User user = (User)obj;
			//创建业务逻辑对象
			IBlogKindService kindService = new BlogKindServiceImpl();
			IBlogService blogService = new BlogServiceImpl();
			//获取Type值
			String type = request.getParameter("type");
			//根据type值进行判断
			if(type.equals("1")){
				//调用业务逻辑对象取到博客类型集合
				List<BlogKind> blogKindList = kindService.findList();
				//将集合放入作用域中
				request.setAttribute("blogKindList", blogKindList);
				//转发到博客创建页面中
				request.getRequestDispatcher("blogcreate.jsp").forward(request, response);
			}else if(type.equals("2")){
				//获取页面中信息文本
				String title = request.getParameter("title");
				String kind = request.getParameter("kind");
				String schema = request.getParameter("schema");
				String content = request.getParameter("content");
				//调用业务逻辑对象方法，得到返回值
				boolean flag = blogService.saveBlog(user.getId(), title, kind, schema, content);
				//根据返回值，响应
				if(flag){
					response.sendRedirect("personbloglist.action?uid="+user.getId());//重定向到新的控制器
				}else{
					request.getRequestDispatcher("404.jsp").forward(request, response);
				}
			}
		}else{
			//重定向
			response.sendRedirect("login.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);//委托
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}

	
	
}
