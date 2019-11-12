<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title></title>
<!-- 引入 Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="js/jquery.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="js/bootstrap.min.js"></script>
<link href="css/style.css" rel="stylesheet" />
<script type="text/javascript">
	onload = function() {
		setInterval(go, 1000);
	};
	var x = 3; //利用了全局变量来执行 
	function go() {
		x--;
		if (x > 0) {
			document.getElementById("sp").innerHTML = x; //每次设置的x的值都不一样了。 
		} else {
			location.href = 'index.html';
		}
	}
</script>
</head>

<body>
	<!-- 头部  -->
	<c:import url="heard.jsp"></c:import>

	<!--中间内容部分开始-->
	<div class="container fourzerofour">
		<p>
			倒数<span id="num"></span>秒后：跳转到<a href="index.html">首页</a>
		</p>
		<img src="img/404.jpg" />
	</div>
	<!--内容部分结束-->

	<!-- 脚部 -->
	<c:import url="footer.jsp"></c:import>
	<!-- 脚部结束 -->

</body>

</html>