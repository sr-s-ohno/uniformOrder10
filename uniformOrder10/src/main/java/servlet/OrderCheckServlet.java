//最新盤
package servlet;

import java.io.IOException;

import bean.Order;
import bean.Uniform;
import bean.User;
import dao.OrderDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderCheck")

public class OrderCheckServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エラーメッセージ,cmdの変数
		String error = "";
		String cmd = "";

		try {

			//Order,uniform,userのセッションスコープを取得する
			HttpSession session = request.getSession();
			Order order = (Order) session.getAttribute("order");
			User objUser = (User) session.getAttribute("objUser");
			Uniform uniform = (Uniform)session.getAttribute("uniform");
			
			//テスト用
			//String user = objUser.getUser();
			//String unino = uniform.getUnino();
			
			//order.setUser(user);
			//order.setUnino(unino);
			
			//会員権限のチェック
			String member = null;
			member = objUser.getMember();
			
			if(member.equals("2")) {
				
				//ユーザー情報使用
				String user = objUser.getUser();
				order.setUser(user);
				//ユーザー情報強制登録
				UserDAO objUserDao = new UserDAO();
				objUserDao.insert(objUser);
				//uninoをセット
				String unino = uniform.getUnino();
				order.setUnino(unino);
			}
			
		
			//orderを用いてinsertメソッドに登録
			OrderDAO objOrderDao = new OrderDAO();
			objOrderDao.insert(order);

			//DB接続エラー
		} catch (IllegalStateException e) {
			
			//エラーメッセージの登録
			error = "DB接続エラーの為、注文できませんでした。";
			//cmdの登録
			cmd = "userlogin";

		} finally {

			//エラーの有無でフォワード先を呼び別ける。
			if (error.equals("")) {
				
				request.getRequestDispatcher("/orderSendMail").forward(request, response);
				
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}
	}

}
