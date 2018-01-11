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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="jobrolemaster.title" text="Job Role Master" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="jobroledao">

                    <div class="form-body theme-blue">
                        <div class="frm-row">

                            <div class="section colm colm6">

                                <label for="names" class="field-label">From date</label>
                                <label class="field prepend-icon">
                                    <form:input path="fromdate" type="date" name="fromdate"/>
                                </label>

                            </div><!-- end section -->
                            <div class="section colm colm6">

                                <label for="names" class="field-label">To date</label>
                                <label class="field prepend-icon">
                                    <form:input path="todate" type="date" name="todate"/>
                                </label>

                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <c:if test="${records != null}"> 
                            <div class="frm-row">
                                <table><tr><td colspan=5 align=right><a href="#" onclick="writeExcelSheet();"><img src="<%=request.getContextPath()%>/assets/images/excel.ico" width=30px height=30px/></a></td></tr></table>
                                <display:table name="records" class="table table-bordered" requestURI="initSearch.io" pagesize="40">
                                    <display:column property="jobrole" title="Job Role" />
                                    <display:column property="theorypass" title="Theory Pass"/>
                                    <display:column property="theoryfail" title="Theory Failed"/>
                                    <display:column property="vivapass" title="Viva Pass"/>
                                    <display:column property="vivafail" title="Viva Failed"/>
                                    

                                </display:table>

                            </div>
                        </c:if>
                    </div><!-- end .form-body section -->

                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>


</html>
