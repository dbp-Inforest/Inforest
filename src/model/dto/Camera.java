package model.dto;

public class Camera extends Product{

	private double cDisplay;
	private int cPixel;
	private String cBattery;
	private String cVibration;
	private double cBurstshot;
	private String productId;
	private String cLens;
	
	public double getcDisplay() {
		return cDisplay;
	}
	public void setcDisplay(double cDisplay) {
		this.cDisplay = cDisplay;
	}
	public double getcPixel() {
		return cPixel;
	}
	public void setcPixel(int cPixel) {
		this.cPixel = cPixel;
	}
	public String getcBattery() {
		return cBattery;
	}
	public void setcBattery(String d) {
		this.cBattery = d;
	}
	public String getcVibration() {
		return cVibration;
	}
	public void setcVibration(String cVibration) {
		this.cVibration = cVibration;
	}
	public double getcBurstshot() {
		return cBurstshot;
	}
	public void setcBurstshot(double cBurstshot) {
		this.cBurstshot = cBurstshot;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getcLens() {
		return cLens;
	}
	public void setcLens(String cLens) {
		this.cLens = cLens;
	}
	
	
}
