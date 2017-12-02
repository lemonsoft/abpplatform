
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
        <link href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />

    </head>

    <div class="smart-wrap">
        <div class="smart-forms smart-container wrap-full">

            <div class="form-header header-blue">
                <h4><i class="fa fa-pencil-square"></i>Senario Questions</h4>
                <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

            </div><!-- end .form-header section -->


            <div class="form-body theme-blue">


                <div class="frm-row">
                    <c:if test = "${alldata!=null}">

                        <display:table name="alldata" class="table table-bordered" requestURI="initSearch.io"  pagesize="10">
                            <display:column property="id" title="Sr No #" />
                            <display:column property="question" title="Question"/>
                            <display:column property="pcsselectedmarks" title="PC'S with Selected Marks"/>
                            <display:column property="totalmarks" title="Total Marks"/>


                        </display:table>


                    </c:if>
                </div><!-- end frm-row section -->



            </div><!-- end .form-body section -->



        </div><!-- end .smart-forms section -->
    </div><!-- end .smart-wrap section -->



    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <script src="<%=request.getContextPath()%>/assets/global/plugins/jquery-repeater/jquery.repeater.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->

