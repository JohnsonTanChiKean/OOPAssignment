
public class Earphone extends Product{
	private String generation, color;
	
	public Earphone() {
		
	}
	
	public Earphone(String productID, String productName, double price, String category, String type, int quantity, String generation, String color) {
		super(productID, productName, price, category, type, quantity);
		setGeneration(generation);
		setColor(color);
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-15s%-17s%-19s%-13s%s\n", "Product ID", "Product Name", "Generation", "Color", "Price");
		productDetails+=String.format("%-15s%-17s%-19s%-13s%s\n", "----------", "------------", "----------", "-----", "-----");
		productDetails+=String.format("%-15s%-17s%-19s%-13s%.2f", super.getProductID(), super.getProductName(), generation, color, super.getPrice());
		return productDetails;
	}
	
}
