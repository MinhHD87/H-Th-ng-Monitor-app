var app = angular.module('recycleAccountApp', ['ui.bootstrap']);
app.controller('recycleAccountCtrl', function ($scope, $http, $filter) {
    $scope.reloadFilter = function (str) {
        $.blockUI({message: '<img src="' + context + '/admin/images/indicator_48.gif" />'});
        $http({
            method: "POST",
            url: urlBase + "/admin/sys-account/rest/recycle-data",
            params: {crPage: $scope.crPage, key: $scope.key, phone: $scope.phone, email: $scope.email, status: $scope.status, maxRow: $scope.maxRow}
        }).then(function Succes(resp) {
            if (resp !== undefined && typeof resp === "object") {
                $scope.result = resp.data.result;
                if ($scope.result.code === nologin) {
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
            $scope.message = response.message;
        });
    };
    $scope.$watch('crPage + crPage', function () {
        $scope.reloadFilter();
    });
    $scope.pageChanged = function () {
        $scope.crPage = this.crPage;
    };
    $scope.deleteforEver = function (id) {
        $("#result_model").text('');
        jConfirm('<b style="color:red">Bạn chắc chắn muốn xóa vĩnh viễn Tài Khoản này?</b>', 'Confirm', function (r) {
            if (r) {
                $http({
                    method: "POST",
                    url: urlBase + "/admin/sys-account/rest/deleteForEver",
                    params: {id: id}
                }).then(function Succes(resp) {
                    if (resp.data !== undefined && typeof resp.data === "object") {
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
                    $scope.message = response.message;
                });
            } else
                console.log('Cai noi gi the')
        });
    };
    $scope.undoDelete = function (id) {
        jConfirm('<b style="color:red">Bạn chắc chắn muốn khôi phục Tài Khoản này?</b>', 'Confirm', function (r) {
            if (r) {
                $http({
                    method: "POST",
                    url: urlBase + "/admin/sys-account/rest/undo-account",
                    params: {id: id}
                }).then(function Succes(resp) {
                    if (resp !== undefined && typeof resp === "object") {
                         $scope.result = resp.data;
                        if ($scope.result.code === -1) {
                            location.href = context + adm_login_uri;
                        } else {
                            $scope.reloadFilter($scope.result.message);
                        }
                    } else {
                        console.log("process undoDelete Error")
                    }
                }, function Error(response) {
                    $scope.message = response.message;
                });
            }
        });
    };
    $scope.formatDateTime = function (date) {
        if (!angular.isUndefined(date) && date !== null)
            return $filter('date')(new Date(date), 'dd/MM/yyyy HH:mm:ss')
        else
            return "";
    };
});