<!-- 
/*
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : 会員登録確認の実装
 * 作成者 : 大野隼大
 * 作成日 : 2024年 6月24日
 */
 -->
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User"%>

<%
//セッションからユーザー情報を取得
User objUser = (User)session.getAttribute("objUser");
//セッション切れか確認
if(objUser == null){
	//セッション切れならerror.jspへフォワード
	request.setAttribute("error","セッション切れの為、注文完了画面が表示できませんでした。");
	request.setAttribute("cmd","userlogin");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}

String message = (String) request.getAttribute("message");
if(message == null){
	message = "";
}
%>

<html>
   <head>
      <title>受注管理システム</title>
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
   </head>
   
   <body>
      <div style="text-align:center">
         
         <!-- メニューデザイン -->
         <!-- ヘッダー -->
         <%@include file= "/common/header.jsp" %>
         <p class="space2"></p>
         
		 <h2>注文完了</h2>
		 <hr style="height:3; background-color:#008000" />
         <p class="space2"></p>
         
         <!-- 報告 -->
         <p class="space2"></p>
         <h3>注文が完了しました。</h3>
         
         <p class="space"></p>
         <h3 style="color: 0000ff;"><%= message %></h3>
         
         <p class="space2"></p>
         
         <!-- 遷移 -->
         <!-- ユーザーログインリンク -->
	     <A href="<%= request.getContextPath() %>/view/userLogin.jsp">【ログイン画面へ】</A>
	     <!-- 注文登録リンク -->
	     <A href="<%= request.getContextPath() %>/view/orderInsert.jsp">【注文登録へ戻る】</A>
	     
      </div>
      
      <div class="push"></div>
      <!-- フッター -->
      <%@include file= "/common/footer.jsp" %>
      
   </body>
</html>