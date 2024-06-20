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

		String cmdError = "";
		String error = "";

		try {
			OrderDAO objOrderDao = new OrderDAO();
			
			Order order = new Order();
			
			int orderno = Integer.parseInt(request.getParameter("orderno"));
			String cmd = request.getParameter("cmd");
			
			order = objOrderDao.selectByOrderno(orderno);
			
			request.setAttribute("order", order);
			
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmdError = "menu";
		} finally {
			if (error == "") {
				if (cmdError.equals("detail")) {
					request.getRequestDispatcher("/view/orderDetail.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmdError);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
