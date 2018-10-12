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
    .controller('Role_addController', Role_addController);

function Role_addController($rootScope, $scope, $timeout, Version, onmDataService, $state, $stateParams, checkUtil, checkRV) {

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


    // input check : checkUtil의 종류에 맞춰 수정해야 합니다.
    $scope.$watch('newData.auth_name', checkUtil.inputAll, true);
    $scope.$watch('newData.etc', checkUtil.inputAll, true);
    $scope.$watch('newData.del_yn', checkUtil.inputAll, true);


    $scope.insertAuth = function() {
        $scope.selectedData = [];
        $scope.selectedData.push($scope.newData);

      // checkRV : Required & Validation의 종류에 맞춰 수정해야 합니다.
        if(! checkRV.checkRV($scope.newData.auth_name, true, 'checkAll', '권한명 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.etc, true, 'checkAll', '기타 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

        var sendParam = {};
        sendParam.list = $scope.selectedData;

        onmDataService.insertObjs(sendParam, '/admin/onm/insertAuth')
        .success(function(data){
            $state.go('role_list', {reload : true});
        }).error(function(data){
            $state.go('role_list', {reload : true});
        });

    }


    $scope.insertAuthMenu = function() {
        $scope.selectedData = [];
        $scope.selectedData.push($scope.newData);

      // checkRV : Required & Validation의 종류에 맞춰 수정해야 합니다.
        if(! checkRV.checkRV($scope.newData.auth_name, true, 'checkAll', '권한명 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.etc, true, 'checkAll', '기타 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

        var sendParam = {};
        sendParam.list = $scope.selectedData;

        onmDataService.insertObjs(sendParam, '/admin/onm/insertAuthMenu')
        .success(function(data){
            $state.go('role_list', {reload : true});
        }).error(function(data){
            $state.go('role_list', {reload : true});
        });

    }

    $scope.selectMenu = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectMenuResList = [];
        onmDataService.selectList(param, '/admin/onm/selectMenu')
        .success(function(data){
            $scope.selectMenuResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectMenu();


    $scope.logout = function() {
        $scope.selectedData = [];
        for(var i=0;i<$scope.resList.length;i++){
            if($scope.resList[i].checked==true){
                $scope.selectedData.push($scope.resList[i])
            }
        }

        if($scope.selectedData.length <1){
            alert('먼저 업데이트할 데이터를 선택하세요.');
            return;
        }else{
            // checkRV : Required & Validation의 종류에 맞춰 수정해야 합니다.
            if(! checkRV.checkRV($scope.newData.auth_name, true, 'checkAll', '권한명 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.etc, true, 'checkAll', '기타 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

            var sendParam = {};
            sendParam.list = $scope.selectedData;

            onmDataService.updateObjs(sendParam, '/admin/onm/logout')
            .success(function(data){
                $state.go('role_list', {reload : true});
            }).error(function(data){
                $state.go('role_list', {reload : true});
            });
        }
    }

    $scope.goList = function() {
        $state.go('role_list', {reload : true});
    }

    //**********************************
    // 사용자 코딩 영역 
    //**********************************






}
