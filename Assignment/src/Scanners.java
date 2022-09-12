
public class Scanners extends Product {
	private String series, resolution;
	
	public Scanners() {
		
	}
	
	public Scanners(String productID, String productName, double price, String category, String type, int quantity, String series, String resolution, String color) {
		super(productID, productName, price, category, type, quantity, color);
		setSeries(series);
		setResolution(resolution);
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

	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-10s-----------------------------------------------------------------------------------------------\n", "");
		productDetails+=String.format("%-10s|                                                                                             |\n", "");
		productDetails+=String.format("%-10s|    %-15s%-17s%-21s%-15s%-10s%-10s |\n", "", "Product ID", "Product Name", "Series", "Resolution", "Color", "Price");
		productDetails+=String.format("%-10s|    %-15s%-17s%-21s%-15s%-10s%-10s |\n", "", "----------", "------------", "------", "----------", "-----", "-----");
		productDetails+=String.format("%-10s|    %-15s%-17s%-21s%s%-11s%-10s%-10.2f |\n", "", super.getProductID(), super.getProductName(), series, resolution, "dpi", super.getColor(), super.getPrice());
		productDetails+=String.format("%-10s|                                                                                             |\n", "");
		productDetails+=String.format("%-10s-----------------------------------------------------------------------------------------------\n", "");
		return productDetails;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Scanners) {
			if(((Scanners)o).getProductID().equals(super.getProductID())) {
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
