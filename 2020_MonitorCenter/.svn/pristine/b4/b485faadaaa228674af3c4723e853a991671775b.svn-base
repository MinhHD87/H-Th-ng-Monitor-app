var app = angular.module('listMonitorApp', ['ui.bootstrap']);
app.controller('listMonitorCtrl', function ($scope, $http, $filter) {
    $scope.reloadFilter = function (str) {
        $.blockUI({message: '<img src="' + context + '/admin/images/indicator_48.gif" />'});
        $http({
            method: "POST",
            url: urlBase + "/admin/monitor/rest/data",
            params: {crPage: $scope.crPage, name: $scope.name, parent: $scope.parent, ip: $scope.ip, pos: $scope.pos, maxRow: $scope.maxRow}
        }).then(function Succes(resp) {
            if (resp !== undefined && typeof resp === "object") {
                $scope.result = resp.data.result;
                if ($scope.result.code === nologin) {
                    $.unblockUI();
                    location.href = context + adm_login_uri;
                } else {
                    $scope.datas = resp.data.datas;
                    $scope.totalRow = resp.data.totalRow;
                    $scope.roles = resp.data.roles;
                    if (!angular.isUndefined(str) && str !== '') {
                        $scope.result.message = str;
                    }
                    blink_text('callback_info', 5000);
                    $.unblockUI();
                }
            }
        }, function Error(response) {
            $.unblockUI();
            $scope.message = response.status;
        });
    };
    $scope.pageChanged = function () {
        $scope.crPage = this.crPage;
    };
    $scope.$watch('crPage + crPage', function () {
        $scope.reloadFilter('');
    });
    $scope.reset = function () {
        $scope.name = '';
        $scope.ip = '';
        $scope.pos = '';
        $scope.parent = '';
        $scope.reloadFilter();
    };
    $scope.deleteforEver = function (id) {
        $("#result_model").text('');
        jConfirm('<b style="color:red">Bạn chắc chắn muốn xóa?</b>', 'Confirm', function (r) {
            if (r) {
                $http({
                    method: "POST",
                    url: urlBase + "/admin/monitor/rest/deleteForEver",
                    params: {id: id}
                }).then(function Succes(resp) {
                    if (resp !== undefined && typeof resp === "object") {
                        $scope.result = resp.data.result;
                        if ($scope.result.code === nologin) {
                            location.href = context + adm_login_uri;
                        } else {
                            if ($scope.result.code === success) {
                                $scope.reloadFilter($scope.result.message);
                            }
                        }
                    }
                }, function Error(response) {
                    $scope.message = response.status;
                });
            }
        });
    };
    $scope.updateMaxRow = function () {
        $scope.crPage = 1;
        $scope.reloadFilter();
    };
    $scope.formatDate = function (date) {
        if (!angular.isUndefined(date) && date !== null)
            return $filter('date')(new Date(date), 'dd/MM/yyyy');
        else
            return "";
    };
    $scope.formatDateTime = function (date) {
        if (!angular.isUndefined(date) && date !== null)
            return $filter('date')(new Date(date), 'dd/MM/yyyy HH:mm:ss');
        else
            return "";
    };
});