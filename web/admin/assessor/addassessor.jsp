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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="assessor.title" text="Assessor" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"><a href="<%=request.getContextPath()%>/admin/assessor/initSearch.io" class="button btn-primary block pushed expand"> <spring:message code="common.button.search" text="Search" /> </a></div>

                </div><!-- end .form-header section action="${action}"-->

                <form:form method="post" action="${action}"   commandName="assessor" enctype="multipart/form-data">
                    <form:hidden path="assessorid" />
                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="ssc.title" text="Sector Skill Council" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="ssc_id" id="ssc_id" class="gui-input" >
                                            <form:option value="0">--Select--</form:option>
                                            <form:options items="${ssc}"/>


                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.jobrole" text="Job Role" /></label>
                                    <label class="field prepend-icon">

                                        <form:select path="options" id="jobrole" class="gui-input" multiple="true" size="4" required="true">
                                            <form:option value="">--Select--</form:option>
                                            <form:options items="${jobroles}"/>


                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.firstname" text="First Name" /></label>
                                    <label class="field prepend-icon">
                                        <form:input path="firstname" id="firstname" class="gui-input" placeholder="Enter First Name" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.lastname" text="Last Name" /></label>
                                    <label class="field prepend-icon">
                                        <form:input path="lastname" id="lastname" class="gui-input" placeholder="Enter Last Name" required="true"/>

                                        <span class="field-icon"><i class="fa fa-language"></i></span>  
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.qualification" text="Qualification" /></label>
                                    <label class="field prepend-icon">
                                        <form:input path="qualification" id="qualification" class="gui-input" placeholder="Enter Qualification" required="true"/>

                                        <span class="field-icon"><i class="fa fa-language"></i></span>  
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.contactno" text="Contact No" /></label>
                                    <label class="field prepend-icon">
                                        <form:input path="contactno" id="contactno" class="gui-input" placeholder="Enter Contact No" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.aadharno" text="Aadhar No" /></label>
                                    <label class="field prepend-icon">
                                        <form:input path="aadharno" id="aadharno" class="gui-input" placeholder="Enter Aadhar No" required="true"/>

                                        <span class="field-icon"><i class="fa fa-language"></i></span>  
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.totalexp" text="Total Experience" /></label>
                                    <label class="field prepend-icon">
                                        <form:input path="totalexp" id="totalexp" class="gui-input" placeholder="Enter Total Experience" required="true"/>

                                        <span class="field-icon"><i class="fa fa-language"></i></span>  
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.emailid" text="Email ID" /></label>
                                    <label class="field prepend-icon">
                                        <form:input type="email" path="emailid" id="emailid" class="gui-input" placeholder="Enter Email ID" required="true"/>

                                        <span class="field-icon"><i class="fa fa-language"></i></span>  
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.loginid" text="Login ID" /></label>
                                    <label class="field prepend-icon">
                                        <form:input path="loginid" id="loginid" class="gui-input" placeholder="Enter Login ID" required="true"/>

                                        <span class="field-icon"><i class="fa fa-language"></i></span>  
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.password" text="Password" /></label>
                                    <label class="field prepend-icon">
                                        <form:input type="password" path="password" id="password" class="gui-input" placeholder="Enter Password" required="true"/>

                                        <span class="field-icon"><i class="fa fa-language"></i></span>  
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">

                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.stateid" text="State" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="stateid" id="stateid" class="gui-input" >
                                            <form:option value="0">--Select--</form:option>
                                            <form:options items="${allstates}"/>


                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.districtid" text="District" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="districtid" id="districtid" class="gui-input" >
                                            
                                            <form:option value="0">--Select--</form:option>
                                            <c:if test = "${mode=='update'}"><form:options items="${district}"/></c:if>

                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-language"></i></span>  
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.pincode" text="Pincode" /></label>
                                    <label class="field prepend-icon">
                                        <form:input path="pincode" id="pincode" class="gui-input" placeholder="Enter Pincode" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <c:if test = "${mode=='add'}">
                            <div class="frm-row">

                                <div class="section colm colm4"> 
                                    <div class="section">
                                        <label for="names" class="field-label"><spring:message code="assessor.photoname" text="Upload Image" /></label>
                                        <label class="field prepend-icon">
                                            <input type="file"  name="files" id="photoname" class="gui-input" required/>
                                            
                                            <span class="field-icon"><i class="fa fa-language"></i></span>  
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->
                                <div class="section colm colm4">
                                    <div class="section">
                                        <label for="names" class="field-label"><spring:message code="assessor.aadharimage" text="Attach Aadhar Card" /></label>
                                        <label class="field prepend-icon">
                                            <input type="file" name="files" id="aadharimage" class="gui-input" required/>
                                            
                                            <span class="field-icon"><i class="fa fa-language"></i></span>  
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->
                                <div class="section colm colm4">
                                    <div class="section">
                                        <label for="names" class="field-label"><spring:message code="assessor.resumename" text="Upload Resume" /></label>
                                        <label class="field prepend-icon">
                                            <input type="file" name="files" id="resumename" class="gui-input" required/>
                                            
                                            <span class="field-icon"><i class="fa fa-language"></i></span>  
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section --> 
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
        <script type="text/javascript"
        src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script>

            $(document).ready(function () {
                


            });
            $("#photoname").change(function () {

                var ext = $('#photoname').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                    alert('invalid extension!');
                    $('#photoname').val('');
                }
            });
            $("#aadharimage").change(function () {

                var ext = $('#aadharimage').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                    alert('invalid extension!');
                    $('#aadharimage').val('');
                }
            });
            $("#resumename").change(function () {

                var ext = $('#resumename').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['docx', 'doc']) == -1) {
                    alert('invalid extension!');
                    $('#resumename').val('');
                }
            });
            $("#stateid").change(function () {

                var state_id = $(this).val();
                if (state_id) {

                    $.ajax({
                        url: "getDistricts.io",
                        data: {s_id: state_id},
                        type: 'GET',
                        success: function (data) {


                            var str = "";
                            if (data.length === 0) {
                                str = "<option value=''>------- Select --------</option>";
                            }
                            $.each(data, function (index, jsonObject) {
                                //$.each(jsonObject, function (key, val) {
                                //alert("key : " + jsonObject.ID + " ; value : " + jsonObject.NAME);
                                str = str + "<option value=" + jsonObject.ID + ">" + jsonObject.NAME + "</option>"
                                // });
                            });
                            $("#districtid").html(str);
                        }
                    });
                } else {
                    $("#districtid").html("<option value=''>------- Select --------</option>");
                }
            });

        </script>
    </body>

</html>
