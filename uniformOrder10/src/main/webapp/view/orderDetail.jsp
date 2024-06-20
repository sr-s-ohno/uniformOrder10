<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Order" %>
<%@page import="bean.Uniform" %>

<%
//金額フォーマット
MyFormat mf = new MyFormat();

//セッションからユーザー情報を取得
Admin admin = (Admin)session.getAttribute("admin");
//セッション切れか確認
if(admin == null){
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error","セッション切れの為、詳細画面が表示できませんでした。");
	request.setAttribute("cmd","adminlogin");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}

Order order = (Order)request.getAttribute("order");
Uniform uniform = (Uniform)request.getAttribute("uniform");
%>

<html>
	<head>
		<title>受注詳細</title>
		<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
	</head>
	
	<body>
		<div style="text-align:center">
         
         	<!-- メニューデザイン -->
         	<!-- ヘッダー -->
         	<%@include file= "/common/header.jsp" %>
         	
         	<!-- メニュー部分 -->
		 	<div id="menu">
		    	<div class="container">
		       	<!-- ナビゲーション -->
		       	<div id="nav">
		          <ul>
			      <!-- メニューリンク -->
	              <A href="<%= request.getContextPath() %>/view/adminMenu.jsp">
	              【メニューに戻る】</A>
                  <!-- 書籍一覧リンク -->
	              <A href="<%= request.getContextPath() %>/orderList">
	              【受注管理一覧に戻る】</A>
	              </ul>
	           </div>
	           </div>
         	</div>
         	
         	<h2>注文詳細情報</h2>
         	<hr style="height:3; background-color:#008000" />
         	<p class="space2"></p>
         	
		
			<div style="margin-bottom:250px">
			<table style="margin:auto">
				<%
				if(order != null){
				%>
				<tr>
					<td style="width:80px; background-color:#00b16b">No</td>
					<td style="width:100px; background-color:#00ffff"><%=order.getUnino() %></td>
				</tr>
				<tr>
					<td style="width:80px; background-color:#00b16b">氏名</td>
					<td style="width:100px; background-color:#00ffff"><%=order.getUser() %></td>
				</tr>
				<tr>
					<td style="width:80px; background-color:#00b16b">種類</td>
					<td style="width:100px; background-color:#00ffff"><%=uniform.getType() %></td>
				</tr>
				<tr>
					<td style="width:80px; background-color:#00b16b">個数</td>
					<td style="width:100px; background-color:#00ffff"><%=order.getQuantity() %></td>
				</tr>
				<tr>
					<td style="width:80px; background-color:#00b16b">合計金額</td>
					<td style="width:100px; background-color:#00ffff"><%= mf.moneyFormat( uniform.getPrice() * order.getQuantity() %></td>
				</tr>
				<tr>
					<td style="width:80px; background-color:#00b16b">発注日</td>
					<td style="width:100px; background-color:#00ffff"><%=order.getDate() %></td>
				</tr>
				<tr>
					<td style="width:80px; background-color:#00b16b">入金状況</td>
					<td style="width:100px; background-color:#00ffff"><%=order.getPayment() %></td>
				</tr>
				<tr>
					<td style="width:80px; background-color:#00b16b">発送状況</td>
					<td style="width:100px; background-color:#00ffff"><%=order.getSend() %></td>
				</tr>
				<%
				}
				%>
			</table>
			</div>
			
			<p class="space"></p>
			
		</div>
      
      <!-- フッター -->
      <%@include file= "/common/footer.jsp" %>
			
	</body>
</html>