package me.lqlu.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.lqlu.AbstractSpringTest4Junit;
import me.lqlu.weixin.AccessTokenCache;
import me.lqlu.weixin.SecretProperties;
import me.lqlu.weixin.WeixinApi;
import me.lqlu.weixin.model.Button;
import me.lqlu.weixin.model.ClickButton;
import me.lqlu.weixin.model.Menu;
import me.lqlu.weixin.model.MiniprogramButton;
import me.lqlu.weixin.model.ViewButton;

public class TestWeixinApi extends AbstractSpringTest4Junit {

	@Autowired
	private SecretProperties prop;

	@Autowired
	private WeixinApi api;
	
	@Autowired
	private AccessTokenCache accessTokenCache;

	@Test
	public void testAccessToken() {
		String appID = prop.getAppID();
		String appSecret = prop.getAppSecret();
		String accessToken = api.getAccessToken(appID, appSecret);
		Assert.assertNotNull(accessToken);
		Assert.assertNotEquals(accessToken, "");
	}

	@Test
	public void generateMenu() {

		Button click = new ClickButton("今日歌曲", "V1001_TODAY_MUSIC");

		Collection<Button> subButtons = new ArrayList<Button>();
		subButtons.add(new ClickButton("赞一下我们", "V1001_GOOD"));
		subButtons.add(
				new MiniprogramButton("wxa", "http://mp.weixin.qq.com", "wx286b93c14bbf93aa", "pages/lunar/index"));
		subButtons.add(new ViewButton("搜索", "http://www.soso.com/"));

		Button button2 = new Button("菜单", subButtons);

		Collection<Button> buttons = new ArrayList<Button>();
		buttons.add(click);
		buttons.add(button2);

		Menu menu = new Menu(buttons);

		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			mapper.writeValue(System.out, menu);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	@Test
	public void createMenu() {
		Button click = new ClickButton("今日歌曲", "V1001_TODAY_MUSIC");

		Collection<Button> subButtons = new ArrayList<Button>();
		subButtons.add(new ClickButton("赞一下我们", "V1001_GOOD"));
		subButtons.add(
				new MiniprogramButton("wxa", "http://mp.weixin.qq.com", "wx286b93c14bbf93aa", "pages/lunar/index"));
		subButtons.add(new ViewButton("搜索", "http://www.soso.com/"));

		Button button2 = new Button("菜单", subButtons);

		Collection<Button> buttons = new ArrayList<Button>();
		buttons.add(click);
		buttons.add(button2);

		Menu menu = new Menu(buttons);

		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			String menuJson = mapper.writeValueAsString(menu);
			String access_token = accessTokenCache.readAccess_Token();
			api.createMenu(access_token, menuJson);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}

	
		
	}
}
