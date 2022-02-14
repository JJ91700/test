<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
<a href="admin/index.html">admin/index.html</a><br />
<a href="admin/login.html">admin/login.html</a><br />
<br />
<br />
<br />
<a href="/express/list.do?offset=2&pageNumber=3">/express/list.do</a><br />
<a href="admin/views/express/list.html">/express/list.do</a><br />
<br />
<br />
<br />
<a href="/express/find.do?number=123110">/express/find.do</a><br />
<br />
<br />
<br />
<a href="/courier/list.do?offset=2&pageNumber=3">/courier/list.do</a><br />
<a href="admin/views/courier/list.html">/courier/list.do</a><br />


</body>
</html>