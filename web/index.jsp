<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>NC_L2</title>

    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="js/bootstrap.js">
    <link rel="stylesheet" href="js/bootstrap.min.js">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">

</head>

<body>

<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");%>

<style>
    body {
        background-image: url(fonts/1.jpg);
        -moz-background-size: 100%; /* Firefox 3.6+ */
        -webkit-background-size: 100%; /* Safari 3.1+ и Chrome 4.0+ */
        -o-background-size: 100%; /* Opera 9.6+ */
        background-size: 100%; /* Современные браузеры */
    }
</style>

    <nav class="navbar navbar-inverse navbar-static-top" role="navigation">
        <div class ="container-fluid">
            <div class = "navbar-header maintitle">
                <button type = "button" class = "navbar-toggle">
                    <span class = "sc-only">Toggle navigation</span>
                    <span class = "icon-bar"></span>
                    <span class = "icon-bar"></span>
                    <span class = "icon-bar"></span>
                </button>
                <a class = "navbar-brand active" href = "index.jsp">NC_L2_Company</a>

            </div>

            <div class = "collapse navbar-collapse">
                <ul class = "nav navbar-nav">
                    <li class="secondtitle">
                        <a  id = "pointer1" href="javascript:onoff('ProjectInfo');" style = "pointer-events: none;">Projects</a>
                    </li>
                </ul>

            </div>
        </div>
    </nav>

    <script type="text/javascript">
        function onoff(t) {
            p=document.getElementById(t);
            if(p.style.display=="none"){
                p.style.display="block";
            }else{
                p.style.display="none";
            }
        };

        function setPointer(t) {
            p=document.getElementById(t);
            if(p.style.pointerEvents = "none"){
                p.style.pointerEvents="auto";
            }else{
                p.style.pointerEvents="none";
            }
        };

    </script>


    <%--<div class = "col-xs-6 col-md-2"></div>--%>
    <%--<div class = "col-xs-6 col-md-4" >--%>
        <%--<p>Project creation service</p>--%>
    <%--</div>--%>
    <%--<div class = "col-xs-6 col-md-6"></div>--%>

    <div id = "Authorization" style = "display: block;">
        <div class = "col-xs-6 col-md-4"></div>
        <div class = "col-xs-6 col-md-4" >
            <form name = "AuthorizationServlet" action = "/AuthorizationServlet" method="post">

                <div class = "row">
                        <div class="form-group">
                            <div class = "input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                    <input id = "text_5" type="email" class="form-control text"  placeholder="Login" name = "Login"  title = "" required="" value="" maxlength = "30"/>
                                <span class="glyphicon form-control-feedback"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class = "input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                    <input id = "text_6" type="password" class="form-control text"  placeholder="Password" name = "Password"  title = "" required="" value="" maxlength = "30"/>
                                <span class="glyphicon form-control-feedback"></span>
                            </div>
                        </div>
                </div>

                <div class="form-group">
                    <div class = "col-xs-6 col-md-4">
                        <div id = "button1">
                            <button type="submit" class="btn btn-labeled btn-primary" id = "authorize" style = "display: block">
                                <span class="btn-label"><i class="glyphicon glyphicon-ok"></i></span>Log in
                            </button>
                        </div>
                    </div>

                    <div class = "col-xs-6 col-md-4">
                        <div id = "button2">
                            <a href="javascript:onoff('Registration');onoff('Authorization');">
                                <button type="button" class="btn btn-labeled btn-primary" style = "display: block">
                                    <span class="btn-label"><i class="glyphicon glyphicon-registration-mark"></i></span>Registration
                                </button>
                            </a>
                        </div>
                    </div>
                </div>

            </form>
        </div>
        <div class = "col-xs-6 col-md-4"></div>
    </div>




    <div id = "Registration" style = "display: none;">
        <div class = "col-xs-6 col-md-2"></div>
        <div class = "col-xs-6 col-md-8">
            <form name = "registration" action = "/RegistrationServlet" method="post">
                <div class ="panel panel-default">

                    <div class="panel-heading">
                        <h3 class="panel-title">Registration</h3>
                    </div>

                    <div class = "panel-body">

                        <div class = "row">

                            <div class ="panel-group">
                                <div class = "panel-body">
                                    <div class="form-group">
                                        <div class = "input-group">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                            <input type="text" class="form-control text"  placeholder="Fist name" name = "Name" pattern="^[A-Z]{1}[a-z]{1,10}(-[A-Z]{1}[a-z]{1,10})?" title = "Формат Ooo або Ooo-Ooo. Варіант англійською." required="" value="" maxlength = "30"/>
                                            <span class="glyphicon form-control-feedback"></span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                            <input type="text" class="form-control" placeholder="Second name" name = "Secondname" pattern="^[A-Z]{1}[a-z]{1,10}(-[A-Z]{1}[a-z]{1,10})?" title = "Формат Ooo або Ooo-Ooo. Варіант англійською." required="" value="" maxlength = "30"/>
                                            <span class="glyphicon form-control-feedback"></span>
                                        </div>
                                    </div>
                                </div>


                                <div class = "panel-body">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                            <input type="email" class="form-control" placeholder="Login" name = "Login" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required="" value="" maxlength = "30"/>
                                            <span class="glyphicon form-control-feedback"></span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                            <input type="password" class="form-control" placeholder="Password" name="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required="" value="" maxlength = "30" title = "Пароль має мітити букви у верхньому та нижньому регістрі, цифри, а також довжину не менше 8-ми символів."/>
                                            <span class="glyphicon form-control-feedback"></span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                            <input type="text" class="form-control" placeholder="Phone number" name="Phone_number" pattern="\([0-9]{3}\)\s[0-9]{3}-[0-9]{2}-[0-9]{2}" required="" value="" maxlength = "30" title = "Please write (XXX) XXX-XX-XX"/>
                                            <span class="glyphicon form-control-feedback"></span>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                                    <select class="form-control" name = "Status">
                                                        <option value="user">customer</option>
                                                        <option value="admin">admin</option>
                                                    </select>
                                            <span class="glyphicon form-control-feedback"></span>
                                        </div>
                                    </div>

                                    <div class="form-group b2">
                                        <a href="javascript:onoff('Registration'); onoff('Authorization');">
                                            <button type="button" class="btn btn-labeled btn-primary">
                                                <span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>Close
                                            </button>
                                        </a>
                                        <a href="#">
                                            <button type="submit" class="btn btn-labeled btn-primary" id = "save">
                                                <span class="btn-label"><i class="glyphicon glyphicon-registration-mark"></i></span>Create the profile
                                            </button>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        <div class = "col-xs-6 col-md-2"></div>
    </div>
    </div>

    <div id = "LogOut" style = "display: none;">
        <div class = "row">
            <div class = "col-xs-6 col-md-9"></div>
            <div class = "col-xs-6 col-md-1">
                <form name = "LogOutServlet" action = "/LogOutServlet" method="post">
                    <button type="submit" class="btn btn-labeled btn-primary">
                        <span class="btn-label"><i class="glyphicon glyphicon-log-out"></i></span>Log out
                    </button>
                </form>
            </div>
        </div>
    </div>

    <div id = "ProjectInfo" style = "display: none;">
        <form name = "ProjectInfoServlet" action = "/ProjectInfoServlet" method="post">
            <div class = "row">
                <div class = "col-xs-6 col-md-4 "></div>
                <div class = "col-xs-6 col-md-4 ">
                    <div class ="panel panel-default">

                        <div class="panel-heading">
                            <h3 class="panel-title">Project info</h3>
                        </div>

                        <div class = "panel-body">

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                        <input id = "text_7" type="text" class="form-control text"  placeholder="Project number" name = "Project_number"  title = "" required="" value="" maxlength = "30"/>
                                    <span class="glyphicon form-control-feedback"></span>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                        <input id = "text_8" type="text" class="form-control text"  placeholder="Phone" name = "Phone"  title = "" required="" value="" maxlength = "30"/>
                                    <span class="glyphicon form-control-feedback"></span>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-labeled btn-primary">
                                <span class="btn-label"><i class="glyphicon glyphicon-log-out"></i></span>Find
                            </button>
                        </div>

                    </div>
                </div>
                <div class = "col-xs-6 col-md-4 "></div>
            </div>
        </form>
    </div>


