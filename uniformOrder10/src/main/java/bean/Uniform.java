package bean;

public class Uniform {
	
	//フィールド変数
	//商品ID
	private String unino;
	//商品種類
	private String type;
	//税込み価格
	private int price;
	//在庫数
	private int stock;
	
	//ゲッター
	public String getUnino() {
		return unino;
	}
	
	public String getType() {
		return type;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getStock() {
		return stock;
	}
	
	//セッター
	public void setUnino(String unino) {
		this.unino = unino;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}

}
