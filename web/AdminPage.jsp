<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin page</title>
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
<% if(session.getAttribute("username") == null){
    response.sendRedirect("index.jsp");
}%>
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
                        <a  id = "pointer1" href="javascript:onoff('NewObject');" style = "pointer-events: auto;">New Object</a>
                    </li>
                    <li class="secondtitle">
                        <a  id = "pointer2" href="javascript:onoff('NewAttribute');" style = "pointer-events: auto;">New Attribute</a>
                    </li>
                    <li class="secondtitle">
                        <a  id = "pointer3" href="javascript:onoff('NewEntity');" style = "pointer-events: auto;">New Entity</a>
                    </li>
                    <li class="secondtitle">
                        <a  id = "pointer4" href="javascript:onoff('Employees');" style = "pointer-events: auto;">Employees</a>
                    </li>
                    <li class="secondtitle">
                        <a  id = "pointer5" href="javascript:onoff('Projects');" style = "pointer-events: auto;">Projects</a>
                    </li>
                    <li class="secondtitle">
                        <a  id = "pointer7" href="javascript:onoff('Tasks');" style = "pointer-events: auto;">Tasks</a>
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


<div id = "LogOut" style = "display: block;">
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


<div id = "NewObject" style = "display: none;">
    <div class = "col-xs-6 col-md-4"></div>
    <div class = "col-xs-6 col-md-4">
        <form name = "newobject" action = "/NewObjectServlet" method="post">
            <div class ="panel panel-default">

                <div class="panel-heading">
                    <h3 class="panel-title">Create new object</h3>
                </div>

                <div class = "panel-body">

                    <div class = "row">

                        <div class ="panel-group">
                            <div class = "panel-body">
                                <div class="form-group">
                                    <div class = "input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                        <input id = "text_6" type="text" class="form-control"  placeholder="Object name" name = "Object_name"  title = "" required="" value="" maxlength = "30"/>
                                        <span class="glyphicon form-control-feedback"></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                        <input id = "text_7" type="text" class="form-control" placeholder="Parent Object id" name = "Parent_object_id"  title = "" required="" value="" maxlength = "30"/>
                                        <span class="glyphicon form-control-feedback"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group b2">
                                <a href="javascript:onoff('NewObject');">
                                    <button type="button" class="btn btn-labeled btn-primary">
                                        <span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>Close
                                    </button>
                                </a>
                                <a href="#">
                                    <button type="submit" class="btn btn-labeled btn-primary">
                                        <span class="btn-label"><i class="glyphicon glyphicon-registration-mark"></i></span>Create Object
                                    </button>
                                </a>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class = "col-xs-6 col-md-4"></div>
</div>


<div id = "NewAttribute" style = "display: none;">
    <div class = "col-xs-6 col-md-4"></div>
    <div class = "col-xs-6 col-md-4">
        <form name = "newattribute" action = "/NewAttributeServlet" method="post">
            <div class ="panel panel-default">

                <div class="panel-heading">
                    <h3 class="panel-title">Create new attribute</h3>
                </div>

                <div class = "panel-body">
                    <div class = "row">
                        <div class ="panel-group">
                            <div class = "panel-body">
                                <div class="form-group">
                                    <div class = "input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                        <input type="text" class="form-control"  placeholder="Object id" name = "Object_id"  title = "" required="" value="" maxlength = "30"/>
                                        <span class="glyphicon form-control-feedback"></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                        <input type="text" class="form-control" placeholder="Attribute" name = "Attribute"  title = "" required="" value="" maxlength = "30"/>
                                        <span class="glyphicon form-control-feedback"></span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                        <select class="form-control" name = "Type">
                                            <option value="1">String</option>
                                            <option value="2">Integer</option>
                                            <option value="3">Data</option>
                                        </select>
                                        <span class="glyphicon form-control-feedback"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group b2">
                                <a href="javascript:onoff('NewAttribute');">
                                    <button type="button" class="btn btn-labeled btn-primary">
                                        <span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>Close
                                    </button>
                                </a>
                                <a href="#">
                                    <button type="submit" class="btn btn-labeled btn-primary">
                                        <span class="btn-label"><i class="glyphicon glyphicon-registration-mark"></i></span>Create Attribute
                                    </button>
                                </a>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </form>
    </div>
        <div class = "col-xs-6 col-md-4"></div>
</div>


<div id = "NewEntity" style = "display: none;">
    <div class = "col-xs-6 col-md-4"></div>
    <div class = "col-xs-6 col-md-4">
        <form name = "NewEntityServlet" action = "/NewEntityServlet" method="post">
            <div class ="panel panel-default">

                <div class="panel-heading">
                    <h3 class="panel-title">Create new entity</h3>
                </div>

                <div class = "panel-body">
                    <div class = "row">
                        <div class ="panel-group">
                            <div class = "panel-body">
                                <div class="form-group">
                                    <div class = "input-group">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                        <input type="text" class="form-control"  placeholder="Object id" name = "Object_id"  title = "" required="" value="" maxlength = "30"/>
                                        <span class="glyphicon form-control-feedback"></span>
                                    </div>
                                </div>

                            </div>

                            <div class="form-group b2">
                                <a href="javascript:onoff('NewEntity');">
                                    <button type="button" class="btn btn-labeled btn-primary">
                                        <span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>Close
                                    </button>
                                </a>
                                <a href="#">
                                    <button type="submit" class="btn btn-labeled btn-primary">
                                        <span class="btn-label"><i class="glyphicon glyphicon-registration-mark"></i></span>Create Entity
                                    </button>
                                </a>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class = "col-xs-6 col-md-4"></div>
