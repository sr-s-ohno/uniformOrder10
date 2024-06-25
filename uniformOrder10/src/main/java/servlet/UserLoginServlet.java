package servlet;

import java.io.IOException;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String error = "";
		String cmd = "";

		try {

			//入力パラメータ取得
			String user = request.getParameter("user");
			String password = request.getParameter("password");

			//オブジェクト宣言
			UserDAO userDaoObj = new UserDAO();

			//user passwordと同じuserデータを検索
			User objUser = userDaoObj.selectByUser(user, password);

			//User情報の有無
			if (objUser.getUser() != null) {

				//セッションオブジェクトの生成
				HttpSession session = request.getSession();
				//検索結果をセッションスコープに登録
				session.setAttribute("objUser", objUser);

				//クッキーに登録(5日間)
				//useridのクッキー
				Cookie userCookie = new Cookie("user", user);
				userCookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(userCookie);
				//passwordのクッキー
				Cookie passwordCookie = new Cookie("password", password);
				passwordCookie.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(passwordCookie);

				//User情報がない場合
			} else {
				error = "入力データが間違っています！";
				return;
			}

			//エラー時
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、ログインは出来ません。";
			cmd = "userlogin";
			return;

		} catch (Exception e) {

			error = "予期せぬエラーが発生しました。";
			cmd = "userlogin";
			return;

			//フォワード処理
		} finally {
			//正常
			if (error.equals("")) {

				//遷移
				request.getRequestDispatcher("/view/userMenu.jsp").forward(request, response);

				//ログイン失敗
			} else if (error.equals("入力データが間違っています！")) {

				request.setAttribute("error", error);
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