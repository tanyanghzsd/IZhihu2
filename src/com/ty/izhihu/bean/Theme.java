package com.ty.izhihu.bean;

import java.io.Serializable;

public class Theme implements Serializable{
	private String name;
	private int id;
	private String thumbnail;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Theme() {
		// TODO Auto-generated constructor stub
	}
	public Theme(String name, int id, String thumbnail, String description) {
		super();
		this.name = name;
		this.id = id;
		this.thumbnail = thumbnail;
		this.description = description;
	}
	
	
}
