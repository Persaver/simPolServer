package fr.entities;

import java.util.Arrays;

public class Sprite {
	private int id;
	private String url;
	private String name;
	private int x, y, w, h;
	
	public Sprite() {
	}


	public Sprite(int id, String url, String name, int x, int y, int w, int h) {
		this.id = id;
		this.url = url;
		this.name = name;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getW() {
		return w;
	}


	public void setW(int w) {
		this.w = w;
	}


	public int getH() {
		return h;
	}


	public void setH(int h) {
		this.h = h;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sprite [id=");
		builder.append(id);
		builder.append(", url=");
		builder.append(url);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
}
