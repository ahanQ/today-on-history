package me.lqlu.api.todayonhistory.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import me.lqlu.AbstractSpringTest4Junit;
import me.lqlu.api.todayonhistory.entity.Event;

public class TestEventDao extends AbstractSpringTest4Junit {

	@Autowired
	@Qualifier("mysql")
	private EventDao jdbc;
	
	@Test
	public void testFindById() {
		Event event = jdbc.findById("003b90a2-c35e-403b-acd6-8675686c8723");
		Assert.assertNotNull(event);
		System.out.println(event.getContent());
	}

	@Test
	public void testFindByE_id() {
		Event event = jdbc.findByE_id(9357);
		Assert.assertNotNull(event);
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
		jdbc.save(event);
	}

}
