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
      <title>受注管理システム</title>
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
			         <!-- ユーザーログインリンク -->
	                 <A href="<%= request.getContextPath() %>/view/userLogin.jsp">
	                 【ログイン画面】</A>
	              </ul>
               </div>
            </div>
         </div>
         
		 <h2>会員登録完了</h2>
		 <hr style="height:3; background-color:#008000" />
         <p class="space2"></p>
         
         <!-- 報告 -->
         <p class="space2"></p>
         <%
            if( count > 0 ) {
         %>
            <h3 style="color: ff0000;">登録が完了しました。</h3>
         <%
            } else {
         %>
            <h3 style="color: 0000ff;">登録が出来ていません。</h3>
         <%
            }
         %>
      </div>
      
      <div class="push"></div>
      <!-- フッター -->
      <%@include file= "/common/footer.jsp" %>
      
   </body>
</html>