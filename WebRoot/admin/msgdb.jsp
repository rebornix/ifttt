<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Public Message Dashboard</title>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="/ifttt/admin/resources/css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="/ifttt/admin/resources/css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="/ifttt/admin/resources/css/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="/ifttt/admin/resources/scripts/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="/ifttt/admin/resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="/ifttt/admin/resources/scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="/ifttt/admin/resources/scripts/jquery.wysiwyg.js"></script>
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript" src="/ifttt/admin/resources/scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="/ifttt/admin/resources/scripts/jquery.date.js"></script>
</head>
<body>
<%@ page session="true"%>
<%@ page import="java.io.Serializable" %>
<%@ page import="Bean.*" %>
<jsp:useBean id="adminmessagebean" scope="session" class="Bean.AdminMessageBean" type ="Bean.AdminMessageBean"/>
<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  <div id="sidebar">
    <div id="sidebar-wrapper">
      <!-- Sidebar with logo and menu -->
      <h1 id="sidebar-title"><a href="#">Admin</a></h1>
      <!-- Logo (221px wide) -->
      <a href="#"><img id="logo" src="/ifttt/admin/resources/images/rix.jpg" style="height:110px; width:110px;margin:50px;"alt="Simpla Admin logo" /></a>
      <!-- Sidebar Profile links -->
      <div id="profile-links">Hi <%= session.getAttribute("username") %>,  you have <a href="#messages" rel="modal" title="3 Messages">1 Messages</a><br />
        <br />
        <a href="/ifttt/index.jsp" title="View the Site">View the Site</a> | <a href="#" title="Sign Out">Sign Out</a> </div>
      <ul id="main-nav">
        <!-- Accordion Menu -->
        <li> <a href="#" class="nav-top-item no-submenu">
          <!-- Add the class "no-submenu" to menu items with no sub menu -->
          Dashboard </a> </li>
        <li> <a href="#" class="nav-top-item">
          <!-- Add the class "current" to current menu item -->
          Articles </a>
          <ul>
            <li><a href="/ifttt/admin/db.jsp">Manage User Info</a></li>
            <!-- Add class "current" to sub menu items also -->   
	  </ul>
        </li>
        <li> <a href="#" class="nav-top-item  current"> Messages </a>
          <ul>
	    <li><a href="#" class="current">Manage Public Messages</a></li>
            <li><a href="/ifttt/admin/pmsgdb.jsp" >Manage User Messages</a></li>
          </ul>
        </li>
       
      </ul>
      <!-- End #main-nav -->

      <div id="messages" style="display: none">
        <!-- Messages are shown when a link with these attributes are clicked: href="#messages" rel="modal"  -->
        <h3>1 Messages</h3>
        <p> <strong>16th Dec 2011</strong> by Admin<br />
          <small>Hei Bird!<a href="#" class="remove-link" title="Remove message">Remove</a></small> </p>
        <form action="/ifttt/servlet/SendMessage" method="post">
          <h4>New Message</h4>
          <fieldset>
          <textarea class="textarea" name="textfield" cols="79" rows="5"></textarea>
          </fieldset>
          <fieldset>
          <select name="dropdown" class="small-input">
            <option value="option1">Send to...</option>
            <option value="option2">Everyone</option>
            <option value="option3">Admin</option>
            <option value="option4">Jane Doe</option>
          </select>
          <input class="button" type="submit" value="Send" />
          </fieldset>
        </form>
      </div>
      <!-- End #messages -->
    </div>
  </div>
  <!-- End #sidebar -->


  <div id="main-content">
    <!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
    <div class="notification error png_bg">
      <div> Javascript is disabled or is not supported by your browser. Please <a href="http://browsehappy.com/" title="Upgrade to a better browser">upgrade</a> your browser or <a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable Javascript in your browser">enable</a> Javascript to navigate the interface properly.
        Download From <a href="http://www.exet.tk">exet.tk</a></div>
    </div>
    </noscript>
    <!-- Page Head -->
    <h2>Morning My Lord! </h2>
    <p id="page-intro">What would you like to do?</p>
    <ul class="shortcut-buttons-set">
      <li><a class="shortcut-button" href="#"><span> <img src="/ifttt/admin/resources/images/icons/pencil_48.png" alt="icon" /><br />
        Write a new Public message </span></a></li>
      <li><a class="shortcut-button" href="#"><span> <img src="/ifttt/admin/resources/images/icons/clock_48.png" alt="icon" /><br />
        Add an Event </span></a></li>
    </ul>
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>Public Message List</h3>
        <ul class="content-box-tabs">
          <li><a href="#tab1" class="default-tab">Table</a></li>
          <!-- href must be unique and match the id of target div -->
          
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->


