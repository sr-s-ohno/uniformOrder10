package servlet;

import java.io.IOException;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userUpdate")
public class UserUpdateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//変数宣言
		String cmd = "";
		String error = "";

		try {

			//セッションオブジェクトの生成
			HttpSession session = request.getSession();
			//セッションからユーザー情報を取得
			User objUser = (User) session.getAttribute("objUser");

			//セッション切れか確認
			if (objUser == null) {
				//セッション切れならerror.jspへフォワード
				error = "セッション切れの為、登録情報を変更できません。";
				cmd = "userlogin";
				return;
			}

			UserDAO objUserDao = new UserDAO();

			String user = request.getParameter("user");
			String mail = request.getParameter("mail");
			String address = request.getParameter("address");

			//エラー処理
			if (mail.equals("")) {
				//価格未入力
				error = "アドレスが入力されていないため、登録情報更新は行えませんでした。";
				cmd = "user";
				return;
			}
			if (address.equals("")) {
				//在庫数未入力
				error = "住所が入力されていないため、登録情報更新は行えませんでした。";
				cmd = "user";
				return;
			}
			//該当するuninoのデータがDBに存在するかどうか
			//エラーチェック用のUniformオブジェクト生成
			User userError = objUserDao.selectByUser(user);
			if (userError.getUser() == null) {
				//該当するuserのデータが存在しない場合
				error = "該当するIDが存在しないため、登録情報更新は行えませんでした。";
				cmd = "userlogin";
				return;
			}

			//正常動作
			if (error.equals("")) {

				//入力された情報を代入
				objUser.setUser(user);
				objUser.setMail(mail);
				objUser.setAddress(address);
				objUserDao.update(objUser);

			}

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "userlogin";

		} finally {
			if ("".equals(cmd)) {
				//Menuにフォワード
				request.getRequestDispatcher("/view/userMenu.jsp").forward(request, response);
			} else if ("user".equals(cmd)) {
				//userUpdateにフォワード
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/userUpdate.jsp").forward(request, response);
			} else {
				//errorにフォワード
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
