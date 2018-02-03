<%@ page import="persistance.models.Room" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    <%@include file="../../rooms.css" %>
</style>

<script>
    <%@include file="../../rooms.js" %>
</script>

<t:layout>
    <jsp:body>
        <div class="container-fluid" style="margin-top: 51px;">
            <div class="row">
                <c:forEach items="${rooms}" var="room">
                    <t:room room="${room}"></t:room>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</t:layout>

