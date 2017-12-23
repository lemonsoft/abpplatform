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
                    <h4><i class="fa fa-pencil-square">Over All Batch Report</i></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->


                <div class="form-body theme-blue">
                    <div class="frm-row">
                        <div class="section colm colm6"> 
                            <div class="section">

                                <b>Sector Skills Council :</b>${sscid}
                            </div><!-- end section -->
                        </div><!-- end section -->
                         <div class="section colm colm6"> 
                            <div class="section">

                                <b>Qualification Pack :</b>${qpid}
                            </div><!-- end section -->
                        </div><!-- end section -->


                    </div><!-- end frm-row section -->


                    <div class="frm-row">
                        <display:table name="batchresult" class="table table-bordered" requestURI="initSearch.io"  pagesize="10">
                            <display:column property="caandidateid" title="Candidate ID" />
                            <display:column property="candidatename" title="Candidate Name" />
                            <display:column property="enrollmentno" title="Enrollment No" />
                            <display:column property="fathername" title="Name Of Father Or Husband" />
                            <display:column property="partnername" title="Partner Name" />
                            <display:column property="jobrole" title="JobRole" />
                            <display:column property="assesmentdate" title="Assessment Date" />
                            <display:column property="assesorid" title="Assessor ID" />
                            <display:column property="maxtheorymarks" title="Max Theory Marks" />
                            <display:column property="maxpracticalmarks" title="Max Practical Marks"/>
                            <display:column property="markstheory" title="Marks Theory" />
                            <display:column property="markspractical" title="Marks Practical"/>
                            <display:column property="result" title="Result"/>
                        </display:table>

                    </div>


                </div><!-- end .smart-forms section -->
            </div><!-- end .smart-wrap section -->
        </div><!-- end .smart-wrap section -->

    </body>

</html>
