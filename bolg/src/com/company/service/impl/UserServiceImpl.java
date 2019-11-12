package com.company.service.impl;

import java.sql.Timestamp;
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

import com.company.service.IUserService;
import com.company.util.DataConnector;

@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private ICommentDao mycommentdao;
	@Resource
	private IBlogDao myBlogDao;
	@Resource
	private IUserDao myUserDao;

	@Test
	public void test() {
		User user = myUserDao.findByUserName("admin");
		System.err.println(user.getAreaId());

	}

	@Override
	public User find(String id, String pass) {
		User user = null;
		if (id != null && pass != null) {
			user = myUserDao.find(Integer.valueOf(id), pass);
		}
		return user;
	}

	@Override
	public User findByName(String name, String pass) {
		User user = null;
		if (name != null && pass != null) {
			user = myUserDao.findByName(name, pass);
		}
		return user;
	}

	@Override
	public boolean add(User user) {

		return myUserDao.add(user) > 0 ? true : false;
	}

	@Override
	public List<User> findUserList() {

		return myUserDao.findUserList();
	}
	// @Test
	// public void test1() {
	// System.out.println(findByUserName("aaa"));
	// }

	@Override
	public boolean delete(String id) {
		boolean flag = false;
		if (id != null && !id.equals("")) {
			// 转化为int类型
			int userId = Integer.valueOf(id);
			List<Blog> blogs = myBlogDao.findListl(userId);
			for (Blog blog : blogs) {
				// 根据博客id得到所有的评论
				mycommentdao.deleteByBlogId(blog.getId());
				myBlogDao.delete(blog.getId());
			}
			// 删除发表的所有评论
			mycommentdao.deleteByUserId(userId);
			int i = myUserDao.delete(userId);
			if (i > 0) {
				flag = true;
			}

		}

		return flag;
	}

	@Override
	public User find(String id) {

		return myUserDao.findById(Integer.valueOf(id));
	}

	@Override
	public boolean update(User user) {

		return myUserDao.update(user) > 0 ? true : false;
	}

	@Override
	public boolean findByUserName(String name) {
		if (myUserDao.findByUserName(name) == null) {
			return true;
		} else
			return false;

	}

}
