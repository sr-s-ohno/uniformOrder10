package servlet;
import java.io.IOException;
import java.util.ArrayList;

import bean.Order;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * 書籍管理システムにおける書籍一覧機能に関する処理をおこなうサーブレットクラス
 *
 * @author KandaITSchool
 *
 *
 */
@WebServlet("/orderList")
public class OrderListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;
		
		try {

			OrderDAO objDao = new OrderDAO();
			
			cmd = "menu";

			
			ArrayList<Order> OrderList = objDao.selectAll();

			
			request.setAttribute("order_list", OrderList);

			
		}catch (IllegalStateException e) {
			error ="DB接続エラーの為、登録できませんでした。";
			request.setAttribute("cmd",cmd);
			return;

		}finally{
			if(error == null) {
				request.getRequestDispatcher("/OrderList").forward(request, response);
			}else {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
