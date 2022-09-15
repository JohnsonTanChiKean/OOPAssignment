import java.util.Scanner;
import java.util.ArrayList;

public class JiaHui {
	public static void processRefund(ArrayList<Receipt> receiptList, ArrayList<Product> productList, BankAccount bankAcc, ArrayList<Refund> refundList) {
		Scanner input = new Scanner(System.in);
		
		int index;
		index=findReceipt(receiptList);
		System.out.println(receiptList.get(index).toString());
		Refund refund = new Refund(receiptList.get(index));

		int rfOption;
		char otherProd;
		double pricePerItem;
		
		
		do {
			productDetails(receiptList.get(index).getPayment().getCart(),refund);
			rfOption = SelectedProd(productList,receiptList.get(index).getPayment().getCart());
			refundQuantity(receiptList.get(index).getPayment().getCart(),rfOption,receiptList.get(index).getPayment(),refund,bankAcc);


			do { 
				System.out.println("Do you want to refund another product? (y=yes)");
				otherProd = input.next().toUpperCase().charAt(0);
				input.nextLine();
			}while(otherProd == 'Y');
			
		}while(otherProd == 'Y');

	}
	
	public static int findReceipt(ArrayList<Receipt> receiptList) {
		Scanner input=new Scanner(System.in);
		String receiptID;
		int valid=0, found =0;
		int index=0;
		do {
			valid=0;
			found=0;
			System.out.print("Enter receipt ID > ");
			receiptID = input.next().toUpperCase(); //R1001
			
			//validation
			//check length & first char
			if(receiptID.length() == 5 && receiptID.charAt(0) == 'R') {
				for(int i=0; i<receiptList.size(); i++) {
					if(receiptID.equals(receiptList.get(i).getFullRecID()) ) {
						valid = 1;
						found=1;
						index=i;
					}
				}
			}
			if((valid==0)||(found==0)) {
				System.out.println("Invalid receipt ID ! Please try again.");
			}

		}while(valid == 0);
		
		return index;
	}
	
	
	public static void productDetails(Cart cart, Refund refund) {
		
		System.out.printf("%-10s-------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s|                                                                                   |\n", "");
		System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
		System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","----------", "------------", "------------------", "--------", "-----");
		
		for(int i=0; i<cart.getNoOfProducts(); i++) {
			for(int j =0; j<refund.getNoOfProducts(); j++) {
				if(cart.getProduct()[i].getProductID().equals(refund.getRefundProduct()[j].getProductID())) {
					System.out.printf("%-5s%-5d|    %-15s%-17s%-23.2f%-13d%-10.2f |\n", "", (i+1),cart.getProduct()[i].getProductID(), cart.getProduct()[i].getProductName(), cart.getProduct()[i].getPrice(),( cart.getProduct()[i].getQuantity() - refund.getRefundProduct()[j].getQuantity() ), (cart.getPricePerItem()[i] - refund.getPricePerItem()[j]) );	
					
				}
			}
		}
		
		System.out.printf("%-10s|                                                                                   |\n", "");
		System.out.printf("%-10s-------------------------------------------------------------------------------------\n\n", "");

		

	}
	
	
	
