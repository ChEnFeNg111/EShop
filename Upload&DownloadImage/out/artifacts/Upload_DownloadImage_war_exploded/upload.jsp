<%--
  Created by IntelliJ IDEA.
  User: 陈峰
  Date: 2018/10/19
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/upload" enctype="multipart/form-data" method="post">
        <input type="file" name="images">
        <input type="submit" value="上传图片">
    </form>
</body>
</html>
