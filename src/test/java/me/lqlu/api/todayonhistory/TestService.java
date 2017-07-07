package me.lqlu.api.todayonhistory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.lqlu.AbstractSpringTest4Junit;
import me.lqlu.api.todayonhistory.entity.EventDetailResult.EventDetail;
import me.lqlu.api.todayonhistory.entity.EventResult.Event;

public class TestService extends AbstractSpringTest4Junit {

	private static final Log LOG = LogFactory.getLog(TestService.class);

	@Autowired
	private Service service;

	/**
	 * 一年三百六十六天随机一天的数据
	 */
	@Test
	public void testQueryEventsAndQueryDetail() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, RandomUtils.nextInt(0, 366));
		Date date = calendar.getTime();
		List<Event> events = service.queryEvents(date);
		for (Event event : events) {
			System.out.println(event);
			int e_id = event.getE_id();
			List<EventDetail> details = service.queryDetail(e_id);
			if (details == null) {
				LOG.error("e_id: " + e_id + " no content.");
				continue;
			}
			for (EventDetail detail : details) {
				System.out.println(detail);
			}
		}
	}

	/**
	 * 今天的数据
	 */
	@Test
	public void testTodayEvents() {
		List<Event> events = service.todayEvents();
		for (Event event : events) {
			System.out.println(event);
		}
	}

}
