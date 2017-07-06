package me.lqlu.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import me.lqlu.api.weather.TestWeatherApi;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestWeatherApi.class })
public class TestApi {

}
