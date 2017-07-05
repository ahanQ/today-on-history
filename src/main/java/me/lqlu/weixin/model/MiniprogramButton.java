package me.lqlu.weixin.model;

/**
 * 小程序跳转
 * @author ahan
 *
 */
public class MiniprogramButton extends Button {

	public static final String TYPE = "miniprogram";
	
	private String url;

	private String appid;

	private String pagepath;

	public MiniprogramButton(String name, String url, String appid, String pagepath) {
		super(name, TYPE);
		this.url = url;
		this.appid = appid;
		this.pagepath = pagepath;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPagepath() {
		return pagepath;
	}

	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
}
