package me.lqlu.weixin;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AccessTokenCache implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(AccessTokenCache.class);

	@Autowired
	private WeixinApi api;

	private AccessToken accessToken;

	private Date due;

	private String filePath;

	private File file;

	private ObjectMapper mapper;

	public String readAccess_Token() {
		if (accessToken == null) {
			loadAccessTokenFromApi();
		}
		Date now = Calendar.getInstance().getTime();
		if (due == null || now.after(due)) {
			loadAccessTokenFromApi();
		}
		return accessToken.getAccess_token();
	}

	private void loadAccessTokenFromApi() {
		String content = api.getAccessToken();
		try {
			AccessToken access = mapper.readValue(content, AccessToken.class);
			Calendar instance = Calendar.getInstance();
			instance.add(Calendar.SECOND, access.getExpires_in().intValue());
			setAccessToken(access);
			setDue(instance.getTime());
			logger.info("load access_token from WeixinApi");
			logger.info("access_token: " + accessToken.getAccess_token());
			DateFormat formatter = DateFormat.getDateTimeInstance();
			logger.info("access_token due to: " + formatter.format(due));
			logger.info("write access_token to " + file.getAbsolutePath());
			mapper.writeValue(file, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		filePath = System.getProperty("user.home") + "/.weixin/accessCache.json";
		file = new File(filePath);
		mapper = new ObjectMapper();
		try {
			AccessTokenCache readValue = mapper.readValue(file, AccessTokenCache.class);
			Date now = Calendar.getInstance().getTime();
			if (readValue.due != null && now.before(readValue.getDue())) {
				setAccessToken(readValue.getAccessToken());
				setDue(readValue.getDue());
				logger.info("load access_token from " + file.getAbsolutePath());
				logger.info("access_token: " + accessToken.getAccess_token());
				DateFormat formatter = DateFormat.getDateTimeInstance();
				logger.info("access_token due to " + formatter.format(due));
			}
		} catch (IOException e1) {
			logger.warn(file.getAbsolutePath() + " in not exists");
		}
	}

}
