
public class Receipt {
	private String receiptID;
	private int idNo;
	private Payment payment;
	private GetDate receiptDate;
	private static int receiptCount;
	private static int genIdNo=1001;
	
	public Receipt() {
		
	}
	
	public Receipt(Payment payment) {
		this.payment=payment;
		this.receiptDate=payment.getPaymentDate();
		receiptID="R";
		idNo=genIdNo;
		genIdNo++;
		receiptCount++;
	}
	
	public String getFullRecID() {
		return receiptID+idNo;
	}
	
	public String toString() {
		String receiptDetails="";
		receiptDetails+=String.format("-----------------------------------------------------------------------------------\n");
		receiptDetails+=String.format("| Receipt ID: %-67s |\n", getFullRecID());
		receiptDetails+=String.format("| Receipt Date: %-65s |\n", receiptDate);
		receiptDetails+=String.format("| Handled By: %-68s|\n", payment.getStaff().getFullStaffID());
		receiptDetails+=String.format("| %-15s%-17s%-23s%-10s%14s |\n", "Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
		receiptDetails+=String.format("| %-15s%-17s%-23s%-10s%s |\n", "----------", "------------", "------------------", "--------", "--------------");
		for(int i=0; i<payment.getCart().getNoOfProducts(); i++) {
			receiptDetails+=String.format("| %-15s%-17s%-23.2f%-8d%16.2f |\n", payment.getCart().getProduct()[i].getProductID(), payment.getCart().getProduct()[i].getProductName(), payment.getCart().getProduct()[i].getPrice(), payment.getCart().getProduct()[i].getQuantity(), payment.getCart().getPricePerItem()[i]);
		}
		receiptDetails+=String.format("| ------------------------------------------------------------------------------- |\n");
		receiptDetails+=String.format("| %65s%14.2f |\n", "Subtotal(RM): ", payment.getCart().getTotalPrice());
		if(payment.getMember().getMemberID()!="null") {
			receiptDetails+=String.format("| Member ID: %-68s |\n", payment.getMember().getFullMemID());
			receiptDetails+=String.format("| Discount : %.0f%%%-66s |\n", (payment.getDiscount()*100), "");
		}
		receiptDetails+=String.format("| Payment Amount(RM): %-59.2f |\n", payment.getPaymentAmount());
		receiptDetails+=String.format("| Payment Method: %-63s |\n", payment.getPaymentMethod());
		receiptDetails+=String.format("| Received Amount(RM): %-58.2f |\n", payment.getReceivedAmount());
		if(payment.getPaymentMethod().equals("Cash")) {
			receiptDetails+=String.format("| Balance(RM): %-66.2f |\n", payment.getBalance());
		}
		receiptDetails+=String.format("-----------------------------------------------------------------------------------\n");
		return receiptDetails;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Receipt) {
			if(((Receipt)o).idNo==idNo) {
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
