package me.lqlu.weixin.model;

/**
 * 拍照或者相册发图
 * 
 * @author ahan
 *
 */
public class PicWeixinButton extends Button {
	
	public static final String TYPE = "pic_weixin";

	private String key;
	
	public PicWeixinButton(String name) {
		super(name, TYPE);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
