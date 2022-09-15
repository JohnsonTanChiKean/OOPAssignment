
public class Refund {
	private String refundID="F";
	private int idNo;
	private Receipt receipt;
	
	private Product[] refundProduct = new Product[100]; // store all status of refund
	//private Product[] requestedRefund = new Product[100]; // store only requested
	//private String[] rfStatus = new String[100];
	private double[] pricePerItem = new double[100];
	//private double[] totalRefund = new double[100];
	private String[] rfReason = new String[100];
	
	private double refundAmount;
	private double totalAmount;
	private double discount;
	private String reason;
	private String status = "requested";
	private GetDate refundDate;
	private int noOfProducts;
	private int genRefundID=1001;
	
	public Refund() {
		
	}
	
	public Refund(Receipt receipt) {
		this.receipt=receipt;
	}

	public void addRefund(Product refundProduct, double pricePerItem, String status, String rfReason) {
		this.refundProduct[noOfProducts]=refundProduct;
		this.pricePerItem[noOfProducts]=pricePerItem;
		//this.rfStatus[noOfProducts]=status;
		this.status=status;
		//refundAmount = totalRefund;
		this.rfReason[noOfProducts] = rfReason;
		noOfProducts++;
	}
	

	
	public void calTotalAmount() {
		totalAmount = 0;
		for(int i =0; i<noOfProducts; i++) {
			totalAmount += pricePerItem[i];
		}
	}
	
	public void calDiscountGiven() {
		discount = totalAmount * receipt.getPayment().getDiscount();
	}
	
	
	
	public void calRefundAmount() {
		refundAmount = totalAmount - discount;
	}
	

	
	public String getFullRefundID() {
		return refundID+idNo;
	}
	
	
	public String toString(Payment payment) {
		String refundDetails="";
		refundDetails+=String.format("%-10s-----------------------------------------------------------------------------------------\n", "");
		refundDetails+=String.format("%-10s|                                                                                       |\n", "");
		refundDetails+=String.format("%-10s| Refund ID         : %-64s |\n", "", getFullRefundID());
		refundDetails+=String.format("%-10s| Payment Date       : %-64s |\n", "", refundDate.toString());
		refundDetails+=String.format("%-10s| Status       : %-64s |\n", "", getStatus());
		refundDetails+=String.format("%-10s|                                                                                       |\n", "");
		refundDetails+=String.format("%-10s|---------------------------------------------------------------------------------------|\n", "");
		refundDetails+=String.format("%-10s|                                                                                       |\n", "");
		refundDetails+=String.format("%-10s|    %-15s%-17s%-23s%-10s%14s %s  %-3s |\n", "", "Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price", "Reason",  "");
		refundDetails+=String.format("%-10s|    %-15s%-17s%-23s%-10s%s %s  %-3s |\n", "", "----------", "------------", "------------------", "--------", "--------------", "--------------------------------", "");
		for(int i=0; i<getNoOfProducts(); i++) {
			refundDetails+=String.format("%-10s|    %-15s%-17s%-23.2f%-8d%16.2f%-3s |\n", "", 
					refundProduct[i].getProductID(), 
					refundProduct[i].getProductName(),
					refundProduct[i].getPrice(), 
					refundProduct[i].getQuantity(), 
					pricePerItem[i], 
					rfReason[i],
					"");	
			
		}
		refundDetails+=String.format("%-10s|---------------------------------------------------------------------------------------|\n", "");
		
		refundDetails+=String.format("%-10s| %67s%15.2f%-3s |\n", "", "Total Price(RM): ", totalAmount, "");
		refundDetails+=String.format("%-10s| %67s%15.2f%-3s |\n", "", "Discount Given(RM): ", discount, "");
		refundDetails+=String.format("%-10s| %67s%15.2f%-3s |\n", "", "Subtotal(RM): ", refundAmount, "");
		
		refundDetails+=String.format("%-10s|                                                                                       |\n", "");
		refundDetails+=String.format("%-10s|---------------------------------------------------------------------------------------|\n", "");
		return refundDetails;
	}
	
	
	
	/*
	public Product[] getRequestedRefund() {
		return requestedRefund;
	}

	public void setRequestedRefund(Product[] requestedRefund) {
		this.requestedRefund = requestedRefund;
	}

	public String[] getRfStatus() {
		return rfStatus;
	}

	public void setRfStatus(String[] rfStatus) {
		this.rfStatus = rfStatus;
	}

	public double[] getTotalRefund() {
		return totalRefund;
	}

	public void setTotalRefund(double[] totalRefund) {
		this.totalRefund = totalRefund;
	}*/

	public String[] getRfReason() {
		return rfReason;
	}

	public void setRfReason(String[] rfReason) {
		this.rfReason = rfReason;
	}

	public void setPricePerItem(double[] pricePerItem) {
		this.pricePerItem = pricePerItem;
	}


	
	
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getRefundID() {
		return refundID;
	}

	public void setRefundID(String refundID) {
		this.refundID = refundID;
	}

	public int getIdNo() {
		return idNo;
	}

	public void setIdNo(int idNo) {
		this.idNo = idNo;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Product[] getRefundProduct() {
		return refundProduct;
	}

	public void setRefundProduct(Product[] refundProduct) {
		this.refundProduct = refundProduct;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public GetDate getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(GetDate refundDate) {
		this.refundDate = refundDate;
	}

	public int getNoOfProducts() {
		return noOfProducts;
	}

	public void setNoOfProducts(int noOfProducts) {
		this.noOfProducts = noOfProducts;
	}

	public int getGenRefundID() {
		return genRefundID;
	}

	public void setGenRefundID(int genRefundID) {
		this.genRefundID = genRefundID;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}


	
	public double getRefundAmount() {
		return refundAmount;
	}
	
	
	public double[] getPricePerItem() {
		return pricePerItem;
	}
	
	
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	

}
