<%-- 
    Document   : common
    Created on : 8 Nov, 2017, 10:29:42 AM
    Author     : ss
--%>

<%@page pageEncoding="UTF-8"%>
<%
    String body = (String) request.getSession().getAttribute("body");
    System.out.println("body page..."+ body);
%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<tiles:insert page="/common/assessor/layout.jsp" flush="true">

    <tiles:put name="header" value="/common/assessor/header.jsp" />

    <tiles:put name="body" value="<%=body%>" />
    <tiles:put name="footer" value="/common/assessor/footer.jsp" />
</tiles:insert>