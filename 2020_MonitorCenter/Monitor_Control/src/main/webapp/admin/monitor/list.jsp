<%@page language="java" contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="<c:url value="/admin/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> ${Lang['generic.home']}</a> <a href="<c:url value="/admin/monitor/list"/>" class="current">Quản lý monitor</a></div>
    </div>
    <div class="container-fluid">
        <div ng-app="listMonitorApp" ng-controller="listMonitorCtrl" class="row-fluid" ng-init="maxRow = '20'; crPage = '1'; key = ''; phone = ''; email = ''; parent = ''; status = '127';">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"><i class="icon-align-justify"></i> </span>
                        <h5>Tìm kiếm monitor</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">Tên monitor </label>
                                <div class="controls">
                                    <input class="text-input" ng-model="name" type="text">
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <!--                                    Mã cha monitor
                                                                        <input class="text-input" ng-model="parent" type="parent">-->
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Địa chỉ IP </label>
                                <div class="controls">
                                    <input class="text-input" ng-model="ip" type="text">
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    Cổng 
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input class="text-input" ng-model="pos" type="text">
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <div ng-click="reloadFilter()" class="btn btn-success">${Lang['generic.search']}</div>
                                    &nbsp;&nbsp;&nbsp;
                                    <input ng-click="reset()" type="reset" class="btn btn-warning" name="Reset Form" value="${Lang['generic.reload']}"/>
                                    &nbsp;&nbsp;&nbsp;
                                    <input class="btn btn-primary" onclick="location.href = '<c:url value="/admin/monitor/create" />'" value="${Lang['generic.addNew']}" type="button">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
                        <h5>${Lang['system.account.detail.title']} <span id="result_model" class="callback_info">${result.message}</span>
                            <span class="callback_info">{{result.message}}</span></h5>
                        <div class="fr" style="padding: 3px">
                            ${Lang['generic.display']} 
                            <select ng-model="maxRow" ng-change="updateMaxRow()">
                                <option value="20">20 ${Lang['generic.line']}</option>
                                <option value="50">50 ${Lang['generic.line']}</option>
                                <option value="100">100 ${Lang['generic.line']}</option>
                            </select>
                        </div>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th>${Lang['generic.index']}</th>
                                    <th>Tên</th>
                                    <th>Thể loại</th>
                                    <th>IP</th>
                                    <th>POS</th>
                                    <th>${Lang['generic.userName']}</th>
                                    <th>${Lang['generic.desc']}</th>
                                    <th>${Lang['generic.phone']}</th>
                                    <th>${Lang['generic.email']}</th>
                                    <th>Telegram</th>
                                    <th>Voice</th>
                                    <th>${Lang['generic.edit']}/${Lang['generic.delete']}</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="oneAcc in datas">
                                    <td>{{($index + 1)}}</td>
                                    <td><a href="${pageContext.request.contextPath}/admin/chart/view?id={{oneAcc.parent == 0 ? oneAcc.id : oneAcc.parent}}" >
                                            {{oneAcc.name}}
                                        </a></td>
                                    <td>{{oneAcc.parent == 0 ? "App client" : "Hệ thống"}}</td>
                                    <td>{{oneAcc.ip}}</td>
                                    <td>{{oneAcc.pos}}</td>
                                    <td>{{oneAcc.username}}</td>
                                    <td>{{oneAcc.description}}</td>
                                    <td>{{oneAcc.phone}}</td>
                                    <td>{{oneAcc.email}}</td>
                                    <td>{{oneAcc.telegram}}</td>
                                    <td>{{oneAcc.voice}}</td>
                                    <td style="width: 70px">
                                        <a  href="${pageContext.request.contextPath}/admin/monitor/edit?id={{oneAcc.id}}" >
                                            <img src="<c:url value="/admin/images/edit.png"/>" title="Edit">
                                        </a>
                                        <a  ng-click="deleteforEver(oneAcc.id)" href="" title="Delete">
                                            <img src="<c:url value="/admin/images/remove.png"/>" title="Delete">
                                        </a>
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
<script src="<c:url value='/admin/controller/Monitor.js'/>"></script>