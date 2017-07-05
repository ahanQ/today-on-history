package me.lqlu.weixin.model;

/**
 * 图文消息
 * 
 * @author ahan
 *
 */
public class ViewLimitedButton extends Button {
	
	public static final String TYPE = "view_limited";

	private String media_id;
	
	public ViewLimitedButton(String name) {
		super(name, TYPE);
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

}
