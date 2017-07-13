package me.lqlu.api.todayonhistory.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.lqlu.api.todayonhistory.TodayOnHistoryApi;
import me.lqlu.api.todayonhistory.dao.EventDao;
import me.lqlu.api.todayonhistory.entity.Event;
import me.lqlu.api.todayonhistory.entity.EventDetailResult;
import me.lqlu.api.todayonhistory.entity.EventDetailResult.EventDetail;
import me.lqlu.util.JsonUtil;
import me.lqlu.util.PropertiesUtil;

//@Component
public class Service {

	private static final DateFormat DATEFORMAT = new SimpleDateFormat("M-d");

	@Autowired
	private TodayOnHistoryApi api;

	@Autowired
	private JsonUtil json;

	@Autowired
	private PropertiesUtil prop;
	
	@Autowired
	private EventDao dao;

	private String apiKey;

	public Event findById(String id) {
		return dao.findById(id);
	}

	public List<Event> findByDate(Date date) {
		return dao.findByDate(date);
	}

	public List<EventDetail> queryDetail(int e_id) {
		String content = api.queryDetail(apiKey, e_id);
		EventDetailResult result = json.readValue(content, EventDetailResult.class);
		return result.getResult();
	}

//	@Override
//	public void afterPropertiesSet() throws Exception {
//		String userHome = System.getProperty("user.home");
//		String separator = System.getProperty("file.separator");
//		String juhe = userHome + separator + ".juhe" + separator;
//		apiKey = prop.readProperty("TodayOnHistory", juhe + "ApiKey.properties");
//	}


	
}
