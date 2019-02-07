<%@ page import="metube.domain.models.view.AllTubesViewModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<% List<AllTubesViewModel> allTubesViewModels = (List<AllTubesViewModel>) request.getAttribute("allTubes");%>
<div class="jumbotron">
    <div class="row">
        <div class="col col-md-12 d-flex justify-content-center">
            <h1>
                All Tubes
            </h1>
        </div>
    </div>
    <hr/>
    <div class="class row">
        <div class="class col col-md-12 d-flex justify-content-center">
            <h3>
                Check our Tubes
            </h3>
        </div>
    </div>
    <hr/>
    <div class="class row">
        <div class="class col col-md-12 d-flex justify-content-center">
            <% if (allTubesViewModels.size() == 0){%>
            <p>No Tubes in DB <a href="/tubes/create">Create some</a></p>
            <% } else {%>
            <ul>
                <% for (AllTubesViewModel allTubesViewModel : allTubesViewModels) { %>
                          <li><a href="/tubes/details?name=<%= allTubesViewModel.getName() %>"><%= allTubesViewModel.getName()%>></a></li>
                <% } %>
            </ul>
            <% }%>
        </div>
    </div>

    <div class="class col col-md-12 d-flex justify-content-center">
        <a href="/">Back to homepage</a>
    </div>
</div>
</body>
</html>
