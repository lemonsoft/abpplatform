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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="add.assessor.title" text="Assign Assessor" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"><a href="<%=request.getContextPath()%>/admin/batches/init.io" class="button btn-primary block pushed expand"><spring:message code="add.assessor.batches" text="Batches" /> </a></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="#"  commandName="assessor">

                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="add.assessor.sectorskillcouncil" text="Sector Skill Council" /> ${sscid}</label>

                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="add.assessor.qualificationpack" text="Qualification Pack" />:${qid}</label>

                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="add.assessor.batchid" text="Batch ID" />:${batch_id}</label>

                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="add.assessor.centerid" text="Center Pincode" />: ${pincode}</label>

                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="add.assessor.centerdistrict" text="Center District" /> : ${district}</label>

                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="add.assessor.centerstate" text="Center State" /> : ${state}</label>

                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <h3><spring:message code="add.assessor.title" text="Assigned Assessor" /> </h3>
                        <div class="frm-row">
                            <c:if test = "${record != null}">
                            <display:table name="record" class="table table-bordered" requestURI="addassessor.io" decorator="com.abp.admin.batches.AssessorDecorator" pagesize="1">
                                <display:column property="loginid" titleKey="add.assessor.username"/>
                                <display:column property="firstname" titleKey="add.assessor.name" />
                                <display:column property="emailid" titleKey="add.assessor.emailid" />
                                <display:column property="contactno" titleKey="add.assessor.contactno" />
                                <display:column property="state" titleKey="add.assessor.state" />
                                <display:column property="district" titleKey="add.assessor.district" />
                                <display:column property="pincode" titleKey="add.assessor.pincode" />
                                <display:column property="actions" titleKey="Actions"/>
                            </display:table>
                            </c:if>
                        </div>



                        <div class="frm-row">
                            <h3><spring:message code="add.assessor.allavailableassesor" text="All Available Assessors" /></h3>
                            <div class="section colm colm6"> 
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
                            <div class="section colm colm6">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="assessor.districtid" text="District" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="districtid" id="districtid" class="gui-input" >

                                            <form:option value="0">--Select--</form:option>


                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-language"></i></span>  
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <div class="frm-row" id="displaytabledata">
                            <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">

                            </table>
                        </div>
                    </form:form>

                </div><!-- end .smart-forms section -->
            </div><!-- end .smart-wrap section -->

            <script>


                $("#districtid").change(function () {

                    var stateid = $('#stateid').val();
                    var districtid = $('#districtid').val();
                    if (districtid) {

                        $.ajax({
                            url: "loadassessor.io",
                            data: {stateid: stateid, districtid: districtid},
                            type: 'GET',
                            success: function (data) {
                                //alert(data);
                                $("#example-basic4").empty();
                                $('#example-basic4').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center">&nbsp;</th><th align="center">Username</th><th align="center">Name</th><th align="center">Email ID</th><th align="center">Contact No</th>' +
                                        '<th align="center">State</th><th align="center">District</th><th align="center">Pincode</th></tr></thead >');

                                for (var i = 0, lennos = data.length; i < lennos; i++) {
                                    // alert(data[i].LoginID);
                                    $('#example-basic4 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center"><input type="radio" id="assessorid" name="ID" value="' + data[i].ID + '" /></td><td align="center">' + data[i].LoginID + '</td><td align="center">' + data[i].Name + '</td><td align="center">' + data[i].EmailID + '</td><td align="center">' + data[i].ContactNo + '</td><td align="center">' + data[i].State + '</td><td align="center">' + data[i].District + '</td><td align="center">' + data[i].Pincode + '</td></tr>');


                                }
                                if (data.length > 0) {
                                    $('#example-basic4 tr:last').after('<td colspan="8"><button name="map" type="button" id="mapAssessor" class="button btn-primary block pushed expand" onclick="mapAssessor();">Map Assessor</button></td></tr>');
                                }
                                //                            $("#example-basic4").empty();



                            }
                        });
                    } else {
                        $("#example-basic4").empty();
                        //$("#qpid").html("<option value=''>------- Select --------</option>");
                    }
                });
                $(document).on('click', '#mapAssessor', function () {
                    var id = $('input[name=ID]:checked').val();
                    //alert(id);
                    window.location.href = "<%=request.getContextPath()%>/admin/batches/asignassessor.io?assid=" + id;
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
                                //if (data.length === 0) {
                                str = "<option value=''>------- Select --------</option>";
                                //}
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
