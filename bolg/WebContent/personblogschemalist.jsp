<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				function delete_blog(param){
					var del = confirm("确定删除id:"+param+", 你不考虑一下?");
					if(del){
						window.location = "editblog.action?type=1&id="+param;
					}
				}
		</script>
</head>

<body>

	<!-- 头部  -->
	<c:import url="heard.jsp"></c:import>

	<!-- 内容模块1 -->
	<div class="d2 allwidth">
		<!--用户信息-->
		<div id="userinfo" class="templatemo-container userinfo">
			<ul>
				<li>
					<span class="glyphicon glyphicon-user">：${user.name }</span>
				</li>
				<li>
					<span class="glyphicon glyphicon-tree-conifer">：</span>
					<c:if test="${user.sex=='f'}">♀</c:if>
					&nbsp;
					<c:if test="${user.sex!='f'}">♂</c:if>
				</li>
				<li>
					<span class="glyphicon glyphicon-circle-arrow-right">：</span>${user.age }</li>
				<li>
					<span class="glyphicon glyphicon-earphone">：${user.tel }</span>
				</li>
				<c:if test="${current_user != null }">
					<li>
						<a href="showbloglist.action?type=1&uid=${current_user.id }">click me into my blog</a>
					</li>
					<li>
						<a href="createblog.action?type=1">create blog</a>
					</li>
				</c:if>
			</ul>
		</div>
		<!--用户博客概要列表-->
		<div class="schemalist">
			<table class="table table-hover bloglist">
				<tr class="personbloginfo_tr">
					<th style="width: 10%;">作者</th>
					<th>标题</th>
					<th>时间</th>
				</tr>
				<!--要循环的博客概要-->
				<c:forEach items="${list }" var="blogVO">
					<tr class="personbloginfo_tr">
						<td class="line">
							<a href="personbloglist.action?uid=${blogVO.uid }">${blogVO.authorName }</a>
						</td>
						<td class="line">
							<span class="schema_title">${blogVO.title }</span>
						</td>
						<td class="line">
							<span class="schema_time">
								时间：
								<fmt:formatDate pattern="yyyy/MM/dd" value="${blogVO.dateTime }" />
							</span>
							<c:if test="${current_user.id == blogVO.uid }">
								<span class="blogedit">
									<a onclick="delete_blog(${blogVO.id })" style="cursor: pointer;">删除</a>
									&nbsp;|&nbsp;
									<a href="editblog.action?type=2&id=${blogVO.id }">编辑</a>
								</span>
							</c:if>
						</td>
					</tr>
					<tr class="personbloginfo_tr">
						<td class="layout" colspan="3">
							<a href="showbloginfo.action?id=${blogVO.id }"> ${blogVO.schema } </a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<!-- 脚部 -->
	<c:import url="footer.jsp"></c:import>
	<!-- 脚部结束 -->

</body>

</html>