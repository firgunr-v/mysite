<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>登录</title>
    <style type="text/css">
        .error {
            color: red;
        }
    </style>

</head>
<body>
<#if errMsg?exists>
<div class="error">${errMsg!''}</div>
</#if>
<form action="loginCheck" method="post">

    用户名： <input type="text" name="username" placeholder="用户名"/> <br>
    密　码：<input type="password" name="password" placeholder="密码"/> <br>
    <button type="submit">登录</button>
</form>
</body>
</html>