</div>

    <div id = "CreateEntity" style = "display: none;">
        <div class = "col-xs-6 col-md-4"></div>
        <div class = "col-xs-6 col-md-4">
            <form name = "NewEntityServlet" action = "/NewEntityServlet" method="post">
                <div class ="panel panel-default">

                    <div class="panel-heading">
                        <h3 class="panel-title">Create new entity</h3>
                    </div>

                    <div class = "panel-body">
                        <div class = "row">
                            <div class ="panel-group">
                                <div class = "panel-body">
                                    <div class="form-group">
                                        <div class = "input-group">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                            <input type="text" class="form-control"  placeholder="<%= session.getAttribute("objName")%>" name = "Object_name"  title = "" required="" value="" maxlength = "30" disabled/>
                                            <span class="glyphicon form-control-feedback"></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                            <select class="form-control" name = "Attributes">
                                                <%int l = 0;%>
                                                <c:forEach items="${paramNames}" var="paramNames">
                                                    <%l++;%>
                                                    <option value= "<%=l%>" style="display: block">${paramNames}</option>
                                                </c:forEach>
                                            </select>
                                            <span class="glyphicon form-control-feedback"></span>
                                        </div>
                                    </div>
                                    <div  class="form-group">
                                        <div class = "input-group">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                            <input type="text" class="form-control"  placeholder="Attribute content" name = "Attribute_content"  title = "" required="" value="" maxlength = "30"/>
                                            <span class="glyphicon form-control-feedback"></span>
                                        </div>
                                    </div>

                                </div>

                                <div class="form-group b2">
                                    <a href="javascript:onoff('CreateEntity');">
                                        <button type="button" class="btn btn-labeled btn-primary">
                                            <span class="btn-label"><i class="glyphicon glyphicon-remove"></i></span>Close
                                        </button>
                                    </a>
                                    <a href="#">
                                        <button type="submit" class="btn btn-labeled btn-primary">
                                            <span class="btn-label"><i class="glyphicon glyphicon-registration-mark"></i></span>Create Entity
                                        </button>
                                    </a>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class = "col-xs-6 col-md-4"></div>
    </div>

<div id = "Employees" style = "display: none;">
    <div class = "col-xs-6 col-md-1"></div>
    <div class = "col-xs-6 col-md-10">
        <form action = "/ListEmployeesServlet" method="post">
            <div class ="panel panel-default">

                <div class="panel-heading">
                    <h3 class="panel-title">Employees</h3>
                </div>

                <div class = "panel-body">

                    <table  id = "table" class = "table table-bordered" style = "display: none;">

                        <thead>
                        <tr>
                            <th>№</th>
                            <th>id</th>
                            <c:forEach items="${dataNames}" var="dataNames">
                                <th>${dataNames}</th>
                            </c:forEach>
                        </tr>
                        </thead>
                        <tbody>
                        <%int k = 0;%>
                        <c:forEach items="${dataCon}" var="dataCon">
                            <%k++;%>
                            <tr>
                                <th><%=k%></th>
                                <c:forEach items="${dataCon}" var="dataCon2">
                                    <th>${dataCon2}</th>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <a id = "button1" href="#" style="display: block">
                        <button type = "submit" class="btn btn-labeled btn-primary" >
                            <span class="btn-label"><i class="glyphicon glyphicon-log-out"></i></span>Open list
                        </button>
                    </a>

                    <a id = "button2" href="javascript:onoff('Employees');" style="display: none">
                        <button type="button" class="btn btn-labeled btn-primary">
                            <span class="btn-label"><i class="glyphicon glyphicon-log-out"></i></span>Close
                        </button>
                    </a>

                </div>
            </div>
        </form>
        <div class = "col-xs-6 col-md-1"></div>
    </div>
</div>


<div id = "Projects" style = "display: none;">
    <div class = "col-xs-6 col-md-2"></div>
    <div class = "col-xs-6 col-md-8">
        <form action = "/ListProjectsServlet" method="post">
            <div class ="panel panel-default">

                <div class="panel-heading">
                    <h3 class="panel-title">Projects</h3>
                </div>

                <div class = "panel-body">

                    <table  id = "table2" class = "table table-bordered" style = "display: none;">
                        <thead>
                        <tr>
                            <th>№</th>
                            <c:forEach items="${dataNames}" var="dataNames">
                                <th>${dataNames}</th>
                            </c:forEach>
                        </tr>
                        </thead>
                        <tbody>
                        <%int j = 0;%>
                        <c:forEach items="${dataConts}" var="dataConts">
                            <%j++;%>
                            <tr>
                                <th><%=j%></th>
                                <c:forEach items="${dataConts}" var="dataConts2">
                                    <th>${dataConts2}</th>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <a id = "button3" href="#" style="display: block">
                        <button type = "submit" class="btn btn-labeled btn-primary" >
                            <span class="btn-label"><i class="glyphicon glyphicon-log-out"></i></span>Open list
                        </button>
                    </a>

                    <a id = "button4" href="javascript:onoff('Projects');" style="display: none">
                        <button type="button" class="btn btn-labeled btn-primary">
                            <span class="btn-label"><i class="glyphicon glyphicon-log-out"></i></span>Close
                        </button>
                    </a>

                </div>
            </div>
        </form>
        <div class = "col-xs-6 col-md-2"></div>
    </div>
