<%-- 
    Document   : baseLayout
    Created on : Dec 19, 2008, 1:28:41 AM
    Author     : eswar@vaannila.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>

<html>
    <head>
        
        <title><spring:message code="welcome.title" text="TAG" /></title>
        <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/smart-forms.css">    
        <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/smart-themes/purple.css"> 
    </head>
    <body style="background-color: #f1f1f1;">
        <table width="100%" height="100%" align="center">
            <tr>
                <td height="20%" colspan="2">
                    <tiles:insert attribute="header" ignore="true" />
                </td>
            </tr>
            <tr>

                <td>
                    <tiles:insert attribute="body" />
                </td>
            </tr>
            <tr>
                <td height="20%" colspan="2">
                    <tiles:insert attribute="footer" />
                </td>
            </tr>
        </table>
    </body>
</html>
