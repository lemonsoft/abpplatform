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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="individualassesor.title" text="Individual Assessor Dashboard" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="individualdao">

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
                                    <display:column property="srno" titleKey="individualassesor.srno" />
                                    <display:column property="assesorname" titleKey="individualassesor.assesorname"/>
                                    <display:column property="jobroles" titleKey="individualassesor.jobroles"/>
                                    <display:column property="affiliationdate" titleKey="individualassesor.affiliationdate"/>
                                    <display:column property="state" titleKey="individualassesor.state"/>
                                    <display:column property="city" titleKey="individualassesor.city"/>
                                    <display:column property="aadharno" titleKey="individualassesor.aadharno"/>
                                    <display:column property="qualification" titleKey="individualassesor.qualification"/>
                                    <display:column property="totalexp" titleKey="individualassesor.totalexp"/>
                                    <display:column property="noofcandidateasses" titleKey="individualassesor.noofcandidateasses"/>
                                    <display:column property="pass" titleKey="individualassesor.pass"/>
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
                            $("#state").html(str);
                        }
                    });
                } else {
                    $("#state").html("<option value=''>------- Select --------</option>");
                }
            });



        });

        function writeExcelSheet() {
            
            var sscid = $("#sscid").val();
            alert(sscid + " Test data " );
            window.location.href = "<%=request.getContextPath()%>/admin/individualdashboard/writeExcel.io?sscid=" + sscid;

        }
    </script>

</html>
