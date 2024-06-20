/*
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : 注文完了 メール送信
 * 作成者 : 大野隼大
 * 作成日 : 2024年 6月20日
 */

package servlet;

import java.io.IOException;

import bean.Order;
import bean.User;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.SendMail;

@WebServlet("/orderSendMail")
public class OrderSendMailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {

			//セッションオブジェクトの生成
			HttpSession session = request.getSession();

			//セッションからユーザー情報を取得
			User user = (User) session.getAttribute("user");
			//セッションから注文情報を取得
			Order order = (Order) session.getAttribute("order");

			//DAOオブジェクト宣言
			OrderDAO objOrderDao = new OrderDAO();

			//合計
			int total = 0;

			//メール本文
			StringBuilder text = new StringBuilder();
			text.append(user.getUser() + "様\n");
			text.append("\nユニフォームのご注文ありがとうございます。\n");
			text.append("以下内容でご注文を受け付けましたので。ご連絡致します。\n\n");


			/* メール機能 */
			//氏名 ID を追加
			text.append("氏名 : " + user.getUser() + " 様\n");
			//メールアドレスを追加
			text.append("メールアドレス : " + user.getAddress() + "\n");
			//商品名を追加
			text.append("商品 : " + order.getType() + "\n");
			//購入数を追加
			text.append("購入数 : " + order.getQuantity() + "個\n");
			//合計計算
			//total += order.getPrice() * order.getQuantity();

			//合計金額表示
			text.append("合計 : " + total + "円\n");
			//発注日を追加
			text.append("発注日 : " + order.getDate() + "\n");
			text.append("\nまたのご利用よろしくお願いします。");
			
			//メールオブジェクト生成
			SendMail sendMail = new SendMail();
			//メール本文
			sendMail.setText(text.toString());
			//メールタイトル
			sendMail.setTitle("注文完了のお知らせ");
			//メールアドレス
			sendMail.setAdress(user.getMail());
			//メール送信
			sendMail.createMail();

			//セッションのorder_listをクリア
			session.setAttribute("order_list", null);

			//エラー時
		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、注文は出来ません。";
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
				request.getRequestDispatcher("/view/orderSendMail.jsp").forward(request, response);

				//エラー処理
			} else {

				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}
	}
}
