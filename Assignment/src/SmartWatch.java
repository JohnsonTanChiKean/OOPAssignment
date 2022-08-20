
public class SmartWatch extends Product{
	private double screenSize;
	private int waterResistance, series;
	private String resolution, batteryLife, color;
	
	public SmartWatch() {
		
	}
	
	public SmartWatch(String productID, String productName, double price, String category, String type, int quantity, double screenSize, String resolution, int series, String batteryLife, int waterResistance, String color) {
		super(productID, productName, price, category, type, quantity);
		setScreenSize(screenSize);
		setSeries(series);
		setResolution(resolution);
		setBatteryLife(batteryLife);
		setWaterResistance(waterResistance);
		setColor(color);
	}
	
	public double getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(double screenSize) {
		this.screenSize = screenSize;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getBatteryLife() {
		return batteryLife;
	}
	public void setBatteryLife(String batteryLife) {
		this.batteryLife = batteryLife;
	}
	public int getWaterResistance() {
		return waterResistance;
	}
	public void setWaterResistance(int waterResistance) {
		this.waterResistance = waterResistance;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-15s%-17s%-16s%-18s%-11s%-19s%-21s%-13s%s\n", "Product ID", "Product Name", "Screen Size", "Resolution", "Series", "Battery Life", "Water Resistance", "Color", "Price");
		productDetails+=String.format("%-15s%-17s%-16s%-18s%-11s%-19s%-21s%-13s%s\n", "----------", "------------", "-----------", "----------", "------", "------------", "----------------", "-----", "-----");
		productDetails+=String.format("%-15s%-17s%.0f%-14s%s%-11s%-11d%-19s%-21d%-13s%.2f", super.getProductID(), super.getProductName(), screenSize, "mm", resolution, "pixels", series, batteryLife, waterResistance, color, super.getPrice());
		return productDetails;
	}
}
