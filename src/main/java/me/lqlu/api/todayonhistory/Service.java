package me.lqlu.api.todayonhistory;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.lqlu.api.todayonhistory.entity.EventDetailResult;
import me.lqlu.api.todayonhistory.entity.EventResult;
import me.lqlu.api.todayonhistory.entity.EventDetailResult.EventDetail;
import me.lqlu.api.todayonhistory.entity.EventResult.Event;
import me.lqlu.util.JsonUtil;
import me.lqlu.util.PropertiesUtil;

/**
 * 历史上的今天接口服务类
 * 
 * @author ahan
 *
 */
@Component
public class Service implements InitializingBean {

	private static final DateFormat DATEFORMAT = new SimpleDateFormat("M-d");

	@Autowired
	private TodayOnHistoryApi api;

	@Autowired
	private JsonUtil json;

	@Autowired
	private PropertiesUtil prop;

	private String apiKey;

	private String userHome = System.getProperty("user.home");

	private String separator = System.getProperty("file.separator");

	private String todayOnHistory = userHome + separator + ".juhe" + separator + "TodayOnHistory" + separator;

	public List<Event> todayEvents() {
		return queryEvents(Calendar.getInstance().getTime());
	}

	public List<Event> queryEvents(Date date) {
		String content = api.queryEvent(apiKey, date);
		EventResult result = json.readValue(content, EventResult.class);
		long currentTimeMillis = System.currentTimeMillis();
		File file = new File(todayOnHistory + DATEFORMAT.format(date) + "-Events-" + currentTimeMillis + ".json");
		List<Event> events = result.getResult();
		json.writeJson(file, result);
		return events;
	}

	public List<EventDetail> queryDetail(int e_id) {
		String content = api.queryDetail(apiKey, e_id);
		EventDetailResult result = json.readValue(content, EventDetailResult.class);
		List<EventDetail> detail = result.getResult();
		long currentTimeMillis = System.currentTimeMillis();
		File file = new File(todayOnHistory + e_id + "-EventDetail-" + currentTimeMillis + ".json");
		json.writeJson(file, result);
		return detail;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String userHome = System.getProperty("user.home");
		String separator = System.getProperty("file.separator");
		String juhe = userHome + separator + ".juhe" + separator;
		apiKey = prop.readProperty("TodayOnHistory", juhe + "ApiKey.properties");
	}

}