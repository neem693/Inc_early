package com.anylogic.iot.base.remote;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * <PRE>
 *  ClassName : HttpProperties
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 6. 8. 오후 8:27:15
 * @author  : jun
 * @brief   :
 */

@ConfigurationProperties(prefix="auth")
@PropertySources({
	/*@PropertySource(value = "file:${properties.path}/application.properties", ignoreResourceNotFound = true)*/
	@PropertySource(value = "classpath:properties/application.properties", ignoreResourceNotFound = true)
})
public class HttpProperties {
	private String clientId;
	private String clientSecurity;
	private String authUrl;

	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecurity() {
		return clientSecurity;
	}
	public void setClientSecurity(String clientSecurity) {
		this.clientSecurity = clientSecurity;
	}
	public String getAuthUrl() {
		return authUrl;
	}
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	@Override
	public String toString() {
		return "HttpProperties [clientId=" + clientId + ", clientSecurity="
				+ clientSecurity + ", authUrl=" + authUrl + "]";
	}
}
