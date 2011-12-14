<html>
<head>
<title>ifttt demo</title>
<link rel="stylesheet" href="/ifttt/stylesheets/main.css" type="text/css">

</head>
<body style="margin: 0; font-family: arial,sans-serif; color: #333;">
<div id="wrap_all">
	<div>
		<div style="position: relative; margin: 10 auto; ">
			<div style="float: left;"><i><span class="logo_text" href="/ifttt/index.jsp"> ifttt demo </span>DashBoard</i></div>
			<div style="float: right;">Hi <%= session.getAttribute("username") %></div>	
			<div style="clear: both;"></div>
		</div>
		<hr>
	</div>
	<br>
	
	<div class="big_btn" id="big_btn_top">
		<a href="/ifttt/TaskCreate.jsp" style="color:fff">Create task</a>
		<div class="clear"></div>
	</div>
	<br><br>
	<div class="statement  first" id="statement_319471">
		<div style="margin: 15px auto; text-align:center; font-size: 86; color:#07f;text-shadow: 2px 4px 3px rgba(0, 0, 0, .45)">
			<div>
				<div style="float:left">
				If 
				</div>
				<div style="float:left ;margin-left: 20px; margin-right: 20px">
					<img src="img/datetime.png"/>
				</div>
				<div style="float:left">
				Then
				</div>
				<div style="float:left ;margin-left: 20px; margin-right: 20px">
					<img src="img/weibo.png"/>
				</div>
				<div style="float:right">
					<img src="img/check_true.png"/>
				</div>
				<div style="float:right">
					<img src="img/delete_task.PNG"/>
				</div>
				<div class="clear"></div>
			</div>			
		</div>
			
		
	</div>
	<br>
	<div>
		<div id="statements_first_run_helper">
			<span class="medium_text bold">
				<strong>Quick reminder:</strong>
				</span>
			<span class="medium_text">
				Tasks are checked once per minute	. When you turn a task off then back on, it resets as if you had just created it.
			</span>
		</div>
	</div>	

	<br>
	<br>
</div>
<div id="footer" style="visibility: visible; position:relative; margin-bottom: 0px; background-color:black;">
	
	<div id="footer_inner">
		<div id="logo" style="margin: 50x auto; text-align:center; font-size: 56;font-style: inherit;">
			<span class="full_logo_text">
				<a href="/" title="Return to home page" style="color:white">ifttt Demo</a>
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
