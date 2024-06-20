/*
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : ログイン機能の実装
 * 作成者 : 山田彩乃
 * 作成日 : 2024年 6月20日
 */

package servlet;

import java.io.IOException;

import bean.Admin;
import dao.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//ログイン画面の実装
//サーブレットアノテーション
@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//エラー画面遷移用変数
		String error = "";
		String cmd = "";

		try {

			//入力パラメータ取得
			String adminId = request.getParameter("admin");
			String password = request.getParameter("password");

			//オブジェクト宣言
			//DAOクラスオブジェクト化
			AdminDAO objAdminDao = new AdminDAO();

			//DTOクラスから
			Admin objAdmin = objAdminDao.selectByAdmin(adminId, password);

			//AdminID情報の有無
			//Admin情報がある場合
			if (objAdmin.getAdmin() != null) {

				//セッションオブジェクトの生成
				HttpSession session = request.getSession();
				//検索結果をセッションスコープに登録
				session.setAttribute("objAdmin", objAdmin);

				//クッキーに登録(5日間)
				//useridのクッキー
				Cookie adminIdCookie = new Cookie("adminId", adminId);
				adminIdCookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(adminIdCookie);
				//passwordのクッキー
				Cookie passwordCookie = new Cookie("password", password);
				passwordCookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(passwordCookie);
				;

				//Admin情報がない場合
			} else {
				error = "入力データが間違っています！";
				return;
			}

			//エラー時
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、ログインは出来ません。";
			cmd = "adminlogin";
			return;

		} catch (Exception e) {

			error = "予期せぬエラーが発生しました。";
			cmd = "adminlogin";
			return;

			//フォワード処理
		} finally {
			//正常
			if (error.equals("")) {

				//遷移
				request.getRequestDispatcher("/view/adminMenu.jsp").forward(request, response);

				//ログイン失敗
			} else if (error.equals("入力データが間違っています！")) {

				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/adminLogin.jsp").forward(request, response);

				//エラー処理
			} else {

				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}
	}
}