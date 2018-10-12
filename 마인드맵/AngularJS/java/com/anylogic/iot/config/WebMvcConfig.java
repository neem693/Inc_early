package com.anylogic.iot.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <PRE>
 *  ClassName : WebMvcConfig
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 6. 3. 오전 9:52:59
 * @author  : jkkim
 * @brief   :
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

	@Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/index.html" );
        super.addViewControllers( registry );
    }
	
	@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

		return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {

	            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED,"/error/error.html");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,"/error/error.html");
	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/error.html");
	            ErrorPage error505Page = new ErrorPage(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "/error/error.html");
	            ErrorPage error506Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/error.html");
	            container.addErrorPages(error401Page, error404Page,
	                    error500Page, error505Page, error506Page);
	        }
	    };
	}   

}
