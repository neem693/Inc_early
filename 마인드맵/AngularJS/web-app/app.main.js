
angular.module('app')
    .controller('MainController', MainController);

	function MainController($rootScope, $scope, $state, runtimeStates, mainDataService) {

		var vm = this;
		vm.loginNm = sessionStorage.getItem('loginNm');
		var loadScript = function() {

			var script = document.createElement('script');
			script.type = 'text/javascript';
			script.src = 'publish/js/common.js';
			document.body.appendChild(script);
			

		}
		loadScript();
		
		$scope.userNm = sessionStorage.getItem('loginNm');
		 $rootScope.companyName = sessionStorage.getItem('company_name');
		 
		 

			
		 
		 
	}

