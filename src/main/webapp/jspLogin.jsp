<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="java.util.HashMap" %>
	<%String filename=(String)request.getAttribute("filename");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kaitori</title>
<style>
/* スタイルは任意のものを使用してください */
#topButton {
	display: none;
	position: fixed;
	bottom: 20px;
	right: 20px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	padding: 10px 20px;
	cursor: pointer;
	z-index: 1000;
}
</style>
</head>
<body>
	<h2>ようこそ</h2>
	<form action="ServSerch" method="post">
	<p><font size="5"><input type="text" name="txtSerch" ID="txtSerch"
			size="49"  placeholder="検索" onfocus="clearText()"></font>
		<font size="5"> <input type="submit" value="検索" id="btn_Login"></font>	
			<br><br>
	</p>
	</form>
	<div align="right">
		<a href="htmlProduct.html">商品登録する</a>
	</div>
	
	<!-- ページコンテンツ -->
	<table class ="table">
        <% 
            ArrayList<HashMap<String, Object>> resultList = (ArrayList<HashMap<String, Object>>) request.getAttribute("resultList");
            for (HashMap<String, Object> row : resultList) { 
        %>
            <tr>
            	<td><img src=image/<%= row.get("Image") %>></td>
                <td>商品名：<%= row.get("name") %></td>&nbsp;
                <td>値段：<%= row.get("Price") %></td>&nbsp;
                <td>出品者：<%= row.get("Seller") %></td>&nbsp;
                <td>カテゴリー：<%= row.get("Category") %></td>&nbsp;
                <td>備考：<%= row.get("Freetext") %></td>
                <!-- 列の数に応じて追加 -->
            </tr>
        <% } %><br>
    </table>
	<!-- ページコンテンツ -->
	<!-- Topに戻るボタン -->
	<button onclick="topFunction()" id="topButton" title="Topに戻る">Topに戻る</button>

	<script>
				// Topに戻るボタンを表示するかどうかを判断する関数
				function scrollFunction() {
	  			if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
	    			document.getElementById("topButton").style.display = "block";
	  			} 
	  			else {
	    			document.getElementById("topButton").style.display = "none";
	  				}
				}
	
				// Topに戻る関数
				function topFunction() {
	  				document.body.scrollTop = 0; // For Safari
	  				document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
				}
	
				// スクロール時にTopに戻るボタンを表示するかどうかを判断
				window.onscroll = function() {scrollFunction()};
			</script>
</body>
</html>