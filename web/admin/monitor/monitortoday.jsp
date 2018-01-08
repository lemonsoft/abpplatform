<%-- 
    Document   : addlanguage
    Created on : 9 Nov, 2017, 2:29:37 PM
    Author     : ss
--%>

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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="monitortoday.title" text="Monitor Todays Assessment" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->



                <div class="form-body theme-blue">

                    <div class="frm-row">
                        <div class="section colm colm12">
                            <display:table name="records" class="table table-bordered" requestURI="init.io" decorator="com.abp.admin.monitorassesment.SrcDecorator" pagesize="50">
                                <display:column property="batchID" titleKey="monitortoday.batchID" />
                                <display:column property="jobrole" titleKey="monitortoday.jobrole" />
                                <display:column property="ssc" titleKey="monitortoday.ssc" />
                                <display:column property="tpname" titleKey="monitortoday.tpname" />
                                <display:column property="centeraddr" titleKey="monitortoday.centeraddr" />
                                <display:column property="studentno" titleKey="monitortoday.studentno" />
                                <display:column property="actions" titleKey="monitortoday.actions" />
                            </display:table>

                        </div>
                    </div>


                </div><!-- end .form-body section -->
                <div class="frm-row" id="displaytabledata">
                    <div class="section colm colm12">
                        <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">


                        </table>
                    </div>    
                </div>




            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>
    <script>

        function viewAssesment(batchid) {

            //alert(" " + batchid);
            if (batchid) {

                $.ajax({
                    url: "getUserResult.io",
                    data: {batchid: batchid},
                    type: 'GET',
                    success: function (data) {
                        //alert(data.length);
                        $("#example-basic4").empty();

                        $('#example-basic4').prepend('<thead ><tr style="height: 50px;font-size:12px;color: #000;background-color: #fff;" ><th align="center"><spring:message code="monitortoday.srno" text="monitortoday.srno" /></th><th align="center"><spring:message code="monitortoday.enrollno" text="monitortoday.enrollno" /></th><th align="center"><spring:message code="monitortoday.studentname" text="monitortoday.studentname" /></th><th align="center"><spring:message code="monitortoday.status" text="monitortoday.status" /></th><th align="center" colspan="2"><spring:message code="monitortoday.view" text="monitortoday.view" /></th></tr></thead >');
                        for (var i = 0, lennos = data.length; i <= lennos; i++) {
                            var view = "";
                            if (data[i].view != 'NA') {
                                view = "<button class=\"button btn-primary block pushed expand\" onclick=\"viewResult('" + data[i].view + "');\">View</button>";
                            }

                            $('#example-basic4 tr:last').after('<tr data-tt-id="1" id="qpack" style="height: 50px;font-size:12px;color: #000;background-color: #fff;"><td align="center">' + i + '</td><td align="center">' + data[i].enrollmentno + '</td><td align="center">' + data[i].traineename + '</td><td align="center">' + data[i].examstatus + '</td><td align="center">' + view + '</td></tr>');
                        }

                     }
                });
            } else {
                $("#example-basic4").empty();


            }
        }

        function viewResult(id) {
            var array = id.split(',');
            var w = window.open("<%=request.getContextPath()%>/admin/monitor/totalreport.io?userid=" + array[0] + "&userresultid=" + array[1], "popupWindow", "width=1200, height=500, scrollbars=yes");
        }


    </script>
</html>
