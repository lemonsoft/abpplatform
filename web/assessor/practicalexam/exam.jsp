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
                    <h4><i class="fa fa-pencil-square"></i>Practical Paper</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="language">
                    <input type="hidden" name="userid" id="userid" value="${userid}"/>
                    <input type="hidden" name="batchid" id="batchid" value="${batchid}"/>

                    <div class="form-body theme-blue">

                        <div class="frm-row">
                            <div class="section colm colm6"><b>Student Name :</b>${studentname}</div>
                            <div class="section colm colm6"><b>Enrollment No :</b>${enrollno}</div>
                        </div>
                        <div class="frm-row">

                            <div class="section colm colm12">
                                <table class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">
                                    <tr><td>Senario</td><td></td></tr>

                                    <c:forEach var="senerio" items="${questionlist}">
                                        <tr><td>${senerio.key}</td>
                                            <td>

                                                <display:table name="${senerio.value}" class="table table-bordered" decorator="com.abp.assessor.practicalexam.SrcDecorator" requestURI="initSearch.io"  pagesize="5">
                                                    <display:column property="question" title="Question"  style="word-break:break-all;"/>
                                                    <display:column property="options" title="Actions" />
                                                </display:table>

                                            </td> </tr>

                                    </c:forEach>
                                </table>           
                            </div><!-- end section -->



                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm12"><span id="result"></span></div>
                        </div>


                    </div><!-- end .form-body section -->
                    <div class="form-footer">
                        <c:if test = "${mode == 'add'}">
                            <button type="button" onclick="saveAnswer();" class="button btn-blue"><spring:message code="common.button.submit" text="Submit" /></button>
                        </c:if>
                        <c:if test = "${mode=='update'}">
                            <button type="submit" class="button btn-blue"><spring:message code="common.button.update" text="Update" /></button>
                        </c:if>
                        <button type="button" onclick="window.close();" class="button btn-blue"><spring:message code="common.button.cancel" text="Cancel" /></button>
                    </div><!-- end .form-footer section -->
                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>
    <script>

        function saveAnswer() {

            $(':radio:checked').each(function () {
                var userid = $('#userid').val();
                var batchid = $('#batchid').val();
                var quesno = $(this).attr("id");
                var answer = $(this).val();

                $.ajax({
                    url: "saveanswer.io",
                    data: {userid: userid, batchid: batchid, quesno: quesno, answer: answer},
                    type: 'GET',
                    success: function (data) {
                        //alert(data.status);
                        if (data.status == 'save') {
                            $('#result').text("Result Saved Successfully");
                        }
                        if (data.status == 'update') {
                            $('#result').text("Result Update Successfully");
                        }
                        if (data.status == 'fail') {
                            $('#result').text("Result Saved Failed");
                        }
                    }
                });

            });

        }

    </script>
</html>
