package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Uniform;
import dao.UniformDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/uniformList")
public class UniformListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			//DAOオブジェクト生成
			UniformDAO uniformDao = new UniformDAO();

			//selectAllメソッドの戻り値をArrayListに格納
			ArrayList<Uniform> list = uniformDao.selectAll();

			//リクエストスコープに登録
			request.setAttribute("uniform_list", list);

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "adminlogin";
			return;

		} catch (Exception e) {
			
			error = "予期せぬエラーが発生しました。";
			cmd = "adminlogin";
			return;

		} finally {
			//条件よってフォワード先を指定
			if ( error.equals("") ) {
				
				request.getRequestDispatcher("/view/uniformList.jsp").forward(request, response);
				
			} else {
				
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}
