package me.lqlu.weixin.model;

/**
 * 单击推事件
 * 
 * @author ahan
 *
 */
public class ClickButton extends Button {

	public static final String TYPE = "click";
	
	private String key;

	public ClickButton(String name) {
		this(name, null);
	}
	
	public ClickButton(String name, String key) {
		super(name, TYPE);
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
