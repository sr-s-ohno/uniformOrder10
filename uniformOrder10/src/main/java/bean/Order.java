package bean;

public class Order {
	
	//フィールド変数
	//注文番号
	private int orderno;
	//商品ID
	private String unino;
	//商品名
	private String type;
	//氏名
	private String user;
	//注文個数
	private int quantity;
	//税込み価格
	private int price;
	//発注日
    private String date;
    //入金状況
	private String payment;
	//発送状況
	private String send;
	//備考欄
	private String text;
	
	//ゲッター
	public int getOrderno() {
		return orderno;
	}
	
	public String getUnino() {
		return unino;
	}
	
	public String getType() {
		return type;
	}
	
	public String getUser() {
		return user;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getPayment() {
		return payment;
	}
	
	public String getSend() {
		return send;
	}
	
	public String getText() {
		return text;
	}
	
	//セッター
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	
	public void setUnino(String unino) {
		this.unino = unino;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setDate(String date) {
		this.date = date; 
	}
	
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	public void setSend(String send) {
		this.send = send;
	}
	
	public void setText(String text) {
		this.text = text;
	}

}
