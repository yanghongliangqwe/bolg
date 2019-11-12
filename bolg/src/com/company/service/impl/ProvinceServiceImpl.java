package com.company.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.company.bean.Province;
import com.company.dao.IProvinceDao;

import com.company.service.IProvinceService;
import com.company.util.DataConnector;

@Service
public class ProvinceServiceImpl implements IProvinceService {
	@Resource
	private IProvinceDao myProvincedao;

	@Test
	public void test() {
		myProvincedao.findObject("120000");
	}

	@Override
	public List<Province> findAll() {

		return myProvincedao.findAll();
	}

	@Override
	public Province findObject(String provinceId) {
		return myProvincedao.findObject(provinceId);
	}

}
