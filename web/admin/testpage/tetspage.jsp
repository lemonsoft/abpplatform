
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
        <link href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />

    </head>

    <div class="smart-wrap">
        <div class="smart-forms smart-container wrap-full">

            <div class="form-header header-blue">
                <h4><i class="fa fa-pencil-square"></i>Add New Senario based Practical Question</h4>
                <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

            </div><!-- end .form-header section -->

            <form:form method="post" action="${action}"  commandName="practicalmmq">
                <form:hidden path="id" />
                <form:hidden path="qpackid" />
                <div class="form-body theme-blue">
                    <div class="frm-row">
                        <div class="section colm colm6"> 
                            <div class="section">
                                <label for="names" class="field-label">Sector Skill Council : ${sscname}</label>

                            </div><!-- end section -->
                        </div><!-- end section -->
                        <div class="section colm colm6">
                            <div class="section">
                                <label for="names" class="field-label">Qualification Pack :${qpname}</label>

                            </div><!-- end section -->
                        </div><!-- end section -->

                    </div><!-- end frm-row section -->

                    <div class="frm-row">
                        <label for="names" class="field-label">Enter Senario</label>
                        <div class="section colm colm6">
                            <label class="field prepend-icon">
                                <form:textarea rows="5" cols="20" path="senario" class="gui-input"></form:textarea>
                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->



                        </div><!-- end frm-row section -->
                                <c:if test = "${mode=='update'}">
                        <hr>
                        <div class="frm-row">
                            <form role="form" action="#" class="form-horizontal">

                                <div class="form-group">

                                    <div class="col-md-9">
                                        <div class="mt-repeater">
                                            <div data-repeater-list="group-b">
                                                <div data-repeater-item class="row">
                                                    <div class="col-md-7">
                                                        <label class="control-label">Question</label>
                                                        <input type="text" placeholder="Question" class="gui-input" /> </div>

                                                    <div class="col-md-1">
                                                        <label class="control-label">&nbsp;&nbsp;</label><br/>
                                                        <a href="javascript:;" data-repeater-delete class="btn btn-danger">
                                                            <i class="fa fa-close"></i>
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                            <br>
                                            <a href="javascript:;" data-repeater-create class="btn btn-info mt-repeater-add">
                                                <i class="fa fa-plus"></i> Add More Question</a>&nbsp;<button type="button" class="button btn-blue">Insert</button>
                                            <br>
                                            <br> 
                                        </div>
                                    </div>
                                </div>

                            </form> 
                        </div><!-- end frm-row section -->
                        </c:if>

                    </div><!-- end .form-body section -->
                    <div class="form-footer">
                    <c:if test = "${mode == 'add'}">
                        <button type="submit" class="button btn-blue"><spring:message code="common.button.submit" text="Submit" /></button>
                    </c:if>
                    <c:if test = "${mode=='update'}">
                        <button type="submit" class="button btn-blue"><spring:message code="common.button.update" text="Update" /></button>
                    </c:if>
                    <button type="reset" class="button btn-blue"><spring:message code="common.button.cancel" text="Cancel" /></button>
                </div><!-- end .form-footer section -->
            </form:form>

        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->






    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-repeater/jquery.repeater.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->

