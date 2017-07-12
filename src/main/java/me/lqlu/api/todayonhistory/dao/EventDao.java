package me.lqlu.api.todayonhistory.dao;

import java.util.List;

import me.lqlu.api.todayonhistory.entity.Event;

public interface EventDao {

	Event findById(String id);

	int save(Event event);

	int[] save(List<Event> events);

}
