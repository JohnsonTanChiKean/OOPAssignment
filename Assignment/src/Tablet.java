
public class Tablet extends Product{
	private double displaySize;
	private int sizeOfRam;
	
	public Tablet() {
		
	}
	
	public Tablet(String productID, String productName, double price, String category, String type, int quantity, double displaySize, int sizeOfRam, String color) {
		super(productID, productName, price, category, type, quantity, color);
		setDisplaySize(displaySize);
		setSizeOfRam(sizeOfRam);
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
	
	public String toString() {
		String productDetails="";
		if(sizeOfRam==8) {
			productDetails+=String.format("%-10s------------------------------------------------------------------------------------------\n","");
			productDetails+=String.format("%-10s|                                                                                        |\n","");
			productDetails+=String.format("%-10s|    %-15s%-17s%-17s%-8s%-16s%-10s |\n", "", "Product ID", "Product Name", "Display Size", "RAM", "Color", "Price");
			productDetails+=String.format("%-10s|    %-15s%-17s%-17s%-8s%-16s%-10s |\n", "","----------", "------------", "------------", "---", "-----", "-----");
			productDetails+=String.format("%-10s|    %-15s%-17s%.1f%-13s%d%-7s%-16s%-10.2f |\n", "", super.getProductID(), super.getProductName(), displaySize, "inches", sizeOfRam, "GB", super.getColor(), super.getPrice());
			productDetails+=String.format("%-10s|                                                                                        |\n","");
			productDetails+=String.format("%-10s------------------------------------------------------------------------------------------\n","");
		}
		
		else {
			productDetails+=String.format("%-10s-------------------------------------------------------------------------------------------\n","");
			productDetails+=String.format("%-10s|                                                                                         |\n","");
			productDetails+=String.format("%-10s|    %-15s%-17s%-17s%-9s%-16s%-10s |\n", "", "Product ID", "Product Name", "Display Size", "RAM", "Color", "Price");
			productDetails+=String.format("%-10s|    %-15s%-17s%-17s%-9s%-16s%-10s |\n", "","----------", "------------", "------------", "---", "-----", "-----");
			productDetails+=String.format("%-10s|    %-15s%-17s%.1f%-13s%d%-7s%-16s%-10.2f |\n", "", super.getProductID(), super.getProductName(), displaySize, "inches", sizeOfRam, "GB", super.getColor(), super.getPrice());
			productDetails+=String.format("%-10s|                                                                                         |\n","");
			productDetails+=String.format("%-10s-------------------------------------------------------------------------------------------\n","");
		}
		return productDetails;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Tablet) {
			if(((Tablet)o).getProductID().equals(super.getProductID())) {
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
