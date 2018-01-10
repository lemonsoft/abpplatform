<%-- 
    Document   : examinstruction
    Created on : 8 Dec, 2017, 10:57:57 AM
    Author     : ss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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

    </head>
    <body>
       <div class="section colm colm12"> 
            <div class="col-md-12">
                <!-- BEGIN Portlet PORTLET-->
                <div class="portlet box blue-hoki">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-gift"></i><spring:message code="instruction.title" text="Exam Instructions" />: </div>

                    </div>
                    <div class="portlet-body">
                        <div class="scroller" style="height:350px" data-rail-visible="1" data-rail-color="yellow" data-handle-color="#a1b2bd">
                            <ul>
                                <li><span class="field-icon"><i class="fa fa-location-arrow"></i></span><spring:message code="instruction.note1" text="The exam has fixed number of questions and allotted time. The time remaining for the exam is shown on the right corner of the screen throughout the test." /></li>
                                <li><span class="field-icon"><i class="fa fa-star-o"></i></span><spring:message code="instruction.note2" text="The questions are available in English and Hindi. In special cases, the paper may also be available in other languages. Please click on “Select Language” and click on submit. The questions will appear in the selected language." /></li>
                                <li><span class="field-icon"><i class="fa fa-star-o"></i></span><spring:message code="instruction.note3" text="On the left side panel, you can view the question numbers and can answer the questions in any sequence. However, you need to answer all questions to complete the exam." /></li>
                                <li><span class="field-icon"><i class="fa fa-star-o"></i></span><spring:message code="instruction.note4" text="You can view and edit previously given answers to the questions BEFORE final submission of the exam. Once you have answered all questions and clicked on Finish button, you cannot come back to the exam." /></li>
                                <li><span class="field-icon"><i class="fa fa-star-o"></i></span><spring:message code="instruction.note5" text="There is no negative marking for wrong answers." /></li>
                                <li><span class="field-icon"><i class="fa fa-star-o"></i></span><spring:message code="instruction.note6" text="In case you have any difficulty in understanding the question or have any other difficulty, you may speak to the assessor regarding the same. You are not allowed to talk to fellow candidates or any other person regarding the same." /></li>
                                <li><span class="field-icon"><i class="fa fa-star-o"></i></span><spring:message code="instruction.note7" text="Use of any electronic gadgets like calculator, mobile, MS-excel etc or any other kind of reference during the test is strictly not allowed." /></li>
                                <li><span class="field-icon"><i class="fa fa-star-o"></i></span><spring:message code="instruction.note8" text="You are expected to take the test in utmost honesty and integrity. If you are found doing any malpractice or cheating, your test is liable to be cancelled." /></li>
                                <li><spring:message code="instruction.note9" text="You test will start in few minutes. Please go through the instructions carefully before you start the test." /></li>
                            </ul>
                            <center><button onclick="startExam();" class="button btn-primary block pushed expand"><spring:message code="instruction.note10" text="Start Exam" /></button></center>
                        </div>
                    </div>
                </div>
                <!-- END Portlet PORTLET-->

            </div><!-- end section -->
        </div><!-- end section -->
        <script>

        function startExam() {
            
            window.location.href = "<%=request.getContextPath()%>/student/exam/startexam.io";

        }

    </script> 
    </body>
</html>
