
public class Printer extends Product {
	private String series, resolution, printerType, duplexCapability;

	public Printer() {
		
	}
	
	public Printer(String productID, String productName, double price, String category, String type, int quantity, String series, String resolution, String printerType, String duplexCapability, String color) {
		super(productID, productName, price, category, type, quantity, color);
		setSeries(series);
		setResolution(resolution);
		setPrinterType(printerType);
		setDuplexCapability(duplexCapability);
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

	public String getPrinterType() {
		return printerType;
	}

	public void setPrinterType(String printerType) {
		this.printerType = printerType;
	}

	public String getDuplexCapability() {
		return duplexCapability;
	}

	public void setDuplexCapability(String duplexCapability) {
		this.duplexCapability = duplexCapability;
	}
	
	public String toString() {
		String productDetails="";
		productDetails+=String.format("%-10s----------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		productDetails+=String.format("%-10s|                                                                                                                                            |\n", "");
		productDetails+=String.format("%-10s|    %-15s%-17s%-23s%-15s%-23s%-22s%-10s%-10s |\n", "", "Product ID", "Product Name", "Series", "Resolution", "Printer Type", "Duplex Capability", "Color", "Price");
		productDetails+=String.format("%-10s|    %-15s%-17s%-23s%-15s%-23s%-22s%-10s%-10s |\n", "", "----------", "------------", "------", "----------", "------------", "-----------------", "-----", "-----");
		productDetails+=String.format("%-10s|    %-15s%-17s%-23s%s%-12s%-23s%-22s%-10s%-10.2f |\n", "", super.getProductID(), super.getProductName(), series, resolution, "dpi", printerType, duplexCapability, super.getColor(), super.getPrice());
		productDetails+=String.format("%-10s|                                                                                                                                            |\n", "");
		productDetails+=String.format("%-10s----------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		return productDetails;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Printer) {
			if(((Printer)o).getProductID().equals(super.getProductID())) {
				return true;
			}
			else{
				return false;
			}
		}
		else {
			return false;
		}
	}
}
