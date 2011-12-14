<html>
<head>
<title>ifttt login</title>
<link rel="stylesheet" href="/ifttt/stylesheets/main.css" type="text/css">
<script>

function checkLogin()
{
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	var pswcomfirm = document.getElementById("comfirm");
	if(username.value == "")
	{
		//alert("Username!");
		document.getElementById("usernameMessage").innerText ="required ! ";
		username.focus();
		return;
	}
	if(password.value == "")
	{
		document.getElementById("passwordMessage").innerText ="required ! ";
		password.focus();
		return;
	}	
	LoginForm.submit();
	
}
</script>
</head>
<body style="margin: 0; font-family: arial,sans-serif; color: #333;">
	<div id="index_wrap_all">
		<div style="position: relative; margin: 10 auto;">
			<div style="font-size: 24px;">
				<div style="float: left;"><i><span class="logo_text" href="/ifttt/index.jsp"> ifttt demo</span></i></div>
				<div style="float: right;"><span style="padding: 10px;"><a href="/ifttt/login.jsp">Sign in</a></span>
					<span style="padding: 10px 50px; background: #0056F9;"><a style="color: #fff;" href="/ifttt/join.jsp">Join</a></span>
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>
		<div id="main_container" style="font-size: 24px">
			<br>
			<form method="post" name = "LoginForm" action="/ifttt/servlet/LoginServlet">
				<div>
					<label for="login">Username or Email</label>
					<br>
					<input type="text" name="username" id="username" autocorrect="off" autocapitalize="off " style="width: 300px; height: 45px;font-size:24px">
					<span id = "usernameMessage" style="color:red; font-size:20px"></span>
					<font color="#FF0000">${requestScope.userError}</font>
				</div>
				<div>
					<label for="password">Password</label>
					<br>
					<input type="password" name="password" id="password" autocorrect="off" autocapitalize="off" style="width: 300px; height: 45px;ont-size:24px">
					<span id = "passwordMessage" style="color:red; font-size:20px"></span>
					<font color="#FF0000">${requestScope.passwordError}</font>
					<br>
				</div>
				<div>
				<p>
					<label for="remember_me" style="vertical-align:middle;">Remember me?</label>
					<input type="checkbox" value="1" name="remember_me" id="remember_me" checked="checked" style="vertical-align:middle;text-align:left">
				</p>
				</div>
				<div class="big_btn">
				<input type="button" onclick="checkLogin()" value="Sign In" style="border: none; margin: 10px; background: #0056F9; color: #fff; font-size: 36px; width: 150px; height: 60px;" />	</div>
			</form>
		</div>
		<div style="clear:both"></div>

	</div>
	

	<div id="footer" style="visibility: visible; margin-top: 0px; background-color:black;">
		<div id="footer_inner">
			<div id="logo" style="margin: 50x auto; text-align:center; font-size: 56;font-style: inherit;">
				<span class="full_logo_text">
					<a href="/" title="Return to home page" style="color:white"><span class="logo_text"> ifttt demo</span></a>
				</span>
			</div>
		</div>
		<div >
			<div id="other_links" style="margin: 38px auto; text-align:center; font-size: 18;">
				<span><a href="/wtf" style="color:white">About</a></span>
				<span><a href="http://blog.ifttt.com"style="color:white">Blog</a></span>
				<span><a href="/contact"style="color:white">Contact</a></span>
				<span><a href="/terms"style="color:white">Terms</a></span>
				<span><a href="/privacy"style="color:white">Privacy</a></span>
			</div>
			<div id="created_in"  id="other_links" style="margin: 15px auto; text-align:center; font-size: 16;">
			Created in 5A238 By Rebornix
			</div>
		</div>
	</div>	

</body>
</html>
