package com.ty.izhihu.bean;

import java.io.Serializable;

public class News implements Serializable{
	private long id;
	private String title;
	private String images;
	
	public News() {
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public News(long id, String title, String images) {
		super();
		this.id = id;
		this.title = title;
		this.images = images;
	}
	
	
}
