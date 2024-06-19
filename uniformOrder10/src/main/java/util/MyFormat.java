package util;

import java.text.DecimalFormat;

public class MyFormat {
	
	public String moneyFormat(int price) {
		
		//戻り値
		String money;
		
		//￥付き、3桁カンマ区切り
		DecimalFormat format = new DecimalFormat("￥0,000");
		
		//カンマで区切る
		money = format.format(price);
		
		//戻り値
		return money;
		
	}

}
