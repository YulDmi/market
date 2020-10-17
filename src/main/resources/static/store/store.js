angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

        $scope.fillTable = function (pageIndex = 1) {

                $http ({
                 url: contextPath + '/api/v1/products',
                             method: "GET",
                              params: {
                              name_product: $scope.filter ? $scope.filter.name_product : null,
                              min_price: $scope.filter ? $scope.filter.min_price : null,
                              max_price: $scope.filter ? $scope.filter.max_price : null,
                              p: pageIndex
                              }
                })
                       .then(function (response) {
                        $scope.ProductsPage = response.data;
                        $scope.PaginationArray = $scope.generatePagesInd(1, $scope.ProductsPage.totalPages)

                   //     $scope.newProduct = null;
                    });
            };

        $scope.generatePagesInd = function(startPage, endPage){
            let arr = [];
            for(let i = startPage; i < endPage+1; i++){
                arr.push(i)
            }
            return arr;
        }

    $scope.fillTable();


});