<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
<meta charset='UTF-8'>
<title>TimeCard-app</title>
</head>
<body>
<div class="login-box">
<h1>Login</h1>

<form action="/timecard-app/LoginServlet" method="post">
	<label for="number">従業員番号</label>
	<input type="text" name="number" id="number" class="login"><br>
	<label for="pass">パスワード</label>
	<input type="password" name="pass" id="pass" class="login"><br>
	<input type="submit" value="ログイン" class="login">
</form>

</div>
</body>
</html>