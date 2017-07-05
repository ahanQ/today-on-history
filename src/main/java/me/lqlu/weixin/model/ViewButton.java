package me.lqlu.weixin.model;

/**
 * 链接跳转
 * @author ahan
 *
 */
public class ViewButton extends Button {
	
	public static final String TYPE = "view";

	private String url;
	
	public ViewButton(String name, String url) {
		super(name, TYPE);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
