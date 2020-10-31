angular.module('app').controller('regController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.tryToReg = function () {
    console.log('tryToReg');
        $http.post(contextPath + '/reg', $scope.user)
            .then(function successCallback(response) {
                   window.alert("Регистрация прошла успешно. Для продолжения нажмите кнопку Войти);
           },
            function errorCallback(response) {
              window.alert("Пользователь с таким именем уже существует. Войдите или зарегистрируйтесь новым именем");
                $scope.clearUser();
           });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        if ($scope.user.email) {
                    $scope.user.email = null;
                }
    };

    $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
            return true;
        } else {
            return false;
        }
    };
});