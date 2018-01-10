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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="settings.title" text="Settings" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="setting">

                    <div class="form-body theme-blue">

                        <label for="names" class="field-label"><spring:message code="settings.enterstusername" text="Enter Student Username" /> :</label>
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label class="field prepend-icon">
                                    <form:input path="studentname" id="studentname" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label class="field prepend-icon">
                                    <button type="submit" class="button btn-blue"><spring:message code="common.button.submit" text="Submit" /></button>
                                </label>
                            </div><!-- end section -->



                        </div><!-- end frm-row section -->



                    </div><!-- end .form-body section -->

                    <c:if test = "${records !=null}">

                        <div class="frm-row">
                            <div class="section colm colm12">
                            <display:table name="records" class="table table-bordered" requestURI="search.io" decorator="com.abp.admin.setting.SrcDecorator" pagesize="1">
                                <display:column property="studentname" titleKey="settings.username" />
                                <display:column property="enablelogin" titleKey="settings.enablelogin"/>
                                
                            </display:table>

                        </div></div>


                    </c:if>

                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>

</html>
