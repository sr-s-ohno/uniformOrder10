<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Uniform, util.MyFormat, bean.Admin"%>

<%
//金額フォーマット
MyFormat mf = new MyFormat();

//セッションからユーザー情報を取得
Admin objAdmin = (Admin) session.getAttribute("objAdmin");
//セッション切れか確認
if (objAdmin == null) {
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error", "セッション切れの為、商品一覧画面が表示できませんでした。");
	request.setAttribute("cmd", "adminlogin");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}

ArrayList<Uniform> list = (ArrayList<Uniform>) request.getAttribute("uniform_list");
String error = (String) request.getAttribute("error");
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
						<A href="<%=request.getContextPath()%>/view/adminMenu.jsp">【メニューに戻る】</A>
					</ul>
				</div>
			</div>
		</div>

		<h2>商品一覧</h2>
		<hr style="height: 3; background-color: #00b16b" />
		<p class="space2"></p>

		<table style="margin: 0 auto">
			<tr>
				<th style="background-color: #00b16b; width: 200">ID</th>
				<th style="background-color: #00b16b; width: 200">商品</th>
				<th style="background-color: #00b16b; width: 200">税込み価格</th>
				<th style="background-color: #00b16b; width: 200">在庫</th>
				<th style="background-color: #00b16b; width: 200">変更/削除</th>
			</tr>

			<%
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
			%>

			<tr>
				<td style="width: 200"><%=list.get(i).getUnino()%></td>
				<td style="width: 200"><%=list.get(i).getType()%></td>
				<td style="width: 200"><%= mf.moneyFormat( list.get(i).getPrice() ) %></td>
				<td style="width: 200"><%=list.get(i).getStock()%></td>
				<td style="width: 200">&emsp; 
				<a href="<%=request.getContextPath()%>/uniformDetail?unino=<%=list.get(i).getUnino()%>">変更</a>
					&emsp;&emsp; 
				<a href="<%=request.getContextPath()%>/uniformDelete?unino=<%=list.get(i).getUnino()%>">削除</a>
				</td>
			</tr>

			<%
			   }
			}
			%>
	</table>
	</div>

	<!-- フッター -->
	<div class="push"></div>
	<%@include file="../common/adminFooter.jsp"%>
	
</body>
</html>