package com.company.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.company.bean.Blog;
import com.company.bean.BlogKind;
import com.company.bean.User;
import com.company.dao.IBlogDao;
import com.company.dao.IBlogKindDao;
import com.company.dao.ICommentDao;
import com.company.dao.IUserDao;
import com.company.service.IBlogService;
import com.company.util.DataConnector;
import com.company.vo.BlogVO;

@Service
public class BlogServiceImpl implements IBlogService {
	@Resource
	private IUserDao userDao;
	@Resource
	private IBlogKindDao blogKindDao;
	@Resource
	private ICommentDao mycommentDao;
	@Resource
	private IBlogDao myBlogDao;

	/*
	 * public void test() { List<BlogVO> list = findHotList();
	 * System.out.println(list.size()); for (BlogVO blogVO : list) {
	 * System.out.println(blogVO.getKindName());
	 * System.out.println(blogVO.getTitle());
	 * System.out.println(blogVO.getAuthorName()); } }
	 */
	@Test
	public void test() {
		Blog blog = new Blog(2, 5, "杨1", "宏", "s", 3, new Timestamp(System.currentTimeMillis()));
		myBlogDao.add(blog);
		/*
		 * List<Blog> list=myBlogDao.findList(); System.out.println(list);
		 */
		/*
		 * List<Blog> list=myBlogDao.findHotList(); System.out.println(list);
		 * List<Blog> list2=myBlogDao.findIndexList();
		 * System.out.println(list2);
		 */
		// Blog blog=new Blog(7, 3, 8, "杨", "宏", "亮", 11, new
		// Timestamp(System.currentTimeMillis()));
		// myBlogDao.update(blog);

	}

	@Override
	public List<BlogVO> findList() {
		List<BlogVO> list = new ArrayList<BlogVO>();
		List<Blog> blogs = myBlogDao.findList();
		list = getBlogVO(blogs);
		return list;
	}

	@Override
	public List<BlogVO> findHotList() {
		List<BlogVO> list = new ArrayList<BlogVO>();
		List<Blog> blogs = myBlogDao.findHotList();
		list = getBlogVO(blogs);
		return list;
	}

	@Override
	public List<BlogVO> findIndexList() {
		List<BlogVO> list = new ArrayList<BlogVO>();
		List<Blog> blogs = myBlogDao.findIndexList();
		list = getBlogVO(blogs);
		return list;
	}

	@Override
	public List<BlogVO> findList(int uid) {
		List<BlogVO> list = new ArrayList<BlogVO>();
		List<Blog> blogs = myBlogDao.findListl(uid);
		list = getBlogVO(blogs);
		return list;
	}

	@Override
	public List<BlogVO> findKindList(int kid) {
		List<BlogVO> list = new ArrayList<BlogVO>();
		List<Blog> blogs = myBlogDao.findKindList(kid);
		list = getBlogVO(blogs);
		return list;
	}

	@Override
	public Blog find(int id) {
		return myBlogDao.find(id);
	}

	@Override
	public boolean delete(int id) {
		// 首先删除该博客的所有评论：使用的是评论的数据访问层对象
		mycommentDao.deleteByBlogId(id);

		// 删除该博客对象，根据博客id
		boolean flag = myBlogDao.delete(id) > 0 ? true : false;
		return flag;
	}

	@Override
	public boolean update(int id, String title, String kind, String schema, String content) {
		Blog blog = myBlogDao.find(id);
		return false;
	}

	@Override
	public boolean saveBlog(int uid, String title, String kind, String schema, String content) {

		int kid = Integer.valueOf(kind);

		int clicks = 0; // 初始化的点击量为0
		// 创建博客对象
		Blog blog = new Blog(kid, uid, title, schema, content, clicks, new Timestamp(System.currentTimeMillis()));
		int i = myBlogDao.add(blog);
		if (i > 0) {
			return true;
		} else {
			return false;

		}
	}

	private List<BlogVO> getBlogVO(List<Blog> blogs) {

		List<BlogVO> list = new ArrayList<BlogVO>();
		for (Blog blog : blogs) {
			BlogVO blogVO = new BlogVO();
			blogVO.setId(blog.getId());
			blogVO.setKid(blog.getKid());
			blogVO.setUid(blog.getUid());
			blogVO.setTitle(blog.getTitle());
			blogVO.setSchema(blog.getSchema());
			blogVO.setContent(blog.getContent());
			blogVO.setClicks(blog.getClicks());
			blogVO.setDateTime(blog.getDatetime());
			User user = userDao.findById(blog.getUid());
			blogVO.setAuthorName(user.getName());
			BlogKind blogKind = blogKindDao.find(blog.getKid());
			blogVO.setKindName(blogKind.getName());
			list.add(blogVO);
		}
		return list;
	}
}
