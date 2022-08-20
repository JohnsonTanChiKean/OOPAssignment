
public class Payment {
	private Staff staff;
	private Member member;
	private String paymentID;
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
	
	public Payment(Cart cart) {
		this.cart=cart;
		subtotal=cart.getTotalPrice();
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Payment(double subtotal, String status, String paymentMethod) {
		this.subtotal=subtotal;
		this.status=status;
		if(status.equals("Completed")) {
			idNo=generateIDNo();
		}
	}
	
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public int generateIDNo() {
		int tempIDNo;
		tempIDNo=genIDNo;
		genIDNo++;
		return tempIDNo;
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
			if(member.getMembership().equals("Silver")) {
				this.discount=0.02;
			}
			else if(member.getMembership().equals("Gold")) {
				this.discount=0.03;
			}
			else if(member.getMembership().equals("Platinum")) {
				this.discount=0.05;
			}
			
		}
		else {
			this.discount=0.0;
		}
		
		calcPaymentAmount();
	}

	private void calcPaymentAmount() {
		paymentAmount=subtotal-(subtotal*discount);
	}
	
	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public double getReceivedAmount() {
		return receivedAmount;
	}

	public void setPaymentDetails(double receivedAmount, String paymentMethod, String status) {
		this.paymentMethod=paymentMethod;
		this.status=status;
		this.receivedAmount = receivedAmount;
		this.balance=receivedAmount-paymentAmount;
		this.paymentID="P";
		this.idNo=genIDNo;
		genIDNo++;
		
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

	public String toString() {
		String paymentDetails="";
		paymentDetails+=String.format("Payment ID  : "+paymentID+idNo+"\n");
		paymentDetails+=String.format("Payment Date: "+paymentDate.getFullDateTime()+"\n");
		paymentDetails+=String.format("\n");
		paymentDetails+=String.format(cart.toString()+"\n");
		if(member.getMemberID()!="null") {
			paymentDetails+=String.format("Member ID: "+member.getFullMemID()+"\n");
			paymentDetails+=String.format("Discount : %.0f%%\n", (discount*100));
		}
		paymentDetails+=String.format("Payment Amount(RM): %.2f\n", paymentAmount);
		paymentDetails+=String.format("Payment Method: "+paymentMethod+"\n");
		paymentDetails+=String.format("Received Amount(RM): %.2f\n", receivedAmount);
		if(paymentMethod.equals("Cash")) {
			paymentDetails+=String.format("Balance(RM): %.2f\n", balance);
		}
		
		
		
		
		return paymentDetails;
	}

	public double getBalance() {
		return balance;
	}

	public double getDiscount() {
		return discount;
	}
	
}