	public static int SelectedProd(ArrayList<Product> productList, Cart cart) {
		Scanner refundInput = new Scanner(System.in);
		
		ArrayList<Product> prodList = productList;
		
		System.out.println("Please enter your choice for refund: ");
		int rfOption = refundInput.nextInt();
		refundInput.nextLine();
		
		rfOption = rfOption -1;
		
		System.out.println();
		System.out.printf("%-10s-------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s|                                                                                   |\n", "");
		System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
		System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","----------", "------------", "------------------", "--------", "-----");
		
		System.out.printf("%-5s%-5d|    %-15s%-17s%-23.2f%-13d%-10.2f |\n", 
				rfOption + 1, 
				cart.getProduct()[rfOption].getProductID(), 
				cart.getProduct()[rfOption].getProductName(), 
				cart.getProduct()[rfOption].getPrice(), 
				cart.getProduct()[rfOption].getQuantity(), 
				cart.getPricePerItem()[rfOption]);	
		System.out.printf("%-10s|                                                                                   |\n", "");
		System.out.printf("%-10s-------------------------------------------------------------------------------------\n\n", "");
		return rfOption;
	}
	
	
	public static void refundQuantity(Cart cart, int rfOption, Payment payment, Refund refund, BankAccount bankAcc) {
		Scanner refundInput = new Scanner(System.in);
		
		double pricePerItem=0;
		double afterDiscount;
		int validQty = 0;
		int rQuantity;
		double discount;
		
		char confirm = 'N';
		Product prod = new Product();
		
		do {
			validQty = 0;
			
			System.out.println("\nPlease enter the QUANTITY of product(refund):");
			rQuantity = refundInput.nextInt();
			refundInput.nextLine();
			
			if(rQuantity <= cart.getProduct()[rfOption].getQuantity()) {
				
				validQty = 1;
				
				pricePerItem = rQuantity * cart.getProduct()[rfOption].getPrice();
				
				
				//need check output display
				System.out.println();
				System.out.printf("%-10s-------------------------------------------------------------------------------------\n", "");
				System.out.printf("%-10s|                                                                                   |\n", "");
				System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Total Price", "Discount", "Refund Price");
				System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","----------", "------------", "------------------", "--------", "-----------", "--------", "------------");
				
				System.out.printf("%-5s%-5d|    %-15s%-17s%-23.2f%-13d %.2f %.2f %-10.2f |\n", 
						rfOption + 1, 
						cart.getProduct()[rfOption].getProductID(), 
						cart.getProduct()[rfOption].getProductName(), 
						cart.getProduct()[rfOption].getPrice(), 
						rQuantity, 
						pricePerItem,
						refund.getDiscount(),
						refund.getRefundAmount());
							
				System.out.printf("%-10s|                                                                                   |\n", "");
				System.out.printf("%-10s-------------------------------------------------------------------------------------\n\n", "");
				
				rfReason(refund);
				verifyReason(refund);
				
				if(refund.getStatus() == "approved") {
					do {
						System.out.printf("Do you confirm to refund this product? (y=yes) ");
						
						confirm = refundInput.next().toUpperCase().charAt(0);
						refundInput.nextLine();
						
						if(confirm == 'Y') {
							prod.setProductID(cart.getProduct()[rfOption].getProductID());
							prod.setProductName(cart.getProduct()[rfOption].getProductName());
							prod.setCategory(cart.getProduct()[rfOption].getCategory());
							prod.setType(cart.getProduct()[rfOption].getType());
							prod.setPrice(cart.getProduct()[rfOption].getPrice());
							prod.setQuantity(cart.getProduct()[rfOption].getQuantity());
							prod.setColor(cart.getProduct()[rfOption].getColor());
							
							
							refund.addRefund(prod, 
									pricePerItem, 
									refund.getStatus(), 
									refund.getReason());
							bankAcc.addRefund(refund);
						}
						
						else if(confirm != 'N' && confirm !='Y'){
							System.out.println("Invalid input. Please try again.");

						}
						
					}while(confirm != 'N' && confirm !='Y');
					
					
				}
				else if(refund.getStatus() == "rejected") {
					prod.setProductID(cart.getProduct()[rfOption].getProductID());
					prod.setProductName(cart.getProduct()[rfOption].getProductName());
					prod.setCategory(cart.getProduct()[rfOption].getCategory());
					prod.setType(cart.getProduct()[rfOption].getType());
					prod.setPrice(cart.getProduct()[rfOption].getPrice());
					prod.setQuantity(rQuantity);
					prod.setColor(cart.getProduct()[rfOption].getColor());
					
					refund.addRefund(prod, 
							pricePerItem,  
							refund.getStatus(), 
							refund.getReason());
				}
				
			}
			
			else {
				System.out.println(rQuantity + " had exceeded the quantity purchased " + cart.getProduct()[rfOption].getQuantity() + ".");
				System.out.println("Please reenter the quantity.");
				System.out.println();
			}
		}while(validQty == 0);
	}
	
	
	public static void rfReason(Refund refund) {
		Scanner refundInput = new Scanner(System.in);
		
		//enter & verify reason 
		System.out.printf("%-10s-----------------------------------\n", "");
		System.out.printf("%-10s|          Refund Reason          |\n", "");
		System.out.printf("%-10s| =============================== |\n", "");
		System.out.printf("%-10s|                                 |\n", "");
		System.out.printf("%-10s|    1. Broken / Damage           |\n", "");
		System.out.printf("%-10s|    2. Not function properly     |\n", "");
		System.out.printf("%-10s|    3. Buy too much              |\n", "");
		System.out.printf("%-10s|    0. Others                    |\n", "");
		System.out.printf("%-10s|                                 |\n", "");
		System.out.printf("%-10s-----------------------------------\n", ""); 
		
		int reasonOption;
		
		
		do {
			System.out.println("Please select the refund reason: ");
			reasonOption = refundInput.nextInt();
			refundInput.nextLine();
			
			switch(reasonOption) {
			case 1:
				refund.setStatus("approved");
				refund.setReason("Broken / Damage");
				break;
			case 2:
				refund.setStatus("approved");
				refund.setReason("Not function properly");
				break;
			case 3:
				refund.setStatus("approved");
				refund.setReason("Buy too much");
				break;
			case 0:
				//go to verifyReason();
				verifyReason(refund);

				break;				

				
			default:
				System.out.println("Invalid input, please enter again.");
				break;
			
			}
		}while( reasonOption <0 || reasonOption >3);
		

	}
	
	
	public static void verifyReason(Refund refund) {
		Scanner refundInput = new Scanner(System.in);
		String reason;

		System.out.println();
		System.out.println("Please enter an valid refund reason : ");				
		reason = refundInput.nextLine();
		refundInput.nextLine();
		
		if( (reason.indexOf("meet") >=0 && reason.indexOf("expectation")  >= 0 )
				|| (reason.indexOf("wrong") >=0 && reason.indexOf("product")  >= 0 )
				|| (reason.indexOf("wrong") >=0 && reason.indexOf("size")  >= 0 )
				|| (reason.indexOf("wrong") >=0 && reason.indexOf("quantity")  >= 0 ) ){
				
			refund.setStatus("approved");
			refund.setReason(reason);
		}
		
		else if(reason.indexOf("wardrobing")  >=0 ) {
			System.out.println("This is not a valid refund reason.");
			refund.setStatus("rejected");
		}
		
		else {
			System.out.println("This is not a valid refund reason.");
			refund.setStatus("rejected");
		}
	}
	
	
	
	
	
