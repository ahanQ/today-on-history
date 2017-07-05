package me.lqlu.weixin.model;

import java.util.Collection;

/**
 * 菜单类
 * 
 * @author ahan
 *
 */
public class Menu {

	private Collection<Button> button;

	public Menu(Collection<Button> button) {
		super();
		this.button = button;
	}

	public Collection<Button> getButton() {
		return button;
	}

	public void setButton(Collection<Button> button) {
		this.button = button;
	}
}
