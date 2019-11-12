package com.company.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.company.bean.City;
import com.company.dao.ICityDao;

import com.company.service.ICityService;
import com.company.util.DataConnector;


@Service
public class CityServiceImpl implements ICityService {
	@Resource
	private ICityDao myCitydao;
	@Test
	public void test(){
		myCitydao.findObject("110100");
	}

	@Override
	public List<City> findCitiesByPid(String provinceId) {

		return myCitydao.findCitiesByPid(provinceId);
	}

	@Override
	public City findObject(String cityId) {

		return myCitydao.findObject(cityId);
	}

}
