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
    .controller('Member_listController', Member_listController);

function Member_listController($rootScope, $scope, $timeout, Version, onmDataService, $state, $stateParams, checkUtil, checkRV, $upload, fileFactory) {

    $scope.newData = {};


    $scope.loginNm = sessionStorage.getItem('loginNm');
    $scope.loginAplyClass = sessionStorage.getItem('loginAplyClass');
    $scope.loginMemberId = sessionStorage.getItem('loginMemberId');
    $scope.loginId = sessionStorage.getItem('loginId');

    $scope.searchType = '';
    $scope.searchText = '';

    $scope.file = '파일을 첨부하세요';
    $scope.fileInfo = {};
    $scope.fileList = [];
    $scope.saveFileList = [];
    $scope.previewImgList = [];

    $scope.total_count = 0;
    $scope.itemsPerPage = 10;
    $scope.currentPage = 1; ;


    // input check : checkUtil의 종류에 맞춰 수정해야 합니다.
    $scope.$watch('newData.id', checkUtil.inputAll, true);
    $scope.$watch('newData.passwd', checkUtil.inputAll, true);
    $scope.$watch('newData.user_name', checkUtil.inputAll, true);
    $scope.$watch('newData.email', checkUtil.inputAll, true);
    $scope.$watch('newData.tel', checkUtil.inputAll, true);
    $scope.$watch('newData.create_dt', checkUtil.inputAll, true);
    $scope.$watch('newData.del_yn', checkUtil.inputAll, true);


    $scope.addInput = function (form, name, value) {
        if (value == undefined || value == null || value.toString().trim() == '') return;
        var t = document.createElement("input");
        form.appendChild(t);
        t.setAttribute("type", "hidden");
        t.setAttribute("name", name);
        t.setAttribute("value", value);
    }



    function readURL(file, index) {
        var reader = new FileReader();
        reader.onload = function(e) {
    
            $scope.previewImg0 = e.target.result;
            $scope.$apply();
        };
        reader.readAsDataURL(file);
    }
    
    $scope.onFileSelect = function($files, cmd, index) {
    
        if ($files.length === 0) {
            return;
        }
        if (fileFactory.checkFileType($files[0], "etc") || fileFactory.checkFileSize($files[0], 1024)) {
            return;
        }
    
        $scope.file = null;
        $scope.fileInfo[cmd] = $files[0].name;
        $scope.file = $files[0].name;
        $scope.fileData = $files[0];
        readURL($scope.fileData, index);
        $scope.fileName0 = $files[0].name;
        $scope.fileList.push($files[0]);
    }
    
    $scope.getImageData = function(param){
        var sendParam = { };
        sendParam.uploadPath = param.file_path;
        sendParam.orgFilename = param.file_name;
        sendParam.mimeTypeParam = 'image/jpeg';
    
        onmDataService.selectList(sendParam, '/admin/common/downloadImageFile')
          .success(function(obj){
              var blob = new Blob([obj], { type: "image/png" });
              var fileURL = URL.createObjectURL(blob); 
              $scope.previewImg0 = 'data:image/png;base64,' + obj;
        });
    }

    $scope.selectUser = function(pageno) {
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
        onmDataService.selectList(param, '/admin/onm/selectUser')
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

    $scope.selectUser(1);

    $scope.selectUserExcel = function(pageno) {
        var excelForm = document.createElement('form');
        excelForm.target = 'excelIframe';
        excelForm.method = 'POST';
        excelForm.action = Version.V1 + '/admin/onm/selectUserExcel';
        document.body.appendChild(excelForm);

        //$scope.addInput(excelForm, "date_from", $scope.date_from);
        //$scope.addInput(excelForm, "date_to", $scope.date_to);
        //$scope.addInput(excelForm, $scope.searchOption, $scope.searchWord);

        excelForm.submit();
        document.body.removeChild(excelForm);
    }


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

    $scope.getFileData();


    function updateUserRegistData(uploadInfo, atcFileId, files){
        return $upload.upload({
                url : Version.V1 + '/admin/onm/updateUser',
                method : 'POST',
                file : files,
                fields : {
                files : files
            },
            data : {
                atcFileId : atcFileId,
                uploadInfo : uploadInfo
            },
            fileName : uploadInfo.tempFileList,
            fileFormDataName : 'fileField1',
        }).success(registComplete)
          .error(registFailed);
        function registComplete(response){     return response;  }
        function registFailed(error) {     console.log(error);  }
    }

    
    $scope.updateUser = function() {
    
        // checkRV : Required & Validation의 종류에 맞춰 수정해야 합니다.
        if(! checkRV.checkRV($scope.newData.id, true, 'checkAll', 'ID 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.passwd, true, 'checkAll', '패스워드 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.user_name, true, 'checkAll', '사용자명 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.email, true, 'checkAll', '이메일 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.tel, true, 'checkAll', '전화번호 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
        if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;
      
            $scope.saveFileList = [];
            $scope.newData.tempFileList = [];
            angular.forEach($scope.fileList, function(fileInfo) {
                $scope.newData.tempFileList.push({
                  "name" : fileInfo.name
                });
            $scope.saveFileList.push(fileInfo);
        });

        $scope.newData.table_name = 'sdp.tb_user';
         updateUserRegistData($scope.newData, null, $scope.saveFileList)
             .success(function(data) {
                 alert("등록되었습니다.");
                 $state.go('member_list', {reload : true});
             });
    }
    
    
    $scope.deleteUser = function() {
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

                onmDataService.deleteObjs(sendParam, '/admin/onm/deleteUser')
                .success(function(data){
                    $state.go('member_list', {reload : true});
                }).error(function(data){
                    $state.go('member_list', {reload : true});
                });
            };
        }
    }

    $scope.deleteUser = function() {
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

                onmDataService.deleteObjs(sendParam, '/admin/onm/deleteUser')
                .success(function(data){
                    $state.go('member_list', {reload : true});
                }).error(function(data){
                    $state.go('member_list', {reload : true});
                });
            };
        }
    }

    $scope.dupID = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.dupIDResList = [];
        onmDataService.selectList(param, '/admin/onm/dupID')
        .success(function(data){
            $scope.dupIDResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.dupID();

    $scope.myRole = function() {
        var param = {};

        param.searchType = $scope.searchType;
        if($scope.searchText == ''){
            param.searchText = null;
        }else{
            param.searchText = $scope.searchText;
        }

        $scope.myRoleResList = [];
        onmDataService.selectList(param, '/admin/onm/myRole')
        .success(function(data){
            $scope.myRoleResList = data.data.rows;
        }).error(function(error){console.log(error)});
    }

    $scope.myRole();


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
            if(! checkRV.checkRV($scope.newData.id, true, 'checkAll', 'ID 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.passwd, true, 'checkAll', '패스워드 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.user_name, true, 'checkAll', '사용자명 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.email, true, 'checkAll', '이메일 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.tel, true, 'checkAll', '전화번호 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.create_dt, true, 'checkAll', '등록일시 형식을 확인하세요.')) return;
            if(! checkRV.checkRV($scope.newData.del_yn, true, 'checkAll', '삭제여부 형식을 확인하세요.')) return;

            var sendParam = {};
            sendParam.list = $scope.selectedData;

            onmDataService.updateObjs(sendParam, '/admin/onm/logout')
            .success(function(data){
                $state.go('member_list', {reload : true});
            }).error(function(data){
                $state.go('member_list', {reload : true});
            });
        }
    }

    $scope.goList = function() {
        $state.go('member_list', {reload : true});
    }

    //**********************************
    // 사용자 코딩 영역 
    //**********************************






}
