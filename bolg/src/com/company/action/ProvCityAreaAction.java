package com.company.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.service.IAreaService;
import com.company.service.ICityService;
import com.company.service.IProvinceService;
import com.company.service.impl.AreaServiceImpl;
import com.company.service.impl.CityServiceImpl;
import com.company.service.impl.ProvinceServiceImpl;
import com.sun.beans.finder.FieldFinder;

import net.sf.json.JSONObject;

/**
 * @author 杨宏亮
 * @category 省市区控制器
 * @category type 1：省会 2：市 3：区县
 */
@WebServlet("/pca.action")
public class ProvCityAreaAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProvCityAreaAction() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收业务逻辑对象
		IProvinceService provinceService=new ProvinceServiceImpl();
		ICityService cityService=new  CityServiceImpl();
		IAreaService areaService=new AreaServiceImpl();

		// 接收参数
		String type= request.getParameter("type");
		List list=null;//声明
		//判断
		if(type.equals("1")){
			list=provinceService.findAll();
		}else if(type.equals("2")){
			String provinceId=request.getParameter("provinceId");
			list =cityService.findCitiesByPid(provinceId);
		}else{
			String cityId=request.getParameter("cityId");
			list =areaService.findAreasByCid(cityId);
		}
		//创建json对象
		JSONObject json=new JSONObject();
		//将集合放去
		json.put("list", list);
		//输出去
		response.getWriter().print(json);

	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