<div id = "ViewProjectInfo" style = "display: none;">
    <div class = "col-xs-6 col-md-2"></div>
    <div class = "col-xs-6 col-md-8">
        <form action = "/" method="post">
            <div class ="panel panel-default">

                <div class="panel-heading">
                    <h3 class="panel-title">Project info</h3>
                </div>

                <div class = "panel-body">


                    <table  id = "table3" class = "table" style = "display: block;">
                        <thead>
                        <tr>
                            <c:forEach items="${paramNames}" var="paramNames">
                                <th>${paramNames}</th>
                            </c:forEach>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:forEach items="${paramContents}" var="paramContents">
                                <td>${paramContents}</td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>

                    <table  id = "table4" class = "table" style = "display: block;">
                        <thead>
                            <c:forEach items="${sprintsNames}" var="sprintsNames">
                                <tr>
                                    <c:forEach items="${sprintsNames}" var="sprintsNames2">
                                        <th>${sprintsNames2}</th>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </thead>
                        <tbody>
                            <c:forEach items="${sprintsContents}" var="sprintsContents">
                                <tr>
                                    <c:forEach items="${sprintsContents}" var="sprintsContents2">
                                        <td>${sprintsContents2}</td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <table  id = "table5" class = "table" style = "display: block;">
                        <thead>
                        <c:forEach items="${taskNames}" var="taskNames">
                            <tr>
                                <c:forEach items="${taskNames}" var="taskNames2">
                                    <th>${taskNames2}</th>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        </thead>
                        <tbody>
                        <c:forEach items="${taskContents}" var="taskContents">
                            <tr>
                                <c:forEach items="${taskContents}" var="taskContents2">
                                    <td>${taskContents2}</td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class = "row">
                        <div class = "col-xs-6 col-md-2">
                            <a id = "button6" href="javascript:onoff('ViewProjectInfo');" style="display: block">
                                <button type="button" class="btn btn-labeled btn-primary">
                                    <span class="btn-label"><i class="glyphicon glyphicon-log-out"></i></span>Close
                                </button>
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </form>
    </div>
    <div class = "col-xs-6 col-md-2"></div>
</div>

            <%  Object not_a_customer = request.getAttribute("not_a_customer");
            if(not_a_customer != null){%>
                <script>
            alert('Wrong email or password!')
        </script>
           <% }
            Object authorization = request.getAttribute("authorization");
    if(authorization != null){%>
        <script>
            alert('Successfully!')
            setPointer('pointer1');
            onoff('Authorization');
            onoff('LogOut');
        </script>
            <%}%>

<%Object info = request.getAttribute("info");
    if(info != null){%>
<script>
    setPointer('pointer1');
    onoff('Authorization');
    onoff('LogOut');
    onoff('ViewProjectInfo');
</script>
<%}%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
