package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.User;

public class UserDAO {

	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/uniformdb";
	private static String USER = "root";
	private static String PASS = "root123";

	private static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(RDB_DRIVE);
			con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public User selectByUser(String userid) {

		Connection con = null;
		Statement smt = null;

		User user = new User();

		try {
			con = getConnection();
			smt = con.createStatement();

			String sql = "SELECT * FROM userinfo WHERE user ='" + userid + "'";

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				user.setUser(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setMail(rs.getString("mail"));
				user.setAddress(rs.getString("address"));
				user.setMember(rs.getString("member"));
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
		return user;
	}

	public User selectByUser(String userid, String password) {
		Connection con = null;
		Statement smt = null;

		User user = new User();

		try {
			con = getConnection();
			smt = con.createStatement();

			String sql = "SELECT * FROM userinfo WHERE user ='" + userid + "' AND password='" + password + "'";

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				user.setUser(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setMail(rs.getString("mail"));
				user.setAddress(rs.getString("address"));
				user.setMember(rs.getString("member"));
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
		return user;
	}

	public int insert(User user) {

		//変数宣言
		Connection con = null;
		Statement smt = null;

		//戻り値宣言
		int count = 0;

		//sql文
		String sql = "INSERT INTO userinfo VALUES('"
				+ user.getUser() + "','" + user.getPassword() + "','"
				+ user.getMail() + "','" + user.getAddress() + "','" + user.getMember() + "')";

		try {

			con = getConnection();
			smt = con.createStatement();

			//sql文をDBへ移行
			count = smt.executeUpdate(sql);

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

		return count;
	}
}
