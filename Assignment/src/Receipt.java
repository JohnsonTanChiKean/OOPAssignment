
public class Receipt {
	private String receiptID="R";
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
		idNo=genIdNo;
		genIdNo++;
		receiptCount++;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public String getFullRecID() {
		return receiptID+idNo;
	}
	
	public String toString() {
		String receiptDetails="";
		receiptDetails+=String.format("%-10s-----------------------------------------------------------------------------------------\n", "");
		receiptDetails+=String.format("%-10s|                                                                                       |\n", "");
		receiptDetails+=String.format("%-10s|                         _______     __     __     _____________                       |\n", "");
		receiptDetails+=String.format("%-10s|                        /  _____|   |  \\   |  |   |             |                      |\n", "");
		receiptDetails+=String.format("%-10s|                       /  /         |   \\  |  |   |____     ____|                      |\n", "");
		receiptDetails+=String.format("%-10s|                      /  /          |    \\ |  |        |   |                           |\n", "");
		receiptDetails+=String.format("%-10s|                     /  /           |     \\|  |        |   |                           |\n", "");
		receiptDetails+=String.format("%-10s|                     \\  \\           |  |\\     |        |   |                           |\n", "");
		receiptDetails+=String.format("%-10s|                      \\  \\          |  | \\    |        |   |                           |\n", "");
		receiptDetails+=String.format("%-10s|                       \\  \\_____    |  |  \\   |        |   |                           |\n", "");
		receiptDetails+=String.format("%-10s|                        \\_______|   |__|   \\__|        |___|                           |\n", "");
		receiptDetails+=String.format("%-10s|                                                                                       |\n", "");
		receiptDetails+=String.format("%-10s|                                                                                       |\n", "");
		receiptDetails+=String.format("%-10s| Receipt ID         : %-64s |\n", "", getFullRecID());
		receiptDetails+=String.format("%-10s| Receipt Date       : %-64s |\n", "", receiptDate);
		receiptDetails+=String.format("%-10s| Handled By         : %-64s |\n", "", payment.getStaff().getFullStaffID());
		receiptDetails+=String.format("%-10s|                                                                                       |\n", "");
		receiptDetails+=String.format("%-10s|---------------------------------------------------------------------------------------|\n", "");
		receiptDetails+=String.format("%-10s|                                                                                       |\n", "");
		receiptDetails+=String.format("%-10s|    %-15s%-17s%-23s%-10s%14s%-3s |\n", "", "Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price", "");
		receiptDetails+=String.format("%-10s|    %-15s%-17s%-23s%-10s%s%-3s |\n", "", "----------", "------------", "------------------", "--------", "--------------","");
		for(int i=0; i<payment.getCart().getNoOfProducts(); i++) {
			receiptDetails+=String.format("%-10s|    %-15s%-17s%-23.2f%-8d%16.2f%-3s |\n", "", payment.getCart().getProduct()[i].getProductID(), payment.getCart().getProduct()[i].getProductName(), payment.getCart().getProduct()[i].getPrice(), payment.getCart().getProduct()[i].getQuantity(), payment.getCart().getPricePerItem()[i], "");
		}
		receiptDetails+=String.format("%-10s|    -------------------------------------------------------------------------------    |\n", "");
		receiptDetails+=String.format("%-10s| %67s%15.2f%-3s |\n", "", "Subtotal(RM): ", payment.getCart().getTotalPrice(), "");
		receiptDetails+=String.format("%-10s|                                                                                       |\n", "");
		receiptDetails+=String.format("%-10s|---------------------------------------------------------------------------------------|\n", "");
		receiptDetails+=String.format("%-10s|                                                                                       |\n", "");
		if(payment.getMember()!=null) {
			receiptDetails+=String.format("%-10s| Member ID          : %-64s |\n", "", payment.getMember().getFullMemID());
			receiptDetails+=String.format("%-10s| Discount           : %.0f%-63s |\n", "", (payment.getDiscount()*100), "%");
		}
		receiptDetails+=String.format("%-10s| Payment Amount(RM) : %-64.2f |\n", "", payment.getPaymentAmount());
		receiptDetails+=String.format("%-10s| Payment Method     : %-64s |\n", "", payment.getPaymentMethod());
		receiptDetails+=String.format("%-10s| Received Amount(RM): %-64.2f |\n", "", payment.getReceivedAmount());
		if(payment.getPaymentMethod().equals("Cash")) {
			receiptDetails+=String.format("%-10s| Balance(RM)        : %-64.2f |\n", "", payment.getBalance());
		}
		receiptDetails+=String.format("%-10s|                                                                                       |\n", "");
		receiptDetails+=String.format("%-10s-----------------------------------------------------------------------------------------\n", "");
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
