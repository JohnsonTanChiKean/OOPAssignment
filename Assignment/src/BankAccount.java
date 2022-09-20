
public class BankAccount {
	private String accountNo;
	private Payment[] payment =new Payment[10000];
	private Refund[] refund=new Refund[10000];
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
		calcRevenue();
	}
	
	public void addRefund(Refund refund) {
		this.refund[noOfRefunds] = refund;
		noOfRefunds++;
		calcRevenue();
	}

	private void calcRevenue() {
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

	public String toString() {
		String bankAccountDetails="";
		bankAccountDetails+=String.format("%-10s--------------------------------------------------------------\n","");
		bankAccountDetails+=String.format("%-10s| Total Payment Amount | Total Refund Amount | Total Revenue |\n","");
		bankAccountDetails+=String.format("%-10s|----------------------+---------------------+---------------|\n","");
		bankAccountDetails+=String.format("%-10s|        %-5d         |         %-5d       |    %-10.2f |\n","", noOfPayments, noOfRefunds, revenue);
		bankAccountDetails+=String.format("%-10s--------------------------------------------------------------\n","");
		bankAccountDetails+=String.format("%-10s","");
		bankAccountDetails+=String.format("%-10s","");
		return bankAccountDetails;
	}
}