<!-- Differ from other admin dashboard-->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <!-- This is the target div. id must match the href of this div's tab -->
          
          <table>
            <thead>
              <tr>
                <th>
                  <input class="check-all" type="checkbox" />
                </th>
                <th>Topic</th>
                <th>Content</th>
                <th>Time</th>
                <th>Operation</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                  <div class="bulk-actions align-left">
                    <select name="dropdown">
                      <option value="option1">Choose an action...</option>
                      <option value="option2">Edit</option>
                      <option value="option3">Delete</option>
                    </select>
                    <a class="button" href="#">Apply to selected</a> </div>
                  
                  
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>
            <% 
              adminmessagebean.setMessages();
              int amount = adminmessagebean.getAmount();
              String[] subjects = new String[amount];
              String[] times = new String[amount];
              String[] contents = new String[amount];
           	  
              subjects = adminmessagebean.getSubjects();
              times = adminmessagebean.getSendtimes();
              contents = adminmessagebean.getContend();
             
              for(int i = 0 ; i < amount; i++){
              %><tr>
                <td>
                  <input type="checkbox" />
                </td>
                <td title="title">
              <%
              	out.print(subjects[i]);%>
              	</td>
                <td><a href="#" >
              	<%
                out.print(contents[i]); 
              	%>
              	</a></td>
                <td>
              	<%
              	out.print(times[i]);%>
              	</td>
                <td>
                  <!-- Icons -->
                  <a href="#" title="Edit"><img src="/ifttt/admin/resources/images/icons/pencil.png" alt="Edit" /></a> <a href="#" title="Delete"><img src="/ifttt/admin/resources/images/icons/cross.png" alt="Delete" /></a> <a href="#" title="Edit Meta"><img src="/ifttt/admin/resources/images/icons/hammer_screwdriver.png" alt="Edit Meta" /></a> </td>
              </tr>
              	<%
              } %>
              
              
		
            </tbody>
          </table>
        </div>
        <!-- End #tab1 -->


        <div class="tab-content" id="tab2">
          <form action="#" method="post">
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
              <label>Small form input</label>
              <input class="text-input small-input" type="text" id="small-input" name="small-input" />
              <span class="input-notification success png_bg">Successful message</span>
              <!-- Classes for input-notification: success, error, information, attention -->
              <br />
              <small>A small description of the field</small> </p>
            <p>
              <label>Medium form input</label>
              <input class="text-input medium-input datepicker" type="text" id="medium-input" name="medium-input" />
              <span class="input-notification error png_bg">Error message</span> </p>
            <p>
              <label>Large form input</label>
              <input class="text-input large-input" type="text" id="large-input" name="large-input" />
            </p>
            <p>
              <label>Checkboxes</label>
              <input type="checkbox" name="checkbox1" />
              This is a checkbox
              <input type="checkbox" name="checkbox2" />
              And this is another checkbox </p>
            <p>
              <label>Radio buttons</label>
              <input type="radio" name="radio1" />
              This is a radio button<br />
              <input type="radio" name="radio2" />
              This is another radio button </p>
            <p>
              <label>This is a drop down list</label>
              <select name="dropdown" class="small-input">
                <option value="option1">Option 1</option>
                <option value="option2">Option 2</option>
                <option value="option3">Option 3</option>
                <option value="option4">Option 4</option>
              </select>
            </p>
            <p>
              <label>Textarea with WYSIWYG</label>
              <textarea class="text-input textarea wysiwyg" id="textarea" name="textfield" cols="79" rows="15"></textarea>
            </p>
            <p>
              <input class="button" type="submit" value="Submit" />
            </p>
            </fieldset>
            <div class="clear"></div>
            <!-- End .clear -->
          </form>
        </div>
        <!-- End #tab2 -->
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box -->
   
    <div class="clear"></div>
   
    <div id="footer"> <small>
      <!-- Remove this notice or replace it with whatever you want -->
      &#169; Copyright 2011 Rebornix | Powered by 5A238 </small> </div>
    <!-- End #footer -->
  </div>
  <!-- End #main-content -->
</div>
</body>
<!-- Download From www.exet.tk-->
</html>
