package com.anylogic.iot.base.security.filter;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.anylogic.iot.Const;
import com.anylogic.iot.api.auth.service.AuthService2;
import com.anylogic.iot.base.security.userdetails.CustomUserDetails;
import com.anylogic.iot.base.security.vo.UserDetailsVO;
import com.anylogic.iot.base.util.Crypto;

/**
 * <PRE>
 *  ClassName : CustomAuthenticationProvider
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 4. 28. 오후 5:29:39
 * @author  : jkkim
 * @brief   :
 */

public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
    private AuthService2 authService;

	@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UserDetailsVO userDetailsVO = new UserDetailsVO();

		String userid = authentication.getName();
		String password = Crypto.toHash_sha256(authentication.getCredentials().toString());
		boolean enabled = true;
		String rolePrefix = Const.ROLE_ID_PREFIX;
		String roleId = "";

		userDetailsVO.setUserId(userid);
		userDetailsVO.setPassWord(password);
		userDetailsVO = authService.login(userDetailsVO);

		if(userDetailsVO == null){
			throw new BadCredentialsException("권한이 없습니다.");
		}

		//roleId = rolePrefix + userDetailsVO.getRoleId();
		roleId = rolePrefix + "ADMIN";

		
		// 다중권한
		//List<UserDetailsVO> userAuthorities = authSVC.getAuthorities(userDetailsVO);

		Collection<? extends GrantedAuthority> authorities =
				Arrays.asList(new GrantedAuthority[] {new SimpleGrantedAuthority(roleId)});

        User user = new CustomUserDetails(userid, password, enabled, true, true, true, authorities, userDetailsVO);

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

}
