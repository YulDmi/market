angular.module('app').controller('profController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.userContentRequest = function () {
            console.log('user')
                $http({
                    url: contextPath + '/api/v1/user/profiles',
                    method: 'GET'
                })
                    .then(function (response) {
                        console.log(response.data);
                        $scope.user = response.data;
                    });
            };
        $scope.updateUser = function () {
             console.log('updateUser')
                 $http.post(contextPath + '/api/v1/user/profiles', $scope.user)
                     .then(function (response) {
                         $scope.user = response.data;
                         window.alert("Изменения сохранены");
                         $scope.user = response.data;
                    });
        };
        $scope.orderItemsRequest = function () {
             console.log('orderItemsRequest')
             $http({
                url: contextPath + '/api/v1/user/orders',
                method: 'GET'
             })
            .then(function (response) {
                console.log(response.data);
                $scope.Orders = response.data;
            });
    };

             $scope.userContentRequest();
             $scope.orderItemsRequest();
            });