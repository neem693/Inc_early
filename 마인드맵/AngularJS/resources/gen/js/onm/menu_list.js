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
    .controller('Menu_listController', Menu_listController);

function Menu_listController($rootScope, $scope, $timeout, Version, onmDataService, $state, $stateParams, $upload, checkUtil, checkRV) {

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

    $scope.selectMenu(1);

    $scope.getFileData = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.getFileDataResList = [];
        commonDataService.selectList(param, '/admin/common/getFileData')
        .success(function(data){
            $scope.getFileDataResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.getFileData(1);

    $scope.selectMenuL = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectMenuLResList = [];
        onmDataService.selectList(param, '/admin/onm/selectMenuL')
        .success(function(data){
            $scope.selectMenuLResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectMenuL(1);

    $scope.selectMenuM = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectMenuMResList = [];
        onmDataService.selectList(param, '/admin/onm/selectMenuM')
        .success(function(data){
            $scope.selectMenuMResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectMenuM(1);

    $scope.selectMenuS = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectMenuSResList = [];
        onmDataService.selectList(param, '/admin/onm/selectMenuS')
        .success(function(data){
            $scope.selectMenuSResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectMenuS(1);

    $scope.selectMenuD = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectMenuDResList = [];
        onmDataService.selectList(param, '/admin/onm/selectMenuD')
        .success(function(data){
            $scope.selectMenuDResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectMenuD(1);

    $scope.viewMenu = function(pageno) {
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
        onmDataService.selectList(param, '/admin/onm/viewMenu')
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

    $scope.viewMenu(1);

    $scope.selectIDL = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectIDLResList = [];
        onmDataService.selectList(param, '/admin/onm/selectIDL')
        .success(function(data){
            $scope.selectIDLResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectIDL(1);

    $scope.selectIDM = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectIDMResList = [];
        onmDataService.selectList(param, '/admin/onm/selectIDM')
        .success(function(data){
            $scope.selectIDMResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectIDM(1);

    $scope.selectIDS = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectIDSResList = [];
        onmDataService.selectList(param, '/admin/onm/selectIDS')
        .success(function(data){
            $scope.selectIDSResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectIDS(1);

    $scope.selectIDD = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectIDDResList = [];
        onmDataService.selectList(param, '/admin/onm/selectIDD')
        .success(function(data){
            $scope.selectIDDResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectIDD(1);


    $scope.insertMenuL = function() {
        $scope.selectedData = [];
        $scope.selectedData.push($scope.newData);

      // checkRV : Required & Validation의 종류에 맞춰 수정해야 합니다.
        if(! checkRV.checkRV($scope.newData.menu_id, true, 'checkAll', '메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.lmenu_id, true, 'checkAll', '대메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mmenu_id, true, 'checkAll', '중메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.smenu_id, true, 'checkAll', '소메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.menu_name, true, 'checkAll', '메뉴명 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mapping_prog, true, 'checkAll', '매핑프로그램 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mapping_para, true, 'checkAll', '매핑프로그램 파라미터 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.rank, true, 'checkNumberOnly', 'rank 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.comment, true, 'checkAll', '주석 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.menu_level, true, 'checkNumberOnly', 'menu_level 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_id, true, 'checkNumberOnly', 'create_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_id, true, 'checkNumberOnly', 'update_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_dt, true, 'checkAll', '수정일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

        var sendParam = {};
        sendParam.list = $scope.selectedData;

        onmDataService.insertObjs(sendParam, '/admin/onm/insertMenuL')
        .success(function(data){
            $state.go('menu_list', {reload : true});
        }).error(function(data){
            $state.go('menu_list', {reload : true});
        });

    }


    $scope.insertMenuM = function() {
        $scope.selectedData = [];
        $scope.selectedData.push($scope.newData);

      // checkRV : Required & Validation의 종류에 맞춰 수정해야 합니다.
        if(! checkRV.checkRV($scope.newData.menu_id, true, 'checkAll', '메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.lmenu_id, true, 'checkAll', '대메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mmenu_id, true, 'checkAll', '중메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.smenu_id, true, 'checkAll', '소메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.menu_name, true, 'checkAll', '메뉴명 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mapping_prog, true, 'checkAll', '매핑프로그램 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mapping_para, true, 'checkAll', '매핑프로그램 파라미터 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.rank, true, 'checkNumberOnly', 'rank 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.comment, true, 'checkAll', '주석 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.menu_level, true, 'checkNumberOnly', 'menu_level 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_id, true, 'checkNumberOnly', 'create_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_id, true, 'checkNumberOnly', 'update_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_dt, true, 'checkAll', '수정일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

        var sendParam = {};
        sendParam.list = $scope.selectedData;

        onmDataService.insertObjs(sendParam, '/admin/onm/insertMenuM')
        .success(function(data){
            $state.go('menu_list', {reload : true});
        }).error(function(data){
            $state.go('menu_list', {reload : true});
        });

    }


    $scope.insertMenuS = function() {
        $scope.selectedData = [];
        $scope.selectedData.push($scope.newData);

      // checkRV : Required & Validation의 종류에 맞춰 수정해야 합니다.
        if(! checkRV.checkRV($scope.newData.menu_id, true, 'checkAll', '메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.lmenu_id, true, 'checkAll', '대메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mmenu_id, true, 'checkAll', '중메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.smenu_id, true, 'checkAll', '소메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.menu_name, true, 'checkAll', '메뉴명 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mapping_prog, true, 'checkAll', '매핑프로그램 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mapping_para, true, 'checkAll', '매핑프로그램 파라미터 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.rank, true, 'checkNumberOnly', 'rank 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.comment, true, 'checkAll', '주석 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.menu_level, true, 'checkNumberOnly', 'menu_level 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_id, true, 'checkNumberOnly', 'create_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_id, true, 'checkNumberOnly', 'update_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_dt, true, 'checkAll', '수정일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

        var sendParam = {};
        sendParam.list = $scope.selectedData;

        onmDataService.insertObjs(sendParam, '/admin/onm/insertMenuS')
        .success(function(data){
            $state.go('menu_list', {reload : true});
        }).error(function(data){
            $state.go('menu_list', {reload : true});
        });

    }


    $scope.insertMenuD = function() {
        $scope.selectedData = [];
        $scope.selectedData.push($scope.newData);

      // checkRV : Required & Validation의 종류에 맞춰 수정해야 합니다.
        if(! checkRV.checkRV($scope.newData.menu_id, true, 'checkAll', '메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.lmenu_id, true, 'checkAll', '대메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mmenu_id, true, 'checkAll', '중메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.smenu_id, true, 'checkAll', '소메뉴ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.menu_name, true, 'checkAll', '메뉴명 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mapping_prog, true, 'checkAll', '매핑프로그램 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.mapping_para, true, 'checkAll', '매핑프로그램 파라미터 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.rank, true, 'checkNumberOnly', 'rank 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.comment, true, 'checkAll', '주석 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.menu_level, true, 'checkNumberOnly', 'menu_level 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_id, true, 'checkNumberOnly', 'create_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_id, true, 'checkNumberOnly', 'update_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_dt, true, 'checkAll', '수정일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

        var sendParam = {};
        sendParam.list = $scope.selectedData;

        onmDataService.insertObjs(sendParam, '/admin/onm/insertMenuD')
        .success(function(data){
            $state.go('menu_list', {reload : true});
        }).error(function(data){
            $state.go('menu_list', {reload : true});
        });

    }


    $scope.updateMenuL = function() {
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

            onmDataService.updateObjs(sendParam, '/admin/onm/updateMenuL')
            .success(function(data){
                $state.go('menu_list', {reload : true});
            }).error(function(data){
                $state.go('menu_list', {reload : true});
            });
        }
    }


    $scope.updateMenuM = function() {
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

            onmDataService.updateObjs(sendParam, '/admin/onm/updateMenuM')
            .success(function(data){
                $state.go('menu_list', {reload : true});
            }).error(function(data){
                $state.go('menu_list', {reload : true});
            });
        }
    }


    $scope.updateMenuS = function() {
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

            onmDataService.updateObjs(sendParam, '/admin/onm/updateMenuS')
            .success(function(data){
                $state.go('menu_list', {reload : true});
            }).error(function(data){
                $state.go('menu_list', {reload : true});
            });
        }
    }


    $scope.updateMenuD = function() {
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

            onmDataService.updateObjs(sendParam, '/admin/onm/updateMenuD')
            .success(function(data){
                $state.go('menu_list', {reload : true});
            }).error(function(data){
                $state.go('menu_list', {reload : true});
            });
        }
    }

    $scope.deleteMenuL = function() {
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

                onmDataService.deleteObjs(sendParam, '/admin/onm/deleteMenuL')
                .success(function(data){
                    $state.go('menu_list', {reload : true});
                }).error(function(data){
                    $state.go('menu_list', {reload : true});
                });
            };
        }
    }

    $scope.deleteMenuM = function() {
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

                onmDataService.deleteObjs(sendParam, '/admin/onm/deleteMenuM')
                .success(function(data){
                    $state.go('menu_list', {reload : true});
                }).error(function(data){
                    $state.go('menu_list', {reload : true});
                });
            };
        }
    }

    $scope.deleteMenuS = function() {
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

                onmDataService.deleteObjs(sendParam, '/admin/onm/deleteMenuS')
                .success(function(data){
                    $state.go('menu_list', {reload : true});
                }).error(function(data){
                    $state.go('menu_list', {reload : true});
                });
            };
        }
    }

    $scope.deleteMenuD = function() {
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

                onmDataService.deleteObjs(sendParam, '/admin/onm/deleteMenuD')
                .success(function(data){
                    $state.go('menu_list', {reload : true});
                }).error(function(data){
                    $state.go('menu_list', {reload : true});
                });
            };
        }
    }


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

            onmDataService.updateObjs(sendParam, '/admin/onm/logout')
            .success(function(data){
                $state.go('menu_list', {reload : true});
            }).error(function(data){
                $state.go('menu_list', {reload : true});
            });
        }
    }

    $scope.goList = function() {
        $state.go('menu_list', {reload : true});
    }

    //**********************************
    // 사용자 코딩 영역 
    //**********************************






}
