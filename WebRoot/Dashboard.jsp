<html>
<head>
<title>ifttt demo</title>
<link rel="stylesheet" href="/ifttt/stylesheets/main.css" type="text/css">
</head>
<body style="margin: 0; font-family: arial,sans-serif; color: #333; height:100%">
<%@ page session="true"%>
<%@ page import="java.io.Serializable" %>
<jsp:useBean id="account" scope="session" class="Bean.Account" type ="Bean.Account"/>

	<div id="wrap_all">
		<div>
			<div style="position: relative; margin: 10 auto; ">
				<div style="float: left;"><i><span class="logo_text" href="/ifttt/index.jsp"> ifttt demo </span>DashBoard</i></div>
				
				<div style="float: right; align: absmiddle; ">Hi <%= session.getAttribute("username") %></div>
				
				<div style="clear: both;"></div>
			</div>
			<hr>
		</div>
		<div style="margin: 50px auto; width: 1000px;">
			<div>
			<!-- / TASKS -->
				<div class="two_col_left_inverse">
					<div class="medium_text bold">U:</div>
					<div>
						<span class="medium_text bold">Name: <%= session.getAttribute("username") %></span>
						<br>
						<span class="medium_text bold">Level: <span class="heart"></span></span>
					</div>
					<br><hr><br>
					<div class="medium_text bold">Tasks</div>
					<!-- The number is dinamic, use JSP!-->
					<div>
						<span class="large_text bold"><jsp:getProperty name="account" property="taskenabledamount"/> </span><span class="medium_text">enabled</span>
						<br>
						<span class="large_text bold"><jsp:getProperty name="account" property="taskamount"/> </span><span class="medium_text">total</span>
						<br>
					</div>
					<br>
					<div>	
						<div class="helper_button_inline in_text">
							<a href="/ifttt/TaskCreate.jsp" style="color:#0056F9">Create a task</a>
							<div class="clear"></div>
						</div>
						<div class="helper_button_inline in_text">
							<a href="/ifttt/TaskList.jsp" style="color:#0056F9">Your tasks</a>
							<div class="clear"></div>
						</div>
					</div>
					<br>
					<br>
					<div class="medium_text bold">Invites</div>
					<div class="medium_text">Tell your friends about<span class="logo_text"> ifttt demo</span>
						<div class="half_break"></div>
						<br>
						<div style="font-weight: normal;" class="helper_button_inline in_text">
							<a href="/invitations/new" style="color:#0056F9">Invite others</a>
						</div>
						<br>
						<br>
					</div>
				</div>
				<div class="two_col_right_inverse">
					<div class="medium_text bold">Channels</div>
					<span class="medium_text">3 Channels with 8 possible task combinations.</span>
					<div id="ServiceClockImg" style="float:left"><img style="margin:4px" onclick="showthisclock()" src="/ifttt/img/datetime.png"/></div>
					<div id="ServiceMailImg" style="float:left"><img style="margin:4px" onclick="showthismail()" src="/ifttt/img/email.png"/></div>	
					<div id="ServiceWeiboImg" style="float:left"><img style="margin:4px" onclick="showthismail()" src="/ifttt/img/weibo.png"/></div>
				</div>
			</div>	
		</div>
		<div style="clear:both"></div>

</div>
	<div id="footer" style="visibility: visible; margin-top: 0px; background-color:black;text-align:center;">
		<div id="footer_inner">
			<div id="logo" style="margin: 50x auto; text-align:center; font-size: 56;font-style: inherit;">
				<span class="full_logo_text">
					<a href="index.html" title="Return to home page" style="color:white">ifttt Demo</a>
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
