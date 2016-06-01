package com.ty.izhihu.bean;

public class HistoryNews {
	private String date;
	private String images;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public HistoryNews(String date, String images) {
		super();
		this.date = date;
		this.images = images;
	}
	
	
}
