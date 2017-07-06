package me.lqlu.api.weather;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.lqlu.AbstractSpringTest4Junit;
import me.lqlu.util.JsonUtil;
import me.lqlu.util.PropertiesUtil;

public class TestWeatherApi extends AbstractSpringTest4Junit {

	@Autowired
	private PropertiesUtil prop;

	@Autowired
	private JsonUtil json;

	@Autowired
	private WeatherApi api;

	private String weatherAppKey;

	private String userHome = System.getProperty("user.home");

	private String separator = System.getProperty("file.separator");

	private String juhe = userHome + separator + ".juhe" + separator;

	private File saveFile;

	@Before
	public void init() {
		weatherAppKey = prop.readProperty("Weather", juhe + "ApiKey.properties");
	}

	@Test
	public void testReadProp() {
		Assert.assertNotNull(weatherAppKey);
		Assert.assertNotEquals("", weatherAppKey);
	}

	@Test
	public void TestIndex() {
		String city = "广州";
		String index = api.index(weatherAppKey, city, null, null);
		saveFile = new File(juhe + city + System.currentTimeMillis() + ".json");
		json.writeJson(saveFile, index);
	}

	@Test
	public void TestIp() {
		String index = api.ip(weatherAppKey, "113.111.119.156", null, null);
		saveFile = new File(juhe + "113.111.119.156 " + System.currentTimeMillis() + ".json");
		json.writeJson(saveFile, index);
	}

	@Test
	public void TestGeo() {
		String lon = "113.23";
		String lat = "23.16";
		String index = api.geo(weatherAppKey, lon, lat, null, null);
		saveFile = new File(juhe + lon + " " + lat + " " + System.currentTimeMillis() + ".json");
		json.writeJson(saveFile, index);
	}

	@Test
	public void TestForecast3h() {
		String city = "广州";
		String index = api.forecast3h(weatherAppKey, city, null);
		saveFile = new File(juhe + city + System.currentTimeMillis() + ".json");
		json.writeJson(saveFile, index);
	}

}
