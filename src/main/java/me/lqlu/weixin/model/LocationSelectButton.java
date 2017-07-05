package me.lqlu.weixin.model;

/**
 * 发送位置
 * 
 * @author ahan
 *
 */
public class LocationSelectButton extends Button {

	public static final String TYPE = "location_select";
	
	private String key;
	
	public LocationSelectButton(String name) {
		super(name, TYPE);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