</div>


<div id = "Tasks" style = "display: none;">
    <div class = "col-xs-6 col-md-2"></div>
    <div class = "col-xs-6 col-md-8">
        <form action = "/TasksServlet" method="post">
            <div class ="panel panel-default">

                <div class="panel-heading">
                    <h3 class="panel-title">Tasks</h3>
                </div>

                <div class = "panel-body">

                    <table  id = "table3" class = "table" style = "display: none;">
                        <thead>
                            <tr>
                                <th>№</th>
                                <th>id</th>
                                <c:forEach items="${dataNames}" var="dataNames2">
                                    <th>${dataNames2}</th>
                                </c:forEach>
                                <th>Done</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%int i = 0;%>
                            <c:forEach items="${dataContents}" var="dataContents">
                                <%i++;%>
                                <tr>
                                    <th><%=i%></th>
                                    <th>${taskIds}</th>
                                    <c:forEach items="${dataContents}" var="dataContents2">
                                        <th>${dataContents2}</th>
                                    </c:forEach>
                                    <td>
                                        <input id = "checkBox" type = "checkbox" name = "<%=i%>">
                                    </td>
                                </tr>
                            </c:forEach>
                            <%session.setAttribute("countOfRows",i);%>
                        </tbody>
                    </table>

                    <div class = "row">
                        <div class = "col-xs-6 col-md-2">
                            <a id = "button5" href="" style="display: block">
                                <button type = "submit" class="btn btn-labeled btn-primary" >
                                    <span class="btn-label"><i class="glyphicon glyphicon-log-out"></i></span>Ok
                                </button>
                            </a>
                        </div>
                        <div class = "col-xs-6 col-md-2">
                            <a id = "button6" href="javascript:onoff('Tasks');" style="display: none">
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

<%  Object employees = request.getAttribute("employees");
    if(employees != null){%>
<script>
    onoff("pointer7");
    onoff('Employees');
    onoff('table');
    onoff('button1');
    onoff('button2');
</script>
<%}%>

<%  Object projects = request.getAttribute("projects");
    if(projects != null){%>
<script>
    onoff("pointer7");
    onoff('Projects');
    onoff('table2');
    onoff('button3');
    onoff('button4');
</script>
<%}%>

<%  Object tasksInfo = request.getAttribute("tasksInfo");
    if(tasksInfo != null){%>
<script>
    onoff('Tasks');
    onoff('table3');
    onoff('button6');
</script>
<%}%>

<%  Object status = request.getAttribute("status");
    if(status != null){%>
<script>
    alert("Status of task is successfully changed!");
</script>
<%}%>

    <%  Object object = request.getAttribute("object");
    if(object != null){%>
        <script>
            alert('Object successfully created!')
            onoff('NewObject');
        </script>
<%}%>

<%  Object objectError = request.getAttribute("objectError");
    if(objectError != null){%>
<script>
    alert('Something wrong! Please try again..')
    onoff('NewObject');
</script>
<%}%>


<%  Object attribute = request.getAttribute("attribute");
    if(attribute != null){%>
<script>
    alert('Attribute successfully created!');
    onoff('NewAttribute');
</script>
<%}%>

<%  Object attributeError = request.getAttribute("attributeError");
    if(attributeError != null){%>
<script>
    alert('Something wrong! Please try again!');
    onoff('NewAttribute');
</script>
<%}%>


<%  Object newEntity = request.getAttribute("newEntity");
    if(newEntity != null){%>
<script>
    onoff("CreateEntity");
</script>
<%}%>

<%  Object authorization1 = request.getAttribute("authorization1");
    if(authorization1 != null){%>
<script>
    onoff("pointer7");
    alert('Hello, Admin!')
</script>
<%}%>

<%  Object authorization2 = request.getAttribute("authorization2");
    if(authorization2 != null){%>
<script>
    onoff("pointer1");
    onoff("pointer2");
    onoff("pointer3");
    onoff("pointer4");
    onoff("pointer5");
    alert("Successfully!");
</script>
<%}%>

<%  Object entityResult = request.getAttribute("entityResult");
    if(entityResult != null){%>
<script>
    onoff("pointer7");
    alert('Entity successfully created!');
</script>
<%}%>

<%  Object entityError = request.getAttribute("entityError");
    if(entityError != null){%>
<script>
    onoff("pointer7");
    alert('Something wrong! Please try again..');
</script>
<%}%>

</body>
</html>
