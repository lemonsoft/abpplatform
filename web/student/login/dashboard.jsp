<%-- 
    Document   : dashboard
    Created on : 7 Dec, 2017, 1:07:03 PM
    Author     : ss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

<div class="form-body theme-blue">
    <div class="frm-row">
        <div class="section colm colm6"> 
            <div class="col-md-4 ">
                <!-- BEGIN Portlet PORTLET-->
                <div class="portlet box blue-hoki">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-gift"></i>Student Details </div>

                    </div>
                    <div class="portlet-body">
                        <div class="scroller" style="height:350px" data-rail-visible="1" data-rail-color="yellow" data-handle-color="#a1b2bd">
                            <div><img src="<%=request.getContextPath()%>/assets/images/NoPhotoAvailable.jpg" class="circle"></div>
                            <table class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">
                                <tr><td>Father :</td><td>${father}</td></tr>
                                <tr><td>State :</td><td>${state}</td></tr>
                                <tr><td>District :</td><td>${district}</td></tr>
                                <tr><td>Enrollment no.:</td><td>${enrollno}</td></tr>

                            </table>
                        </div>
                    </div>
                </div>
                <!-- END Portlet PORTLET-->

            </div><!-- end section -->
        </div><!-- end section -->
        <div class="section colm colm6"> 
            <div class="col-md-8">
                <!-- BEGIN Portlet PORTLET-->
                <div class="portlet box blue-hoki">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-gift"></i>Assessment Details </div>

                    </div>
                    <div class="portlet-body">
                        <div class="scroller" style="height:350px" data-rail-visible="1" data-rail-color="yellow" data-handle-color="#a1b2bd">
                            <table class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">
                                <tr><td>Quality Pack :</td><td>${qpack}</td></tr>
                                <tr><td>Assessment Proposed Date :</td><td>${assesmentdate}</td></tr>
                                <tr><td>Center Contact No :</td><td>${centercontactno}</td></tr>
                                <tr><td>Center Address :</td><td>${centeraddr}</td></tr>
                                <tr><td>Sector Skill Council :</td><td>${ssc}</td></tr>
                                <tr><td>Training Partner :</td><td>${trainingpartner}</td></tr>
                                <tr><td colspan="2">I here by declare that the above information is true to my knowledge. </td></tr>
                            </table>
                            <center><button onclick="gotoInstruction();" class="button btn-primary block pushed expand">Exam Instruction</button></center>
                        </div>
                    </div>
                </div>
                <!-- END Portlet PORTLET-->

            </div><!-- end section -->
        </div><!-- end section -->
    </div>
    <script>

        function gotoInstruction() {
            
            window.location.href = "<%=request.getContextPath()%>/student/login/examinstruction.io";

        }

    </script>                           
</div><!-- end .form-body section -->