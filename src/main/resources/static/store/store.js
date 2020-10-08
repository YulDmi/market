angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillTable = function () {
        console.log('fill');
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.Products = response.data;
                $scope.newProduct = null;
            });
    };

    $scope.fillTable();

    $scope.submitCreateNewProduct = function () {
         $http({
             url: contextPath + '/api/v1/products',
             method: "GET",
             params: {name_product: $scope.newProduct.name_product, min_price: $scope.newProduct.min_price, max_price: $scope.newProduct.max_price}
         }).then(function (response) {
            $scope.Products = response.data;
         });
     };
});