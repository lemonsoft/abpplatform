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
                    <h4><i class="fa fa-pencil-square"></i>Create Question Paper</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="qpaper">
                    <form:hidden path="questionpaperid" />
                    <form:hidden path="qpackid" />
                    <div class="form-body theme-blue">


                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Question Paper Name</label>
                                <label class="field prepend-icon">
                                    <form:input path="questionpapername" id="questionpapername" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Total Time</label>
                                <label class="field prepend-icon">
                                    <form:input type="text" path="totaltime"  id="totaltime" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->

                        <div class="frm-row">
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Total Marks</label>
                                <label class="field prepend-icon">
                                    <c:if test = "${mode=='update'}">
                                    <form:input type="number" path="totalmarks" id="totalmarks" class="gui-input"/>
                                    </c:if>
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Is Question Random</label>
                                <label class="field prepend-icon">
                                    <c:choose>
                                        <c:when test = "${israndom == 'on'}">
                                            <input type="checkbox" name="israndom" id="israndom" checked/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="israndom" id="israndom" />
                                        </c:otherwise>
                                    </c:choose>

                                </label>
                            </div><!-- end section -->
                            
                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Is Option Random</label>
                                <label class="field prepend-icon">
                                    <c:choose>
                                        <c:when test = "${isoptionrandom == 'on'}">
                                            <input type="checkbox" name="isoptionrandom" id="isoptionrandom" checked/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="isoptionrandom" id="isoptionrandom" />
                                        </c:otherwise>
                                    </c:choose>



                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Is Active</label>
                                <label class="field prepend-icon">
                                    <c:choose>
                                        <c:when test = "${isactive == 'on'}">
                                            <input type="checkbox" name="isactive" id="isactive" checked/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" name="isactive" id="isactive" />
                                        </c:otherwise>
                                    </c:choose>


                                </label>
                            </div><!-- end section -->
                            <span id="error"></span>
                            <span id="status"></span>

                        </div><!-- end frm-row section -->




                    </div><!-- end .form-body section -->
                    <div class="form-footer">
                        <c:if test = "${mode == 'add'}">
                            <button type="button" class="button btn-blue" onclick="generateQuestion();"><spring:message code="common.button.submit" text="Submit" /></button>
                        </c:if>
                        <c:if test = "${mode=='update'}">
                            <button type="button" class="button btn-blue" onclick="updateQuestionPaper();"><spring:message code="common.button.update" text="Update" /></button>
                        </c:if>
                        <button type="reset" class="button btn-blue" onclick="window.close();window.opener.refresh();"><spring:message code="common.button.cancel" text="Cancel" /></button>
                    </div><!-- end .form-footer section -->
                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
        <script type="text/javascript"        src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

        <script>

                                function generateQuestion() {
                                    var qpackid = $('#qpackid').val();

                                    if (qpackid) {
                                        //alert("Equal ");
                                        var formData = {
                                            'qpackid': $('#qpackid').val(),
                                            'questionpapername': $('#questionpapername').val(),
                                            'totaltime': $('#totaltime').val(),
                                            'israndom': $('#israndom').val(),
                                            'isoptionrandom': $('#isoptionrandom').val(),
                                            'isactive': $('#isactive').val()

                                        };
                                        $.ajax({
                                            url: "generateQuestionPaper.io",
                                            data: formData,
                                            type: 'GET',
                                            success: function (data) {
                                                //alert(data.error);
                                                if (data.error !== undefined) {
                                                    $('#error').html('<font color=red>' + data.error + '</font>');
                                                }
                                                if (data.status !== undefined) {
                                                    $('#status').html('<font color=green>' + data.status + '</font>');
                                                }

                                            }
                                        });

                                    } else {

                                        $('#error').html("<font color=red>Invalid Qualification Pack</font>");
                                    }
                                }
                                
                                function updateQuestionPaper() {
                                    var questionpaperid = $('#questionpaperid').val();

                                    if (questionpaperid) {
                                        //alert("Equal ");
                                        var formData = {
                                            'questionpaperid': $('#questionpaperid').val(),
                                            'qpackid': $('#qpackid').val(),
                                            'questionpapername': $('#questionpapername').val(),
                                            'totaltime': $('#totaltime').val(),
                                            'totalmarks': $('#totalmarks').val(),
                                            'israndom': $('#israndom').val(),
                                            'isoptionrandom': $('#isoptionrandom').val(),
                                            'isactive': $('#isactive').val()

                                        };
                                        $.ajax({
                                            url: "updateQuestionPaper.io",
                                            data: formData,
                                            type: 'GET',
                                            success: function (data) {
                                                //alert(data.error);
                                                
                                                if (data.status == "ok") {
                                                    $('#status').html('<font color=green>Question Paper Updated</font>');
                                                }
                                                if (data.status == "fail") {
                                                    $('#status').html('<font color=red>Question Paper Update Failed</font>');
                                                }

                                            }
                                        });

                                    } else {

                                        $('#error').html("<font color=red>Invalid Qualification Pack</font>");
                                    }
                                }

        </script>
    </body>

</html>
