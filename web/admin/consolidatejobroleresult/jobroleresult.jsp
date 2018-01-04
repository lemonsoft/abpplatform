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
                    <h4><i class="fa fa-pencil-square"></i>Consolidate Job Role Result</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="jobroledao">

                    <div class="form-body theme-blue">


                        <div class="frm-row">
                            <div class="section colm colm4">
                                <label for="names" class="field-label"><spring:message code="ssc.title" text="Sector Skill Council" /></label>
                                <label class="field prepend-icon">
                                    <form:select path="sscid" name="sscid" id="sscid" class="gui-input" >
                                        <form:option value="">--Select--</form:option>
                                        <form:options items="${ssc}"/>
                                    </form:select>

                                    <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label">Qualification Packs</label>
                                    <label class="field prepend-icon">
                                        <form:select path="qpackid" id="qpackid" class="gui-input" >
                                            <form:option value="0">--Select--</form:option>
                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label">Select Month</label>
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
                                                    <display:column property="srno" title="SrNo#" />
                                                    <display:column property="jobrole" title="Job Role"/>
                                                    <display:column property="tpname" title="TP Name"/>
                                                    <display:column property="batchid" title="Batch ID"/>
                                                    <display:column property="centerid" title="Centre ID"/>
                                                    <display:column property="state" title="State"/>
                                                    <display:column property="location" title="Location"/>
                                                    <display:column property="assesorname" title="Assessor Name"/>
                                                    <display:column property="pass" title="Pass"/>
                                                    <display:column property="fail" title="Fail"/>
                                                    <display:column property="notappeared" title="Not Appeared"/>
                                                    <display:column property="totalnostudent" title="Total No. of Students"/>
                                                    <display:column property="assesmentdate" title="Assessment Date"/>
                                                    <display:column property="resultsubmissonplanneddate" title="Result Submission - Planned Date"/>
                                                    <display:column property="resultsubmissonactualdate" title="Result Submission - Actual Date"/>
                                                    <display:column property="status" title="Status"/>
                                                    <display:column property="deviationnoofdays" title="Deviation in Number of Days"/>
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
            $("#sscid").change(function () {

                var sscid = $(this).val();
                if (sscid) {

                    $.ajax({
                        url: "getQP.io",
                        data: {ssc_id: sscid},
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
            window.location.href = "<%=request.getContextPath()%>/admin/jobroleresult/writeExcel.io?qpackid=" + qpackid + "&srcdate=" + srcdate;

        }
    </script>

</html>