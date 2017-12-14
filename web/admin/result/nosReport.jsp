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
                    <h4><i class="fa fa-pencil-square"></i>NOS wise Report</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

              

                    <div class="form-body theme-blue">


                        <div class="frm-row">
                            <display:table name="noswiselog" class="table table-bordered" requestURI="nosreport.io"  pagesize="50">
                                <display:column property="nosid" title="NOSID" />
                                <display:column property="theorymarks" title="Theory Marks"/>
                                <display:column property="theorycutoff" title="Theory Cutoff"/>
                                <display:column property="scoredtheorymarks" title="Scored Theory Marks"/>
                                <display:column property="practicalmarks" title="Practical Marks"/>
                                <display:column property="practicalcutoff" title="Practical Cutoff"/>
                                <display:column property="practicalscoredmarks" title="Scored Practical Marks"/>
                                <display:column property="totalmarks" title="Total Marks"/>
                                <display:column property="overallcutoff" title="Overall Cutoff"/>
                                <display:column property="scoredtotalmarks" title="Scored Total Marks"/>
                                <display:column property="weightedavg" title="Weighted Average"/>
                                <display:column property="scoredweightavg" title="Scored Weighted Average"/>
                                <display:column property="result" title="Results"/>
                            </display:table>


                        </div><!-- end frm-row section -->



                    </div><!-- end .form-body section -->

              

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>

</html>
