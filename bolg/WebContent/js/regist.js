function nameBlur() {
	var n = $("#name").val();
	console.log(n);
	$.ajax({
		type : 'post',
		url : 'check.action',
		data : {
			name : n
		},
		success : function(reslut) {
			console.log(reslut);
			if (reslut == "true") {
				$("#name_exit").html("");
			} else {
				$("#name_exit").html("不用点了，早就用人了");
			}

		},
		dataType : 'text'
	});
}
/**
 * 页面加载就将所有的省份加载出来
 */
$(function() {
	$.post("pca.action", {
		type : 1
	}, function(result) {
		console.log(result);
		var html = "<option value='0'>-prov-</option>";
		// 将集合遍历到一个html字符串中
		$.each(result.list, function(i, data) {
			html += "<option value='" + data.provinceID + "'>" + data.province
					+ "</option>";
		});
		// 将html放入到页面里面
		$("#provincedId").html(html);
	}, "json");
});

/**
 * 显示出所有的市
 * 
 * @param pid
 *            省份的id
 */
function showCity(pid) {
	console.log(pid);
	$.post("pca.action", {
		type : 2,
		provinceId : pid
	}, function(result) {
		console.log(result);
		var html = "<option value='0'>-city-</option>";
		// 将集合遍历到一个html字符串中
		$.each(result.list, function(i, data) {
			html += "<option value='" + data.cityID + "'>" + data.city
					+ "</option>";
		});
		// 将html放入到页面里面
		$("#cityId").html(html);
	}, "json");
	showArea(0);
}

/**
 * 显示出所有的区县
 * 
 * @param cid
 *            市的id
 */
function showArea(cid) {
	$.post("pca.action", {
		type : 3,
		cityId : cid
	}, function(result) {
		var html = "<option value='0'>-area-</option>";
		$.each(result.list, function(i, data) {
			html += "<option value='" + data.areaID + "'>" + data.areas
					+ "</option>";
		});
		// 将html放入到select中
		$("#areaId").html(html);
	}, "json");
}
/**
 * 校验是否成功，然后提交
 * 
 */
function check() {
	var pass1 = $("#pass1").val();
	var pass2 = $("#pass2").val();
	var name = $("#name").val();
	var name_exit = $("#name_exit").html();
	var age = $("#age").val();
	if (name == "") {
		alert("用户名不能为空");
		return false;
	}
	if (name_exit != "") {
		alert("用户名已经存在");
		return false;
	}
	if (pass1 == "") {
		alert("密码不能为空");
		return false;
	}
	if (age == "") {
		alert("年龄不能为空");
		return false;
	}
	if (pass1 == pass2) {
		document.getElementsByTagName("form").item(0).submit();
	} else {
		alert("密码不相同");
		return false;
	}

}
