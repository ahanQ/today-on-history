package me.lqlu.game.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import me.lqlu.api.TestApi;
import me.lqlu.game.TestAccessToken;
import me.lqlu.game.TestSecretProperties;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestAccessToken.class, TestSecretProperties.class, TestApi.class })
public class MainTest {

}
