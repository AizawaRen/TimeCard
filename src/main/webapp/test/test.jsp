<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>deploy</title>
</head>
<body>

<p>選択されたものを英語で表示します</p>
<form action="test" method="post">
	<p><input type="radio" name="radio" value="apple" checked>アップル</p>
	<p><input type="radio" name="radio" value="orange">オレンジ</p>
	<p><input type="radio" name="radio" value="grape">グレープ</p>
	<p><input type="submit" value="表示する"></p>
</form>

<%if(request.getAttribute("eng") != null) { %>
<p>【　<%=request.getAttribute("eng") %>　】</p>
<% } %>

</body>
</html>