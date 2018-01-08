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
    <body>

        <div class="smart-wrap">
            <div class="smart-forms smart-container wrap-full">

                <div class="form-header header-blue">
                    <h4><i class="fa fa-pencil-square"><spring:message code="batches.title" text="Over All Batch Report" /></i></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->


                <div class="form-body theme-blue">
                    <div class="frm-row">
                        <div class="section colm colm6"> 
                            <div class="section">

                                <b><spring:message code="batches.title" text="Sector Skills Council" /> :</b>${sscid}
                            </div><!-- end section -->
                        </div><!-- end section -->
                         <div class="section colm colm6"> 
                            <div class="section">

                                <b><spring:message code="batches.title" text="Qualification Pack" /> :</b>${qpid}
                            </div><!-- end section -->
                        </div><!-- end section -->


                    </div><!-- end frm-row section -->


                    <div class="frm-row">
                        <display:table name="batchresult" class="table table-bordered" requestURI="initSearch.io"  pagesize="10">
                            <display:column property="caandidateid" titleKey="finalresult.caandidateid" />
                            <display:column property="candidatename" titleKey="finalresult.candidatename" />
                            <display:column property="enrollmentno" titleKey="finalresult.enrollmentno" />
                            <display:column property="fathername" titleKey="finalresult.fathername" />
                            <display:column property="partnername" titleKey="finalresult.partnername" />
                            <display:column property="jobrole" titleKey="finalresult.jobrole" />
                            <display:column property="assesmentdate" titleKey="finalresult.assesmentdate" />
                            <display:column property="assesorid" titleKey="finalresult.assesorid" />
                            <display:column property="maxtheorymarks" titleKey="finalresult.maxtheorymarks" />
                            <display:column property="maxpracticalmarks" titleKey="finalresult.maxpracticalmarks"/>
                            <display:column property="markstheory" titleKey="finalresult.markstheory" />
                            <display:column property="markspractical" titleKey="finalresult.markspractical"/>
                            <display:column property="result" titleKey="finalresult.result"/>
                        </display:table>

                    </div>


                </div><!-- end .smart-forms section -->
            </div><!-- end .smart-wrap section -->
        </div><!-- end .smart-wrap section -->

    </body>

</html>
