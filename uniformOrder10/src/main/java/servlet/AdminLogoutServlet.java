/*
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : ログアウト機能の実装
 * 作成者 : 山田彩乃
 * 作成日 : 2024年 6月20日
 * ※同日作成者本人が修正（トライキャッチつけてなかった）
 */

package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminLogout")
public class AdminLogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エラー画面遷移用変数
		String error = "";
		String cmd = "";

		try {

			//セッションオブジェクトの取得
			HttpSession session = request.getSession();
			//セッションの破棄
			session.invalidate();

		//ログイン画面もしくはエラー画面へ遷移
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、ログアウトはできません。";
			cmd = "adminlogin";
			return;

		} catch (Exception e) {

			error = "DB接続エラーの為、ログアウトはできません。";
			cmd = "adminlogin";
			return;

		} finally {

			//正常な処理
			if (error.equals("")) {
				//遷移
				request.getRequestDispatcher("/view/adminLogin.jsp").forward(request, response);
				//エラーの場合
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}
	}
}
