package com.ty.izhihu.bean;

public class HotNews {
	private long news_id;
	private String url;
	private String thumbnail;
	private String title;
	public long getNews_id() {
		return news_id;
	}
	public void setNews_id(long news_id) {
		this.news_id = news_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HotNews(long news_id, String url, String thumbnail, String title) {
		super();
		this.news_id = news_id;
		this.url = url;
		this.thumbnail = thumbnail;
		this.title = title;
	}
	
	
}
