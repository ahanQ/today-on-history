package me.lqlu.game;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.lqlu.AbstractSpringTest4Junit;
import me.lqlu.weixin.SecretProperties;
import me.lqlu.weixin.WeixinApi;

public class TestWeixinApi extends AbstractSpringTest4Junit {

	@Autowired
	private SecretProperties prop;

	@Autowired
	private WeixinApi api;

	@Test
	public void testAccessToken() {
		String appID = prop.getAppID();
		String appSecret = prop.getAppSecret();
		String accessToken = api.getAccessToken(appID, appSecret);
		Assert.assertNotNull(accessToken);
		Assert.assertNotEquals(accessToken, "");
	}
}
