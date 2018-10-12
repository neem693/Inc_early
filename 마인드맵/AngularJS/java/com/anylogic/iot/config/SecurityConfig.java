package com.anylogic.iot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.anylogic.iot.Version;
import com.anylogic.iot.base.security.filter.CustomAccessDeniedHandler;
import com.anylogic.iot.base.security.filter.CustomAuthenticationProvider;
import com.anylogic.iot.base.security.filter.LoginFailureHandler;
import com.anylogic.iot.base.security.filter.LoginProcessFilter;
import com.anylogic.iot.base.security.filter.LoginSuccessHandler;

/**
 * <PRE>
 *  ClassName : SecurityConfig
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 5. 15. 오후 2:53:33
 * @author  : jkkim
 * @brief   :
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
        auth.authenticationProvider(customAuthenticationProvider());
    }


	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/*");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		 CharacterEncodingFilter filter = new CharacterEncodingFilter();

	        filter.setEncoding("UTF-8");

	        filter.setForceEncoding(true);
	        
		http
			.exceptionHandling()
				.accessDeniedHandler(customAccessDeniedHandler())
				.authenticationEntryPoint(authenticationEntryPoint())
				
			.and()
				.authorizeRequests()
				.antMatchers("/**").permitAll()
				.antMatchers("/v1/auth/**").permitAll()
				.antMatchers("/v1/common/**").permitAll()
				.antMatchers("/v1/sysmsg/**").permitAll()
				.antMatchers("/v1/App/**").permitAll()
				.antMatchers("/v1/mobile/**").permitAll()
				.antMatchers("/v1/front/**").permitAll()
				//.antMatchers("/kiwi/**").permitAll()
				.antMatchers(actuatorEndpoints()).access("hasAnyRole('ROLE_ADMIN')")
				.anyRequest().authenticated()
			.and()
			.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(authenticationProcessFilter(), UsernamePasswordAuthenticationFilter.class)
			.csrf().disable();
			//accessDecisionManager(defaultAccessDecisionManager(roleHierarchy()));
		//http.addFilterBefore(authenticationProcessFilter(), UsernamePasswordAuthenticationFilter.class);

	}


	private String[] actuatorEndpoints() {
		return new String[]{"/v1/**"};
	}

	@Bean
    public LoginUrlAuthenticationEntryPoint authenticationEntryPoint() {
    	return new LoginUrlAuthenticationEntryPoint("/" + Version.V1+"/auth/accessDenied");
    }


	@Bean
	public LoginProcessFilter authenticationProcessFilter() throws Exception{
		LoginProcessFilter filter = new LoginProcessFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
		filter.setFilterProcessesUrl("/v1/loginProcess");
		//filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/loginProcess"));
		filter.setAuthenticationFailureHandler(authenticationFailureHandler());
		return filter;
	}


	@Bean
	public LoginSuccessHandler authenticationSuccessHandler(){
		return new LoginSuccessHandler();
	}


	@Bean
	public LoginFailureHandler authenticationFailureHandler(){
		return new LoginFailureHandler();
	}


	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider(){
		return new CustomAuthenticationProvider();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler(){
		return new CustomAccessDeniedHandler();
	}


	@Bean
	public AffirmativeBased defaultAccessDecisionManager(RoleHierarchy roleHierarchy){
		List<AccessDecisionVoter> decisionVoters = new ArrayList<>();

		// webExpressionVoter
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy);
		webExpressionVoter.setExpressionHandler(expressionHandler);

		decisionVoters.add(webExpressionVoter);
		decisionVoters.add(roleHierarchyVoter(roleHierarchy));
		// return new AffirmativeBased(Arrays.asList((AccessDecisionVoter) webExpressionVoter));
	  return new AffirmativeBased(decisionVoters);
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("");
		/*roleHierarchy.setHierarchy(environment
				.getProperty("security.rolesHierarchy"));*/
		return roleHierarchy;
	}

	@Bean
	public RoleHierarchyVoter roleHierarchyVoter(RoleHierarchy roleHierarchy) {
	  return new RoleHierarchyVoter(roleHierarchy);
	}




}
