<!-- /*
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : メニュー画面の実装
 * 作成者 : 光本慶太
 * 作成着手日 : 2024年 6月25日
 */ -->

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User" %>

<%
//セッションからユーザー情報を取得
User objUser = (User) session.getAttribute("objUser");
//セッション切れか確認
if (objUser == null) {
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error", "セッション切れの為、メニュー画面が表示できませんでした。");
	request.setAttribute("cmd", "userlogin");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}
%>

<html>
<head>
   <title>受注管理システム</title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
   <div style="text-align:center">

    <!-- ヘッダー -->
	<%@include file="/common/userHeader.jsp"%>
	<p class="space"></p>

	<h2>メニュー画面</h2>
	<hr style="height: 5; background-color: #a0d0ff";/>

	<div id="main">
		<table>
			<tr>
				<td><a href="<%=request.getContextPath()%>/view/orderInsert.jsp">商品購入</a></td>
			</tr>
			<p class="space"></p>
			
			<tr>
				<td><a href="<%=request.getContextPath()%>/userDetail?user=<%= objUser.getUser() %>&cmd=update">会員情報変更</a></td>
			</tr>
			<p class="space"></p>

			<tr>
				<td><a href="<%=request.getContextPath()%>/userLogout">ログアウト</a></td>
			</tr>
			<p class="space"></p>
			
		</table>
	</div>
   </div>
	
	<!-- フッター -->
	<p class="push"></p>
	<%@include file="/common/userFooter.jsp"%>
	
</body>
</html>
