<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList, bean.Order, util.MyFormat, bean.Admin" %>

<%
//金額フォーマット
MyFormat mf = new MyFormat();

//セッションからユーザー情報を取得
Admin objAdmin = (Admin)session.getAttribute("objAdmin");
//セッション切れか確認
if(objAdmin == null){
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error","セッション切れの為、詳細画面が表示できませんでした。");
	request.setAttribute("cmd","adminlogin");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}

Order order = (Order)request.getAttribute("order");
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
                  <!-- 受注管理一覧リンク -->
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
					<td style="width:150px; background-color:#8AC75A">No</td>
					<td style="width:150px; background-color:#bce2e8"><%=order.getOrderno() %></td>
				</tr>
				<tr>
					<td style="width:150px; background-color:#8AC75A">氏名</td>
					<td style="width:150px; background-color:#bce2e8"><%=order.getUser() %></td>
				</tr>
				<tr>
					<td style="width:150px; background-color:#8AC75A">種類</td>
					<td style="width:150px; background-color:#bce2e8"><%=order.getType() %></td>
				</tr>
				<tr>
					<td style="width:150px; background-color:#8AC75A">個数</td>
					<td style="width:150px; background-color:#bce2e8"><%=order.getQuantity() %></td>
				</tr>
				<tr>
					<td style="width:150px; background-color:#8AC75A">合計金額</td>
					<td style="width:150px; background-color:#bce2e8"><%= mf.moneyFormat( order.getPrice() * order.getQuantity() ) %></td>
				</tr>
				<tr>
					<td style="width:150px; background-color:#8AC75A">発注日</td>
					<td style="width:150px; background-color:#bce2e8"><%=order.getDate() %></td>
				</tr>
				<tr>
					<td style="width:150px; background-color:#8AC75A">入金状況</td>
					<td style="width:150px; background-color:#bce2e8"><%=order.getPayment() %></td>
				</tr>
				<tr>
					<td style="width:150px; background-color:#8AC75A">発送状況</td>
					<td style="width:150px; background-color:#bce2e8"><%=order.getSend() %></td>
				</tr>
				<%
				}
				%>
			</table>
			</div>
			
			<p class="space"></p>
			
		</div>
      
      <!-- フッター -->
      <div class="push"></div>
      <%@include file= "/common/footer.jsp" %>
			
	</body>
</html>