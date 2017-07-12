package me.lqlu.api.todayonhistory.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import me.lqlu.api.todayonhistory.entity.Event;

@Component
@Qualifier("mysql")
public class MysqlEventDao implements EventDao {

	private static final String INSERT = "insert into event (id, e_id, title, content, picNo, picUrl, date, day, collect_time) values (?,?,?,?,?,?,?,?,?)";

	private static final String FIND_BY_ID = "select * from event where id=?";

	@Autowired
	private JdbcOperations jdbc;

	@Override
	public Event findById(String id) {
		return jdbc.query(FIND_BY_ID, new ResultSetExtractor<Event>() {

			@Override
			public Event extractData(ResultSet rs) throws SQLException, DataAccessException {
				Event event = new Event();
				if (rs.next()) {
					event.setId(rs.getString("id"));
					event.setE_id(rs.getInt("e_id"));
					event.setTitle(rs.getString("title"));
					event.setContent(rs.getString("content"));
					event.setPicNo(rs.getInt("picNo"));
					event.setPicUrl(rs.getString("picUrl"));
					event.setDate(rs.getDate("date"));
					event.setDay(rs.getString("day"));
					event.setCollectTime(rs.getDate("collect_time"));
				}
				return event;
			}

		}, id);
	}

	@Override
	public int save(Event event) {
		return jdbc.update(INSERT, new PreparedStatementSetter() {
			private void setValues(Event event, PreparedStatement ps) throws SQLException {
				int i = 0;
				ps.setString(++i, event.getId());
				ps.setInt(++i, event.getE_id());
				ps.setString(++i, event.getTitle());
				ps.setString(++i, event.getContent());
				ps.setInt(++i, event.getPicNo());
				ps.setString(++i, event.getPicUrl());
				ps.setTimestamp(++i, new Timestamp(event.getDate().getTime()));
				ps.setString(++i, event.getDay());
				ps.setTimestamp(++i, new Timestamp(event.getCollectTime().getTime()));
			}

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				setValues(event, ps);
			}
		});
	}

	@Override
	public int[] save(List<Event> events) {
		return jdbc.batchUpdate(INSERT, new BatchPreparedStatementSetter() {
			private void setValues(PreparedStatement ps, Event event) throws SQLException {
				int i = 0;
				ps.setString(++i, event.getId());
				ps.setInt(++i, event.getE_id());
				ps.setString(++i, event.getTitle());
				ps.setString(++i, event.getContent());
				ps.setInt(++i, event.getPicNo());
				ps.setString(++i, event.getPicUrl());
				ps.setTimestamp(++i, new Timestamp(event.getDate().getTime()));
				ps.setString(++i, event.getDay());
				ps.setTimestamp(++i, new Timestamp(event.getCollectTime().getTime()));
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Event event = events.get(i);
				setValues(ps, event);
			}

			@Override
			public int getBatchSize() {
				return events.size();
			}
		});
	}

}
