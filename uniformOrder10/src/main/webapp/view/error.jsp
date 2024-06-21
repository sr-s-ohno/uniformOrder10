<%@page contentType="text/html; charset=UTF-8"%>

<%
String cmd = (String)request.getAttribute("cmd");
String error = (String)request.getAttribute("error");
%>

<html>
   <head>
      <title>Error</title>
      <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
   </head>
   
   <body>
      <div style="text-align:center">
         
         <!-- メニューデザイン -->
         <!-- ヘッダー -->
         <%@include file= "/common/header.jsp" %>
         
		 <h2>■■エラー■■</h2>
         <p class="space2"></p>
         
         <!-- エラー文表示 -->
         <p class="space2"><%= error %></p>
         
          
         <!-- 戻り先の指定 -->
         <%
            if( cmd.equals("adminlogin") ) {
         %>
         <!-- メニューリンク -->
	     <A href="<%= request.getContextPath() %>/adminLogout">
	     【ログイン画面に戻る】</A>
	     <p class="space"></p>
	     
	     <%
            }else if( cmd.equals("userlogin") ){
         %>
         <!-- メニューリンク -->
	     <A href="<%= request.getContextPath() %>/view/userLogin.jsp">
	     【ログイン画面に戻る】</A>
	     <p class="space"></p>
	     
         <%
            } else if( cmd.equals("menu") ) {
         %>
         <!-- 一覧表示リンク -->
         <A href="<%= request.getContextPath() %>/view/adminMenu.jsp">
         【メニューに戻る】</A>
         <p class="space"></p>
         
        <%
           }
        %>
        
      </div>
      
      <div class="push"></div>
      <!-- フッター -->
      <%@include file= "/common/footer.jsp" %>
      
   </body>
</html>