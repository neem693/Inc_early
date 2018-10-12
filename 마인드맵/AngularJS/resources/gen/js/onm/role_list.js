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
    .controller('Role_listController', Role_listController);

function Role_listController($rootScope, $scope, $timeout, Version, onmDataService, $state, $stateParams, checkUtil, checkRV) {

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

    $scope.selectAuth = function(pageno) {
        var param = {};
        param.pageNum = pageno;
        param.pageCon = 10;

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.resList = [];
        onmDataService.selectList(param, '/admin/onm/selectAuth')
        .success(function(data){
            if(data.data.rows.length < 1){
                $scope.resList  = [];
                $scope.total_count  = 0;
                return;
            }
            $scope.resList = data.data.rows;
            for (var k = 0; k < $scope.resList.length; k++) {
                $scope.resList[k].pageno = (k + 1) + ((1 - 1) * 10);
            }

            $scope.total_count = data.data.rows[0].totalCnt;
        }).error(function(error){console.log(error)});
    }

    $scope.selectAuth(1);


    $scope.updateAuth = function() {
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

            onmDataService.updateObjs(sendParam, '/admin/onm/updateAuth')
            .success(function(data){
                $state.go('role_list', {reload : true});
            }).error(function(data){
                $state.go('role_list', {reload : true});
            });
        }
    }

    $scope.deleteAuth = function() {
        $scope.selectedData = [];
        for(var i=0;i<$scope.resList.length;i++){
            if($scope.resList[i].checked==true){
                $scope.selectedData.push($scope.resList[i])
            }
        } 
        if($scope.selectedData.length <1){
            alert('먼저 삭제할 데이터를 선택하세요.');
            return;
        }else{
            if(confirm('정말 삭제하시겠습니까?')){
                var sendParam = {};
                sendParam.list = $scope.selectedData;

                onmDataService.deleteObjs(sendParam, '/admin/onm/deleteAuth')
                .success(function(data){
                    $state.go('role_list', {reload : true});
                }).error(function(data){
                    $state.go('role_list', {reload : true});
                });
            };
        }
    }

    $scope.viewAuth = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.viewAuthResList = [];
        onmDataService.selectList(param, '/admin/onm/viewAuth')
        .success(function(data){
            $scope.viewAuthResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.viewAuth();

    $scope.selectMenuAll = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectMenuAllResList = [];
        onmDataService.selectList(param, '/admin/onm/selectMenuAll')
        .success(function(data){
            $scope.selectMenuAllResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectMenuAll();


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
