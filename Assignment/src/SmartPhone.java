
public class SmartPhone extends Product{
	private int storageCapacity, sizeOfRam;
	private String color;
	private double screenSize;
	
	public SmartPhone() {
		
	}
	
	public SmartPhone(String productID, String productName, double price, String category, String type, int quantity, int storageCapacity, int sizeOfRam, double screenSize, String color) {
		super(productID, productName, price, category, type, quantity);
		setStorageCapacity(storageCapacity);
		setSizeOfRam(sizeOfRam);
		setScreenSize(screenSize);
		setColor(color);
	}
	public int getSizeOfRam() {
		return sizeOfRam;
	}

	public void setSizeOfRam(int sizeOfRam) {
		this.sizeOfRam = sizeOfRam;
	}

	public double getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(double screenSize) {
		this.screenSize = screenSize;
	}

	public int getStorageCapacity() {
		return storageCapacity;
	}
	public void setStorageCapacity(int storageCapacity) {
		this.storageCapacity = storageCapacity;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-10s--------------------------------------------------------------------------------------------------------------\n","");
		productDetails+=String.format("%-10s|                                                                                                            |\n","");
		productDetails+=String.format("%-10s|    %-15s%-17s%-21s%-8s%-15s%-17s%-10s |\n", "", "Product ID", "Product Name", "Internal Storage", "RAM", "Screen Size", "Color", "Price");
		productDetails+=String.format("%-10s|    %-15s%-17s%-21s%-8s%-15s%-17s%-10s |\n", "", "----------", "------------", "----------------", "---", "-----------", "-----", "-----");
		productDetails+=String.format("%-10s|    %-15s%-17s%d%-18s%d%-7s%.2f%-11s%-17s%-10.2f |\n", "", super.getProductID(), super.getProductName(), storageCapacity, "GB", sizeOfRam, "GB", screenSize, "inches", color, super.getPrice());
		productDetails+=String.format("%-10s|                                                                                                            |\n","");
		productDetails+=String.format("%-10s--------------------------------------------------------------------------------------------------------------\n","");
		return productDetails;
	}
}
