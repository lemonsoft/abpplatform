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
                    <h4><i class="fa fa-pencil-square"></i><spring:message code="addtheorymmq.title" text="Edit Theory MMQ" /></h4>
                    <div style="position: absolute;top:5px;right:5px;width: 100px;"></div>

                </div><!-- end .form-header section action="${action}"-->

                <form:form method="post" action="${action}"   commandName="theorymmq" enctype="multipart/form-data">
                    <form:hidden path="id" />
                    <form:hidden path="qpackid" />
                    <form:hidden path="pcidwithmarks" />
                    <form:hidden path="isactive" />
                    
                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                    <div class="form-body theme-blue">
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.questionlevel" text="Question Level" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="question_level" id="question_level" class="gui-input" >
                                            <form:option value="1">Easy</form:option>
                                            <form:option value="2">Medium</form:option>
                                            <form:option value="3">Tough</form:option>
                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.questiontype" text="Question Type" /></label>
                                    <label class="field prepend-icon">

                                        <form:select path="question_type" id="question_type" class="gui-input" >
                                            <form:option value="1">MC</form:option>
                                            <form:option value="2">TF</form:option>



                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.marks" text="Marks" /></label>
                                    <label class="field prepend-icon">

                                        <form:select path="marks" id="marks" class="gui-input" >
                                            <form:option value="1">1</form:option>
                                            <form:option value="2">2</form:option>
                                            <form:option value="3">3</form:option>
                                            <form:option value="4">4</form:option>
                                            <form:option value="5">5</form:option>
                                            <form:option value="6">6</form:option>
                                            <form:option value="7">7</form:option>
                                            <form:option value="8">8</form:option>
                                            <form:option value="9">9</form:option>
                                            <form:option value="10">10</form:option>
                                            <form:option value="11">11</form:option>
                                            <form:option value="12">12</form:option>
                                            <form:option value="13">13</form:option>
                                            <form:option value="14">14</form:option>
                                            <form:option value="15">15</form:option>
                                            <form:option value="16">16</form:option>
                                            <form:option value="17">17</form:option>
                                            <form:option value="18">18</form:option>
                                            <form:option value="19">19</form:option>
                                            <form:option value="20">20</form:option>
                                            <form:option value="21">21</form:option>
                                            <form:option value="22">22</form:option>
                                            <form:option value="23">23</form:option>
                                            <form:option value="24">24</form:option>
                                            <form:option value="25">25</form:option>




                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.noofoption" text="No of Option" /></label>
                                    <label class="field prepend-icon">
                                        <form:select path="noofoption" id="noofoption" class="gui-input" >
                                            <form:option value="3">3</form:option>
                                            <form:option value="4">4</form:option>
                                            <form:option value="5">5</form:option>
                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm6"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.correctanswer" text="Correct Answer" /></label>
                                    <label class="field prepend-icon">

                                        <form:select path="correct_option" id="correct_option" class="gui-input" >
                                            <form:option value="1">A</form:option>
                                            <form:option value="2">B</form:option>
                                            <form:option value="3">C</form:option>
                                            <form:option value="4">D</form:option>



                                        </form:select>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->

                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.question" text="Question" /></label>
                                    <label class="field prepend-icon">
                                        <form:textarea path="question_title" rows="5" cols="10" class="gui-input" required="true"></form:textarea>


                                            <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->

                                <div class="section colm colm3"> 
                                    <div class="section">
                                        <label for="names" class="field-label"><spring:message code="addtheorymmq.questionimageurl" text="Question Image Url" /> :</label>
                                        <label class="field prepend-icon">

                                        <form:input path="questionimgurl" id="questionimgurl" class="gui-input"  />

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.uploadimage" text="Upload Image" /></label>
                                    <label class="field prepend-icon">

                                        <input type="file" name="files" id="uploadImage" class="gui-input" />

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm2"> 
                                <div class="section" id="showImage">
                                    <c:if test = "${mode == 'add'}">
                                        <img id="questionImg" src="<%=request.getContextPath()%>/uploaded/questions/no-image.png"  width="150" height="150"/>
                                    </c:if>
                                    <c:if test = "${mode=='update'}">
                                        <img id="questionImg" src="${imgurl0}"  width="150" height="150"/>
                                    </c:if>


                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.optiona" text="Option A" /></label>
                                    <label class="field prepend-icon">
                                        <form:textarea path="option1" rows="5" cols="10" class="gui-input" required="true"></form:textarea>


                                            <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->

                                <div class="section colm colm3"> 
                                    <div class="section">
                                        <label for="names" class="field-label"><spring:message code="addtheorymmq.optionaimgurl" text="Option A Image Url" /> :</label>
                                        <label class="field prepend-icon">

                                        <form:input path="imageurl1" id="imageurl1" class="gui-input"  />

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.uploadimage" text="Upload Image" /></label>
                                    <label class="field prepend-icon">

                                        <input type="file" name="files" id="uploadImageA" class="gui-input" />

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm2"> 
                                <div class="section" id="showImageA">
                                    <c:if test = "${mode == 'add'}">
                                        <img id="optionAImg" src="<%=request.getContextPath()%>/uploaded/questions/no-image.png"  width="150" height="150"/>
                                    </c:if>
                                    <c:if test = "${mode=='update'}">
                                        <img id="optionAImg" src="${imgurl1}"  width="150" height="150"/>
                                    </c:if>


                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.optionb" text="Option B" /></label>
                                    <label class="field prepend-icon">
                                        <form:textarea path="option2" rows="5" cols="10" class="gui-input" required="true"></form:textarea>


                                            <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->

                                <div class="section colm colm3"> 
                                    <div class="section">
                                        <label for="names" class="field-label"><spring:message code="addtheorymmq.optionbimgurl" text="Option B Image Url" /> :</label>
                                        <label class="field prepend-icon">

                                        <form:input path="imageurl2" id="imageurl2" class="gui-input"  />

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.uploadimage" text="Upload Image" /></label>
                                    <label class="field prepend-icon">

                                        <input type="file" name="files" id="uploadImageB" class="gui-input" />

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm2"> 
                                <div class="section" id="showImageB">
                                    <c:if test = "${mode == 'add'}">
                                        <img id="optionBImg" src="<%=request.getContextPath()%>/uploaded/questions/no-image.png"  width="150" height="150"/>
                                    </c:if>
                                    <c:if test = "${mode=='update'}">
                                        <img id="optionBImg" src="${imgurl2}"  width="150" height="150"/>
                                    </c:if>

                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row" id="option3">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.optionc" text="Option C" /></label>
                                    <label class="field prepend-icon">
                                        <form:textarea path="option3" rows="5" cols="10" class="gui-input" required="true"></form:textarea>


                                            <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->

                                <div class="section colm colm3"> 
                                    <div class="section">
                                        <label for="names" class="field-label"><spring:message code="addtheorymmq.optioncimgurl" text="Option C Image Url" /> :</label>
                                        <label class="field prepend-icon">

                                        <form:input path="imageurl3" id="imageurl3" class="gui-input"  />

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.uploadimage" text="Upload Image" /></label>
                                    <label class="field prepend-icon">

                                        <input type="file" name="files" id="uploadImageC" class="gui-input" />

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm2"> 
                                <div class="section" id="showImageC">

                                    <c:if test = "${mode == 'add'}">
                                        <img id="optionCImg" src="<%=request.getContextPath()%>/uploaded/questions/no-image.png"  width="150" height="150"/>
                                    </c:if>
                                    <c:if test = "${mode=='update'}">
                                        <img id="optionCImg" src="${imgurl3}"  width="150" height="150"/>
                                    </c:if>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        <div class="frm-row" id="option4">
                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.optiond" text="Option D" /></label>
                                    <label class="field prepend-icon">
                                        <form:textarea path="option4" rows="5" cols="10" class="gui-input" ></form:textarea>


                                            <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                        </label>
                                    </div><!-- end section -->
                                </div><!-- end section -->

                                <div class="section colm colm3"> 
                                    <div class="section">
                                        <label for="names" class="field-label"><spring:message code="addtheorymmq.optiondimgurl" text="Option D Image Url" /> :</label>
                                        <label class="field prepend-icon">

                                        <form:input path="imageurl4" id="imageurl4" class="gui-input"  />

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm3"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.uploadimg" text="Upload Image" /></label>
                                    <label class="field prepend-icon">

                                        <input type="file" name="files" id="uploadImageD" class="gui-input" />

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->
                            <div class="section colm colm2"> 
                                <div class="section" id="showImageD">

                                    <c:if test = "${mode == 'add'}">
                                        <img id="optionDImg" src="<%=request.getContextPath()%>/uploaded/questions/no-image.png"  width="150" height="150"/>
                                    </c:if>
                                    <c:if test = "${mode=='update'}">
                                        <img id="optionCImg" src="${imgurl4}"  width="150" height="150"/>
                                    </c:if>
                                </div><!-- end section -->
                            </div><!-- end section -->
                        </div><!-- end frm-row section -->
                        
                        <div class="frm-row">

                            <div class="section colm colm4"> 
                                <div class="section">
                                    <label for="names" class="field-label"><spring:message code="addtheorymmq.solution" text="Solution" /></label>
                                    <label class="field prepend-icon">

                                        <form:input path="solution" id="solution" class="gui-input"  required="true"/>

                                        <span class="field-icon"><i class="fa fa-location-arrow"></i></span> 
                                    </label>
                                </div><!-- end section -->
                            </div><!-- end section -->

                        </div><!-- end frm-row section -->



                    </div><!-- end .form-body section -->
                    <div class="form-footer">
                        <c:if test = "${mode == 'add'}">
                            <button type="submit" class="button btn-blue"><spring:message code="common.button.submit" text="Submit" /></button>
                        </c:if>
                        <c:if test = "${mode=='update'}">
                            <button type="submit" class="button btn-blue"><spring:message code="common.button.update" text="Update" /></button>
                        </c:if>
                        <button type="reset" class="button btn-blue"><spring:message code="common.button.cancel" text="Cancel" /></button>
                    </div><!-- end .form-footer section -->
                </form:form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->
        <script type="text/javascript"
        src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script>

            $("#uploadImage").change(function () {

                var ext = $('#uploadImage').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                    alert('invalid extension!');
                    $('#uploadImage').val('');
                }
            });
            $("#uploadImageA").change(function () {

                var ext = $('#uploadImageA').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                    alert('invalid extension!');
                    $('#uploadImageA').val('');
                }
            });
            $("#uploadImageB").change(function () {

                var ext = $('#uploadImageB').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                    alert('invalid extension!');
                    $('#uploadImageB').val('');
                }
            });
            $("#uploadImageC").change(function () {

                var ext = $('#uploadImageC').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                    alert('invalid extension!');
                    $('#uploadImageC').val('');
                }
            });
            $("#uploadImageD").change(function () {

                var ext = $('#uploadImage').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                    alert('invalid extension!');
                    $('#uploadImageD').val('');
                }
            });
            $(document).ready(function () {
                
                var val = $('#noofoption').find(":selected").text();

                if (parseInt(val) === 3) {
                    $("#option4").hide();
                    $("#option5").hide();

                }
                if (parseInt(val) === 4) {
                    $("#option4").show();
                    $("#option5").hide();
                }
                if (parseInt(val) === 5) {
                    $("#option4").show();
                    $("#option5").show();
                }
            });

            function readURL(input) {

                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    $("#showImage").show();
                    reader.onload = function (e) {
                        $('#questionImg').attr('src', e.target.result);
                    }

                    reader.readAsDataURL(input.files[0]);
                }
            }
            function readURLA(input) {

                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    $("#showImageA").show();
                    reader.onload = function (e) {
                        $('#optionAImg').attr('src', e.target.result);
                    }

                    reader.readAsDataURL(input.files[0]);
                }
            }
            function readURLB(input) {

                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    $("#showImageB").show();
                    reader.onload = function (e) {
                        $('#optionBImg').attr('src', e.target.result);
                    }

                    reader.readAsDataURL(input.files[0]);
                }
            }
            function readURLC(input) {

                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    $("#showImageC").show();
                    reader.onload = function (e) {
                        $('#optionCImg').attr('src', e.target.result);
                    }

                    reader.readAsDataURL(input.files[0]);
                }
            }
            function readURLD(input) {

                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    $("#showImageD").show();
                    reader.onload = function (e) {
                        $('#optionDImg').attr('src', e.target.result);
                    }

                    reader.readAsDataURL(input.files[0]);
                }
            }

            $("#uploadImage").change(function () {

                readURL(this);
            });
            $("#uploadImageA").change(function () {

                readURLA(this);
            });
            $("#uploadImageB").change(function () {

                readURLB(this);
            });
            $("#uploadImageC").change(function () {

                readURLC(this);
            });
            $("#uploadImageD").change(function () {

                readURLD(this);
            });
            $("#photoname").change(function () {

                var ext = $('#photoname').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                    alert('invalid extension!');
                    $('#photoname').val('');
                }
            });
            $("#aadharimage").change(function () {

                var ext = $('#aadharimage').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                    alert('invalid extension!');
                    $('#aadharimage').val('');
                }
            });
            $("#resumename").change(function () {

                var ext = $('#resumename').val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['docx', 'doc']) == -1) {
                    alert('invalid extension!');
                    $('#resumename').val('');
                }
            });
            $("#stateid").change(function () {

                var state_id = $(this).val();
                if (state_id) {

                    $.ajax({
                        url: "getDistricts.io",
                        data: {s_id: state_id},
                        type: 'GET',
                        success: function (data) {


                            var str = "";
                            if (data.length === 0) {
                                str = "<option value=''>------- Select --------</option>";
                            }
                            $.each(data, function (index, jsonObject) {
                                str = str + "<option value=" + jsonObject.ID + ">" + jsonObject.NAME + "</option>"
                               
                            });
                            $("#districtid").html(str);
                        }
                    });
                } else {
                    $("#districtid").html("<option value=''>------- Select --------</option>");
                }
            });
            $("#noofoption").change(function () {

                var val = $('#noofoption').find(":selected").text();

                if (parseInt(val) === 3) {
                    $("#option4").hide();
                    $("#option5").hide();

                }
                if (parseInt(val) === 4) {
                    $("#option4").show();
                    $("#option5").hide();
                }
                if (parseInt(val) === 5) {
                     $("#option4").show();
                    $("#option5").show();
                }
            });
        </script>
    </body>

</html>
