//package com.company.util;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.company.bean.Blog;
//import com.company.bean.BlogKind;
//import com.company.bean.Comment;
//import com.company.bean.User;
//import com.company.dao.IBlogDao;
//import com.company.dao.IBlogKindDao;
//import com.company.dao.IUserDao;
//
//import com.company.vo.BlogVO;
//import com.company.vo.CommentVO;
//
///**
// * @author yang
// *
// */
//public class VOUtil {
//	public static List<BlogVO> getBlogVO(List<Blog> blogs) {
//		IUserDao userDao = DataConnector.getConnector().getMapper(IUserDao.class);
//		IBlogKindDao blogKindDao =DataConnector.getConnector().getMapper(IBlogKindDao.class);
//		List<BlogVO> list = new ArrayList<BlogVO>();
//		for (Blog blog : blogs) {
//			BlogVO blogVO = new BlogVO();
//			blogVO.setId(blog.getId());
//			blogVO.setKid(blog.getKid());
//			blogVO.setUid(blog.getUid());
//			blogVO.setTitle(blog.getTitle());
//			blogVO.setSchema(blog.getSchema());
//			blogVO.setContent(blog.getContent());
//			blogVO.setClicks(blog.getClicks());
//			blogVO.setDateTime(blog.getDatetime());
//			User user = userDao.findById(blog.getUid());
//			blogVO.setAuthorName(user.getName());
//			BlogKind blogKind = blogKindDao.find(blog.getKid());
//			blogVO.setKindName(blogKind.getName());
//			list.add(blogVO);
//		}
//		return list;
//	}
//
//	public static List<CommentVO> getCommentVO(List<Comment> comments) {
//		IUserDao userDao =DataConnector.getConnector().getMapper(IUserDao.class);
//		IBlogDao blogDao = DataConnector.getConnector().getMapper(IBlogDao.class);
//		List<CommentVO> list = new ArrayList<CommentVO>();
//		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		for (Comment comment : comments) {
//			CommentVO commentVO = new CommentVO();
//			java.sql.Timestamp datetime = comment.getDatetime();
//			String datetimeStr = sdf.format(datetime);
//			User user = userDao.findById(comment.getUid());
//			Blog blog = blogDao.find(comment.getBid());
//			commentVO.setId(comment.getId());
//			commentVO.setUid(comment.getUid());
//			commentVO.setBid(comment.getBid());
//			commentVO.setIp(comment.getIp());
//			commentVO.setDatetime(datetime);
//			commentVO.setContent(comment.getContent());
//			commentVO.setDatetimeStr(datetimeStr);
//			commentVO.setUserName(user.getName());
//			commentVO.setBlogName((blog.getTitle()));
//			list.add(commentVO);
//		}
//		return list;
//	}
//}
