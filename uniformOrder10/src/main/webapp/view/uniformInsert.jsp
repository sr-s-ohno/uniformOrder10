<%@page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>商品登録</title>
</head>
<body>
<%--どこまでが共通のヘッダーとしてインクルードするのかいまいち分かっていないので
ひとまずデータ表示部分だけ組みます --%>

<form action="<%=request.getContextPath()%>/uniformUpdate">
			<table>
				<tr>
					<td class="detail-table">ID</td>
					<td style="width: 180"><input type="text" size="30"
						name="unino"></td>
				</tr>
				<tr>
					<td class="detail-table">商品名</td>
					<td style="width: 180"><input type="text" size="30"
						name="type"></td>
				</tr>
				<tr>
					<td class="detail-table">税込み価格</td>
					<td style="width: 180"><input type="text" size="30"
						name="price"></td>
				</tr>
				<tr>
					<td class="detail-table">在庫数</td>
					<td style="width: 180"><input type="text" size="30"
						name="stock"></td>
				</tr>
			</table>
			<input type="submit" value="登録">
		</form>

</body>
</html>