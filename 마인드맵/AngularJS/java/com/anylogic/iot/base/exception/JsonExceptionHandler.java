package com.anylogic.iot.base.exception;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.dao.UncategorizedDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.anylogic.iot.base.message.BaseResponse;

/**
 * <PRE>
 *  ClassName : JsonExceptionHandler
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 5. 15. 오후 2:15:11
 * @author  : jkkim
 * @brief   :
 */
@Component
public class JsonExceptionHandler implements HandlerExceptionResolver {

	@Autowired
	private MessageSource messageSource;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub

		try{
			ObjectMapper mapper = new ObjectMapper();
	    	response.setContentType("application/json; charset=UTF-8");
	    	BaseResponse baseResponse = new BaseResponse();

	    	String errorText = messageSource.getMessage("fw.errortext.scexpectation_failed", null, Locale.getDefault());

        	//예외 메시지 정의...추가...필요
	    	if(ex instanceof DataIntegrityViolationException){
        		errorText = messageSource.getMessage("fw.errortext.dataintegrityviolation", null, Locale.getDefault());
        	}else if(ex instanceof UncategorizedDataAccessException){
        		//미분류 오류 발생
        		errorText = messageSource.getMessage("fw.errortext.scexpectation_failed", null, Locale.getDefault());
        	}else if(ex instanceof DeadlockLoserDataAccessException){
        		//Dead-Lock 발생 트랙잭션 오류
        		errorText = messageSource.getMessage("fw.errortext.scexpectation_failed", null, Locale.getDefault());
        	}else if(ex instanceof OptimisticLockingFailureException){
        		//optimistic locking 위반. ORM 또는 DAO 구현 오류
        		errorText = messageSource.getMessage("fw.errortext.scexpectation_failed", null, Locale.getDefault());
        	}else if(ex instanceof DataRetrievalFailureException){
        		//데이타 조회 실패
        		errorText = messageSource.getMessage("fw.errortext.scexpectation_failed", null, Locale.getDefault())+messageSource.getMessage("fw.errortext.dataretrievalfailure", null, Locale.getDefault());
        	}else if(ex instanceof InvalidDataAccessResourceUsageException
        			|| ex instanceof IncorrectUpdateSemanticsDataAccessException
        			|| ex instanceof TypeMismatchDataAccessException){
        		//SQL 문법 오류
        		errorText = messageSource.getMessage("fw.errortext.scexpectation_failed", null, Locale.getDefault());
        	}else if(ex instanceof InvalidDataAccessApiUsageException){
        		//api 잘못 사용
        		errorText = messageSource.getMessage("fw.errortext.scexpectation_failed", null, Locale.getDefault());
        	}else if(ex instanceof CleanupFailureDataAccessException){
        		//CRUD는 성공했으나 리소스회수 실패함. 예) connection.close() 실패 등등
        		errorText = messageSource.getMessage("fw.errortext.scexpectation_failed", null, Locale.getDefault());
        	}else if(ex instanceof DataAccessResourceFailureException){
        		//DB연결 실패 등 리소스 접근 오류 등.
        		errorText = messageSource.getMessage("fw.errortext.dataaccessresourcefailure", null, Locale.getDefault());
        	}else if(ex instanceof BadCredentialsException){
        		errorText = messageSource.getMessage("fw.errortext.dataaccessresourcefailure", null, Locale.getDefault());
        		baseResponse.setResponseCode("403");
        	}else if(ex instanceof KTBCCException){
        		//DB연결 실패 등 리소스 접근 오류 등.
         		errorText = ex.getMessage();
        	}


	    	baseResponse.setMessage(errorText);
	    	baseResponse.setResponseNG();
	        mapper.writeValue(response.getWriter(), baseResponse);

		}catch(Exception e){
			e.printStackTrace();
		}

		return new ModelAndView();
	}

}
