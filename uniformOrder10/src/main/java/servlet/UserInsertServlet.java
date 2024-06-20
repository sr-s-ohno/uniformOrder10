/*
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : ユーザー登録機能
 * 作成者 : 大野隼大
 * 作成日 : 2024年 6月20日
 */

package servlet;

import java.io.IOException;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userInsert")
public class UserInsertServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {

			//オブジェクト宣言
			UserDAO userDaoObj = new UserDAO();

			//パラメータの取得
			String user = request.getParameter("user");
			String mail = request.getParameter("mail");
			String address = request.getParameter("address");
			String pass = request.getParameter("pass");
			String passCheck = request.getParameter("passCheck");

			//エラー処理
			//空文字チェック
			if (user.equals("")) {
				//ユーザーが空
				error = "ユーザー入力値不正の為、登録できません。";
				cmd = "direct";
				return;
			}
			if (mail.equals("")) {
				//Eメールが空
				error = "メール入力値不正の為、登録できません。";
				cmd = "direct";
				return;
			}
			if (address.equals("") ) {
				//住所が空
				error = "住所が入力値不正の為、登録できません。";
				cmd = "direct";
				return;
			}
			if (pass.equals("")) {
				//パスワードが空
				error = "パスワード入力値不正の為、登録できません。";
				cmd = "direct";
				return;
			}
			if (passCheck.equals("")) {
				//パスワード確認用が空
				error = "パスワード（確認用）入力値不正の為、登録できません。";
				cmd = "direct";
				return;
			}

			//新と確認パスワードが一致するか
			if (!(pass.equals(passCheck))) {
				//パスワードと確認パスワードが一致しない
				error = "入力パスワードがパスワード(確認用)と一致しない為、登録できません。";
				cmd = "direct";
				return;
			}

			//ユーザ名が登録済み
			User userError = userDaoObj.selectByUser(user);
			if (userError.getUser() != null) {
				//登録済みのユーザー名
				error = "入力ユーザー名は既に使用済みの為、登録できません。";
				cmd = "direct";
				return;
			}

			//正常動作
			if (error.equals("")) {
				
				//会員登録の際にフラグを設定
				String member = "1";

				//User(DTO)オブジェクト生成
				User objUser = new User();
				
				//取得パラメータを設定
				objUser.setUser(user);
				objUser.setMail(mail);
				objUser.setAddress(address);
				objUser.setPassword(pass);
				objUser.setMember(member);

				//登録処理
				int count = userDaoObj.insert(objUser);

				//SQL文の発行チェック
				if (count == 0) {
					//クエリ発行に失敗
					error = "クエリ発行に失敗しました。";
					cmd = "direct";
					return;
				}
				
				//登録完了確認のため
				request.setAttribute( "member", objUser.getMember() );
				request.setAttribute( "count", count );
			}

			//エラー時
		} catch (IllegalStateException e) {
			
			
			error = "DB接続エラーの為、ユーザ登録は行えません。";
			//ユーザーログインへ遷移予定
			cmd = "login";
			return;

		} catch (Exception e) {

			error = "予期せぬエラーが発生しました。";
			//ユーザーログインへ遷移予定
			cmd = "login";
			return;

			//フォワード処理
		} finally {
			//正常
			if (cmd.equals("")) {
				//遷移
				request.getRequestDispatcher("/view/userCheck.jsp").forward(request, response);
				
				//入力間違いの場合は登録ページへ戻る
			} else if(cmd.equals("direct") ) {
				
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/userInsert.jsp").forward(request, response);

				//エラー処理
			} else {

				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}
	}

}
