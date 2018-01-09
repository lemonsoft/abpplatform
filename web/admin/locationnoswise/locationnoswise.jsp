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
                    <h4><i class="fa fa-pencil-square"></i>Location Wise NOS Report</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="locationnoswisedao">

                    <div class="form-body theme-blue">


                        <div class="frm-row">
                            <div class="section colm colm4">
                                <label for="names" class="field-label">State</label>
                                <label class="field prepend-icon">
                                    <form:select path="state" name="state" id="state" class="gui-input" >
                                        <form:option value="">--Select--</form:option>
                                        <form:options items="${allstates}"/>
                                    </form:select>

                                    <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label">District</label>
                                    <label class="field prepend-icon">
                                        <form:select path="district" id="district" class="gui-input" >
                                            <form:option value="0">--Select--</form:option>
                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="locationwise.month" text="Select Month" /></label>
                                    <label class="field prepend-icon">

                                        <form:input path="month" type="month" id="month" class="gui-input"/>
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
                                    <display:column property="jobrole" title="Job Role" />
                                    <display:column property="theorypass" title="Theory Pass"/>
                                    <display:column property="theoryfail" title="Theory Fail"/>
                                    <display:column property="vivapass" title="Viva Pass"/>
                                    <display:column property="vivafail" title="Viva Fail"/>
                                    <display:column property="noswisefail" title="Noswise Fail"/>
                                    <display:column property="noswisepass" title="Noswise Pass"/>
                                </display:table>

                            </div>
                        </c:if>


                    </div><!-- end .form-body section -->



                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>
    <script type="text/javascript">

        $(document).ready(function () {
            $("#state").change(function () {

                var stateid = $(this).val();
                if (stateid) {

                    $.ajax({
                        url: "getDistricts.io",
                        data: {stateid: stateid},
                        type: 'GET',
                        success: function (data) {

                            var str = "<option value=''>------- Select --------</option>";
                            if (data.length === 0) {
                                str = "<option value=''>------- Select --------</option>";
                            }
                            $.each(data, function (index, jsonObject) {
                                str = str + "<option value=" + jsonObject.ID + ">" + jsonObject.NAME + "</option>";

                            });
                            $("#district").html(str);
                        }
                    });
                } else {
                    $("#district").html("<option value=''>------- Select --------</option>");
                }
            });



        });

        function writeExcelSheet() {
            var qpackid = $("#state").val();
            var srcdate = $("#month").val();
            alert(srcdate + " Test data " + qpackid);
            window.location.href = "<%=request.getContextPath()%>/admin/jobroleresult/writeExcel.io?qpackid=" + qpackid + "&srcdate=" + srcdate;

        }
    </script>

</html>
