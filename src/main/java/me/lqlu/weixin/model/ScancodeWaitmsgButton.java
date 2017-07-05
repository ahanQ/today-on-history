package me.lqlu.weixin.model;

/**
 * 扫码带提示
 * 
 * @author ahan
 *
 */
public class ScancodeWaitmsgButton extends Button {
	
	public static final String TYPE = "scancode_waitmsg";

	private String key;
	
	public ScancodeWaitmsgButton(String name) {
		super(name, TYPE);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
