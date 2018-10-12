
angular.module('app.navigationLinkDirective', [])
	.directive("navigationLink", function($compile, $timeout){

		var linker = function(scope, element, attrs){

			var scrnAdr = scope.menu.scrnAdr,
			gnbLink = attrs.gnblink;

			if(attrs.scrnadr){
				scrnAdr = attrs.scrnadr;
			}

			scrnAdr = scrnAdr.replace("/", "");
			scrnAdr = scrnAdr.replace(new RegExp("\/{[a-zA-Z]*}", "gm"), '');
			scrnAdr = scrnAdr.replace(/\//gi, '.');

			// 화면인 경우에만 링크
			if(scope.menu.menuTypeCd === 'LINK' || gnbLink === "true"){
				template = '<a href="" ui-sref="'+ scrnAdr +'" ui-sref-opts="{ reload: true }">{{menu.menuNm}}</a>';
			}else{
				template = '<a href="">{{menu.menuNm}}</a>';
			}

			element.prepend(template);
	        $compile(element.contents())(scope);
		}

		return {
			restrict : 'AE',
			scope: {
                menu : '='
            },
			link : linker
		}
	}).directive("stateSelector", function() {
		  return {
			    restrict: "E",
			    template: '  <select ng-model="ctrl.currentstate" ng-change="ctrl.$state.go(ctrl.currentstate);" ' +
			      '    ng-options="state as state.name for state in ctrl.$state.get()">' +
			      '     <option value="">Choose a state</option>' +
			      '  </select>',
			    controller: function($scope, $state) {
			      var ctrl = this;
			      ctrl.$state = $state;
			      ctrl.currentstate = $state.current;
			      $scope.$on("$stateChangeSuccess", function(evt, to) {
			        ctrl.currentstate = $state.current
			      });
			    },
			    controllerAs: "ctrl"
			  }
			});