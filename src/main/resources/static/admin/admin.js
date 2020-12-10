angular.module('app').controller('adminController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

$scope.submitCreateNewProduct = function () {
       console.log($scope.newProduct.name)
         $http.post(contextPath + '/api/v1/admin', $scope.newProduct)

            .then(function (response) {
               $scope.newProduct = null;
               alert('Добавлен новый продукт');
            });
    };

    $scope.submitCreateNewCategory = function () {
           console.log($scope.newCategoryDto.title)
             $http.post(contextPath + '/api/v1/admin/newCategory', $scope.newCategoryDto)

                .then(function (response) {
                   $scope.newCategory = null;
                   alert('Добавлена новая категория');
                    $scope.categoryContentRequest();
                });
        };

     $scope.categoryContentRequest = function () {
        console.log('categories')
            $http({
                url: contextPath + '/api/v1/admin/categories',
                method: 'GET'
            })
                .then(function (response) {
                    console.log(response.data);
                    $scope.categories = response.data;
                });
        };
     $scope.categoryContentRequest();
});