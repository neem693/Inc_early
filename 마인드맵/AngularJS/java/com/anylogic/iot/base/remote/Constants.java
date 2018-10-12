package com.anylogic.iot.base.remote;

public class Constants {

	// 인증 관련 변수
	public static final String AUTH_GRANT_TYPE     = "grant_type";
	public static final String AUTH_GRANT_TYPE_MSG = "client_credentials";
	public static final String AUTH_AUTHORIZATION  = "Authorization";
	public static final String AUTH_ACCESSTOKEN = "access_token";

	public static final String _PREFIX_JWT_BEFORE = "Basic ";
	public static final String _PREFIX_JWT_AFTER  = "Bearer ";

	public static final String _PREFIX_RESULT_CODE = "responseCode";
	public static final String _PREFIX_RESULT_MAG = "message";
	public static final String _PREFIX_RESULT_DATA = "data";

	public static final String _PREFIX_RESULT_CODE_OK = "OK";
	public static final String _PREFIX_RESULT_CODE_NG = "NG";

	// API URL
	public static final String API_GET_SVCS_DSTR = "api.svcs.dstr.get";
	public static final String API_POST_SVCS_DSTR = "api.svcs.dstr.post";
	public static final String API_PUT_SVCS_DSTR = "api.svcs.dstr.put";
	public static final String API_DELETE_SVCS_DSTR = "api.svcs.dstr.delete";
	public static final String API_GET_SVCS_THEME = "api.svcs.theme.get";
	public static final String API_POST_SVCS_THEME = "api.svcs.theme.post";
	public static final String API_PUT_SVCS_THEME = "api.svcs.theme.put";
	public static final String API_DELETE_SVCS_THEME = "api.svcs.theme.delete";
	public static final String API_GET_SVCS_UNIT = "api.svcs.unit.get";
	public static final String API_POST_SVCS_UNIT = "api.svcs.unit.post";
	public static final String API_PUT_SVCS_UNIT = "api.svcs.unit.put";
	public static final String API_DELETE_SVCS_UNIT = "api.svcs.unit.delete";
	public static final String API_GET_SVCS_LIST = "api.svcs.list.get";
	public static final String API_GET_SVC_TGT = "api.svctgt.list.get";
	public static final String API_GET_SVC_TGT_EXCEL = "api.svctgt.list.excel.get";
	public static final String API_POST_SVC_TGT = "api.svctgt.list.post";

	public static final String API_POST_SVC_TGT_V2 = "api.svctgt.list2.post";
	public static final String API_GET_SVC_TGT_V2 = "api.svctgt.list2.get";

	public static final String API_PUT_SVC_TGT = "api.svctgt.list.put";
	public static final String API_DELETE_SVC_TGT = "api.svctgt.list.delete";
	public static final String API_GET_SNSNTAGS = "api.snsntags.list.get";
	public static final String API_GET_SNSNTAGS_EXCEL = "api.snsntags.list.excel.get";
	public static final String API_POST_SNSNTAGS = "api.snsntags.list.post";
	public static final String API_PUT_SNSNTAGS = "api.snsntags.list.put";
	public static final String API_DELETE_SNSNTAGS = "api.snsntags.list.delete";
	public static final String API_GET_DEVMODELS = "api.devModels.list.get";
	public static final String API_GET_DEVMODELS_v2 = "api.devModels.list.get.v2";
	public static final String API_GET_DEVMODELS_EXCEL = "api.devModels.list.excel.get";
	public static final String API_POST_DEVMODELS = "api.devModels.list.post";
	public static final String API_POST_DEVMODELS_V2 = "api.devModels.list.post.v2";
	public static final String API_PUT_DEVMODELS = "api.devModels.list.put";
	public static final String API_PUT_DEVMODELS_V2 = "api.devModels.list.put.v2";

	public static final String API_DELETE_DEVMODELS = "api.devModels.list.delete";
	public static final String API_DELETE_DEVMODELS_V2 = "api.devModels.list.delete.v2";

