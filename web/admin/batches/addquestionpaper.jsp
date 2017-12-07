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
                    <h4><i class="fa fa-pencil-square"></i>Assign Question Paper</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"><a href="<%=request.getContextPath()%>/admin/batches/init.io" class="button btn-primary block pushed expand">Batches </a></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="#"  commandName="assessor">
                    
                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label">Sector Skill Council : ${sscid}</label>

                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm6">
                                <div class="section">
                                    <label for="names" class="field-label">Qualification Pack :${qid}</label>

                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm12"> 
                                <div class="section">
                                    <label for="names" class="field-label">Batch ID :${batch_id}</label>

                                </div><!-- end section -->
                            </div><!-- end section -->


                        </div><!-- end frm-row section -->
                        <span id="error" style="color:red;">${error}</span>
                        <h3>Assigned Question Paper</h3>
                        <div class="frm-row">
                            <c:if test = "${record != null}">
                                <display:table name="record" class="table table-bordered" requestURI="addquestionpaper.io" decorator="com.abp.admin.batches.QuestionPaperDecorator" pagesize="1">
                                    <display:column property="questionpaperid" title="QPID"/>
                                    <display:column property="questionpapername" title="QP Desc" />
                                    <display:column property="totaltime" title="Total Time" />
                                    <display:column property="totalmarks" title="Total Marks" />
                                    <display:column property="israndom" title="Is Random" />
                                    <display:column property="actions" title="Actions"/>
                                </display:table>
                            </c:if>
                        </div>



                        <div class="frm-row">
                            <h3>All Available Question Paper</h3>


                        </div><!-- end frm-row section -->
                        <div class="frm-row" id="displaytabledata">
                            <c:if test = "${allquestionpapers != null}">
                                <display:table name="allquestionpapers" class="table table-bordered" requestURI="addquestionpaper.io" decorator="com.abp.admin.batches.AllQuestionPaperDecorator" pagesize="10">
                                    <display:column property="questionpaperid" title="QPID"/>
                                    <display:column property="questionpapername" title="QP Desc" />
                                    <display:column property="totaltime" title="Total Time" />
                                    <display:column property="totalmarks" title="Total Marks" />
                                    <display:column property="israndom" title="Is Random" />

                                </display:table>
                                <button name="map" type="button" id="mapQuestionPaper" class="button btn-primary block pushed expand" onclick="mapQuestionPaper();">Map Question Paper</button>
                            </c:if>
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
                $(document).on('click', '#mapQuestionPaper', function () {
                    var id = $('input[name=ID]:checked').val();
                    //alert(id);
                    window.location.href = "<%=request.getContextPath()%>/admin/batches/asignQuestionPaper.io?qpaperid=" + id;
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

                function deleteQuestionPaper(id) {

                    
                    if (id) {

                        $.ajax({
                            url: "deleteQuestionPaper.io",
                            data: {batchid: id},
                            type: 'GET',
                            success: function (data) {
                                alert(data.status);
                                window.location.reload();
                            }
                        });
                    } else {
                        alert("Invalid Batch Id.");
                    }
                }
            </script>
    </body>

</html>
