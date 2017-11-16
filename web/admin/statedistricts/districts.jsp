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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="statedistrict.title" text="States & Districts" /></h4>
                    <div style="position: absolute;top:20px;right:45px;width: 100px;"><a href="#responsive" class="btn btn-outline dark" data-toggle="modal" > <spring:message code="statedistrict.addstate" text="Add New State" /> </a></div>
                   
                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="state">

                    <div class="form-body theme-blue">


                        <div class="frm-row">

                            <div class="section colm colm6">
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="statedistrict.selectstate" text="Select State" /></label>
                                    <label class="field prepend-icon"><form:select path="stateName" id="stateName" class="gui-input">
                                            <form:option value="0">--Select--</form:option>
                                            <form:options items="${allstates}"/>
                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3">
                                <div class="section">
                                    <label for="names" class="field-label">&nbsp;</label>
                                    <label class="field prepend-icon">
                                        <button type="submit" class="button btn-blue"><spring:message code="common.button.search" text="Search" /></button>
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3">
                                <div class="section">
                                    <label for="names" class="field-label">&nbsp;</label>
                                    <label class="field prepend-icon">
                                        <a href="<%=request.getContextPath()%>/admin/statedistricts/initDistrict.io" class="button btn-primary block pushed expand"> <spring:message code="statedistrict.adddistrict" text="Add District" /> </a>
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <display:table name="records" class="table table-bordered" requestURI="initSearch.io" decorator="com.abp.statedistrict.SrcDecorator" pagesize="10">
                                <display:column property="districtID" title="District ID" />
                                <display:column property="districtName" title="District Name" sortable="true"/>
                                <display:column property="actions" title="Actions"/>
                            </display:table>

                        </div>
                    </div><!-- end .form-body section -->

                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->


        <!-- responsive -->
        <!-- /.modal -->
        <div id="responsive" class="modal fade" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                        <h4 class="modal-title"><spring:message code="statedistrict.addstate" text="State" /></h4>
                    </div>
                    <div class="modal-body">
                        <div class="scroller" style="height:100px" data-always-visible="1" data-rail-visible1="1">
                            <form:form method="post" action="/admin/statedistricts/saveState.io"  commandName="state" id="stateForm">

                                <div class="form-body theme-blue">


                                    <div class="frm-row">
                                        <span style="color:greenyellow;" id="result"></span>
                                        <div class="section colm colm6">
                                            <label for="names" class="field-label"><spring:message code="statedistrict.statenam" text="Enter State" /></label>
                                            <div class="section">

                                                <label class="field prepend-icon">
                                                    <input required type="text" name="statename" id="statename" class="col-md-12 form-control" placeholder="Enter State Name"/> 

                                                </label>
                                            </div><!-- end section -->
                                        </div><!-- end section -->

                                    </div><!-- end frm-row section -->
                                </div><!-- end .form-body section -->

                            </form:form>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" class="btn dark btn-outline">Close</button>
                        <button type="button" id="btnSaveState" class="btn green">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        
        
        <script type="text/javascript"
        src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

        <script type="text/javascript">

            $(document).ready(function () {
                $("#btnSaveState").click(function () {
                    var statname = $('#statename').val();
                    if (statname.trim()) {
                        $.ajax({
                            url: 'saveState.io',
                            data: {name: statname},
                            success: function (data) {

                                $('#result').html(data);
                                $('#statename').val("");
                            }
                        });
                    } else {
                        $('#result').html("Invalid State Name");
                    }


                });
            });

        </script>

    </body>

</html>
