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
                    <h4><i class="fa fa-pencil-square"><spring:message code="noswiseresult.title" text="NOS Report" /></i></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->


                <div class="form-body theme-blue">
                    <div class="frm-row">
                        <div class="section colm colm4"> 
                            <div class="section">

                                <b><spring:message code="noswiseresult.sscid" text="Sector Skills Council" /> :</b>${sscid}
                            </div><!-- end section -->
                        </div><!-- end section -->
                        <div class="section colm colm4"> 
                            <div class="section">

                                <b><spring:message code="noswiseresult.qpid" text="Qualification Pack" /> :</b>${qpid}
                            </div><!-- end section -->
                        </div><!-- end section -->
                        <div class="section colm colm4"> 
                            <div class="section">

                                <b><spring:message code="noswiseresult.batchid" text="Batch ID" /> :</b>${batchid}
                            </div><!-- end section -->
                        </div><!-- end section -->


                    </div><!-- end frm-row section -->
                    <div class="frm-row">
                        <table class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">
                            <tr>
                                <c:forEach var="headers" items="${headers}">
                                    <td>${headers}</td>
                                </c:forEach>

                            </tr>
                            <tr>
                                <c:forEach var="totalnosresult" items="${totalrecords}">
                                <tr><td>${totalnosresult.enrollmentno}</td>
                                    <td>${totalnosresult.traineename}</td>
                                    <c:forEach var="nosresult" items="${totalnosresult.nosdetails}">
                                    <td>${nosresult.nostheory}</td>
                                    <td>${nosresult.nospractical}</td>
                                    <td>${nosresult.total}</td>
                                    </c:forEach>
                                    <td>${totalnosresult.totaltheorymarks}</td>
                                    <td>${totalnosresult.totalpracticalmarks}</td>
                                    <td>${totalnosresult.totalmarks}</td>
                                    <td>${totalnosresult.result}</td>
                                </tr>
                            </c:forEach>




                        </table> 

                    </div>
                </div><!-- end .smart-forms section -->
            </div><!-- end .smart-wrap section -->
        </div><!-- end .smart-wrap section -->

    </body>

</html>
