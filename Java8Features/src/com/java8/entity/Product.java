package com.java8.entity;

public class Product {
	
	private String name;
	private int id;
	private float price;
	public Product(int id, String name, float price) {
		super();
		this.name = name;
		this.id = id;
		this.price = price;
	}
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", id=" + id + ", price=" + price + "]";
	}

}
