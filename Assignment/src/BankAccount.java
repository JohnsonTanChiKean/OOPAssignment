
public class BankAccount {
	private String accountNo;
	private Payment[] payment =new Payment[1000];
	private int noOfPayments;
	private double moneyAmount;
	
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

	public void calcMoney() {
		moneyAmount=0;
		for(int i=0; i<noOfPayments; i++) {
			moneyAmount+=payment[i].getPaymentAmount();
		}
	}
	
	public double getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}
}
