<html>
<head>
<title>ifttt demo</title>
<link rel="stylesheet" href="stylesheets/main.css" type="text/css">
<script>
function setThis(thisAction, thisValue){
	document.getElementById("this_type").value = thisAction;
	document.getElementById("this_value").value = thisValue;
	SetForm.submit();
}

</script>
</head>
<body style="margin: 0; font-family: arial,sans-serif; color: #333; height:100%">
<%@ page session="true"%>
<%@ page import="java.io.Serializable" %>
<%@ page import="Bean.*" %>
<jsp:useBean id="recordlistbean" scope="session" class="Bean.RecordListBean" type ="Bean.RecordListBean"/>
	<div id="wrap_all">
		<div>
			<div style="position: relative; margin: 10 auto; width: 1000px;">
				<div style="float: left;"><i><span class="logo_text"> ifttt demo </span>User Setting</i></div>
				<div style="float: right; align: absmiddle; ">Hi <%= session.getAttribute("username") %></div>
				<div style="clear: both;"></div>
			</div>
			<hr>
		</div>
		<div style="margin: 50px auto; width: 1000px;">
			<div>
			<!-- / TASKS -->
				<div class="two_col_left_inverse" style="background-color:#eee">
				<form name = "SetForm" action="/ifttt/servlet/InsertMoneyServlet" method="post">
					<input type="hidden" name="this_type" id="this_type" />
					<input type="hidden" name="this_value" id="this_value" />
				</form>
					<div style="background-color:#eee; height:400px;width:300px;">
					<br>
					
				<div>
					<label  style="margin:20px;font-size:24px;">New Password:</label>
					<br>
					<input name="newpassword" id="newpassword" autocorrect="off" autocapitalize="off" style="margin:20px;width: 200px; height: 36px;vertical-align:bottom;" type="password">
					<div class="helper_button_inline in_text" style="margin:20px;">
							<a  style="color:#0056F9" onclick="setThis('password', document.getElementById('newpassword').value)">Change!</a>
							<div class="clear"></div>
					   </div>
				</div>
				<label style="margin-left:20px;font-size:24px;">Add Money:</label>
					<input name="moremoney" id="moremoney" autocorrect="off" autocapitalize="off " style="margin:20px;width: 200px; height: 36px; font-size: 24px;vertical-align:bottom;" type="text">
					<div class="helper_button_inline in_text" style="margin:20px;" >
							<a  style="color:#0056F9" onclick="setThis('addmoney', document.getElementById('moremoney').value)">Add!</a>
							<div class="clear"></div>
					   </div>

                    </div>                      
                </div>
				<div class="two_col_right_inverse" style="background-color:#e77">
					<div class="medium_text bold">Task logs</div>
					<jsp:setProperty name="recordlistbean" property="userName" value="<%= session.getAttribute(\"username\")%>" />
					<%	
						String[] logs = new String[recordlistbean.getAmount()];
						logs = recordlistbean.getString();
						for(int i = 0; i < recordlistbean.getAmount(); i++){
							out.print("<span class=\"medium_text\">"+logs[i]+"</span><br>");
						}
						
					 %>
					<br>
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
			Created in 5A238 & 5A251 By Rebornix & GreatShang
			</div>
		</div>
	</div>	
</body>
</html>
