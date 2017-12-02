<%-- 
    Document   : addlanguage
    Created on : 9 Nov, 2017, 2:29:37 PM
    Author     : ss
--%>

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
                    <h4><i class="fa fa-pencil-square"></i>Import Multilingual Theory MMQ</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 200px;"><a href="<%=request.getContextPath()%>/admin/theorymmq/init.io" class="button btn-primary block pushed expand">Theory MMQ </a></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="questionsmultilang" enctype="multipart/form-data">

                    <div class="form-body theme-blue">

                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label">Note :  It will accept only Excel files with *.xls and.xlsx extension only.</label>
                                    <label class="field prepend-icon">
                                        Use the same format as given below :<a href="#" id="download">Download</a> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div>

                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="user.upload" text="Select Excel File" /></label>
                                    <label class="field prepend-icon">
                                        <input type="file" name="file" id="excelfile" class="gui-input" required/>
                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label">&nbsp;</label>
                                    <label class="field prepend-icon">
                                        <button type="submit" class="button btn-blue">Upload</button>
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->

                        <div class="frm-row">
                            <div class="section colm colm12"> 
                                <div class="section" style='overflow:auto;width:1200px;'>
                                    <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" id="format">
                                        <thead>
                                        <th>QuestionID</th><th>LanguageID</th><th>Question</th><th>Option1</th>
                                        <th>Option2</th><th>Option3</th>
                                        <th>Option4</th>
                                        </thead>
                                    </table>
                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <c:if test = "${importdata !=null}">
                            <div class="frm-row" style='overflow:auto;width:1200px;height:650px;'>
                                <display:table name="importdata" class="table table-bordered" requestURI="importQuestionExcel.io"  pagesize="50">
                                   <display:column property="question_id" title="QuestionID" />
                                    <display:column property="language_id" title="LanguageID" />
                                    <display:column property="question_title" title="Question" />
                                    <display:column property="option1" title="Option1" />
                                    <display:column property="option2" title="Option2" />
                                    <display:column property="option3" title="Option3" />
                                    <display:column property="option4" title="Option4" />
                                     <display:column property="status" title="Status" />

                                </display:table>
                                <c:if test = "${isError == true}">
                                    <font color="red"><h4>Excel Sheet Have Errors.</h4></font>
                                </c:if>
                                <c:if test = "${isError == false}">
                                    <button type="button" onclick="startimport();" class="button btn-blue">Import Question</button>
                                </c:if>
                            </div>
                        </c:if>

                    </div><!-- end .form-body section -->


                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
        <script>

            $("#excelfile").change(function () {

                var ext = $('#excelfile').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['xls', 'xlsx']) == -1) {
                    alert('invalid extension!');
                    $('#excelfile').val('');
                }
            });
            function startimport() {

                window.location.href = "<%=request.getContextPath()%>/admin/theorymmq/insertMultiQuestions.io";

            }
            $('#download').click(function (e) {
                e.preventDefault();  //stop the browser from following
                window.location.href = '<%=request.getContextPath()%>/uploaded/download/sample_question_excel.xls';
            });
        </script>

    </body>

</html>
