package me.lqlu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import me.lqlu.api.todayonhistory.Service;
import me.lqlu.api.todayonhistory.entity.EventDetailResult.EventDetail;
import me.lqlu.api.todayonhistory.entity.EventResult.Event;

@RestController
public class TodayOnHistory {

	private static final Log LOG = LogFactory.getLog(TodayOnHistory.class);

	@Autowired
	private Service service;

	@GetMapping("/todayonhistory")
	public Map<String, Object> todayOnHistory() {
		Map<String, Object> result = new HashMap<>();
		List<Event> events = service.todayEvents();
		result.put("events", events);
		List<EventDetail> eventDetails = new ArrayList<>();
		for (Event event : events) {
			int e_id = event.getE_id();
			List<EventDetail> details = service.queryDetail(e_id);
			if (details == null) {
				LOG.error("e_id: " + e_id + " no content.");
				continue;
			}
			for (EventDetail detail : details) {
				eventDetails.add(detail);
			}
		}
		result.put("details", eventDetails);
		return result;
	}
}
