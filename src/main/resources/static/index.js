(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
        .when('/main', {
                            templateUrl: 'main/main.html',
                    })
            .when('/auth', {
                            templateUrl: 'auth/auth.html',
                            controller: 'authController'
            })
            .when('/reg', {
                            templateUrl: 'reg/reg.html',
                            controller: 'regController'
            })
           .when('/admin', {
                           templateUrl: 'admin/admin.html',
                           controller: 'adminController'
            })
           .when('/cart', {
                           templateUrl: 'cart/cart.html',
                           controller: 'cartController'
           })
           .when('/prof', {
                          templateUrl: 'prof/prof.html',
                          controller: 'profController'
           })
           .when('/exit', {
                          templateUrl: 'exit/exit.html',
                          controller: 'exitController'
           })
           .when('/store', {
                           templateUrl: 'store/store.html',
                           controller: 'storeController'
           });

        $httpProvider.interceptors.push(function ($q, $location) {
            return {
                'responseError': function (rejection, $localStorage, $http) {
                    var defer = $q.defer();
                    if (rejection.status == 401 || rejection.status == 403) {
                        console.log('error: 401-403');
                        $location.path('/auth');
                        if (!(localStorage.getItem("localUser") === null)) {
                            delete $localStorage.currentUser;
                            $http.defaults.headers.common.Authorization = '';
                            console.log('zxc');
                        }
                        console.log(rejection.data);
                        var answer = JSON.parse(rejection.data);
                        console.log(answer);
                        // window.alert(answer.message);
                    }
                    defer.reject(rejection);
                    return defer.promise;
                }
            };
        });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }
    }
})();

angular.module('app').controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';
    $scope.isAuth = function () {
        if($localStorage.currentUser) {
            return true;
        } else {
            return false
         }
    };

   $scope.tryToLogout = function () {
        if($localStorage.currentUser) {
          $scope.clearUser();
         }
      };

      $scope.clearUser = function () {
          delete $localStorage.currentUser;
          $http.defaults.headers.common.Authorization = '';
      };


    });
