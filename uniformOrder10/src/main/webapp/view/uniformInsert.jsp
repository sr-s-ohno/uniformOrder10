<%@page contentType="text/html; charset=UTF-8"%>

<%
String error = (String)request.getAttribute("error");
if(error == null){
	error = "";
}
%>

<html>
<head>
   <title>ユニフォーム受注管理システム</title>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

	<div style="text-align: center">
	    <!-- ヘッダー -->
		<%@include file="../common/adminHeader.jsp"%>
		<p class="space"></p>
		
		<div id="menu">
			<div class="container">
				<div id="nav">
					<ul>
						<!-- メニューリンク -->
						<A href="<%=request.getContextPath()%>/view/adminMenu.jsp">
						【メニューに戻る】</A>
					</ul>
				</div>
			</div>
		</div>

		<h2 style="text-align: center">商品登録</h2>
		<hr style="height: 5; background-color: #7fef6f" />
		<p class="space2"></p>

		<form action="<%=request.getContextPath()%>/uniformInsert">
			<table>
				<tr>
					<td style="background-color: #00b16b; width: 150">ID</td>
					<td style="width: 180"><input type="text" size="30" name="unino"></td>
				</tr>

				<tr>
					<td style="background-color: #00b16b; width: 150">商品名</td>
					<td style="width: 180"><input type="text" size="30" name="type"></td>
				</tr>

				<tr>
					<td style="background-color: #00b16b; width: 150">税込み価格</td>
					<td style="width: 180"><input type="text" size="30" name="price"></td>
				</tr>

				<tr>
					<td style="background-color: #00b16b; width: 150">在庫数</td>
					<td style="width: 180"><input type="text" size="30" name="stock"></td>
				</tr>

			</table>

			<p class="space2"></p>
			<input type="submit" value="登録"></input>
			
		</form>
		
		<p class="space"></p>
		<p style="color: #a52a2a; text-align: center"><%= error %></p>
		
	</div>
	
	<!-- フッター -->
	<div class="push"></div>
	<%@include file="../common/adminFooter.jsp"%>
	
</body>
</html>