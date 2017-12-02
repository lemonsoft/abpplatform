<%-- 
    Document   : addlanguage
    Created on : 9 Nov, 2017, 2:29:37 PM
    Author     : ss
--%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="<%=request.getContextPath()%>/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->

        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="<%=request.getContextPath()%>/assets/global/scripts/datatable.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <script type="text/javascript"
        src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <!--[if lte IE 9]>
            <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>    
            <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
        <![endif]-->    

        <!--[if lte IE 8]>
            <link type="text/css" rel="stylesheet" href="css/smart-forms-ie8.css">
        <![endif]-->
        <script>
            $(document).ready(function () {

                $('#showform').hide();

            });



            function editQuestion(id) {


                if (id) {

                    $.ajax({
                        url: "getMultiQuestion.io",
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        data: {mqid: id},
                        type: 'GET',
                        success: function (data) {

                            $("#qid").val(data.ID);
                            $("#question").val(data.question_title);
                            $("#option1").val(data.option1);
                            $("#option2").val(data.option2);
                            $("#option3").val(data.option3);
                            $("#option4").val(data.option4);
                            $('#showform').show();
                        }
                    });
                }
            }
            function updateQuestion() {
                var mqid = $("#qid").val();
                var question = $("#question").val();
                var option1 = $("#option1").val();
                var option2 = $("#option2").val();
                var option3 = $("#option3").val();
                var option4 = $("#option4").val();

                if (mqid) {

                    $.ajax({
                        url: "updateQuestion.io",
                        data: {mqid: mqid, question: question, option1: option1, option2: option2, option3: option3, option4: option4},
                        type: 'GET',
                        success: function (data) {
                            //alert(data.status);
                            $("#qid").val('');
                            $("#question").val('');
                            $("#option1").val('');
                            $("#option2").val('');
                            $("#option3").val('');
                            $("#option4").val('');

                            $('#showform').hide();
                            location.reload();
                        }
                    });
                }
            }
        </script>
    </head>
    <body >

        <div class="smart-wrap">
            <div class="smart-forms smart-container wrap-full">

                <div class="form-header header-blue">
                    <h4><i class="fa fa-pencil-square"></i>Multilingual Question</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="questions" enctype="multipart/form-data">

                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <c:if test = "${records !=null}">
                                <div class="frm-row"   id="displaytabledata">
                                    <display:table name="records" class="table table-bordered"   pagesize="10">
                                        <display:column property="question_id" title="QuestionID" />
                                        <display:column property="language_id" title="LanguageID" />
                                        <display:column property="question_title" style="white-space:wrap;" title="Question" />
                                        <display:column property="option1" title="Option1" />
                                        <display:column property="option2" title="Option2" />
                                        <display:column property="option3" title="Option3" />
                                        <display:column property="option4" title="Option4" />
                                        <display:column property="actions" title="Edit" />


                                    </display:table>

                                </div>
                            </c:if>
                        </div>
                        <div class="frm-row" id="showform" >
                            <div class="frm-row" >
                                <input type="hidden" id="qid" name="qid"  />
                                <div class="section colm colm4"> 
                                    <div class="section">
                                        <label for="names" class="field-label">Question</label>
                                        <label class="field prepend-icon">
                                            <textarea name="question"  rows="5" cols="20" id="question" class="gui-input"></textarea>


                                            <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->
                                <div class="section colm colm4">
                                    <div class="section">
                                        <label for="names" class="field-label">Option1</label>
                                        <label class="field prepend-icon">
                                            <input  id="option1" name="option1" class="gui-input" />

                                            <span class="field-icon"><i class="fa fa-language"></i></span>  
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->
                                <div class="section colm colm4">
                                    <div class="section">
                                        <label for="names" class="field-label">Option2</label>
                                        <label class="field prepend-icon">
                                            <input  id="option2" name="option2" class="gui-input" />

                                            <span class="field-icon"><i class="fa fa-language"></i></span>  
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->

                            </div><!-- end frm-row section -->
                            <div class="frm-row">

                                <div class="section colm colm6">
                                    <div class="section">
                                        <label for="names" class="field-label">Option3</label>
                                        <label class="field prepend-icon">
                                            <input  id="option3" name="option3" class="gui-input" />

                                            <span class="field-icon"><i class="fa fa-language"></i></span>  
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->
                                <div class="section colm colm6">
                                    <div class="section">
                                        <label for="names" class="field-label">Option4</label>
                                        <label class="field prepend-icon">
                                            <input  id="option4" name="option4" class="gui-input" />

                                            <span class="field-icon"><i class="fa fa-language"></i></span>  
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->
                                

                            </div><!-- end frm-row section -->
                            <div class="form-footer">


                                <button type="button" class="button btn-blue" onclick="updateQuestion();"><spring:message code="common.button.update" text="Update" /></button>

                                <button type="reset" class="button btn-blue"><spring:message code="common.button.cancel" text="Cancel" /></button>
                            </div><!-- end .form-footer section -->
                        </div>
                    </div><!-- end .form-body section -->


                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->


    </body>

</html>
