
(function() {
	angular
		.module('app')
		.config(translateConfig)

		.factory('customLoader', function ($http, $q, Version, $translate) {
			return function (options) {
				var deferred = $q.defer();
				/*$http.get(Version.V1 + '/sysmsg/messages', {
		    		params : {
		    			"sysDivCd" : 'PMG',
		    			"sysDivCd" : 'AUI',
						"msgTypeCd" : '05',
						"langCd" : options.key
					}
		    	}).success(function (data) {
					deferred.resolve(data.data);
				}).error(function (msg) {
					deferred.reject(msg);
				});*/

				return deferred.promise;
			}
		});


	function translateConfig($translateProvider, $translatePartialLoaderProvider) {
		$translateProvider.useLoader('customLoader');
		$translateProvider.preferredLanguage('KOR');
	}
})();