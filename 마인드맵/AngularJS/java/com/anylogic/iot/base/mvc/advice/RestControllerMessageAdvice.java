package com.anylogic.iot.base.mvc.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.anylogic.iot.base.common.InterfacJson;
import com.anylogic.iot.base.message.BaseResponse;
import com.anylogic.iot.base.mvc.context.BaseRequestContextHolder;
import com.anylogic.iot.base.mvc.message.MessageAccessor;

/**
 * RestControler에 적용되는 ControllerAdvice로서 BaseResponse로 message나 데이터를 wrapping하여 클라이언트에 전달하는 데이터 골격을 생성한다.
 * @author jeado
 *
 */
@ControllerAdvice(annotations = RestController.class)
public class RestControllerMessageAdvice implements ResponseBodyAdvice<Object> {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#supports(org.springframework.core.MethodParameter, java.lang.Class)
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO 이부분은 바꿔야함.
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#beforeBodyWrite(java.lang.Object, org.springframework.core.MethodParameter, org.springframework.http.MediaType, java.lang.Class, org.springframework.http.server.ServerHttpRequest, org.springframework.http.server.ServerHttpResponse)
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

		/* Master API를 통해서 받는것들에 대해서 Master API 결과 그대로 화면으로 전송 */
		if(body instanceof InterfacJson) {
			InterfacJson interfaceJson = (InterfacJson)body;
			return interfaceJson.convertJson();
		}

		MessageAccessor messageAccessor = BaseRequestContextHolder.get().getMessageAccessor();
		//TOOD 기본 Response가 확정되면 해당 Response로 변경해야함.

		BaseResponse baseResponse = new BaseResponse();

		if(messageAccessor != null && messageAccessor.getMessageList().size() > 0){
//			baseResponse.setResponseOK();
			baseResponse.setResponseCode(messageAccessor.getMessageList().get(0).getCode());
			baseResponse.setMessage(messageAccessor.getMessageList().get(0).getMsg());
		}

		baseResponse.setData(body);

		return body instanceof BaseResponse ? body : baseResponse;
	}

	/**
	 * 기본 예외처리 헨들러, 다른 예외처리 헨들러에서 처리되지 않은 예외들을 처리함.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse> handleOtherExceptions(Exception ex, WebRequest request) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setResponseNG();
		baseResponse.setMessage(ex.getMessage());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<BaseResponse>(baseResponse, headers, HttpStatus.OK);
	}

}
