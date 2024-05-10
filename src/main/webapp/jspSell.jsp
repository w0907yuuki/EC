<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<div id="lblLogin">
	<label><font size="7">ログイン</font></label>
</div>

<style>
#lblLogin>label {
	text-align: center;
	display: block;
}

.center {
	width: 80%; /* 固定の幅を設定します */
	margin: auto; /* 左右のマージンを自動的に等しくします */
}
</style>
<body>
	<form action="" method="post">
		<!--フォーム内を中央寄せにする-->
		<div style="width: 400px; margin: auto;">
			<!--テキストボックス、ボタン設定-->
			<p>
				<font size="5"> 商品名：<br> <input type="text"
					name="txtName" size="49"></font>
			</p>
			<p>
				<font size="5"> 値段：<br> <input type="number"
					name="intPrice" size="49"></font><br>
				<br>
			</p>
			<p>
				<font size="5"> 出品者：<br> <input type="text"
					name="txtSeller" size="49"></font><br>
				<br>
			</p>
			<p>
				<font size="5"> カテゴリー：<br> <input type="text"
					name="txtCategory" size="49"></font><br>
				<br>
			</p>
			<p>
				<font size="5"> 補足<br> <input type="text"
					name="txtFreetext" size="49"></font><br>
				<br>
			</p>
			<p>
			<p>
				<font size="5"> <input type="submit"
					style="width: 95%; padding: 8px; font-size: 20px;" value="登録"
					id="btn_Login"></font><br>
			</p>
		</div>
	</form>
	<!--登録画面先リンクを中央寄せ-->
	<!--登録画面先リンクを設置-->
	<div align="center">
		<a href="Register.html">登録</a>
	</div>
</body>
</html>