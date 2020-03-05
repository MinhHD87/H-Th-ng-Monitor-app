<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="<c:url value="/admin/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> ${Lang['generic.home']}</a> 
            <a cl href="<c:url value="/admin/chart/list"/>" class="tip-bottom">App client</a>
            <a cl href="<c:url value="/admin/chart/view?id=${sysMonitor.id}"/>" class="current">Biểu đồ</a>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12" style="padding-top: 5px">
                <c:if test="${result!=null && result.code!=1}">
                    <div id="alert alert-error" class="alert"><button class="close" data-dismiss="alert">×</button><strong>Error!</strong> ${result.message}</div>
                </c:if>
                <div class="widget-box">
                    <div class="widget-content nopadding">
                        <form:form id="editForm" method="post" modelAttribute="sysAcc"  action="">
                            <input type="hidden" name="id" value="${sysMonitor.id}"/>
                            <table class="table table-bordered table-striped">
                                <tbody>
                                    <tr>
                                        <td class="span3">Name</td>
                                        <td>
                                            <input readonly="true" value="${sysMonitor.name}" type="text" class="span10" name="name"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="span3">Description</td>
                                        <td><textarea readonly="true" class="span10" name="description">${sysMonitor.description}</textarea></td>
                                    </tr>
                                    <tr>
                                        <td class="span3">Ip</td>
                                        <td>
                                            <input readonly="true" value="${sysMonitor.ip}" type="text" class="span10" name="ip"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="span3">Pos</td>
                                        <td>
                                            <input readonly="true" value="${sysMonitor.pos}" type="text" class="span10" name="pos"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="span3">Status</td>
                                        <td>
                                            <input readonly="true" value="${sysMonitor.status == 0 ? "Off" : "On"}" type="text" class="span10" name="status"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="span3">Time down</td>
                                        <td>
                                            <input readonly="true" value="${sysMonitor.timedown}" type="text" class="span10" name="timedown"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="span3">Time up</td>
                                        <td>
                                            <input readonly="true" value="${sysMonitor.timeup}" type="text" class="span10" name="timeup"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Hệ thống</td>
                                        <td>
                                            <div style="float: left">                                                
                                                <c:forEach var='item' items='${sysMonitor.arr}'>                
                                                    <input readonly="true" value="${item.name}" type="text" name="" size="25" class="span10"/><br/>
                                                </c:forEach>
                                            </div>
                                            <div style="float: left">
                                                <c:forEach var='item' items='${sysMonitor.arr}'>                
                                                    <input readonly="true" value="${item.status == 0 ? 'Off' : 'On'}" type="text" name="" size="25" class="span3"/><br/>
                                                </c:forEach>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input value="${sysMonitor.timesreach}" class="text-input" size="55" id="datetimepicker" name="datesreach" type="text" >
                                            <script>
                                                jQuery.datetimepicker.setLocale('de');
                                                jQuery('#datetimepicker').datetimepicker({
                                                 i18n:{
                                                  de:{
                                                   months:[
                                                    'January','February','March','April',
                                                    'May','June','July','August',
                                                    'September','October','November','December',
                                                   ],
                                                   dayOfWeek:[
                                                    "Sunday", "Monday", "Tuesday", "Wednesday", 
                                                    "Thursday", "Friday", "Saturday",
                                                   ]
                                                  }
                                                 },
                                                 timepicker:false,
                                                 format:'Y-m-d'
                                                });
                                            </script>
                                            <input id="btnSubmit" class="btn btn-success" name="submit" value="${Lang['generic.update']}" type="submit">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>RAM CHART</td>
                                        <td>
                                            <div style="overflow: scroll;width: 1360px;">
                                                <canvas id="buyers" height="500" width="10000"></canvas>
                                            </div>
                                            <script>
                                                var buyerData = {
                                                labels: ['Min','Max',<c:forEach var='item' items='${sysMonitor.listRam}'>'${item.time}',</c:forEach>],
                                                datasets: [{
                                                        fillColor: "#ed3b3b",
                                                        strokeColor : "#ACC26D",
                                                        pointColor : "#ffffff",
                                                        pointStrokeColor : "#9DB86D",
                                                        data: [0,100,<c:forEach var="item" items="${sysMonitor.listRam}">${item.value},</c:forEach>]
                                                        }]
                                                }
                                                var buyers = document.getElementById('buyers').getContext('2d');
                                                new Chart(buyers).Line(buyerData);
                                            </script>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>CPU CHART</td>
                                        <td>
                                            <div style="overflow: scroll;width: 1360px;">
                                                <canvas id="buyers2" height="500" width="10000"></canvas>
                                            </div>
                                            <script>
                                                var buyerData2 = {
                                                labels: ['Min','Max',<c:forEach var='item' items='${sysMonitor.listCPU}'>'${item.time}',</c:forEach>],
                                                datasets: [{
                                                        fillColor: "#FC8213",
                                                        strokeColor : "#ACC26D",
                                                        pointColor : "#ffffff",
                                                        pointStrokeColor : "#9DB86D",
                                                        data: [0,100,<c:forEach var="item" items="${sysMonitor.listCPU}">${item.value},</c:forEach>]
                                                        }]
                                                }
                                                var buyers2 = document.getElementById('buyers2').getContext('2d');
                                                new Chart(buyers2).Line(buyerData2);
                                            </script>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>HDD CHART</td>
                                        <td>
                                            <div style="overflow: scroll;width: 1360px;">
                                                <canvas id="buyers3" height="500" width="10000"></canvas>
                                            </div>
                                            <script>
                                                var buyerData3 = {
                                                labels: ['Min',<c:forEach var='item' items='${sysMonitor.listHDD}'>'${item.time}',</c:forEach>],
                                                datasets: [{
                                                        fillColor: "#edea3b",
                                                        strokeColor : "#ACC26D",
                                                        pointColor : "#ffffff",
                                                        pointStrokeColor : "#9DB86D",
                                                        data: [0,<c:forEach var="item" items="${sysMonitor.listHDD}">${item.value},</c:forEach>]
                                                        }]
                                                }
                                                var buyers3 = document.getElementById('buyers3').getContext('2d');
                                                new Chart(buyers3).Line(buyerData3);
                                            </script>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value='/admin/js/jquery.min.js'/>"></script>
<script src="<c:url value='/admin/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.alerts.js'/>"></script>