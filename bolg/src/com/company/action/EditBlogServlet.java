package com.company.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.bean.Blog;
import com.company.bean.BlogKind;
import com.company.bean.User;
import com.company.dao.IBlogDao;

import com.company.service.IBlogKindService;
import com.company.service.IBlogService;
import com.company.service.impl.BlogKindServiceImpl;
import com.company.service.impl.BlogServiceImpl;

/**
 * @author 杨宏亮
 * @category 编辑博客, type: 1：删除博客，2：准备编辑，3：保存编辑信息
 */
@WebServlet("/editblog.action")
public class EditBlogServlet extends HttpServlet {
	
	private IBlogService blogService = new BlogServiceImpl();
	private IBlogKindService blogKindService = new BlogKindServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("current_user");
		int id = Integer.valueOf(request.getParameter("id"));
		if(obj!=null){
			User user = (User)obj;
			if(type.equals("1")){
				boolean flag = blogService.delete(id);
				response.sendRedirect("personbloglist.action?uid="+user.getId());
			}else if (type.equals("2")) {
				Blog blog = blogService.find(id);
				List<BlogKind> blogKinds = blogKindService.findList();
				request.setAttribute("blog", blog);
				request.setAttribute("blogKinds", blogKinds);
				request.getRequestDispatcher("blogedit.jsp").forward(request, response);
			}else if (type.equals("3")) {
				String title = request.getParameter("title");
				String kind = request.getParameter("kind");
				String schema = request.getParameter("schema");
				String content = request.getParameter("content");
				boolean flag = blogService.update(id, title, kind, schema, content);
				if(flag){
					response.sendRedirect("showbloginfo.action?id="+id);
				}
			}
		}else{
			response.sendRedirect("/index.html");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
