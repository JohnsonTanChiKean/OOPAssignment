
public class Product {
	private String productID, productName, category, type, color;
	private double price;
	private int quantity;
	
	public Product() {
		
	}
	
	public Product(String productID, String productName, double price, String category, String type, int quantity, String color){
		setProductID(productID);
		setProductName(productName);
		setCategory(category);
		setType(type);
		setPrice(price);
		setQuantity(quantity);
		setColor(color);
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-10s-----------------------------------------------------------------------------------------\n", "");
		productDetails+=String.format("%-10s|                                                                                       |\n", "");
		productDetails+=String.format("%-10s|    %-15s%-17s%-23s%-10s%14s |\n", "", "Product ID", "Product Name", "Price Per Quantity", "Quantity", "");
		productDetails+=String.format("%-10s|    %-15s%-17s%-23s%-10s%s |\n", "", "----------", "------------", "------------------", "--------", "");
		productDetails+=String.format("%-10s|    %-15s%-17s%-23.2f%-8d%16.2f |\n", "", productID, productName, price, quantity, "");
		productDetails+=String.format("%-10s|                                                                                       |\n", "");
		productDetails+=String.format("%-10s-----------------------------------------------------------------------------------------\n", "");
		return productDetails;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Product) {
			if(((Product)o).productID.equals(productID)) {
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
