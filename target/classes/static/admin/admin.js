angular.module('app').controller('adminController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

$scope.submitCreateNewProduct = function () {
       console.log($scope.newProduct.name)
        $http({
            url: contextPath + '/api/v1/products',
            method: 'POST',
            params: {
                name: $scope.newProduct != null ? $scope.newProduct.name : '',
                categoryId: $scope.newProduct !=null ? $scope.newProduct.categoryId : '',
                cost: $scope.newProduct != null ? $scope.newProduct.cost : ''
            }
        })
            .then(function (response) {
               $scope.newProduct = null;
               alert('Добавлен новый продукт');
            });
    };

//    $scope.submitCreateNewProduct = function () {
//        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
//            .then(function (response) {
//                $scope.newProduct = null;
//                alert('Добавлен новый продукт');
//            });
//    };

     $scope.categoryContentRequest = function () {
        console.log('categories')
            $http({
                url: contextPath + '/api/v1/categories',
                method: 'GET'
            })
                .then(function (response) {
                    console.log(response.data);
                    $scope.categories = response.data;
                });
        };
     $scope.categoryContentRequest();
});