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
        <style>
        .circle {
            display: block;
            width: 250px;
            height: 250px;
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

        <!--[if lte IE 9]>
            <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>    
            <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
        <![endif]-->    

        <!--[if lte IE 8]>
            <link type="text/css" rel="stylesheet" href="css/smart-forms-ie8.css">
        <![endif]-->
        <style type="text/css">
            body { font-family: Helvetica, sans-serif; }
            h2, h3 { margin-top:0; }
            form { margin-top: 15px; }
            form input { margin-right: 15px; }
            #results { float:right; margin:20px; padding:20px; border:1px solid; background:#ccc; }
        </style>
        <script type="text/javascript" src="../webcam.min.js"></script>

        <!-- Configure a few settings and attach camera -->
        <script language="JavaScript">
            Webcam.set({
                width: 320,
                height: 240,
                image_format: 'jpeg',
                jpeg_quality: 90
            });
            Webcam.attach('#my_camera');
        </script>
    </head>
    <body >

        <div class="smart-wrap">
            <div class="smart-forms smart-container wrap-full">
                <input type="hidden" name="userid" id="userid" value="${userid}"/>
                <input type="hidden" name="batchid" id="batchid" value="${batchid}"/>
                <div class="form-header header-blue">
                    <h4><i class="fa fa-pencil-square"></i>Capture Image</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->
                <div class="form-body theme-blue">
                    <div class="section colm colm6"><img src="<%=request.getContextPath()%>/assets/images/NoPhotoAvailable.jpg" width="250px;" height="250px;" class="circle"/></div>
                    <div class="section colm colm6"><button type="button" class="button btn-blue" onclick="saveImage('${batchid}');">Capture Image</button>
                    <button type="button" class="button btn-blue" onclick="continueExam('${userid}');">Continue Exam</button></div>
                </div>


            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>
    <script>
        function saveImage(id) {

            $.ajax({
                url: "takeattendance.io",
                data: {batchid: id},
                type: 'GET',
                success: function (data) {
                    if (data.status == 'save') {
                        window.close();
                    }
                }
            });
        }
        function continueExam(id) {

            alert(" display id " + id);
            var batchid = $('#batchid').val();
            var w = window.open("<%=request.getContextPath()%>/assessor/practicalexam/continueExam.io?userid=" + id + "&batchid=" + batchid, "popupWindow", "width=1200, height=500, scrollbars=yes");


        }
    </script> 
    <script language="JavaScript">
        function preview_snapshot() {
            // freeze camera so user can preview pic
            Webcam.freeze();

            // swap button sets
            document.getElementById('pre_take_buttons').style.display = 'none';
            document.getElementById('post_take_buttons').style.display = '';
        }

        function cancel_preview() {
            // cancel preview freeze and return to live camera feed
            Webcam.unfreeze();

            // swap buttons back
            document.getElementById('pre_take_buttons').style.display = '';
            document.getElementById('post_take_buttons').style.display = 'none';
        }

        function save_photo() {
            // actually snap photo (from preview freeze) and display it
            Webcam.snap(function (data_uri) {
                // display results in page
                document.getElementById('results').innerHTML =
                        '<h2>Here is your image:</h2>' +
                        '<img src="' + data_uri + '"/>';

                // swap buttons back
                document.getElementById('pre_take_buttons').style.display = '';
                document.getElementById('post_take_buttons').style.display = 'none';
            });
        }
    </script>
</html>
