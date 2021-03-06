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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="nos.title" text="ADD NOS" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="nosdao" id="submitForm">
                    <form:hidden path="nosID" id="nosID"/>
                    <form:hidden path="qpackid" id="qpackid"/>
                    <div class="form-body theme-blue">

                        <div id="msg"></div>
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label"><spring:message code="nos.packid" text="NOS ID" /></label>
                                <label class="field prepend-icon">
                                    <c:if test = "${mode == 'add'}">
                                        <form:input path="nosid" id="nosid" class="gui-input" placeholder="NOS ID" required="true"/>
                                    </c:if>
                                    <c:if test = "${mode=='update'}">
                                        <form:input path="nosid" id="nosid" class="gui-input" readonly="true" placeholder="NOS ID" required="true"/>
                                    </c:if>


                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label><div id="result"></div>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label"><spring:message code="nos.pcackname" text="NOS Name" /></label>
                                <label class="field prepend-icon">
                                    <form:input path="nosname" id="nosname" class="gui-input" placeholder="NOS Name" required="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->




                        </div><!-- end frm-row section -->

                        <div class="frm-row">

                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><input type="checkbox" id="theorycutoffmarksopen" value="on"/><spring:message code="nos.theorycutoff" text="Is theory cutoff available" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="theorycutoffmarks" type="number" id="theorycutoffmarks" class="gui-input"  placeholder=""/>


                                </label>  <span id="errortheory"></span>  
                            </div><!-- end section -->
                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><input type="checkbox" id="practicalcutoffmarksopen" value="on"/><spring:message code="nos.practicalcutoff" text="Is practical cutoff available" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="practicalcutoffmarks" type="number" id="practicalcutoffmarks" class="gui-input"  placeholder=""/>


                                </label><span id="errorpractical"></span> 
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><input type="checkbox" id="overallcutoffmarksopen" value="on"/><spring:message code="nos.overallcutoff" text="Is overall cutoff available" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="overallcutoffmarks" type="number" id="overallcutoffmarks" class="gui-input"  placeholder=""/>


                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><input type="checkbox" id="weightedavgmarksopen" value="on"/><spring:message code="nos.weightedavg" text="Is Weighted available" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="weightedavgmarks" type="number" id="weightedavgmarks" class="gui-input"  placeholder=""/>


                                </label>
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                    </div><!-- end .form-body section -->
                    <div class="form-footer">
                        <c:if test = "${mode == 'add'}">
                            <button type="submit" class="button btn-blue"><spring:message code="common.button.submit" text="Submit" /></button>
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
            <c:if test = "${mode == 'add'}">
                                $('#theorycutoffmarks').hide();
                                $('#practicalcutoffmarks').hide();
                                $('#overallcutoffmarks').hide();
                                $('#weightedavgmarks').hide();
            </c:if>
            <c:if test = "${mode=='update'}">
                                $('#theorycutoffmarks').show();
                                $('#practicalcutoffmarks').show();
                                $('#overallcutoffmarks').show();
                                $('#weightedavgmarks').show();
            </c:if>

                                $('#theorycutoffmarksopen').change(function () {
                                    //alert("hi");
                                    if (this.checked) {
                                        $('#theorycutoffmarks').show();
                                    } else {
                                        $('#theorycutoffmarks').hide();
                                    }
                                    // $('#textbox1').val(this.checked);
                                });
                                $('#practicalcutoffmarksopen').change(function () {
                                    //alert("hi");
                                    if (this.checked) {
                                        $('#practicalcutoffmarks').show();
                                    } else {
                                        $('#practicalcutoffmarks').hide();
                                    }
                                    //$('#textbox1').val(this.checked);
                                });
                                $('#overallcutoffmarksopen').change(function () {
                                    //alert("hi");
                                    if (this.checked) {
                                        $('#overallcutoffmarks').show();
                                    } else {
                                        $('#overallcutoffmarks').hide();
                                    }
                                    //$('#textbox1').val(this.checked);
                                });
                                $('#weightedavgmarksopen').change(function () {
                                    //alert("hi");
                                    if (this.checked) {
                                        $('#weightedavgmarks').show();
                                    } else {
                                        $('#weightedavgmarks').hide();
                                    }
                                    //$('#textbox1').val(this.checked);
                                });
                                $('#closewindow').click(function () {
                                    $("#dialog").dialog("close");
                                });
                            });
        </script>
        <script type="text/javascript">

            $(document).ready(function () {
                $('#nosid').on('change', function () {

                    var current = $(this).val();
                    if (current.trim()) {
                        $.ajax({
                            url: 'checkNOSID.io',
                            data: {name: current},
                            success: function (data) {
                                if (data === 'avail') {
                                    $('#result').html("<font color=\"green\">Available</font>");
                                }
                                if (data === 'notavail') {

                                    $('#result').html("<font color=\"red\">NOS ID Already Exist</font>");
                                    $('#nosid').val('');
                                }


                            }
                        });
                    } else {
                        $('#result').html("Invalid ID");
                    }

                });

                $("#theorycutoffmarks").on("change", function () {
                    var val = $(this).val();
                    var qpackid = $('#qpackid').val();
                    var nosID = $('#nosID').val();

                    $.ajax({
                        url: 'checkTheoryMarks.io',
                        data: {qpackid: qpackid, nosID: nosID, theorymarks: val},
                        success: function (data) {

                            if (data.status == 'greator') {
                               
                                $("#theorycutoffmarks").val(0);
                                $('#errortheory').html("<font color=red>Theory Marks Should Not Be Greator Than Qualification Pack</font>");
                            } else {
                                $('#errortheory').html("");
                            }
                        }
                    });

                });
                $("#practicalcutoffmarks").on("change", function () {
                    var val = $(this).val();
                    var qpackid = $('#qpackid').val();
                    var nosID = $('#nosID').val();
                    $.ajax({
                        url: 'checkPracticalMarks.io',
                        data: {qpackid: qpackid, nosID: nosID, practicalmarks: val},
                        success: function (data) {

                            if (data.status == 'greator') {
                                
                                $("#practicalcutoffmarks").val(0);
                                $('#errorpractical').html("<font color=red>Practical Marks Should Not Be Greator Than Qualification Pack</font>");
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
                    var nosname = $('#nosname').val();

                    var theorycutoffmarks = $('#theorycutoffmarks').val();
                    var practicalcutoffmarks = $('#practicalcutoffmarks').val();
                    var overallcutoffmarks = $('#overallcutoffmarks').val();
                    var weightedavgmarks = $('#weightedavgmarks').val();

                    if (!theorycutoffmarks) {
                        theorycutoffmarks = 0;
                    }
                    if (!practicalcutoffmarks) {
                        practicalcutoffmarks = 0;
                    }
                    if (!overallcutoffmarks) {
                        overallcutoffmarks = 0;
                    }
                    if (!weightedavgmarks) {
                        weightedavgmarks = 0;
                    }
                    //alert($('#theorycutoffmarks').val());
                    if (qpackid.trim()) {
                        $.ajax({
                            url: 'savenos.io',
                            data: {qpackid: qpackid, nosid: nosid, nosname: nosname, theorycutoffmarks: theorycutoffmarks, practicalcutoffmarks: practicalcutoffmarks, overallcutoffmarks: overallcutoffmarks, weightedavgmarks: weightedavgmarks},
                            success: function (data) {

                                $('#msg').html("<font color=\"green\">" + data + "</font>");
                                $('#nosid').val('');
                                $('#nosname').val('');
                                $('#theorycutoffmarks').val('');
                                $('#practicalcutoffmarks').val('');
                                $('#overallcutoffmarks').val('');
                                $('#weightedavgmarks').val('');
                            }
                        });
                    } else {
                        $('#msg').html("<font color=\"red\">Error submitting form ...</font>");
                    }

                });
            });
            function update() {
                //alert("going to update..");
                var nosID = $('#nosID').val();
                var qpackid = $('#qpackid').val();
                var nosid = $('#nosid').val();
                var nosname = $('#nosname').val();
                var theorycutoffmarks = $('#theorycutoffmarks').val();
                var practicalcutoffmarks = $('#practicalcutoffmarks').val();
                var overallcutoffmarks = $('#overallcutoffmarks').val();
                var weightedavgmarks = $('#weightedavgmarks').val();

                //alert($('#theorycutoffmarks').val());
                if (qpackid.trim()) {
                    $.ajax({
                        url: 'updatenos.io',
                        data: {nosID: nosID, qpackid: qpackid, nosid: nosid, nosname: nosname, theorycutoffmarks: theorycutoffmarks, practicalcutoffmarks: practicalcutoffmarks, overallcutoffmarks: overallcutoffmarks, weightedavgmarks: weightedavgmarks},
                        success: function (data) {

                            $('#msg').html("<font color=\"green\">" + data + "</font>");

                            window.close();
                            window.opener.refresh();



                        }
                    });
                } else {
                    $('#msg').html("<font color=\"red\">Error submitting form ...</font>");
                }
            }
        </script>
    </body>

</html>
