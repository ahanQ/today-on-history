package me.lqlu.game;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.lqlu.AbstractSpringTest4Junit;
import me.lqlu.weixin.AccessTokenCache;

public class TestAccessToken extends AbstractSpringTest4Junit {

	@Autowired
	private AccessTokenCache cache;

	@Test
	public void testAccessToken() {
		String access_token = cache.readAccess_Token();
		Assert.assertNotNull(access_token);
	}
	
}
