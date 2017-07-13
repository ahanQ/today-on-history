package me.lqlu.api.todayonhistory.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import me.lqlu.api.todayonhistory.entity.Event;

@Component
@Qualifier("mysql")
public class MysqlEventDao implements EventDao {

	private static final String FIND_BY_DATE = "select * from event where day=?";

	private static final String DELETE = "delete from event where id=?";

	private static final String INSERT = "insert into event (id, e_id, title, content, picNo, picUrl, date, day, collect_time) values (?,?,?,?,?,?,?,?,?)";

	private static final String FIND_BY_ID = "select * from event where id=?";

	private static final String FIND_BY_E_ID = "select * from event where e_id=?";

	private static final ResultSetExtractor<Event> EVENTRESULTSETEXTRACTOR = new ResultSetExtractor<Event>() {
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
	};

	private static final RowMapper<Event> EVENTROWSET = new RowMapper<Event>() {

		@Override
		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
			Event event = new Event();
			event.setId(rs.getString("id"));
			event.setE_id(rs.getInt("e_id"));
			event.setTitle(rs.getString("title"));
			event.setContent(rs.getString("content"));
			event.setPicNo(rs.getInt("picNo"));
			event.setPicUrl(rs.getString("picUrl"));
			event.setDate(rs.getDate("date"));
			event.setDay(rs.getString("day"));
			event.setCollectTime(rs.getDate("collect_time"));
			return event;
		}

	};

	@Autowired
	private JdbcOperations jdbc;

	@Override
	public Event findById(String id) {
		return jdbc.query(FIND_BY_ID, EVENTRESULTSETEXTRACTOR, id);
	}

	@Override
	public Event findByE_id(int e_id) {
		return jdbc.query(FIND_BY_E_ID, EVENTRESULTSETEXTRACTOR, e_id);
	}

	@Override
	public List<Event> findByDate(Date date) {
		DateFormat format = new SimpleDateFormat("M/d");
		return jdbc.query(FIND_BY_DATE, EVENTROWSET, format.format(date));
	}

	@Override
	public int save(Event event) {
		return jdbc.update(INSERT, eventPreparedStatementSetter(event));
	}

	@Override
	public int[] save(List<Event> events) {
		return jdbc.batchUpdate(INSERT, eventBatchPreparedStatementSetter(events));
	}

	@Override
	public int deleteById(String id) {
		return jdbc.update(DELETE, id);
	}

	private BatchPreparedStatementSetter eventBatchPreparedStatementSetter(List<Event> events) {
		return new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Event event = events.get(i);
				int offset = 0;
				ps.setString(++offset, event.getId());
				ps.setInt(++offset, event.getE_id());
				ps.setString(++offset, event.getTitle());
				ps.setString(++offset, event.getContent());
				ps.setInt(++offset, event.getPicNo());
				ps.setString(++offset, event.getPicUrl());
				ps.setTimestamp(++offset, new Timestamp(event.getDate().getTime()));
				ps.setString(++offset, event.getDay());
				ps.setTimestamp(++offset, new Timestamp(event.getCollectTime().getTime()));
			}

			@Override
			public int getBatchSize() {
				return events.size();
			}
		};
	}

	private PreparedStatementSetter eventPreparedStatementSetter(Event event) {
		return new PreparedStatementSetter() {
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
		};
	}
}
