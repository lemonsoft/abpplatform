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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="assessor.title" text="Assessor" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"><a href="<%=request.getContextPath()%>/admin/assessor/init.io" class="button btn-primary block pushed expand"> <spring:message code="common.button.add" text="Add" /> </a></div>

                </div><!-- end .form-header section -->

                <form:form method="post" action="search.io" id="theForm" commandName="assessor">
                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="ssc.title" text="Sector Skill Council" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="ssc" id="selectssc" class="gui-input" >
                                            <form:option value="0">--Select--</form:option>
                                            <form:options items="${ssc}"/>


                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            
                            <%--<div class="section colm colm3">
                                <div class="section">
                                    <label for="names" class="field-label">&nbsp;</label>
                                    <label class="field prepend-icon">
                                        <button type="submit" class="button btn-blue"><spring:message code="common.button.search" text="Search" /></button>
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->--%>

                        </div><!-- end frm-row section -->


                        <div class="frm-row">
                            <display:table name="records" class="table table-bordered" requestURI="initSearch.io" decorator="com.abp.admin.assessor.SrcDecorator" pagesize="10">
                                <display:column property="photoname" title="Photo" />
                                <display:column property="loginid" title="Username" sortable="true"/>
                                <display:column property="firstname" title="Name" />
                                <display:column property="emailid" title="Email ID" />
                                <display:column property="contactno" title="Contact No" />
                                <display:column property="state" title="State" />
                                <display:column property="district" title="District" />
                                <display:column property="pincode" title="Pincode" />
                                <display:column property="jobrole" title="Job Role" />
                                <display:column property="actions" title="Actions"/>
                            </display:table>

                        </div>
                    </form:form>

                </div><!-- end .smart-forms section -->
            </div><!-- end .smart-wrap section -->

            <script>

                $(document).ready(function () {
//
//                    $("#theForm").submit(function (e)
//                        {
//                            var postData = $(this).serializeArray();
//                            var formURL = $(this).attr("action");
//                            alert("Yes");
//                            $.ajax(
//                                    {
//                                        url: formURL,
//                                        type: "POST",
//                                        data: postData,
//                                        success: function (data, textStatus, jqXHR)
//                                        {
//                                            alert("Yes");
//                                        },
//                                        error: function (jqXHR, textStatus, errorThrown)
//                                        {
//                                            //if fails      
//                                        }
//                                    });
//                            e.preventDefault(); //STOP default action
//                            e.unbind(); //unbind. to stop multiple form submit.
//                        });
                    // process the form
                    $("#selectssc").change(function (event) {
                        
                        //alert($("#selectssc").val());
                        $("#theForm").submit().serialize(); //Submit  the FORM
                    });

                });


            </script>
    </body>

</html>
