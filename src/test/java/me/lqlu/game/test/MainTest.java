package me.lqlu.game.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import me.lqlu.game.TestAccessToken;
import me.lqlu.game.TestSecretProperties;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestAccessToken.class, TestSecretProperties.class })
public class MainTest {

}
