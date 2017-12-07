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
                    <h4><i class="fa fa-pencil-square"></i>Questions Usage</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->
                <form:form method="post" action="${action}"   commandName="questions" > 
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
                        <div class="frm-row">
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label">Select NOS</label>
                                    <label class="field prepend-icon">
                                        <form:select path="nosid" id="nosid" class="gui-input" >
                                            <form:option value="0">--Select--</form:option>

                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label">Select PC</label>
                                    <label class="field prepend-icon">
                                        <form:select path="pcid" id="pcid" class="gui-input" >
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
                $("#addbtn").hide();
                $("#expbtn").hide();
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
                                $("#qpackid").html(str);
                            }
                        });
                    } else {
                        $("#qpackid").html("<option value=''>------- Select --------</option>");
                        $("#nosid").html("<option value=''>------- Select --------</option>");
                        $("#pcid").html("<option value=''>------- Select --------</option>");
                        $("#addbtn").hide();
                        $("#expbtn").hide();
                        $("#example-basic4").empty();
                    }
                });
                $("#qpackid").change(function () {

                    var qpackid = $(this).val();
                    if (qpackid) {

                        $.ajax({
                            url: "getnos.io",
                            data: {qpid: qpackid},
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
                                $("#nosid").html(str);
                            }
                        });
                    } else {
                        $("#nosid").html("<option value=''>------- Select --------</option>");
                        $("#pcid").html("<option value=''>------- Select --------</option>");
                        $("#addbtn").hide();
                        $("#expbtn").hide();
                        $("#example-basic4").empty();
                    }
                });
                $("#nosid").change(function () {

                    var nosid = $(this).val();
                    if (nosid) {

                        $.ajax({
                            url: "getpc.io",
                            data: {nosid: nosid},
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
                                $("#pcid").html(str);
                            }
                        });
                    } else {
                        $("#pcid").html("<option value=''>------- Select --------</option>");
                        $("#addbtn").hide();
                        $("#expbtn").hide();
                    }
                });

                $("#qpackid").change(function () {

                    var qpackid = $(this).val();
                    if (qpackid) {

                        $.ajax({
                            url: "getQuestions.io",
                            data: {qp_id: qpackid},
                            type: 'GET',
                            success: function (data) {

                                $("#example-basic4").empty();
                                $('#example-basic4').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center">Question ID</th><th align="center">Question Title</th><th align="center">Option 1</th><th align="center">Option 2</th>' +
                                        '<th align="center">Option 3</th><th align="center">Option 4</th><th align="center">Question Appeared</th><th align="center">Accurately Answered</th><th align="center">Is Phase Out</th></tr></thead >');
                                //alert(data.length);
                                for (var i = 0, lennos = data.length; i < lennos; i++) {
                                    //alert(window.location.host);
                                    var actionicon = "";
                                    if (data[i].isphaseout == 'Y') {
                                        actionicon = "<a href=\"#\" onclick=\"initChange(" + data[i].ID + ",'" + data[i].isphaseout + "');\" \"><img src=\"http://localhost:8085/ABP-Ver1/assets/images/Tick-Box.png\" width=20 height=20/></a>";
                                    } else {
                                        actionicon = "<a href=\"#\" onclick=\"initChange(" + data[i].ID + ",'" + data[i].isphaseout + "');\" \"><img src=\"http://localhost:8085/ABP-Ver1/assets/images/wrong.png\" width=20 height=20/></a>";
                                    }

                                    $('#example-basic4 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center">' + data[i].ID + '</td><td align="center">' + data[i].question_title + '&nbsp<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].questionimgurl + ' width="50" height="50"/></td><td align="center">' + data[i].option1 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl1 + ' width="50" height="50"/></td><td align="center">' + data[i].option2 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl2 + ' width="50" height="50"/></td><td align="center">' + data[i].option3 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl3 + ' width="50" height="50"/></td><td align="center">' + data[i].option4 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl4 + ' width="50" height="50"/></td><td align="center">' + data[i].appeared + '</td><td align="center">' + data[i].answered + '</td><td align="center">' + actionicon + '</td></tr>');


                                }

                                $("#expbtn").show();
                            }


                            //$("#qpid").html(data);

                        });
                    } else {
                        $("#example-basic4").empty();
                        //$("#qpid").html("<option value=''>------- Select --------</option>");
                    }
                });
            });
            $("#nosid").change(function () {

                var qpackid = $("#qpackid").val();
                var nosid = $(this).val();
                if (nosid) {

                    $.ajax({
                        url: "getQuestionsbynos.io",
                        data: {qp_id: qpackid, nosid: nosid},
                        type: 'GET',
                        success: function (data) {

                            $("#example-basic4").empty();
                            $('#example-basic4').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center">Question ID</th><th align="center">Question Title</th><th align="center">Option 1</th><th align="center">Option 2</th>' +
                                    '<th align="center">Option 3</th><th align="center">Option 4</th><th align="center">Question Appeared</th><th align="center">Accurately Answered</th><th align="center">Is Phase Out</th></tr></thead >');
                            //alert(data.length);
                            for (var i = 0, lennos = data.length; i < lennos; i++) {
                                //alert(window.location.host);
                                var actionicon = "";
                                if (data[i].isphaseout == 'Y') {
                                    actionicon = "<a href=\"#\" onclick=\"initChange(" + data[i].ID + ",'" + data[i].isphaseout + "');\" \"><img src=\"http://localhost:8085/ABP-Ver1/assets/images/Tick-Box.png\" width=20 height=20/></a>";
                                } else {
                                    actionicon = "<a href=\"#\" onclick=\"initChange(" + data[i].ID + ",'" + data[i].isphaseout + "');\" \"><img src=\"http://localhost:8085/ABP-Ver1/assets/images/wrong.png\" width=20 height=20/></a>";
                                }

                                $('#example-basic4 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center">' + data[i].ID + '</td><td align="center">' + data[i].question_title + '&nbsp<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].questionimgurl + ' width="50" height="50"/></td><td align="center">' + data[i].option1 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl1 + ' width="50" height="50"/></td><td align="center">' + data[i].option2 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl2 + ' width="50" height="50"/></td><td align="center">' + data[i].option3 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl3 + ' width="50" height="50"/></td><td align="center">' + data[i].option4 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl4 + ' width="50" height="50"/></td><td align="center">' + data[i].appeared + '</td><td align="center">' + data[i].answered + '</td><td align="center">' + actionicon + '</td></tr>');


                            }


                        }


                        //$("#qpid").html(data);

                    });
                } else {
                    $("#example-basic4").empty();
                    //$("#qpid").html("<option value=''>------- Select --------</option>");
                }
            });

            $("#pcid").change(function () {

                var qpackid = $("#qpackid").val();
                var nosid = $("#nosid").val();
                var pcid = $(this).val();
                if (pcid) {

                    $.ajax({
                        url: "getQuestionsbypc.io",
                        data: {qp_id: qpackid, nosid: nosid, pcid: pcid},
                        type: 'GET',
                        success: function (data) {

                            $("#example-basic4").empty();
                            $('#example-basic4').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center">Question ID</th><th align="center">Question Title</th><th align="center">Option 1</th><th align="center">Option 2</th>' +
                                    '<th align="center">Option 3</th><th align="center">Option 4</th><th align="center">Question Appeared</th><th align="center">Accurately Answered</th><th align="center">Is Phase Out</th></tr></thead >');
                            //alert(data.length);
                            for (var i = 0, lennos = data.length; i < lennos; i++) {
                                //alert(window.location.host);
                                var actionicon = "";
                                if (data[i].isphaseout == 'Y') {
                                    actionicon = "<a href=\"#\" onclick=\"initChange(" + data[i].ID + ",'" + data[i].isphaseout + "');\" \"><img src=\"http://localhost:8085/ABP-Ver1/assets/images/Tick-Box.png\" width=20 height=20/></a>";
                                } else {
                                    actionicon = "<a href=\"#\" onclick=\"initChange(" + data[i].ID + ",'" + data[i].isphaseout + "');\" \"><img src=\"http://localhost:8085/ABP-Ver1/assets/images/wrong.png\" width=20 height=20/></a>";
                                }

                                $('#example-basic4 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center">' + data[i].ID + '</td><td align="center">' + data[i].question_title + '&nbsp<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].questionimgurl + ' width="50" height="50"/></td><td align="center">' + data[i].option1 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl1 + ' width="50" height="50"/></td><td align="center">' + data[i].option2 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl2 + ' width="50" height="50"/></td><td align="center">' + data[i].option3 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl3 + ' width="50" height="50"/></td><td align="center">' + data[i].option4 + '&nbsp;<img src=http://' + window.location.host + '/ABP-Ver1/uploaded/questions/' + data[i].imageurl4 + ' width="50" height="50"/></td><td align="center">' + data[i].appeared + '</td><td align="center">' + data[i].answered + '</td><td align="center">' + actionicon + '</td></tr>');


                            }


                        }




                    });
                } else {
                    $("#example-basic4").empty();
                    $("#addbtn").hide();
                    $("#expbtn").hide();

                }
            });


            $('#openeditqp').on('click', function (event) {
                event.preventDefault();
                alert("Test EDIt Dialog");
            });
            $('#addbtn').on('click', function (event) {
                //event.preventDefault();
                var qpackid = $("#qpackid").val();
                var nosid = $("#nosid").val();
                var pcid = $("#pcid").val();
                alert(qpackid + "--" + nosid + "--" + pcid);
                window.location.href = "<%=request.getContextPath()%>/admin/questions/addQuestion.io?qpackid=" + qpackid + "&nosid=" + nosid + "&pcid=" + pcid;
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
            function initChange(questionid, isactive) {
                //alert(questionid);
                //alert(isactive);
                $.ajax({
                    url: "initChange.io",
                    data: {questionid: questionid, isactive: isactive},
                    type: 'GET',
                    success: function (data) {
                        if (data.status == 'save') {
                            $('#qpackid').trigger("change");
                        }
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
            function showmultilanguage(id) {
                event.preventDefault();
                var w = window.open("<%=request.getContextPath()%>/admin/questions/openqplang.io?qid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }

        </script>
    </body>

</html>