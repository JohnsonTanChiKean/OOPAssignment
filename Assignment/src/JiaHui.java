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
			productDetails(receiptList.get(index), refundList, refund, bankAcc);

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
	
	
	public static void productDetails(Receipt receipt, ArrayList<Refund> refundList, Refund refund, BankAccount bankAcc) {
		Scanner refundInput=new Scanner(System.in);
		int refundQty=0;
		double refundPrice=0;
		
		System.out.printf("%-10s-------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s|                                                                                   |\n", "");
		System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
		System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","----------", "------------", "------------------", "--------", "-----");
		
		for(int i=0; i<receipt.getPayment().getCart().getNoOfProducts(); i++) {
			refundQty=0;
			refundPrice=0;
			for(int j =0; j<refundList.size(); j++) {
				if((receipt.getFullRecID().equals(refundList.get(j).getReceipt().getFullRecID()))&&refundList.get(j).getStatus().equals("approved")) {
					for(int k=0; k<refundList.get(j).getNoOfProducts(); i++) {
						if(receipt.getPayment().getCart().getProduct()[i].getProductID().equals(refundList.get(j).getRefundProduct()[k].getProductID())) {
							refundQty+=refundList.get(j).getRefundProduct()[k].getQuantity();
							refundPrice+=refundList.get(j).getPricePerItem()[k];
						}
					}
				}
			}
			for(int j=0; j<refund.getNoOfProducts(); j++) {
				if(receipt.getPayment().getCart().getProduct()[i].getProductID().equals(refund.getRefundProduct()[j].getProductID())) {
					refundQty+=refund.getRefundProduct()[j].getQuantity();
					refundPrice+=refund.getPricePerItem()[j];
				}
			}
			
			System.out.printf("%-5s%-5d|    %-15s%-17s%-23.2f%-13d%-10.2f |\n", "", (i+1),receipt.getPayment().getCart().getProduct()[i].getProductID(), receipt.getPayment().getCart().getProduct()[i].getProductName(), receipt.getPayment().getCart().getProduct()[i].getPrice(),( receipt.getPayment().getCart().getProduct()[i].getQuantity() - refundQty ), (receipt.getPayment().getCart().getPricePerItem()[i] - refundPrice) );	
		}
		System.out.printf("%-10s|                                                                                   |\n", "");
		System.out.printf("%-10s-------------------------------------------------------------------------------------\n\n", "");
		
		//taken from SelectedProd
		System.out.println("Please enter your choice for refund: ");
		int rfOption = refundInput.nextInt();
		refundInput.nextLine();
		
		rfOption = rfOption -1;
		refundQty=0;
		refundPrice=0;
		System.out.println();
		System.out.printf("%-10s-------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s|                                                                                   |\n", "");
		System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
		System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","----------", "------------", "------------------", "--------", "-----");
		for(int i=0; i<refundList.size(); i++) {
			if((receipt.getFullRecID().equals(refundList.get(i).getReceipt().getFullRecID()))&&refundList.get(i).getStatus().equals("approved")) {
				for(int j=0; j<refundList.get(i).getNoOfProducts(); i++) {
					if(receipt.getPayment().getCart().getProduct()[rfOption].getProductID().equals(refundList.get(i).getRefundProduct()[j].getProductID())) {
						refundQty+=refundList.get(i).getRefundProduct()[j].getQuantity();
						refundPrice+=refundList.get(i).getPricePerItem()[j];
					}
				}
			}
		}
		
		for(int i=0; i<refund.getNoOfProducts(); i++) {
			if(receipt.getPayment().getCart().getProduct()[rfOption].getProductID().equals(refund.getRefundProduct()[i].getProductID())) {
				refundQty+=refund.getRefundProduct()[i].getQuantity();
				refundPrice+=refund.getPricePerItem()[i];
			}
		}
		System.out.printf("%-5s%-5d|    %-15s%-17s%-23.2f%-13d%-10.2f |\n", 
				rfOption + 1, 
				receipt.getPayment().getCart().getProduct()[rfOption].getProductID(), 
				receipt.getPayment().getCart().getProduct()[rfOption].getProductName(), 
				receipt.getPayment().getCart().getProduct()[rfOption].getPrice(), 
				(receipt.getPayment().getCart().getProduct()[rfOption].getQuantity()-refundQty), 
				(receipt.getPayment().getCart().getPricePerItem()[rfOption]-refundPrice));	
		System.out.printf("%-10s|                                                                                   |\n", "");
		System.out.printf("%-10s-------------------------------------------------------------------------------------\n\n", "");
		
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
			
			if((rQuantity <= (receipt.getPayment().getCart().getProduct()[rfOption].getQuantity()-refundQty))&&(rQuantity>0)) {
				
				validQty = 1;
				
				pricePerItem = rQuantity * receipt.getPayment().getCart().getProduct()[rfOption].getPrice();
				
				
				//need check output display
				System.out.println();
				System.out.printf("%-10s-------------------------------------------------------------------------------------\n", "");
				System.out.printf("%-10s|                                                                                   |\n", "");
				System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Total Price", "Discount", "Refund Price");
				System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","----------", "------------", "------------------", "--------", "-----------", "--------", "------------");
				
				System.out.printf("%-5s%-5d|    %-15s%-17s%-23.2f%-13d %.2f %.2f %-10.2f |\n", 
						rfOption + 1, 
						receipt.getPayment().getCart().getProduct()[rfOption].getProductID(), 
						receipt.getPayment().getCart().getProduct()[rfOption].getProductName(), 
						receipt.getPayment().getCart().getProduct()[rfOption].getPrice(), 
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
							prod.setProductID(receipt.getPayment().getCart().getProduct()[rfOption].getProductID());
							prod.setProductName(receipt.getPayment().getCart().getProduct()[rfOption].getProductName());
							prod.setCategory(receipt.getPayment().getCart().getProduct()[rfOption].getCategory());
							prod.setType(receipt.getPayment().getCart().getProduct()[rfOption].getType());
							prod.setPrice(receipt.getPayment().getCart().getProduct()[rfOption].getPrice());
							prod.setQuantity(rQuantity);
							prod.setColor(receipt.getPayment().getCart().getProduct()[rfOption].getColor());
							
							
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
					prod.setProductID(receipt.getPayment().getCart().getProduct()[rfOption].getProductID());
					prod.setProductName(receipt.getPayment().getCart().getProduct()[rfOption].getProductName());
					prod.setCategory(receipt.getPayment().getCart().getProduct()[rfOption].getCategory());
					prod.setType(receipt.getPayment().getCart().getProduct()[rfOption].getType());
					prod.setPrice(receipt.getPayment().getCart().getProduct()[rfOption].getPrice());
					prod.setQuantity(rQuantity);
					prod.setColor(receipt.getPayment().getCart().getProduct()[rfOption].getColor());
					
					refund.addRefund(prod, 
							pricePerItem,  
							refund.getStatus(), 
							refund.getReason());
				}
				
			}
			
			else {
				System.out.println(rQuantity + " had exceeded the available refund quantity " + (receipt.getPayment().getCart().getProduct()[rfOption].getQuantity()-refundQty) + ".");
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
