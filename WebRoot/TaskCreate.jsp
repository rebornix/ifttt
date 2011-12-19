<html>
<head>
<title>ifttt demo</title>
<link rel="stylesheet" href="/ifttt/stylesheets/main.css" type="text/css">
<style>
.showbox{
z-index:0; 
text-align: left; padding: 20px;
 font-size: 24px; z-index: 10;
  position: center; 
  width: 1000px; height: 300px; 
  background: #fff; 
  display: none;
  -moz-box-shadow: 6px 2px 21px #000000;
-moz-box-shadow: 6px 2px 21px #000000;
-webkit-box-shadow: 6px 2px 21px #000000;
box-shadow: 6px 2px 21px #000000;
}
</style>
<script language='javascript'>
var showbox = 0;
function showthis(){
	document.getElementById("thisbox").style.display = "block";
	document.getElementById("thatbox").style.display = "none";
	document.getElementById("thistxt").style.color = "rgba(0, 86, 249, .1)";
}

function showthat(){
	document.getElementById("thatbox").style.display = "block";
	document.getElementById("thisbox").style.display = "none";
	document.getElementById("thattxt").style.color = "rgba(0, 86, 249, .1)";
}

function showthisclock(){
	document.getElementById("thisServiceClock").style.display = "block";
	document.getElementById("thisServiceMail").style.display = "none";
	document.getElementById("thisbox").style.display = "none";
}
function showthismail(){
	document.getElementById("thisServiceMail").style.display = "block";
	document.getElementById("thisServiceClock").style.display = "none";
	document.getElementById("thisbox").style.display = "none";
}
function showthisweibo(){
	document.getElementById("thisServiceWeibo").style.display = "block";
	document.getElementById("thisServiceClock").style.display = "none";
	document.getElementById("thisServiceMail").style.display = "none";
	document.getElementById("thisbox").style.display = "none";
}
function showthisweiboword(){
	document.getElementById("thisServiceWeiboWord").style.display = "block";
	document.getElementById("thisServiceWeibo").style.display = "none";
}
function showthisweiboduration(){
	document.getElementById("thisServiceWeiboDuration").style.display = "block";
	document.getElementById("thisServiceWeibo").style.display = "none";
}
function showthatweibo(){
	document.getElementById("thatServiceWeibo").style.display = "block";
	document.getElementById("thatServiceMail").style.display = "none";
	document.getElementById("thatbox").style.display = "none";
}
function showthatmail(){
	document.getElementById("thatServiceWeibo").style.display = "none";
	document.getElementById("thatServiceMail").style.display = "block";
	document.getElementById("thatbox").style.display = "none";
}

function setThis(thisAction, thisValue){
	document.getElementById("this_type").value = thisAction;
	document.getElementById("this_value").value = thisValue;
	document.getElementById("thisServiceClock").style.display = "none";
	document.getElementById("thisServiceMail").style.display = "none";
	document.getElementById("thisServiceWeiboWord").style.display = "none";
	document.getElementById("thisServiceWeiboDuration").style.display = "none";
}

function setThat(thatAction, thatValue){
	document.getElementById("that_type").value = thatAction;
	document.getElementById("that_value").value = thatValue;
	document.getElementById("thatServiceWeibo").style.display = "none";
	document.getElementById("thatServiceMail").style.display = "none";
	document.getElementById("thisServiceWeiboWord").style.display = "none";
	document.getElementById("thisServiceWeiboDuration").style.display = "none";
}

window.onmouseup = function(){
	if(showbox){
		document.getElementById("thisbox").style.display = "none";
		document.getElementById("thatbox").style.display = "none";
		document.getElementById("thisServiceMail").style.display = "none";
		document.getElementById("thisServiceClock").style.display = "none";
		document.getElementById("thatServiceWeibo").style.display = "none";
		document.getElementById("thatServiceMail").style.display = "none";
		document.getElementById("thisServiceWeibo").style.display = "none";
		document.getElementById("thisServiceWeiboWord").style.display = "none";
		document.getElementById("thisServiceWeiboDuration").style.display = "none";
		showbox = 0;
	}
}

