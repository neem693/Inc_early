package com.anylogic.iot.base.security.userdetails.util;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.anylogic.iot.base.util.Crypto;

public class KtPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return Crypto.toHash_sha256(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword == null || rawPassword == null) {
			return false;
		}

		if (!encodedPassword.equals(encode(rawPassword))) {
			return false;
		}
		return true;
	}

}
