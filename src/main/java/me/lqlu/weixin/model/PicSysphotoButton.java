package me.lqlu.weixin.model;

/**
 * 系统拍照发图
 * 
 * @author ahan
 *
 */
public class PicSysphotoButton extends Button {

	public static final String TYPE = "pic_sysphoto";
	
	private String key;
	
	public PicSysphotoButton(String name) {
		super(name, TYPE);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
