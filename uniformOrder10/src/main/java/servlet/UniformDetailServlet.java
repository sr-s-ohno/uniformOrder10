package servlet;

import java.io.IOException;

import bean.Uniform;
import dao.UniformDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/uniformDetail")
public class UniformDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cmd = "";
		String error = "";

		try {

			//orderDAOオブジェクト化
			UniformDAO objUniformDao = new UniformDAO();

			//パラメータ取得
			String unino = request.getParameter("unino");
			cmd = request.getParameter("cmd");

			//メソッド実行で、ordernoのデータ取得
			Uniform objUniform = objUniformDao.selectByUnino(unino);

			request.setAttribute("objUniform", objUniform);

		} catch (IllegalStateException e) {

			    cmd = "adminlogin";
				error = "DB接続エラーの為、変更画面は表示出来ませんでした。";
				return;

		} finally {
			if (error.equals("")) {
				
					request.getRequestDispatcher("/view/uniformUpdate.jsp").forward(request, response);
				
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
}}
