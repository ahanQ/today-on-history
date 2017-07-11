package me.lqlu.api.todayonhistory.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import me.lqlu.AbstractSpringTest4Junit;
import me.lqlu.api.todayonhistory.entity.Event;

@SuppressWarnings("deprecation")
public class TestEventDao extends AbstractSpringTest4Junit {

	@Autowired
	@Qualifier("mysql")
	private EventDao eventDao;

	@Test
	public void testFindById() {
		Event event = eventDao.findById("1");
		Assert.assertNotNull(event);
	}
	
	@Test
	public void testSave() {
		Event event = new Event();
		event.setId(RandomStringUtils.randomNumeric(16));
		event.setE_id(RandomUtils.nextInt(1, 10000));
		event.setTitle("title");
		event.setContent("content");
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

}