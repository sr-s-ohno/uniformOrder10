//最新盤
package servlet;

import java.io.IOException;

import bean.Order;
import bean.Uniform;
import bean.User;
import dao.UniformDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderInsert")

public class OrderInsertServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//変数の宣言
		String error = "";
		String cmd = "";
		String user = "";
		String mail = "";
		String address = "";
		HttpSession session = request.getSession();

		String member = null;

		try {

			//Userのセッションを取得
			User objUser = (User) session.getAttribute("objUser");
			if (objUser != null) {
				member = objUser.getMember();
			}

			//購入された情報のパラメータを取得する
			if (member.equals("1")) {
				
				user = objUser.getUser();
				mail = objUser.getMail();
				address = objUser.getAddress();

			} else {

				user = request.getParameter("user");
				mail = request.getParameter("mail");
				address = request.getParameter("address");

			}

			//パラメータ取得
			String unino = request.getParameter("type");
			String strquantity = request.getParameter("quantity");
			String text = request.getParameter("text");

			if (text == null) {
				text = "";
			}

			//値のチェックを行う
			if (user.equals("")) {

				error = "氏名が未入力のため、登録できません";
				cmd = "orderInsert";
				return;

			} else if (user.equals("")) {

				error = "メールアドレスが未入力のため、登録できません";
				cmd = "orderInsert";
				return;

			} else if (address.equals("")) {

				error = "住所が未入力のため、登録できません";
				cmd = "orderInsert";
				return;

			} else if (unino.equals("")) {

				error = "商品が選択されていないため、登録できません";
				cmd = "orderInsert";
				return;

			} else if (strquantity.equals("")) {

				error = "個数が未入力のため、登録できません";
				cmd = "orderInsert";
				return;

			}

			//正常動作
			if (error.equals("")) {

				int quantity = Integer.parseInt(strquantity);

				//セッションスコープに登録
				objUser.setUser(user);
				objUser.setMail(mail);
				objUser.setAddress(address);
				objUser.setMember(member);
				session.setAttribute("objUser", objUser);

				//UniformDAOのオブジェクトを生成し、関連メソッドを呼び出す
				UniformDAO objUniDao = new UniformDAO();
				Uniform uniform = objUniDao.selectByUnino(unino);

				//Uniformオブジェクトをリクエストスコープに登録
				session.setAttribute("uniform", uniform);

				//Orderオブジェクトの生成
				Order order = new Order();

				//orderに値をセットする
				order.setUnino(unino);
				order.setUser(user);
				order.setType(uniform.getType());
				order.setQuantity(quantity);
				order.setPrice(uniform.getPrice());
				order.setPayment("入金待ち");
				order.setSend("未");
				order.setText(text);

				//セッションスコープに登録
				session.setAttribute("order", order);

				session.setAttribute("quantity", quantity);
			}

			//DB接続エラー
		} catch (IllegalStateException e) {
			//エラーメッセージの登録
			error = "DB接続エラーの為、注文を登録出来ません。";
			//cmdの登録
			cmd = "userlogin";

		} finally {

			//エラーの有無でフォワード先を呼び別ける。
			if (cmd.equals("")) {

				request.getRequestDispatcher("/view/orderCheck.jsp").forward(request, response);

			} else if (cmd.equals("orderInsert")) {

				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/orderInsert.jsp").forward(request, response);

			} else {

				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}
	}

}
