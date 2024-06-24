<!-- /*
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : メニュー画面の実装
 * 作成者 : 山田彩乃
 * 作成着手日 : 2024年 6月20日
 */ -->

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Admin" %>

<%
//セッションからユーザー情報を取得
Admin objAdmin = (Admin) session.getAttribute("objAdmin");
//セッション切れか確認
if (objAdmin == null) {
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error", "セッション切れの為、メニュー画面が表示できませんでした。");
	request.setAttribute("cmd", "adminlogin");
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
	<%@include file="/common/adminHeader.jsp"%>
	<p class="space"></p>

	<h2>管理者メニュー</h2>
	<hr style="height: 3; background-color: #00b16b"/>

	<div id="main">
		<table>
			<tr>
				<td><a href="<%=request.getContextPath()%>/orderList">受注管理一覧</a></td>
			</tr>
			<p class="space"></p>
			
			<tr>
				<td><a href="<%=request.getContextPath()%>/uniformList">商品一覧</a></td>
			</tr>
			<p class="space"></p>
			
			<tr>
				<td><a
					href="<%=request.getContextPath()%>/view/uniformInsert.jsp">商品登録</a></td>
			</tr>
			<p class="space"></p>
			
			<tr>
				<td><a href="<%=request.getContextPath()%>/adminLogout">ログアウト</a></td>
			</tr>
			<p class="space"></p>
			
		</table>
	</div>
   </div>
	
	<!-- フッター -->
	<p class="push"></p>
	<%@include file="/common/adminFooter.jsp"%>
	
</body>
</html>
