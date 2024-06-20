<!-- 
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : ユーザー登録の実装
 * 作成者 : 大野隼大
 * 作成日 : 2024年 6月20日
 -->

<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession, bean.User"%>

<%
String error = (String)request.getAttribute("error");
if(error == null){
	error = "";
}

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
         
		 <h2>会員登録</h2>
         <hr style="height:3; background-color:#008000" />
         <p class="space2"></p>
          
         <!-- 変更入力フォーム -->
         <form action="<%= request.getContextPath() %>/userInsert">
             <!-- テーブル作成 -->
	         <table style="margin:0 auto">
	         	    
	            <tr>
	               <td style="background-color: #a9a9a9; width:200">氏名（ID）</td>
	               <td style="width:150"><input type="text" size="30" name="user"></td>
	            </tr>
	            
	            <tr>
	               <td style="background-color: #a9a9a9; width:200">メールアドレス</td>
	               <td style="width:150"><input type="text" size="30" name="mail"></td>
	            </tr>
	            
	            <tr>
	               <td style="background-color: #a9a9a9; width:200">住所</td>
	               <td style="width:150"><input type="text" size="30" name="address"></td>
	            </tr>
	               
	            <tr>
	               <td style="background-color: #a9a9a9; width:200">パスワード</td>
	               <td style="width:150"><input type="password" size="30" name="pass"></td>
	            </tr>
	            
	            <tr>
	               <td style="background-color: #a9a9a9; width:200">新パスワード(確認用)</td>
	               <td style="width:150"><input type="password" size="30" name="passCheck"></td>
	            </tr>

	         </table>
	         <p class="space2"></p>
	         
	         <input type="submit" value="登録">
	         <p class="space"></p>
	         
         </form>
      </div>
      
      <p class="space"></p>
      <p style="color: ff0000; text-align: center"><%= error %></p>
      
      <!-- フッター -->
      <div class="push"></div>
      <%@include file= "/common/footer.jsp" %>
      
   </body>
</html>
