<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>calendar</title>
</head>
<body>
	<div align="center">
		<form action="request.jsp">
			<table>
				<tr>
					<td>时间</td>
					<td>
						<select id="year" name="year" onchange="year_click()">
							<option>-</option>
							<% int i = 0; %>
							<% for (i = 1970; i <= 2017; i++) { %>
							<option><%=i %></option>
							<%} %>
						</select>年
						<select id="month" name="month" onchange="month_click(this)">
							<option>-</option>
						</select>月
						<select id="day" name="day">
							<option>-</option>
						</select>日
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交"/></td>
				</tr>
			</table>
			<table>
				<tr>
					<td>时间2</td>
					<td>
						<select id="yearq" name="yearq" onchange="year_clickq()">
							<option>-</option>
							<% int y = 0; %>
							<% for (y = 1970; y <= 2017; y++) { %>
							<option><%=y %></option>
							<%} %>
						</select>年
						<select id="monthq" name="monthq" onchange="month_clickq(this)">
							<option>-</option>
						</select>月
						<select id="dayq" name="dayq">
							<option>-</option>
						</select>日
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交"/></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		
		function year_click() {
			var html = "<option>-</option>";
			var year = document.getElementById("year").value;
			
			if(year != "-") {
				for(var j = 1; j < 13; j++) {
					html += "<option>"+j+"</option>";
				}
			}
			
			document.getElementById("month").innerHTML = html;
			document.getElementById("day").innerHTML = "<option>-</option>";
		}
		
		function month_click(obj) {
			if(obj.value == "-") {
				document.getElementById("day").innerHTML = "<option>-</option>";
				return;
			}
			
			var html = "<option>-</option>";
			var year = document.getElementById("year").value;
			var day = 30;
			var month = parseInt(obj.value);
			
			switch(month) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					day = 31;
					break;
			}
			if(month == 2) {
				if(year%4==0 && year%100!=0 || year%400==0) {
					day = 29;
				} else {
					day = 28;
				}
			}
			
			for(var k = 1; k <= day; k++) {
				html += "<option>"+k+"</option>";
			}
			
			document.getElementById("day").innerHTML = html;
		}
		/*上面  */
		function year_clickq() {
			var htmlq = "<option>-</option>";
			var yearq = document.getElementById("yearq").value;
			
			if(yearq != "-") {
				for(var j = 1; j < 13; j++) {
					htmlq += "<option>"+j+"</option>";
				}
			}
			
			document.getElementById("monthq").innerHTML = htmlq;
			document.getElementById("dayq").innerHTML = "<option>-</option>";
		}
		
		function month_clickq(obj) {
			if(obj.value == "-") {
				document.getElementById("dayq").innerHTML = "<option>-</option>";
				return;
			}
			
			var htmlq = "<option>-</option>";
			var yearq = document.getElementById("yearq").value;
			var dayq = 30;
			var monthq = parseInt(obj.value);
			
			switch(monthq) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					dayq = 31;
					break;
			}
			if(monthq == 2) {
				if(yeaqr%4==0 && yearq%100!=0 || yearq%400==0) {
					dayq = 29;
				} else {
					dayq = 28;
				}
			}
			
			for(var k = 1; k <= dayq; k++) {
				htmlq += "<option>"+k+"</option>";
			}
			
			document.getElementById("dayq").innerHTML = htmlq;
		}
		
		
		
	</script>
</body>
</html>