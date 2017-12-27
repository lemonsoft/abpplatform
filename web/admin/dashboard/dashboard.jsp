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
                    <h4><i class="fa fa-pencil-square"></i>Dashboard</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->
                <div class="form-body theme-blue">
                    <div class="frm-row">
                        <div class="section colm colm12"> 

                            <div class="portlet-body">
                                <div class="tiles">
                                    
                                    <div class="tile double bg-red-sunglo">
                                        <div class="tile-body">
                                            <i class="fa fa-calendar"></i>
                                        </div>
                                        <div class="tile-object">
                                            <div class="name"> Sectors </div>
                                            <div class="number"> ${totalSectors} </div>
                                        </div>
                                    </div>
                                    
                                    <div class="tile double selected bg-yellow-saffron">
                                        <div class="corner"> </div>
                                        <div class="tile-body">
                                            <i class="fa fa-user"></i>
                                        </div>
                                        <div class="tile-object">
                                            <div class="name"> Assessor </div>
                                            <div class="number"> ${totalassessor} </div>
                                        </div>
                                    </div>
                                    
                                    <div class="tile double bg-purple-studio">
                                        <div class="tile-body">
                                            <i class="fa fa-shopping-cart"></i>
                                        </div>
                                        <div class="tile-object">
                                            <div class="name"> Batches </div>
                                            <div class="number"> 121 </div>
                                        </div>
                                    </div>
                                    
                                    <div class="tile double bg-green-meadow">
                                        <div class="tile-body">
                                            <i class="fa fa-comments"></i>
                                        </div>
                                        <div class="tile-object">
                                            <div class="name"> Assessments </div>
                                            <div class="number"> 12 </div>
                                        </div>
                                    </div>
                                    
                                    
                                    
                                </div>
                            </div>
                        </div>
                    </div>


                </div><!-- end section -->


            </div><!-- end frm-row section -->

        </div>


    </div><!-- end .smart-forms section -->
</div><!-- end .smart-wrap section -->


</body>

</html>
