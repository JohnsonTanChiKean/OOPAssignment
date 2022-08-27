
public class Product {
	private String productID, productName, category, type;
	private double price;
	private int quantity;
	
	public Product() {
		
	}
	
	public Product(String productID, String productName, double price, String category, String type, int quantity){
		setProductID(productID);
		setProductName(productName);
		setCategory(category);
		setType(type);
		setPrice(price);
		setQuantity(quantity);
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
}
