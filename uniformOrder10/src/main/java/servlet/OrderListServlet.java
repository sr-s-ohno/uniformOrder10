package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Admin;
import bean.Order;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

		String error = "";
		String cmd = "";
		
		try {
			
			//セッションオブジェクトの生成
			HttpSession session = request.getSession();
			//セッションからユーザー情報を取得
			Admin objAdmin = (Admin) session.getAttribute("objAdmin");

			//セッション切れか確認
			if (objAdmin == null) {
				//セッション切れならerror.jspへフォワード
				error = "セッション切れの為、受注管理一覧表示は行えません。";
				cmd = "adminlogin";
				return;
			}

			//注文情報を一覧で取得するための配列
			ArrayList<Order> orderList;
			
			//DAOオブジェクト生成
			OrderDAO objOrderDao = new OrderDAO();
			
			//受注管理一覧を配列で取得
			orderList = objOrderDao.selectAll();

			//セッションスコープに登録
			request.setAttribute("order_list", orderList);

		}catch (IllegalStateException e) {
			
			error ="DB接続エラーの為、登録できませんでした。";
			cmd = "adminlogin";
			return;
			
		} catch (Exception e) {

			error = "予期せぬエラーが発生しました。";
			cmd = "adminlogin";
			return;

		}finally{
			
			//正常動作
			if(error.equals("")) {
				
				request.getRequestDispatcher("/view/orderList.jsp").forward(request, response);
				
			}else {
				
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
