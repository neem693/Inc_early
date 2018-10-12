/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


angular.module('app.onm')
    .controller('LoginController', LoginController);

function LoginController($rootScope, $scope, $timeout, Version, onmDataService, $state, $stateParams) {

    $scope.newData = {};


    $scope.loginNm = sessionStorage.getItem('loginNm');
    $scope.loginAplyClass = sessionStorage.getItem('loginAplyClass');
    $scope.loginMemberId = sessionStorage.getItem('loginMemberId');
    $scope.loginId = sessionStorage.getItem('loginId');

    $scope.searchType = '';
    $scope.searchText = '';

    $scope.total_count = 0;
    $scope.itemsPerPage = 10;
    $scope.currentPage = 1; ;


    $scope.login = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.loginResList = [];
        onmDataService.selectList(param, '/admin/onm/login')
        .success(function(data){
            $scope.loginResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.login();

    $scope.goList = function() {
        $state.go('login', {reload : true});
    }

    //**********************************
    // 사용자 코딩 영역 
    //**********************************






}