	public static final String API_GET_DEVMODELS_SNSN = "api.devModels.snsnList.get";
	public static final String API_GET_DEVMODELS_SNSN2 = "api.devModels.snsnList2.get"; //only devModelSeq
	public static final String API_GET_DEVMODELS_APPLY_SNSN = "api.devModels.applySnsnList.get";
	public static final String API_POST_DEVMODELS_APPLY_SNSN = "api.devModels.applySnsnList.post";
	public static final String API_POST_DEVMODELS_APPLY_SNSN_V2 = "api.devModels.applySnsnList.post.v2";
	public static final String API_POST_DEVMODELS_DELETE_SNSN_V2 = "api.devModels.delSnsnList.post.v2";

	public static final String API_GET_IMAGE = "api.devModels.imageList.get";
	public static final String API_POST_IMAGE = "api.devModels.imageList.post";
	public static final String API_DELETE_IMAGE = "api.devModels.imageList.delete";
	public static final String API_GET_IMAGE_DOWN = "api.devModels.imageDown.get";
	public static final String API_GET_PROTOCOL_TYPES = "api.protocol.list.get";
	public static final String API_GET_BIND_TYPES = "api.bind.list.get";

	public static final String API_GET_EVENTLIST = "api.event.list.get";
	public static final String API_GET_EVENTSTATUS_CHANGE = "api.event.status.change.get";
	public static final String API_GET_EVENTSTATUS_DEPLOY = "api.event.status.deploy.get";

	public static final String API_GET_EVENTSTATUS_ADMIN_DEPLOY = "api.event.status.admin.deploy.get";
	public static final String API_GET_LOG = "api.event.log.get";
//	public static final String API_GET_LOG_FOR_STAT = "api.event.log.get.forStat";
	public static final String API_GET_SELECT_EVENT_DATA = "api.event.select.event.data";
	public static final String API_GET_SELECT_EVENT_DATA2 = "api.event.select.event.data2";
	public static final String API_GET_EDITOR_DELETE = "api.event.editor.delete.post";
	public static final String API_GET_EDITOR_DATA = "api.event.editor.data.get";
	public static final String API_GET_EDITOR_SAVE = "api.event.editor.save.post";
	public static final String API_GET_EDITOR_EDIT = "api.event.editor.edit.post";
	public static final String API_GET_EDITOR_OTHERDATA = "api.event.edtor.otherData.get";
	public static final String API_GET_EDITOR_EVENTCHECK = "api.event.edtor.addEventCheck.get";
	public static final String API_GET_EDITOR_VALIDATEEPL = "api.event.edtor.validateEpl.post";
	public static final String API_GET_EDITOR_VALLIST = "api.event.edtor.eventValList.get";
	public static final String API_GET_PROTOCOL_USER_LIST = "api.event.master.protocol.user.get";
	public static final String API_GET_DEVMODEL_LIST = "api.event.master.devmodel.get";
	public static final String API_GET_PROTOCOL_LIST = "api.event.master.protocol.get";
	public static final String API_GET_TAGSTREAM_LIST = "api.event.master.streams.get";
	public static final String API_GET_TAGSTREAM_LIST2 = "api.event.master.streams2.get";

	public static final String API_GET_CODES = "api.codes.get";
	public static final String API_GET_CODES2 = "api.codes2.get";
	public static final String API_GET_DEVICE_LIST1 = "api.device.list1.get";
	public static final String API_GET_DEVICE_LIST2 = "api.device.list2.get";	//ottpYn==''
	public static final String API_GET_DEVICE_LIST3 = "api.device.list3.get";	//useYn==''
	public static final String API_GET_DEVICE_LIST4 = "api.device.list4.get";	//useYn=='',ottpYn==''
	public static final String API_GET_DEVICE_LIST5 = "api.device.list5.get";
	public static final String API_GET_DEVICE_LIST6 = "api.device.list6.get";	//svcTgtSeq
	public static final String API_GET_DEVICE_LIST_V2 = "api.device.list.get.v2";
	public static final String API_GET_DEVICE_LIST_COMMON_V2 = "api.device.list.get.common.v2";
	public static final String API_GET_DEVICE_LIST_INFO_V2 = "api.device.get.v2";

