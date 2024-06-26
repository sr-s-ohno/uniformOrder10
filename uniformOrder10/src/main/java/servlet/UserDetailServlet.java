package servlet;

import java.io.IOException;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userDetail")
public class UserDetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cmd = "";
		String cmdError = "";
		String error = "";

		try {

			//UserDAOオブジェクト化
			UserDAO objUserDao = new UserDAO();

			//パラメータ取得
			String user = request.getParameter("user");
			cmd = request.getParameter("cmd");

			//メソッド実行で、userのデータ取得
			User objUser = objUserDao.selectByUser(user);

			request.setAttribute("objUser", objUser);

		} catch (IllegalStateException e) {

			cmdError = "userlogin";

			switch (cmd) {
			//詳細表示
			case "detail":
				error = "DB接続エラーの為、詳細表示は出来ませんでした。";
				break;
			//更新処理
			default:
				error = "DB接続エラーの為、変更画面は表示出来ませんでした。";
				break;
			}
		} finally {
			if (error.equals("")) {

				if (cmd.equals("update")) {

					request.getRequestDispatcher("/view/userUpdate.jsp").forward(request, response);

				} else {

					request.getRequestDispatcher("/view/userDetail.jsp").forward(request, response);

				}

			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmdError);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
