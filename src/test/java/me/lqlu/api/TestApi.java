package me.lqlu.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import me.lqlu.api.todayonhistory.config.TestConfig;
import me.lqlu.api.todayonhistory.dao.TestEventDao;
import me.lqlu.api.weather.TestWeatherApi;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestWeatherApi.class, TestConfig.class, TestEventDao.class })
public class TestApi {

}
