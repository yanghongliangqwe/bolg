package com.company.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.company.bean.Blog;
import com.company.bean.Comment;
import com.company.bean.User;
import com.company.dao.IBlogDao;
import com.company.dao.ICommentDao;
import com.company.dao.IUserDao;
import com.company.service.ICommentService;
import com.company.util.DataConnector;
import com.company.vo.BlogVO;
import com.company.vo.CommentVO;

@Service
public class CommentServiceImpl implements ICommentService {
	@Resource
	private ICommentDao mycommentdao;

	@Test
	public void test() {
		mycommentdao.find(15);
	}

	@Override
	public boolean save(int uid, int bid, String content, String ip) {
		Comment comment = new Comment(uid, bid, ip, new Timestamp(System.currentTimeMillis()), content);
		return mycommentdao.save(comment) > 0 ? true : false;
	}

	@Override
	public boolean delete(int id) {

		return mycommentdao.delete(id) > 0 ? true : false;
	}

	@Override
	public List<CommentVO> find(int blogId) {
		List<CommentVO> list = new ArrayList<CommentVO>();
		List<Comment> comments = mycommentdao.find(blogId);
		list = getCommentVO(comments);
		return list;
	}

	public static List<CommentVO> getCommentVO(List<Comment> comments) {
		IUserDao userDao = DataConnector.getConnector().getMapper(IUserDao.class);
		IBlogDao blogDao = DataConnector.getConnector().getMapper(IBlogDao.class);
		List<CommentVO> list = new ArrayList<CommentVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Comment comment : comments) {
			CommentVO commentVO = new CommentVO();
			java.sql.Timestamp datetime = comment.getDatetime();
			String datetimeStr = sdf.format(datetime);
			User user = userDao.findById(comment.getUid());
			Blog blog = blogDao.find(comment.getBid());
			commentVO.setId(comment.getId());
			commentVO.setUid(comment.getUid());
			commentVO.setBid(comment.getBid());
			commentVO.setIp(comment.getIp());
			commentVO.setDatetime(datetime);
			commentVO.setContent(comment.getContent());
			commentVO.setDatetimeStr(datetimeStr);
			commentVO.setUserName(user.getName());
			commentVO.setBlogName((blog.getTitle()));
			list.add(commentVO);
		}
		return list;
	}
}
