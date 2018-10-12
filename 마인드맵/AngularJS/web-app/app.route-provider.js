
angular
	.module('app')
	.provider('runtimeStates', function runtimeStates($stateProvider, $urlRouterProvider) {
		this.$get = function($q, $timeout, $state) {
			var state = {
				// state 삭제
				removeState : function(){
					//$state
				},
				// 기본 화면
				defaultState : function(){

					$urlRouterProvider.otherwise( function($injector, $location) {
						var $state = $injector.get("$state");
						
						$state.go("login");
					});

					// 비메뉴 화면
					$stateProvider
						.state('login', {
				        	url : '/login',
				        	templateUrl: 'components/admin/auth/admin_login.html',
				        	controller: 'AdminLoginController as vm'
				        })
				        .state('mainView', {
				        	url : '/mainView',
				        	templateUrl: 'components/admin/main/mainView.html',
				        	controller: 'MainViewController as vm',
							data: {requiredLogin: true}
				        })

				        .state('tourApplicantsList', {
				        	url : '/tourApplicantsList',
				        	templateUrl: 'components/admin/tour/tourApplicantsList.html',
				        	controller: 'TourApplicantsListController as vm',
				        })
				        .state('accountList', {
				        	url : '/accountList',
				        	templateUrl: 'components/admin/systemConfig/accountList.html',
				        	controller: 'AccountListController as vm',
							data: {requiredLogin: true}
				        })
				        .state('equipmentList', {
				        	url : '/equipmentList',
				        	templateUrl: 'components/admin/systemConfig/equipmentList.html',
				        	controller: 'EquipmentListController as vm',
							data: {requiredLogin: true}
				        })
				        .state('facilityList', {
				        	url : '/facilityList',
				        	templateUrl: 'components/admin/systemConfig/facilityList.html',
				        	controller: 'FacilityListController as vm',
							data: {requiredLogin: true}
				        })
				        .state('goodsList', {
				        	url : '/goodsList',
				        	templateUrl: 'components/admin/systemConfig/goodsList.html',
				        	controller: 'GoodsListController as vm',
							data: {requiredLogin: true}
				        })
				        .state('meetingRoomList', {
				        	url : '/meetingRoomList',
				        	templateUrl: 'components/admin/systemConfig/meetingRoomList.html',
				        	controller: 'MeetingRoomListController as vm',
							data: {requiredLogin: true}
				        })
				        .state('officeList', {
				        	url : '/officeList',
				        	templateUrl: 'components/admin/systemConfig/officeList.html',
				        	controller: 'OfficeListController as vm',
							data: {requiredLogin: true}
				        })
				         .state('goodsView', {
				        	url : '/goodsView',
				        	templateUrl: 'components/admin/systemConfig/goodsView.html',
				        	controller: 'GoodsViewController as vm',
				        	params : {sendParam: '{}'}  ,
							data: {requiredLogin: true} 
				        })
				        .state('goodsEdit', {
				        	url : '/goodsEdit',
				        	templateUrl: 'components/admin/systemConfig/goodsEdit.html',
				        	controller: 'GoodsEditController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true} 
				        })
				        .state('accountEdit', {
				        	url : '/accountEdit',
				        	templateUrl: 'components/admin/systemConfig/accountEdit.html',
				        	controller: 'AccountEditController as vm',
						    params : {sendParam: '{}'} ,
							data: {requiredLogin: true}  
				        })					        
					   .state('accountWrite', {
				        	url : '/accountWrite',
				        	templateUrl: 'components/admin/systemConfig/accountWrite.html',
				        	controller: 'AccountWriteController as vm',
							data: {requiredLogin: true}
				        })					        
					   .state('accountView', {
				        	url : '/accountView',
				        	templateUrl: 'components/admin/systemConfig/accountView.html',
				        	controller: 'AccountViewController as vm',
					        params : {sendParam: '{}'}   ,
							data: {requiredLogin: true}
				        })					        

				        .state('meetingRoomWrite', {
				        	url : '/meetingRoomWrite',
				        	templateUrl: 'components/admin/systemConfig/meetingRoomWrite.html',
				        	controller: 'MeetingRoomWriteController as vm',
						    params : {sendParam: '{}'}   ,
							data: {requiredLogin: true}
				        })
				        .state('meetingRoomReserve', {
				        	url : '/meetingRoomReserve',
				        	templateUrl: 'components/admin/booking/meeting_room_reserve.html',
				        	controller: 'CnfrBookingController as vm',
				        	params : {setDate: '{}'}  ,
							data: {requiredLogin: true}
				        })				        
				        .state('meetingRoomReserveReg', {
				        	url : '/meetingRoomReserveReg',
				        	templateUrl: 'components/admin/booking/meeting_room_reserve_reg.html',
				        	controller: 'CnfrBookingRegController as vm',
				            params : {param_conference_id : '{}', param_booking_date : '{}', param_start_time : '{}', param_end_time : '{}'},
							data: {requiredLogin: true}
				        })				        
				        .state('meetingRoomReserveEdit', {
				        	url : '/meetingRoomReserveEdit',
				        	templateUrl: 'components/admin/booking/meeting_room_reserve_edit.html',
				        	controller: 'CnfrBookingEditController as vm',
				        	params : {bookingConferenceId: '{}'}  ,
							data: {requiredLogin: true} 
				        })		
				        .state('fcReserve', {
				        	url : '/fcReserve',
				        	templateUrl: 'components/admin/booking/fc_reserve.html',
				        	controller: 'FcltBookingController as vm',
				        	params : {setDate: '{}'}  ,
							data: {requiredLogin: true}
				        })				        
				        .state('fcReserveReg', {
				        	url : '/fcReserveReg',
				        	templateUrl: 'components/admin/booking/fc_reserve_reg.html',
				        	controller: 'FcltBookingRegController as vm',
				        	 params : {param_conference_id : '{}', param_booking_date : '{}', param_start_time : '{}', param_end_time : '{}'},
							data: {requiredLogin: true}
				        })				        
				        .state('slpRoomReserve', {
				        	url : '/slpRoomReserve',
				        	templateUrl: 'components/admin/booking/slp_room_reserve.html',
				        	params : {setDate: '{}'}  ,
				        	controller: 'SlpRmBookingController as vm',
				        	data: {requiredLogin: true}
				        })				        
				        .state('slpRoomReserveReg', {
				        	url : '/slpRoomReserveReg',
				        	templateUrl: 'components/admin/booking/slp_room_reserve_reg.html',
				        	controller: 'SlpRmBookingRegController as vm',
				        	 params : {param_conference_id : '{}', param_booking_date : '{}', param_start_time : '{}', param_end_time : '{}'},
							data: {requiredLogin: true}
				        })		
				        
				        .state('calcTenantList', {
				        	url : '/calcTenantList',
				        	templateUrl: 'components/admin/calc/calc_tenant_list.html',
				        	controller: 'CalcTenantListController as vm',
							data: {requiredLogin: true}
				        })				        				        
				        .state('calcTenantView', {
				        	url : '/calcTenantView',
				        	templateUrl: 'components/admin/calc/calc_tenant_view.html',
				        	controller: 'CalculTenantViewController as vm',
				        	params : {office_rent_id: '{}', one_ago_month : '{}'},
							data: {requiredLogin: true}
				        })				        	
				        .state('invoiceWrite', {
				        	url : '/invoiceWrite',
				        	templateUrl: 'components/admin/calc/invoice_write.html',
				        	controller: 'InvoiceWriteController as vm',
				        	params : {office_rent_id: '{}', invoice_id : '{}', one_ago_month : '{}', invoice_origin : '{}'},
							data: {requiredLogin: true}
				        })		
				        .state('invoiceSetVat', {
				        	url : '/invoiceSetVat',
				        	templateUrl: 'components/admin/calc/invoice_set_vat.html',
				        	controller: 'InvoiceSetVatController as vm',
				        	params : {office_rent_id: '{}', one_ago_month : '{}', invoice_id : '{}', pre_page : '{}'},
							data: {requiredLogin: true}
				        })	
				        .state('invoiceList', {
				        	url : '/invoiceList',
				        	templateUrl: 'components/admin/calc/invoice_list.html',
				        	controller: 'InvoiceListController as vm',
							data: {requiredLogin: true}
				        })
				        .state('invoiceView', {
				        	url : '/invoiceView',
				        	templateUrl: 'components/admin/calc/invoice_view.html',
				        	controller: 'InvoiceViewController as vm',
				        	params : {office_rent_id: '{}', invoice_id : '{}', booking_id : '{}'},
							data: {requiredLogin: true}
				        })	
				        .state('meetingroomStatusList', {
				        	url : '/meetingroomStatusList',
				        	templateUrl: 'components/admin/calc/meetingroom_status_list.html',
				        	controller: 'MeetingRoomStatusListController as vm',
				        	params : {conference_id: '{}', floor : '{}', searchType : '{}'},
							data: {requiredLogin: true}
				        })						        
				        .state('meetingroomStatusCalendar', {
				        	url : '/meetingroomStatusCalendar',
				        	templateUrl: 'components/admin/calc/meetingroom_status_calendar.html',
				        	controller: 'MeetingroomStatusCalendarController as vm',
				        	params : {conference_id: '{}', floor : '{}', searchType : '{}'},
							data: {requiredLogin: true}
				        })	
				        .state('facilityStatusList', {
				        	url : '/facilityStatusList',
				        	templateUrl: 'components/admin/calc/facility_status_list.html',
				        	controller: 'FacilityStatusListController as vm',
				        	params : {facility_id: '{}', searchType : '{}'},
							data: {requiredLogin: true}
				        })						        
				        .state('facilityStatusCalendar', {
				        	url : '/facilityStatusCalendar',
				        	templateUrl: 'components/admin/calc/facility_status_calendar.html',
				        	controller: 'FacilityStatusCalendarController as vm',
				        	params : {facility_id: '{}', searchType : '{}'},
							data: {requiredLogin: true}
				        })	
				        .state('printerStatusList', {
				        	url : '/printerStatusList',
				        	templateUrl: 'components/admin/calc/printer_status_list.html',
				        	controller: 'MulMachineUseStatusListController as vm',
							data: {requiredLogin: true}
				        })	
				        .state('pgStatusList', {
				        	url : '/pgStatusList',
				        	templateUrl: 'components/admin/calc/pg_status_list.html',
				        	controller: 'PgUseStatusListController as vm',
							data: {requiredLogin: true}
				        })	
				        .state('vendingStatusList', {
				        	url : '/vendingStatusList',
				        	templateUrl: 'components/admin/calc/vending_status_list.html',
				        	controller: 'VendingUseStatusListController as vm',
							data: {requiredLogin: true}
				        })	
				        .state('reportAll', {
				        	url : '/reportAll',
				        	templateUrl: 'components/admin/calc/report_all.html',
				        	controller: 'ReportAllStatusListController as vm',
							data: {requiredLogin: true}
				        })						        
				        .state('reportOffice', {
				        	url : '/reportOffice',
				        	templateUrl: 'components/admin/calc/report_office.html',
				        	controller: 'ReportOfficeStatusListController as vm',
							data: {requiredLogin: true}
				        })						        
				        .state('dailyCalMonthList', {
				        	url : '/dailyCalMonthList',
				        	templateUrl: 'components/admin/calc/dailyCal_Month_List.html',
				        	controller: 'DailyCalMonthListController as vm',
							data: {requiredLogin: true}
				        })
				        .state('dailyCalDayList', {
				        	url : '/dailyCalDayList',
				        	templateUrl: 'components/admin/calc/dailyCal_Day_List.html',
				        	controller: 'DailyCalDayListController as vm',
				        	params : {sendData: '{}' },
							data: {requiredLogin: true}
				        })
				        .state('dailyCalInsert', {
				        	url : '/dailyCalInsert',
				        	templateUrl: 'components/admin/calc/dailyCal_insert.html',
				        	controller: 'DailyCalInsertController as vm',
				        	params : {sendData: '{}' },
							data: {requiredLogin: true}
				        })
				        .state('dailyCalUpdate', {
				        	url : '/dailyCalUpdate',
				        	templateUrl: 'components/admin/calc/dailyCal_update.html',
				        	controller: 'DailyCalUpdateController as vm',
				        	params : {sendData: '{}' },
							data: {requiredLogin: true}
				        })
				        .state('noticeList', {
				        	url : '/noticeList',
				        	templateUrl: 'components/admin/board/notice_list.html',
				        	controller: 'NoticeListController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('noticeView', {
				        	url : '/noticeView',
				        	templateUrl: 'components/admin/board/notice_view.html',
				        	controller: 'NoticeViewController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('noticeWrite', {
				        	url : '/noticeWrite',
				        	templateUrl: 'components/admin/board/notice_write.html',
				        	controller: 'NoticeWriteController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('noticeModify', {
				        	url : '/noticeModify',
				        	templateUrl: 'components/admin/board/notice_modify.html',
				        	controller: 'NoticeModifyController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('faqList', {
				        	url : '/faqList',
				        	templateUrl: 'components/admin/board/faq_list.html',
				        	controller: 'FaqListController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('faqView', {
				        	url : '/faqView',
				        	templateUrl: 'components/admin/board/faq_view.html',
				        	controller: 'FaqViewController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('faqWrite', {
				        	url : '/faqWrite',
				        	templateUrl: 'components/admin/board/faq_write.html',
				        	controller: 'FaqWriteController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('faqModify', {
				        	url : '/faqModify',
				        	templateUrl: 'components/admin/board/faq_modify.html',
				        	controller: 'FaqModifyController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('programList', {
				        	url : '/programList',
				        	templateUrl: 'components/admin/board/program_list.html',
				        	controller: 'ProgramListController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('programView', {
				        	url : '/programView',
				        	templateUrl: 'components/admin/board/program_view.html',
				        	controller: 'ProgramViewController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('qnaList', {
				        	url : '/qnaList',
				        	templateUrl: 'components/admin/board/qna_list.html',
				        	controller: 'QnaListController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('qnaView', {
				        	url : '/qnaView',
				        	templateUrl: 'components/admin/board/qna_view.html',
				        	controller: 'QnaViewController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('qnaWrite', {
				        	url : '/qnaWrite',
				        	templateUrl: 'components/admin/board/qna_write.html',
				        	controller: 'QnaWriteController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('qnaModify', {
				        	url : '/qnaModify',
				        	templateUrl: 'components/admin/board/qna_modify.html',
				        	controller: 'QnaModifyController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('freeBoardList', {
				        	url : '/freeBoardList',
				        	templateUrl: 'components/admin/board/freeBoard_list.html',
				        	controller: 'FreeBoardListController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('freeBoardView', {
				        	url : '/freeBoardView',
				        	templateUrl: 'components/admin/board/freeBoard_view.html',
				        	controller: 'FreeBoardViewController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('freeBoardWrite', {
				        	url : '/freeBoardWrite',
				        	templateUrl: 'components/admin/board/freeBoard_write.html',
				        	controller: 'FreeBoardWriteController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('freeBoardModify', {
				        	url : '/freeBoardModify',
				        	templateUrl: 'components/admin/board/freeBoard_modify.html',
				        	controller: 'FreeBoardModifyController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('programWrite', {
				        	url : '/programWrite',
				        	templateUrl: 'components/admin/board/program_write.html',
				        	controller: 'ProgramWriteController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('programModify', {
				        	url : '/programModify',
				        	templateUrl: 'components/admin/board/program_modify.html',
				        	controller: 'ProgramModifyController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        

				        .state('memberList', {
				        	url : '/memberList',
				        	templateUrl: 'components/admin/member/member_list.html',
				        	controller: 'MemberListController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('memberEdit', {
				        	url : '/memberEdit',
				        	templateUrl: 'components/admin/member/member_edit.html',
				        	controller: 'MemberEditController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('memberReg', {
				        	url : '/memberReg',
				        	templateUrl: 'components/admin/member/member_reg.html',
				        	controller: 'MemberRegController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('sendingHistoryList', {
				        	url : '/sendingHistoryList',
				        	templateUrl: 'components/admin/member/sending_history_list.html',
				        	controller: 'SendingHistoryListController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        
				        .state('sendingHistoryView', {
				        	url : '/sendingHistoryView',
				        	templateUrl: 'components/admin/member/sending_history_view.html',
				        	controller: 'SendingHistoryViewController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })						        

				         .state('tenantList', {
				        	url : '/tenantList',
				        	templateUrl: 'components/admin/tenant/tenant_list.html',
				        	controller: 'TenantListController as vm',
							data: {requiredLogin: true}
				        })	
				        .state('tenantEdit', {
				        	url : '/tenantEdit',
				        	templateUrl: 'components/admin/tenant/tenant_edit.html',
				        	controller: 'TenantEditController as vm',
				        	params : {sendData : '{}' },
							data: {requiredLogin: true}
				        })
				        .state('tenantReg', {
				        	url : '/tenantReg',
				        	templateUrl: 'components/admin/tenant/tenant_reg.html',
				        	controller: 'TenantRegController as vm',
							data: {requiredLogin: true}
				        })	
				        //견적서
				        .state('estimateList', {
				        	url : '/estimateList',
				        	templateUrl: 'components/admin/calc/estimate_list.html',
				        	controller: 'EstimateListController as vm',
							data: {requiredLogin: true}
				        })	
				        .state('estimateWrite', {
				        	url : '/estimateWrite',
				        	templateUrl: 'components/admin/calc/estimate_write.html',
				        	controller: 'EstimateWriteController as vm',
							data: {requiredLogin: true}
				        })	
				         .state('estimateRelativeWrite', {
				        	url : '/estimateRelativeWrite',
				        	templateUrl: 'components/admin/calc/estimate_relative_write.html',
				        	controller: 'EstimateRelativeWriteController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })
				        .state('estimateView', {
				        	url : '/estimateView',
				        	templateUrl: 'components/admin/calc/estimate_view.html',
				        	controller: 'EstimateViewController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })
				        .state('invoiceRelativeWrite', {
				        	url : '/invoiceRelativeWrite',
				        	templateUrl: 'components/admin/calc/invoice_relative_write.html',
				        	controller: 'InvoiceRelativeWriteController as vm',
				        	params : {dataParam: '{}' },
							data: {requiredLogin: true}
				        })
				        //출입등록 관리
				        .state('guestEnterList', {
				        	url : '/guestEnterList',
				        	templateUrl: 'components/admin/tenant/guest_enter_list.html',
				        	controller: 'GuestEnterListController as vm',
							data: {requiredLogin: true}
				        })
				       /* //메뉴권한 관리
				        .state('role_menu_list', {
				        	url : '/role_menu_list',
				        	templateUrl: 'components/admin/role/menu_list.html',
				        	controller: 'RoleController as vm',
							data: {requiredLogin: true}
				        })
				        //메뉴권한 관리
				        .state('role_menu_add', {
				        	url : '/role_menu_add',
				        	templateUrl: 'components/admin/role/menu_add.html',
				        	controller: 'RoleController as vm',
							data: {requiredLogin: true}
				        })	
				        //메뉴권한 관리
				        .state('role_member_list', {
				        	url : '/role_member_list',
				        	templateUrl: 'components/admin/role/member_list.html',
				        	controller: 'RoleController as vm',
							data: {requiredLogin: true}
				        })	
				        
				        .state('member_add', {
				        	url : '/member_add',
				        	templateUrl: 'components/admin/role/member_add.html',
				        	controller: 'Member_addController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })
				        //메뉴권한 관리
				        .state('role_list', {
				        	url : '/role_list',
				        	templateUrl: 'components/admin/role/role_list.html',
				        	controller: 'RoleController as vm',
							data: {requiredLogin: true}
				        })	
				        //메뉴권한 관리
				        .state('role_add', {
				        	url : '/role_add',
				        	templateUrl: 'components/admin/role/role_add.html',
				        	controller: 'RoleController as vm',
							data: {requiredLogin: true}
				        })	
				        //메뉴권한 관리
				        .state('codegroup', {
				        	url : '/codegroup',
				        	templateUrl: 'components/admin/role/codgroup.html',
				        	controller: 'RoleController as vm',
							data: {requiredLogin: true}
				        })
				        //메뉴권한 관리
				        .state('code', {
				        	url : '/code',
				        	templateUrl: 'components/admin/role/code.html',
				        	controller: 'RoleController as vm',
							data: {requiredLogin: true}
				        })		
				        
				        .state('login', {
				        	url : '/login',
				        	templateUrl: 'components/admin/onm/login.html',
				        	controller: 'LoginController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })
*/
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

				        .state('menu_list', {
				        	url : '/menu_list',
				        	templateUrl: 'components/admin/onm/menu_list.html',
				        	controller: 'Menu_listController as vm',
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

				        .state('code_list', {
				        	url : '/code_list',
				        	templateUrl: 'components/admin/onm/code_list.html',
				        	controller: 'Code_listController as vm',
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
				        
				        .state('board_list', {
				        	url : '/board_list',
				        	templateUrl: 'components/admin/board_list.html',
				        	controller: 'Board_listController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })
				        .state('board_add', {
				        	url : '/board_add',
				        	templateUrl: 'components/admin/onm/board_add.html',
				        	controller: 'Board_addController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })
				         .state('email_send', {
				        	url : '/email_send',
				        	templateUrl: 'components/admin/onm/email_send.html',
				        	controller: 'Email_sendController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })
				        .state('sms_send', {
				        	url : '/sms_send',
				        	templateUrl: 'components/admin/onm/sms_send.html',
				        	controller: 'Sms_sendController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })
				        .state('test_insert', {
				        	url : '/test_insert',
				        	templateUrl: 'components/admin/onm/test_insert.html',
				        	controller: 'Test_insertController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })
				        .state('test_insert2', {
				        	url : '/test_insert2',
				        	templateUrl: 'components/admin/onm/test_insert2.html',
				        	controller: 'Test_inserController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })
				         .state('test_insert_list', {
				        	url : '/test_insert_list',
				        	templateUrl: 'components/admin/onm/test_insert_list.html',
				        	controller: 'Test_insert_listController as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })
				        .state('view', {
				        	url : '/test_view',
				        	templateUrl: 'components/admin/onm/test_view.html',
				        	controller: 'Test_view_Controller as vm',
					        params : {sendParam: '{}'}  ,
							data: {requiredLogin: true}
				        })

				},
				// DB menu로 state를 등록한다.
				makeState: function(menuList) {

					// 메뉴 목록
					angular.forEach(menuList, function(menuInfo){

						var stateUrl = menuInfo.scrnAdr,
							scrnAdr = stateUrl.replace('/', '').replace(new RegExp("\/{[a-zA-Z0-9]*}", "gm"), ''),
							templateUrl = state.makeTemplateUrl(scrnAdr),
							controllerName = state.makeControllerName(scrnAdr),
							params = null;

						scrnAdr = scrnAdr.replace(/\//gi, '.');
						stateUrl = state.currentUrl(stateUrl);

						// 이벤트관리 따로 메뉴 추가
						if( scrnAdr == 'eplList.event'){
							stateUrl = '/eplList/event';
							templateUrl = 'components/eplGUI/eplList/indexEventList.html';
							controllerName = 'EventListController';
						}else if( scrnAdr == 'eplList.editor'){
							stateUrl = '/eplList/editor';
							templateUrl = 'components/eplGUI/eplEditor/indexGraph.html';
							controllerName = 'EventViewController';
							menuInfo.scrnAdr = menuInfo.scrnAdr + '/:type?data';
							params = {'type' : 'default' , 'data' : '{}'};
						}

						// 1뎁스인 경우 lnb와 contentview 를 구성하는 html/js 가 등록되어야 한다.
						if(menuInfo.depthVal === '1'){
							if(!$state.get(scrnAdr)){
								$stateProvider
								.state(scrnAdr, {
									url : stateUrl,
									templateUrl: templateUrl,
									controller: controllerName
								});
							}
						}else{// 메뉴이면서 화면이 없음
							// 메뉴인 경우
							if(menuInfo.menuTypeCd === 'DIR'){
								if(!$state.get(scrnAdr)){
									$stateProvider
									.state(scrnAdr, {
										abstract: true,
										url : stateUrl,
										template: '<ui-view/>'
									});
								}
							}else{
								if(!$state.get(scrnAdr)){
									if(params){
										$stateProvider
										.state(scrnAdr, {
											url : stateUrl,
											templateUrl: templateUrl,
											controller: controllerName,
											data: {requiredLogin: true},
											params : params	
										});
									}else{
										$stateProvider
										.state(scrnAdr, {
											url : stateUrl,
											templateUrl: templateUrl,
											controller: controllerName,
											data: {requiredLogin: true}
										});
									}
								}
							}
						}

						// 자식 노드가 존재할 경우 재귀호출
						if(menuInfo.nodes && menuInfo.nodes.length > 0){
							state.makeState(menuInfo.nodes);
						}
					});
				},

				// 현재 자신의 메뉴 주소만 리턴
				currentUrl : function(stateUrl){
					var tempArr = stateUrl.split("/"),
						tempVal = "",
						tempIdx = 0,
						keepGoing = true;

					angular.forEach(tempArr, function(obj, index){
						if(keepGoing){
							if(obj.indexOf("{") > -1){
								tempIdx = index;
								keepGoing = false;
								for(var i=tempIdx-1; i<tempArr.length; i++){
									tempVal = tempVal + "/" + tempArr[i];
								}
							}
						}
					});

					if(keepGoing){
						tempVal = "/" + tempArr[tempArr.length-1];
					}

					return tempVal;
				},

				// menu_bas의 scrn_adr 정보로 템플릿 URL 을 만든다.
				makeTemplateUrl : function(scrnAdr){
					var tempArr = scrnAdr.split("/"),
						templateUrl = tempArr[tempArr.length-1];
					return 'components/' + tempArr[0] + '/' + templateUrl +'.html';
				},

				// menu_bas의 scrn_adr 정보로 컨트롤러 이름을 만든다.
				makeControllerName : function(scrnAdr){
					var DEFAULT_SUFFIX = 'Controller as vm',
						tempArr = scrnAdr.split("/");
					scrnAdr = tempArr[tempArr.length-1];
					return scrnAdr[0].toUpperCase() + scrnAdr.substr(1) + DEFAULT_SUFFIX;
				}
			}
			return state;
		}
	});