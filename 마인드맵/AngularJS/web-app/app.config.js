angular.module('app').factory('authInterceptor',
		function($location, $q, $window) {

			return {
				request : function(config) {
					config.headers = config.headers || {};

					//config.headers.Authorization = 'xxxxsdgdsgds-xxxx';
					config.headers.Authorization = sessionStorage.getItem('authToken');
					
					return config;
				}
			};
		})
		.config(
		function($httpProvider) {
			 $httpProvider.interceptors.push('authInterceptor');
			$httpProvider.interceptors.push(function($q, $rootScope, $location,
					$injector) {
				return {
					'response' : function(response) {
						
						return response;
					},
					'responseError' : function(rejection) {
						return $q.reject(rejection);
					}
				};
			});
		})
		
		.config(function($provide) {
	$provide.decorator('$state', function($delegate, $stateParams) {
		$delegate.forceReload = function() {
			return $delegate.go($delegate.current, $stateParams, {
				reload : true,
				inherit : false,
				notify : true
			});
		};
		return $delegate;
	});
});