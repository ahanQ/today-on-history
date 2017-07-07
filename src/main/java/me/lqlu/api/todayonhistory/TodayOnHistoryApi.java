package me.lqlu.api.todayonhistory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.lqlu.util.HttpGetAndPostUtil;

/**
 * 历史上的今天聚合接口
 * 
 * @author ahan
 *
 */
@Component
public class TodayOnHistoryApi {

	@Autowired
	private HttpGetAndPostUtil http;

	private static final DateFormat DATEFORMAT = new SimpleDateFormat("M/d");

	private static final String APIKEY = "{ApiKey}";

	private static final String DATE = "{date}";

	private static final String E_ID = "{e_id}";

	private static final String QUERYEVENT = "http://v.juhe.cn/todayOnhistory/queryEvent?key={ApiKey}&date={date}";

	private static final String QUERYDETAIL = "http://v.juhe.cn/todayOnhistory/queryDetail?key={ApiKey}&e_id={e_id}";

	/**
	 * 事件列表
	 * 
	 * @param apiKey
	 *            必须
	 * @param date
	 *            必须， 日期
	 * @return
	 */
	public String queryEvent(String apiKey, Date date) {
		String dateStr = DATEFORMAT.format(date);
		try {
			dateStr = URLEncoder.encode(dateStr, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		String url = QUERYEVENT.replace(APIKEY, apiKey).replace(DATE, dateStr);
		return http.get(url);
	}

	/**
	 * 根据事件id查询详细信息
	 * 
	 * @param apiKey
	 *            必须
	 * @param e_id
	 *            事件id
	 * @return
	 */
	public String queryDetail(String apiKey, int e_id) {
		String url = QUERYDETAIL.replace(APIKEY, apiKey).replace(E_ID, String.valueOf(e_id));
		return http.get(url);
	}

}
