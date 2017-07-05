package me.lqlu.weixin.model;

/**
 * 微信相册发图
 * 
 * @author ahan
 *
 */
public class PicPhotoOrAlbumButton extends Button {

	public static final String TYPE = "pic_photo_or_album";
	
	private String key;
	
	public PicPhotoOrAlbumButton(String name) {
		super(name, TYPE);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
