package me.lqlu.api.todayonhistory.entity;

import java.util.List;

/**
 * api返回的结果
 * 
 * @author ahan
 *
 * @param <T>
 */
public class EventResult {

	private String reason;

	private List<Event> result;

	private Integer error_code;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<Event> getResult() {
		return result;
	}

	public void setResult(List<Event> result) {
		this.result = result;
	}

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	public static class Event {
		private String day;

		private String date;

		private String title;

		private int e_id;

		public String getDay() {
			return day;
		}

		public void setDay(String day) {
			this.day = day;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getE_id() {
			return e_id;
		}

		public void setE_id(int e_id) {
			this.e_id = e_id;
		}

		@Override
		public String toString() {
			return "Event [getDay()=" + getDay() + ", getDate()=" + getDate() + ", getTitle()=" + getTitle()
					+ ", getE_id()=" + getE_id() + "]";
		}

	}

}
