
public class Refrigerator extends Product {
	private double horsePower, capacity;
	private String color;
	
	public Refrigerator() {
		
	}
	
	public Refrigerator(String productID, String productName, double price, String category, String type, int quantity, double horsePower, double capacity, String color) {
		super(productID, productName, price, category, type, quantity);
		setHorsePower(horsePower);
		setCapacity(capacity);
		setColor(color);
	}
	
	public double getHorsePower() {
		return horsePower;
	}
	public void setHorsePower(double horsePower) {
		this.horsePower = horsePower;
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-15s%-17s%-19s%-13s%-16s%s\n", "Product ID", "Product Name", "Horse Power(W)", "Capacity", "Color", "Price");
		productDetails+=String.format("%-15s%-17s%-19s%-13s%-16s%s\n", "----------", "------------", "--------------", "--------", "-----", "-----");
		productDetails+=String.format("%-15s%-17s%-19.2f%-13.0f%-16s%.2f", super.getProductID(), super.getProductName(), horsePower, capacity, color, super.getPrice());
		return productDetails;
	}
	
	
}
