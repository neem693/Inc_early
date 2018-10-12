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
				        .state('main', {
				        	url : '/main',
				        	templateUrl: 'components/admin/anpro/main.html',
				        	controller: 'MainController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })

