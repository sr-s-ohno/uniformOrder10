<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User" %>

<%
String error = (String)request.getAttribute("error");
if(error == null){
	error = "";
}

//セッションからユーザー情報を取得
User objUser = (User)session.getAttribute("objUser");
//セッション切れか確認
if(objUser == null){
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error","セッション切れの為、詳細画面が表示できませんでした。");
	request.setAttribute("cmd","userlogin");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}
%>

<html>
	<head>
		<title>登録情報変更</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	
	<body>
		<div style="text-align:center">
         
         	<!-- メニューデザイン -->
         	<!-- ヘッダー -->
         	<%@include file= "/common/userHeader.jsp" %>
         	<p class="space"></p>
         	
         	<!-- メニュー部分 -->
		 	<div id="menu">
		    	<div class="container">
		       	<!-- ナビゲーション -->
		       	<div id="nav">
		          <ul>
			      <!-- メニューリンク -->
	              <A href="<%= request.getContextPath() %>/view/userMenu.jsp">
	              【メニューに戻る】</A>
	              </ul>
	           </div>
	           </div>
         	</div>
         	
         	<h2>登録情報変更</h2>
         	<hr style="height:5; background-color:#7fef6f"/>
         	<p class="space2"></p>
         	
			
			<form action="<%=request.getContextPath() %>/userUpdate">
			<div style="margin-bottom:250px">
			<table style="margin:auto">
				<%
				if(objUser != null){
				%>
				<tr>
					<td style="width:150px; background-color:#8AC75A">氏名 (ID)</td>
					<td style="width:150px; background-color:#bce2e8"><%=objUser.getUser() %></td>
					<td style="width:150px;"><%=objUser.getUser() %></td>
					<input type="hidden" name="user" value="<%=objUser.getUser() %>">
				</tr>
				<tr>
					<td style="width:150px; background-color:#8AC75A">メールアドレス</td>
					<td style="width:150px; background-color:#bce2e8"><%=objUser.getMail() %></td>
					<td style="width:150px;"><input style="height:48px; white-space: pre-wrap;" type="text" size="30" name="mail"></td>
				</tr>
				<tr>
					<td style="width:150px; background-color:#8AC75A">住所</td>
					<td style="width:150px; background-color:#bce2e8"><%=objUser.getAddress() %></td>
						               <td style="width:150px;"><input type="text" size="30" name="address"></td>
				</tr>

				<%
				}
				%>
				</table>
				
				<p class="space2"></p>
				<input type="submit" value="変更">
				<p class="space2"></p>
				<h3 style="color: #a52a2a;"><%= error %></h3>
			</div>
			<p class="space"></p>
			</form>
		</div>

      <!-- フッター -->
      <div class="push"></div>
      <%@include file= "/common/userFooter.jsp" %>
			
	</body>
</html>