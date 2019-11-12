package com.company.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.company.bean.Area;
import com.company.dao.IAreaDao;

import com.company.service.IAreaService;
import com.company.util.DataConnector;

import jdk.nashorn.internal.ir.annotations.Reference;

@Service
public class AreaServiceImpl implements IAreaService {
	@Resource
	private IAreaDao myareadao;

	@Test
	public void Test() {
		myareadao.findObject("110101");
	}

	@Override
	public List<Area> findAreasByCid(String cityId) {

		return myareadao.findAreasByCid(cityId);
	}

	@Override
	public Area findObject(String areaId) {

		return myareadao.findObject(areaId);
	}

}
