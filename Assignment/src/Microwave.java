
public class Microwave extends Product{
	private double capacity;
	private String microwaveType, color, size;
	
	public Microwave() {
		
	}
	
	public Microwave(String productID, String productName, double price, String category, String type, int quantity, String size, String microwaveType, double capacity, String color) {
		super(productID, productName, price, category, type, quantity);
		setSize(size);
		setCapacity(capacity);
		setMicrowaveType(microwaveType);
		setColor(color);
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	public String getMicrowaveType() {
		return microwaveType;
	}
	public void setMicrowaveType(String microwaveType) {
		this.microwaveType = microwaveType;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-10s-----------------------------------------------------------------------------------------------------------\n", "");
		productDetails+=String.format("%-10s|                                                                                                         |\n", "");
		productDetails+=String.format("%-10s|    %-15s%-17s%-15s%-21s%-13s%-10s%-9s |\n", "", "Product ID", "Product Name", "Size", "Microwave Type", "Capacity", "Color", "Price");
		productDetails+=String.format("%-10s|    %-15s%-17s%-15s%-21s%-13s%-10s%-9s |\n", "", "----------", "------------", "----", "--------------", "--------", "-----", "-----");
		productDetails+=String.format("%-10s|    %-15s%-17s%-15s%-21s%-13.2f%-10s%-9.2f |\n", "", super.getProductID(), super.getProductName(), size, microwaveType, capacity, color, super.getPrice());
		productDetails+=String.format("%-10s|                                                                                                         |\n", "");
		productDetails+=String.format("%-10s-----------------------------------------------------------------------------------------------------------\n", "");
		return productDetails;
	}
}
