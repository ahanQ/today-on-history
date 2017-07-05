package me.lqlu.weixin.model;

/**
 * 扫码推
 * 
 * @author ahan
 *
 */
public class ScancodePushButton extends Button {
	
	public static final String TYPE = "scancode_push";

	private String key;
	
	public ScancodePushButton(String name) {
		super(name, TYPE);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
