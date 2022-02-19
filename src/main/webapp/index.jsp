<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
<meta charset='UTF-8'>
<title>TimeCard-app</title>
</head>
<body>
<h2>ログイン</h2>
<div>

<form action="/timecard-app/LoginServlet" method="post">
	社員番号：<input type="text" name="number"><br>
	パスワード：<input type="password" name="pass"><br>
	<input type="submit" value="ログイン">
</form>

</div>
</body>
</html>