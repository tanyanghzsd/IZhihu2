package com.ty.izhihu.bean;

public class Selection {
	private String images;
	private String date;
	private String display_date;
	private long id;
	private String title;
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDisplay_date() {
		return display_date;
	}
	public void setDisplay_date(String display_date) {
		this.display_date = display_date;
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
	
	public Selection() {
		// TODO Auto-generated constructor stub
	}
	
	public Selection(String images, String date, String display_date, long id, String title) {
		super();
		this.images = images;
		this.date = date;
		this.display_date = display_date;
		this.id = id;
		this.title = title;
	}
	
	
	
}
