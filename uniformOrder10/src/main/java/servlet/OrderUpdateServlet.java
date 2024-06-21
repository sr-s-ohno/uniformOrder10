package servlet;

import java.io.IOException;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderUpdate")
public class OrderUpdateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//変数宣言
		String cmd = "";
		String error = "";

		try {

			//入力された値を取得
			String payment = request.getParameter("payment");
			String send = request.getParameter("send");
			String ordernoStr = request.getParameter("orderno");
			
			int orderno = Integer.parseInt(ordernoStr);
			//オブジェクト化
			OrderDAO objOrderDao = new OrderDAO();
			objOrderDao.update(payment, send, orderno);

		} catch (IllegalStateException e) {
			cmd = "logout";
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			//リクエストスコープ
			request.setAttribute("cmd", cmd);
		} finally {
			if ("".equals(cmd)) {
				//orderListにフォワード
				request.getRequestDispatcher("/orderList").forward(request, response);
			} else {
				//リクエストスコープ
				request.setAttribute("error", error);
				//errorにフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}
	}
}
