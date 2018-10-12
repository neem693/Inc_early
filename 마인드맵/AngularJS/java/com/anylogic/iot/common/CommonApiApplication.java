package com.anylogic.iot.common;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import com.anylogic.iot.Const;


@Configuration
@ComponentScan(basePackages=Const.PACKAGE)

@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class })
@EnableConfigurationProperties(com.anylogic.iot.base.remote.HttpProperties.class)
//@EnableEncryptableProperties
@PropertySources({
  
	 @PropertySource(name="EncryptedProperties", value = "classpath:properties/application.properties", ignoreResourceNotFound = true),
    @PropertySource(value = "classpath:properties/interface.properties", ignoreResourceNotFound = true)
})
@EnableScheduling
//@ImportResource("classpath:/tasks.xml")
public class CommonApiApplication extends SpringBootServletInitializer implements WebApplicationInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CommonApiApplication.class);
    }

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//		servletContext.addListener(new ContextLoaderListener(context));
		context.setConfigLocation(Const.PACKAGE);
		context.setServletContext(servletContext);

		Dynamic servlet = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/*");
		servlet.setMultipartConfig(new MultipartConfigElement("", 1024*1024*500, 1024*1024*100, 1024*1024*10));

		// CharacterSet Encoding
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic charEncRegi = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        charEncRegi.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

//        FilterRegistration.Dynamic redirectFilter = servletContext.addFilter("redirectFilter", com.anylogic.iot.base.filter.RedirectionFilter.class);
//        //redirectFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/masterapi/*");
//        redirectFilter.addMappingForUrlPatterns(null, false, "/*");


//		FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", CORSFilter.class);
//		corsFilter.addMappingForUrlPatterns(null, false, "/*");
//
//
        
        servletContext.addFilter("springSecurityFilterChain",  new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "/*");

		super.onStartup(servletContext);
	}

	public static void main(String[] args) {
		//System.setProperty ( " jasypt.encryptor.password " , "encryptkey" );
		SpringApplication.run(CommonApiApplication.class, args);
	}
}