<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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


        <!--[if lte IE 9]>
            <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>    
            <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
        <![endif]-->    

        <!--[if lte IE 8]>
            <link type="text/css" rel="stylesheet" href="css/smart-forms-ie8.css">
        <![endif]-->
        <!--        <link rel="stylesheet" type="text/css" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/themes/smoothness/jquery-ui.css">
                <link rel="stylesheet" type="text/css" href="http://ludo.cubicphuse.nl/jquery-treetable/css/screen.css">
                <link rel="stylesheet" type="text/css" href="http://ludo.cubicphuse.nl/jquery-treetable/css/jquery.treetable.css">
                <link rel="stylesheet" type="text/css" href="http://ludo.cubicphuse.nl/jquery-treetable/css/jquery.treetable.theme.default.css">
                <script type="text/javascript"   src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
                <script type="text/javascript"   src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
                <script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min.js"></script>
                <script type='text/javascript' src="http://ludo.cubicphuse.nl/jquery-treetable/jquery.treetable.js"></script>-->


    </head>
    <body>
        <div class="smart-wrap">
            <div class="smart-forms smart-container wrap-full">

                <div class="form-header header-blue">
                    <h4><i class="fa fa-pencil-square"></i> <spring:message code="batches.titlesrc" text="Batches" /></h4>
                    <div style="position: absolute;top:10px;right:90px;width:150px;"><a href="#" id="addbatch" class="button btn-primary block pushed expand"> <spring:message code="batches.title" text="Add New Batch" /> </a></div>

                </div><!-- end .form-header section -->
                <form:form method="post" action="${action}"   commandName="batches" >
                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="ssc.title" text="Sector Skill Council" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="ssc_id" name="ssc_id" id="ssc_id" class="gui-input" >
                                            <form:option value="">--Select--</form:option>
                                            <form:options items="${ssc}"/>
                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="qualificationpack.title" text="Qualification Packs" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="qpid" id="qpid" class="gui-input" >
                                            <form:option value="">--Select--</form:option>

                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="batches.id" text="Batch ID" /></label>
                                    <label class="field prepend-icon">
                                        <form:input type="number" path="batch_id" id="batch_id" class="gui-input" placeholder="Search Batch ID"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label">&nbsp;</label>
                                    <label class="field prepend-icon">

                                        <button type="submit" class="button btn-blue" ><spring:message code="common.button.search" text="Search" /></button>
                                        <button type="button" class="button btn-red" onclick="importusers();">Import Users</button>

                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->

                        <div class="frm-row" id="displaytabledata">
                            <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">

                            </table>
                        </div>
                    </div>
                </div>
            </form:form>


        </div><!-- end .smart-forms section -->


        <script>

            $("#ssc_id").change(function () {

                var sscid = $(this).val();
                if (sscid) {

                    $.ajax({
                        url: "getQP.io",
                        data: {ssc_id: sscid},
                        type: 'GET',
                        success: function (data) {


                            var str = "<option value=''>------- Select --------</option>";
                            if (data.length === 0) {
                                str = "<option value=''>------- Select --------</option>";
                            }
                            $.each(data, function (index, jsonObject) {
                                //$.each(jsonObject, function (key, val) {
                                //alert("key : " + jsonObject.ID + " ; value : " + jsonObject.NAME);
                                str = str + "<option value=" + jsonObject.ID + ">" + jsonObject.NAME + "</option>";
                                // });
                            });
                            $("#qpid").html(str);
                        }
                    });
                } else {
                    $("#qpid").html("<option value=''>------- Select --------</option>");
                }
            });

            $("#qpid").change(function () {

                var qpid = $(this).val();
                if (qpid) {

                    $.ajax({
                        url: "getbatches.io",
                        data: {qpid: qpid},
                        type: 'GET',
                        success: function (data) {
                            //alert(data);
                            $("#example-basic4").empty();
                            $('#example-basic4').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center">Batch ID</th><th align="center">Batch Size</th><th align="center">State</th><th align="center">Center Address</th>' +
                                    '<th align="center">Assessment Start Date</th><th align="center">Assessment End Date</th><th align="center">Training Partner Name</th><th align="center">Edit</th><th align="center">Users</th><th align="center">Assessors</th><th align="center">Question Paper</th><th align="center">Result</th></tr></thead >');

                            for (var i = 0, lennos = data.length; i < lennos; i++) {
                                var assessor=data[i].assessorId;
                                
                                $('#example-basic4 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center">' + data[i].batch_id + '</td><td align="center">' + data[i].batch_size + '</td><td align="center">' + data[i].state + '</td><td align="center">' + data[i].centerAddress + '</td><td align="center">' + data[i].assessmentStartDate + '</td><td align="center">' + data[i].assessmentEndDate + '</td><td align="center">' + data[i].tpName + '</td><td align="center"><button type="button" class="button btn-blue" onclick="callEdit(' + data[i].ID + ');">Edit</button></td><td align="center"><button type="button" class="button btn-blue" onclick="callUser(' + data[i].ID + ');">User</button></td><td align="center"><button type="button" class="button btn-blue" onclick="callAssessor(' + data[i].ID + ');">' + assessor + '</button></td><td align="center"><button type="button" class="button btn-blue" onclick="callQuestionPaper(' + data[i].ID + ');">Question Paper</button></td><td align="center"><button type="button" class="button btn-blue" onclick="callResult(' + data[i].ID + ');">Result</button><button type="button" class="button btn-blue" onclick="callResultNOS(' + data[i].ID + ');">NOS Result</button></td></tr>');


                            }
                            //                            $("#example-basic4").empty();



                        }
                    });
                } else {
                    $("#example-basic4").empty();
                    //$("#qpid").html("<option value=''>------- Select --------</option>");
                }
            });

            function callEdit(id) {
                //alert(id);
                window.location.href = "<%=request.getContextPath()%>/admin/batches/initupdate.io?recid=" + id;

            }
            function callUser(id) {
                //alert(id);
                var sscidval = $('#ssc_id').val();
                var qidval = $('#qpid').val();
                var sscid = $("#ssc_id option:selected").text();
                var qid = $("#qpid option:selected").text();
                //alert(sscid);
                if (sscidval) {
                    if (qidval) {
                        if (id) {
                            window.location.href = "<%=request.getContextPath()%>/admin/users/showuser.io?batchid=" + id + "&sscid=" + sscid + "&qid=" + qid;
                        }

                    } else {
                        alert("Please select Qualification Packs");
                    }
                } else {
                    alert("Please select Sector Skill Council");
                }

            }
            function callAssessor(id) {
                //alert(id);
                var sscid = $("#ssc_id option:selected").text();
                var qid = $("#qpid option:selected").text();
                //var w = window.open("<%=request.getContextPath()%>/admin/batches/addassessor.io?batchid=" +id, "popupWindow", "width=1024, height=500, scrollbars=yes");
                window.location.href = "<%=request.getContextPath()%>/admin/batches/addassessor.io?batchid=" + id + "&sscid=" + sscid + "&qid=" + qid;
            }
            function callQuestionPaper(id) {
                alert(id);

            }
            function callResult(id) {
                alert(id);

            }
            function callResultNOS(id) {
                alert(id);

            }

            $(document).ready(function () {
                $("#addbatch").click(function () {
                    var sscidval = $('#ssc_id').val();
                    var qid = $('#qpid').val();
                    //alert("Yes" + qid);
                    if (sscidval) {
                        if (qid) {

                            window.location.href = "<%=request.getContextPath()%>/admin/batches/initadd.io?recid=" + qid;


                        } else {
                            alert("Please select Qualification Packs");
                        }
                    } else {
                        alert("Please select Sector Skill Council");
                    }



                });
            });
            function importusers() {
                var sscidval = $('#ssc_id').val();
                var qidval = $('#qpid').val();
                var sscid = $("#ssc_id option:selected").text();
                var qid = $("#qpid option:selected").text();
                //alert(sscid);
                if (sscidval) {
                    if (qidval) {
                        window.location.href = "<%=request.getContextPath()%>/admin/users/initimport.io?sscid=" + sscid + "&qid=" + qid;
                    } else {
                        alert("Please select Qualification Packs");
                    }
                } else {
                    alert("Please select Sector Skill Council");
                }


            }
        </script>
    </body>

</html>