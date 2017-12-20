<%-- 
    Document   : addlanguage
    Created on : 9 Nov, 2017, 2:29:37 PM
    Author     : ss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/smart-forms.css">
        <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/smart-themes/blue.css">
        <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/font-awesome.min.css">


        <!--[if lte IE 9]>
            <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>    
            <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
        <![endif]-->    

        <!--[if lte IE 8]>
            <link type="text/css" rel="stylesheet" href="css/smart-forms-ie8.css">
        <![endif]-->
        <style>
            .circle {
                display: block;
                width: 150px;
                height: 150px;
                margin: 1em auto;
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center center;
                -webkit-border-radius: 99em;
                -moz-border-radius: 99em;
                border-radius: 99em;
                border: 5px solid #eee;
                box-shadow: 0 3px 2px rgba(0, 0, 0, 0.3);  
            }
        </style>
    </head>
    <body >

        <div class="smart-wrap">
            <div class="smart-forms smart-container wrap-full">

                <div class="form-header header-blue">
                    <h4><i class="fa fa-pencil-square"></i>Student Live Report</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <div class="form-body theme-blue">
                    <div class="frm-row">
                        <div class="section colm colm4"> 
                            <div class="section">
                                <img src="<%=request.getContextPath()%>/assets/images/NoPhotoAvailable.jpg" width="150px;" height="150px;" class="circle"/>
                            </div><!-- end section -->
                        </div><!-- end section -->
                        <div class="section colm colm4"> 
                            <div class="section">
                                <label for="names" class="field-label">Student Name :${studentname}</label>
                                <label for="names" class="field-label">Enrollment No:${enrollmentno}</label>
                            </div><!-- end section -->
                        </div><!-- end section -->

                    </div>

                    <div class="frm-row">


                        <table class="table table-bordered" cellpadding="2" cellspacing="2" border="1">

                            <tr><td>Exam Start Time : ${startdatetime}</td><td>Exam End Time :  ${enddatetime}</td><td>Exam Status :  ${examstatus}</td></tr>
                            <tr>  <td>Total Time :  ${totaltime}</td><td>Time Taken :  ${timetaken}</td><td>Remaining Questions :  ${remainingquestion}</td></tr>
                            <tr>  <td>IP Address :  ${ipaddress}</td><td>Browser Version : ${browser}</td><td>No of Questions Answered :  ${totalanswer}</td></tr>

                        </table>

                        <display:table name="datatheorywise" class="table table-bordered" requestURI="totalreport.io"  pagesize="50">
                            <display:column property="sno" title="Sno" />
                            <display:column property="question" title="Question"/>
                            <display:column property="option1" title="Option1"/>
                            <display:column property="option2" title="Option2"/>
                            <display:column property="option3" title="Option3"/>
                            <display:column property="option4" title="Option4"/>
                            <display:column property="correctanswer" title="Correctanswer"/>
                            <display:column property="selectedanswer" title="Selectedanswer"/>
                            <display:column property="marks" title="Marks"/>
                            <display:column property="scoredmarks" title="Scoredmarks"/>
                            <display:column property="timetaken" title="Timetaken"/>
                            <display:column property="reviewlater" title="Reviewlater"/>
                        </display:table>
                        <table class="table table-bordered" cellpadding="2" cellspacing="2" border="1">
                            <tr><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td ><b>Total :</b> ${totalmarks}</td><td ><b>Scored :</b>${totaltheorymarks}</td><td >&nbsp;</td><td >&nbsp;</td></tr>
                        </table>

                    </div><!-- end frm-row section -->

                </div><!-- end .form-body section -->

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>

</html>
