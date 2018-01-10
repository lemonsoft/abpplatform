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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="user.srctitle" text="Users" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"><a href="<%=request.getContextPath()%>/admin/batches/init.io" class="button btn-primary block pushed expand"><spring:message code="showuser.batches" text="Batches" /> </a></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="search.io"  commandName="language">
                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><b><spring:message code="showuser.ssc" text="Sector Skill Council" /> :</b>${sscname}</label>
                                    <label class="field prepend-icon">
                                        <b><spring:message code="showuser.qpack" text="Qualification Pack" /> :</b>${qpname}
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div>


                        <div class="frm-row">
                            <div style="position: relative;right:2px;"><a href="exportexcel.io?batchid=2"><spring:message code="showuser.exportexcel" text="Export Excel" /></a></div>
                            <display:table name="importdata" class="table table-bordered" requestURI="showuser.io" decorator="com.abp.admin.batches.SrcDecorator"  pagesize="50">
                                <display:column property="photoname" titleKey="showuser.photo" />
                                <display:column property="enrollmentno" titleKey="showuser.enrollno" />
                                <display:column property="username" titleKey="showuser.username" />
                                <display:column property="traineename" titleKey="showuser.studentname" />
                                <display:column property="gender" titleKey="showuser.gender" />
                                <display:column property="dateofbirth" titleKey="showuser.dob" />
                                <display:column property="traineeadress" titleKey="showuser.address" />
                                <display:column property="district" titleKey="showuser.district" />
                                <display:column property="states" titleKey="showuser.state" />
                                <display:column property="contactno" titleKey="showuser.contactno" />
                                <display:column property="webcam" titleKey="showuser.webcam" />

                            </display:table>

                        </div>
                    </form:form>

                </div><!-- end .smart-forms section -->
            </div><!-- end .smart-wrap section -->
    </body>

</html>
