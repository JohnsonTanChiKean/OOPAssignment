
public class Cart {
	private Product[] product=new Product[100];
	private double[] pricePerItem=new double[100];
	private double totalPrice;
	private int noOfProducts;
	Cart() {
		
	}
	
	public void addProduct(Product product) {
		this.product[noOfProducts]=product;
		this.pricePerItem[noOfProducts]=product.getPrice()*product.getQuantity();
		totalPrice+=product.getPrice()*product.getQuantity();
		noOfProducts++;
	}
	
	public int getNoOfProducts() {
		return noOfProducts;
	}
	
	public Product[] getProduct() {
		return product;
	}
	
	public double[] getPricePerItem() {
		return pricePerItem;
	}
	
	public void removeItem(int index) {
		for(int i=index; i<noOfProducts; i++) {
			product[i]=product[i+1];
			pricePerItem[i]=pricePerItem[i+1];
		}
		noOfProducts--;
		recalcTotalPrice();
	}
	
	public void recalcTotalPrice() {
		totalPrice=0;
		for(int i=0; i<noOfProducts; i++) {
			totalPrice+=pricePerItem[i];
		}
	}
	
	public void modifyQuantity(int index, int qty) {
		product[index].setQuantity(qty);
		
		for(int i=0; i<noOfProducts; i++) {
			pricePerItem[i]=product[i].getPrice()*product[i].getQuantity();
		}
		
		recalcTotalPrice();
	}
	
	public String toString() {
		String cartDetails="";
		cartDetails+=String.format("%-15s%-17s%-23s%-10s%14s\n", "Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
		cartDetails+=String.format("%-15s%-17s%-23s%-10s%s\n", "----------", "------------", "------------------", "--------", "--------------");
		
		for(int i=0; i<noOfProducts; i++) {
			cartDetails+=String.format("%-15s%-17s%-23.2f%-8d%16.2f\n", getProduct()[i].getProductID(), getProduct()[i].getProductName(), getProduct()[i].getPrice(), getProduct()[i].getQuantity(), getPricePerItem()[i]);	
		}
		cartDetails+=String.format("-------------------------------------------------------------------------------\n");
		cartDetails+=String.format("%65s%14.2f", "Subtotal(RM): ", totalPrice);
		return cartDetails;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
}
