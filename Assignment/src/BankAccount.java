
public class BankAccount {
	private String accountNo;
	private Payment[] payment =new Payment[1000];
	private Refund[] refund=new Refund[1000];
	private int noOfPayments;
	private int noOfRefunds;
	private double revenue;
	
	public BankAccount() {
		accountNo="CNTACC001";
	}

	public String getAccountNo() {
		return accountNo;
	}

	public Payment[] getPayment() {
		return payment;
	}

	public void addPayment(Payment payment) {
		this.payment[noOfPayments] = payment;
		noOfPayments++;
		calcMoney();
	}
	
	public void addRefund(Refund refund) {
		this.refund[noOfRefunds] = refund;
		noOfRefunds++;
		calcMoney();
	}

	public void calcMoney() {
		revenue=0;
		for(int i=0; i<noOfPayments; i++) {
			revenue+=payment[i].getPaymentAmount();
		}
		
		for(int i=0; i<noOfRefunds; i++) {
			revenue-=refund[i].getRefundAmount();
		}
	}
	
	
	public double getRevenue() {
		return revenue;
	}


}
