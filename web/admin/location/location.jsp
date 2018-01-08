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

        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/css/easyLocator.css">  

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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="location.title" text="Our Assessment Locations" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="locationdao">
                <div class="form-body theme-blue">
                    <div class="frm-row">
                        <div class="section colm colm4"> 
                            <div class="section">
                                <label for="names" class="field-label"><spring:message code="location.fromdate" text="From Date" /></label>
                                <label class="field prepend-icon">
                                    <form:input path="fromdate" type="date" id="fromdate" class="gui-input" placeholder="From Date" required="true"/>

                                    <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                </label>
                            </div><!-- end section -->
                        </div><!-- end section -->
                        <div class="section colm colm4"> 
                            <div class="section">
                                <label for="names" class="field-label"><spring:message code="location.todate" text="To Date" /></label>
                                <label class="field prepend-icon">

                                    <form:input path="todate" type="date" id="todate" class="gui-input" placeholder="To Date" required="true"/>

                                    <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                </label>
                            </div><!-- end section -->
                        </div><!-- end section -->
                        <div class="section colm colm4">
                            <div class="section">
                                <label for="names" class="field-label">&nbsp;</label>
                                <label class="field prepend-icon">

                                    <button type="submit" class="button btn-blue"><spring:message code="common.button.search" text="Search" /></button>

                                    
                                </label>
                            </div><!-- end section -->
                            
                        </div>

                    </div><!-- end frm-row section -->
                    </form:form>
                    <div class="frm-row">


                        <div class="section colm colm12">


                            <div id="locatorList" style="height:400px"></div>
                            <script>
                                $(document).ready(function () {

                                    var data = [{
                                            title: 'Tag',
                                            description: '',
                                            image: '',
                                            link: '',
                                            iconMarker: '',
                                            iconMarkerActive: '',
                                            lat: 26.922070,
                                            lng: 75.778885
                                        }]
                                    var easyLocator = $('#locatorList').easyLocator({
                                        myLocations: data,
                                        apiKey: 'AIzaSyB28b_RQ6Pnz9_Zhcf5VUVw_q3A8sRoskg',
                                        useMarkerCluster: true,
                                        markerClustererOptions: {
                                            imagePath: 'images/m'
                                        }
                                    });
                                    easyLocator.onEvents.progress(function (evt) {
                                        console.log(evt);
                                    });
                                });
                            </script>



                        </div><!-- end section -->
                    </div><!-- end frm-row section -->
                </div><!-- end .form-body section -->
            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>

</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/easyLocator.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/markerclusterer.min.js"></script>

</html>
