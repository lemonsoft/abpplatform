<%-- 
    Document   : addlanguage
    Created on : 9 Nov, 2017, 2:29:37 PM
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
    <body >

        <div class="smart-wrap">
            <div class="smart-forms smart-container wrap-full">

                <div class="form-header header-blue">
                    <h4><i class="fa fa-pencil-square"></i>Exam Finished</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->
                <div class="form-body theme-blue">


                    <div class="frm-row">

                        <div class="section colm colm12">
                            <label class="field prepend-icon">
                                <center><h2>You have already attempted exam once !..</h2></center>
                            </label>

                            <center> <button id="finishexam" class="button btn-primary block pushed expand">&nbsp;Close&nbsp;</button></center>
                        </div><!-- end section -->



                    </div><!-- end frm-row section -->



                </div><!-- end .form-body section -->


            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->

        <script>

            $("#finishexam").click(function () {
                 window.location.href = "<%=request.getContextPath()%>/student/login/closeexam.io";
            });
        </script>
    </body>

</html>
