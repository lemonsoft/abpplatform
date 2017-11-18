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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="qualificationpack.title" text="Qualification Packs" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="qualificationpack" id="submitForm">
                    <form:hidden path="qpid" id="qpid"/>
                    <form:hidden path="ssc_id" id="ssc_id"/>
                    <div class="form-body theme-blue">

                        <div id="msg"></div>
                        <div class="frm-row">

                            <div class="section colm colm4">
                                <label for="names" class="field-label"><spring:message code="qualificationpack.packid" text="Qualification Pack ID" /></label>
                                <label class="field prepend-icon">
                                    <c:if test = "${mode == 'add'}">
                                        <form:input path="qpackid" id="qpackid" class="gui-input" placeholder="Pack ID" required="true"/>
                                    </c:if>
                                    <c:if test = "${mode=='update'}">
                                        <form:input path="qpackid" id="qpackid" class="gui-input" readonly="true" placeholder="Pack ID" required="true"/>
                                    </c:if>


                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label><div id="result"></div>
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <label for="names" class="field-label"><spring:message code="qualificationpack.pcackname" text="Qualification Pack Name" /></label>
                                <label class="field prepend-icon">
                                    <form:input path="qpackname" id="qpackname" class="gui-input" placeholder="Pack Name" required="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <label for="names" class="field-label"><spring:message code="qualificationpack.packlevel" text="Qualification Pack Level" /></label>
                                <label class="field prepend-icon">
                                    <form:input path="qpacklevel" id="qpacklevel" class="gui-input" placeholder="Pack Level" required="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->



                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm4">
                                <label for="names" class="field-label"><spring:message code="qualificationpack.theorymarks" text="Total Theory Marks" /></label>
                                <label class="field prepend-icon">
                                    <form:input type="number" path="totaltheorymarks" id="totaltheorymarks" class="gui-input" placeholder="Total Theory Marks" required="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <label for="names" class="field-label"><spring:message code="qualificationpack.practicalmarks" text="Total Practical Marks" /></label>
                                <label class="field prepend-icon">
                                    <form:input type="number" path="totalpracticalmarks" id="totalpracticalmarks" class="gui-input" placeholder="Total Practical Marks" required="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <label for="names" class="field-label"><spring:message code="qualificationpack.totalmarks" text="Total Marks" /></label>
                                <label class="field prepend-icon">
                                    <form:input type="number" path="totalmarks" id="totalmarks" class="gui-input" placeholder="Total Marks " required="true" readonly="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->



                        </div><!-- end frm-row section id="theorycutoffmarks"-->
                        <div class="frm-row">

                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><input type="checkbox" id="theorycutoffmarksopen" value="on"/><spring:message code="qualificationpack.theorycutoff" text="Is theory cutoff available" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="theorycutoffmarks" type="number" id="theorycutoffmarks" class="gui-input"  placeholder=""/>


                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><input type="checkbox" id="practicalcutoffmarksopen" value="on"/><spring:message code="qualificationpack.practicalcutoff" text="Is practical cutoff available" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="practicalcutoffmarks" type="number" id="practicalcutoffmarks" class="gui-input"  placeholder=""/>


                                </label>
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><input type="checkbox" id="overallcutoffmarksopen" value="on"/><spring:message code="qualificationpack.overallcutoff" text="Is overall cutoff available" /></label>
                                <label class="field prepend-icon" >
                                    <form:input path="overallcutoffmarks" type="number" id="overallcutoffmarks" class="gui-input"  placeholder=""/>


                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6" >
                                <label for="names" class="field-label"><input type="checkbox" id="weightedavgmarksopen" value="on"/><spring:message code="qualificationpack.weightedavg" text="Is Weighted available" /></label>
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
                        <button type="button" class="button btn-blue" onclick="window.close();window.opener.refreshqp();" ><spring:message code="common.button.cancel" text="Cancel" /></button>
                    </div><!-- end .form-footer section -->
                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->


        <script type="text/javascript"
        src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

        <script>
                            $(document).ready(function () {

            <c:if test = "${mode=='update'}">
                                $('#theorycutoffmarks').show();
                                $('#practicalcutoffmarks').show();
                                $('#overallcutoffmarks').show();
                                $('#weightedavgmarks').show();
            </c:if>
            <c:if test = "${mode == 'add'}">
                                $('#theorycutoffmarks').hide();
                                $('#practicalcutoffmarks').hide();
                                $('#overallcutoffmarks').hide();
                                $('#weightedavgmarks').hide();
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
                $('#qpackid').on('change', function () {

                    var current = $(this).val();
                    if (current.trim()) {
                        $.ajax({
                            url: 'checkQPID.io',
                            data: {name: current},
                            success: function (data) {
                                if (data === 'avail') {
                                    $('#result').html("<font color=\"green\">Available</font>");
                                }
                                if (data === 'notavail') {

                                    $('#result').html("<font color=\"red\">Qualification Pack ID Already Exist</font>");
                                    $('#qpackid').val('');
                                }


                            }
                        });
                    } else {
                        $('#result').html("Invalid ID");
                    }

                });
                $('#totalpracticalmarks').on('change', function () {
                    var totaltheorymarks = $('#totaltheorymarks').val();
                    var practiclemarks = $('#totalpracticalmarks').val();
                    var totalmarks = parseInt(totaltheorymarks) + parseInt(practiclemarks);
                    $('#totalmarks').val(totalmarks);
                    //alert(practiclemarks);
                    //alert(a);

                });
                $('#totaltheorymarks').on('change', function () {
                    var totaltheorymarks = $('#totaltheorymarks').val();
                    var practiclemarks = $('#totalpracticalmarks').val();
                    var totalmarks = parseInt(totaltheorymarks) + parseInt(practiclemarks);
                    $('#totalmarks').val(totalmarks);
                    //alert(practiclemarks);
                    //alert(a);

                });
                $('#submitForm').submit(function (e) {
                    e.preventDefault();


                    var ssc_id = $('#ssc_id').val();
                    var qpackid = $('#qpackid').val();
                    var qpackname = $('#qpackname').val();
                    var qpacklevel = $('#qpacklevel').val();
                    var totaltheorymarks = $('#totaltheorymarks').val();
                    var totalpracticalmarks = $('#totalpracticalmarks').val();
                    var totalmarks = $('#totalmarks').val();
                    var theorycutoffmarks = $('#theorycutoffmarks').val();
                    var practicalcutoffmarks = $('#practicalcutoffmarks').val();
                    var overallcutoffmarks = $('#overallcutoffmarks').val();
                    var weightedavgmarks = $('#weightedavgmarks').val();

                    //alert($('#theorycutoffmarks').val());
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
                    if (qpackid.trim()) {
                        $.ajax({
                            url: 'saveqp.io',
                            data: {ssc_id: ssc_id, qpackid: qpackid, qpackname: qpackname, qpacklevel: qpacklevel, totaltheorymarks: totaltheorymarks, totalpracticalmarks: totalpracticalmarks, totalmarks: totalmarks, theorycutoffmarks: theorycutoffmarks, practicalcutoffmarks: practicalcutoffmarks, overallcutoffmarks: overallcutoffmarks, weightedavgmarks: weightedavgmarks},
                            success: function (data) {

                                $('#msg').html("<font color=\"green\">" + data + "</font>");
                                $('#qpackid').val('');
                                $('#qpackname').val('');
                                $('#qpackname').val('');
                                $('#qpacklevel').val('');
                                $('#totaltheorymarks').val('');
                                $('#totalpracticalmarks').val('');
                                $('#totalmarks').val('');
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
                var qpid = $('#qpid').val();
                var ssc_id = $('#ssc_id').val();
                var qpackid = $('#qpackid').val();
                var qpackname = $('#qpackname').val();
                var qpacklevel = $('#qpacklevel').val();
                var totaltheorymarks = $('#totaltheorymarks').val();
                var totalpracticalmarks = $('#totalpracticalmarks').val();
                var totalmarks = $('#totalmarks').val();
                var theorycutoffmarks = $('#theorycutoffmarks').val();
                var practicalcutoffmarks = $('#practicalcutoffmarks').val();
                var overallcutoffmarks = $('#overallcutoffmarks').val();
                var weightedavgmarks = $('#weightedavgmarks').val();

                //alert($('#theorycutoffmarks').val());
                if (qpackid.trim()) {
                    $.ajax({
                        url: 'updateqp.io',
                        data: {qpid: qpid, ssc_id: ssc_id, qpackid: qpackid, qpackname: qpackname, qpacklevel: qpacklevel, totaltheorymarks: totaltheorymarks, totalpracticalmarks: totalpracticalmarks, totalmarks: totalmarks, theorycutoffmarks: theorycutoffmarks, practicalcutoffmarks: practicalcutoffmarks, overallcutoffmarks: overallcutoffmarks, weightedavgmarks: weightedavgmarks},
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
