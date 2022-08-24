
public class Refund {
	private String refundID="F";
	private int idNo;
	private Receipt receipt;
	private Product[] refundProduct=new Product[100];
	private double refundAmount;
	private String reason;
	private String status;
	private GetDate refundDate;
	private int noOfProducts;
	private int genRefundID=1001;
	
	public double getRefundAmount() {
		return refundAmount;
	}
	
	
	
}
