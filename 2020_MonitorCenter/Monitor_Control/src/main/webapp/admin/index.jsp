<%@page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Thông tin hệ thống</a></div>
        <h1>Thông tin hệ thống <span style="color: red">${result.message}</span></h1>
    </div>
    <div class="container-fluid">
        <div class="widget-box widget-plain">
            <div class="center">
                <ul class="stat-boxes">
                    <li>
                        <div class="left peity_bar_neutral"><span><span style="display: none;">2,4,9,7,12,10,12</span>
                                <canvas width="50" height="24"></canvas>
                            </span></div>
                        <div class="right"> <strong>${accInfo.username}</strong> Account </div>
                    </li>
                    <li>
                        <div class="left peity_line_neutral"><span><span style="display: none;">10,15,8,14,13,10,10,15</span>
                                <canvas width="50" height="24"></canvas>
                            </span></div>
                        <div class="right"> <strong>${accInfo.agency_code}</strong>Agency Code</div>
                    </li>
                    <li>
                        <div class="left peity_bar_bad"><span><span style="display: none;">10,5,6,16,8,10,6</span>
                                <canvas width="50" height="24"></canvas>
                            </span></div>
                        <div class="right"> <strong>${systemInfo.cpu}</strong> CPU</div>
                    </li>
                    <li>
                        <div class="left peity_line_good"><span><span style="display: none;">12,6,9,23,14,10,17</span>
                                <canvas width="50" height="24"></canvas>
                            </span></div>
                        <div class="right"> <strong>${systemInfo.currentCall}</strong> CurrentCall </div>
                    </li>
                    <li>
                        <div class="left peity_bar_good"><span>12,6,9,23,14,10,13</span></div>
                        <div class="right"> <strong>${systemInfo.maxCall}</strong> MaxCall</div>
                    </li>
                    <li>
                        <div class="left peity_bar_bad"><span>12,6,9,23,14,10,5</span></div>
                        <div class="right"> <strong>${accInfo.balance} VNĐ</strong> Blance</div>
                    </li>
                    <li>
                        <div class="left peity_line_good"><span><span style="display: none;">12,6,9,23,14,10,17</span>
                                <canvas width="50" height="24"></canvas>
                            </span></div>
                        <div class="right"> <strong>${systemInfo.queueCall}</strong> QueueCall </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row-fluid" style="visibility: hidden;">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-signal"></i> </span>
                        <h5>Bar chart</h5>
                    </div>
                    <div>
                        <div class="bars"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid" style="visibility: hidden;">
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-signal"></i> </span>
                        <h5>Line chart</h5>
                    </div>
                    <div class="widget-content">
                        <div class="chart"></div>
                    </div>
                </div>
            </div>
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-signal"></i> </span>
                        <h5>Pie chart</h5>
                    </div>
                    <div>
                        <div class="pie"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value='/admin/js/excanvas.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.ui.custom.js'/>"></script>
<script src="<c:url value='/admin/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.flot.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.flot.pie.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.flot.resize.min.js'/>"></script>
<script src="<c:url value='/admin/js/maruti.js'/>"></script>
<script src="<c:url value='/admin/js/maruti.charts.js'/>"></script>
<script src="<c:url value='/admin/js/maruti.dashboard.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.peity.min.js'/>"></script>
<script src="<c:url value='/admin/js/fullcalendar.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.validate.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.alerts.js'/>"></script>