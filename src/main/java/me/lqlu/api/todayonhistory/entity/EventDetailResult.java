package me.lqlu.api.todayonhistory.entity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.lqlu.api.todayonhistory.entity.EventDetailResult.EventDetail.PicUrl;

/**
 * api返回的结果
 * 
 * @author ahan
 *
 * @param <T>
 */
public class EventDetailResult {

	private String reason;

	private List<EventDetail> result;

	private Integer error_code;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<EventDetail> getResult() {
		return result;
	}

	public void setResult(List<EventDetail> result) {
		this.result = result;
	}

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	public static class EventDetail {

		private int e_id;

		private String title;

		private String content;

		private String picNo;

		private List<PicUrl> picUrl;

		public int getE_id() {
			return e_id;
		}

		public void setE_id(int e_id) {
			this.e_id = e_id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getPicNo() {
			return picNo;
		}

		public void setPicNo(String picNo) {
			this.picNo = picNo;
		}

		public List<PicUrl> getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(List<PicUrl> picUrl) {
			Collections.sort(picUrl, COMPARATOR);
			this.picUrl = picUrl;
		}

		@Override
		public String toString() {
			return "EventDetail [getE_id()=" + getE_id() + ", getTitle()=" + getTitle() + ", getContent()="
					+ getContent() + ", getPicNo()=" + getPicNo() + ", getPicUrl()=" + getPicUrl() + "]";
		}

		public static class PicUrl {

			private String pic_title;

			private int id;

			private String url;

			public String getPic_title() {
				return pic_title;
			}

			public void setPic_title(String pic_title) {
				this.pic_title = pic_title;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

		}
	}

	private static final Comparator<PicUrl> COMPARATOR = new Comparator<PicUrl>() {
		@Override
		public int compare(PicUrl o1, PicUrl o2) {
			return o1.id - o2.id;
		}
	};

}
