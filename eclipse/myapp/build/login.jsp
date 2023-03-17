<html>
<head>
        <title>Login</title>
</head>

<body>
  <h2>Login to the <%= application.getInitParameter("appTitle") %>.</h2>

  <form method="POST" action="j_security_check">
      Username: <input type="text" name="j_username"><br>
      Password: <input type="password" name="j_password"><br>
      <input type="submit" value="Submit"><br>
  </form>

</body>
</html>

