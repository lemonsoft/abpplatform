<%-- 
    Document   : addlanguage
    Created on : 9 Nov, 2017, 2:29:37 PM
    Author     : ss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
            <div class="smart-forms smart-container wrap-2">

                <div class="form-header header-blue">
                    <h4><i class="fa fa-pencil-square"></i>Question Bank Analysis</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="qbankanalysisdao">
                    <form:hidden path="id" />
                    <div class="form-body theme-blue">


                        <div class="frm-row">

                            <div class="section colm colm4">
                                <label for="names" class="field-label">Sector Skill Council:</label>
                                <label class="field prepend-icon">
                                    <form:select path="sscid" name="sscid" id="sscid" class="gui-input" >
                                        <form:option value="">--Select--</form:option>
                                        <form:options items="${ssc}"/>
                                    </form:select>
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <label for="names" class="field-label">Job Role:</label>
                                <label class="field prepend-icon">
                                    <form:select path="qpackid" name="qpackid" id="qpackid" class="gui-input" >
                                        <form:option value="0">--Select--</form:option>
                                    </form:select>
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <label for="names" class="field-label">Month:</label>
                                <label class="field prepend-icon">
                                    <form:input path="month" type="month" id="month" class="gui-input"/>
                                </label>
                            </div><!-- end section -->
                            
                        </div><!-- end frm-row section -->
                    </div><!-- end .form-body section -->
                    <div class="form-footer">
                        <c:if test = "${mode == 'add'}">
                            <button type="submit" class="button btn-blue"><spring:message code="common.button.submit" text="Submit" /></button>
                        </c:if>
                        <c:if test = "${mode=='update'}">
                            <button type="submit" class="button btn-blue"><spring:message code="common.button.update" text="Update" /></button>
                        </c:if>
                        <button type="reset" class="button btn-blue"><spring:message code="common.button.cancel" text="Cancel" /></button>
                    </div><!-- end .form-footer section -->
                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>

    <script type="text/javascript">

        $(document).ready(function () {
            $("#sscid").change(function () {

                var sscid = $(this).val();
                if (sscid) {

                    $.ajax({
                        url: "getQP.io",
                        data: {sscid: sscid},
                        type: 'GET',
                        success: function (data) {

                            var str = "<option value=''>------- Select --------</option>";
                            if (data.length === 0) {
                                str = "<option value=''>------- Select --------</option>";
                            }
                            $.each(data, function (index, jsonObject) {
                                str = str + "<option value=" + jsonObject.ID + ">" + jsonObject.NAME + "</option>";

                            });
                            $("#qpackid").html(str);
                        }
                    });
                } else {
                    $("#qpackid").html("<option value=''>------- Select --------</option>");
                }
            });

        });

        function writeExcelSheet() {
            var qpackid = $("#qpackid").val();
            var srcdate = $("#month").val();
            alert(srcdate + " Test data " + qpackid);
            window.location.href = "<%=request.getContextPath()%>/admin/qbankanalysis/writeExcel.io?qpackid=" + qpackid + "&srcdate=" + srcdate;

        }
    </script>

</html>
