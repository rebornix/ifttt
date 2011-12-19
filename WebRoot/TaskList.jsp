<html>
<head>
<title>ifttt demo</title>
<link rel="stylesheet" href="/ifttt/stylesheets/main.css" type="text/css">
<script>
function deleteTaskComfirm(task_id_value){
	document.getElementById("task_id").value = task_id_value;
	DeleteTaskForm.submit();
}
function changeTaskComfirm(task_id_value){
	document.getElementById("task_change_id").value = task_id_value;
	ChangeTaskForm.submit();
}
</script>
</head>
<body style="margin: 0; font-family: arial,sans-serif; color: #333;">
<%@ page session="true"%>
<%@ page import="java.io.Serializable" %>
<%@ page import="Bean.*" %>
<jsp:useBean id="tasklistbean" scope="session" class="Bean.TaskListBean" type ="Bean.TaskListBean"/>
<div id="wrap_all">
	<div>
		<div style="position: relative; margin: 10 auto; ">
			<div style="float: left;"><i><span class="logo_text" ><a href="/ifttt/Dashboard.jsp"> ifttt demo </a></span>TaskList</i></div>
			<div style="float: right;">Hi <%= session.getAttribute("username") %></div>	
			<div style="clear: both;"></div>
		</div>
		<hr>
	</div>
	<br>
	<form name="DeleteTaskForm" action="/ifttt/servlet/DeleteTask" method="post">			
			<input type="hidden" name="task_id" id="task_id" />
		</form>
	<form name="ChangeTaskForm" action="/ifttt/servlet/ChangeTaskStatus" method="post">			
			<input type="hidden" name="task_change_id" id="task_change_id" />
			<input type="hidden" name="task_change_status" id="task_change_status" value="1">
	</form>
	<div class="big_btn" id="big_btn_top" >
		<a href="/ifttt/TaskCreate.jsp" style="color:fff">Create task</a>
		<div class="clear"></div>
	</div>
	
	<div class="clear:both"></div>
	<hr>
	<br><br>
	<div class="statement  first" id="statement_319471">
		<div style="margin: 15px auto; text-align:center; font-size: 86; color:#07f;text-shadow: 2px 4px 3px rgba(0, 0, 0, .45)">
			<%
				TaskItem[] taskitems = tasklistbean.getTaskItem();
				for(int i = 0; i < tasklistbean.getAmount(); i++){
					int taskid = taskitems[i].getTaskID();
					out.print("<div><div style=\"float:left\">"+(i+1)+". If</div>");
					if(taskitems[i].getThisType() == 1){
						out.print("<div style=\"float:left ;margin-left: 20px; margin-right: 20px\"><img src=\"/ifttt/img/datetime.png\"/></div>");
					}
					else if(taskitems[i].getThisType() == 2){
						out.print("<div style=\"float:left ;margin-left: 20px; margin-right: 20px\"><img src=\"/ifttt/img/email.png\"/></div>");
					}
					else {
						out.print("<div style=\"float:left ;margin-left: 20px; margin-right: 20px\"><img src=\"/ifttt/img/weibo.png\"/></div>");
					}
					out.print("<div style=\"float:left\">Then</div>");
					if(taskitems[i].getThatType() == 1){
						out.print("<div style=\"float:left ;margin-left: 20px; margin-right: 20px\"><img src=\"/ifttt/img/weibo.png\"/></div>");
					}
					else if(taskitems[i].getThatType() == 2){
						out.print("<div style=\"float:left ;margin-left: 20px; margin-right: 20px\"><img src=\"/ifttt/img/email.png\"/></div>");	
					}
					
					if(taskitems[i].getStatus() == 1){
						out.print("<div style=\"float:right;\"><img src=\"/ifttt/img/check_true.png\" onclick=\"changeTaskComfirm("+taskid+")\"/></div></div>");
					}
					else{
						out.print("<div style=\"float:right;\"><img src=\"/ifttt/img/check_false.png\" onclick=\"changeTaskComfirm("+taskid+")\"/></div></div>");
					}
					out.print("<div style=\"float:right;\"><img src=\"/ifttt/img/delete_task.PNG\" onclick=\"deleteTaskComfirm("+taskid+")\"/></div><div class=\"clear\"></div><hr>");
				}
			 %>				
			</div>			
		</div>
	
	<div class="big_btn" id="big_btn_top" >
		<a href="/ifttt/Dashboard.jsp" style="color:fff">Back</a>
		<div class="clear"></div>
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
	</div>
	
	<div style="clear:both"></div>
	<br>
	<br>
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
