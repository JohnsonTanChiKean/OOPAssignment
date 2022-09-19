
public class Payment {
	private Staff staff;
	private Member member;
	private String paymentID="P";
	private int idNo;
	private double subtotal;
	private Cart cart;
	private String paymentMethod;
	private String status;
	private double receivedAmount;
	private double balance;
	private double discount;
	private double paymentAmount;
	private GetDate paymentDate;
	private static int genIDNo=1001;
	

	public Payment() {
		
	}
	
	public Payment(Cart cart, String status) {
		this.cart=cart;
		this.status=status;
		idNo=genIDNo;
		genIDNo++;
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Member getMember() {
		return member;
	}

	public void memberDiscount(Member member) {
		this.member = member;
		if(member!=null) {
			if((member.getMembership().equals("Silver"))&&(member.getMbrStatus().equalsIgnoreCase("active"))) {
				this.discount=0.02;
			}
			else if(member.getMembership().equals("Gold")&&(member.getMbrStatus().equalsIgnoreCase("active"))) {
				this.discount=0.03;
			}
			else if(member.getMembership().equals("Platinum")&&(member.getMbrStatus().equalsIgnoreCase("active"))) {
				this.discount=0.05;
			}
			else {
				this.discount=0.0;
			}
		}
		else {
			this.discount=0.0;
		}
		
		calcPaymentAmount();
	}

	private void calcPaymentAmount() {
		subtotal=cart.getTotalPrice();
		paymentAmount=subtotal-(subtotal*discount);
	}
	
	public double getPaymentAmount() {
		return paymentAmount;
	}

	public double getReceivedAmount() {
		return receivedAmount;
	}

	public String getFullPaymentID() {
		return paymentID+idNo;
	}
	
	public void setPaymentDetails(double receivedAmount, String paymentMethod, String status) {
		this.paymentMethod=paymentMethod;
		this.status=status;
		this.receivedAmount = receivedAmount;
		this.balance=receivedAmount-paymentAmount;
		
		GetDate paymentDate=new GetDate();
		this.paymentDate=paymentDate;
	}

	public GetDate getPaymentDate() {
		return paymentDate;
	}

	public String getStatus() {
		return status;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public double getBalance() {
		return balance;
	}

	public double getDiscount() {
		return discount;
	}
	
	public String toString() {
		String paymentDetails="";
		paymentDetails+=String.format("%-10s-----------------------------------------------------------------------------------------\n", "");
		paymentDetails+=String.format("%-10s|                                                                                       |\n", "");
		paymentDetails+=String.format("%-10s| Payment ID         : %-64s |\n", "", getFullPaymentID());
		paymentDetails+=String.format("%-10s| Payment Date       : %-64s |\n", "", paymentDate.toString());
		paymentDetails+=String.format("%-10s|                                                                                       |\n", "");
		paymentDetails+=String.format("%-10s|---------------------------------------------------------------------------------------|\n", "");
		paymentDetails+=String.format("%-10s|                                                                                       |\n", "");
		paymentDetails+=String.format("%-10s|    %-15s%-17s%-23s%-10s%14s%-3s |\n", "", "Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price", "");
		paymentDetails+=String.format("%-10s|    %-15s%-17s%-23s%-10s%s%-3s |\n", "", "----------", "------------", "------------------", "--------", "--------------", "");
		
		for(int i=0; i<cart.getNoOfProducts(); i++) {
			paymentDetails+=String.format("%-10s|    %-15s%-17s%-23.2f%-8d%16.2f%-3s |\n", "", cart.getProduct()[i].getProductID(), cart.getProduct()[i].getProductName(), cart.getProduct()[i].getPrice(), cart.getProduct()[i].getQuantity(), cart.getPricePerItem()[i], "");	
		}
		paymentDetails+=String.format("%-10s|    -------------------------------------------------------------------------------    |\n", "");
		paymentDetails+=String.format("%-10s| %67s%15.2f%-3s |\n", "", "Subtotal(RM): ", cart.getTotalPrice(), "");
		paymentDetails+=String.format("%-10s|                                                                                       |\n", "");
		paymentDetails+=String.format("%-10s|---------------------------------------------------------------------------------------|\n", "");
		paymentDetails+=String.format("%-10s|                                                                                       |\n", "");
		if(member!=null) {
			paymentDetails+=String.format("%-10s| Member ID          : %-64s |\n", "", member.getFullMemID());
			paymentDetails+=String.format("%-10s| Discount           : %.0f%-63s |\n", "", (discount*100), "%");
		}
		paymentDetails+=String.format("%-10s| Payment Amount(RM) : %-64.2f |\n", "", paymentAmount);
		paymentDetails+=String.format("%-10s| Payment Method     : %-64s |\n", "", paymentMethod);
		paymentDetails+=String.format("%-10s| Received Amount(RM): %-64.2f |\n", "", receivedAmount);
		if(paymentMethod.equals("Cash")) {
			paymentDetails+=String.format("%-10s| Balance(RM)        : %-64.2f |\n", "", balance);
		}
		paymentDetails+=String.format("%-10s|                                                                                       |\n", "");
		paymentDetails+=String.format("%-10s-----------------------------------------------------------------------------------------\n", "");
		return paymentDetails;
	}

	public boolean equals(Object o) {
		if(o instanceof Payment) {
			if(((Payment)o).idNo==idNo) {
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
