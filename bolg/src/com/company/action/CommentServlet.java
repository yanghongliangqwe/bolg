package com.company.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.service.ICommentService;
import com.company.service.impl.CommentServiceImpl;
import com.company.vo.CommentVO;

import net.sf.json.JSONObject;

/**
 * @author Johnny
 * @category 评论的控制器 type: 1:查询该博客下的所有评论,2：保存评论，3：删除评论
 */
@WebServlet("/comment.action")
public class CommentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ICommentService commentService = new CommentServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		int bid = Integer.valueOf(request.getParameter("bid"));
		JSONObject json = new JSONObject();
		if (type.equals("1")) {
			List<CommentVO> list = commentService.find(bid);
			json.put("list", list);
		} else if (type.equals("2")) {
			int uid = Integer.valueOf(request.getParameter("uid"));
			String ip = request.getRemoteAddr();// 获得用户的ip地址
			String content = request.getParameter("content");
			boolean flag = commentService.save(uid, bid, content, ip);
			json.put("flag", flag);
		} else if (type.equals("3")) {
			int id = Integer.valueOf(request.getParameter("id"));
			boolean flag = commentService.delete(id);
			json.put("flag", flag);
		}
		out.print(json.toString());
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
