
angular.module('app.commSvc', [])
	/* messageBox */
	.factory('messageBox', function($modal, $rootScope, messageType, focus){
		return{
			open : function (message, opts) {
				var newScope = $rootScope.$new(),
					templateUrl = "./template/messageBox.html",fv
					modalInstance,
					options = angular.extend({}, opts);

				if(options.title){
					templateUrl = "./template/messageTitleBox.html",
					newScope.title = options.title;
				}

				if(options.confirm){
					templateUrl = "./template/messageBoxConfim.html";
				}

				if(options.result){
					templateUrl = "./template/messageBoxResult.html";
					newScope.title = options.title;
				}

				newScope.message = message;
				newScope.type = messageType[options.type] || undefined;

				modalInstance = $modal.open({
					templateUrl : templateUrl,
					dialogClass : "dialogue",
		  			scope : newScope,
		  			backdrop : true,
		  			dialogFade : false
				});
				modalInstance.opened.then(function(d) {
					$('.modal #defaultBtn').focus();
				});
				newScope.ok = function() {
					modalInstance.close(true);
				};

				newScope.notOk = function() {
					modalInstance.close(false);
				};
				newScope.close = function() {
					modalInstance.dismiss('cancle');
                    focus(options.focus);

				};
				return modalInstance;
			}
		};
	})
    .directive('focusOn', function() {
        return function(scope, elem, attr) {
            scope.$on('focusOn', function(e, name) {
                if(name === attr.focusOn) {
                    elem[0].focus();
                }
            });
        };
    })
    .factory('focus', function ($rootScope, $timeout) {
        return function(name) {
            $timeout(function (){
                $rootScope.$broadcast('focusOn', name);
            });
        };	
    })
    
    .factory('findName', function ($rootScope, $timeout) {
    	return {
    			restoreOldValue : function(sco, fexp, rvalue){
				var aaa = fexp.split('.');
				var target = sco;
				var i = 0;
				for (; i < aaa.length-1; i++) {
					target = target[aaa[i]];
				}
				
				target[aaa[i]] = rvalue;
				
				return true;
			}
    	}
    })
			
    .factory('checkRV', ['checkUtil', function (checkUtil, $rootScope, $timeout) {
    	return {
    		checkRV : function (vName, bRequired, chkType, msg) {				// 필수와 타입체크 : 변수명, 필수여부, 체크 타입, 메시지
		
				if(bRequired){
					if(vName == undefined || vName == ''){
						alert(msg);
						return false;
					}
				}
			
				if(!checkUtil[chkType](vName)){
						alert(msg);
						return false;
				}
				return true;
			},
    	}
    }])

			
			
			
    .factory('popupControl', function ($rootScope, $timeout) {
        return  {
        	popupOpen : function(){
               	var targetPop=$(this).attr('popupTarget');
    			if(targetPop==undefined){
    				$('.popupWrap').bPopup({
    					follow: [false, false], 
    					opacity:0.3,
    					positionStyle: 'fixed'
    				});
    			}else{
    				$('.popupWrap.'+targetPop).bPopup({
    					follow : [false, false], 
    					opacity : 0.3,
    					positionStyle : 'fixed'
    				});
    			}
        	},
        	selectBox : function(){
        		var selectOpt = $('select.selectOpt');
        		selectOpt.each(function(){
        			var selectedOpt = $(this).children("option:selected").text();
        			$(this).siblings("label").text(selectedOpt);
        		});

        		selectOpt.on('change',function(){
        			var selectedOpt = $(this).children("option:selected").text();
        			$(this).siblings("label").text(selectedOpt);
        		});
        	}
       	}
    })
	.factory('stringUtil',[function(){
		return {
			byteLength:function(str){
				var bl = (function(s,b,i,c){
					for(b=i=0;c=s.charCodeAt(i++);b+=c>>7?2:1);
					return b;
				})(str);
				return bl;
			},
			leftPad:function(digit, size, attatch) {
	            var add = "";
	            digit = digit.toString();
	            if (digit.length < size) {
	                var len = size - digit.length, i;
	                for (i = 0; i < len; i++) {
	                    add += attatch;
	                }
	            }
	            return add + digit;
	        },
			isNumber:function(n) {
				return !isNaN(parseFloat(n)) && isFinite(n);
			}
		};
	}])
	.factory('transformTozTreeFormat', [function(){
		return function(menuInfo){
			var i,l,
	    		key = "menuId",
	    		parentKey = "upMenuId",
	    		childKey = "nodes",
	    		sNodes = menuInfo;
	    	if (!key || key=="" || !sNodes) return [];

	    	if(Object.prototype.toString.apply(sNodes)){
	    		var r = [];
	    		var tmpMap = [];
	    		for (i=0, l=sNodes.length; i<l; i++) {
	    			tmpMap[sNodes[i][key]] = sNodes[i];
	    		}
	    		for (i=0, l=sNodes.length; i<l; i++) {
	    			if (tmpMap[sNodes[i][parentKey]] && sNodes[i][key] != sNodes[i][parentKey]) {
	    				if (!tmpMap[sNodes[i][parentKey]][childKey])
	    					tmpMap[sNodes[i][parentKey]][childKey] = [];
	    				tmpMap[sNodes[i][parentKey]][childKey].push(sNodes[i]);
	    			} else {
	    				r.push(sNodes[i]);
	    			}
	    		}
	    		return r;
	    	}else {
	    		return [sNodes];
	    	}
		};
	}])
	.filter("hpNoFilter", function () {
	    return function (tel) {
	        console.log(tel);
	        if (!tel) { return ''; }

	        var value = tel.toString().trim().replace(/^\+/, '');

	        if (value.match(/[^0-9]/)) {
	            return tel;
	        }

	        var country, city, number;

	        switch (value.length) {
	            case 1:
	            case 2:
	            case 3:
	                city = value;
	                break;

	            default:
	                city = value.slice(0, 3);
	                number = value.slice(3);
	        }

	        if(number){
	            if(number.length>3){
	                number = number.slice(0, 3) + '-' + number.slice(3,7);
	            }
	            else{
	                number = number;
	            }

	            return (city + "-" + number).trim();
	        }
	        else{
	            return city;
	        }
	    };
    })
	.factory('checkUtil', ['messageBox', 'findName', function(messageBox, findName){
		return {
			checkObj : function (obj) {																			// undefined인지 여부
				if (obj == undefined || obj == null) return false;
				return true;
			},
			checkValObj : function (obj) {																		// 값이 존재하는지 여부
				if (obj == undefined || obj == null || obj.toString().trim() == '') return false;
				return true;
			},
			checkVersion : function(str, el){
				var number =/^[0-9\.]*$/;
				if(!number.test(str)){
					messageBox.open("숫자와 '.'만 입력 가능합니다.", {
	    				type :"info",
	    				title : "INFO",
	    				focus : el
	    			});
					str = '';
				}
				return str;
			},
			checkByte : function(value, length, label, el){
				var str = value;
				var l = 0, r = 0;
				if(typeof str == "undefined") return '';
				for (var i=0; i<str.length; i++){
					l += (str.charCodeAt(i) > 128) ? 2 : 1;
					if(l <= length) r = i+1;
				}
				if(l > length){
					messageBox.open(label + ' 길이를 초과하였습니다.', {
	    				type :"info",
	    				title : "INFO",
	    				focus : el
	    			});
					return str.substring(0, r);
				}
				return str;
			},
			checkCodeId : function(value, label, el, single){
				if(single !== undefined && single) return;
				single = true;
				var rc = /^[a-zA-Z0-9]+$/.test(value);
				if(!rc){
					messageBox.open('영문 및 숫자만 입력 가능합니다.', {
	    				type :"info",
	    				title : "INFO",
	    				focus : el
					});
				}
				single = false;
				return rc;
			},
		
			
			
			
			
			
			
			
			checkSeianPwd : function (id, password) {																// 패스워드 체크 :  영문, 숫자, 특수문자 포함 (8~12자) 체크
				if (!/^[a-zA-Z0-9\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]{8,12}$/.test(password)) {
					alert('숫자와 영문자, 특수문자 조합으로 6~15자리를 사용해야 합니다.');
					return false;
				}

				var checkNumber = password.search(/[0-9]/g);
				var checkEnglish = password.search(/[a-z]/ig);
				var checkChar = password.search(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi);
				if (checkNumber < 0 || checkEnglish < 0 || checkChar < 0) {											// 개별적으로 영문, 숫자, 특수 문자 반드시 포함
					alert("숫자와 영문자, 특수문자를 혼용하여야 합니다.");
					return false;
				}

				if (/(\w)\1\1\1/.test(password)) {																			// 같은 문자 3번 반복 금지
					alert('같은 문자를 3번 이상 사용하실 수 없습니다.');
					return false;
				}

				var test = password.search(id);
				if (password.search(id) > -1) {																				// 패스 워드에 id 포함 금지
					alert("비밀번호에 아이디가 포함되었습니다.");
					return false;
				}

				return true;
			},
			checkHpNo : function (str) {																			// 휴대폰 번호 체크 : (위는 '-' 있는것), 아래는 '-'없는 것
				// var regExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
				var regExp = /^01([016789]{1})([0-9]{7,8})$/;
				if (!regExp.test(str)) {
					return false;
				}
				return true;
			},
			checkTelNo : function (str) {																			//  번호 체크 : (위는 '-' 있는것), 아래는 '-'없는 것
				var regExp = /^0[2-9]{1,2}\-[0-9]{3,4}\-[0-9]{4}$/;
				if (!regExp.test(str)) {
					return false;
				}
				return true;
			},
			checkNumberOnly : function(str){																	// 숫자 체크
				var number = /^[0-9]*$/;
				if(!number.test(str)){
		        	return false;
				}
		        return true;
			},
			checkReal : function(str){																				// 숫자 체크
				var number = /^[+-]?[0-9]*(\.?[0-9]*)$/ ;
				if(!number.test(str)){
		        	return false;
				}
		        return true;
			},
			checkPositiveNumber : function(str, el){															// 양수 숫자
				if(str === undefined || str === NaN || str == null){
					return false
				}
				var number =/^[0-9]*(.[0-9]*)$/;
				if(!number.test(str)){
					return false;
				}
				if(str <= 0){
					return false;
				}
				return true;
			},
			checkAll : function(str){																		// 모든문자
				return true;
			},
			checkBizno : function(num){																		// 사업자 번호 체크
				var sumMod = 0;
		        sumMod  +=  parseInt(num.substring(0,1));
		        sumMod  +=  parseInt(num.substring(1,2)) * 3 % 10;
		        sumMod  +=  parseInt(num.substring(2,3)) * 7 % 10;
		        sumMod  +=  parseInt(num.substring(3,4)) * 1 % 10;
		        sumMod  +=  parseInt(num.substring(4,5)) * 3 % 10;
		        sumMod  +=  parseInt(num.substring(5,6)) * 7 % 10;
		        sumMod  +=  parseInt(num.substring(6,7)) * 1 % 10;
		        sumMod  +=  parseInt(num.substring(7,8)) * 3 % 10;
		        sumMod  +=  Math.floor(parseInt(num.substring(8,9)) * 5 / 10);
		        sumMod  +=  parseInt(num.substring(8,9)) * 5 % 10;
		        sumMod  +=  parseInt(num.substring(9,10));

		        if (sumMod % 10  !=  0){
		        	return false;
		        }
		        return true;
			},
			checkId : function(str){																				// 첫글자는 영문자 그리고 숫자 포함(5~12)자 
				var idRegx = /^[a-zA-Z]+[a-zA-Z0-9]{5,12}$/;
				return idRegx.test(str);
			},
			checkEmail : function(str){																		// 이메일 주소 검증
				var emailRegx =/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{2,5}$/;
				return emailRegx.test(str);
			},
			checkIpAddr : function(str){
				var number =/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
				if(!number.test(str)){
					return false;
				}
				return true;
			},
			checkKor : function(str){
				var kor =/^[0-9a-zA-Z]*$/;
				if(!kor.test(str)){
					return false;
				}
				return true;
				//return str;
			},
			checkNull : function(str){
				if(str == '' || str == undefined){
					return false;
				}
				return true;
			},
		
			
			
			
			
	
			
			
			
			
			
			// input util
			inputHpNo : function (newValue, oldValue, sco) {												// 휴대폰 번호 체크 : (위는 '-' 있는것), 아래는 '-'없는 것
				var regs = [ /^0/,
							/^01/,
							/^01([016789]{1})/,
							/^01([016789]{1})([0-9]{1})/,
							/^01([016789]{1})([0-9]{2})/,
							/^01([016789]{1})([0-9]{3})/,
							/^01([016789]{1})([0-9]{4})/,
							/^01([016789]{1})([0-9]{5})/,
							/^01([016789]{1})([0-9]{6})/,
							/^01([016789]{1})([0-9]{7})/,
							/^01([016789]{1})([0-9]{8})/ ];
				
				if(newValue == undefined || newValue == '')	return;
				try{
					if(!regs[newValue.length-1].test(newValue)){
						findName.restoreOldValue(sco, this.exp, oldValue);
					}
				}catch(e){
					findName.restoreOldValue(sco, this.exp, oldValue);
				}
			},
			inputTelNo : function (newValue, oldValue, sco) {													// 전화 번호 체크 : (위는 '-' 있는것), 아래는 '-'없는 것
				var regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;

				var regs = [ /^0/,
							/^01/,
							/^01([016789]{1})/,
							/^01([016789]{1})([0-9]{1})/,
							/^01([016789]{1})([0-9]{2})/,
							/^01([016789]{1})([0-9]{3})/,
							/^01([016789]{1})([0-9]{4})/,
							/^01([016789]{1})([0-9]{5})/,
							/^01([016789]{1})([0-9]{6})/,
							/^01([016789]{1})([0-9]{7})/,
							/^01([016789]{1})([0-9]{8})/ ];
				
				if(newValue == undefined || newValue == '')	return;
				try{
					if(!regs[newValue.length-1].test(newValue))
						findName.restoreOldValue(sco, this.exp, oldValue);
				}catch(e){
					findName.restoreOldValue(sco, this.exp, oldValue);
				}
			},
			inputNumberOnly : function (newValue, oldValue, sco) {												// 숫자만 체크
				var regs = [ /^[0-9]+$/ ] ;
			
				if(newValue == undefined || newValue == '')	return;
				try{
					if(!regs[0].test(newValue))
						findName.restoreOldValue(sco, this.exp, oldValue);
				}catch(e){
					findName.restoreOldValue(sco, this.exp, oldValue);
				}
			},
			inputReal : function (newValue, oldValue, sco) {															// 실수만 체크
				var regs = [ /^[+-]?[0-9]*(\.?[0-9]*)$/ ]
			
				if(newValue == undefined || newValue == '')	return;
				try{
					if(!regs[0].test(newValue))
						findName.restoreOldValue(sco, this.exp, oldValue);
				}catch(e){
					findName.restoreOldValue(sco, this.exp, oldValue);
				}
			},
			inputPlusReal : function (newValue, oldValue, sco) {													// 양의 실수만 체크
				var regs = [ /^[+]?\d*(\.?\d*)$/ ]
			
				if(newValue == undefined || newValue == '')	return;
				try{
					if(!regs[0].test(newValue))
						findName.restoreOldValue(sco, this.exp, oldValue);
				}catch(e){
					findName.restoreOldValue(sco, this.exp, oldValue);
				}
			},
			inputBusiness : function (newValue, oldValue, sco) {													// 사업자번호 체크 - 정수와 '-' 인지만 체크
				var regs = [ /^[0-9\-]+$/ ];
			
				if(newValue == undefined || newValue == '')	return;
				try{
					var trans = (newValue+'').match(/\d{1}/g);
					if(!regs[0].test(newValue))
						findName.restoreOldValue(sco, this.exp, oldValue);
				}catch(e){
					findName.restoreOldValue(sco, this.exp, oldValue);
				}
			},
			inputID : function (newValue, oldValue, sco) {															//  ID 체크 - 문자 & 숫자만 체크
				var regs = [ /^[a-zA-Z0-9\-]+$/ ];
			
				if(newValue == undefined || newValue == '')	return;
				try{
					if(!regs[0].test(newValue))
						findName.restoreOldValue(sco, this.exp, oldValue);
				}catch(e){
					findName.restoreOldValue(sco, this.exp, oldValue);
				}
			},										
			inputPW : function (newValue, oldValue, sco) {															//  PW 체크 - 문자 & 숫자 & 특수문자  체크
				var regs = [ /^[a-zA-Z0-9\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]*$/ ];
			
				if(newValue == undefined || newValue == '')	return;
				try{
					if(!regs[0].test(newValue))
						findName.restoreOldValue(sco, this.exp, oldValue);
				}catch(e){
					findName.restoreOldValue(sco, this.exp, oldValue);
				}
			},			
			inputEmail : function (newValue, oldValue, sco) {														//  Email 체크 - 문자 & 숫자 & 특수문자  체크
				var regs = [ /^[-A-Za-z0-9_@.]*$/ ];
			
				if(newValue == undefined || newValue == '')	return;
				try{
					if(!regs[0].test(newValue))
						findName.restoreOldValue(sco, this.exp, oldValue);
				}catch(e){
					findName.restoreOldValue(sco, this.exp, oldValue);
				}
			},			
			inputIpAddr : function(newValue, oldValue, sco){																				// ip addr 체크
				var regs = [ /^[0-9.]*$/ ];
				
				if(newValue == undefined || newValue == '')	return;
				try{
					if(!regs[0].test(newValue))
						findName.restoreOldValue(sco, this.exp, oldValue);
				}catch(e){
					findName.restoreOldValue(sco, this.exp, oldValue);
				}
			},
			inputAll : function(newValue, oldValue, sco){																				// ip addr 체크
				return;
			},
			/*	
			// 국내에 종속성을 가지고있기에 라온에는 직접 들어가지 않겠지만 나중에 확장용으로 들어갈 수 있습니다. (은글슬쩍 라이브러리 광고....)
			// https://dev.saro.me/raon/
			// 사업자번호 체크
			// 2015-04-24 전명 박용서 작성
			function ckBisNo(bisNo)
			{
				// 넘어온 값의 정수만 추츨하여 문자열의 배열로 만들고 10자리 숫자인지 확인합니다.
				if ((bisNo = (bisNo+'').match(/\d{1}/g)).length != 10) { return false; }
				
				// 합 / 체크키
				var sum = 0, key = [1, 3, 7, 1, 3, 7, 1, 3, 5];
				
				// 0 ~ 8 까지 9개의 숫자를 체크키와 곱하여 합에더합니다.
				for (var i = 0 ; i < 9 ; i++) { sum += (key[i] * Number(bisNo[i])); }
				
				// 각 8번배열의 값을 곱한 후 10으로 나누고 내림하여 기존 합에 더합니다.
				// 다시 10의 나머지를 구한후 그 값을 10에서 빼면 이것이 검증번호 이며 기존 검증번호와 비교하면됩니다.
				return (10 - ((sum + Math.floor(key[8] * Number(bisNo[8]) / 10)) % 10)) == Number(bisNo[9]);
			}	
		*/	
			
		
			
		
			
			
			
			
			
			
		};
	}])
	
	
	
	
	
	
	
	
	
	
	
	.factory('fileFactory',function(messageBox){											// 첨부 파일 체크
		return {
			// 파일 추가
			add: function(file, type, fileList){

				var fileOptions = this.getTypeCd(type),
					typeCd = fileOptions.typeCd,
					fileType = fileOptions.fileType,
					fileMaxSize = fileOptions.fileMaxSize;

				// 파일 size check
				if(this.checkFileSize(file[0], fileMaxSize)){
					return false;
				}
				if(this.checkFileType(file[0], fileType)){
					return false;
				}

				angular.forEach(file, function(fileInfo){
					for(var i=0; i<fileList.length; i++){
						if(typeCd !== '000' && typeCd !== '005'
							&& typeCd !== '006' && typeCd !== '018' && typeCd !== '019' && fileList[i].fileTypeCd === typeCd){
							fileList.splice(i, 1);
						}
					}
					fileInfo['fileTypeCd'] = typeCd;
					fileInfo['atcFileGubun'] = 'new';
					fileInfo['fileNm'] = fileInfo.name;
					fileList.push(fileInfo);
				});


				return true;
			},
			replace: function(file, type, fileList){

				var fileOptions = this.getTypeCd(type),
					typeCd = fileOptions.typeCd,
					fileType = fileOptions.fileType,
					fileMaxSize = fileOptions.fileMaxSize;

				// 파일 size check
				if(this.checkFileSize(file[0], fileMaxSize)){
					return false;
				}
				if(this.checkFileType(file[0], fileType)){
					return false;
				}

				angular.forEach(file, function(fileInfo){
					for(var i=0; i<fileList.length; i++){
						if(fileList[i].fileTypeCd === typeCd){
							fileList.splice(i, 1);
						}
					}
					fileInfo['fileTypeCd'] = typeCd;
					fileInfo['atcFileGubun'] = 'new';
					fileInfo['fileNm'] = fileInfo.name;
					fileList.push(fileInfo);
				});
				return true;
			},
			// 파일 삭제
			remove: function(file, fileList){
		    	var idx = fileList.indexOf(file);
		    	fileList.splice(idx, 1);
				return fileList;
			},
			// 파일 사이즈 체크
			checkFileSize : function(file, fileMaxSize){
				var oriFileSize = file.size,
					mbSize = oriFileSize/Math.pow(1024, Math.floor(2)).toFixed(2);
					
				if(mbSize > fileMaxSize){
					/*messageBox.open(fileMaxSize + 'MB 사이즈를 초과하였습니다.', {
	    				type :"info",
	    				title : "INFO"
	    			});*/
					alert(fileMaxSize + 'MB 사이즈를 초과하였습니다.');
					return true;
				}
				return false;
			},
			// 파일 유형 체크
			checkFileType : function(file, fileType){
				var arrFileName = file.name.split("."),
					extensions = arrFileName[arrFileName.length-1];

				var enableFileList = ["doc", "docx", "xls", "xlsx", "ppt","pptx","pdf","hwp","txt","jpg","gif","png","bmp","zip"];
				
				var fileFlag = false;
				for(var i=0;i<enableFileList.length;i++){
					if(extensions == enableFileList[i]){
						//alert(extensions + "형식의 확장자는 첨부가 불가능 합니다.");
						//return true;
						fileFlag = true;
					}
				}
				if(fileFlag == false){
					alert(extensions + "형식의 확장자는 첨부가 불가능 합니다.");
					return true;
				}
				if(fileType === "image"){
					if(extensions !== "jpg" && extensions !== "gif" && extensions !== "png" && extensions !== "bmp" && extensions !== "tif"){
						messageBox.open('jpg, gif, png, bmp, tif 만 첨부가능 합니다.', {
		    				type :"info",
		    				title : "INFO"
		    			});
						return true;
					}
				}/*else{
					if(extensions !== "pptx" && extensions !== "xlsx" && extensions !== "png"){

					}
				}*/

				return false;
			},
			// 첨부파일 유형코드 조회
			getTypeCd : function(type){
				var options = {
				};
				switch(type){
					case "MODEL":
						options.typeCd = "009";
						options.fileType = "image";
						options.fileMaxSize = 10;
						break;
					case "MODEM":
						options.typeCd = "010";
						options.fileType = "image";
						options.fileMaxSize = 10;
						break;
					case "WARES":
						options.typeCd = "011";
						options.fileType = "etc";
						options.fileMaxSize = 20;
						break;
					case "NET":
						options.typeCd = "012";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "KC":
						options.typeCd = "013";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "BIZR":
						options.typeCd = "014";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "DCLA":
						options.typeCd = "015";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "WAIVER":
						options.typeCd = "016";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "ETCTXT":
						options.typeCd = "017";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "TFA":
						options.typeCd = "018";
						options.fileType = "etc";
						options.fileMaxSize = 20;
						break;
					case "MFA":
						options.typeCd = "019";
						options.fileType = "etc";
						options.fileMaxSize = 20;
						break;
					case "VERS":
						options.typeCd = "020";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "VERF":
						options.typeCd = "021";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "VERFA":
						options.typeCd = "022";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "VERSUL":
						options.typeCd = "023";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "VERETC":
						options.typeCd = "024";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "DIST":
						options.typeCd = "025";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "MODEMFW":
						options.typeCd = "026";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
					case "COM":
						options.typeCd = "026";
						options.fileType = "etc";
						options.fileMaxSize = 10;
						break;
				}
				return options;
			}
		}
	})
	.factory('currentDateFactory',['stringUtil', function(stringUtil){							// 날짜 관련 함수
		return {
			between: function(fromDt,toDt){													// 둘 날짜 사이의 일 수 계산
				var from = new Date(fromDt);
				var to = new Date(toDt);
				return (to - from) / 86400000;		// 24(hour) * 60(minute) * 60 (sec) * 1000
			},
			betweenDay: function(fromDt,toDt){												// 둘 날짜 사이의 일 수 계산
				var from = new Date(fromDt);
				var to = new Date(toDt);
				return (to - from) / 86400000;		// 24(hour) * 60(minute) * 60 (sec) * 1000
			},
			betweenMinute:function(sd,shour,smin,td,thour,tmin){							// 둘 날짜 사이의 분 계산
				var from = new Date(sd);
				from.setHours(shour, smin, 0, 0);
				var to = new Date(td);
				to.setHours(thour, tmin, 0, 0);
				return (to - from) / 60000;
			},
			currentHour: function(){																// 현재 시간 문자열 반환 ( hh:mm:ss )
				var dt = new Date();
				return stringUtil.leftPad(dt.getHours(),2,'0') + ':' + '00' + ':' + '00';
			},
			isPast:function(sday,shour,smin){													// 현재 시간보다 과거인지
				var curr = new Date(), start = new Date(sday);
				start.setHours(shour,smin,0,0);
				return start < curr;
			},
			today: function(){																		// 현재 날짜 포맷팅
				var dt = new Date();
				return dt.getFullYear() + '-' + stringUtil.leftPad(dt.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(dt.getDate(),2,'0');
			},
			hours: function(){																		// 현재 시간
				var dt = new Date();
				return stringUtil.leftPad(dt.getHours(),2,'0');
			},
			minutes: function(){																	// 현재 분
				var dt = new Date();
				return stringUtil.leftPad(dt.getMinutes(),2,'0');
			},
			firstThisMonth: function(){															// 현재 월
				var dt = new Date();
				return dt.getFullYear() + '-' + stringUtil.leftPad(dt.getMonth()+1,2,'0') + '-' + '01';
			},
			lastThisMonth: function(){															// 이달의 마지막 날
				var dt = new Date();
				var temp  = new Date(dt.getFullYear(), dt.getMonth()+1, 0);
				return temp.getFullYear() + '-' + stringUtil.leftPad(temp.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(temp.getDate(),2,'0');
			},
			firstThisWeek: function(){																// 이번주의 시작 일
				var dt = new Date();
				var temp = new Date();
				temp.setDate(dt.getDate() - dt.getDay());
				return temp.getFullYear() + '-' + stringUtil.leftPad(temp.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(temp.getDate(),2,'0');
			},
			lastThisWeek: function(){																// 이번주의 마지막 일
				var dt = new Date();
				var temp = new Date();
				temp.setDate(dt.getDate() + (6 - dt.getDay()));
				return temp.getFullYear() + '-' + stringUtil.leftPad(temp.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(temp.getDate(),2,'0');
			},
			calculate:function(fromDt,day){														// 기준 날짜에 일수 더하기
				var dt = new Date(fromDt);
				dt.setDate(dt.getDate() + day);
				return dt.getFullYear() + '-' + stringUtil.leftPad(dt.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(dt.getDate(),2,'0');
			},
			addDay:function(fromDt,day){														// 기준 날짜에 일수 더하기
				var dt = new Date(fromDt);
				dt.setDate(dt.getDate() + day);
				return dt.getFullYear() + '-' + stringUtil.leftPad(dt.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(dt.getDate(),2,'0');
			},
			addDay2:function(fromDt,day){														// 기준 날짜에 일수 더하기
				var dt = new Date(fromDt);
				dt.setDate(dt.getDate() + day);
				return dt.getFullYear() + '-' + stringUtil.leftPad(dt.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(dt.getDate(),2,'0') + ' ' + stringUtil.leftPad(dt.getHours(),2,'0') + stringUtil.leftPad(dt.getMinutes(),2,'0')+ stringUtil.leftPad(dt.getSeconds(),2,'0');
			},
			addDayWithMonDay:function(fromDt,day){										// 
				var dt = new Date(fromDt);
				dt.setDate(dt.getDate() + day);
				return stringUtil.leftPad(dt.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(dt.getDate(),2,'0');
			},
			addMonth:function(fromDt,month){												// 달수 더하기
				var dt = new Date(fromDt);
				dt.setMonth(dt.getMonth() + month);
				return dt.getFullYear() + '-' + stringUtil.leftPad(dt.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(dt.getDate(),2,'0');
			},
			addMonthOnly:function(fromDt,month){
				var dt = new Date(fromDt);
				dt.setMonth(dt.getMonth() + month);
				return dt.getFullYear() + '-' + stringUtil.leftPad(dt.getMonth()+1,2,'0') ;
			},
			addYear:function(fromDt,year){
				var dt = new Date(fromDt);
				dt.setMonth(dt.getMonth() +(12 * year));
				return dt.getFullYear() + '-' + stringUtil.leftPad(dt.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(dt.getDate(),2,'0');
			},
			
			agoMonth: function(mon){																// 현재일자 N 개월전
				var dt = new Date();
				var temp = new Date(dt.getFullYear(), dt.getMonth()-mon, dt.getDate());
				return temp.getFullYear() + '-' + stringUtil.leftPad(temp.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(temp.getDate(),2,'0');
			},
			
			calMonth:function(toDt, mon){															// 조회일자 N개월전
				var to = new Date(toDt);
				var from = new Date(to.getFullYear(), to.getMonth()-mon, to.getDate());
				return from.getFullYear() + '-' + stringUtil.leftPad(from.getMonth()+1,2,'0') + '-' + stringUtil.leftPad(from.getDate(),2,'0');
			},
			calYear: function(dif){
				var dt = new Date();
				return dt.getFullYear()-dif;
			},
			datetime: function(){
				var dt = new Date();
				return dt.getFullYear() + stringUtil.leftPad(dt.getMonth()+1,2,'0') + stringUtil.leftPad(dt.getDate(),2,'0') + stringUtil.leftPad(dt.getHours(),2,'0') + stringUtil.leftPad(dt.getMinutes(),2,'0')+ stringUtil.leftPad(dt.getSeconds(),2,'0')+ stringUtil.leftPad(dt.getMilliseconds(),3,'0');
			}
		};
	}])
	.factory('dateUtil',['stringUtil', function(stringUtil){
		return {
			format: function(date, format){
			    if (!date.valueOf()) return " ";
			    
			    dt = new Date(date);
			    
			    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
			     
			    return format.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
			        switch ($1) {
			            case "yyyy": return dt.getFullYear();
			            case "yy": return (dt.getFullYear() % 1000).zf(2);
			            case "MM": return stringUtil.leftPad(dt.getMonth()+1, 2, '0');
			            case "dd": return stringUtil.leftPad(dt.getDate(), 2,'0');
			            case "E": return weekName[dt.getDay()];
			            case "HH": return stringUtil.leftPad(dt.getHours(), 2,'0');
			            case "hh": return stringUtil.leftPad(((h = dt.getHours() % 12) ? h : 12), 2,'0');
			            case "mm": return stringUtil.leftPad(dt.getMinutes(), 2, '0');
			            case "ss": return stringUtil.leftPad(dt.getSeconds(), 2, '0');
			            case "a/p": return dt.getHours() < 12 ? "오전" : "오후";
			            default: return $1;
			        }
			    });
			    
			}
		};
	}]);




