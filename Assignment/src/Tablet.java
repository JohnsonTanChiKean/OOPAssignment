
public class Tablet extends Product{
	private double displaySize;
	private int sizeOfRam;
	private String color;
	
	public Tablet() {
		
	}
	
	public Tablet(String productID, String productName, double price, String category, String type, int quantity, double displaySize, int sizeOfRam, String color) {
		super(productID, productName, price, category, type, quantity);
		setDisplaySize(displaySize);
		setSizeOfRam(sizeOfRam);
		setColor(color);
	}
	
	public double getDisplaySize() {
		return displaySize;
	}
	public void setDisplaySize(double displaySize) {
		this.displaySize = displaySize;
	}
	public int getSizeOfRam() {
		return sizeOfRam;
	}
	public void setSizeOfRam(int sizeOfRam) {
		this.sizeOfRam = sizeOfRam;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-15s%-17s%-17s%-8s%-16s%s\n", "Product ID", "Product Name", "Display Size", "RAM", "Color", "Price");
		productDetails+=String.format("%-15s%-17s%-17s%-8s%-16s%s\n", "----------", "------------", "------------", "---", "-----", "-----");
		productDetails+=String.format("%-15s%-17s%.1f%-13s%d%-7s%-16s%.2f", super.getProductID(), super.getProductName(), displaySize, "inches", sizeOfRam, "GB", color, super.getPrice());
		return productDetails;
	}
}
