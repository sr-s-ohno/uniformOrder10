package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Admin;

public class AdminDAO {

	//データベース接続情報
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/uniformdb";
	private static String USER = "root";
	private static String PASS = "root123";

	private static Connection getConnection() {
		try {

			//JDBCドライバをロード
			Class.forName(RDB_DRIVE);
			//Connectionオブジェクト生成
			Connection con = DriverManager.getConnection(URL, USER, PASS);

			return con;

		} catch (Exception e) {

			throw new IllegalStateException(e);
		}
	}
	//userinfoテーブルから指定ユーザーの条件に合致する情報を取得する
	public Admin selectByAdmin(String admin) {

		//変数宣言
		Connection con = null;
		Statement smt = null;

		//戻り値のAdmin(DTO)オブジェクト生成
		Admin ObjAdmin = new Admin();

		//SQL文
		String sql = "SELECT * FROM admininfo WHERE admin = '" + admin + "'";

		try {

			con = getConnection();
			smt = con.createStatement();

			//sql文をDBへ移行
			ResultSet rs = smt.executeQuery(sql);

			//検索結果をDTOオブジェクトに格納
			if (rs.next()) {

				//オブジェクトにセッター起動
				ObjAdmin.setAdmin(rs.getString("user"));
				ObjAdmin.setPassword(rs.getString("password"));
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//リソースの開放
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

		return ObjAdmin;
	}

	//userinfoテーブルから指定のユーザーとパスワード
	public Admin selectByAdmin(String admin, String password) {

		//変数宣言
		Connection con = null;
		Statement smt = null;

		//戻り値のUser(DTO)オブジェクト生成
		Admin ObjAdmin = new Admin();

		//SQL文
		String sql = "SELECT * FROM admininfo WHERE admin ='"
				+ admin + "' AND password='" + password + "'";

		try {

			con = getConnection();
			smt = con.createStatement();

			//sql文をDBへ移行
			ResultSet rs = smt.executeQuery(sql);

			//検索結果をDTOオブジェクトに格納
			if (rs.next()) {
				//オブジェクトにセッター起動
				ObjAdmin.setAdmin(rs.getString("admin"));
				ObjAdmin.setPassword(rs.getString("password"));
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//リソースの開放
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

		return ObjAdmin;
	}
}