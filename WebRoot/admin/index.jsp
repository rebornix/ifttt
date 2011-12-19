<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Simpla Admin | Sign In by www.865171.cn</title>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="/ifttt/admin/resources/css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="/ifttt//admin/resources/css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="/ifttt/admin/resources/css/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="/ifttt/adminresources/scripts/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="/ifttt/adminresources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="/ifttt/admin/resources/scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="/ifttt/admin/resources/scripts/jquery.wysiwyg.js"></script>
<style>
.switch {
-webkit-animation: cssAnimation 2.3997s 16 ease;
-moz-animation: cssAnimation 2.3997s 16 ease;

}
@-webkit-keyframes cssAnimation {
from { -webkit-transform: rotate(16deg) scale(1) skew(-1deg) translate(0px); }
to { -webkit-transform: rotate(360deg) scale(1) skew(-1deg) translate(0px); }
}
@-moz-keyframes cssAnimation {
from { -moz-transform: rotate(16deg) scale(1) skew(-1deg) translate(0px); }
to { -moz-transform: rotate(360deg) scale(1) skew(-1deg) translate(0px); }
}
</style>
</head>
<body id="login">
<div id="login-wrapper" class="png_bg">
  <div id="login-top" style="background-color:#ddd;">
    <h1>Simpla Admin</h1>
    <!-- Logo (221px width) -->
    <a style="font-size:36px;color:#0056F9;">Admin Login</a> 
  </div>
<div>
  <!-- End #logn-top -->
  <div id="login-content">
    <form action="/ifttt/servlet/AdminLogin" method="post">
      <div class="notification information png_bg">
        <div> Only Adminstrator can be logged in</div>
      </div>
      <p>
        <label>Username</label>
       <input type="text" name="username" id="username" autocorrect="off" autocapitalize="off" class="text-input" />
        <font color="#FF0000">${requestScope.userError}</font>
      </p>
      <div class="clear"></div>
      <p>
        <label>Password</label>
        <input type="password" name="password" id="password" autocorrect="off" autocapitalize="off" class="text-input" />
        <font color="#FF0000">${requestScope.passwordError}</font>
      </p>
      <div class="clear"></div>
      <p id="remember-password">
        <input type="checkbox" />
        Remember me </p>
      <div class="clear"></div>
      <p>
        <input class="button" type="submit" value="Sign In" style="border: none; margin: 10px;background: #0056F9; color: #fff; font-size: 36px; width: 150px; height: 60px;"/>
      </p>
    </form>
  </div>
</div>
  <!-- End #login-content -->
</div>
<!-- End #login-wrapper -->
</body>
</html>
