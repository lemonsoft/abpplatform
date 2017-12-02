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
                    <h4><i class="fa fa-pencil-square"></i>PC Wise Questions</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}" id="form1"  commandName="pcwisequestion">
                    <form:hidden path="id" id="id"/>
                    <form:hidden path="pcid" id="pcid"/>
                    <div class="form-body theme-blue">


                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">PCID</label>
                                <label class="field prepend-icon">
                                    <form:input path="pcids" id="pcids" class="gui-input" readonly="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">PC Name</label>
                                <label class="field prepend-icon">
                                    <form:input path="pcname" id="pcname" class="gui-input" readonly="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Theory Marks</label>
                                <label class="field prepend-icon">
                                    <form:input path="theorymarks" id="theorymarks" class="gui-input" readonly="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Total Added Marks</label>
                                <label class="field prepend-icon">
                                    <form:input path="totaladdedmarks" id="totaladdedmarks" class="gui-input" readonly="true"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->

                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 1 (${marks1})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks1" id="marks1" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 2 (${marks2})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks2" id="marks2" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 3 (${marks3})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks3" id="marks3" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 4 (${marks4})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks4" id="marks4" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 5 (${marks5})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks5" id="marks5" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 6 (${marks6})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks6" id="marks6" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 7 (${marks7})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks7" id="marks7" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 8 (${marks8})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks8" id="marks8" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 9 (${marks9})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks9" id="marks9" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 10 (${marks10})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks10" id="marks10" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 11 (${marks11})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks11" id="marks11" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 12 (${marks12})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks12" id="marks12" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 13 (${marks13})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks13" id="marks13" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 14 (${marks14})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks14" id="marks14" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 15 (${marks15})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks15" id="marks15" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 16 (${marks16})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks16" id="marks16" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 17 (${marks17})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks17" id="marks17" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 18 (${marks18})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks18" id="marks18" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 19 (${marks19})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks19" id="marks19" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 20 (${marks20})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks20" id="marks20" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 21 (${marks21})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks21" id="marks21" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 22 (${marks22})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks22" id="marks22" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 23 (${marks23})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks23" id="marks23" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 24 (${marks24})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks24" id="marks24" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label for="names" class="field-label">Marks 25 (${marks25})</label>
                                <label class="field prepend-icon">
                                    <form:input path="marks25" id="marks25" class="gui-input"/>

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->



                    </div><!-- end .form-body section -->
                    <div class="form-footer">
                        <c:if test = "${mode == 'add'}">
                            <button type="button" class="button btn-blue" onclick="addPcwiseQuestion();"><spring:message code="common.button.submit" text="Submit" /></button>
                        </c:if>
                        <c:if test = "${mode=='update'}">
                            <button type="button" class="button btn-blue" onclick="updatePcwiseQuestion();"><spring:message code="common.button.update" text="Update" /></button>
                        </c:if>
                        <button type="reset" class="button btn-blue"><spring:message code="common.button.cancel" text="Cancel" /></button>
                        <span id="msg"></span>
                    </div><!-- end .form-footer section -->
                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
        <script type="text/javascript"   src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script type="text/javascript">
                                $(document).ready(function () {

            <c:if test = "${marks1 ==0}">
                                    $('#marks1').prop('readonly', true);
            </c:if>
            <c:if test = "${marks2 ==0}">
                                    $('#marks2').prop('readonly', true);
            </c:if>
            <c:if test = "${marks3 ==0}">
                                    $('#marks3').prop('readonly', true);
            </c:if>
            <c:if test = "${marks4 ==0}">
                                    $('#marks4').prop('readonly', true);
            </c:if>
            <c:if test = "${marks5 ==0}">
                                    $('#marks5').prop('readonly', true);
            </c:if>
            <c:if test = "${marks6 ==0}">
                                    $('#marks6').prop('readonly', true);
            </c:if>
            <c:if test = "${marks7 ==0}">
                                    $('#marks7').prop('readonly', true);
            </c:if>
            <c:if test = "${marks8 ==0}">
                                    $('#marks8').prop('readonly', true);
            </c:if>
            <c:if test = "${marks9 ==0}">
                                    $('#marks9').prop('readonly', true);
            </c:if>
            <c:if test = "${marks10 ==0}">
                                    $('#marks10').prop('readonly', true);
            </c:if>
            <c:if test = "${marks11 ==0}">
                                    $('#marks11').prop('readonly', true);
            </c:if>
            <c:if test = "${marks12 ==0}">
                                    $('#marks12').prop('readonly', true);
            </c:if>
            <c:if test = "${marks13 ==0}">
                                    $('#marks13').prop('readonly', true);
            </c:if>
            <c:if test = "${marks14 ==0}">
                                    $('#marks14').prop('readonly', true);
            </c:if>
            <c:if test = "${marks15==0}">
                                    $('#marks15').prop('readonly', true);
            </c:if>
            <c:if test = "${marks16==0}">
                                    $('#marks16').prop('readonly', true);
            </c:if>
            <c:if test = "${marks17==0}">
                                    $('#marks17').prop('readonly', true);
            </c:if>
            <c:if test = "${marks18==0}">
                                    $('#marks18').prop('readonly', true);
            </c:if>
            <c:if test = "${marks19==0}">
                                    $('#marks19').prop('readonly', true);
            </c:if>
            <c:if test = "${marks20 ==0}">
                                    $('#marks20').prop('readonly', true);
            </c:if>
            <c:if test = "${marks21 ==0}">
                                    $('#marks21').prop('readonly', true);
            </c:if>
            <c:if test = "${marks22 ==0}">
                                    $('#marks22').prop('readonly', true);
            </c:if>
            <c:if test = "${marks23 ==0}">
                                    $('#marks23').prop('readonly', true);
            </c:if>
            <c:if test = "${marks24 ==0}">
                                    $('#marks24').prop('readonly', true);
            </c:if>
            <c:if test = "${marks25 ==0}">
                                    $('#marks25').prop('readonly', true);
            </c:if>

                                    $("#marks1").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks1} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks2").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks2} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks3").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks3} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks4").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks4} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks5").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks5} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks6").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks6} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks7").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks7} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks8").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks8} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks9").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks9} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks10").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks10} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks11").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks11} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks12").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks12} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks13").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks13} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks14").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks14} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks15").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks15} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks16").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks16} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks17").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks17} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks18").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks18} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks19").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks19} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks20").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks20} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks21").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks21} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks22").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks22} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks23").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks23} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks24").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks24} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });
                                    $("#marks25").on("change", function () {
                                        var val = $(this).val();
                                        if (${marks25} < val) {
                                            alert("Input Value Must be Smaller Than Available");
                                            $(this).val(0);
                                            calculateaddedMarks();
                                        } else {
                                            calculateaddedMarks();
                                        }

                                    });

                                    function calculateaddedMarks() {

                                        var marks1 = parseInt($('#marks1').val()) * 1;
                                        var marks2 = parseInt($('#marks2').val()) * 2;
                                        var marks3 = parseInt($('#marks3').val()) * 3;
                                        var marks4 = parseInt($('#marks4').val()) * 4;
                                        var marks5 = parseInt($('#marks5').val()) * 5;
                                        var marks6 = parseInt($('#marks6').val()) * 6;
                                        var marks7 = parseInt($('#marks7').val()) * 7;
                                        var marks8 = parseInt($('#marks8').val()) * 8;
                                        var marks9 = parseInt($('#marks9').val()) * 9;
                                        var marks10 = parseInt($('#marks10').val()) * 10;
                                        var marks11 = parseInt($('#marks11').val()) * 11;
                                        var marks12 = parseInt($('#marks12').val()) * 12;
                                        var marks13 = parseInt($('#marks13').val()) * 13;
                                        var marks14 = parseInt($('#marks14').val()) * 14;
                                        var marks15 = parseInt($('#marks15').val()) * 15;
                                        var marks16 = parseInt($('#marks16').val()) * 16;
                                        var marks17 = parseInt($('#marks17').val()) * 17;
                                        var marks18 = parseInt($('#marks18').val()) * 18;
                                        var marks19 = parseInt($('#marks19').val()) * 19;
                                        var marks20 = parseInt($('#marks20').val()) * 20;
                                        var marks21 = parseInt($('#marks21').val()) * 21;
                                        var marks22 = parseInt($('#marks22').val()) * 22;
                                        var marks23 = parseInt($('#marks23').val()) * 23;
                                        var marks24 = parseInt($('#marks24').val()) * 24;
                                        var marks25 = parseInt($('#marks25').val()) * 25;
                                        var sum = marks1 + marks2 + marks3 + marks4 + marks5 + marks6 + marks7 + marks8 + marks9 + marks10 + marks11 + marks12 + marks13 + marks14 + marks15 + marks16 + marks17 + marks18 + marks19 + marks20 + marks21 + marks22 + marks23 + marks24 + marks25;
                                        $('#totaladdedmarks').val(sum);

                                    }


                                });
                                function addPcwiseQuestion() {
                                    var flag = false;
                                    var totaltheory = $('#theorymarks').val();
                                    var totaladdedmarks = $('#totaladdedmarks').val();

                                    if (parseInt(totaltheory) === parseInt(totaladdedmarks)) {
                                        flag = true;
                                    }
                                    if (flag) {
                                        //alert("Equal ");
                                        var formData = {
                                            'pcid': $('#pcid').val(),
                                            'pcids': $('#pcids').val(),
                                            'pcname': $('#pcname').val(),
                                            'theorymarks': $('#theorymarks').val(),
                                            'totaladdedmarks': $('#totaladdedmarks').val(),
                                            'marks1': $('#marks1').val(),
                                            'marks2': $('#marks2').val(),
                                            'marks3': $('#marks3').val(),
                                            'marks4': $('#marks4').val(),
                                            'marks5': $('#marks5').val(),
                                            'marks6': $('#marks6').val(),
                                            'marks7': $('#marks7').val(),
                                            'marks8': $('#marks8').val(),
                                            'marks9': $('#marks9').val(),
                                            'marks10': $('#marks10').val(),
                                            'marks11': $('#marks11').val(),
                                            'marks12': $('#marks12').val(),
                                            'marks13': $('#marks13').val(),
                                            'marks14': $('#marks14').val(),
                                            'marks15': $('#marks15').val(),
                                            'marks16': $('#marks16').val(),
                                            'marks17': $('#marks17').val(),
                                            'marks18': $('#marks18').val(),
                                            'marks19': $('#marks19').val(),
                                            'marks20': $('#marks20').val(),
                                            'marks21': $('#marks21').val(),
                                            'marks22': $('#marks22').val(),
                                            'marks23': $('#marks23').val(),
                                            'marks24': $('#marks24').val(),
                                            'marks25': $('#marks25').val()
                                        };
                                        $.ajax({
                                            url: "addPcwiseQuestion.io",
                                            data: formData,
                                            type: 'GET',
                                            success: function (data) {
                                                //alert(data.status);
                                                if(data.status=='save'){
                                                    $('#msg').html("<font color=green>Record Successfully Saved</font>");
                                                }
                                            }
                                        });

                                    } else {

                                        //alert("not Equal ");
                                        $('#msg').html("<font color=red>Total Theory Marks and Added Marks are Not Equal</font>");
                                    }
                                }
                                function updatePcwiseQuestion() {
                                    var flag = false;
                                    var totaltheory = $('#theorymarks').val();
                                    var totaladdedmarks = $('#totaladdedmarks').val();

                                    if (parseInt(totaltheory) === parseInt(totaladdedmarks)) {
                                        flag = true;
                                    }
                                    if (flag) {
                                        //alert("Equal ");
                                        var formData = {
                                            'id': $('#id').val(),
                                            'pcid': $('#pcid').val(),
                                            'pcids': $('#pcids').val(),
                                            'pcname': $('#pcname').val(),
                                            'theorymarks': $('#theorymarks').val(),
                                            'totaladdedmarks': $('#totaladdedmarks').val(),
                                            'marks1': $('#marks1').val(),
                                            'marks2': $('#marks2').val(),
                                            'marks3': $('#marks3').val(),
                                            'marks4': $('#marks4').val(),
                                            'marks5': $('#marks5').val(),
                                            'marks6': $('#marks6').val(),
                                            'marks7': $('#marks7').val(),
                                            'marks8': $('#marks8').val(),
                                            'marks9': $('#marks9').val(),
                                            'marks10': $('#marks10').val(),
                                            'marks11': $('#marks11').val(),
                                            'marks12': $('#marks12').val(),
                                            'marks13': $('#marks13').val(),
                                            'marks14': $('#marks14').val(),
                                            'marks15': $('#marks15').val(),
                                            'marks16': $('#marks16').val(),
                                            'marks17': $('#marks17').val(),
                                            'marks18': $('#marks18').val(),
                                            'marks19': $('#marks19').val(),
                                            'marks20': $('#marks20').val(),
                                            'marks21': $('#marks21').val(),
                                            'marks22': $('#marks22').val(),
                                            'marks23': $('#marks23').val(),
                                            'marks24': $('#marks24').val(),
                                            'marks25': $('#marks25').val()
                                        };
                                        $.ajax({
                                            url: "updatePcwiseQuestion.io",
                                            data: formData,
                                            type: 'GET',
                                            success: function (data) {
                                                //alert(data.status);
                                                if(data.status=='save'){
                                                    $('#msg').html("<font color=green>Record Successfully Update</font>");
                                                }
                                            }
                                        });

                                    } else {

                                        //alert("not Equal ");
                                        $('#msg').html("<font color=red>Total Theory Marks and Added Marks are Not Equal</font>");
                                    }
                                }
        </script>
    </body>

</html>
