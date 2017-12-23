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
                                        <i class="fa fa-gift"></i>Question Wise Report </div>
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
                                            <div class="tab-pane" id="tab_5_2">
                                                <table class="table table-striped table-bordered table-hover dt-responsive"  border="1" cellpadding="2" cellspacing="2" width="100%">
                                                    <tr><td>Senario</td><td></td></tr>

                                                    <c:forEach var="senerio" items="${practicallog}">
                                                        <tr><td>${senerio.key}</td>
                                                            <td><table class="table table-bordered" border="1" cellpadding="2" cellspacing="2" width="100%">
                                                                <c:forEach var="practicallogquest" items="${senerio.value}">
                                                                    
                                                                        <tr><td>${practicallogquest.snquestion}</td>
                                                                            <td>${practicallogquest.answerstatus}</td>
                                                                            <td>${practicallogquest.actualmarks}</td>
                                                                            <td>${practicallogquest.scoredmarks}</td></tr>
                                                                    
                                                                    
                                                                </c:forEach></table>
                                                                
                                                                
                                                                
                                                               <%-- <display:table name="${senerio.value}" class="table table-bordered"  requestURI="totalreport.io"  pagesize="5">
                                                                    <display:column property="snquestion" title="Question"  style="word-break:break-all;"/>
                                                                    <display:column property="answerstatus" title="Answer Status" />
                                                                    <display:column property="actualmarks" title="Actual Marks" />
                                                                    <display:column property="scoredmarks" title="Scored Marks" />
                                                                </display:table>--%>

                                                            </td> </tr>

                                                    </c:forEach>
                                                </table>     
                                            </div>
                                            <div class="tab-pane active" id="tab_5_1">

                                                <table class="table table-bordered" cellpadding="2" cellspacing="2" border="1">

                                                    <tr><td>Exam Start Time : ${startdatetime}</td><td>Exam End Time :  ${enddatetime}</td></tr>
                                                    <tr>  <td>Total Time :  ${totaltime}</td><td>Time Taken :  ${timetaken}</td></tr>
                                                    <tr>  <td>IP Address :  ${ipaddress}</td><td>Browser Version : ${browser}</td></tr>

                                                </table>

                                                <display:table name="datatheorywise" class="table table-bordered" requestURI="totalreport.io"  pagesize="50">
                                                    <display:column property="sno" title="Sno" />
                                                    <display:column property="question" title="Question"/>
                                                    <display:column property="option1" title="Option1"/>
                                                    <display:column property="option2" title="Option2"/>
                                                    <display:column property="option3" title="Option3"/>
                                                    <display:column property="option4" title="Option4"/>
                                                    <display:column property="correctanswer" title="Correctanswer"/>
                                                    <display:column property="selectedanswer" title="Selectedanswer"/>
                                                    <display:column property="marks" title="Marks"/>
                                                    <display:column property="scoredmarks" title="Scoredmarks"/>
                                                    <display:column property="timetaken" title="Timetaken"/>
                                                    <display:column property="reviewlater" title="Reviewlater"/>
                                                </display:table>
                                                <table class="table table-bordered" cellpadding="2" cellspacing="2" border="1">
                                                    <tr><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td ><b>Total :</b> ${totalmarks}</td><td ><b>Scored :</b>${totaltheorymarks}</td><td >&nbsp;</td><td >&nbsp;</td></tr>
                                                </table>
                                            </div>
                                            
                                            <div class="tab-pane" id="tab_5_3">
                                                <display:table name="asseslogdao" class="table table-bordered" requestURI="totalreport.io"  pagesize="50">
                                                    <display:column property="sno" title="Sno" />
                                                    <display:column property="datetime" title="Datetime"/>
                                                    <display:column property="action" title="Action"/>

                                                </display:table>
                                            </div>
                                            <div class="tab-pane" id="tab_5_4">
                                                <display:table name="questlogdao" class="table table-bordered" requestURI="totalreport.io"  pagesize="50">
                                                    <display:column property="sno" title="Sno" />
                                                    <display:column property="questionno" title="Question No"/>
                                                    <display:column property="startdate" title="Start Date"/>
                                                    <display:column property="enddate" title="End Date"/>
                                                    <display:column property="timetaken" title="Time Taken (Sec)"/>

                                                </display:table>
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
