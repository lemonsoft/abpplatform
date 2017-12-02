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
                    <h4><i class="fa fa-pencil-square"></i> Generate Question Paper</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->
                <form:form method="post" action="${action}" commandName="generateqp" > 
                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm6"> 
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
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="qualificationpack.title" text="Qualification Packs" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="qpackid" id="qpackid" class="gui-input" >
                                            <form:option value="0">--Select--</form:option>

                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->


                        </div><!-- end frm-row section -->


                        <div class="frm-row" id="displaytabledata">

                            <span id="title1"><h5>Multi Tagged Questions</h5></span>
                            <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">

                            </table>
                            <span id="title2"><h5>PCs</h5></span>
                            <table id="example-basic5" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">

                            </table>
                        </div>


                    </div>
                </form:form>







            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->




        <script type="text/javascript">
            $(document).ready(function () {
                $("#title1").hide();
                $("#title2").hide();

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
                                $("#qpackid").html(str);
                            }
                        });
                    } else {
                        $("#qpackid").html("<option value=''>------- Select --------</option>");

                        $("#example-basic4").empty();
                    }
                });



                $("#qpackid").change(function () {

                    var qpackid = $(this).val();
                    if (qpackid) {

                        $.ajax({
                            url: "getAllPCByQPID.io",
                            data: {qpackid: qpackid},
                            type: 'GET',
                            success: function (data) {
                                
                                
                                var totlen=data.length-1;
                                
                                $("#example-basic4").empty();
                                $("#title1").show();
                                $('#example-basic4').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center"></th><th align="center">Question ID</th><th align="center">Question Title</th><th align="center">Option 1</th><th align="center">Option 2</th><th align="center">Option 3</th><th align="center">Option 4</th><th align="center">Marks</th><th align="center">PC wise Added Marks</th></tr></thead >');
                                for (var i = 0, lennos = data[0].length; i < lennos; i++) {
                                   
                                    $('#example-basic4 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center"><input type="checkbox" name="option" id="questionid" value="' + data[0][i].ID + '"/></td><td align="center">' + data[0][i].ID + '</td><td align="center">' + data[0][i].question_title + '</td><td align="center">' + data[0][i].option1 + '</td><td align="center">' + data[0][i].option2 + '</td><td align="center">' + data[0][i].option3 + '</td><td align="center">' + data[0][i].option4 + '</td><td align="center">' + data[0][i].marks + '</td><td align="center">' + data[0][i].pcwithmarks + '</td></tr>');
                                }
                                
                                $("#example-basic5").empty();
                                $("#title2").show();
                                $('#example-basic5').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center">Sr.#</th><th align="center">PCID</th><th align="center">Name</th><th align="center">Marks</th><th align="center">Action</th></tr></thead >');
                                 for (var i = 0, lennos = data[1].length; i < lennos; i++) {
                                   
                                    $('#example-basic5 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center">' + i + '</td><td align="center">' + data[1][i].PCID + '</td><td align="center">' + data[1][i].PCNAME + '</td><td align="center">' + data[1][i].Theorymarks + '</td><td align="center"><a href="#" onclick="showpcquestionmap(' + data[1][i].ID + ');">EDIT</a></td></tr>');
                                }
                                
                                $('#example-basic5 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center">&nbsp;</td><td align="center">&nbsp;</td><td align="center">Total QualityPack Marks :' + data[totlen].totaltheorymarksqp + '</td><td align="center">&nbsp;</td><td align="center">&nbsp;</td></tr>');
                            }
                        });
                    } else {
                        $("#example-basic4").empty();
                        $("#example-basic5").empty();

                    }
                });
            });






            function divFunction() {
                var qpid = $('select[name=qpid]').val();
                //alert("Test code" + qpid);
                var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/openaddnos.io?qpid=" + qpid, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            function openpcdialog(id) {
                //var qpid = $('select[name=qpid]').val();
                //alert("Test code" + id);
                var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/openaddpc.io?nosid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            function openeditqp(id) {
                //var qpid = $('select[name=qpid]').val();
                //alert("Test code" + id);
                var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/initupdateqp.io?recid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            function openeditnos(id) {
                //var qpid = $('select[name=qpid]').val();
                //alert("Test code" + id);
                var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/initUpdatenos.io?recid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            function openeditpc(id) {
                //var qpid = $('select[name=qpid]').val();
                //alert("Test code" + id);
                var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/initUpdatepc.io?recid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
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
            function exportexcel() {
                var qpackid = $("#qpackid").val();
                $.ajax({
                    url: "exportexcel.io",
                    data: {qpid: qpackid},
                    type: 'GET',
                    success: function () {

                    }
                });

            }
            $('#expbtn').on('click', function (event) {
                var qpackid = $("#qpackid").val();
                //alert(qpackid);
                window.open("<%=request.getContextPath()%>/admin/questions/exportexcel.io?qpid=" + qpackid, '_blank');
//                $.ajax({
//                    url: "exportexcel.io",
//                    data: {qpid: qpackid},
//                    type: 'GET',
//                    success: function () {
//
//                    }
//                });
            });

            function importQuestions() {

                window.location.href = "<%=request.getContextPath()%>/admin/questions/importQuestions.io";



            }
            function importmultilanguage() {

                window.location.href = "<%=request.getContextPath()%>/admin/questions/importMultiLanguage.io";



            }
            function showpcquestionmap(id) {
                event.preventDefault();
                var w = window.open("<%=request.getContextPath()%>/admin/generateqp/pcwisequestion.io?pcid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
                
            }

        </script>
    </body>

</html>