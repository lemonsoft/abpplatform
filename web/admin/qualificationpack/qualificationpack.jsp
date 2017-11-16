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
                    <h4><i class="fa fa-pencil-square"></i> <spring:message code="qualificationpack.title" text="Qualification Packs" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->
                <form:form method="post" action="${action}"   commandName="qualificationpack" >
                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="ssc.title" text="Sector Skill Council" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="ssc_id" name="ssc_id" id="ssc_id" class="gui-input" >

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
                                    <label for="names" class="field-label">&nbsp;</label>
                                    <label class="field prepend-icon">

                                        <a class=" btn yellow btn-outline sbold" href="<%=request.getContextPath()%>/admin/qualificationpack/openaddqp.io" id="OpenDialog" data-target="#ajax2" data-toggle="modal"> <spring:message code="qualificationpack.addnew" text="Add New Qualification Packs" /> </a>


                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->
                        <div class="frm-row" id="displaytabledata">
                            <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">

                                <%--<thead >
                                    <tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" >
                                        <th>Qualification Pack ID</th>
                                        <th>Qualification Pack Name</th>
                                        <th>Qualification Pack Level</th>
                                        <th>Total Marks</th>
                                        <th>Total Theory Marks</th>
                                        <th>Total Practical Marks</th>
                                        <th>Theory Cutoff</th>
                                        <th>Practical Cutoff</th>
                                        <th>Overall Cutoff</th>
                                        <th>Weighted Average (%)</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <tr data-tt-id="1.1" data-tt-parent-id="1">
                                        <td colspan="10">
                                            <table id="example-basic1">
                                                <caption>Basic jQuery treetable Example</caption>
                                                <thead>
                                                    <tr style="height: 50px;font-size:12px;color: #FFF;">
                                                        <th>Tree column</th>
                                                        <th>Additional data</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr data-tt-id="1">
                                                        <td>Node 1: Click on the icon in front of me to expand this branch.</td>
                                                        <td>I live in the second column.</td>
                                                    </tr>
                                                    <tr data-tt-id="1.1" data-tt-parent-id="1">
                                                        <td>Node 1.1: Look, I am a table row <em>and</em> I am part of a tree!</td>
                                                        <td>Interesting.</td>
                                                    </tr>
                                                    <tr data-tt-id="1.1.1" data-tt-parent-id="1.1">
                                                        <td>Node 1.1.1: I am part of the tree too!</td>
                                                        <td>That's it!</td>
                                                    </tr>
                                                    <tr data-tt-id="2">
                                                        <td>Node 2: I am another root node, but without children</td>
                                                        <td>Hurray!</td>
                                                    </tr>
                                                    <tr data-tt-id="2.1" data-tt-parent-id="2">
                                                        <td>Node 2.1: Look, I am a table row <em>and</em> I am part of a tree!</td>
                                                        <td>Interesting.</td>
                                                    </tr>
                                                    <tr data-tt-id="2.1.1" data-tt-parent-id="2.1">
                                                        <td>Node 2.1.1: I am part of the tree too!</td>
                                                        <td>That's it!</td>
                                                    </tr>
                                                </tbody>
                                            </table>

                                        </td>

                                    </tr>--%>


                            </table>
                        </div>
                    </div>
                </form:form>



                <div class="modal fade" id="ajax" role="basic" aria-hidden="true" style="position:absolute;left:200px; width:900px;height:600px; ">
                    <div class="modal-dialog modal-full">
                        <div class="modal-content">


                            <div class="modal-body">
                                <img src="<%=request.getContextPath()%>/assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
                                <span> &nbsp;&nbsp;Loading... </span>
                            </div>



                        </div>
                    </div>
                </div>



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
                            url: "getQPcakDetails.io",
                            data: {qp_id: qpid},
                            type: 'GET',
                            success: function (data) {

                                //alert(data[1]);
                                //alert("key : " + data[0].QpackID + " ; value : " + data[0].QpackName);


                                // Iterate through items.
                                //for (var i = 0, len = data[2].length; i < len; i++) {
                                //   alert(data[2][i].PCID);
                                //}

                                //$('#displaytabledata').html('');
                                //var tbl = $("<table></table>").attr("id", "example-basic");
                                //$("#displaytabledata").append(tbl);



                                $("#example-basic4").empty();
                                $('#example-basic4').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center">Qualification Pack ID</th><th align="center">Qualification Pack Name</th><th align="center">Qualification Pack Level</th><th align="center">Total Marks</th>' +
                                        '<th align="center">Total Theory Marks</th><th align="center">Total Practical Marks</th><th align="center">Theory Cutoff</th><th align="center">Practical Cutoff</th><th align="center">Overall Cutoff</th><th align="center">Weighted Average (%)</th><th align="center">Actions</th></tr></thead >');
                                $('#example-basic4 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center">' + data[0].QpackID + '</td><td align="center">' + data[0].QpackName + '</td><td align="center">' + data[0].QpackLevel + '</td><td align="center">' + data[0].Totaltheorymarks + '</td><td align="center">' + data[0].Totalpracticalmarks + '</td><td align="center">' + data[0].Totalmarks + '</td><td align="center">' + data[0].Theorycutoffmarks + '</td><td align="center">' + data[0].Practicalcutoffmarks + '</td><td align="center">' + data[0].Overallcutoffmarks + '</td><td align="center">' + data[0].Weightedavgmarks + '</td>'
                                        + '<td><a href="initupdateqp.io?recid=' + data[0].ID + '" id="openeditqp" data-target="#ajax3" data-toggle="modal" onclick="openeditqp(' + data[0].ID + ');">Edit</a>|<a href="deleteqp.io?recid=' + data[0].ID + '" data-target="#ajax2" data-toggle="modal" onclick="opendeleteqp(' + data[0].ID + ');">Delete</a></td></tr>');


                                $('#example-basic4 tr:last').after('<tr><td colspan="11" align="right"><button type="button" id="OpenNOSDialog" onClick="divFunction();" class="button btn-blue">Add New NOS</button></td></tr>');
