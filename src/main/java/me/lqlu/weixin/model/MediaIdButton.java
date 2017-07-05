package me.lqlu.weixin.model;

/**
 * 图片
 * 
 * @author ahan
 *
 */
public class MediaIdButton extends Button {

	public static final String TYPE = "media_id";
	
	private String media_id;

	public MediaIdButton(String name) {
		super(name, TYPE);
	}
	
	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

}
