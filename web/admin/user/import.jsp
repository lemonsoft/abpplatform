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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="user.title" text="Import User" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"><a href="<%=request.getContextPath()%>/admin/batches/init.io" class="button btn-primary block pushed expand"><spring:message code="import.batches" text="Batches" /> </a></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="user" enctype="multipart/form-data">

                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="import.ssc" text="Sector Skill Council" /> :${sscname}</label>
                                    <label class="field prepend-icon">
                                        <spring:message code="import.qpack" text="Qualification Pack" /> :${qpname}
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div>
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="import.note1" text="Note :  It will accept only Excel files with *.xls and.xlsx extension only." /></label>
                                    <label class="field prepend-icon">
                                        <spring:message code="import.note2" text="Use the same format as given below" /> :<a href="#" id="download"><spring:message code="import.download" text="Download" /></a> 
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
                                        <button type="submit" class="button btn-blue"><spring:message code="import.upload" text="Upload" /></button>
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->

                        <div class="frm-row">
                            <div class="section colm colm12"> 
                                <div class="section" style='overflow:auto;width:1200px;'>
                                    <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" id="format">
                                        <thead>
                                        <th><spring:message code="import.batchid" text="BatchID" /></th><th><spring:message code="import.enrollno" text="Enrollment No" /></th>
                                        <th><spring:message code="import.trainingagency" text="Name of Training Agency" /></th><th><spring:message code="import.nameoftrainee" text="Name of the Trainee" /></th>
                                        <th><spring:message code="import.gender" text="Gender(M/F/T)" /></th><th><spring:message code="import.category" text="Category(Gen/SC/ST/OBC)" /></th>
                                        <th><spring:message code="import.dob" text="DOB" /></th><th><spring:message code="import.fathername" text="Father's name" /></th>
                                        <th><spring:message code="import.mothername" text="Mother's name" /></th><th><spring:message code="import.addresstrainee" text="Address of the trainee" /></th>
                                        <th><spring:message code="import.district" text="District" /></th><th><spring:message code="import.state" text="State" /></th>
                                        <th><spring:message code="import.traineephone" text="Phone no of the trainee" /></th><th><spring:message code="import.educationalqualification" text="Educational Qualification" /></th>
                                        <th><spring:message code="import.techqualification" text="Tech Qualification(If Any)" /></th><th><spring:message code="import.occupationalstandard" text="Is the training conducted linked to Occupational Standards" /></th>
                                        <th><spring:message code="import.jobrole" text="Job Role" /></th><th><spring:message code="import.isnsdcpartner" text="Is the training agency an NSDC partner? (Y/N)" /></th>
                                        <th><spring:message code="import.skilling_upskilling" text="Skilling/Upskilling" /></th><th><spring:message code="import.coursestartdate" text="Course Start Date" /></th>
                                        <th><spring:message code="import.coursecompletion" text="Date of course completion" /></th><th><spring:message code="import.trainername" text="Trainer's name" /></th>
                                        <th><spring:message code="import.trainingproviderspoc" text="Training Provider SPOC name" /></th><th><spring:message code="import.contactno" text="Contact No" /></th>
                                        <th><spring:message code="import.emailid" text="Email ID" /></th><th><spring:message code="import.totalfeecharged" text="Total fee charged for training" /></th>
                                        <th><spring:message code="import.dateofassesment" text="Date of assessment" /></th><th><spring:message code="import.nameofagency" text="Name of assessment agency" /></th>
                                        <th><spring:message code="import.totalfeecharged" text="Total Fee charged for assessment" /></th><th><spring:message code="import.assesmentfee" text="Assessment fee charged from whom?" /></th>
                                        <th><spring:message code="import.totalfee" text="Total Fee charged for certification" /></th><th><spring:message code="import.certificationfee" text="Certification fee charged from whom?" /></th>
                                        <th><spring:message code="import.noofyears" text="No of years of prior work experience" /></th><th><spring:message code="import.keycontactemployer" text="Employed (Y/N)" /></th>
                                        <th><spring:message code="import.employer" text="Name of the employer" /></th><th><spring:message code="import.employer" text="Name of key contact from the employer" /></th>
                                        <th><spring:message code="import.phonenokeycontact" text="Phone no of the key contact from the employer" /></th><th><spring:message code="import.postcertification" text="% age increase in wage post certification" /></th>
                                        <th><spring:message code="import.benifitsgained" text="Benefits gained from certification" /></th>

                                        </thead>
                                    </table>
                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <c:if test = "${importdata !=null}">
                            <div class="frm-row" style='overflow:auto;width:1200px;height:650px;'>
                                <display:table name="importdata" class="table table-bordered" requestURI="importshow.io"  pagesize="200">
                                    <display:column property="batchid" titleKey="import.batchid" />
                                    <display:column property="enrollmentno" titleKey="import.enrollno" />
                                    <display:column property="trainingagency" titleKey="import.trainingagency" />
                                    <display:column property="traineename" titleKey="import.nameoftrainee" />
                                    <display:column property="gender" titleKey="import.gender" />
                                    <display:column property="category" titleKey="import.category" />
                                    <display:column property="dateofbirth" titleKey="import.dob" />
                                    <display:column property="fathername" titleKey="import.fathername" />
                                    <display:column property="mothername" titleKey="import.mothername" />
                                    <display:column property="traineeadress" titleKey="import.addresstrainee" />
                                    <display:column property="district" titleKey="import.district" />
                                    <display:column property="states" titleKey="import.state" />
                                    <display:column property="traineephone" titleKey="import.traineephone" />
                                    <display:column property="eduqualification" titleKey="import.educationalqualification" />
                                    <display:column property="techqualification" titleKey="import.techqualification" />
                                    <display:column property="istraineconductocupstd" titleKey="import.occupationalstandard" />
                                    <display:column property="jobrole" titleKey="import.jobrole" />
                                    <display:column property="istagencynsdcpartner" titleKey="import.isnsdcpartner" />
                                    <display:column property="skillingupskilling" titleKey="import.skilling_upskilling" />
                                    <display:column property="coursestartdate" titleKey="import.coursestartdate" />
                                    <display:column property="dateofcompletion" titleKey="import.coursecompletion" />
                                    <display:column property="trainersname" titleKey="import.trainername" />
                                    <display:column property="tproviderspocname" titleKey="import.trainingproviderspoc" />
                                    <display:column property="contactno" titleKey="import.contactno" />
                                    <display:column property="emailid" titleKey="import.emailid" />
                                    <display:column property="totalfeefortraining" titleKey="import.totalfeecharged" />
                                    <display:column property="dateofassessment" titleKey="import.dateofassesment" />
                                    <display:column property="assessmentagency" titleKey="import.nameofagency" />
                                    <display:column property="totalfeeforassessment" titleKey="import.totalfeecharged" />
                                    <display:column property="assessmentfeefromwhom" titleKey="import.assesmentfee" />
                                    <display:column property="totalfeeforcertification" titleKey="import.totalfee" />
                                    <display:column property="certificationchargefromwhom" titleKey="import.certificationfee" />
                                    <display:column property="priorworkexpyear" titleKey="import.noofyears" />
                                    <display:column property="employed" titleKey="import.employed" />
                                    <display:column property="nameofemployer" titleKey="import.employer" />
                                    <display:column property="keycontactfrmemployer" titleKey="import.keycontactemployer" />
                                    <display:column property="phonenoofkeycontact" titleKey="import.phonenokeycontact" />
                                    <display:column property="perageincreasewagepostcert" titleKey="import.postcertification" />
                                    <display:column property="benifitgainfrmcert" titleKey="import.benifitsgained" />
                                    <display:column property="status" titleKey="Status" />

                                </display:table>
                                <c:if test = "${displaybtn == 'yes'}">
                                    <button type="button" onclick="startimport();" class="button btn-blue"><spring:message code="import.importusers" text="Import Users" /></button>
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

                window.location.href = "<%=request.getContextPath()%>/admin/users/importusers.io";

            }
            $('#download').click(function (e) {
                e.preventDefault();  //stop the browser from following
                window.location.href = '<%=request.getContextPath()%>/uploaded/download/sample_students_excel.xls';
            });
        </script>

    </body>

</html>
