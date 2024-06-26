package servlet;

import java.io.IOException;

import bean.Order;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderDetail")
public class OrderDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cmd = "";
		String cmdError = "";
		String error = "";

		try {

			//orderDAOオブジェクト化
			OrderDAO objOrderDao = new OrderDAO();

			//パラメータ取得
			int orderno = Integer.parseInt(request.getParameter("orderno"));
			cmd = request.getParameter("cmd");

			//メソッド実行で、ordernoのデータ取得
			Order order = objOrderDao.selectByOrderno(orderno);

			request.setAttribute("order", order);

		} catch (IllegalStateException e) {

			cmdError = "adminlogin";

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
				
				if (cmd.equals("detail")) {
					
					request.getRequestDispatcher("/view/orderDetail.jsp").forward(request, response);
					
				} else {
					
					request.getRequestDispatcher("/view/orderUpdate.jsp").forward(request, response);
					
				}
				
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmdError);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
