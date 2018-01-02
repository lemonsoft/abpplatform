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
            <div class="smart-forms smart-container wrap-full">

                <div class="form-header header-blue">
                    <h4><i class="fa fa-pencil-square"></i>Job Role Master</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="jobroledao">

                    <div class="form-body theme-blue">
                        <div class="frm-row">

                            <div class="section colm colm6">

                                <label for="names" class="field-label"><spring:message code="ssc.title" text="Sector Skill Council" /></label>
                                <label class="field prepend-icon">
                                    <form:select path="sectorskill" name="sectorskill" id="sectorskill" class="gui-input" >
                                        <form:option value="">--Select--</form:option>
                                        <form:options items="${records}"/>
                                    </form:select>

                                    <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                </label>

                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm12">
                                <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">



                                </table>

                            </div>
                        </div><!-- end frm-row section -->
                    </div><!-- end .form-body section -->
                    
                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>
    <script>
        $("#sectorskill").change(function () {

            var sscid = $(this).val();

            // alert(sscid);
            if (sscid) {

                $.ajax({
                    url: "jobRoleMaster.io",
                    data: {sscid: sscid},
                    type: 'GET',
                    success: function (data) {
                        //alert(data);
                         var str ="<tr><td colspan=5 align=right><a href=\"<%=request.getContextPath()%>/admin/jobrole/writeExcel.io?sscid="+sscid+"\"><img src=\"<%=request.getContextPath()%>/assets/images/excel.ico\" width=30px height=30px/></a></td></tr>";
                         str = str + "<tr><td>Sr.#</td><td>Sector</td><td>Job Role</td><td>Job Role Number</td><td>Job Role Level</td></tr>";

                        $.each(data, function (index, jsonObject) {

                            str = str + "<tr><td>" + index + "</td><td>" + jsonObject.sscname + "</td><td>" + jsonObject.jobrole + "</td><td>" + jsonObject.jobroleno + "</td><td>" + jsonObject.jobrolelevel + "</td></tr>";

                        });
                        $("#example-basic4").html(str);
                    }
                });
            } else {

                $("#example-basic4").html("");
            }
        });
    </script>

</html>
