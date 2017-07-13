package me.lqlu.api.todayonhistory.dao;

import static org.hamcrest.number.OrderingComparison.greaterThan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import me.lqlu.AbstractSpringTest4Junit;
import me.lqlu.api.todayonhistory.entity.Event;

public class TestEventDao extends AbstractSpringTest4Junit {

	private List<Event> events = new ArrayList<>();

	@Before
	public void init() {
		Calendar now = Calendar.getInstance();
		DateFormat dateformat = new SimpleDateFormat("M/d");
		Event event = new Event();
		event.setId(UUID.randomUUID().toString());
		event.setE_id(RandomUtils.nextInt(1, 10000));
		event.setTitle("title");
		event.setContent("内容");
		event.setPicNo(7);
		event.setPicUrl("picUrl");
		now.add(Calendar.SECOND, 53847);
		event.setCollectTime(now.getTime());
		now.add(Calendar.SECOND, -153847);
		event.setDate(now.getTime());
		event.setDay(dateformat.format(now.getTime()));

		events.add(event);

		Event event2 = new Event();
		event2.setId(UUID.randomUUID().toString());
		event2.setE_id(RandomUtils.nextInt(1, 10000));
		event2.setTitle("title");
		event2.setContent("内容");
		event2.setPicNo(7);
		event2.setPicUrl("picUrl");
		now.add(Calendar.SECOND, 53847);
		event2.setCollectTime(now.getTime());
		now.add(Calendar.SECOND, -153847);
		event2.setDate(now.getTime());
		event2.setDay(dateformat.format(now.getTime()));

		events.add(event2);
	}

	@Autowired
	@Qualifier("mysql")
	private EventDao jdbc;

	@Test
	public void testFindById() {
		Event event = jdbc.findById("003b90a2-c35e-403b-acd6-8675686c8723");
		Assert.assertNotNull(event);
	}

	@Test
	public void testFindByE_id() {
		Event event = jdbc.findByE_id(9357);
		Assert.assertNotNull(event);
	}

	@Test
	public void testFindByDate() {
		List<Event> events = jdbc.findByDate(Calendar.getInstance().getTime());
		Assert.assertThat(events.size(), greaterThan(1));
		Assert.assertNotNull(events);
	}

	@Test
	public void testSave() {
		int rowNum = jdbc.save(events.get(0));
		Assert.assertEquals(1, rowNum);
	}

	@Test
	public void testSaveEvents() {
		int[] rowNum = jdbc.save(events);
		Assert.assertEquals(1, rowNum[0]);
		Assert.assertEquals(1, rowNum[1]);
	}

	@Test
	public void testDelete() {
		int rowNum = jdbc.deleteById("78fb9966-d064-4609-b04e-94a9c8525e00");
		Assert.assertEquals(1, rowNum);
	}

}
