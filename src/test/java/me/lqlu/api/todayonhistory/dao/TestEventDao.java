package me.lqlu.api.todayonhistory.dao;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import me.lqlu.AbstractSpringTest4Junit;
import me.lqlu.api.todayonhistory.entity.Event;
import me.lqlu.api.todayonhistory.entity.EventDetailResult;
import me.lqlu.api.todayonhistory.entity.EventDetailResult.EventDetail;
import me.lqlu.api.todayonhistory.entity.EventDetailResult.EventDetail.PicUrl;
import me.lqlu.api.todayonhistory.entity.EventResult;
import me.lqlu.util.JsonUtil;

public class TestEventDao extends AbstractSpringTest4Junit {

	@Autowired
	@Qualifier("mysql")
	private EventDao eventDao;
	
	@Autowired
	private JsonUtil json;

	@Test
	public void testFindById() {
		Event event = eventDao.findById("2d1f3c2c-c9cd-493c-b4bb-f48cf769e3a1");
		Assert.assertNotNull(event);
		System.out.println(event.getContent());
	}

	@Test
	public void testSave() {
		Event event = new Event();
		event.setId(UUID.randomUUID().toString());
		event.setE_id(RandomUtils.nextInt(1, 10000));
		event.setTitle("title");
		event.setContent("内容");
		event.setPicNo(7);
		event.setPicUrl("picUrl");

		Calendar now = Calendar.getInstance();
		now.add(Calendar.SECOND, -153847);
		event.setCollectTime(now.getTime());

		now.add(Calendar.SECOND, -153847);
		event.setDate(now.getTime());

		DateFormat dateformat = new SimpleDateFormat("M/d");
		event.setDay(dateformat.format(now.getTime()));
		eventDao.save(event);
	}

	@Test
	public void tranform() {

		String userHome = System.getProperty("user.home");

		String separator = System.getProperty("file.separator");

		String todayOnHistory = userHome + separator + ".juhe" + separator + "TodayOnHistory" + separator;
		
		File dir = new File(todayOnHistory);
		
		File[] files = dir.listFiles();
		
		DateFormat dateformat = new SimpleDateFormat("y年M月d日");

		Map<Integer, List<Event>> eventDetailMap = new HashMap<>();
		for (File file : files) {
			String name = file.getName();
			if(name.contains("EventDetail")) {
				String eventDetailName = name.substring(0, name.indexOf(".json"));
				String[] eventDetailSplit = eventDetailName.split("-");
				String collectTimeStr = eventDetailSplit[2];
				Date collectTime = new Date(Long.parseLong(collectTimeStr));
				EventDetailResult eventDetailResult = json.readValue(file, EventDetailResult.class);
				List<EventDetail> eventDetails = eventDetailResult.getResult();
				if(eventDetails == null) {
					continue;
				}
				EventDetail eventDetail = eventDetails.get(0);
				Integer e_id = eventDetail.getE_id();
				String content = eventDetail.getContent();
				String picNoStr = eventDetail.getPicNo();
				Integer picNo = Integer.parseInt(picNoStr);
				String title = eventDetail.getTitle();
				List<PicUrl> picUrlList = eventDetail.getPicUrl();
				String picUrl = json.toJsonString(picUrlList);
				
				Event event = new Event();
				event.setId(UUID.randomUUID().toString());
				event.setTitle(title);
				event.setContent(content);
				event.setE_id(e_id);
				event.setPicNo(picNo);
				event.setPicUrl(picUrl);
				event.setCollectTime(collectTime);
				
				Assert.assertTrue(StringUtils.isNotBlank(event.getId()));
				Assert.assertTrue(StringUtils.isNotBlank(event.getTitle()));
				Assert.assertTrue(StringUtils.isNotBlank(event.getContent()));
				Assert.assertNotNull(event.getE_id());
				Assert.assertNotNull(event.getPicNo());
				Assert.assertTrue(StringUtils.isNotBlank(event.getPicUrl()));
				Assert.assertNotNull(event.getCollectTime());
				
				List<Event> eventDetailList = eventDetailMap.get(e_id);
				eventDetailList = eventDetailList == null ? new ArrayList<Event>() : eventDetailList;
				eventDetailList.add(event);
				eventDetailMap.put(e_id, eventDetailList);
			}
		}
		
		List<Event> eventResultList = new ArrayList<Event>();
		for (File file : files) {
			String name = file.getName();
			if(name.contains("Events")) {
				EventResult eventResult = json.readValue(file, EventResult.class);
				
				List<me.lqlu.api.todayonhistory.entity.EventResult.Event> events = eventResult.getResult();
				for (me.lqlu.api.todayonhistory.entity.EventResult.Event event : events) {
					String day = event.getDay();
					String dateStr = event.getDate();
					Date date = null;
					try {
						date = dateformat.parse(dateStr.replace("前", "-"));
					} catch (ParseException e) {
						System.out.println(dateStr);
						Assert.fail();
					}
					
					Integer e_id = event.getE_id();
					
					List<Event> eventList = eventDetailMap.remove(e_id);
					if(eventList == null) {
						continue;
					}
					for (Event event_r : eventList) {
						event_r.setDate(date);
						event_r.setDay(day);
						Assert.assertTrue(StringUtils.isNotBlank(event_r.getId()));
						Assert.assertTrue(StringUtils.isNotBlank(event_r.getTitle()));
						Assert.assertTrue(StringUtils.isNotBlank(event_r.getContent()));
						Assert.assertNotNull(event_r.getE_id());
						Assert.assertNotNull(event_r.getPicNo());
						Assert.assertTrue(StringUtils.isNotBlank(event_r.getPicUrl()));
						Assert.assertNotNull(event_r.getCollectTime());
						Assert.assertNotNull(event_r.getDate());
						Assert.assertTrue(StringUtils.isNotBlank(event_r.getDay()));
						eventResultList.add(event_r);
					}
				}
			}
		}
		int[] saveSuccess = eventDao.save(eventResultList);
		for (int i : saveSuccess) {
			if (i != 1) {
				System.out.println(i);
			}
		}
	}

}
