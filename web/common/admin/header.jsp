<%-- 
    Document   : header
    Created on : Dec 19, 2008, 1:36:55 AM
    Author     : eswar@vaannila.com
--%>

<%@page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<head>

    <meta charset="utf-8" />

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <link href="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/assets/global/plugins/morris/morris.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/assets/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/assets/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css" />
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="<%=request.getContextPath()%>/assets/global/css/components-rounded.min.css" rel="stylesheet" id="style_components" type="text/css" />
    <link href="<%=request.getContextPath()%>/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
    <!-- END THEME GLOBAL STYLES -->
    <!-- BEGIN THEME LAYOUT STYLES -->
    <link href="<%=request.getContextPath()%>/assets/layouts/layout3/css/layout.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/assets/layouts/layout3/css/themes/default.min.css" rel="stylesheet" type="text/css" id="style_color" />
    <link href="<%=request.getContextPath()%>/assets/layouts/layout3/css/custom.min.css" rel="stylesheet" type="text/css" />
    <!-- END THEME LAYOUT STYLES -->
    <script type="text/javascript"  src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
</head>


<body class="page-container-bg-solid">
    <div class="page-wrapper">
        <div class="page-wrapper-row">
            <div class="page-wrapper-top">
                <!-- BEGIN HEADER -->
                <div class="page-header">
                    <!-- BEGIN HEADER TOP -->
                    <div class="page-header-top">
                        <div class="container">
                            <!-- BEGIN LOGO -->
                            <div class="page-logo">
                                <a href="#">
                                    <img src="<%=request.getContextPath()%>/assets/images/IASSESS_logo.jpg" alt="logo" width="80" height="40" class="logo-default">
                                </a>
                            </div>
                            <!-- END LOGO -->
                            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                            <a href="javascript:;" class="menu-toggler"></a>
                            <!-- END RESPONSIVE MENU TOGGLER -->
                            <!-- BEGIN TOP NAVIGATION MENU -->
                            <div class="top-menu">
                                <ul class="nav navbar-nav pull-right">

                                    Language : <a href="?language=en">English</a>|<a href="?language=hi">Hindi</a>
                                    <form:form action="${pageContext.request.contextPath}/j_spring_security_logout" method="POST">

                                     <a href="#" onclick="$(this).closest('form').submit()"><span class="arrow"></span>Logout</a>
                                    </form:form>
                                </ul>
                            </div>
                            <!-- END TOP NAVIGATION MENU -->
                        </div>
                    </div>
                    <!-- END HEADER TOP -->
                    <!-- BEGIN HEADER MENU -->
                    <div class="page-header-menu">
                        <div class="container">
                            <!-- BEGIN HEADER SEARCH BOX -->
                            <form class="search-form" action="page_general_search.html" method="GET">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search" name="query">
                                    <span class="input-group-btn">
                                        <a href="javascript:;" class="btn submit">
                                            <i class="icon-magnifier"></i>
                                        </a>
                                    </span>
                                </div>
                            </form>
                            <!-- END HEADER SEARCH BOX -->
                            <!-- BEGIN MEGA MENU -->
                            <!-- DOC: Apply "hor-menu-light" class after the "hor-menu" class below to have a horizontal menu with white background -->
                            <!-- DOC: Remove data-hover="dropdown" and data-close-others="true" attributes below to disable the dropdown opening on mouse hover -->
                            <div class="hor-menu  ">
                                <ul class="nav navbar-nav">
                                    <li aria-haspopup="true" class="menu-dropdown classic-menu-dropdown active">
                                        <a href="javascript:;"> Dashboard
                                            <span class="arrow"></span>
                                        </a>

                                    </li>

                                    <li aria-haspopup="true" class="menu-dropdown mega-menu-dropdown  ">
                                        <a href="javascript:;"> Modules
                                            <span class="arrow"></span>
                                        </a>
                                        <ul class="dropdown-menu" style="min-width: 710px">
                                            <li>
                                                <div class="mega-menu-content">
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <ul class="mega-menu-submenu">
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/#"> Assessment Location </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/#">Monitor Assessment </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/ssc/initSearch.io"> Sectors </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/assessor/initSearch.io"> Assessors </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/qualificationpack/init.io"> Qualification Pack </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/batches/init.io"> Batches </a>
                                                                </li>



                                                            </ul>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <ul class="mega-menu-submenu">
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/#"> Results </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/questions/init.io"> Questions </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/#"> Question usage </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/#"> Settings </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/statedistricts/init.io"> State & Districts </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/language/initSearch.io"> Languages </a>
                                                                </li>

                                                            </ul>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <ul class="mega-menu-submenu">
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/#"> Generate QP </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/#"> Question Papers </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/#"> Question Approval </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/theorymmq/init.io"> Theory MMQ </a>
                                                                </li>
                                                                <li>
                                                                    <a href="<%=request.getContextPath()%>/admin/#"> Practical MMQ </a>
                                                                </li>

                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </li>

                                    <li aria-haspopup="true" class="menu-dropdown mega-menu-dropdown  ">
                                        <a href="javascript:;"> Reports
                                            <span class="arrow"></span>
                                        </a>
                                        <ul class="dropdown-menu" style="min-width: 710px">
                                            <li>
                                                <div class="mega-menu-content">
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <ul class="mega-menu-submenu">
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_mega_menu_light.html" class="nav-link  "> Result Summary </a>
                                                                </li>
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_top_bar_light.html" class="nav-link  "> Job Role Master </a>
                                                                </li>
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_fluid_page.html" class="nav-link  "> Question Bank Analysis </a>
                                                                </li>
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_top_bar_fixed.html" class="nav-link  "> Training Partner Wise </a>
                                                                </li>
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_mega_menu_fixed.html" class="nav-link  "> Consolidate Assessor Dashboard </a>
                                                                </li>
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_disabled_menu.html" class="nav-link  "> Assessor Report </a>
                                                                </li>
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_blank_page.html" class="nav-link  "> Individual Assessor Dashboard </a>
                                                                </li>




                                                            </ul>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <ul class="mega-menu-submenu">
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_mega_menu_fixed.html" class="nav-link  "> Monthly Dashboard </a>
                                                                </li>
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_disabled_menu.html" class="nav-link  "> Time Analysis </a>
                                                                </li>
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_blank_page.html" class="nav-link  "> Location wise </a>
                                                                </li>
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_mega_menu_fixed.html" class="nav-link  "> Batch Analysis- NOS Wise </a>
                                                                </li>
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_disabled_menu.html" class="nav-link  "> Location Report- NOS Wise </a>
                                                                </li>
                                                                <li aria-haspopup="true" class=" ">
                                                                    <a href="layout_blank_page.html" class="nav-link  "> Job Role Wise </a>
                                                                </li>



                                                            </ul>
                                                        </div>

                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <!-- END MEGA MENU -->
                        </div>
                    </div>
                    <!-- END HEADER MENU -->
                </div>
                <!-- END HEADER -->
            </div>
        </div>
        <div class="page-wrapper-row full-height">
            <div class="page-wrapper-middle">
                <!-- BEGIN CONTAINER -->
                <div class="page-container">
                    <!-- BEGIN CONTENT -->
                    <div class="page-content-wrapper">
                        <!-- BEGIN CONTENT BODY -->
                        <!-- BEGIN PAGE HEAD-->
                        <div class="page-head">
                            <div class="container">
                                <!-- BEGIN PAGE TITLE -->

