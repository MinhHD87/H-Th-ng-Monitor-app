<!DOCTYPE html><%@page contentType="text/html; charset=utf-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
    <head>
        <title>${title}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <tiles:insertAttribute name="css" />
<script src="<c:url value='/admin/js/jquery.min.js'/>"></script>
        <script src="<c:url value='/admin/js/Chart.min.js'/>"></script>
        <script src="<c:url value='/admin/js/jquery.datetimepicker.full.min.js'/>"></script>
    </head>
    <body>
        <tiles:insertAttribute name="menu" />
        <tiles:insertAttribute name="body" />
        <tiles:insertAttribute name="footer" />
    </body>
</html>
