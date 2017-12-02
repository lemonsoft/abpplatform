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
                    <form:hidden path="pcID" id="pcID"/>
                    <form:hidden path="nosid" id="nosid"/>
                    <form:hidden path="qpackid" id="qpackid"/>
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
                                    <form:input path="theorycutoffmarks" id="theorymarks" type="number"  class="gui-input"  placeholder=""/>


                                </label><span id="errortheory"></span>
                            </div><!-- end section -->
                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><spring:message code="pc.practicalcutoff" text="Practical Marks" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="practicalcutoffmarks" id="practicalmarks" type="number"  class="gui-input"  placeholder=""/>


                                </label><span id="errorpractical"></span>
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><spring:message code="pc.overallcutoff" text="Maximum Marks" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="overallcutoffmarks" readonly="true" id="maximummarks" type="number"  class="gui-input"  placeholder=""/>


                                </label>
                            </div><!-- end section -->


                        </div><!-- end frm-row section -->
                    </div><!-- end .form-body section -->
                    <div class="form-footer">
                        <c:if test = "${mode == 'add'}">
                            <button type="submit" class="button btn-blue" id="submitbtn"><spring:message code="common.button.submit" text="Submit" /></button>
                        </c:if>
                        <c:if test = "${mode=='update'}">
                            <button type="button" class="button btn-blue" onclick="update();"><spring:message code="common.button.update" text="Update" /></button>
                        </c:if>
                        <button type="button" class="button btn-blue" onclick="window.close();window.opener.refresh();" ><spring:message code="common.button.cancel" text="Cancel" /></button>
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

                $('input[name="theorycutoffmarks"]').change(function () {
                    var theorymarks = $(this).val();
                    var practicalmarks = $('#practicalmarks').val();
                    var maxmarks = parseInt(theorymarks) + parseInt(practicalmarks);
                    $('#maximummarks').val(maxmarks);
                });
                $('input[name="practicalcutoffmarks"]').change(function () {
                    var practicalmarks = $(this).val();
                    var theorymarks = $('#theorymarks').val();
                    var maxmarks = parseInt(theorymarks) + parseInt(practicalmarks);
                    $('#maximummarks').val(maxmarks);
                });

                $("#theorymarks").on("change", function () {
                    var val = $(this).val();
                    var nosid = $('#nosid').val();
                    var pcID = $('#pcID').val();

                    $.ajax({
                        url: 'checkPCTheoryMarks.io',
                        data: {nosid: nosid, pcID: pcID, theorymarks: val},
                        success: function (data) {

                            if (data.status == 'greator') {

                                $("#theorymarks").val(0);
                                $('#errortheory').html("<font color=red>Theory Marks Should Not Be Greator Than NOS Marks</font>");
                            } else {
                                $('#errortheory').html("");
                            }
                        }
                    });

                });
                $("#practicalmarks").on("change", function () {
                    var val = $(this).val();
                    var nosid = $('#nosid').val();
                    var pcID = $('#pcID').val();
                    $.ajax({
                        url: 'checkPCPracticalMarks.io',
                        data: {nosid: nosid, pcID: pcID, practicalmarks: val},
                        success: function (data) {

                            if (data.status == 'greator') {

                                $("#practicalmarks").val(0);
                                $('#errorpractical').html("<font color=red>Practical Marks Should Not Be Greator Than NOS Marks</font>");
                            } else {
                                $('#errorpractical').html("");
                            }
                        }
                    });

                });

                $('#submitForm').submit(function (e) {
                    e.preventDefault();

                    var qpackid = $('#qpackid').val();
                    var nosid = $('#nosid').val();
                    var pcid = $('#pcid').val();
                    var pcname = $('#pcname').val();

                    var theorycutoffmarks = $('#theorymarks').val();
                    var practicalcutoffmarks = $('#practicalmarks').val();
                    var overallcutoffmarks = $('#maximummarks').val();

                    if (!theorycutoffmarks) {
                        theorycutoffmarks = 0;
                    }
                    if (!practicalcutoffmarks) {
                        practicalcutoffmarks = 0;
                    }
                    if (!overallcutoffmarks) {
                        overallcutoffmarks = 0;
                    }
                    var flag = false;
                    //alert($('#maximummarks').val());
                    if (qpackid.trim()) {

                        $.ajax({
                            url: 'checkMaximumMarks.io',
                            data: {qpackid: qpackid, maximummarks: overallcutoffmarks},
                            success: function (data) {
                                if (data.status == "yes") {
                                    //alert(data.status);
                                    $('#msg').html("<font color=\"red\">Maximum marks should not exceed the total marks of Quality Pack.</font>");
                                    // $('#submitbtn').prop('disabled', true);
                                } else {
                                    alert(data.status);
                                    $('#msg').html("");
                                    //$('#submitbtn').prop('disabled', false);
                                    flag = true;
                                    alert(flag);
                                    if (flag) {

                                        $.ajax({
                                            url: 'savepc.io',
                                            data: {qpackid: qpackid, nosid: nosid, pcid: pcid, pcname: pcname, theorycutoffmarks: theorycutoffmarks, practicalcutoffmarks: practicalcutoffmarks, overallcutoffmarks: overallcutoffmarks},
                                            success: function (data) {

                                                $('#msg').html("<font color=\"green\">" + data + "</font>");
                                                //$('#nosid').val('');
                                                $('#pcname').val('');
                                                $('#theorymarks').val('');
                                                $('#practicalmarks').val('');
                                                $('#maximummarks').val('');

                                            }
                                        });
                                    } else {
                                        //$('#msg').html("<font color=\"red\">Error submitting form ...</font>");
                                    }

                                }


                            }
                        });
                    } else {
                        $('#msg').html("<font color=\"red\">Error submitting form ...</font>");
                    }



                });
            });
            function update() {
                //alert("going to update..");
                var pcID = $('#pcID').val();
                var nosid = $('#nosid').val();
                var pcid = $('#pcid').val();
                var pcname = $('#pcname').val();
                var theorycutoffmarks = $('#theorymarks').val();
                var practicalcutoffmarks = $('#practicalmarks').val();
                var overallcutoffmarks = $('#maximummarks').val();
                //alert($('#theorycutoffmarks').val());
                if (pcID) {
                    $.ajax({
                        url: 'updatepc.io',
                        data: {pcID: pcID, nosid: nosid, pcid: pcid, pcname: pcname, theorycutoffmarks: theorycutoffmarks, practicalcutoffmarks: practicalcutoffmarks, overallcutoffmarks: overallcutoffmarks},
                        success: function (data) {

                            $('#msg').html("<font color=\"green\">" + data + "</font>");
                            window.close();
                            window.opener.refresh();
//                            window.onunload = refreshParent;
//                            function refreshParent() {
//                                window.opener.location.reload();
//                            }

                        }
                    });
                } else {
                    $('#msg').html("<font color=\"red\">Error submitting form ...</font>");
                }
            }

        </script>
    </body>

</html>
