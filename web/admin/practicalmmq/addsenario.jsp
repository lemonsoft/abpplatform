
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
        <link href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />

    </head>

    <div class="smart-wrap">
        <div class="smart-forms smart-container wrap-full">

            <div class="form-header header-blue">
                <h4><i class="fa fa-pencil-square"></i><spring:message code="addsenario.title" text="Add New Senario based Practical Question" /></h4>
                <div style="position: absolute;top:5px;right:25px;width: 200px;"><a href="<%=request.getContextPath()%>/admin/practicalmmq/init.io" class="button btn-primary block pushed expand"><spring:message code="addsenario.practicalmmq" text="Practical MMQ" />  </a></div>

            </div><!-- end .form-header section -->

            <form:form method="post" action="${action}"  commandName="practicalmmq">
                <form:hidden path="id" id="id"/>
                <form:hidden path="qpackid" />
                <div class="form-body theme-blue">
                    <div class="frm-row">
                        <div class="section colm colm6"> 
                            <div class="section">
                                <label for="names" class="field-label"><spring:message code="addsenario.sscid" text="Sector Skill Council" /> : ${sscname}</label>

                            </div><!-- end section -->
                        </div><!-- end section -->
                        <div class="section colm colm6">
                            <div class="section">
                                <label for="names" class="field-label"><spring:message code="addsenario.qpackid" text="Qualification Pack" /> :${qpname}</label>

                            </div><!-- end section -->
                        </div><!-- end section -->

                    </div><!-- end frm-row section -->

                    <div class="frm-row">
                        <label for="names" class="field-label"><spring:message code="addsenario.entersenario" text="Enter Senario" /></label>
                        <div class="section colm colm6">
                            <label class="field prepend-icon">
                                <form:textarea rows="5" cols="20" path="senario" id="senario" class="gui-input"></form:textarea>
                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                    <c:if test = "${mode=='update'}">
                        <div class="frm-row">
                            <display:table name="records" class="table table-bordered" requestURI="initSearch.io" decorator="com.abp.admin.practicalmmq.SrcDecorator" pagesize="10">
                                <display:column property="id" titleKey="addsenario.srno" />
                                <display:column property="question" titleKey="addsenario.question"/>
                                <display:column property="actions"  titleKey="addsenario.maptopc"/>
                                <display:setProperty name="paging.banner.placement" value="bottom" />
                            </display:table>
                        </div>

                    </c:if>
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
                                                        <label class="control-label"><spring:message code="addsenario.question" text="Question" /></label>
                                                        <input type="text" name="question[]" id="question" placeholder="Question" class="gui-input" />

                                                    </div>

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
                                                <i class="fa fa-plus"></i><spring:message code="addsenario.addmorepc" text="Add More Question" /></a>&nbsp;<button type="button" onclick="insertQuestions();" class="button btn-blue"><spring:message code="addsenario.insert" text="Insert" /></button>
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
                        <button type="submit" class="button btn-blue" onclick="updateSenario();"><spring:message code="common.button.update" text="Update" /> Senario</button>
                    </c:if>
                    <button type="reset" class="button btn-blue"><spring:message code="common.button.cancel" text="Cancel" /></button>
                </div><!-- end .form-footer section -->
            </form:form>

        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->

    <script>
        function insertQuestions() {
            var sid = $('#id').val();
            //alert(sid);

            var values = $("input[id='question']")
                    .map(function () {
                        return $(this).val();
                    }).get();

            //alert(values);

            $.ajax({
                url: "insertQuestion.io",
                data: {sid: sid, values: JSON.stringify(values)},
                type: 'GET',
                success: function (data) {
                    //alert(data.status);
                    $("input[id='question']").val('');
                    location.reload();
                }
            });
        }
        function editMapping(id) {
            //alert(id);
            var w = window.open("<%=request.getContextPath()%>/admin/practicalmmq/editMapping.io?recid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
        }
        function updateSenario() {
            var senarioid = $('#id').val();
            var senario = $('#senario').val();
            //alert(senario);
            $.ajax({
                url: "updateSenario.io",
                data: {senarioid: senarioid, senario: senario},
                type: 'GET',
                success: function (data) {
                    //alert(data.status);

                    location.reload();
                }
            });


        }
    </script>




    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-repeater/jquery.repeater.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->

