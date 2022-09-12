
public class Refrigerator extends Product {
	private double horsePower, capacity;
	
	public Refrigerator() {
		
	}
	
	public Refrigerator(String productID, String productName, double price, String category, String type, int quantity, double horsePower, double capacity, String color) {
		super(productID, productName, price, category, type, quantity, color);
		setHorsePower(horsePower);
		setCapacity(capacity);
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
	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-10s-------------------------------------------------------------------------------------------------\n", "");
		productDetails+=String.format("%-10s|                                                                                               |\n", "");
		productDetails+=String.format("%-10s|    %-15s%-17s%-19s%-13s%-16s%-10s |\n", "", "Product ID", "Product Name", "Horse Power(W)", "Capacity", "Color", "Price");
		productDetails+=String.format("%-10s|    %-15s%-17s%-19s%-13s%-16s%-10s |\n", "", "----------", "------------", "--------------", "--------", "-----", "-----");
		productDetails+=String.format("%-10s|    %-15s%-17s%-19.2f%-13.0f%-16s%-10.2f |\n", "", super.getProductID(), super.getProductName(), horsePower, capacity, super.getColor(), super.getPrice());
		productDetails+=String.format("%-10s|                                                                                               |\n", "");
		productDetails+=String.format("%-10s-------------------------------------------------------------------------------------------------\n", "");
		return productDetails;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Refrigerator) {
			if(((Refrigerator)o).getProductID().equals(super.getProductID())) {
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
