package model.dto;

public class Tablet extends Product {

	private String tBattery = null;
	private String tMemory = null;
	private String tOS = null;
	private double tSize = 0;
	private String productId = null;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String gettBattery() {
		return tBattery;
	}
	public void settBattery(String tBattery) {
		this.tBattery = tBattery;
	}
	public String gettMemory() {
		return tMemory;
	}
	public void settMemory(String tMemory) {
		this.tMemory = tMemory;
	}
	public String gettOS() {
		return tOS;
	}
	public void settOS(String tOS) {
		this.tOS = tOS;
	}
	public double gettSize() {
		return tSize;
	}
	public void settSize(double tSize) {
		this.tSize = tSize;
	}
}
