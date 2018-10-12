/*
   GiGA IoT Platform version 2.0

   Copyright ⓒ 2018 Anylogic corp. All rights reserved.

   This is a proprietary software of Anylogic corp, and you may not use this file except in
   compliance with license agreement with Anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of Anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/
angular
    .module('app.anpro')
    .service('anproDataService', anproDataService);

	function anproDataService($http, Version) {

	    var service = {
	    	insertObjs : insertObjs,
	    	updateObjs : updateObjs,
	    	deleteObjs : deleteObjs,
	    	selectList : selectList,
	    };

	    return service;
	    
	    
	    function insertObjs(param, url){
	    	return $http.post(Version.V1 + url, param, {

	    	}).success(insertObjsComplete)
	    	  .error(insertObjsFailed);

	    	function insertObjsComplete(response){
				return response;
			}
			function insertObjsFailed(error){
				console.log(error);
			}
	    }
	    
	    function updateObjs(param, url){
	    	return $http.post(Version.V1 + url, param, {

	    	}).success(updateObjsComplete)
	    	  .error(updateObjsFailed);

	    	function updateObjsComplete(response){
				return response;
			}
			function updateObjsFailed(error){
				console.log(error);
			}
	    }
	    	    
	    function deleteObjs(param, url){
	    	return $http.post(Version.V1 + url, param, {

	    	}).success(deleteObjsComplete)
	    	  .error(deleteObjsFailed);

	    	function deleteObjsComplete(response){
				return response;
			}
			function deleteObjsFailed(error){
				console.log(error);
			}
	    }
	    
	    function selectList(params, url){
	    	return $http.get(Version.V1 + url, {params})
	    		.success(getListComplete).error(getListFailed);
			function getListComplete(response){	return response;	}
			function getListFailed(error){		console.log(error);	}
	    }
	}
