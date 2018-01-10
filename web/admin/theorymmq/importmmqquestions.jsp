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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="importmmq.title" text="Import MMQ" /></h4>
                    <div style="position: absolute;top:5px;right:15px;width: 200px;"><a href="<%=request.getContextPath()%>/admin/theorymmq/init.io" class="button btn-primary block pushed expand"><spring:message code="importmmq.theorymmq" text="Theory MMQ" /> </a></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="theorymmq" enctype="multipart/form-data">

                    <div class="form-body theme-blue">

                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="importmmq.note1" text="Note :  It will accept only Excel files with *.xls and.xlsx extension only." /></label>
                                    <label class="field prepend-icon">
                                        <spring:message code="importmmq.note2" text="Use the same format as given below" /> :<a href="#" id="download"><spring:message code="importmmq.download" text="Download" /></a> 
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
                                        <button type="submit" class="button btn-blue"><spring:message code="importmmq.upload" text="Upload" /></button>
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->

                        <div class="frm-row">
                            <div class="section colm colm12"> 
                                <div class="section" style='overflow:auto;width:1200px;'>
                                    <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" id="format">
                                        <thead>
                                        <th><spring:message code="importmmq.question" text="Question" /></th><th><spring:message code="importmmq.option1" text="Option1" /></th>
                                        <th><spring:message code="importmmq.option2" text="Option2" /></th><th><spring:message code="importmmq.option3" text="Option3" /></th>
                                        <th><spring:message code="importmmq.option4" text="Option4" /></th>
                                        <th><spring:message code="importmmq.cans1" text="CANS1" /></th><th><spring:message code="importmmq.cans2" text="CANS2" /></th>
                                        <th><spring:message code="importmmq.cans3" text="CANS3" /></th><th><spring:message code="importmmq.cans4" text="CANS4" /></th>
                                        <th><spring:message code="importmmq.solution" text="Solution" /></th>
                                        <th><spring:message code="importmmq.noofoptions" text="NoofOptions" /></th><th><spring:message code="importmmq.questiontypeid" text="QuestionTypeID" /></th>
                                        <th><spring:message code="importmmq.questionlevelid" text="QuestionLevelID" /></th>
                                        <th><spring:message code="importmmq.pcidwithmarks" text="PCIDsWithMarks" /></th>

                                        </thead>
                                    </table>
                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <c:if test = "${theorydata !=null}">
                            <div class="frm-row" >
                                <div class="section" style='overflow:auto;width:1200px;'>
                                    <display:table name="theorydata" class="table table-bordered" requestURI="importQuestionExcel.io"  pagesize="50">
                                        <display:column property="question_title" titleKey="importmmq.question" />
                                        <display:column property="option1" titleKey="importmmq.option1" />
                                        <display:column property="option2" titleKey="importmmq.option2" />
                                        <display:column property="option3" titleKey="importmmq.option3" />
                                        <display:column property="option4" titleKey="importmmq.option4" />
                                        <display:column property="cans1" titleKey="importmmq.cans1" />
                                        <display:column property="cans2" titleKey="importmmq.cans2" />
                                        <display:column property="cans3" titleKey="importmmq.cans3" />
                                        <display:column property="cans4" titleKey="importmmq.cans4" />
                                        <display:column property="solution" titleKey="importmmq.solution" />
                                        <display:column property="noofoption" titleKey="importmmq.noofoptions" />
                                        <display:column property="question_type" titleKey="importmmq.questiontypeid" />
                                        <display:column property="question_level" titleKey="importmmq.questionlevelid" />
                                        <display:column property="pcidwithmarks" titleKey="importmmq.pcidwithmarks" />
                                        <display:column property="status" titleKey="importmmq.status" />

                                    </display:table>
                                    <c:if test = "${isError == true}">
                                        <font color="red"><h4><spring:message code="importmmq.note3" text="Excel Sheet Have Errors." /></h4></font>
                                    </c:if>
                                    <c:if test = "${isError == false}">
                                        <button type="button" onclick="startimport();" class="button btn-blue"><spring:message code="importmmq.importquestion" text="Import Question" /></button>
                                    </c:if>
                                </div> </div>
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

                window.location.href = "<%=request.getContextPath()%>/admin/theorymmq/inserttheorymmq.io";

            }
            $('#download').click(function (e) {
                e.preventDefault();  //stop the browser from following
                window.location.href = '<%=request.getContextPath()%>/uploaded/download/sample_question_excel_mmq.xls';
            });
        </script>

    </body>

</html>
