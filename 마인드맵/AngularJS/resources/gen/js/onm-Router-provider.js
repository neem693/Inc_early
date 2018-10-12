/*
   GiGA IoT Platform version 2.0

   Copyright ⓒ 2018 Anylogic corp. All rights reserved.

   This is a proprietary software of Anylogic corp, and you may not use this file except in
   compliance with license agreement with Anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of Anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/

  // stateGo에서 parameter를 전달해야 하는 경우 param을 수정해 준다.
				        .state('login', {
				        	url : '/login',
				        	templateUrl: 'components/admin/onm/login.html',
				        	controller: 'LoginController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })

				        .state('member_add', {
				        	url : '/member_add',
				        	templateUrl: 'components/admin/onm/member_add.html',
				        	controller: 'Member_addController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })

				        .state('member_list', {
				        	url : '/member_list',
				        	templateUrl: 'components/admin/onm/member_list.html',
				        	controller: 'Member_listController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })

				        .state('role_add', {
				        	url : '/role_add',
				        	templateUrl: 'components/admin/onm/role_add.html',
				        	controller: 'Role_addController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })

				        .state('role_list', {
				        	url : '/role_list',
				        	templateUrl: 'components/admin/onm/role_list.html',
				        	controller: 'Role_listController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })

				        .state('main', {
				        	url : '/main',
				        	templateUrl: 'components/admin/onm/main.html',
				        	controller: 'MainController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })

