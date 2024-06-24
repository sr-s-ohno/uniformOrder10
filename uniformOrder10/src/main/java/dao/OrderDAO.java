package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Order;

public class OrderDAO {

	//データベース接続情報
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/uniformdb";
	private static String USER = "root";
	private static String PASS = "root123";

	//データベース接続を行うメソッド
	private static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	//DBのorderinfoとuniforminfoテーブルを結合して購入情報を取得するメソッド
	public ArrayList<Order> selectAll() {

		//変数宣言
		Connection con = null;
		Statement smt = null;

		//SQL文
		String sql = "SELECT o.orderno,o.user,u.type,o.quantity,u.price,o.date,o.payment,o.send "
				+ "FROM uniforminfo u INNER JOIN orderinfo o ON u.unino=o.unino ORDER BY o.orderno DESC";

		//return用のArraylistの作成
		ArrayList<Order> orderlist = new ArrayList<Order>();

		try {

			con = getConnection();
			smt = con.createStatement();

			//sql文をDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//検索結果の格納
			while (rs.next()) {
				//オブジェクト宣言
				Order order = new Order();

				//ステータスをセット
				order.setOrderno(rs.getInt("orderno"));
				order.setUser(rs.getString("user"));
				order.setType(rs.getString("type"));
				order.setQuantity(rs.getInt("quantity"));
				order.setPrice(rs.getInt("price"));
				order.setDate(rs.getString("date"));
				order.setPayment(rs.getString("payment"));
				order.setSend(rs.getString("send"));

				//配列にオブジェクト追加
				orderlist.add(order);

			}

		} catch (Exception e) {
			
			throw new IllegalStateException(e);
			
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		//戻り値
		return orderlist;
	}

	//引数の購入データを元にDBのorderinfoテーブルに新規登録処理を行うメソッド
	public void insert(Order order) {
		//変数宣言
		Connection con = null;
		Statement smt = null;

		//SQL文
		String sql = "INSERT INTO orderinfo VALUES(NULL,'"
				+ order.getUnino() + "','"
				+ order.getUser() + "',"
				+ order.getQuantity() + ","
				+ "CURDATE(),'"
				+ order.getPayment() + "','"
				+ order.getSend() + "','"
				+ order.getText() + "')";

		try {

			con = getConnection();
			smt = con.createStatement();

			//sql文をDBへ発行
			int count = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

	//受注詳細用のメソッド（引数を注文番号にする）
	public Order selectByOrderno(int orderno) {

		Connection con = null;
		Statement smt = null;

		//Orderオブジェクト
		Order order = null;

		//SQL文発行
		String sql = "SELECT o.orderno,o.user,u.type,o.quantity,u.price,o.date,o.payment,o.send "
				+ "FROM uniforminfo u INNER JOIN orderinfo o ON u.unino=o.unino WHERE orderno = '" + orderno + "'";

		try {
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				order = new Order();
				order.setOrderno(rs.getInt("orderno"));
				order.setUser(rs.getNString("user"));
				order.setType(rs.getString("type"));
				order.setQuantity(rs.getInt("quantity"));
				order.setPrice(rs.getInt("price"));
				order.setDate(rs.getString("date"));
				order.setPayment(rs.getString("payment"));
				order.setSend(rs.getString("send"));
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return order;
	}

	//入金・発送状況変更
	public void update(String payment, String send, int orderno) {
		Connection con = null;
		Statement smt = null;

		//SQL文発行
		String sql = "UPDATE orderinfo SET payment='" + payment + "',send='" + send
				+ "' WHERE orderno='"
				+ orderno + "'";
		try {
			con = getConnection();
			smt = con.createStatement();

			//UpdateメソッドでSQLを登録
			int count = smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

}
