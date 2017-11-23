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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="batches.title" text="Add New Batch" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"><a href="<%=request.getContextPath()%>/admin/batches/init.io" class="button btn-primary block pushed expand"> <spring:message code="common.button.search" text="Search" /> </a></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="batches">
                    <form:hidden path="ID" />
                    <form:hidden path="qpackId" />
                    <form:hidden path="assessorId" />
                    <form:hidden path="questionPaperId" />
                    <div class="form-body theme-blue">

                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.id" text="Batch ID" /></label>
                                    <label class="field prepend-icon">
                                        <form:input path="batch_id" type="number" id="batch_id" class="gui-input" placeholder="Batch ID" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label><div id="result"></div>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.size" text="Batch Size" /></label>
                                    <label class="field prepend-icon">

                                        <form:input path="batch_size" type="number" id="batch_size" class="gui-input" placeholder="Batch Size" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.centerid" text="Center ID" /></label>
                                    <label class="field prepend-icon">

                                        <form:input path="center_id" id="center_id" class="gui-input" placeholder="Center ID" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.assessmentstartdate" text="Assessment Start Date" /></label>
                                    <label class="field prepend-icon">
                                        <form:input path="assessmentStartDate" type="datetime-local" id="assessmentStartDate" class="gui-input" placeholder="Assessment Start Date" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.assessmentenddate" text="Assessment End Date" /></label>
                                    <label class="field prepend-icon">

                                        <form:input path="assessmentEndDate"  type="datetime-local" id="assessmentEndDate" class="gui-input" placeholder="Assessment End Date" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            
                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm4">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.selectproject" text="Select Project" />&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/admin/project/init.io">Add Project</a></label>
                                    <label class="field prepend-icon">
                                        <form:select path="project_id" id="project_id" class="gui-input" >
                                            
                                            <form:option value="0">--Select--</form:option>
                                            <form:options items="${projects}"/>
                                           

                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.state" text="State" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="state_id" id="state_id" class="gui-input" >
                                            <form:option value="0">--Select--</form:option>
                                            <form:options items="${allstates}"/>


                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="atches.district" text="District" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="district_id" id="district_id" class="gui-input" >
                                            
                                            <form:option value="0">--Select--</form:option>
                                            <c:if test = "${mode=='update'}"><form:options items="${district}"/></c:if>

                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-language"></i></span>  
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            
                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.centerpincode" text="Center Pincode" /></label>
                                    <label class="field prepend-icon">
                                        <form:input path="centerPincode" type="number" id="batch_id" class="gui-input" placeholder="Center Pincode" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.centercontactno" text="Center Contact No" /></label>
                                    <label class="field prepend-icon">

                                        <form:input path="centerContactno" type="number" id="batch_size" class="gui-input" placeholder="Contact No" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.centeremailid" text="Center Email ID" /></label>
                                    <label class="field prepend-icon">

                                        <form:input path="centerEmailid" id="centerEmailid" class="gui-input" placeholder="Center ID" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.centeraddress" text="Center Address" /></label>
                                    <label class="field prepend-icon">
                                        <form:textarea path="centerAddress" row="5" cols="10" id="batch_id" class="gui-input" placeholder="Center Address" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.tpname" text="Training Partner Name" /></label>
                                    <label class="field prepend-icon">

                                        <form:input path="tpName"  id="tpName" class="gui-input" placeholder="Batch Size" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.loginrestrict" text="Login Restrict Count" /></label>
                                    <label class="field prepend-icon">

                                        <form:input path="loginRestrict" type="number" id="loginRestrict" class="gui-input" placeholder="Login Restrict Count" required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.captureimage" text="Capture Image" /></label>
                                    <label class="field prepend-icon">
                                        <form:input type="number" path="capturetime" id="capturetime" class="gui-input" placeholder="Image Capture Time(sec)"/>
                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                        
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            
                        </div><!-- end frm-row section -->

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
        
        <script>
            $("#state_id").change(function () {

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
                            $("#district_id").html(str);
                        }
                    });
                } else {
                    $("#district_id").html("<option value=''>------- Select --------</option>");
                }
            });
            $('#batch_id').on('change', function () {

            var current = $(this).val();
            if (current.trim()) {
                $.ajax({
                    url: 'checkBatchID.io',
                    data: {batchid: current},
                    success: function (data) {
                        if (data === 'avail') {
                            $('#result').html("<font color=\"green\">Available</font>");
                        }
                        if (data === 'notavail') {

                            $('#result').html("<font color=\"red\">Batch ID Already Exist</font>");
                            $('#batch_id').val('');
                        }


                    }
                });
            } else {
                $('#result').html("Invalid ID");
            }

        });
            
        </script>
    </body>

</html>
