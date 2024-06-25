package servlet;

import java.io.IOException;

import bean.Admin;
import bean.User;
import dao.OrderDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.SendMail;

@WebServlet("/orderUpdate")
public class OrderUpdateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//変数宣言
		String cmd = "";
		String error = "";

		try {

			//セッションオブジェクトの生成
			HttpSession session = request.getSession();
			//セッションからユーザー情報を取得
			Admin objAdmin = (Admin) session.getAttribute("objAdmin");

			//セッション切れか確認
			if (objAdmin == null) {
				//セッション切れならerror.jspへフォワード
				error = "セッション切れの為、入金・発送状況変更は行えません。";
				cmd = "adminlogin";
				return;
			}

			//入力された値を取得
			String payment = request.getParameter("payment");
			String send = request.getParameter("send");
			String ordernoStr = request.getParameter("orderno");
			String user = request.getParameter("user");
			int orderno = Integer.parseInt(ordernoStr);
			
			//オブジェクト化
			UserDAO objUserDao = new UserDAO();
			OrderDAO objOrderDao = new OrderDAO();
			
			//ユーザーの名前、アドレスを取得
			User objUser = objUserDao.selectByUser(user);
			
			//ステータス更新
			objOrderDao.update(payment, send, orderno);

			//メール送信判別
			if ("入金済み".equals(payment) && "発送済み".equals(send)) {
				//メール開始
				//メール本文
				StringBuilder text = new StringBuilder();
				text.append(objUser.getUser() + "様\n\n");
				text.append("この度は(神田ITユニフォーム)でご注文いただき、ありがとうございました。\n\n");
				text.append("商品の発送が完了いたしました。\n");
				text.append("商品が到着しましたら、お早めに内容をご確認下さい。\n\n");

				text.append("※このメールはサーバーからの自動送信メールです。\n");
				text.append("このメールにご返信頂いてもお応え出来かねますのでご了承下さい。\n\n");

				//メールオブジェクト生成
				SendMail sendMail = new SendMail();
				//メール本文
				sendMail.setText(text.toString());
				//メールタイトル
				sendMail.setTitle("発送完了のお知らせ");
				//メールアドレス
				sendMail.setAdress(objUser.getMail());
				//メール送信
				sendMail.createMail();

				//リクエストスコープ
				request.setAttribute("message", "メールを送信しました。");

			} else if ("入金済み".equals(payment)) {
				//メール開始
				//メール本文
				StringBuilder text = new StringBuilder();
				text.append(objUser.getUser() + "様\n\n");
				text.append("この度は(神田ITユニフォーム)でお買い物いただき、ありがとうございました。\n\n");
				text.append("料金を銀行振込決済より領収いたしました。\n");
				text.append("発送出来ましたらまたご連絡いたします。\n\n");

				text.append("※このメールはサーバーからの自動送信メールです。\n");
				text.append("このメールにご返信頂いてもお応え出来かねますのでご了承下さい。\n\n");
				//メールオブジェクト生成
				SendMail sendMail = new SendMail();
				//メール本文
				sendMail.setText(text.toString());
				//メールタイトル
				sendMail.setTitle("入金確認のお知らせ");
				//メールアドレス
				sendMail.setAdress(objUser.getMail());
				//メール送信
				sendMail.createMail();

				//リクエストスコープ
				request.setAttribute("message", "メールを送信しました。");
			}

		} catch (IllegalStateException e) {

			cmd = "adminlogin";
			error = "DB接続エラーの為、一覧表示は行えませんでした。";

		} finally {
			if ("".equals(cmd)) {

				//orderListにフォワード
				request.getRequestDispatcher("/orderList").forward(request, response);

			} else {
				//リクエストスコープ
				request.setAttribute("error", error);
				//リクエストスコープ
				request.setAttribute("cmd", cmd);
				//errorにフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}
	}
}
