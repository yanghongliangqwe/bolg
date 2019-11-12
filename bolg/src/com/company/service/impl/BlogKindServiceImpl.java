package com.company.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.company.bean.BlogKind;
import com.company.dao.IBlogKindDao;

import com.company.service.IBlogKindService;
import com.company.util.DataConnector;

@Service
public class BlogKindServiceImpl implements IBlogKindService {
	@Resource
	private IBlogKindDao myBlogkinddao;

	@Test
	public void test() {

	}

	@Override
	public List<BlogKind> findList() {

		return myBlogkinddao.findList();
	}

	@Override
	public BlogKind find(int id) {

		return myBlogkinddao.find(id);
	}

}