//                               $('#example-basic4 tr:last').after('<tr style="height: 50px;font-size:12px;"><td colspan="11"><table id="nos" border="1" cellpadding="2" cellspacing="2" align="center" width="90%"><thead><tr ><th align="center">Sr.#</th><th align="center">NOS ID</th><th align="center">NOS </th><th align="center">Theory Cutoff</th><th align="center">Practical Cutoff</th><th align="center">Practical Cutoff</th><th align="center">Weighted Average (%)</th><th align="center">Actions</th></tr></thead></table></td></tr>');
//                                 $('#nos tr:last').after('<tr ><td>1</td><td>DWC/N0105</td><td>Carry out basic domestic cooking services for the house</td><td>0.00</td><td>0.00</td><td>0.00</td><td>0</td><td><a href="#">Edit</a>|<a href="#">Delete</a></td></tr>');
//                                $('#nos tr:last').after('<tr><td colspan="11" align="right"><button type="button" class="button btn-blue">Add New PC</button></td></tr>');
//                                $('#nos tr:last').after('<tr style="height: 50px;font-size:12px;"><td colspan="11"><table id="pc" border="1" cellpadding="2" cellspacing="2" align="center" width="90%"><thead><tr ><th align="center">Sr.#</th><th align="center">PC ID</th><th align="center">PC Name</th><th align="center">Maximum Marks</th><th align="center">Theory Marks</th><th align="center">Practical Marks</th><th align="center">Actions</th></tr></thead></table></td></tr>');
//                                $('#pc tr:last').after('<tr ><td>1</td><td>DWC/N0105</td><td>Carry out basic domestic cooking services for the house</td><td>0.00</td><td>0.00</td><td>0</td><td><a href="#">Edit</a>|<a href="#">Delete</a></td></tr>');
//
//
//
//                                $('#example-basic4').append('<tr style="height: 50px;font-size:12px;"><td colspan="11"><table id="nos2" border="1" cellpadding="2" cellspacing="2" align="center" width="90%"><thead><tr ><th align="center">Sr.#</th><th align="center">NOS ID</th><th align="center">NOS </th><th align="center">Theory Cutoff</th><th align="center">Practical Cutoff</th><th align="center">Practical Cutoff</th><th align="center">Weighted Average (%)</th><th align="center">Actions</th></tr></thead></table></td></tr>');
//                                $('#nos2 tr:last').after('<tr ><td>2</td><td>DWC/N0105</td><td>Carry out basic domestic cooking services for the house</td><td>0.00</td><td>0.00</td><td>0.00</td><td>0</td><td><a href="#">Edit</a>|<a href="#">Delete</a></td></tr>');
//                                $('#nos2 tr:last').after('<tr><td colspan="11" align="right"><button type="button" class="button btn-blue">Add New PC</button></td></tr>');
//                                $('#nos2 tr:last').after('<tr style="height: 50px;font-size:12px;"><td colspan="11"><table id="pc2" border="1" cellpadding="2" cellspacing="2" align="center" width="90%"><thead><tr ><th align="center">Sr.#</th><th align="center">PC ID</th><th align="center">PC Name</th><th align="center">Maximum Marks</th><th align="center">Theory Marks</th><th align="center">Practical Marks</th><th align="center">Actions</th></tr></thead></table></td></tr>');
//
//                                $('#pc2 tr:last').after('<tr ><td>1</td><td>DWC/N0105</td><td>Carry out basic domestic cooking services for the house</td><td>0.00</td><td>0.00</td><td>0</td><td><a href="#">Edit</a>|<a href="#">Delete</a></td></tr>');
                                //alert(data[1].length);
                                for (var i = 0, len = data[1].length; i < len; i++) {
                                    alert(data[1][i].NOSNAME);

                                    $('#example-basic4').append('<tr style="height: 50px;font-size:12px;"><td colspan="11"><table id="nos' + i + '" border="1" cellpadding="2" cellspacing="2" align="center" width="90%"><thead><tr ><th align="center">Sr.#</th><th align="center">NOS ID</th><th align="center">NOS </th><th align="center">Theory Cutoff</th><th align="center">Practical Cutoff</th><th align="center">Practical Cutoff</th><th align="center">Weighted Average (%)</th><th align="center">Actions</th></tr></thead></table></td></tr>');
                                    $('#nos' + i + '').append('<tr ><td>' + i + '</td><td>' + data[1][i].NOSID + '</td><td>' + data[1][i].NOSNAME + '</td><td>' + data[1][i].Theorycutoffmarks + '</td><td>' + data[1][i].Practicalcutoffmarks + '</td><td>' + data[1][i].Overallcutoffmarks + '</td><td>' + data[1][i].Weightedavgmarks + '</td><td><a href="#">Edit</a>|<a href="#">Delete</a></td></tr>');
                                    $('#nos' + i + ' tr:last').after('<tr><td colspan="11" align="right"><button type="button" class="button btn-blue" onClick="openpcdialog(' + data[1][i].ID + ');">Add New PC</button></td></tr>');

                                    $('#nos' + i + ' tr:last').after('<tr style="height: 50px;font-size:12px;"><td colspan="11"><table id="pc' + i + '" border="1" cellpadding="2" cellspacing="2" align="center" width="90%"><thead><tr ><th align="center">Sr.#</th><th align="center">PC ID</th><th align="center">PC Name</th><th align="center">Maximum Marks</th><th align="center">Theory Marks</th><th align="center">Practical Marks</th><th align="center">Actions</th></tr></thead></table></td></tr>');

                                    var totalmax = 0;
                                    var totaltheory = 0;
                                    var totalpractical = 0;
                                    var index = 1;
                                    for (var j = 0, len = data[2].length; j < len; j++) {
                                        //alert(data[1][i].ID + "=====" + data[2][j].NOSID);
                                        var val1 = parseInt(data[1][i].ID);
                                        var val2 = parseInt(data[2][j].NOSID);

                                        if (val1 === val2) {
                                            //alert(data[2][j].PCID);

                                            totalmax = totalmax + parseInt(data[2][j].Theorycutoffmarks);
                                            //alert(parseInt(data[2][j].Theorycutoffmarks) + "=====" + totalmax);
                                            totaltheory = totaltheory + parseInt(data[2][j].Practicalcutoffmarks);
                                            totalpractical = totalpractical + parseInt(data[2][j].Overallcutoffmarks);
                                            $('#pc' + i + ' tr:last').after('<tr ><td>' + index + '</td><td>' + data[2][j].PCID + '</td><td>' + data[2][j].PCNAME + '</td><td>' + data[2][j].Theorycutoffmarks + '</td><td>' + data[2][j].Practicalcutoffmarks + '</td><td>' + data[2][j].Overallcutoffmarks + '</td><td><a href="#">Edit</a>|<a href="#">Delete</a></td></tr>');
                                            index++;
                                        }
                                    }
                                    $('#pc' + i + ' tr:last').after('<tr ><td>&nbsp;</td><td>&nbsp;</td><td>Total</td><td>' + totalmax + '</td><td>' + totaltheory + '</td><td>' + totalpractical + '</td><td>&nbsp;</td></tr>');
                                    //$('#nos' + i + ' tr:last').after('<tr><td colspan="11" align="right"><button type="button" class="button btn-blue" onClick="openpcdialog(' + data[1][i].ID + ');">Add New PC</button></td></tr>');
                                    //$('#nos' + i + ' tr:last').after('<tr style="height: 50px;font-size:12px;"><td colspan="11"><table id="pc" border="1" cellpadding="2" cellspacing="2" align="center" width="90%"><thead><tr ><th align="center">Sr.#</th><th align="center">PC ID</th><th align="center">PC Name</th><th align="center">Maximum Marks</th><th align="center">Theory Marks</th><th align="center">Practical Marks</th><th align="center">Actions</th></tr></thead></table></td></tr>');
                                    //$('#pc tr:last').after('<tr ><td>1</td><td>DWC/N0105</td><td>Carry out basic domestic cooking services for the house</td><td>0.00</td><td>0.00</td><td>0</td><td><a href="#">Edit</a>|<a href="#">Delete</a></td></tr>');
                                }


                                //$("#qpid").html(data);
                            }
                        });
                    } else {
                        $("#qpid").html("<option value=''>------- Select --------</option>");
                    }
                });
            });
//            $("#openeditqp").click(function () {
//                //var ssc_id = $('select[name=ssc_id]').val();
//                alert("Test EDIt Dialog");
//                //var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/openaddqp.io?sscid=" + ssc_id, "popupWindow", "width=1024, height=500, scrollbars=yes");
//                //var $w = $(w.document.body);
//                //$w.html("<textarea></textarea>");
//            });
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
                //var qpid = $('select[name=qpid]').val();
                //alert("Test code" + id);
                var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/openaddpc.io?nosid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            function openeditqp(id) {
                //var qpid = $('select[name=qpid]').val();
                alert("Test code" + id);
                var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/initupdateqp.io?recid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            function opendeleteqp(id) {
                //var qpid = $('select[name=qpid]').val();
                var r = confirm("Are you sure you want to delete!\nAll the NOS,PC,Batches, Theory and Practical questions under this QualityPack will be deleted permenantly. .");
                var txt;
                if (r === true) {
                    txt = "You pressed OK!";
                } else {
                    txt = "You pressed Cancel!";
                }
                alert(txt);
                //var w = window.open("<%=request.getContextPath()%>/admin/qualificationpack/openaddpc.io?nosid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
        </script>
    </body>

</html>