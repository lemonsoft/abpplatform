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

        <script src="<%=request.getContextPath()%>/assets/global/plugins/bootstrap-tabdrop/js/bootstrap-tabdrop.js" type="text/javascript"></script>
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
                    <h4><i class="fa fa-pencil-square"></i>Question Wise Report</h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="${action}"  commandName="result" id="submitForm">

                    <div class="form-body theme-blue">


                        <div class="frm-row">
                            <div class="portlet box blue">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-gift"></i>Styled Tabs #2 </div>
                                    <div class="tools">
                                        <a href="javascript:;" class="collapse"> </a>
                                        <a href="#portlet-config" data-toggle="modal" class="config"> </a>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="tabbable-custom ">
                                        <ul class="nav nav-tabs ">
                                            <li class="active">
                                                <a href="#tab_5_1" data-toggle="tab"> Theory Wise </a>
                                            </li>
                                            <li>
                                                <a href="#tab_5_2" data-toggle="tab"> Practical Wise </a>
                                            </li>
                                            <li>
                                                <a href="#tab_5_3" data-toggle="tab"> Assesment Log Details </a>
                                            </li>
                                            <li>
                                                <a href="#tab_5_4" data-toggle="tab"> Questionwise Log Details </a>
                                            </li>
                                            <li>
                                                <a href="#tab_5_5" data-toggle="tab"> Image Log Details </a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="tab_5_1">
                                                <display:table name="datatheorywise" class="table table-bordered" requestURI="initSearch.io"  pagesize="50">
                                                    <display:column property="sno" title="Sno" />
                                                    <display:column property="question" title="Question"/>
                                                    <display:column property="option1" title="option1"/>
                                                    <display:column property="option2" title="option2"/>
                                                    <display:column property="option3" title="option3"/>
                                                    <display:column property="option4" title="option4"/>
                                                    <display:column property="correctanswer" title="correctanswer"/>
                                                    <display:column property="selectedanswer" title="selectedanswer"/>
                                                    <display:column property="marks" title="marks"/>
                                                    <display:column property="scoredmarks" title="scoredmarks"/>
                                                    <display:column property="timetaken" title="timetaken"/>
                                                    <display:column property="reviewlater" title="reviewlater"/>
                                                </display:table>
                                            </div>
                                            <div class="tab-pane" id="tab_5_2">
                                                <p> Howdy, I'm in Section 2. </p>
                                                <p> Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate
                                                    velit esse molestie consequat. Ut wisi enim ad minim veniam, quis nostrud exerci tation. </p>
                                                <p>
                                                    <a class="btn green" href="ui_tabs_accordions_navs.html#tab_5_2" target="_blank"> Activate this tab via URL </a>
                                                </p>
                                            </div>
                                            <div class="tab-pane" id="tab_5_3">
                                                <p> Howdy, I'm in Section 3. </p>
                                                <p> Duis autem vel eum iriure dolor in hendrerit in vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.
                                                    Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat </p>
                                                <p>
                                                    <a class="btn yellow" href="ui_tabs_accordions_navs.html#tab_5_3" target="_blank"> Activate this tab via URL </a>
                                                </p>
                                            </div>
                                            <div class="tab-pane" id="tab_5_4">
                                                <p> Howdy, I'm in Section 3. </p>
                                                <p> Duis autem vel eum iriure dolor in hendrerit in vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.
                                                    Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat </p>
                                                <p>
                                                    <a class="btn yellow" href="ui_tabs_accordions_navs.html#tab_5_3" target="_blank"> Activate this tab via URL </a>
                                                </p>
                                            </div>
                                            <div class="tab-pane" id="tab_5_5">
                                                <p> Howdy, I'm in Section 3. </p>
                                                <p> Duis autem vel eum iriure dolor in hendrerit in vulputate. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.
                                                    Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat </p>
                                                <p>
                                                    <a class="btn yellow" href="ui_tabs_accordions_navs.html#tab_5_3" target="_blank"> Activate this tab via URL </a>
                                                </p>
                                            </div>
                                        </div>
                                    </div>

                                </div><!-- end frm-row section -->



                            </div><!-- end .form-body section -->
                        </div><!-- end .smart-forms section -->
                    </div><!-- end .smart-wrap section -->
                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
    </body>

</html>
