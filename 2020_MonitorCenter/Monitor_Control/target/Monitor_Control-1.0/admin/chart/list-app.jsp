<%@page language="java" contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="<c:url value="/admin/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> ${Lang['generic.home']}</a> 
            <a href="<c:url value="/admin/chart/list"/>" class="current">App client</a></div>
    </div>
    <div class="container-fluid">
        <div ng-app="listChartApp" ng-controller="listChartCtrl" class="row-fluid" ng-init="maxRow = '5'; crPage = '1'; key = ''; ip = ''; pos = ''; status = '127';">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"><i class="icon-align-justify"></i> </span>
                        <h5>Tìm kiếm</h5>
<!--                        <span ng-if="roles.recycle" class="fr" style="padding: 9px"><a href="<c:url value="/admin/sys-account/recycle"/>" style="color: red;font-weight: bold;">${Lang['generic.deleted']} <img width="16" src="<c:url value="/admin/images/recycle.png"/>"></a></span>-->
                    </div>
                    <div class="widget-content nopadding">
                        <form class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">Tên app / Username app </label>
                                <div class="controls">
                                    <input class="text-input" ng-model="key" type="text">
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    Địa chỉ IP
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input class="text-input" ng-model="ip" type="text">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Công kết nối </label>
                                <div class="controls">
                                    <input class="text-input" ng-model="pos" type="text">
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    ${Lang['generic.status']}
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <select style="width: 150px;vertical-align: middle" ng-model="status">
                                        <option value="127">Tất cả</option>
                                        <option value="0">Off</option>
                                        <option value="1">On</option>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <div ng-click="reloadFilter()" class="btn btn-success">${Lang['generic.search']}</div>
                                    &nbsp;&nbsp;&nbsp;
                                    <input ng-click="reset()" type="reset" class="btn btn-warning" name="Reset Form" value="${Lang['generic.reload']}"/>
                                    &nbsp;&nbsp;&nbsp;
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
                        <h5>Danh sách client app <span id="result_model" class="callback_info">${result.message}</span>
                            <span class="callback_info">{{result.message}}</span></h5>
                        <div class="fr" style="padding: 3px">
                            ${Lang['generic.display']} 
                            <select ng-model="maxRow" ng-change="updateMaxRow()">
                                <option value="1">1 ${Lang['generic.line']}</option>
                                <option value="5">5 ${Lang['generic.line']}</option>
                                <option value="10">10 ${Lang['generic.line']}</option>
                            </select>
                        </div>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>${Lang['generic.index']}</th>
                                    <th>${Lang['generic.fullName']}</th>
                                    <th>${Lang['generic.desc']}</th>
                                    <th>Địa chỉ IP</th>
                                    <th>Cổng kết nối</th>
                                    <th>${Lang['generic.status']}</th>
                                    <th>Hệ thống</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="mon in datas">
                                    <td>{{($index + 1)}}</td>
                                    <td><a href="${pageContext.request.contextPath}/admin/chart/view?id={{mon.id}}" >
                                            {{mon.name}}
                                        </a></td>
                                    <td>{{mon.description}}</td>
                                    <td>{{mon.ip}}</td>
                                    <td>{{mon.pos}}</td>
                                    <td class="img-center">
                                        <img ng-if="mon.status === 1" width="24" src="<c:url value="/admin/images/active.png"/>" alt="active">
                                        <img ng-if="mon.status === 0" width="24" src="<c:url value="/admin/images/lock.png"/>" alt="block">
                                    </td>
                                    <td>
                                        {{mon.str}}
                                    </td>
                                </tr>
                                <tr ng-if="totalRow > maxRow">
                                    <td colspan="13">
                                        <div class="pagination aalternate fr">
                                            <ul uib-pagination total-items="totalRow" ng-model="crPage" ng-change="pageChanged()" items-per-page="maxRow" boundary-links="true" num-pages="numPages"></ul>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value='/admin/js/angular.min.js'/>"></script>
<script src="<c:url value='/admin/js/ui-bootstrap-tpls-2.1.3.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.min.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.alerts.js'/>"></script>
<script src="<c:url value='/admin/js/jquery.blockUI.js'/>"></script>
<script src="<c:url value='/admin/controller/Chart.js'/>"></script>