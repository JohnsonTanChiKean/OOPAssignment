
public class SmartWatch extends Product{
	private double screenSize;
	private int waterResistance, series;
	private String resolution, batteryLife;
	
	public SmartWatch() {
		
	}
	
	public SmartWatch(String productID, String productName, double price, String category, String type, int quantity, double screenSize, String resolution, int series, String batteryLife, int waterResistance, String color) {
		super(productID, productName, price, category, type, quantity, color);
		setScreenSize(screenSize);
		setSeries(series);
		setResolution(resolution);
		setBatteryLife(batteryLife);
		setWaterResistance(waterResistance);
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
	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-10s---------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		productDetails+=String.format("%-10s|                                                                                                                                                 |\n", "");
		productDetails+=String.format("%-10s|    %-15s%-17s%-16s%-18s%-11s%-19s%-21s%-13s%-10s |\n", "", "Product ID", "Product Name", "Screen Size", "Resolution", "Series", "Battery Life", "Water Resistance", "Color", "Price");
		productDetails+=String.format("%-10s|    %-15s%-17s%-16s%-18s%-11s%-19s%-21s%-13s%-10s |\n", "", "----------", "------------", "-----------", "----------", "------", "------------", "----------------", "-----", "-----");
		productDetails+=String.format("%-10s|    %-15s%-17s%.0f%-14s%s%-11s%-11d%-19s%-21d%-13s%-10.2f |\n", "", super.getProductID(), super.getProductName(), screenSize, "mm", resolution, "pixels", series, batteryLife, waterResistance, super.getColor(), super.getPrice());
		productDetails+=String.format("%-10s|                                                                                                                                                 |\n", "");
		productDetails+=String.format("%-10s---------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		return productDetails;
	}
	
	public boolean equals(Object o) {
		if(o instanceof SmartWatch) {
			if(((SmartWatch)o).getProductID().equals(super.getProductID())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
