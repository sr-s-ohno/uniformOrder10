package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Uniform;

public class UniformDAO {

	//データベース接続情報
		private static String RDB_DRIVE ="org.mariadb.jdbc.Driver";
		private static String URL = "jdbc:mariadb://localhost/uniformdb";
		private static String USER = "root";
		private static String PASS = "root123";
		
		private static Connection getConnection() {
			try {
				Class.forName(RDB_DRIVE);
				Connection con = DriverManager.getConnection(URL, USER, PASS);
				return con;
				
			}catch(Exception e) {
				throw new IllegalStateException(e);
			}
		}//getConnection
		
		//一覧機能
		public ArrayList<Uniform> selectAll(){
			// 変数宣言
			Connection con = null;
			Statement smt = null;
			
			//return用オブジェクトの生成
			ArrayList<Uniform> list = new ArrayList<Uniform>();
			
			//SQL文
			String sql = "SELECT unino,type,price,stock FROM uniforminfo ORDER BY unino";
			
			try {
				con = getConnection();
				smt = con.createStatement();
				
				//SQLをDBへ発行
				ResultSet rs = smt.executeQuery(sql);
				
				//配列に格納
				while(rs.next()) {
					Uniform info = new Uniform();
					info.setUnino(rs.getString("unino"));
					info.setType(rs.getString("type"));
					info.setPrice(rs.getInt("price"));
					info.setStock(rs.getInt("stock"));
					list.add(info);
				}//while
				
			}catch(Exception e) {
				throw new IllegalStateException(e);
				
			}finally {
				//リソースの開放
				if(smt != null) {
					try {smt.close();}catch(SQLException ignore){}
				}//if
				if(con != null) {
					try {con.close();}catch(SQLException ignore){}
				}//if
			}
			return list;
		}//selectAll
		
		//指定した1件のデータを表示
		public Uniform selectByUnino(String unino) {
			//変数宣言
			Connection con = null;
			Statement smt = null;
			
			//return用オブジェクトを宣言
			Uniform uniform = new Uniform();
			
			//SQL文
			String sql = "SELECT unino,type,price FROM uniforminfo WHERE isbn = '" 
						+ unino + "'";
			
			try {
				con = getConnection();
				smt = con.createStatement();
				
				//SQLをDBへ発行
				ResultSet rs = smt.executeQuery(sql);
				
				//取得した結果をreturn用オブジェクトに格納する
				if(rs.next()) {
					uniform.setUnino(rs.getString("unino"));
					uniform.setType(rs.getString("type"));
					uniform.setPrice(rs.getInt("price"));
					uniform.setStock(rs.getInt("stock"));
				}
				
			}catch(Exception e) {
				throw new IllegalStateException(e);
				
			}finally {
				//リソースの開放
				if(smt != null) {
					try {smt.close();}catch(SQLException ignore) {}
				}
				if(con != null) {
					try {con.close();}catch(SQLException ignore) {}
				}
			}
			return uniform;
		}//selectByUnino

		
		//登録機能
		public void insert(Uniform uniform) {
			//変数宣言
			Connection con = null;
			Statement smt = null;
			
			//SQL文
			String sql = "INSERT INTO uniforminfo VALUES('"
						+uniform.getUnino()+"','"
						+uniform.getType()+"','"
						+uniform.getPrice()+"',"
						+uniform.getStock()+")";
			
			try {
				con = getConnection();
				smt = con.createStatement();
				
				//SQL文をDBへ発行
				smt.executeUpdate(sql);
				
			}catch(Exception e) {
				throw new IllegalStateException(e);
				
			}finally {
				//リソースの開放
				if(smt != null) {
					try {smt.close();}catch(SQLException ignore){}
				}
				if(con != null) {
					try {con.close();}catch(SQLException ignore){}
				}
			}
		}//insert
		
		//更新機能
		public void update(Uniform uniform) {
			//変数宣言
			Connection con = null;
			Statement smt = null;
			
			String sql = "UPDATE uniforminfo SET type='"
						+uniform.getType()+"',price="
						+uniform.getPrice()+"',stock="
						+uniform.getStock()+" WHERE unino='"
						+uniform.getUnino()+"'";
			
			try {
				con = getConnection();
				smt = con.createStatement();
				
				//SQL文をDBへ発行
				smt.executeUpdate(sql);
				
			}catch(Exception e) {
				throw new IllegalStateException(e);
				
			}finally {
				//リソースの開放
				if(smt != null) {
					try {smt.close();}catch(SQLException ignore) {}
				}
				if(con != null) {
					try {con.close();}catch(SQLException ignore) {}
				}
				
			}
		}//update
		
		//削除機能
		public void delete(String unino) {
			//変数宣言
			Connection con = null;
			Statement smt = null;
			
			//SQL文
			String sql = "DELETE FROM uniforminfo WHERE unino = '"
						+ unino +"'";
			
			try {
				
				con = getConnection();
				smt = con.createStatement();
				
				smt.executeUpdate(sql);
				
			}catch(Exception e) {
				throw new IllegalStateException(e);
				
			}finally {
				//リソースの開放
				if(smt != null) {
					try {smt.close();}catch(SQLException ignore) {}
				}
				if(con != null) {
					try {con.close();}catch(SQLException ignore) {}
				}
			}
		}//delete
	
}
