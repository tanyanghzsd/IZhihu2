package com.ty.izhihu.bean;

public class Comments {
	private String author;
	private long id;
	private String content;
	private int likes;
	private long time;
	private String avatar;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public Comments() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Comments(String author, long id, String content, int likes, long time, String avatar) {
		super();
		this.author = author;
		this.id = id;
		this.content = content;
		this.likes = likes;
		this.time = time;
		this.avatar = avatar;
	}
	
	
}
