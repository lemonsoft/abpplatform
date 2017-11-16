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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="pc.title" text="ADD PC" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="pcdao" id="submitForm">
                    <form:hidden path="nosid" id="nosid"/>
                    <div class="form-body theme-blue">

                        <div id="msg"></div>
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label"><spring:message code="pc.packid" text="PC ID" /></label>
                                <label class="field prepend-icon">
                                    <form:input path="pcid" id="pcid" readonly="true" class="gui-input" placeholder="PC ID" required="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label><div id="result"></div>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label"><spring:message code="pc.pcackname" text="PC Name" /></label>
                                <label class="field prepend-icon">
                                    <form:input path="pcname" id="pcname" class="gui-input" placeholder="PC Name" required="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->




                        </div><!-- end frm-row section -->

                        <div class="frm-row">

                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><spring:message code="pc.theorycutoff" text="Theory Marks" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="theorycutoffmarks" type="number"  class="gui-input" value="0" placeholder=""/>


                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><spring:message code="pc.practicalcutoff" text="Practical Marks" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="practicalcutoffmarks" type="number"  class="gui-input" value="0" placeholder=""/>


                                </label>
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><spring:message code="pc.overallcutoff" text="Maximum Marks" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="overallcutoffmarks" type="number"  class="gui-input" value="0" placeholder=""/>


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
                        <button type="button" class="button btn-blue" onclick="window.close();" ><spring:message code="common.button.cancel" text="Cancel" /></button>
                    </div><!-- end .form-footer section -->
                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->


        <script type="text/javascript"
        src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

        <script>
                            $(document).ready(function () {
                                

                                $('#closewindow').click(function () {
                                    $("#dialog").dialog("close");
                                });
                            });
        </script>
        <script type="text/javascript">

            $(document).ready(function () {

                $('#submitForm').submit(function (e) {
                    e.preventDefault();
                    var nosid = $('#nosid').val();
                   
                    var pcid = $('#pcid').val();
                    var pcname = $('#pcname').val();
                     alert(pcid+"::::::"+pcname);
                    var theorycutoffmarks = $('#theorycutoffmarks').val();
                    var practicalcutoffmarks = $('#practicalcutoffmarks').val();
                    var overallcutoffmarks = $('#overallcutoffmarks').val();
                    

                    //alert($('#theorycutoffmarks').val());
                    if (pcid.trim()) {
                        $.ajax({
                            url: 'savepc.io',
                            data: {nosid: nosid, pcid: pcid, pcname: pcname, theorycutoffmarks: theorycutoffmarks, practicalcutoffmarks: practicalcutoffmarks, overallcutoffmarks: overallcutoffmarks},
                            success: function (data) {

                                $('#msg').html("<font color=\"green\">" + data + "</font>");
                                //$('#nosid').val('');
                                $('#pcname').val('');
                                $('#theorycutoffmarks').val('');
                                $('#practicalcutoffmarks').val('');
                                $('#overallcutoffmarks').val('');
                                
                            }
                        });
                    } else {
                        $('#msg').html("<font color=\"red\">Error submitting form ...</font>");
                    }

                });
            });
        </script>
    </body>

</html>
