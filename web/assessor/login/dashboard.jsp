<%-- 
    Document   : dashboard
    Created on : 7 Dec, 2017, 1:07:03 PM
    Author     : ss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/displaytag.tld" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
                            <i class="fa fa-gift"></i><spring:message code="assessor.dashboard" text="Assessor Details" /> </div>

                    </div>
                    <div class="portlet-body">
                        <div class="scroller" style="height:350px" data-rail-visible="1" data-rail-color="yellow" data-handle-color="#a1b2bd">
                            <div><img src="<%=request.getContextPath()%>/assets/images/NoPhotoAvailable.jpg" class="circle"></div>
                            <table class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">
                                <tr><td><spring:message code="assessor.name" text="Name" /> :</td><td>${name}</td></tr>
                                <tr><td><spring:message code="assessor.qualification" text="Qualification" /> :</td><td>${qualification}</td></tr>
                                <tr><td><spring:message code="assessor.state" text="State" /> :</td><td>${state}</td></tr>
                                <tr><td><spring:message code="assessor.district" text="District" />:</td><td>${district}</td></tr>
                                <tr><td><spring:message code="assessor.emailid" text="Email ID" />:</td><td>${emailid}</td></tr>

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
                            <i class="fa fa-gift"></i><spring:message code="assessor.mybatches" text="My Batches" /></div>

                    </div>
                    <div class="portlet-body">
                        <div class="scroller" style="height:350px" data-rail-visible="1" data-rail-color="yellow" data-handle-color="#a1b2bd">
                            <display:table name="records" class="table table-bordered" requestURI="initSearch.io" decorator="com.abp.assessor.login.SrcDecorator" pagesize="10">
                                <display:column property="batchid" titleKey="assessor.batchid" />
                                <display:column property="tpname" titleKey="assessor.tpname" />
                                <display:column property="batchsize" titleKey="assessor.batchsize" />
                                <display:column property="centeraddr" titleKey="assessor.centeraddress" />
                                <display:column property="startdate" titleKey="assessor.examstartdate" />
                                <display:column property="enddate" titleKey="assessor.examenddate" />
                                <display:column property="attendance" titleKey="assessor.attendance" />
                            </display:table>
                        </div>
                    </div>
                </div>
                <!-- END Portlet PORTLET-->

            </div><!-- end section -->
        </div><!-- end section -->
    </div>
    <script>
        function attendance(id) {
            alert(" Take Attendance " + id);
            var w = window.open("<%=request.getContextPath()%>/assessor/auth/captureImage.io?batchid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
        }


    </script>                           
</div><!-- end .form-body section -->