
angular
	.module('app', [
	    'app.shared',
	    'app.commSvc',
	    'app.navigationLinkDirective',
	    'app.auth',	   
	    'app.mainIndex',
	    'app.systemConfig',	 
	    'app.member',	 
	   /* 'app.board',	*/ 
	    'app.booking',
	    'app.calc',
	    'app.tenant',
	    'app.tour',
	    'app.role',
	    'app.onm',
	    'angularUtils.directives.dirPagination',
		'ui.router',
		'angular-storage',
		'pascalprecht.translate'
	]).config(function($provide){
		$provide.decorator('taOptions', ['taRegisterTool', 'taToolFunctions','$delegate', function(taRegisterTool,taToolFunctions, taOptions){
			// $delegate is the taOptions we are decorating
			// register the tool with textAngular
			
			taRegisterTool('uploadImage', {
				  iconclass: "fa fa-picture-o",
				  tooltiptext: 'Upload an image',
				  action: function() {
				    var $editor = this.$editor;

				    // Create a virtual input element.
				    var input = document.createElement('input');
				    input.type = 'file';
				    input.accept = "image/*";

				    input.onchange = function() {
				      var reader = new FileReader();

				      if (this.files && this.files[0]) {
				        reader.onload = function(e) {
				          //@TODO: Do something with an image here.
				          $editor().wrapSelection('insertHtml', '<img src=' + e.target.result + '>', true);
				        };

				        reader.readAsDataURL(this.files[0]);
				      }
				    };

				    // Click on a virtual input element.
				    input.click();
				  },
			        onElementSelect: {
			            element: 'img',
			            action: taToolFunctions.imgOnSelectAction
			        }
			
				})
			// add the button to the default toolbar definition
			taOptions.toolbar[1].push('colourRed');
			return taOptions;
		}]);
	}).directive("fileread", [function () {
		 return {
		        scope: {
		            fileread: "="
		        },
		        link: function (scope, element, attributes) {
		            element.bind("change", function (changeEvent) {
		                var reader = new FileReader();
		                reader.onload = function (loadEvent) {
		                    scope.$apply(function () {
		                        scope.fileread = loadEvent.target.result;
		                    });
		                }
		                reader.readAsDataURL(changeEvent.target.files[0]);
		            });
		        }
		    }
	}]).directive('input', [ '$parse', function($parse) {
		return {
			priority : 2,
			 restrict : 'E',
			compile : function(element) {
				element.on('compositionstart', function(e) {
					e.stopImmediatePropagation();
				});
			},
		};
	} ])
	.directive('textarea', [ '$parse', function($parse) {
		return {
			priority : 2,
			 restrict : 'E',
			compile : function(element) {
				element.on('compositionstart', function(e) {
					e.stopImmediatePropagation();
				});
			},
		};
	} ])
	.directive('eatClickIf', ['$parse', '$rootScope',
	                          function($parse, $rootScope) {
	                            return {
	                              priority: 100,
	                              restrict: 'A',
	                              compile: function($element, attr) {
	                                var fn = $parse(attr.eatClickIf);
	                                return {
	                                  pre: function link(scope, element) {
	                                    var eventName = 'click';
	                                    element.on(eventName, function(event) {
	                                      var callback = function() {
	                                        if (fn(scope, {$event: event})) {
	                                          event.stopImmediatePropagation();
	                                          event.preventDefault();
	                                          return false;
	                                        }
	                                      };
	                                      if ($rootScope.$$phase) {
	                                        scope.$evalAsync(callback);
	                                      } else {
	                                        scope.$apply(callback);
	                                      }
	                                    });
	                                  },
	                                  post: function() {}
	                                }
	                              }
	                            }
	                          }
	                        ])
    .run(function($rootScope, $state, $location, runtimeStates, $document, $injector, mainDataService) {
    	 $("header").hide();
    	 $("footer").hide();
    	//console.log("app.module.js run start!");
    	$("#mainContainer").hide();
    	//console.log("app.module.js run mainContainer hide start!");
    	$rootScope.$on('$stateChangeSuccess', function(e, toState, toParams, fromState, fromParams ) {
    		//console.log("app.module.js $rootScope.$on start!");
    		$("#mainContainer").hide();
    		//$rootScope.requiredLogin = sessionStorage.getItem('loginId') ? false : true;
    		if(toState.name.indexOf("loginwork") > -1){
    			
    		}else if(toState.name.indexOf("login") > -1){
    			
    		}else{
    		if($rootScope.requiredLogin== undefined){
    			//alert("페이지 새로고침은 지원되지 않습니다.");
    			//$state.go('login');
    			//console.log(toState);
    			$state.go('board_list');
    		}
    		}
            if (toState.data && toState.data.requiredLogin) {
                if ($rootScope.requiredLogin) {
                    e.preventDefault();
                    $("#mainContainer").hide();
                    $state.go('login');
                }else{
                	$("#mainContainer").show();
                }
            }else{
            	// 로그인 주소 직접 칠 경우
            	if(toState.name.indexOf("auth") > -1){
            		
            		$rootScope.requiredLogin = true;
            	}
            }
            console.log(toParams);
        });
    	var menuList = $.parseJSON(sessionStorage.getItem('menuList'));

		runtimeStates.defaultState();

		// 메뉴가 존재하면 state에 메뉴 등록
		if(menuList && menuList.length>0){
			runtimeStates.makeState(menuList, '');
			//console.log("app.module.js runtimeStates.makeState start!");
			 if ($rootScope.requiredLogin) {
			$("#mainContainer").hide();
			 }else{
				 $("#mainContainer").show();
			 }
		}
		
		
		$rootScope.sendSMS = function(){
			$rootScope.msg_receivers = '';
			$rootScope.smsReceiveList = [];
			$rootScope.showReceiverList = '';
			$rootScope.msg_text = '';
			$('.popSendSmsEmail2').bPopup({
				follow: [false, false], 
				opacity:0.3,
				positionStyle: 'fixed'
			});
		}
		
		$rootScope.smsReceiveList = [];
		$rootScope.showReceiverList = '';
		
		$rootScope.checkInput = function(){
			var res = false;
			var keyCode =  event.keyCode;
			if(keyCode < 48 || keyCode > 57){
				if(keyCode == 8 || keyCode == 32){
					
				}else if(keyCode >95 && keyCode < 106){
					
				}else if(keyCode == 9 || (keyCode >36 && keyCode <41) ){
					
				}else{
					alert("허용되지 않는 문자입니다.");
					res = true;
					var tempStr = '';
					tempStr = $rootScope.msg_receivers;
					$rootScope.msg_receivers = String(tempStr).substring(0,String(tempStr).length-1);
					
				}				
			}
			if($rootScope.msg_receivers.length > 11){
				alert("수신번호 길이초과입니다.");
				var tempStr = '';
				tempStr = $rootScope.msg_receivers;
				$rootScope.msg_receivers = String(tempStr).substring(0,String(tempStr).length-1);
			}
			
		}
		$rootScope.addReceiver = function(){
			
			
			
			var addData = {};
			addData.number = $rootScope.msg_receivers;
			$rootScope.smsReceiveList.push(addData);
			$rootScope.showReceiverList = '';
			for(var i=0;i<$rootScope.smsReceiveList.length;i++){
				$rootScope.showReceiverList = $rootScope.showReceiverList + ' ' + $rootScope.smsReceiveList[i].number + ' ';
			}
			$rootScope.msg_receivers = '';
		}
		
		
		$rootScope.checkByte = function(){
			
				/*var obj = $("#"+'qaz');
				var stringByteLength = byteCheck(obj);
				console.log("길이 : " + stringByteLength);
				if(Number(stringByteLength) >90){
					alert("발송가능한 길이를 초과하였습니다.");
					while(true){
						var flag = false;
						if(Number(byteCheck2($rootScope.msg_text)) >90 ){
							$rootScope.msg_text = $rootScope.msg_text.substring(0,$rootScope.msg_text.length-1);
						}else{
							flag = true;
						}
						
						if(flag ==true){
							break;
						}
					}
					
				}*/
			
		}
		
		function byteCheck(el){
		    var codeByte = 0;
		    for (var idx = 0; idx < el.val().length; idx++) {
		        var oneChar = escape(el.val().charAt(idx));
		        if ( oneChar.length == 1 ) {
		            codeByte ++;
		        } else if (oneChar.indexOf("%u") != -1) {
		            codeByte += 2;
		        } else if (oneChar.indexOf("%") != -1) {
		            codeByte ++;
		        }
		    }
		    return codeByte;
		}
		
		function byteCheck2(str){
		    var codeByte = 0;
		    for (var idx = 0; idx < str.length; idx++) {
		        var oneChar = escape(str.charAt(idx));
		        if ( oneChar.length == 1 ) {
		            codeByte ++;
		        } else if (oneChar.indexOf("%u") != -1) {
		            codeByte += 2;
		        } else if (oneChar.indexOf("%") != -1) {
		            codeByte ++;
		        }
		    }
		    return codeByte;
		}
		
		$rootScope.sendData = function(){
			

			if (!$rootScope.checkValObj($rootScope.msg_text)) {
				alert ("내용을 입력해주세요.");
				return;
			}
			
			//if (vm.sender_type == "sms") {
				if($rootScope.msg_text.length > 70){
					/*alert("SMS 발송 길이는 70자를 초과할수 없습니다.");
					$rootScope.msg_text = $rootScope.msg_text.substring(0,70);
					return;*/
				}
			//}
			
			var param = {};
			param.sendList = $rootScope.smsReceiveList;
			param.content = $rootScope.msg_text;
			
			param.member_id = sessionStorage.getItem('loginMemberId');
			param.type =  "I";
			
			
			
			
			mainDataService.sendSMS(param)
				.success(function(data){
					alert("발송되었습니다.");
					$('.popSendSmsEmail').bPopup().close();
				});
			
			
		}
		$rootScope.checkValObj = function (obj) {
			if (obj == undefined || obj == null || obj.toString().trim() == '') return false;
			return true;
		};
		
		
		
		
		
		$("body").on("keydown", function(){
    		var charCode = window.event.keyCode;

    		/*if(charCode == 8){
    			if(event.target.nodeName != 'INPUT' && event.target.nodeName != 'TEXTAREA'){
    				window.event.returnValue = false;
    			}
    		}*/
    		/*if(event.target.contentEditable != "true"){
    			if(charCode == 8){
    				window.event.returnValue = false;
    			}
    			
    		}*/
    		if(charCode == 8){
    			if(event.target.nodeName == 'BODY' ){
    				window.event.returnValue = false;
    			}
    		}
    		var a = event.target.nodeName;
    		if(charCode == 116){
    			//if(event.target.nodeName != 'INPUT' && event.target.nodeName != 'TEXTAREA'){
    				window.event.returnValue = false;
    			//}
    		}
    		
    	});
		
		
		
		
		
		
		
		
		
		
    	
    	$rootScope.goState = function(stateNm){
    		//console.log(sessionStorage.getItem("loginAplyClass"));
    		if(typeof $rootScope.loginAplyClass=='undefined'){
    			$rootScope.loginAplyClass = sessionStorage.getItem("loginAplyClass");
    			$rootScope.loginNm = sessionStorage.getItem('loginNm');
    			$rootScope.requiredLogin = false;
    		}
    		switch($rootScope.loginAplyClass){
    		case 'SA':
    			$state.go(stateNm, {}, {reload: true});
    			break;
    		case 'SU':
    			if(stateNm == 'officeList' || stateNm == 'meetingRoomList' ||stateNm == 'facilityList' ||stateNm == 'equipmentList' ||
    					stateNm == 'accountList' ||stateNm == 'goodsList' ){
    				alert("권한이 없습니다.");
    			}else{
    				$state.go(stateNm, {}, {reload: true});
    			}
    			break;
    		case 'AD':
    			if(stateNm == 'officeList' || stateNm == 'meetingRoomList' ||stateNm == 'facilityList' ||stateNm == 'equipmentList' ||
    					stateNm == 'accountList' ||stateNm == 'goodsList' ||stateNm == 'calcTenantList' ||stateNm == 'invoiceList' ||
    					stateNm == 'meetingroomStatusCalendar' ||stateNm == 'facilityStatusCalendar' ||stateNm == 'printerStatusList' ||
    					stateNm == 'vendingStatusList' ||stateNm == 'reportAll'||stateNm == 'pgStatusList'){
    				alert("권한이 없습니다.");
    			}else{
    				$state.go(stateNm, {}, {reload: true});
    			}
    			break;
    		case 'AU'://
    			if(stateNm == 'invoiceList' || stateNm == 'calcTenantList' ){
    				$state.go(stateNm, {}, {reload: true});
    			}else{
    				alert("권한이 없습니다.");
    			}
    			break;
    		}
    		/*if(type == 3){
    			if($rootScope.loginAplyClass !=3){
        			$state.go(stateNm);
        		}else{
        			alert("권한이 없습니다.");
        		}
    		}
    		if(type == 4){
    			if($rootScope.loginType ==4){
        			$state.go(stateNm);
        		}else{
        			alert("권한이 없습니다.");
        		}
    		}*/
    		
    	}
    })
    .filter("codeConvLang", function () {
    	return function (cd) {
			if (cd == "KOR") return "한국어";
			if (cd == "ENG") return "영어";
			return "알수없음";
    	}
    })
    ;


