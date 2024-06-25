<!-- /*
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : 商品情報変更の実装
 * 作成者 : 山田彩乃
 * 作成日 : 2024年 6月21日
 */ 
 -->

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Uniform, util.MyFormat, bean.Admin"%>

<!-- セッションからユニフォーム情報を取得 -->
<%
//金額フォーマット
MyFormat mf = new MyFormat();

//セッションからユーザー情報を取得
Admin objAdmin = (Admin) session.getAttribute("objAdmin");
//セッション切れか確認
if (objAdmin == null) {
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error", "セッション切れの為、商品変更画面が表示できませんでした。");
	request.setAttribute("cmd", "adminlogin");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}

Uniform objUniform = (Uniform) request.getAttribute("objUniform");
%>

<html>
<head>
   <title>ユニフォーム受注管理システム</title>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
	<div style="text-align: center">
	
		<!-- メニューデザイン -->
		<!-- ヘッダー -->
		<%@include file="/common/adminHeader.jsp"%>
		<p class="space"></p>

		<!-- メニュー部分 -->
		<div id="menu">
			<!-- ナビゲーション -->
			<div id="nav">
				<ul>
					<!-- メニューリンク -->
					<A href="<%=request.getContextPath()%>/view/adminMenu.jsp">
					【メニューに戻る】</A>
					<!-- 書籍一覧リンク -->
					<A href="<%=request.getContextPath()%>/uniformList">
					【商品一覧に戻る】</A>
				</ul>
			</div>
		</div>

		<h2>商品情報変更</h2>
		<hr style="height: 5; background-color: #7fef6f" />
		<p class="space2"></p>

		<!-- 変更入力フォーム -->
		<form action="<%= request.getContextPath() %>/uniformUpdate" method="get">
			<!-- uninoを隠しフィールドとして送信準備 -->
			<input type="hidden" name="unino" value="<%= objUniform.getUnino() %>">
			<!-- テーブル作成 -->
			<table style="margin: 0 auto">
				<!-- セッションから情報を取得できている（正常動作） -->
				<% 
				   if(objUniform != null) { 
				%>

				<tr>
					<td style="width: 100"></td>
					<td style="width: 100"><font color="#CC3300">&lt&lt変更前情報&gt&gt</font></td>
					<td style="width: 120"><font color="#CC3300">&lt&lt変更後情報&gt&gt</font></td>
				</tr>
				
				<!-- ユニフォームIDの行 -->
				<tr>
					<td style="background-color: #00b16b; width: 150">ID</td>
					<td style="width: 180"><%= objUniform.getUnino() %></td>
					<td style="width: 150"><%= objUniform.getUnino() %></td>
				</tr>
				
				<!-- 商品名の行 -->
				<tr>
					<td style="background-color: #00b16b; width: 150">商品名</td>
					<td style="width: 180"><%= objUniform.getType() %></td>
					<td style="width: 150"><input type="text" size="30" name="type"></td>
				</tr>
				
				<!-- 価格の行 -->
				<tr>
					<td style="background-color: #00b16b; width: 150">税込み価格</td>
					<td style="width: 180"><%= objUniform.getPrice() %></td>
					<td style="width: 150"><input type="text" size="30" name="price"></td>
				</tr>
				
				<!-- 在庫数の行 -->
				<tr>
					<td style="background-color: #00b16b; width: 150">在庫</td>
					<td style="width: 180"><%= objUniform.getStock() %></td>
					<td style="width: 150"><input type="text" size="30" name="stock"></td>
				</tr>
				<%
					}
	         	%>
			</table>
			<p class="space2"></p>

			<input type="submit" value="変更完了">
			<p class="space"></p>

		</form>
	</div>
	
	<!-- フッター -->
	<div class="push"></div>
	<%@include file="/common/adminFooter.jsp"%>
	
</body>
</html>