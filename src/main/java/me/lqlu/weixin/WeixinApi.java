package me.lqlu.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.lqlu.util.HttpGetAndPostUtil;

/**
 * weixin端的api调用
 * 
 * @author ahan
 *
 */
@Component
public class WeixinApi {

	@Autowired
	private HttpGetAndPostUtil http;

	private static final String APPID = "APPID";

	private static final String APPSECRET = "APPSECRET";

	private static final String ACCESS_TOKEN = "ACCESS_TOKEN";

	private static final String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private static final String CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	private static final String GET_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	private static final String DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	private static final String GET_CURRENT_SELFMENU_INFO = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";
	/**
	 * 获取access_token
	 * @param appID
	 * @param appSecret
	 * @return
	 */
	public String getAccessToken(String appID, String appSecret) {
		return http.get(GET_ACCESS_TOKEN.replace(APPID, appID).replace(APPSECRET, appSecret));
	}

	/**
	 * 创建菜单
	 * @param access_token
	 * @return
	 */
	public String createMenu(String access_token, String content) {
		return http.post(CREATE_MENU.replace(ACCESS_TOKEN, access_token), content);
	}
	
	/**
	 * 获取菜单
	 * @param access_token
	 * @return
	 */
	public String getMenu(String access_token) {
		return http.get(GET_MENU.replace(ACCESS_TOKEN, access_token));
	}
	
	/**
	 * 删除菜单
	 * @param access_token
	 * @return
	 */
	public String deleteMenu(String access_token) {
		return http.get(DELETE_MENU.replace(ACCESS_TOKEN, access_token));
	}

	/**
	 * 获取当前菜单配置
	 * @param access_token
	 * @return
	 */
	public String getCurrentSelfmenuInfo(String access_token) {
		return http.get(GET_CURRENT_SELFMENU_INFO.replace(ACCESS_TOKEN, access_token));
	}
}