	//report
	public static void rfReportMenu() {
		Scanner refundInput = new Scanner(System.in);
		int reportOption;
		
		System.out.println();
		System.out.printf("%-10s------------------------------------\n", "");
		System.out.printf("%-10s|        Refund Report Menu        |\n", "");
		System.out.printf("%-10s| ================================ |\n", "");
		System.out.printf("%-10s|                                  |\n", "");
		System.out.printf("%-10s|    1. Refund Reason              |\n", "");
		System.out.printf("%-10s|    2. Total lost                 |\n", "");
		System.out.printf("%-10s|    3. Refund Product quantity    |\n", "");
		System.out.printf("%-10s|    0. Exit                     |\n", "");
		System.out.printf("%-10s|                                  |\n", "");
		System.out.printf("%-10s------------------------------------\n", ""); 
		
		reportOption = refundInput.nextInt();
		refundInput.nextLine();
		
		switch(reportOption) {
		case 1:
			
			break;
		case 2:
				
			break;
		case 3:
			
			break;
		case 0:
			
			break;
		default:
			break;
		}
	}
	
	
	
	public static void totalLost(Refund refund) {
		System.out.println("  Total Lost for All Refunded Products.");
		
		//add system date;
		
		System.out.printf("%-10s--------------------------------------\n", "");
		System.out.printf("%-10s|  Refund ID  |  Product Name      |  Quantity   |  Status     |  Refund Price(RM)  |\n", "");
		for(int i=0; i<refund.getNoOfProducts(); i++) {
			System.out.printf("%-10s| %s  %s  %d %s &.2f", 
					refund.getRefundID(), 
					refund.getRefundProduct()[i].getProductName(), 
					refund.getRefundProduct()[i].getQuantity(),
					refund.getStatus(),
					refund.getPricePerItem()[i]
					);
		}

	}
	
	
}
