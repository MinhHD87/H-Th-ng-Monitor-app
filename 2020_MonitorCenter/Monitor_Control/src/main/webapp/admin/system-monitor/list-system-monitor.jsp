<%@page language="java" contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content">
    <div id="content-header">
        <div id="breadcrumb"><a href="<c:url value="/admin/index"/>" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> ${Lang['generic.home']}</a> <a href="<c:url value="/admin/message-monitor/list"/>" class="current">${Lang['system.account.subMenu']}</a></div>
    </div>
    <div class="container-fluid">
        <div ng-app="listSystemMonitorApp" ng-controller="listSystemMonitorCtrl" class="row-fluid" ng-init="maxRow = '20';
                        crPage = '1';
                        key = '';
                        phone = '';
                        email = '';
                        status = '127';">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"><i class="icon-align-justify"></i> </span>
                        <h5>${Lang['system.account.search.title']}</h5>
                        <span ng-if="roles.recycle" class="fr" style="padding: 9px"><a href="<c:url value="/admin/message-monitor/recycle"/>" style="color: red;font-weight: bold;">${Lang['generic.deleted']} <img width="16" src="<c:url value="/admin/images/recycle.png"/>"></a></span>
                    </div>
                    <div class="widget-content nopadding">
                        <form class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">${Lang['system.account.search.user']} </label>
                                <div class="controls">
                                    <input class="text-input" ng-model="key" type="text">
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    Đia chỉ IP
                                    <input class="text-input" ng-model="ip" type="text">
                                </div>
                            </div>
                            <div class="control-group">
<!--                                <label class="control-label">SENDTO </label>-->
                                <div class="controls">
                                    <!--<input class="text-input" ng-model="sendto" type="text">-->
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    ${Lang['generic.status']}
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <select style="width: 150px;vertical-align: middle" ng-model="status">
                                        <c:forEach var="s" items="${lstatus}">
                                            
                                            <option value="${s.val}">${s.desc}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <div ng-click="reloadFilter()" class="btn btn-success">${Lang['generic.search']}</div>
                                    &nbsp;&nbsp;&nbsp;
                                    <input type="reset" class="btn btn-warning" name="Reset Form" value="${Lang['generic.reload']}"/>
                                    <!--&nbsp;&nbsp;&nbsp;-->
                                    <!--<input class="btn btn-primary" onclick="location.href = '<c:url value="/admin/sys-account/create" />'" value="${Lang['generic.addNew']}" type="button">-->
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

                                    <th>STT</th>
                                    <th>Tên</th>
                                    <th>Địa chỉ IP</th>
                                    <th>Nội dung</th>
                                    <th>Trạng thái</th>
                                    
                                    <th>Thời gian kiểm tra</th>
                                    <th>Loại</th>
                                   

                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="oneSys in datas">
                                    <td>{{($index + 1)}}</td>
                                    <!--<td>{{oneAcc.accId}}</td>-->
                                    <td>{{oneSys.name}}</td>
                                    <td>{{oneSys.ip}}</td>
                                    <td>{{oneSys.content}}</td>
                                   <td class="img-center">
                                        <img ng-if="oneSys.del" width="24" src="<c:url value="/admin/images/recycle.png"/>" alt="recycle">
                                        <img ng-if="oneSys.status === ${status.ACTIVE}" width="24" src="<c:url value="/admin/images/active.png"/>" alt="active">
                                        <img ng-if="oneSys.status === ${status.UNACTIVE}" width="24" src="<c:url value="/admin/images/cancel.png"/>" alt="inactive">
                                        
                                    </td>
                                  
                                    <td ng-bind="formatDateTime(oneSys.timecheck)"></td>

                                    <td>{{oneSys.type}}</td>
                                    

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

<script src="<c:url value='/admin/controller/SystemMonitor.js'/>"></script>