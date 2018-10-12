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
    .controller('Code_listController', Code_listController);

function Code_listController($rootScope, $scope, $timeout, Version, onmDataService, $state, $stateParams, $upload, checkUtil, checkRV) {

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
    $scope.$watch('newData.codegroup_id', checkUtil.inputNumberOnly, true);
    $scope.$watch('newData.code_name', checkUtil.inputAll, true);
    $scope.$watch('newData.code_level', checkUtil.inputNumberOnly, true);
    $scope.$watch('newData.rank', checkUtil.inputNumberOnly, true);
    $scope.$watch('newData.description', checkUtil.inputAll, true);
    $scope.$watch('newData.create_id', checkUtil.inputNumberOnly, true);
    $scope.$watch('newData.create_dt', checkUtil.inputAll, true);
    $scope.$watch('newData.update_id', checkUtil.inputNumberOnly, true);
    $scope.$watch('newData.update_dt', checkUtil.inputAll, true);
    $scope.$watch('newData.del_yn', checkUtil.inputAll, true);

    $scope.selectCode = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectCodeResList = [];
        onmDataService.selectList(param, '/admin/onm/selectCode')
        .success(function(data){
            $scope.selectCodeResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectCode(1);

    $scope.selectCodeGroup = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectCodeGroupResList = [];
        onmDataService.selectList(param, '/admin/onm/selectCodeGroup')
        .success(function(data){
            $scope.selectCodeGroupResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectCodeGroup(1);

    $scope.selectCodeSearch = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectCodeSearchResList = [];
        onmDataService.selectList(param, '/admin/onm/selectCodeSearch')
        .success(function(data){
            $scope.selectCodeSearchResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectCodeSearch(1);

    $scope.selectCodeGroupSearch = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.selectCodeGroupSearchResList = [];
        onmDataService.selectList(param, '/admin/onm/selectCodeGroupSearch')
        .success(function(data){
            $scope.selectCodeGroupSearchResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.selectCodeGroupSearch(1);


    $scope.insertCode = function() {
        $scope.selectedData = [];
        $scope.selectedData.push($scope.newData);

      // checkRV : Required & Validation의 종류에 맞춰 수정해야 합니다.
        if(! checkRV.checkRV($scope.newData.codegroup_id, true, 'checkNumberOnly', 'codegroup_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.code_name, true, 'checkAll', '코드명 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.code_level, true, 'checkNumberOnly', 'code_level 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.rank, true, 'checkNumberOnly', 'rank 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.description, true, 'checkAll', '설명 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_id, true, 'checkNumberOnly', 'create_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_id, true, 'checkNumberOnly', 'update_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_dt, true, 'checkAll', '수정일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

        var sendParam = {};
        sendParam.list = $scope.selectedData;

        onmDataService.insertObjs(sendParam, '/admin/onm/insertCode')
        .success(function(data){
            $state.go('code_list', {reload : true});
        }).error(function(data){
            $state.go('code_list', {reload : true});
        });

    }


    $scope.insertCodeGroup = function() {
        $scope.selectedData = [];
        $scope.selectedData.push($scope.newData);

      // checkRV : Required & Validation의 종류에 맞춰 수정해야 합니다.
        if(! checkRV.checkRV($scope.newData.codegroup_id, true, 'checkNumberOnly', 'codegroup_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.code_name, true, 'checkAll', '코드명 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.code_level, true, 'checkNumberOnly', 'code_level 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.rank, true, 'checkNumberOnly', 'rank 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.description, true, 'checkAll', '설명 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_id, true, 'checkNumberOnly', 'create_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_id, true, 'checkNumberOnly', 'update_id 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.update_dt, true, 'checkAll', '수정일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

        var sendParam = {};
        sendParam.list = $scope.selectedData;

        onmDataService.insertObjs(sendParam, '/admin/onm/insertCodeGroup')
        .success(function(data){
            $state.go('code_list', {reload : true});
        }).error(function(data){
            $state.go('code_list', {reload : true});
        });

    }


    $scope.updateCode = function() {
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
            if(! checkRV.checkRV($scope.newData.codegroup_id, true, 'checkNumberOnly', '코드그룹ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.code_name, true, 'checkAll', '코드명 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.code_level, true, 'checkNumberOnly', '코드레벨 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.rank, true, 'checkNumberOnly', '순서 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.description, true, 'checkAll', '설명 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.create_id, true, 'checkNumberOnly', '등록자ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.update_id, true, 'checkNumberOnly', '수정자ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.update_dt, true, 'checkAll', '수정일시 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

            var sendParam = {};
            sendParam.list = $scope.selectedData;

            onmDataService.updateObjs(sendParam, '/admin/onm/updateCode')
            .success(function(data){
                $state.go('code_list', {reload : true});
            }).error(function(data){
                $state.go('code_list', {reload : true});
            });
        }
    }


    $scope.updateCodeGroup = function() {
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
            if(! checkRV.checkRV($scope.newData.codegroup_id, true, 'checkNumberOnly', '코드그룹ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.code_name, true, 'checkAll', '코드명 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.code_level, true, 'checkNumberOnly', '코드레벨 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.rank, true, 'checkNumberOnly', '순서 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.description, true, 'checkAll', '설명 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.create_id, true, 'checkNumberOnly', '등록자ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.update_id, true, 'checkNumberOnly', '수정자ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.update_dt, true, 'checkAll', '수정일시 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

            var sendParam = {};
            sendParam.list = $scope.selectedData;

            onmDataService.updateObjs(sendParam, '/admin/onm/updateCodeGroup')
            .success(function(data){
                $state.go('code_list', {reload : true});
            }).error(function(data){
                $state.go('code_list', {reload : true});
            });
        }
    }

    $scope.deleteCode = function() {
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

                onmDataService.deleteObjs(sendParam, '/admin/onm/deleteCode')
                .success(function(data){
                    $state.go('code_list', {reload : true});
                }).error(function(data){
                    $state.go('code_list', {reload : true});
                });
            };
        }
    }

    $scope.deleteCodeGroup = function() {
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

                onmDataService.deleteObjs(sendParam, '/admin/onm/deleteCodeGroup')
                .success(function(data){
                    $state.go('code_list', {reload : true});
                }).error(function(data){
                    $state.go('code_list', {reload : true});
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
            if(! checkRV.checkRV($scope.newData.codegroup_id, true, 'checkNumberOnly', '코드그룹ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.code_name, true, 'checkAll', '코드명 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.code_level, true, 'checkNumberOnly', '코드레벨 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.rank, true, 'checkNumberOnly', '순서 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.description, true, 'checkAll', '설명 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.create_id, true, 'checkNumberOnly', '등록자ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.update_id, true, 'checkNumberOnly', '수정자ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.update_dt, true, 'checkAll', '수정일시 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

            var sendParam = {};
            sendParam.list = $scope.selectedData;

            onmDataService.updateObjs(sendParam, '/admin/onm/logout')
            .success(function(data){
                $state.go('code_list', {reload : true});
            }).error(function(data){
                $state.go('code_list', {reload : true});
            });
        }
    }

    $scope.goList = function() {
        $state.go('code_list', {reload : true});
    }

    //**********************************
    // 사용자 코딩 영역 
    //**********************************






}
