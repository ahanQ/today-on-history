package me.lqlu.weixin.model;

import java.util.Collection;

/**
 * 按钮基础类
 * 
 * @author ahan
 *
 */
public class Button {

	private String name;

	private String type;

	private Collection<Button> sub_button;

	public Button(String name) {
		this.name = name;
	}
	
	public Button(String name, Collection<Button> sub_button) {
		this.name = name;
		this.sub_button = sub_button;
	}
	
	public Button(String name, String type) {
		this(name, type, null);
	}

	public Button(String name, String type, Collection<Button> sub_button) {
		super();
		this.name = name;
		this.type = type;
		this.sub_button = sub_button;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Collection<Button> getSub_button() {
		return sub_button;
	}

	public void setSub_button(Collection<Button> sub_button) {
		this.sub_button = sub_button;
	}

}
