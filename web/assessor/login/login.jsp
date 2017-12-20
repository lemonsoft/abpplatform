<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title> ABP -Student Login </title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/smart-forms.css">    
        <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/assets/css/smart-themes/purple.css"> 
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/jquery-1.9.1.min.js"></script>    
        <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/jquery.placeholder.min.js"></script>

        <!--[if lte IE 9]>
            <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>    
            <script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
        <![endif]-->    

        <!--[if lte IE 8]>
            <link type="text/css" rel="stylesheet" href="css/smart-forms-ie8.css">
        <![endif]-->

    </head>

    <body class="darkbg">

        <div class="smart-wrap">
            <div class="smart-forms smart-container wrap-2">

                <div class="form-header header-purple">
                    <h4><i class="fa fa-sign-in"></i>Assessor Login</h4>
                </div><!-- end .form-header section -->
                <center><c:if test="${not empty error}">
                    <div class="error" style="color:red;">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg" style="color:green;">${msg}</div>
                </c:if></center>


                <form method="post" action="logincheck.io" id="contact">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />
                    <div class="form-body theme-purple">

                        <div class="frm-row"> 

                            <div class="colm colm6 pad-r30 bdr">

                                <div class="spacer-b30">
                                    <div class="tagline"><span>Sign in  With </span></div><!-- .tagline -->
                                </div>                 

                                <div class="section">

                                    <img src="<%=request.getContextPath()%>/assets/images/IASSESS_logo.jpg" width="250" height="150" alt="tag logo"/>

                                </div><!-- end section -->

                            </div><!-- end .colm section -->

                            <div class="colm colm6 pad-l30">                    

                                <div class="spacer-b30">

                                    <div class="tagline"><span> OR  Login </span></div><!-- .tagline -->
                                </div>

                                <div class="section">
                                    <label class="field prepend-icon">
                                        <input type="text" name="username" id="username" class="gui-input" placeholder="Enter username">
                                        <span class="field-icon"><i class="fa fa-user"></i></span>  
                                    </label>
                                </div><!-- end section -->                    

                                <div class="section">
                                    <label class="field prepend-icon">
                                        <input type="text" name="password" id="password" class="gui-input" placeholder="Enter password">
                                        <span class="field-icon"><i class="fa fa-lock"></i></span>  
                                    </label>
                                </div><!-- end section -->  



                            </div><!-- end .colm section -->

                        </div><!-- end .frm-row section -->                                                            

                    </div><!-- end .form-body section -->
                    <div class="form-footer">
                        <button type="submit" class="button btn-purple">Sign in</button>
                    </div><!-- end .form-footer section -->
                </form>

            </div><!-- end .smart-forms section -->
        </div><!-- end .smart-wrap section -->

        <div></div><!-- end section -->

    </body>
</html>
