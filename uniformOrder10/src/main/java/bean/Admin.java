package bean;

public class Admin {
	
	//フィールド変数
	//管理者用ID
	private String admin;
	//管理者用パスワード
	private String password;
	
	//ゲッター
	public String getAdmin() {
		return admin;
	}
	
	public String getPassword() {
		return password;
	}
	
	//セッター
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
