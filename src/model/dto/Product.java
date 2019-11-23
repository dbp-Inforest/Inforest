package model.dto;

import java.sql.Date;

public class Product {
	private String productId = null;
	private String name = null;
	private String color = null;
	private String price = null;
	private String brand = null;
	private Date released_date = null;
	private double weight = (Double) null;
	private int pKind = (Integer) null;
	
	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Date getReleased_date() {
		return released_date;
	}
	public void setReleased_date(Date released_date) {
		this.released_date = released_date;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getpKind() {
		return pKind;
	}
	public void setpKind(int pKind) {
		this.pKind = pKind;
	}
}
