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

        <script type='text/javascript'>//<![CDATA[ 
            $(window).load(function () {
                $("#example-basic").treetable({expandable: true});

                /* 
                 Trying to get this function to happen when 
                 "onNodeCollapse" event happens
                 */
                $("#example-basic").treetable({
                    expandable: true,
                    onNodeCollapse: function () {
                        alert("A branch has collapsed");
                    }
                });
                $("#example-basic1").treetable({expandable: true});

                /* 
                 Trying to get this function to happen when 
                 "onNodeCollapse" event happens
                 */
                $("#example-basic1").treetable({
                    expandable: true,
                    onNodeCollapse: function () {
                        alert("A branch has collapsed");
                    }
                });
            });//]]>  
        </script>
    </head>
    <body>
        <div class="smart-wrap">
            <div class="smart-forms smart-container wrap-full">

                <div class="form-header header-blue">
                    <h4><i class="fa fa-pencil-square"></i> Result </h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->
                <form:form method="post" action="${action}"   commandName="result" >
                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm4"> 
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
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="qualificationpack.title" text="Qualification Packs" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="qpid" id="qpid" class="gui-input" >
                                            <form:option value="0">--Select--</form:option>

                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label">Select Batch</label>
                                    <label class="field prepend-icon">
                                        <form:select path="batchid" id="batchid" class="gui-input" >
                                            <form:option value="0">--Select--</form:option>

                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <div class="frm-row" id="displaytabledata">
                            <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">




                            </table>
                        </div>
                    </div>
                </form:form>







            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->




        <script type="text/javascript">
            $(document).ready(function () {
                $("#OpenDialog").click(function () {
                    var ssc_id = $('select[name=ssc_id]').val();
                    //alert("Test code"+ssc_id);
                    var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/openaddqp.io?sscid=" + ssc_id, "popupWindow", "width=1024, height=500, scrollbars=yes");
                    //var $w = $(w.document.body);
                    //$w.html("<textarea></textarea>");
                });
                $("#OpenNOSDialog").click(function () {
                    var qpid = $('select[name=qpid]').val();
                    alert("Test code" + qpid);
                    //var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/openaddnos.io?qpid=" + qpid, "popupWindow", "width=1024, height=500, scrollbars=yes");
                    //var $w = $(w.document.body);
                    //$w.html("<textarea></textarea>");
                });

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
                            url: "getBatchDetails.io",
                            data: {qp_id: qpid},
                            type: 'GET',
                            success: function (data) {
                                var str = "<option value=''>------- Select --------</option>";
                                if (data.length === 0) {
                                    str = "<option value=''>------- Select --------</option>";
                                }
                                $.each(data, function (index, jsonObject) {

                                    str = str + "<option value=" + jsonObject.ID + ">" + jsonObject.NAME + "</option>";

                                });
                                $("#batchid").html(str);


                            }
                        });
                    } else {
                        $("#batchid").html("<option value=''>------- Select --------</option>");

                    }
                });
            });
            $("#batchid").change(function () {

                var batchid = $(this).val();
                var qpackid =  $("#qpid").val();
                if (batchid) {

                    $.ajax({
                        url: "getResultDetails.io",
                        data: {batchid: batchid,qpackid: qpackid},
                        type: 'GET',
                        success: function (data) {
                            
                            $("#example-basic4").empty();
                            $('#example-basic4').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center">Photo</th><th align="center">Enrollment</th><th align="center">Name</th><th align="center">Max Theory</th>' +
                                    '<th align="center">Theory Cutoff</th><th align="center">Scored Theory Marks</th><th align="center">Max Practical</th><th align="center">Practical Cutoff</th><th align="center">Scored Practical</th><th align="center">Overall Cutoff</th><th align="center">Scored Total</th><th align="center">Scored Weighted Avg</th><th align="center">NOS Report</th><th align="center">PC Report</th><th align="center">Proctoring Video</th><th align="center">Question Wise Report</th></tr></thead >');
                            
                            for (var i = 0, lennos = data.length; i < lennos; i++) {
                              var id=data[i].ID;
                            $('#example-basic4 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td>Photo</td><td align="center">' + data[i].enrollmentno + '</td><td align="center">' + data[i].name + '</td><td align="center">' + data[i].maxtheory + '</td><td align="center">' + data[i].theorycuttoff + '</td><td align="center">' + data[i].scoredtheorymarks + '</td><td align="center">' + data[i].maxpractical + '</td><td align="center">' + data[i].practicalcuttoff + '</td><td align="center">' + data[i].scoredpracticalmarks + '</td><td align="center">' + data[i].overallcutoff + '</td><td align="center">' + data[i].scoredtotal + '</td><td align="center">' + data[i].scoredweightedavg + '</td>'
                                    + '<td><button type="button" class="button btn-blue" onClick="opendisplaynos(' + data[i].ID + ');">NOS</button></td><td><button type="button" class="button btn-blue" onClick="opendisplaypc(' + data[i].ID + ');">PC</button></td><td><button type="button" class="button btn-blue" onClick="openpcdialog(' + data[i].ID + ');">NO VIDEO</button></td><td><button type="button" id="openeditqp" class="button btn-blue" onclick="openreportdialog('+id+');">REPORT</button></td></tr>');
                            
                            }
                           
                        }
                    });
                } else {
                    $("#example-basic4").empty();
                    
                }
            });

