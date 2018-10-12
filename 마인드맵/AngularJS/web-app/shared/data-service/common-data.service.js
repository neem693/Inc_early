
/**
 * 공통 데이터 서비스
 */
angular.module('app.shared', [])
	.service('commonDataService', commonDataService);

	function commonDataService($http, $upload, $translate, $q, Version, $translate) {
		var service = {
			listDtlCd: listDtlCd,
			dtlCodeList: dtlCodeList,
			dtlCodeList_M: dtlCodeList_M,
			dtlCodeList_Event : dtlCodeList_Event,
			listSvcContext: listSvcContext,
			saveFile : saveFile
	    };

	    return service;

	    function saveFile(deviceNo, atcFileId, files){	 

	    	return $upload.upload({
				url : Version.V1 + '/App/saveFile',
				method : 'POST',
				file : files,
				fields : {
					files : files
				},
				// data 속성으로 별도의 데이터를 보냄.
				data : {
					atcFileId : atcFileId,
					deviceNo : deviceNo
				},
				fileFormDataName : 'fileField1',
			}).success(saveFileComplete)
			  .error(saveFileFailed);
			
			
	    	function saveFileComplete(response){
	    		//alert("success");
	    		return response;
	    	}

	    	function saveFileFailed(error) {
	    		//alert("fail");
	    		console.log(error);
	        }
	       
	    }

	    /**
	     * 상세코드 조회
	     */
	    function listDtlCd(cdGroupId) {
	    	var deferred = $q.defer();
            $http.get(Version.V1 + '/common/listDtlCd', {
            	params : {
	    			"cdGroupId" : cdGroupId,
	    			"langCd" : $translate.use() || 'KOR'
				}
            }).success(function (response) {
            	deferred.resolve(response.data.rows);
            }).error(function (msg) {
                deferred.reject(msg);
            });
            return deferred.promise;
	    }


	    function dtlCodeList(cdGroupId) {
            var deferred = $q.defer();
            $http.get(Version.V1 + '/common/listDtlCd', {
            	params : {
	    			"cdGroupId" : cdGroupId,
	    			"langCd" : $translate.use() || 'KOR'
				}
            }).success(function (response) {
                /*var currentLang = $translate.proposedLanguage() || $translate.use();
                var codeList = [];
                angular.forEach(response.data.rows, function (code) {
                    if (code.langCd == currentLang) {
                        codeList.push(code);
                    }
                });
                */
            	deferred.resolve(response.data.rows);
            }).error(function (msg) {
                deferred.reject(msg);
            });
            return deferred.promise;
	    }

	    /**
	     * 상세코드 조회 - MASTER API
	     */
	    function dtlCodeList_M(cdGroupId) {
            var deferred = $q.defer();
            $http.get(Version.V1 + '/common/listDtlCd_M', {
            	params : {
	    			"cdGroupId" : cdGroupId,
	    			"useYn" : "Y",
	    			"langCd" : $translate.use() || 'KOR'
				}
            }).success(function (response) {
            	deferred.resolve(response.data.rows);
            }).error(function (msg) {
                deferred.reject(msg);
            });
            return deferred.promise;
	    }
	    
	    function dtlCodeList_Event(cdGroupId) {
            var deferred = $q.defer();
            $http.get(Version.V1 + '/common/listDtlCd_Event', {
            	params : {
	    			"cdGroupId" : cdGroupId,	    			
	    			"langCd" : $translate.use() || 'KOR'
				}
            }).success(function (response) {
            	deferred.resolve(response.data.rows);
            }).error(function (msg) {
                deferred.reject(msg);
            });
            return deferred.promise;
	    }

	    function listSvcContext(){
	
	    	return $http.get(Version.V1 + '/common/listSvcContext', {
	    		params : {
	    			"userId" : sessionStorage.getItem('userId')
				}
	    	})
	    	.success(function(response){
	    		return response;
	    	})
            .error(function(error) {
	            //console.log(error);
	        });
	    }
	}