package me.lqlu.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.lqlu.util.PropertiesUtil;

@Component
public class SecretProperties {

	private String appID;

	private String appSecret;

	private String secretFileLocation = System.getProperty("user.home") + "/.weixin/secret.properties";

	@Autowired
	private PropertiesUtil prop;

	public String getAppID() {
		if (appID == null) {
			appID = prop.readProperty("AppID", secretFileLocation);
		}
		return appID;
	}

	public String getAppSecret() {
		if (appSecret == null) {
			appSecret = prop.readProperty("AppSecret", secretFileLocation);
		}
		return appSecret;
	}

}
