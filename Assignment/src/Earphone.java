
public class Earphone extends Product{
	private String generation;
	
	public Earphone() {
		
	}
	
	public Earphone(String productID, String productName, double price, String category, String type, int quantity, String generation, String color) {
		super(productID, productName, price, category, type, quantity, color);
		setGeneration(generation);
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
	}
	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-10s--------------------------------------------------------------------------------\n", "");
		productDetails+=String.format("%-10s|                                                                              |\n", "");
		productDetails+=String.format("%-10s|    %-15s%-17s%-19s%-13s%-9s |\n", "", "Product ID", "Product Name", "Generation", "Color", "Price");
		productDetails+=String.format("%-10s|    %-15s%-17s%-19s%-13s%-9s |\n", "", "----------", "------------", "----------", "-----", "-----");
		productDetails+=String.format("%-10s|    %-15s%-17s%-19s%-13s%-9.2f |\n", "", super.getProductID(), super.getProductName(), generation, super.getColor(), super.getPrice());
		productDetails+=String.format("%-10s|                                                                              |\n", "");
		productDetails+=String.format("%-10s--------------------------------------------------------------------------------\n", "");
		return productDetails;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Earphone) {
			if(((Earphone)o).getProductID().equals(super.getProductID())) {
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
