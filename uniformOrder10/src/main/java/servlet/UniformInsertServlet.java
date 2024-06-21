package servlet;

import java.io.IOException;

import bean.Uniform;
import dao.UniformDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/uniformInsert")
public class UniformInsertServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";
		int intPrice = 0;
		int intStock = 0;

		try {
			//DAOオブジェクト生成
			UniformDAO uniformDao = new UniformDAO();

			//DTOオブジェクト生成
			Uniform uniform = new Uniform();

			//文字エンコーディングの指定
			request.setCharacterEncoding("UTF-8");

			//パラメータの取得
			String unino = request.getParameter("unino");
			String type = request.getParameter("type");
			String price = request.getParameter("price");
			String stock = request.getParameter("stock");

			//IDが重複
			if (uniformDao.selectByUnino(unino).getUnino() != null) {
				error = "入力IDは既に登録済みの為、書籍登録処理は行えませんでした。";
				cmd = "uniformInsert";
				return;
			}
			//未入力
			if (unino.equals("")) {
				error = "IDが未入力の為、登録処理は行えませんでした。";
				cmd = "uniformInsert";
				return;
			}
			if (type.equals("")) {
				error = "商品名が未入力の為、登録処理は行えませんでした。";
				cmd = "uniformInsert";
				return;
			}
			if (price.equals("")) {
				error = "価格が未入力の為、登録処理は行えませんでした。";
				cmd = "uniformInsert";
				return;
			}
			if (stock.equals("")) {
				error = "在庫数が未入力の為、登録処理は行えませんでした。";
				cmd = "uniformInsert";
				return;
			}

			//正常動作
			if (error.equals("")) {
				//int型に変換
				intPrice = Integer.parseInt(price);
				intStock = Integer.parseInt(stock);

				//取得した情報をuniformにセット
				uniform.setUnino(unino);
				uniform.setType(type);
				uniform.setPrice(intPrice);
				uniform.setStock(intStock);

				//データ登録
				uniformDao.insert(uniform);
			}

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、商品登録処理は行えませんでした。";
			cmd = "adminlogin";
			return;

		} catch (NumberFormatException e) {

			if (intStock == 0 && intPrice == 0) {

				error = "価格、在庫の値が不正の為、商品登録処理は行えませんでした。";
				cmd = "uniformInsert";

			} else if (intStock == 0 && intPrice != 0) {

				error = "在庫の値が不正の為、商品登録処理は行えませんでした。";
				cmd = "uniformInsert";

			} else {

				error = "価格の値が不正の為、商品登録処理は行えませんでした。";
				cmd = "unifromInsert";

			}

		} finally {
			if ( cmd.equals("") ) {
				
				request.getRequestDispatcher("/uniformList").forward(request, response);
				
			} else if( cmd.equals("uniformInsert") ) {
				
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/unifromInsert.jsp").forward(request, response);
				
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
