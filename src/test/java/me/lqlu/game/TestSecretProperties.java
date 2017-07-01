package me.lqlu.game;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.lqlu.AbstractSpringTest4Junit;
import me.lqlu.weixin.SecretProperties;

public class TestSecretProperties extends AbstractSpringTest4Junit {

	@Autowired
	private SecretProperties secretProp;

	@Test
	public void testSecret() {
		Assert.assertTrue(StringUtils.isNotBlank(secretProp.getAppID()));
		Assert.assertTrue(StringUtils.isNotBlank(secretProp.getAppSecret()));
	}

}
