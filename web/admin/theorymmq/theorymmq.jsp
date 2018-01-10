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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="theorymmq.title" text="Multimapping Questions" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->
                <form:form method="post" action="${action}"   commandName="theorymmq" > 
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
                            <button type="button" class="button btn-blue" onclick="importQuestions();"><spring:message code="theorymmq.importmmq" text="Import MMQ's" /></button>&nbsp;<button type="button" id="importmultilang" class="button btn-blue" onclick="importmultilanguage();"><spring:message code="theorymmq.importmultilingualmmq" text="Import Multi Lingual MMQ's" /></button>
                            <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">

                            </table>
                        </div>


                    </div>
                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
        <script type="text/javascript">

            $("#ssc_id").change(function () {

                var sscid = $(this).val();
                if (sscid) {

                    $.ajax({
                        url: "getQP.io",
                        data: {ssc_id: sscid},
                        type: 'GET',
                        success: function (data) {
                            var str = "<option value='0'>------- Select --------</option>";
                            if (data.length === 0) {
                                str = "<option value='0'>------- Select --------</option>";
                            }
                            $.each(data, function (index, jsonObject) {

                                str = str + "<option value=" + jsonObject.ID + ">" + jsonObject.NAME + "</option>";

                            });
                            $("#qpackid").html(str);
                        }
                    });
                } else {
                    $("#qpackid").html("<option value='0'>------- Select --------</option>");
                    $("#example-basic4").empty();
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
                            $('#example-basic4').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center"><spring:message code="theorymmq.questionid" text="Question ID" /></th><th align="center"><spring:message code="theorymmq.questiontitle" text="Question Title" /></th><th align="center"><spring:message code="theorymmq.option1" text="Option 1" /></th><th align="center"><spring:message code="theorymmq.option2" text="Option 2" /></th>' +
                                    '<th align="center"><spring:message code="theorymmq.option3" text="Option 3" /></th><th align="center"><spring:message code="theorymmq.option4" text="Option 4" /></th><th align="center"><spring:message code="theorymmq.marks" text="Marks" /></th><th align="center"><spring:message code="theorymmq.pcwithmarks" text="PCs with Selected Marks" /></th><th align="center"><spring:message code="theorymmq.isactive" text="Is Active" /></th><th align="center"><spring:message code="theorymmq.correctoption" text="Correct Option" /></th><th align="center"><spring:message code="theorymmq.edit" text="Edit" /></th><th align="center"><spring:message code="theorymmq.multilingual" text="Multilingual" /></th><th align="center">Action</th></tr></thead >');
                            //alert(data.length);
                            for (var i = 0, lennos = data.length; i < lennos; i++) {
                                //alert(window.location.host);
                                $('#example-basic4 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center">' + data[i].ID + '</td><td align="center">' + data[i].question_title + '</td><td align="center">' + data[i].option1 + '</td><td align="center">' + data[i].option2 + '</td><td align="center">' + data[i].option3 + '</td><td align="center">' + data[i].option4 + '</td><td align="center">' + data[i].marks + '</td><td align="center">' + data[i].pcwithmarks + '</td><td align="center">' + data[i].isactive + '</td><td align="center">' + data[i].correctOption + '</td><td align="center"><a href=initUpdate.io?recid=' + data[i].ID + '>Edit</a></td><td align="center"><a href="#" onclick="showmultilanguage(' + data[i].ID + ');">View</a></td><td align="center"><a href="#" onclick="deleteClick(' + data[i].ID + ',event);">Delete</a></td></tr>');


                            }

                        }

                    });
                } else {
                    $("#example-basic4").empty();

                }
            });
            function importQuestions() {
                var qpid = $("#qpackid").val();

                if (parseInt(qpid) !== 0) {
                    window.location.href = "<%=request.getContextPath()%>/admin/theorymmq/importMMQQuestions.io?qpid=" + qpid;
                } else {
                    alert("Please select Qualification Pack");
                }



            }
            function importmultilanguage() {

                window.location.href = "<%=request.getContextPath()%>/admin/theorymmq/importMultiLanguage.io";

            }
            function showmultilanguage(id) {
                event.preventDefault();
                var w = window.open("<%=request.getContextPath()%>/admin/theorymmq/openqplang.io?qid=" + id, "popupWindow", "width=1024, height=500, scrollbars=yes");
            }
            

            $('#onchange').on('click', function (event) {
                event.preventDefault();
                alert($(this).attr('href'));
            });
            function setchange(event) {
                event.preventDefault();
                var href = event.currentTarget.getAttribute('href');

                var str_array = href.split(',');
                var recid = str_array[0];
                var active = str_array[1];
                recid = recid.substring(6);
                active = active.substring(7);

                $.ajax({
                    url: "initChange.io",
                    data: {recid: recid, active: active},
                    type: 'GET',
                    success: function (data) {
                        $('#qpackid').trigger("change");

                    }
                });
            }
            function deleteClick(id,event) {
                event.preventDefault();
                
                $.ajax({
                    url: "delete.io",
                    data: {recid: id},
                    type: 'GET',
                    success: function (data) {
                        $('#qpackid').trigger("change");

                    }
                });
            }

        </script>
    </body>

</html>