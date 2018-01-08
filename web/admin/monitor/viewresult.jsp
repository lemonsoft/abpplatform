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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="viewresult.title" text="Student Live Report" /></h4>
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
                                <label for="names" class="field-label"><spring:message code="viewresult.studentname" text="Student Name" /> :${studentname}</label>
                                <label for="names" class="field-label"><spring:message code="viewresult.enrollno" text="Enrollment No" />:${enrollmentno}</label>
                            </div><!-- end section -->
                        </div><!-- end section -->

                    </div>

                    <div class="frm-row">


                        <table class="table table-bordered" cellpadding="2" cellspacing="2" border="1">

                            <tr><td><spring:message code="viewresult.starttime" text="Exam Start Time" /> : ${startdatetime}</td><td><spring:message code="viewresult.endtime" text="Exam End Time" /> :  ${enddatetime}</td><td><spring:message code="viewresult.examstatus" text="Exam Status" /> :  ${examstatus}</td></tr>
                            <tr>  <td><spring:message code="viewresult.totaltime" text="Total Time" /> :  ${totaltime}</td><td><spring:message code="viewresult.timetaken" text="Time Taken" /> :  ${timetaken}</td><td><spring:message code="viewresult.remainingquestion" text="Remaining Questions" /> :  ${remainingquestion}</td></tr>
                            <tr>  <td><spring:message code="viewresult.ipaddress" text="IP Address" /> :  ${ipaddress}</td><td><spring:message code="viewresult.browser" text="Browser Version" /> : ${browser}</td><td><spring:message code="viewresult.noofquestion" text="No of Questions Answered" /> :  ${totalanswer}</td></tr>

                        </table>

                        <display:table name="datatheorywise" class="table table-bordered" requestURI="totalreport.io"  pagesize="50">
                            <display:column property="sno" titleKey="viewresult.sno" />
                            <display:column property="question" titleKey="viewresult.question"/>
                            <display:column property="option1" titleKey="viewresult.option1"/>
                            <display:column property="option2" titleKey="viewresult.option2"/>
                            <display:column property="option3" titleKey="viewresult.option3"/>
                            <display:column property="option4" titleKey="viewresult.option4"/>
                            <display:column property="correctanswer" titleKey="viewresult.correctanswer"/>
                            <display:column property="selectedanswer" titleKey="viewresult.selectedanswer"/>
                            <display:column property="marks" titleKey="viewresult.marks"/>
                            <display:column property="scoredmarks" titleKey="viewresult.scoredmarks"/>
                            <display:column property="timetaken" titleKey="viewresult.timetaken"/>
                            <display:column property="reviewlater" titleKey="viewresult.reviewlater"/>
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