	public static final String API_GET_DEVICE_INFO_V2 = "api.device.info.get.v2";

	public static final String API_GET_DEVICE_STATUS = "api.device.status";
	public static final String API_GET_DEVICE_IMAGE = "api.device.image";
	public static final String API_GET_DEVICE_IMAGE64 = "api.device.image64";
	public static final String API_GET_DEVICE_VIRUTAL_STATUS = "api.device.virtual.status.get";
	public static final String API_PUT_DEVICE_TOGGLE = "api.device.toggle.put";
	public static final String API_GET_DEVICE_CTGRY = "api.device.ctgry.get";
	public static final String API_GET_DEVICE = "api.device.get";
	public static final String API_POST_DEVICE = "api.device.post";
	public static final String API_POST_DEVICE_V2 = "api.device.post.v2";
	public static final String API_PUT_DEVICE = "api.device.put";
	public static final String API_PUT_DEVICE_V2 = "api.device.put.v2";

	public static final String API_DELETE_DEVICE = "api.device.delete";
	public static final String API_DELETE_DEVICE_V2 = "api.device.delete.v2";
	public static final String API_GET_LAST = "api.device.tagstrm.last";
	public static final String API_GET_SENSTAG_LOG = "api.device.SensTag.Log";

	public static final String API_GET_DEVICE_LOGS_V2 = "api.device.logs.v2";

	public static final String API_GET_ECINFO= "api.ec.gwcnct.get";
	public static final String API_EC_RECVSTAT= "api.ec.rcvpacktstat.post";
	public static final String API_EC_SENDSTAT= "api.ec.trmpacktstat.post";
	public static final String API_EC_CURSESSION_DEV_CNT="api.ec.cursessiondevcnt.get";

	public static final String API_GET_ANALYSYS_DEV_DATAHISTORY= "api.analysis.dev.datahistory.get";

	public static final String API_POST_EVENTRULE= "api.eventrule.post";
	public static final String API_POST_UPDATE_EVENTRULE= "api.eventrule.update.post";

	//master api - device
	public static final String API_GET_DEVICE_CONFIRM = "api.device.confirm.get";
	public static final String API_PUT_MASTER_DEVICE_CTRL = "api.master.device.ctrl";
	public static final String API_POST_DEVICES = "api.master.device.post";

	//master api - log
	public static final String API_GET_DEVICE_LOG = "api.device.log.get";

	//master api - member
	public static final String API_POST_MEMBER_JOIN = "api.member.join.post";

	//push api - insert app id
	public static final String API_POST_INSERT_APP_ID = "api.push.insert.app.id";
	public static final String API_DELETE_GCMID = "api.push.delete.gcmId";

	//core api
	public static final String API_POST_EVENT = "api.event.editor.save.post";
	public static final String API_GET_EVENT_TARGET = "api.event.get.target";
	

	//homeAPI
	public static final String API_GET_HOMECAM_EVENT= "api.homecam.event.get";
	public static final String API_GET_HOMEMANAGER_EVENT= "api.homemanager.event.get";
	public static final String API_GET_HOMECAM_MEMBER= "api.homecam.member.get";
	public static final String API_GET_HOMECAM_DEVCTRL= "api.homecam.devctrl.get";
	public static final String API_GET_HOMEIOT_CONNCNT= "api.ec.homeiot.conncnt";

	//portalAPI
	public static final String API_GET_PORTAL_DEVICE_LOG = "api.portal.device.log.get";
	public static final String API_GET_PORTAL_DEVICE_CTRL = "api.portal.device.ctrl";
	public static final String API_GET_WHETHER= "api.whether.get";

	//externalAPI
	public static final String API_PUT_ACTIVATION = "api.external.activation.put";
	public static final String API_GET_CONTRACT = "api.external.contract.get";
}
