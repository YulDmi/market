angular.module('app').controller('regController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.tryToReg = function () {
    console.log('TryToReg')
        $http.post(contextPath + '/reg', $scope.user)
            .then(function successCallback(response) {

                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.user.email = null;
                    window.alert("Регистрация прошла успешно. Нажмите конопку войти");
            }, function errorCallback(response) {
                window.alert("Данное имя уже используется. Войдите или выберите другое имя.");
                $scope.clearUser();
            });
    };
});