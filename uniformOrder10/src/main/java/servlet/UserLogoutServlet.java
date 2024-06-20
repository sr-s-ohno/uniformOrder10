package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userLogout")
public class UserLogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String error = "";
		String cmd = "";

		try {

			//セッション情報をクリア
			//セッションオブジェクト生成
			HttpSession session = request.getSession();
			//セッションクリア
			session.invalidate();

			//エラー時
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、ログアウトは出来ません。";
			cmd = "logout";
			return;

		} catch (Exception e) {

			error = "予期せぬエラーが発生しました。";
			cmd = "logout";
			return;

		} finally {
			//正常
			if (error.equals("")) {
				//遷移
				request.getRequestDispatcher("/view/userLogin.jsp").forward(request, response);
				//エラー処理
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
