
public class Refund {
	private String refundID = "F";
	private int idNo;
	private Receipt receipt;
	
	private Product[] refundProduct = new Product[100]; // store all status of refund
	private double[] pricePerItem = new double[100]; // quantity * price per unit
	private double[] rfDiscount = new double[100];
	private double[] aftDiscountP = new double[100];
	
	private double refundAmount;
	private double totalAmount;
	private double discount;
	private String reason;
	private String status = "requested";
	private GetDate refundDate;
	private int noOfProducts;
	
	private static int genRefundID=1001;
	
	public Refund() {
		
	}
	
	public Refund(Receipt receipt) {
		this.receipt=receipt;
		idNo = genRefundID;
		genRefundID++;
	}

	public void addRefund(Product refundProduct, double pricePerItem, String status, String reason) {
		refundDate = new GetDate();
		this.refundProduct[noOfProducts] = refundProduct;
		this.pricePerItem[noOfProducts] = pricePerItem;
		//this.rfDiscount[noOfProducts] = pricePerItem * receipt.getPayment().getDiscount();
		//this.aftDiscountP[noOfProducts] = pricePerItem - rfDiscount[noOfProducts];
		//this.rfStatus[noOfProducts]=status;
		this.status=status;
		//refundAmount = totalRefund;
		this.reason = reason;

		noOfProducts++;
		
		calTotalAmount();
		calDiscountGiven();
		calRefundAmount();
	}
	
	
	
	public void calTotalAmount() {
		
		totalAmount = 0;
		
		for(int i =0; i<noOfProducts; i++) {
			totalAmount += pricePerItem[i];
		}
	}
	
	
	public void calDiscountGiven() {
		for(int i=0; i<noOfProducts;i++) {
			rfDiscount[i] = pricePerItem[i] * receipt.getPayment().getDiscount();
			aftDiscountP[i] = pricePerItem[i] - rfDiscount[i];
		}
		discount = totalAmount * receipt.getPayment().getDiscount();
	}
	
	
	public void calRefundAmount() {
		refundAmount = totalAmount - discount;
	}
	

	
	public String getFullRefundID() {
		return refundID+idNo;
	}
	
	
	public void modifyQty(int index, int qty) {
		refundProduct[index].setQuantity(qty);
		
		for(int i=0; i < noOfProducts; i++) {
			pricePerItem[i] = refundProduct[i].getQuantity() * refundProduct[i].getPrice();
		}
		
		calTotalAmount();
		calDiscountGiven();
		calRefundAmount();
	}
	
	
	
