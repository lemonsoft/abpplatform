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

        <style>
            .circle {
                display: block;
                width: 50px;
                height: 50px;
                margin: 1em auto;
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center center;
                -webkit-border-radius: 99em;
                -moz-border-radius: 99em;
                border-radius: 99em;
                border: 5px solid #eee;
                box-shadow: 0 3px 2px rgba(0, 0, 0, 0.3);  
            }
        </style>
    </head>
    <body>
        <div class="smart-wrap">
            <div class="smart-forms smart-container wrap-full">

                <div class="form-header header-blue">
                    <h4><i class="fa fa-pencil-square"></i> Take Practicals</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->
                <form:form method="post" action="${action}"   commandName="practical" > 
                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label">Batch ID:</label>
                                    <label class="field prepend-icon">
                                        <form:select path="batchid" name="batchid" id="batchid" class="gui-input" >
                                            <form:option value="">--Select--</form:option>
                                            <form:options items="${batchlist}"/>
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
    </body>
    <script>

        $("#batchid").change(function () {
            var batchid = $(this).val();
            $.ajax({
                url: "getBatchUser.io",
                data: {batchid: batchid},
                type: 'GET',
                success: function (data) {
                    //alert(data.length);
                    $("#example-basic4").empty();

                    $('#example-basic4').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center">Attendance Image</th><th align="center">Enrollment No</th><th align="center">Student Name</th><th align="center" colspan="2">Practical Exam</th></tr></thead >');
                    for (var i = 0, lennos = data.length; i <= lennos; i++) {

                        var view = "<button type=\"button\" class=\"button btn-primary block pushed expand\" onclick=\"takeExam('" + data[i].userid + "');\">Take Practical</button>";

                        $('#example-basic4 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center"><img src="<%=request.getContextPath()%>/assets/images/NoPhotoAvailable.jpg" class="circle"/></td><td align="center">' + data[i].enrollno + '</td><td align="center">' + data[i].studentname + '</td><td align="center">' + view + '</td></tr>');
                    }

                }
            });

        });
        function takeExam(id) {

            var batchid = $('#batchid').val();
            var w = window.open("<%=request.getContextPath()%>/assessor/practicalexam/continueExam.io?userid=" + id + "&batchid=" + batchid, "popupWindow", "width=1200, height=500, scrollbars=yes");
        }

    </script>
</html>