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

    </head>
    <body >

        <div class="smart-wrap">
            <div class="smart-forms smart-container wrap-full">

                <div class="form-header header-blue">
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="nosreport.title" text="NOS wise Report" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

              

                    <div class="form-body theme-blue">


                        <div class="frm-row">
                            <display:table name="noswiselog" class="table table-bordered" requestURI="nosreport.io"  pagesize="50">
                                <display:column property="nosid" titleKey="nosreport.nosid" />
                                <display:column property="theorymarks" titleKey="nosreport.theorymarks"/>
                                <display:column property="theorycutoff" titleKey="nosreport.theorycutoff"/>
                                <display:column property="scoredtheorymarks" titleKey="nosreport.scoredtheorymarks"/>
                                <display:column property="practicalmarks" titleKey="nosreport.practicalmarks"/>
                                <display:column property="practicalcutoff" titleKey="nosreport.practicalcutoff"/>
                                <display:column property="practicalscoredmarks" titleKey="nosreport.practicalscoredmarks"/>
                                <display:column property="totalmarks" titleKey="nosreport.totalmarks"/>
                                <display:column property="overallcutoff" titleKey="nosreport.overallcutoff"/>
                                <display:column property="scoredtotalmarks" titleKey="nosreport.scoredtotalmarks"/>
                                <display:column property="weightedavg" titleKey="nosreport.weightedavg"/>
                                <display:column property="scoredweightavg" titleKey="nosreport.scoredweightavg"/>
                                <display:column property="result" titleKey="nosreport.result"/>
                            </display:table>


                        </div><!-- end frm-row section -->



                    </div><!-- end .form-body section -->

              

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>

</html>
