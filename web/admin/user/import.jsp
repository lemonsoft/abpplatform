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
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"><a href="<%=request.getContextPath()%>/admin/batches/init.io" class="button btn-primary block pushed expand">Batches </a></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="user" enctype="multipart/form-data">

                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label">Sector Skill Council :${sscid}</label>
                                    <label class="field prepend-icon">
                                        
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div>
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label">Qualification Pack :${qid}</label>
                                    <label class="field prepend-icon">
                                        
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
                                        <th>BatchID</th><th>Enrollment No</th>
                                        <th>Name of Training Agency</th><th>Name of the Trainee</th>
                                        <th>Gender(M/F/T)</th><th>Category(Gen/SC/ST/OBC)</th>
                                        <th>DOB</th><th>Father's name</th>
                                        <th>Mother's name</th><th>Address of the trainee</th>
                                        <th>District</th><th>State</th>
                                        <th>Phone no of the trainee</th><th>Educational Qualification</th>
                                        <th>Tech Qualification(If Any)</th><th>Is the training conducted linked to Occupational Standards</th>
                                        <th>Job Role</th><th>Is the training agency an NSDC partner? (Y/N)</th>
                                        <th>Skilling/Upskilling</th><th>Course Start Date</th>
                                        <th>Date of course completion</th><th>Trainer's name</th>
                                        <th>Training Provider SPOC name</th><th>Contact No</th>
                                        <th>Email ID</th><th>Total fee charged for training</th>
                                        <th>Date of assessment</th><th>Name of assessment agency</th>
                                        <th>Total Fee charged for assessment</th><th>Assessment fee charged from whom?</th>
                                        <th>Total Fee charged for certification</th><th>Certification fee charged from whom?</th>
                                        <th>No of years of prior work experience</th><th>Employed (Y/N)</th>
                                        <th>Name of the employer</th><th>Name of key contact from the employer</th>
                                        <th>Phone no of the key contact from the employer</th><th>% age increase in wage post certification</th>
                                        <th>Benefits gained from certification</th>

                                        </thead>
                                    </table>
                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <c:if test = "${importdata !=null}">
                            <div class="frm-row" style='overflow:auto;width:1200px;height:650px;'>
                                <display:table name="importdata" class="table table-bordered" requestURI="importshow.io"  pagesize="200">
                                    <display:column property="batchid" title="Batch ID" />
                                    <display:column property="enrollmentno" title="Enrollment No" />
                                    <display:column property="trainingagency" title="Training Agency" />
                                    <display:column property="traineename" title="Trainee Name" />
                                    <display:column property="gender" title="Gender" />
                                    <display:column property="category" title="Category" />
                                    <display:column property="dateofbirth" title="Date Of Birth" />
                                    <display:column property="fathername" title="Father Name" />
                                    <display:column property="mothername" title="Mother Name" />
                                    <display:column property="traineeadress" title="Trainee Address" />
                                    <display:column property="district" title="District" />
                                    <display:column property="states" title="State" />
                                    <display:column property="traineephone" title="Phone no of the trainee" />
                                    <display:column property="eduqualification" title="Educational Qualification" />
                                    <display:column property="techqualification" title="Tech Qualification(If Any)" />
                                    <display:column property="istraineconductocupstd" title="Is the training conducted linked to Occupational Standards" />
                                    <display:column property="jobrole" title="Job Role" />
                                    <display:column property="istagencynsdcpartner" title="Is the training agency an NSDC partner? (Y/N)" />
                                    <display:column property="skillingupskilling" title="Skilling/Upskilling" />
                                    <display:column property="coursestartdate" title="Course Start Date" />
                                    <display:column property="dateofcompletion" title="Date of course completion" />
                                    <display:column property="trainersname" title="Trainer's name" />
                                    <display:column property="tproviderspocname" title="Training Provider SPOC name" />
                                    <display:column property="contactno" title="Contact No" />
                                    <display:column property="emailid" title="Email ID" />
                                    <display:column property="totalfeefortraining" title="Total fee charged for training" />
                                    <display:column property="dateofassessment" title="Date of assessment" />
                                    <display:column property="assessmentagency" title="Name of assessment agency" />
                                    <display:column property="totalfeeforassessment" title="Total Fee charged for assessment" />
                                    <display:column property="assessmentfeefromwhom" title="Assessment fee charged from whom?" />
                                    <display:column property="totalfeeforcertification" title="Total Fee charged for certification" />
                                    <display:column property="certificationchargefromwhom" title="Certification fee charged from whom?" />
                                    <display:column property="priorworkexpyear" title="No of years of prior work experience" />
                                    <display:column property="employed" title="Employed (Y/N)" />
                                    <display:column property="nameofemployer" title="Name of the employer" />
                                    <display:column property="keycontactfrmemployer" title="Name of key contact from the employer" />
                                    <display:column property="phonenoofkeycontact" title="Phone no of the key contact from the employer" />
                                    <display:column property="perageincreasewagepostcert" title="% age increase in wage post certification" />
                                    <display:column property="benifitgainfrmcert" title="Benefits gained from certification" />
                                    <display:column property="status" title="Status" />

                                </display:table>
                                <c:if test = "${displaybtn == 'yes'}">
                                <button type="button" onclick="startimport();" class="button btn-blue">Import Users</button>
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
        </script>

    </body>

</html>
