package servlet;

import java.io.IOException;

import bean.Uniform;
import dao.UniformDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/uniformDelete")
public class UniformDeleteServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		
		String error = "";
		String cmd = "";
		
		try {
			//パラメータの取得
			String unino = request.getParameter("unino");
			
			//DAOオブジェクト生成
			UniformDAO objUniformDao = new UniformDAO();
			
			//Uniform(DTO)オブジェクト生成
			//入力したuninoが存在するか
			Uniform objUniform = objUniformDao.selectByUnino(unino);
			
			//取得したuniformオブジェクトがnullなら存在しない
			if (objUniform.getUnino() != null) {
				//正常動作
				//削除機能
				objUniformDao.delete(unino);
				
				//エラー処理
			} else {
				error = "削除対象の商品が存在しない為、削除処理は行えませんでした。";
				cmd = "menu";
				return;
			}
			
		}catch(IllegalStateException e) {
			
			error = "DB接続エラーの為、商品削除処理は行えませんでした。";
			cmd = "adminlogin";
			return;
			
		}catch(Exception e) {
			
			error = "予期せぬエラーが発生しました。";
			cmd = "adminlogin";
			
		}finally {
			if ( error.equals("") ) {
				
				request.getRequestDispatcher("/uniformList").forward(request, response);
				
			} else {
				
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				
			}
		}
	}

}
