<%@page contentType="text/html; charset=UTF-8"%>

<%
String error = (String)request.getAttribute("error");
if(error == null){
	error = "";
}
%>
<html>
	<head>
		<title>ログイン画面</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	<body>
	
	    <%@include file="/common/header.jsp" %>
		<p class="space2"></p>
		
		<h2 style="text-align:center">ログイン画面</h2>
		
		<div style="margin-bottom:250px">
			<table style="margin:auto">
				<form action="<%=request.getContextPath() %>/userLogin" method="post">
					<tr>
						<td colspan="2" style="text-align:center"><%=error %></td>
					</tr>
					<tr>
						<td>名前(ID)</td>
						<td><input type="text" name="user"></td>
					</tr>
					<tr>
						<td>パスワード</td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center">
						<input type="submit" value="ログイン"></td>
					</tr>
				</form>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
					<a href="<%=request.getContextPath() %>/view/userInsert.jsp">新規会員登録</a></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
					登録しない場合は<a href="<%=request.getContextPath() %>/view/orderInsert.jsp">こちら</a>から</td>
				</tr>
			</table>
		</div>
		
		<p class="push"></p>
		<%@include file="/common/footer.jsp" %>
		
	</body>
</html>