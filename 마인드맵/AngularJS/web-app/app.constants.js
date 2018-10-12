
angular
	.module('app')
	.constant("Version", {
      /*  "V1": "/adminui/v1" ,
        "V2": "/adminui/api/v1.1"*/
		  "V1": "/seian_admin/v1" ,
	        "V2": "/seian_admin/api/v1.1"
    })
    .constant("masterApi", {
    	"V1": "/masterapi/v1"
    })
    .constant('messageType', {
        warning : "warning",
        normal : undefined,
        info : "info",
        success : "success"
    });