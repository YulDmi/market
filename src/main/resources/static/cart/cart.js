angular.module('app').controller('cartController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.cartContentRequest = function () {
    console.log('cart')
        $http({
            url: contextPath + '/api/v1/user/cart',
            method: 'GET'
        })
            .then(function (response) {
                console.log(response.data);
                $scope.cart = response.data;
            });
    };

    $scope.decrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/user/cart/dec/' + productId,
            method: 'GET'
        })
            .then(function (response) {
                $scope.cartContentRequest();
            });
    };

    $scope.removeItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/user/cart/remove/' + productId,
            method: 'GET'
        })
            .then(function (response) {
                $scope.cartContentRequest();
            });
    };

    $scope.incrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/user/cart/add/' + productId,
            method: 'GET'
        })
            .then(function (response) {
                $scope.cartContentRequest();
            });
    };

     $scope.createOrder = function () {
                  console.log('createOrder')
                      $http({
                          url: contextPath + '/api/v1/user/orders',
                          method: 'POST',
                          params: {
                              recipient: $scope.order ? $scope.order.recipient : null,
                              phone: $scope.order ? $scope.order.phone : null,
                             address: $scope.order ? $scope.order.address : null,
                          }
                      })
                          .then(function (response) {
                              $scope.order = response.data;
                            window.alert("Ваш заказ успешно оформлен");
                            $scope.cartContentRequest();
                          });
                  };


    $scope.cartContentRequest();
});