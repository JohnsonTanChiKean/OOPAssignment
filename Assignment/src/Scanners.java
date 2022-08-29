
public class Scanners extends Product {
	private String series, resolution, color;
	
	public Scanners() {
		
	}
	
	public Scanners(String productID, String productName, double price, String category, String type, int quantity, String series, String resolution, String color) {
		super(productID, productName, price, category, type, quantity);
		setSeries(series);
		setResolution(resolution);
		setColor(color);
	}
	
	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-10s-----------------------------------------------------------------------------------------------\n", "");
		productDetails+=String.format("%-10s|                                                                                             |\n", "");
		productDetails+=String.format("%-10s|    %-15s%-17s%-21s%-15s%-10s%-10s |\n", "", "Product ID", "Product Name", "Series", "Resolution", "Color", "Price");
		productDetails+=String.format("%-10s|    %-15s%-17s%-21s%-15s%-10s%-10s |\n", "", "----------", "------------", "------", "----------", "-----", "-----");
		productDetails+=String.format("%-10s|    %-15s%-17s%-21s%s%-11s%-10s%-10.2f |\n", "", super.getProductID(), super.getProductName(), series, resolution, "dpi", color, super.getPrice());
		productDetails+=String.format("%-10s|                                                                                             |\n", "");
		productDetails+=String.format("%-10s-----------------------------------------------------------------------------------------------\n", "");
		return productDetails;
	}
}
