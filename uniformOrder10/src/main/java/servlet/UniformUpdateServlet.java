/*
 * プログラム名 : ユニフォーム受注管理システム
 * プログラムの説明 : 商品更新機能の実装
 * 作成者 : 山田彩乃
 * 作成開始日 : 2024年 6月20日
 */
package servlet;

import java.io.IOException;

import bean.Uniform;
import dao.UniformDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/uniformUpdate")
public class UniformUpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エラー画面遷移用変数
		String error = "";
		String cmd = "";
		
		//初期化
		int price = 0;
		int stock = 0;

		try {

			//DAOクラスをオブジェクト化
			UniformDAO objUniformDao = new UniformDAO();
			
			//DTOクラスをオブジェクト化
			Uniform objUniform = new Uniform();

			//各種パラメータ取得
			String unino = request.getParameter("unino");
			String type = request.getParameter("type");
			String priceStr = request.getParameter("price");
			String stockStr = request.getParameter("stock");

			//エラー処理
			//入力情報抜けチェック
			if (type.equals("")) {
				//商品名未入力
				error = "商品名が入力されていないため、商品情報更新は行えませんでした。";
				cmd = "uniformList";
				return;
			}
			if (priceStr.equals("")) {
				//価格未入力
				error = "価格が入力されていないため、商品情報更新は行えませんでした。";
				cmd = "uniformList";
				return;
			}
			if (stockStr.equals("")) {
				//在庫数未入力
				error = "在庫数が入力されていないため、商品情報更新は行えませんでした。";
				cmd = "uniformList";
				return;
			}
			//該当するuninoのデータがDBに存在するかどうか
			//エラーチェック用のUniformオブジェクト生成
			Uniform uniformError = objUniformDao.selectByUnino(unino);
			if (uniformError.getUnino() == null) {
				//該当するuninoのデータが存在しない場合
				error = "該当する商品が存在しないため、商品情報更新は行えませんでした。";
				cmd = "uniformList";
				return;
			}
			
			//正常動作
			if(error.equals("")) {
				
				//priceの数値変換
				price = Integer.parseInt(priceStr);
				//stockの数値変換
				stock = Integer.parseInt(stockStr);
				
				//DTOオブジェクトに値をセットする
				objUniform.setUnino(unino);
				objUniform.setType(type);
				objUniform.setPrice(price);
				objUniform.setStock(stock);

				//更新機能呼び出し
				objUniformDao.update(objUniform);
			}

			//価格あるいは在庫数に数字以外が入力された場合
		} catch (NumberFormatException e) {

			if (stock == 0) {
				
				error = "入力された価格の値が不正なため、商品情報更新は行えませんでした。";
				cmd = "uniformList";
				return;
				
			} else {
				
				error = "入力された在庫の値が不正なため、商品情報更新は行えませんでした。";
				cmd = "uniformList";
				return;
				
			}
			
		} catch (IllegalStateException e) {
			
			//DB接続エラー
			error = "DB接続エラーのため、商品情報更新は行えませんでした。";
			cmd = "adminlogin";
			return;

		} catch (Exception e) {
			//その他エラー
			error = "予期せぬエラーが発生したため、商品情報更新は行えませんでした。";
			cmd = "adminlogin";
			return;

		} finally {

			//正常な動作
			if ( cmd.equals("") ) {
				//遷移
				request.getRequestDispatcher("/uniformList").forward(request, response);
				
			} else if( cmd.equals("uniformList") ) {
				
				//リダイレクトエラー
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/uniformUpdate.jsp").forward(request, response);
				
			} else {
				//エラー画面へ遷移
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}
	}
}
