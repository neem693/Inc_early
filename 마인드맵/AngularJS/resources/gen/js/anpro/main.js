/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


angular.module('app.anpro')
    .controller('MainController', MainController);

function MainController($rootScope, $scope, $timeout, Version, anproDataService, $state, $stateParams, checkUtil, checkRV) {

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
    $scope.$watch('newData.menu_id', checkUtil.inputAll, true);
    $scope.$watch('newData.lmenu_id', checkUtil.inputAll, true);
    $scope.$watch('newData.mmenu_id', checkUtil.inputAll, true);
    $scope.$watch('newData.smenu_id', checkUtil.inputAll, true);
    $scope.$watch('newData.menu_name', checkUtil.inputAll, true);
    $scope.$watch('newData.mapping_prog', checkUtil.inputAll, true);
    $scope.$watch('newData.mapping_para', checkUtil.inputAll, true);
    $scope.$watch('newData.rank', checkUtil.inputNumberOnly, true);
    $scope.$watch('newData.comment', checkUtil.inputAll, true);
    $scope.$watch('newData.menu_level', checkUtil.inputNumberOnly, true);
    $scope.$watch('newData.create_id', checkUtil.inputNumberOnly, true);
    $scope.$watch('newData.create_dt', checkUtil.inputAll, true);
    $scope.$watch('newData.update_id', checkUtil.inputNumberOnly, true);
    $scope.$watch('newData.update_dt', checkUtil.inputAll, true);
    $scope.$watch('newData.del_yn', checkUtil.inputAll, true);

    $scope.selectMyMenu = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectMyMenuResList = [];
        anproDataService.selectList(param, '/admin/anpro/selectMyMenu')
        .success(function(data){
            $scope.selectMyMenuResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectMyMenu();


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
            if(! checkRV.checkRV($scope.newData.menu_id, false, 'checkAll', '메뉴ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.lmenu_id, true, 'checkAll', '대메뉴ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.mmenu_id, true, 'checkAll', '중메뉴ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.smenu_id, true, 'checkAll', '소메뉴ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.menu_name, true, 'checkAll', '메뉴명 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.mapping_prog, true, 'checkAll', '매핑프로그램 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.mapping_para, true, 'checkAll', '매핑프로그램 파라미터 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.rank, true, 'checkNumberOnly', '순번 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.comment, true, 'checkAll', '주석 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.menu_level, true, 'checkNumberOnly', '메뉴레벨 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.create_id, true, 'checkNumberOnly', '등록자ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.update_id, true, 'checkNumberOnly', '수정자ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.update_dt, true, 'checkAll', '수정일시 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

            var sendParam = {};
            sendParam.list = $scope.selectedData;

            anproDataService.updateObjs(sendParam, '/admin/anpro/logout')
            .success(function(data){
                $state.go('main', {reload : true});
            }).error(function(data){
                $state.go('main', {reload : true});
            });
        }
    }

    $scope.goList = function() {
        $state.go('main', {reload : true});
    }

    //**********************************
    // 사용자 코딩 영역 
    //**********************************






}