	public String toString() {
		String refundDetails="";
		refundDetails+=String.format("%-10s-----------------------------------------------------------------------------------------\n", "");
		refundDetails+=String.format("%-10s|                                                                                       |\n", "");
		refundDetails+=String.format("%-10s|                         _______     __     __     _____________                       |\n", "");
		refundDetails+=String.format("%-10s|                        /  _____|   |  \\   |  |   |             |                      |\n", "");
		refundDetails+=String.format("%-10s|                       /  /         |   \\  |  |   |____     ____|                      |\n", "");
		refundDetails+=String.format("%-10s|                      /  /          |    \\ |  |        |   |                           |\n", "");
		refundDetails+=String.format("%-10s|                     /  /           |     \\|  |        |   |                           |\n", "");
		refundDetails+=String.format("%-10s|                     \\  \\           |  |\\     |        |   |                           |\n", "");
		refundDetails+=String.format("%-10s|                      \\  \\          |  | \\    |        |   |                           |\n", "");
		refundDetails+=String.format("%-10s|                       \\  \\_____    |  |  \\   |        |   |                           |\n", "");
		refundDetails+=String.format("%-10s|                        \\_______|   |__|   \\__|        |___|                           |\n", "");
		refundDetails+=String.format("%-10s|                                                                                       |\n", "");
		refundDetails+=String.format("%-10s|                                                                                       |\n", "");
		refundDetails+=String.format("%-10s| Refund ID         : %-64s  |\n", "", getFullRefundID());
		refundDetails+=String.format("%-10s| Refund Date       : %-64s  |\n", "", refundDate);
		
		if(receipt.getPayment().getMember() != null) {
			refundDetails+=String.format("%-10s| Member ID         : %-64s  |\n", "", receipt.getPayment().getMember().getFullMemID());
		}

		refundDetails+=String.format("%-10s| Reason            : %-64s  |\n", "", getReason());
		refundDetails+=String.format("%-10s| Status            : %-64s  |\n", "", getStatus().toUpperCase());
		refundDetails+=String.format("%-10s|                                                                                       |\n", "");
		//refundDetails+=String.format("%-10s|---------------------------------------------------------------------------------------|\n", "");
		refundDetails+=String.format("%-10s|_______________________________________________________________________________________|\n", "");
		refundDetails+=String.format("%-10s|                                                                                       |\n", "");
		refundDetails+=String.format("%-10s|    %-15s%-17s%-23s%-10s%14s%-3s |\n", "", "Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price", "");
		refundDetails+=String.format("%-10s|    %-15s%-17s%-23s%-10s%s%-3s |\n", "", "----------", "------------", "------------------", "--------", "--------------", "");
		
		for(int i=0; i<getNoOfProducts(); i++) {
			
			refundDetails+=String.format("%-10s|    %-15s%-17s%-23.2f%-8d%16.2f%-3s |\n", "", 
					refundProduct[i].getProductID(), 
					refundProduct[i].getProductName(),
					refundProduct[i].getPrice(), 
					refundProduct[i].getQuantity(), 
					pricePerItem[i], 
					"");	
			
		}
		
		//refundDetails+=String.format("%-10s|                                                                                       |\n", "");
		//refundDetails+=String.format("%-10s|---------------------------------------------------------------------------------------|\n", "");
		refundDetails+=String.format("%-10s|_______________________________________________________________________________________|\n", "");

		
		refundDetails+=String.format("%-10s| %67s%15.2f%-3s |\n", "", "Total Price(RM): ", totalAmount, "");
		refundDetails+=String.format("%-10s| %67s%15.2f%-3s |\n", "", "Discount Given(RM): ", discount, "");
		refundDetails+=String.format("%-10s|=======================================================================================|\n", "");
		refundDetails+=String.format("%-10s| %67s%15.2f%-3s |\n", "", "Subtotal(RM): ", refundAmount, "");
		refundDetails+=String.format("%-10s|=======================================================================================|\n", "");
		refundDetails+=String.format("%-10s|                                                                                       |\n", "");
		refundDetails+=String.format("%-10s-----------------------------------------------------------------------------------------\n", "");
		return refundDetails;
	}
	
	

	public Product[] getRefundProduct() {
		return refundProduct;
	}

	public void setRefundProduct(Product[] refundProduct) {
		this.refundProduct = refundProduct;
	}
	
	
	public double[] getPricePerItem() {
		return pricePerItem;
	}
	
	public void setPricePerItem(double[] pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	
	public double[] getRfDiscount() {
		return rfDiscount;
	}

	public void setRfDiscount(double[] rfDiscount) {
		this.rfDiscount = rfDiscount;
	}

	
	public double[] getAftDiscountP() {
		return aftDiscountP;
	}

	public void setAftDiscountP(double[] aftDiscountP) {
		this.aftDiscountP = aftDiscountP;
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


	public void setRefundID(String refundID) {
		this.refundID = refundID;
	}

	public String getRefundID() {
		return refundID;
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


	public GetDate getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(GetDate refundDate) {
		this.refundDate = refundDate;
	}
	
	
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
	

	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	
	public boolean equals(Object o) {
		if(o instanceof Refund) {
			return ((Refund)o).getFullRefundID().equals(getFullRefundID());
		}
		else {
			return false;
		}
	}
}