//            
            $('#openeditqp').on('click', function (event) {
                event.preventDefault();
                alert("Test EDIt Dialog");
            });
            function divFunction() {
                var qpid = $('select[name=qpid]').val();
                //alert("Test code" + qpid);
                var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/openaddnos.io?qpid=" + qpid, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            function openpcdialog(id) {
                var qpid = $('select[name=qpid]').val();
                //alert("Test code" + qpid);
                var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/openaddpc.io?nosid=" + id + "&qpid=" + qpid, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            function opendisplaypc(id,id2) {
               
                var w = window.open("<%=request.getContextPath()%>/admin/result/pcreport.io?userresultid=" + id+"&userid="+id2, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            function opendisplaynos(id,id2) {
                
                var w = window.open("<%=request.getContextPath()%>/admin/result/nosreport.io?userresultid=" + id+"&userid="+id2, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            function openreportdialog(id,id2) {
                //var qpid = $('select[name=qpid]').val();
                alert("Test code" + id);
                alert("Test code" + id2);
                var w = window.open("<%=request.getContextPath()%>/admin/result/totalreport.io?userresultid=" + id+"&userid="+id2, "popupWindow", "width=1200, height=500, scrollbars=yes");
            }
            function opendeletenos(id) {
                //var qpid = $('select[name=qpid]').val();
                // alert("Test code" + id);
                var r = confirm("Are you sure you want to delete!\nAll PC,Batches, Theory and Practical questions under this QualityPack will be deleted permenantly. .");
                var txt;
                if (r === true) {
                    txt = "You pressed OK!";
                    $.ajax({
                        url: "deletenos.io",
                        data: {nosid: id},
                        type: 'GET',
                        success: function (data) {

                            //alert(data);
                            $('#qpid').trigger("change");

                        }
                    });
                } else {
                    txt = "You pressed Cancel!";
                }
            }
            function opendeletepc(id) {
                //var qpid = $('select[name=qpid]').val();
                //alert("Test code" + id);
                var r = confirm("Are you sure you want to delete!\n PC,Batches, Theory and Practical questions under this QualityPack will be deleted permenantly. .");
                var txt;
                if (r === true) {
                    txt = "You pressed OK!";
                    $.ajax({
                        url: "deletepc.io",
                        data: {pcid: id},
                        type: 'GET',
                        success: function (data) {

                            //alert(data);
                            $('#qpid').trigger("change");

                        }
                    });
                } else {
                    txt = "You pressed Cancel!";
                }
            }
            function opendeleteqp(id) {
                //var qpid = $('select[name=qpid]').val();
                var r = confirm("Are you sure you want to delete!\nAll the NOS,PC,Batches, Theory and Practical questions under this QualityPack will be deleted permenantly. .");
                var txt;
                if (r === true) {
                    txt = "You pressed OK!";
                    $.ajax({
                        url: "deleteqp.io",
                        data: {qpid: id},
                        type: 'GET',
                        success: function (data) {

                            //alert(data);
                            window.location.reload();
                            //$('#qpid').trigger("change");
                        }
                    });
                } else {
                    txt = "You pressed Cancel!";
                }
                //alert(txt);
                //var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/openaddpc.io?nosid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            function refresh() {
                $('#qpid').trigger("change");
            }
            function refreshqp() {
                $('#ssc_id').trigger("change");
            }

        </script>
    </body>

</html>