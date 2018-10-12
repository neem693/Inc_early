
(function() {
	angular
    	.module('app')
    	.config(routeConfig);

	function routeConfig($stateProvider, $urlRouterProvider) {

		/*$urlRouterProvider.otherwise( function($injector, $location) {
			var $state = $injector.get("$state");
			$state.go("home");
		});


		$stateProvider
		.state('home', {
			url: '/home',
			controller: 'HomeController as vm',
			templateUrl: 'components/home/home.html',
			data: {requiredLogin: true}
		})
		.state('device', {
			url : '/device',
			templateUrl: 'components/device/device.html',
			controller: 'DeviceController as vm',
		})
		.state('device.list', {
			url : '/list',
			templateUrl: 'components/device/deviceList.html',
			controller: 'DeviceListController as vm',
			data: {requiredLogin: true}
		})
        .state('device.detail', {
        	url : '/detail/{deviceId}',
        	templateUrl: 'components/device/deviceDetail.html',
        	controller: 'DeviceDetailController as vm',
			data: {requiredLogin: true}
        })
        기준정보 관리
        .state('standard', {
        	url : '/standard',
	        templateUrl: 'components/standard/standard.html',
	        controller: 'StandardController as vm'
        })
        .state('standard.comCode', {
        	url : '/comCode',
        	templateUrl: 'components/standard/comCode.html',
        	controller: 'ComCodeController as vm',
			data: {requiredLogin: true}
        })
		.state('standard.platformAdmin', {
			abstract: true,
			template: '<ui-view/>',
			url : '/platformAdmin'
		})
        .state('standard.platformAdmin.adminGroup', {
        	url : '/adminGroup',
        	templateUrl: 'components/standard/adminGroup.html',
        	controller: 'AdminGroupController as vm',
			data: {requiredLogin: true}
        })
        .state('standard.platformAdmin.menuRole', {
        	url : '/menuRole',
        	templateUrl: 'components/standard/menuRole.html',
        	controller: 'MenuRoleController as vm',
			data: {requiredLogin: true}
        })
        .state('standard.platformAdmin.adminList', {
        	url : '/adminList',
        	templateUrl: 'components/standard/adminList.html',
        	controller: 'AdminListController as vm',
			data: {requiredLogin: true}
        })
        .state('standard.platformAdmin.adminDetail', {
        	url : '/adminDetail/{userId}',
        	templateUrl: 'components/standard/adminDetail.html',
        	controller: 'AdminDetailController as vm',
			data: {requiredLogin: true}
        })
        .state('portalComm', {
        	url : '/portalComm',
        	templateUrl: 'components/portalComm/portalComm.html',
        	controller: 'PortalCommController as vm'
        })
        .state('portalComm.multiLangList', {
        	url : '/multiLangList',
        	templateUrl: 'components/portalComm/multiLangList.html',
        	controller: 'MultiLangListController as vm',
			data: {requiredLogin: true}
        })
        .state('portalComm.multiLangDetail', {
        	url : '/multiLangDetail/{sysDivCd}/{msgTypeCd}/{msgId}',
        	templateUrl: 'components/portalComm/multiLangDetail.html',
        	controller: 'MultiLangDetailController as vm',
			data: {requiredLogin: true}
        })
        .state('portalComm.menuList', {
        	url : '/menuList',
        	templateUrl: 'components/portalComm/menuList.html',
        	controller: 'MenuListController as vm',
			data: {requiredLogin: true}
        })
        .state('portalComm.menuDetail', {
        	url : '/menuDetail',
        	templateUrl: 'components/portalComm/menuDetail.html',
        	controller: 'MenuDetailController as vm'
        })
         로그인,아이디,비밀번호 찾기
        .state('auth', {
        	url : '/auth',
        	templateUrl: 'components/auth/auth.html',
        	controller: 'AuthController as vm'
        })
        .state('auth.login', {
        	url : '/login',
        	templateUrl: 'components/auth/login.html',
        	controller: 'LoginController as vm'
        })
        .state('auth.findInfo', {
        	url : '/findInfo',
        	templateUrl: 'components/auth/findInfo.html',
        	controller: 'FindInfoController as vm'
        });*/
	}

})();