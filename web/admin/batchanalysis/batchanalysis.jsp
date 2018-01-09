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
                    <h4><i class="fa fa-pencil-square"></i>Batch Analysis</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="batchanalysisdao">

                    <div class="form-body theme-blue">


                        <div class="frm-row">

                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label">Enter BatchID :</label>
                                    <label class="field prepend-icon">

                                        <form:input path="batchid" type="text" id="batchid" class="gui-input"/>
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm2"> 
                                <div class="section">
                                    <label for="names" class="field-label">&nbsp;</label>
                                    <label class="field prepend-icon"><button type="submit" class="button btn-blue"><spring:message code="common.button.submit" text="Submit" /></button></label>
                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <c:if test="${records != null}"> 
                            <div class="frm-row">
                                <table><tr><td colspan=5 align=right><a href="#" onclick="writeExcelSheet();"><img src="<%=request.getContextPath()%>/assets/images/excel.ico" width=30px height=30px/></a></td></tr></table>
                                                <display:table name="records" class="table table-bordered" requestURI="initSearch.io" pagesize="40">
                                                    <display:column property="srno" title="Sr.#" />
                                                    <display:column property="candidatename" title="Candidate Name"/>
                                                    <display:column property="enrollmentno" title="Enrollment No"/>
                                                    <display:column property="attendance" title="Attendance"/>
                                                    <display:column property="theorymarks" title="Theory Marks"/>
                                                    <display:column property="vivamarks" title="Viva Marks"/>
                                                    <display:column property="noswisemarks" title="NOS Wise Marks"/>
                                                    <display:column property="noofquestionattempted" title="No. Of Question Attempted"/>
                                                    <display:column property="noofincorrectanswer" title="No.Of In-correct Answers"/>
                                                    <display:column property="noofcorrectanswer" title="No.Of Correct Answers"/>
                                                    <display:column property="totaltimetaken" title="Total Time Taken"/>
                                                    <display:column property="result" title="Result"/>


                                </display:table>

                            </div>
                        </c:if>


                    </div><!-- end .form-body section -->



                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>

</html>