</script>
</head>
<body style="margin: 0; font-family: arial,sans-serif; color: #333;">
<div id="wrap_all">
	<div>
		<div style="position: relative; margin: 10 auto; ">
			<div style="float: left;"><i><span class="logo_text" href="/iftttDemo/index.jsp"> ifttt demo </span>DashBoard</i></div>
			<ul style="float: right;">
  			<li><b href="#">TASKS</b></li>
  			<li><a href="#">RECIPIES</a></li>
  			<li><a href="#">CHANNELS</a></li>
 			<li><c href="#">More</c>
    			<ul>
      				<li><a href="#">Activity log</a></li>
      				<li><a href="/ifttt/UserSetting.jsp">Setting </a></li>
      				<li><a href="/ifttt/servlet/LogOut">LogOut</a></li>
    			</ul>
  			</li>
			</ul>
			<div style="float: right;">Hi <%= session.getAttribute("username") %></div>	
			<div style="clear: both;"></div>
		</div>
		<hr>
	</div>
	<div style="margin: 50px auto; width: 1000px; text-align:center; font-size: 56px;">
		<form action="/ifttt/servlet/CreateServlet" method="post">
			<div><strong><a style="color:rgba(0, 0, 0, .1)">If</a> 
			<a id="thistxt" onclick="showthis();" href="#" style="color: #0056F9">This</a> 
			<a style="color:rgba(0, 0, 0, .1)">Then </a>
			 <a id="thattxt" onclick="showthat();" href="#" style="color: #0056F9">That </a></strong></div>
			<div style="margin: 10px;">
			<input type="submit" value="Create" style="float:center; border: none; margin: 10px; background: #0056F9; color: #fff; font-size: 24px; width: 150px; height: 60px;" /></div>
			<input type="hidden" name="this_type" id="this_type" />
			<input type="hidden" name="this_value" id="this_value" />
			<input type="hidden" name="that_type" id="that_type" />
			<input type="hidden" name="that_value" id="that_value" />
		</form>
	
		<div id="thisbox" onmouseout="showbox=1;" onmouseover="showbox=0;"  
			class="showbox">
				<div id="thisServiceClockImg" style="float:left"><img style="margin:4px" onclick="showthisclock()" src="/ifttt/img/datetime.png"/></div>
				<div id="thisServiceMailImg" style="float:left"><img style="margin:4px" onclick="showthismail()" src="/ifttt/img/email.png"/></div>				
				<div id="thisServiceWeiboImg" style="float:left"><img style="margin:4px" onclick="showthisweibo()" src="/ifttt/img/weibo.png"/></div>	
		</div>
		<div id="thisServiceClock" onmouseout="showbox=1;" onmouseover="showbox=0;" 
					class="showbox">
					<div>Clock: do this when it's <br>
					Year: <input type="text" id="clock_year_value" />. <br>
					Month: <input type="text" id="clock_month_value" />. <br>
					Date: <input type="text" id="clock_date_value" />. <br>
					Hour: <input type="text" id="clock_hour_value" />. <br>
					Minute: <input type="text" id="clock_minute_value" />. <br>
					<input type="button" value="Comfirm" onclick="setThis('thisServiceClock',
						document.getElementById('clock_year_value').value +'&'
						+document.getElementById('clock_month_value').value+'&'
						+document.getElementById('clock_date_value').value+'&'
						+document.getElementById('clock_hour_value').value+'&'
						+document.getElementById('clock_minute_value').value);" 
						style="border: none; margin: 10px; background: #0056F9; color: #fff; font-size: 24px; width: 150px; height: 60px;"/>
						</div>
		</div>
		<div id="thisServiceMail" onmouseout="showbox=1;" onmouseover="showbox=0;" 
					class="showbox">
					<div>When Mail:<br>
					Account: <input type="text" id="this_mail_account" /><br>
					Password: <input type="password" id="this_mail_password" /><br>
					recieve a new mail. <br>
					<input type="button" value="OK" onclick="setThis('thisServiceMail',
						document.getElementById('this_mail_account').value +'&'
						+document.getElementById('this_mail_password').value);" 
						style="border: none; margin: 10px; background: #0056F9; color: #fff; font-size: 24px; width: 150px; height: 60px;"/></div>
		</div>
		<div id="thisServiceWeibo" onmouseout="showbox=1;" onmouseover="showbox=0;" 
			class="showbox">
			<div id="thisServiceWeiboWordImg" style="float:left"><img style="margin:4px" onclick="showthisweiboword()" src="/ifttt/img/weibo.png"/></div>
			<div id="thisServiceMailDurationImg" style="float:left"><img style="margin:4px" onclick="showthisweiboduration()" src="/ifttt/img/weibo.png"/></div>	
		</div>
		
		<div id="thisServiceWeiboWord" onmouseout="showbox=1;" onmouseover="showbox=0;" 
			class="showbox">
			<div>Weibo: <br>
			Account: <input type="text" id="this_weibo_word_account" value="uittgbaby@gmail.com"/><br>
			Listen Word: <input type="text" id="this_weibo_word" value="ServerTest"/>. 
			<input type="button" value="OK" onclick="setThis('thisServiceWeiboWord',
			document.getElementById('this_weibo_word_account').value +'&' 
						+document.getElementById('this_weibo_word').value);" 
						style="border: none; margin: 10px; background: #0056F9; color: #fff; font-size: 24px; width: 150px; height: 60px;"/></div>
		</div>
		
		<div id="thisServiceWeiboDuration" onmouseout="showbox=1;" onmouseover="showbox=0;" 
			class="showbox">
			<div>Weibo: <br>
			Account: <input type="text" id="this_weibo_duration_account" value="uittgbaby@gmail.com"/><br>
			Duration: <input type="text" id="this_weibo_duration" value="ServerTest"/>. 
			<input type="button" value="OK" onclick="setThis('thisServiceWeiboDuration',
			document.getElementById('this_weibo_duration_account').value +'&'
						+document.getElementById('this_weibo_duration').value);" 
						style="border: none; margin: 10px; background: #0056F9; color: #fff; font-size: 24px; width: 150px; height: 60px;"/></div>
		</div>
		
		
		<div id="thatbox" onmouseout="showbox=1;" onmouseover="showbox=0;" class="showbox">
				<div id="thatServiceWeiboImg" style="float:left"><img style="margin:4px" onclick="showthatweibo()" src="/ifttt/img/weibo.png"/></div>
				<div id="thatServiceMailImg" style="float:left"><img style="margin:4px" onclick="showthatmail()" src="/ifttt/img/email.png"/></div>		
		</div>
		<div id="thatServiceWeibo" onmouseout="showbox=1;" onmouseover="showbox=0;" 
			class="showbox">
			<div>Weibo: <br>
			Account: <input type="text" id="that_weibo_account" value="uittgbaby@gmail.com"/><br>
			Password: <input type="password" id="that_weibo_password" /><br>
			Send message: <input type="text" id="that_weibo_content" value="ServerTest"/>. 
			<input type="button" value="OK" onclick="setThat('thatServiceWeibo',
			document.getElementById('that_weibo_account').value +'&'
						+document.getElementById('that_weibo_password').value+'&'
						+document.getElementById('that_weibo_content').value);" 
						style="border: none; margin: 10px; background: #0056F9; color: #fff; font-size: 24px; width: 150px; height: 60px;"/></div>
		</div>
		<div id="thatServiceMail" onmouseout="showbox=1;" onmouseover="showbox=0;" 
			class="showbox">
			<div>Mail: <br>
			Account: <input type="text" id="that_mail_account" value="@gmail.com"/><br>
			Password: <input type="password" id="that_mail_password" /><br>
			<input type="text" id="that_mail_target" value="@"/>Will recieve a new mail<br>
			Subject:<input type="text" id="that_mail_subject" />.<br>
			Content:<input type="text" id="that_mail_content" />.<br>
			<input type="button" value="OK" onclick="setThat('thatServiceMail',
				document.getElementById('that_mail_account').value+'&'
				+document.getElementById('that_mail_password').value+'&'
				+document.getElementById('that_mail_target').value+'&'
				+document.getElementById('that_mail_subject').value+'&'
				+document.getElementById('that_mail_content').value);" 
				style="border: none; margin: 10px; background: #0056F9; color: #fff; font-size: 24px; width: 150px; height: 60px;"/></div>
		</div>
		
	</div>
	<div style="clear:both"></div>
	<br><br><br><br><br><br><br><br>
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
