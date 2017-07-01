package me.lqlu.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.lqlu.util.HttpGetAndPostUtil;

@Component
public class WeixinApi {

	@Autowired
	private HttpGetAndPostUtil http;

	@Autowired
	private SecretProperties secretProperties;

	private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	public String getAccessToken() {
		String url = GET_ACCESS_TOKEN_URL.replace("APPID", secretProperties.getAppID()).replace("APPSECRET",
				secretProperties.getAppSecret());
		return http.get(url);
	}

}
