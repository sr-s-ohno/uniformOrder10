package bean;

public class User {
	
	//フィールド変数 会員情報
	//氏名
	private String user;
	//パスワード
	private String password;
	//メールアドレス
	private String mail;
	//住所
	private String address;
	
	//各変数のゲッター
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getAddress() {
		return address;
	}
	
	//各変数のセッター
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

}
