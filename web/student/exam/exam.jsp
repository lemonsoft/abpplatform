<%-- 
    Document   : dashboard
    Created on : 7 Dec, 2017, 1:07:03 PM
    Author     : ss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/smart-forms.css">
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/smart-themes/blue.css">
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets/js/jQuery.countdownTimer.css" />

    <!--[if lte IE 9]>
        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>    
        <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
    <![endif]-->    

    <!--[if lte IE 8]>
        <link type="text/css" rel="stylesheet" href="css/smart-forms-ie8.css">
    <![endif]-->
    <style>
        .square {
            float: left;
            width: 30px;
            height: 30px;
            margin: 5px;
            border: 1px solid rgba(0, 0, 0, .2);
            color: #ffffff;
            text-align: center;
        }

        .red {
            background: #f22;
        }

        .green {
            background: #5cb85c;
        }

        .yellow {
            background: #FBDE2D;
        }
    </style>
</head>
<div class="smart-wrap">
    <div class="smart-forms smart-container wrap-full">
        <div class="form-body theme-blue">
            <div class="frm-row">
                <div class="section colm colm12"> 
                    <!--            <div class="col-md-6 ">-->
                    <!-- BEGIN Portlet PORTLET-->
                    <div class="portlet box green">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-calendar-times-o"></i>Maximum Time(HH:MM:SS) :<span id="maxtime">${maxtime}</span>  &nbsp;</div>
                            <div class="caption">
                                &nbsp;<i class="fa fa-calendar-plus-o"></i>Remaining Time(HH:MM:SS) :<span id="ms_timer"></span> &nbsp;</div>

                            <div class="caption">
                                &nbsp;<i class="fa fa-calculator"></i>Total Question : ${totalquestion}</div>
                        </div>

                    </div>
                    <!-- END Portlet PORTLET-->

                    <!--            </div> end section -->
                </div><!-- end section -->
            </div>
            <div class="frm-row">
                <div class="section colm colm3"> 
                    <!--            <div class="col-md-6 ">-->
                    <!-- BEGIN Portlet PORTLET-->
                    <div class="portlet box blue-hoki">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Test Monitor</div>

                        </div>
                        <div class="portlet-body">
                            <div class="scroller" style="height:350px" data-rail-visible="1" data-rail-color="yellow" data-handle-color="#a1b2bd">
                                <div class="frm-row" >
                                    <div class="section colm colm3" > <div class="square red"></div>Not Answered</div>
                                    <div class="section colm colm4" ><div class="square green"></div>Answered</div>
                                    <div class="section colm colm4" ><div class="square yellow"></div>Review Later</div>

                                </div>

                                <div class="frm-row" id="questions">
                                    <c:forEach begin="1" end="${totalquestion}" varStatus="loop">
                                        <div class="square red" style="display: inline-block;" id="${loop.index}"> ${loop.index}</div>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- END Portlet PORTLET-->

                    <!--            </div> end section -->
                </div><!-- end section -->
                <div class="section colm colm9"> 
                    <!--            <div class="col-md-9">-->
                    <!-- BEGIN Portlet PORTLET-->
                    <div class="portlet box blue-hoki">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-gift"></i>Q.<span id="qno">1</span> Timer <span id="timer">1</span> </div>

                        </div>

                        <div class="portlet-body" id="qpanel">
                            <div class="scroller" style="height:350px" data-rail-visible="1" data-rail-color="yellow" data-handle-color="#a1b2bd">
                                <div class="frm-row">
                                    <div class="section colm colm12">
                                        <div class="section" id="question"></div>
                                    </div> 
                                </div>
                                <div class="frm-row">
                                    <div class="section colm colm3"> 
                                        <div class="section">
                                            <label for="names" class="field-label" ><span id="option1"></span>&nbsp;<span id="optlbl1"> </span></label>
                                            <label class="field prepend-icon">
                                                <span id="option1img">&nbsp;</span>

                                            </label>
                                        </div><!-- end section -->
                                    </div><!-- end section -->
                                    <div class="section colm colm3"> 
                                        <div class="section">
                                            <label for="names" class="field-label"><span id="option2"> </span> &nbsp;<span id="optlbl2"> </span></label>
                                            <label class="field prepend-icon">
                                                <span id="option2img">&nbsp;</span> 
                                            </label>
                                        </div><!-- end section -->
                                    </div><!-- end section -->
                                    <div class="section colm colm3"> 
                                        <div class="section">
                                            <label for="names" class="field-label" ><span id="option3"> </span>&nbsp;<span id="optlbl3"> </span></label>
                                            <label class="field prepend-icon">
                                                <span id="option3img">&nbsp;</span>

                                            </label>
                                        </div><!-- end section -->
                                    </div><!-- end section -->
                                    <div class="section colm colm3"> 
                                        <div class="section">
                                            <label for="names" class="field-label" ><span id="option4"> </span> &nbsp;<span id="optlbl4"> </span></label>
                                            <label class="field prepend-icon">
                                                <span id="option4img">&nbsp;</span>

                                            </label>
                                        </div><!-- end section -->
                                    </div><!-- end section -->

                                </div>
                                <div class="frm-row">
                                    <div class="section colm colm2"> 
                                        <button type="button"  id="savenext" class="button btn-blue btn-primary block pushed expand">Save & Next</button>
                                    </div><!-- end section -->
                                    <div class="section colm colm2"> 
                                        <button type="button" id="reviewlater"  class="button btn-primary block pushed expand">Review Later</button>
                                    </div><!-- end section -->
                                </div>
                                <center><span id="finish"></span></center>
                                <center><span id="finishbtn"></span></center>

                            </div>
                        </div>

                    </div>
                    <!-- END Portlet PORTLET-->

                    <!--            </div> end section -->
                </div><!-- end section -->
            </div>
            <script>
                $(document).ready(function () {
                    loadQuestionAnswered();
                    loadFirstQuestion();
                    updatecountter();
                    function loadQuestionAnswered() {
                        var total =${totalquestion};
                        //alert(total);
                        $.ajax({
                            url: "loadQuestionAnswered.io",
                            type: 'GET',
                            success: function (data) {
                                //alert(data.Qno.length);
                                for (var i = 0; i < data.Qno.length; i++) {
                                    //alert(data.Qno[i]);
                                    $('#' + data.Qno[i]).attr("class", "square " + data.color[i]);

                                }
                            }
                        });


                    }
                    function loadFirstQuestion() {
                        var qno = 0;

                        $.ajax({
                            url: "firstQuestion.io",
                            data: {questionno: qno},
                            type: 'GET',
                            success: function (data) {
                                //alert(data);
                                timecountter(0);
                                $('#question').text(data.question);
                                $('#optlbl1').text(data.option1);
                                if ("" + data.selected == "" + data.optionno1) {

                                    $('#option1').html('<input type="radio" name="answer"  value="' + data.optionno1 + '" checked="true">');
                                } else {
                                    $('#option1').html('<input type="radio" name="answer"  value="' + data.optionno1 + '">');
                                }
                                if (data.option1imgurl != 'no-image.png') {
                                    $('#option1img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option1imgurl + '" width="100px" height="100px"/>');
                                } else {
                                    $('#option1img').html('');
                                }


                                $('#optlbl2').text(data.option2);
                                if ("" + data.selected == "" + data.optionno2) {
                                    $('#option2').html('<input type="radio" name="answer"  value="' + data.optionno2 + '" checked>');
                                } else {
                                    $('#option2').html('<input type="radio" name="answer"  value="' + data.optionno2 + '">');
                                }
                                if (data.option2imgurl != 'no-image.png') {
                                    $('#option2img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option2imgurl + '" width="100px" height="100px"/>');
                                } else {
                                    $('#option2img').html('');
                                }



                                $('#optlbl3').text(data.option3);
                                if ("" + data.selected == "" + data.optionno3) {
                                    $('#option3').html('<input type="radio" name="answer"  value="' + data.optionno3 + '" checked>');
                                } else {
                                    $('#option3').html('<input type="radio" name="answer"  value="' + data.optionno3 + '">');
                                }
                                if (data.option3imgurl != 'no-image.png') {
                                    $('#option3img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option3imgurl + '" width="100px" height="100px"/>');
                                } else {
                                    $('#option3img').html('');
                                }



                                $('#optlbl4').text(data.option4);
                                if ("" + data.selected == "" + data.optionno4) {
                                    $('#option4').html('<input type="radio" name="answer"  value="' + data.optionno4 + '" checked>');
                                } else {
                                    $('#option4').html('<input type="radio" name="answer"  value="' + data.optionno4 + '">');
                                }

                                if (data.option4imgurl != 'no-image.png') {
                                    $('#option4img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option4imgurl + '" width="100px" height="100px"/>');
                                } else {
                                    $('#option4img').html('');
                                }


                                var nextno = parseInt(qno) + 1;
                                $('#qno').text(nextno);
                                $('#' + qno).attr("class", "square green");

                            }
                        });


                    }

                    $("#questions").delegate("div", "click", function () {
                        //alert($(this).text().trim());
                        var url = "";

                        var color = $(this).attr("class");
                        if (color == 'square red') {
                            url = "loadQuestionByNo.io";
                            $('#reviewlater').attr('disabled', false);
                        }
                        if (color == 'square green') {
                            url = "loadQuestionResult.io";
                            $('#reviewlater').attr('disabled', true);
                        }
                        if (color == 'square yellow') {
                            url = "loadQuestionResult.io";
                            $('#reviewlater').attr('disabled', true);

                        }
                        var qno = $(this).text().trim();

                        $.ajax({
                            url: url,
                            data: {questionno: qno},
                            type: 'GET',
                            success: function (data) {
                                if (data.status == 'finish') {
                                    var nextno = parseInt(qno) + 1;
                                    $('#' + qno).attr("class", "square green");
                                    $('#finish').html("<h4 style=\"color:green;\"><b>Question finished.Exam Completed</b></h4>");
                                    $('#finishbtn').html("<button type=\"button\" onclick=\"finishexam();\"  class=\"button btn-primary block pushed expand\">Finish Exam</button>");
                                }
                                timecountter(0);
                                $('#question').text(data.question);
                                $('#optlbl1').text(data.option1);

                                if ("" + data.selected == "" + data.optionno1) {

                                    $('#option1').html('<input type="radio" name="answer"  value="' + data.optionno1 + '" checked="true">');
                                } else {
                                    $('#option1').html('<input type="radio" name="answer"  value="' + data.optionno1 + '">');
                                }

                                if (data.option1imgurl != 'no-image.png') {
                                    $('#option1img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option1imgurl + '" width="100px" height="100px"/>');
                                } else {
                                    $('#option1img').html('');
                                }




                                $('#optlbl2').text(data.option2);

                                if ("" + data.selected == "" + data.optionno2) {
                                    $('#option2').html('<input type="radio" name="answer"  value="' + data.optionno2 + '" checked>');
                                } else {
                                    $('#option2').html('<input type="radio" name="answer"  value="' + data.optionno2 + '">');
                                }
                                if (data.option2imgurl != 'no-image.png') {
                                    $('#option2img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option2imgurl + '" width="100px" height="100px"/>');
                                } else {
                                    $('#option2img').html('');
                                }



                                $('#optlbl3').text(data.option3);

                                if ("" + data.selected == "" + data.optionno3) {
                                    $('#option3').html('<input type="radio" name="answer"  value="' + data.optionno3 + '" checked>');
                                } else {
                                    $('#option3').html('<input type="radio" name="answer"  value="' + data.optionno3 + '">');
                                }
                                if (data.option3imgurl != 'no-image.png') {
                                    $('#option3img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option3imgurl + '" width="100px" height="100px"/>');
                                } else {
                                    $('#option3img').html('');
                                }



                                $('#optlbl4').text(data.option4);

                                if ("" + data.selected == "" + data.optionno4) {
                                    $('#option4').html('<input type="radio" name="answer"  value="' + data.optionno4 + '" checked>');
                                } else {
                                    $('#option4').html('<input type="radio" name="answer"  value="' + data.optionno4 + '">');
                                }
                                if (data.option4imgurl != 'no-image.png') {
                                    $('#option4img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option4imgurl + '" width="100px" height="100px"/>');
                                } else {
                                    $('#option4img').html('');
                                }


                                $('#qno').text(qno);
                                //$('#' + qno).attr("class", "square green");


                            }
                        });

                    });

                    $(this).bind("contextmenu", function (e) {
                        e.preventDefault();
                    });
//                    var start = new Date;
//
//                    setInterval(function () {
//                        $('#timer').text((new Date - start) / 1000 + " Minute");
//                    }, 1000);


                });
                var myInterval;
                function timecountter(count) {
                    clearInterval(myInterval);
                    var counter = count;
                    myInterval = setInterval(function () {
                        ++counter;
                        $('#timer').text(counter);
                    }, 1000);
                }
                var updateInterval;
                function updatecountter() {
                    updateInterval = setInterval("updateTimeTaken()", 60000);
                }
                function updateTimeTaken() {
                    var currenttime = $("#ms_timer").text();

                    if (currenttime != "00:00") {
                        $.ajax({
                            url: "updateTimeTaken.io",
                            data: {timetaken: currenttime},
                            type: 'GET',
                            success: function (data) {
                            }
                        });
                    } else {

                        $.ajax({
                            url: "updateTimeTaken.io",
                            data: {timetaken: currenttime},
                            type: 'GET',
                            success: function (data) {
                                window.location.href = "<%=request.getContextPath()%>/student/login/finishexam.io";
                            }
                        });

                        //window.location.href = "<%=request.getContextPath()%>/student/login/finishexam.io";
                    }


                }
                function gotoInstruction() {

                    window.location.href = "<%=request.getContextPath()%>/student/login/examinstruction.io";

                }

                $("#savenext").click(function () {

                    var qno = $('#qno').text();
                    //alert(qno);
                    var answer = $("input[name='answer']:checked").val();
                    //alert(answer);
                    var timetaken = $('#timer').text();
                    //alert(timetaken);

                    if (answer) {

                        $.ajax({
                            url: "loadQuestion.io",
                            data: {questionno: qno, answer: answer, timetaken: timetaken},
                            type: 'GET',
                            success: function (data) {

                                if (data.status == 'finish') {

                                    var nextno = parseInt(qno) + 1;
                                    $('#' + qno).attr("class", "square green");
                                    $('#finish').html("<h4 style=\"color:green;\"><b>Question finished.Exam Completed</b></h4>");
                                    $('#finishbtn').html("<button type=\"button\" onclick=\"finishexam();\"  class=\"button btn-primary block pushed expand\">Finish Exam</button>");

                                } else if ("" + data.status == 'remains') {

                                    $('#' + qno).attr("class", "square green");
                                    $('#finish').html("<h4 style=\"color:green;\"><b>Question finished.Some Question Remain Unanswered.please Check.</b></h4>");

                                } else {

                                    timecountter(0);
                                    $('#question').text(data.question);
                                    $('#optlbl1').text(data.option1);
                                    if ("" + data.selected == "" + data.optionno1) {

                                        $('#option1').html('<input type="radio" name="answer"  value="' + data.optionno1 + '" checked="true">');
                                    } else {
                                        $('#option1').html('<input type="radio" name="answer"  value="' + data.optionno1 + '">');
                                    }
                                    if (data.option1imgurl != 'no-image.png') {
                                        $('#option1img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option1imgurl + '" width="100px" height="100px"/>');
                                    } else {
                                        $('#option1img').html('');
                                    }


                                    $('#optlbl2').text(data.option2);
                                    if ("" + data.selected == "" + data.optionno2) {
                                        $('#option2').html('<input type="radio" name="answer"  value="' + data.optionno2 + '" checked>');
                                    } else {
                                        $('#option2').html('<input type="radio" name="answer"  value="' + data.optionno2 + '">');
                                    }
                                    if (data.option2imgurl != 'no-image.png') {
                                        $('#option2img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option2imgurl + '" width="100px" height="100px"/>');
                                    } else {
                                        $('#option2img').html('');
                                    }



                                    $('#optlbl3').text(data.option3);
                                    if ("" + data.selected == "" + data.optionno3) {
                                        $('#option3').html('<input type="radio" name="answer"  value="' + data.optionno3 + '" checked>');
                                    } else {
                                        $('#option3').html('<input type="radio" name="answer"  value="' + data.optionno3 + '">');
                                    }
                                    if (data.option3imgurl != 'no-image.png') {
                                        $('#option3img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option3imgurl + '" width="100px" height="100px"/>');
                                    } else {
                                        $('#option3img').html('');
                                    }



                                    $('#optlbl4').text(data.option4);
                                    if ("" + data.selected == "" + data.optionno4) {
                                        $('#option4').html('<input type="radio" name="answer"  value="' + data.optionno4 + '" checked>');
                                    } else {
                                        $('#option4').html('<input type="radio" name="answer"  value="' + data.optionno4 + '">');
                                    }

                                    if (data.option4imgurl != 'no-image.png') {
                                        $('#option4img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option4imgurl + '" width="100px" height="100px"/>');
                                    } else {
                                        $('#option4img').html('');
                                    }


                                    var nextno = parseInt(qno) + 1;
                                    $('#qno').text(nextno);
                                    $('#' + qno).attr("class", "square green");

                                }

                            }
                        });
                    } else {
                        alert("Please Select Answer");
                    }
                });

                $("#reviewlater").click(function () {

                    var qno = $('#qno').text();
                    //alert(qno);
                    var answer = $("input[name='answer']:checked").val();
                    //alert(answer);
                    var timetaken = $('#timer').text();
                    //alert(timetaken);

                    if (answer) {

                        $.ajax({
                            url: "reviewQuestion.io",
                            data: {questionno: qno, answer: answer, timetaken: timetaken},
                            type: 'GET',
                            success: function (data) {
                                if (data.status == 'finish') {
                                    var nextno = parseInt(qno) + 1;
                                    $('#' + qno).attr("class", "square green");
                                    $('#finish').html("<h4 style=\"color:green;\"><b>Question finished.Exam Completed</b></h4>");
                                } else if (data.status == 'remains') {

                                    $('#' + qno).attr("class", "square green");
                                    $('#finish').html("<h4 style=\"color:green;\"><b>Question finished.Some Question Remain Unanswered.please Check.</b></h4>");

                                } else {
                                    timecountter(0);
                                    $('#question').text(data.question);
                                    $('#optlbl1').text(data.option1);
                                    $('#option1').html('<input type="radio" name="answer"  value="' + data.optionno1 + '">');
                                    if (data.option1imgurl != 'no-image.png') {
                                        $('#option1img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option1imgurl + '" width="100px" height="100px"/>');
                                    } else {
                                        $('#option1img').html('');
                                    }




                                    $('#optlbl2').text(data.option2);
                                    $('#option2').html('<input type="radio" name="answer"  value="' + data.optionno2 + '">');
                                    if (data.option2imgurl != 'no-image.png') {
                                        $('#option2img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option2imgurl + '" width="100px" height="100px"/>');
                                    } else {
                                        $('#option2img').html('');
                                    }



                                    $('#optlbl3').text(data.option3);
                                    $('#option3').html('<input type="radio" name="answer"  value="' + data.optionno3 + '">');
                                    if (data.option3imgurl != 'no-image.png') {
                                        $('#option3img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option3imgurl + '" width="100px" height="100px"/>');
                                    } else {
                                        $('#option3img').html('');
                                    }



                                    $('#optlbl4').text(data.option4);
                                    $('#option4').html('<input type="radio" name="answer"  value="' + data.optionno4 + '">');

                                    if (data.option4imgurl != 'no-image.png') {
                                        $('#option4img').html('<img src="<%=request.getContextPath()%>/uploaded/questions/' + data.option4imgurl + '" width="100px" height="100px"/>');
                                    } else {
                                        $('#option4img').html('');
                                    }


                                    var nextno = parseInt(qno) + 1;
                                    $('#qno').text(nextno);
                                    $('#' + qno).attr("class", "square yellow");

                                }

                            }
                        });
                    } else {
                        alert("Please Select Answer");
                    }
                });
                function finishexam() {
                    var currenttime = $("#ms_timer").text();
                    $.ajax({
                        url: "updateTimeTaken.io",
                        data: {timetaken: currenttime},
                        type: 'GET',
                        success: function (data) {
                        }
                    });
                    window.location.href = "<%=request.getContextPath()%>/student/login/finishexam.io";
                }
//                $('#finishexam').on('click', function (event) {
//                    alert("click finish");
//                    window.location.href = "<%=request.getContextPath()%>/student/login/finishexam.io";
//                });

            </script>  
            <script type="text/javascript">

                $(function () {
                    var min = 60;
                    var sec = 00;
                    $.ajax({
                        url: "getRemainingTime.io",
                        type: 'GET',
                        success: function (data) {
                            min = data.min;
                            sec = data.sec;

                            $("#ms_timer").countdowntimer({
                                minutes: min,
                                seconds: sec,
                                size: "lg"
                            });
                        }
                    });


                });
            </script>


        </div><!-- end .form-body section -->
    </div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/scripts/jquery.countdownTimer.js"></script>                   
