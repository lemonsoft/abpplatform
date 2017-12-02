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
                    <h4><i class="fa fa-pencil-square"></i>Map To PC</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="scenariommq">
                    <form:hidden path="id" />
                    <div class="form-body theme-blue">

                        <label for="names" class="field-label">Edit Question:</label>
                        <div class="frm-row">

                            <div class="section colm colm6">
                                <label class="field prepend-icon">
                                    <form:input path="question" id="question" class="gui-input" />

                                    <span class="field-icon"><i class="fa fa-language"></i></span>  
                                </label>
                            </div><!-- end section -->



                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">
                                <thead><th>NOSID</th><th>NOS Name</th><th>&nbsp;</th></thead>
                                <tbody>
                                    <%! int i = 0;%>
                                    <c:forEach var="listdata" items="${noslist}" varStatus="i">

                                        <tr>
                                            <td>${listdata.nosid}</td>
                                            <td>${listdata.nosname}</td>
                                            <td><c:forEach var="pcdata" items="${listdata.pcdata}" >
                                                    <table id="example-basic4" class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">
                                                        <thead><th></th><th>PC ID</th><th>PC Name</th><th>Actual Practical Marks</th><th>Remaining Practical Marks</th><th>Enter Marks</th></thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>
                                                                    <c:if test="${pcdata.checkbox=='Y'}"><input type="checkbox" id="pcid<%=i%>" name="pcid" value="${pcdata.id}" checked/></c:if>
                                                                    <c:if test="${pcdata.checkbox=='N'}"><input type="checkbox" name="pcid" id="pcid<%=i%>" value="${pcdata.id}" /></c:if>
                                                                    </td>
                                                                    <td>${pcdata.pcid}</td>
                                                                <td>${pcdata.pcname}</td>
                                                                <td>${pcdata.actualmarks}</td>
                                                                <td>${pcdata.remainingmarks}</td>
                                                                <td><input type="text" name="marks" value="${pcdata.marks}" id="<%=i%>"/><br/><span id="err<%=i%>" style="color:red;"></span></td>
                                                            </tr>

                                                        </tbody>
                                                    </table> 
                                                    <%i++;%>
                                                </c:forEach></td>
                                        </tr>

                                    </c:forEach>


                                </tbody>
                            </table>


                        </div>



                    </div><!-- end .form-body section -->
                    <div class="form-footer">
                        <c:if test = "${mode == 'add'}">
                            <button type="submit" class="button btn-blue"><spring:message code="common.button.submit" text="Submit" /></button>
                        </c:if>
                        <c:if test = "${mode=='update'}">
                            <button type="button" onclick="mapPCIDwithMarks();" class="button btn-blue" id="updatebtn"><spring:message code="common.button.update" text="Update" /></button>
                        </c:if>
                        <button type="reset" class="button btn-blue"><spring:message code="common.button.cancel" text="Cancel" /></button>
                    </div><!-- end .form-footer section -->
                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->


        <script type="text/javascript"  src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script>
                                $(document).ready(function () {
                                    $(':checkbox').change(function () {

                                        var idattr = $(this).attr('id');
                                        var id = idattr.substring(4);
                                        alert(id);
                                        var marks = $('#' + id).val();
                                        if (parseInt(marks) > 0) {
                                            $('#err' + id).text("");
                                            $('#updatebtn').prop('disabled', false);
                                        } else {

                                        }

                                    });
                                    $('input[name="marks"]').change(function () {

                                        var entermarks = $(this).val();
                                        var id = $(this).attr('id');
                                        var pcid = $('#pcid' + id).val();
                                        var questionid = $('#id').val();

                                        //alert($('#pcid'+id).val());
                                        if (parseInt(entermarks) > 0) {
                                            if (!$('#pcid' + id).is(":checked")) {
                                                $('#err' + id).text("please select checkbox");
                                                //$('#updatebtn').disable();
                                                $('#updatebtn').prop('disabled', true);
                                            } else {

                                                $.ajax({
                                                    url: "checkRemainingmarks.io",
                                                    data: {pcid: pcid, questionid: questionid, entermarks: entermarks},
                                                    type: 'GET',
                                                    success: function (data) {

                                                        if (data.status == 'greator') {
                                                            $('#err' + id).text("Marks shouldn't be greaterthan remaining practical marks !");
                                                            $('#updatebtn').prop('disabled', true);
                                                        }
                                                        if (data.status == 'smaller') {
                                                            $('#err' + id).text("");
                                                            $('#updatebtn').prop('disabled', false);
                                                        }
                                                    }
                                                });

                                            }
                                        } else {
                                            $('#err' + id).text("");
                                            $('#updatebtn').prop('disabled', false);
                                        }
                                    });

                                });

                                function mapPCIDwithMarks() {
                                    alert("Mapping PCID");
                                    var questionid = $('#id').val();
                                    jsonObj = [];
                                    $("input:checkbox[name=pcid]:checked").each(function () {

                                        var idattr = $(this).attr('id');
                                        var id = idattr.substring(4);
                                        var pcid = $(this).val();
                                        var marks = $('#' + id).val();
                                        item = {};
                                        item ["pcid"] = pcid;
                                        item ["marks"] = marks;

                                        jsonObj.push(item);

                                    });
                                    alert(jsonObj);

                                    $.ajax({
                                        url: "updatePCIDMARKS.io",
                                        data: {data: JSON.stringify(jsonObj), questionid: questionid},
                                        type: 'GET',
                                        success: function (data) {
                                            //alert(data);
                                            if (data.status == 'update') {
                                                location.reload();
                                            }
                                        }
                                    });

                                }


        </script>
    </body>

</html>
