package me.lqlu.api.weather;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.lqlu.util.HttpGetAndPostUtil;

/**
 * 聚合城市天气api接口
 * 
 * @author ahan
 *
 */
@Component
public class WeatherApi {

	@Autowired
	private HttpGetAndPostUtil http;

	private static final String APIKEY = "{ApiKey}";

	private static final String CITYNAME = "{cityname}";

	private static final String IPADDR = "{ipaddr}";

	private static final String LON = "{lon}";

	private static final String LAT = "{lat}";

	private static final String INDEX = "http://v.juhe.cn/weather/index?cityname={cityname}&key={ApiKey}";

	private static final String UNI = "http://v.juhe.cn/weather/uni?key={ApiKey}";

	private static final String IP = "http://v.juhe.cn/weather/ip?key={ApiKey}&ip={ipaddr}";

	private static final String GEO = "http://v.juhe.cn/weather/geo?key={ApiKey}&lon={lon}&lat={lat}";

	private static final String FORECAST3H = "http://v.juhe.cn/weather/forecast3h?cityname={cityname}&key={ApiKey}";

	private static final String GET_CITYS = "http://v.juhe.cn/weather/citys?key={ApiKey}";

	/**
	 * 根据城市名/id查询天气
	 * 
	 * @param apiKey
	 *            必须
	 * @param cityname
	 *            必须，城市名或城市ID，如："苏州"，需要utf8 urlencode
	 * @param format
	 *            未来6天预报(future)两种返回格式，1或2，默认1
	 * @param dtype
	 *            返回数据格式：json或xml,默认json
	 * @return
	 */
	public String index(String apiKey, String cityname, Integer format, String dtype) {
		try {
			cityname = URLEncoder.encode(cityname, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}

		String url = INDEX.replace(APIKEY, apiKey).replace(CITYNAME, cityname);
		if (format != null && format == 2) {
			url += url + "&format=2";
		}
		if ("xml".equals(dtype)) {
			url += url + "&dtype=xml";
		}
		return http.get(url);
	}

	/**
	 * 天气种类及标识列表</br>
	 * 此接口返回数据基本不变，开发者可内置数据，图标下载：http://www.juhe.cn/news/index/id/48
	 * 
	 * @param apiKey
	 *            必须
	 * @param dtype
	 *            返回数据格式：json或xml,默认json
	 * @return
	 */
	public String uni(String apiKey, String dtype) {
		String url = UNI.replace(APIKEY, apiKey);
		if ("xml".equals(dtype)) {
			url += url + "&dtype=xml";
		}
		return http.get(url);
	}

	/**
	 * 根据IP查询天气</br>
	 * 根据IP地址查询IP地址所在地的天气预报
	 * 
	 * @param apiKey
	 *            必须
	 * @param ip
	 *            必须，ip地址
	 * @param format
	 *            未来6天预报(future)两种返回格式，1或2，默认1
	 * @param dtype
	 *            返回数据格式：json或xml,默认json
	 * @return
	 */
	public String ip(String apiKey, String ipaddr, Integer format, String dtype) {
		String url = IP.replace(APIKEY, apiKey).replace(IPADDR, ipaddr);
		if (format != null && format == 2) {
			url += url + "&format=2";
		}
		if ("xml".equals(dtype)) {
			url += url + "&dtype=xml";
		}
		return http.get(url);
	}

	/**
	 * 根据IP查询天气</br>
	 * 根据IP地址查询IP地址所在地的天气预报
	 * 
	 * @param apiKey
	 *            必须
	 * @param lon
	 *            必须，经度
	 * @param lat
	 *            必须，纬度
	 * @param format
	 *            未来6天预报(future)两种返回格式，1或2，默认1
	 * @param dtype
	 *            返回数据格式：json或xml,默认json
	 * @return
	 */
	public String geo(String apiKey, String lon, String lat, Integer format, String dtype) {
		String url = GEO.replace(APIKEY, apiKey).replace(LON, lon).replace(LAT, lat);
		if (format != null && format == 2) {
			url += url + "&format=2";
		}
		if ("xml".equals(dtype)) {
			url += url + "&dtype=xml";
		}
		return http.get(url);
	}

	/**
	 * 城市天气三小时预报
	 * 
	 * @param apiKey
	 *            必须
	 * @param cityname
	 *            必须，城市名或城市ID，如："苏州"，需要utf8 urlencode
	 * @param dtype
	 *            返回数据格式：json或xml,默认json
	 * @return
	 */
	public String forecast3h(String apiKey, String cityname, String dtype) {
		try {
			cityname = URLEncoder.encode(cityname, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}

		String url = FORECAST3H.replace(APIKEY, apiKey).replace(CITYNAME, cityname);
		if ("xml".equals(dtype)) {
			url += url + "&dtype=xml";
		}
		return http.get(url);
	}

	/**
	 * 获取城市列表
	 * 
	 * @param apiKey
	 *            必须
	 * @param dtype
	 *            xml或json 默认 json
	 * @return
	 */
	public String citys(String apiKey, String dtype) {
		String url = GET_CITYS.replace(APIKEY, apiKey);
		if ("xml".equals(dtype)) {
			url += url + "&dtype=xml";
		}
		return http.get(url);
	}
}
