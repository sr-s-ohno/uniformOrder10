<!-- 
/*
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : 会員登録確認の実装
 * 作成者 : 大野隼大
 * 作成日 : 2024年 6月20日
 */
 -->

<%@page contentType="text/html; charset=UTF-8"%>

<%
String member = (String)request.getAttribute("member");
int count = (int)request.getAttribute("count");
%>

<html>
   <head>
      <title>ユニフォーム受注管理システム</title>
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
			         <!-- ユーザーログインリンク -->
	                 <A href="<%= request.getContextPath() %>/view/userLogin.jsp">
	                 【ログイン画面】</A>
	              </ul>
               </div>
            </div>
         </div>
         
		 <h2>会員登録完了</h2>
		 <hr style="height:3; background-color:#0095d9" />
         <p class="space2"></p>
         
         <!-- 報告 -->
         <p class="space2"></p>
         <%
            if( count > 0 ) {
         %>
            <h3 style="color: #a52a2a;">登録が完了しました。</h3>
         <%
            } else {
         %>
            <h3 style="color: #a52a2a;">登録が出来ていません。</h3>
         <%
            }
         %>
     </div>
      
     <!-- フッター -->
     <div class="push"></div>
     <%@include file= "/common/userFooter.jsp" %>
      
   </body>
</html>