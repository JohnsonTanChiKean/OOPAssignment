import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class JiaHui {
	public static void refundMenu(ArrayList<Receipt> receiptList, ArrayList<Product> productList, BankAccount bankAcc, ArrayList<Refund> refundList, ArrayList<Payment> paymentList) {
		Scanner input = new Scanner(System.in);
		
		
		for(int i=0;i<receiptList.size();i++) {
			System.out.println(receiptList.get(i));
		}
		
		int menuOption = 0;

		int loop = 0;
		
		do {
			
			loop = 0;
			
			System.out.println();
			System.out.printf("%-10s--------------------------------------\n","");
			System.out.printf("%-10s|            Refund Menu             |\n","");
			System.out.printf("%-10s--------------------------------------\n","");
			System.out.printf("%-10s|                                    |\n","");
			System.out.printf("%-10s|   1. Request For Refund            |\n","");
			System.out.printf("%-10s|   2. Search Refund Record          |\n","");
			System.out.printf("%-10s|   0. Exit                          |\n","");
			System.out.printf("%-10s|                                    |\n","");
			System.out.printf("%-10s--------------------------------------\n","");
			
			System.out.print("Please enter your choice: ");
			
			try {
				menuOption = input.nextInt();
				input.nextLine();
			}
			
			catch(InputMismatchException e){
				loop=1;
				input.nextLine();
			}
			
			switch(menuOption) {
			case 1:
				processRefund(receiptList, productList, bankAcc, refundList);
				loop = 1;
				break;
				
			case 2:
				searchMenu(refundList, productList);
				loop = 1;
				break;
				
			case 0:
				System.out.println("You have quit.");
				System.out.println();
				break;
				
			default:
				System.out.println("Invalid choice! Please try again.");
				loop = 1;
				break;
			}
			
		}while(loop == 1);
		
		
	}
	
	
	public static void processRefund(ArrayList<Receipt> receiptList, ArrayList<Product> productList, BankAccount bankAcc, ArrayList<Refund> refundList) {
		Scanner input = new Scanner(System.in);
		
		int index;
		char otherProd = 'N';
		char otherReceipt = 'N';
		int refundQty = 0;
		double price = 0;
		double afterDiscount = 0;
		
		GetDate date = new GetDate();
		
		do {
			
			otherReceipt = 'N';
			
			index=findReceipt(receiptList);
			System.out.println(receiptList.get(index).toString());
			Refund refund = new Refund(receiptList.get(index));


			productDetails(receiptList.get(index), refundList, refund, bankAcc, productList);
				
					
			do {
				System.out.println();
				System.out.print("Do you want to make another refund for different receipt? (y=yes) ");
				otherReceipt = input.next().toUpperCase().charAt(0);
				input.nextLine();
						
				if(otherReceipt != 'Y' && otherReceipt != 'N') {
					System.out.println("Invalid receipt ID ! Please try again.");
				}
				
			}while(otherReceipt != 'Y' && otherReceipt != 'N');
			
		}while(otherReceipt == 'Y');

	}
	
	
	public static int findReceipt(ArrayList<Receipt> receiptList) {
		Scanner input = new Scanner(System.in);
		
		String receiptID;
		int valid = 0, found = 0;
		int index = 0;
		
		do {
			valid = 0;
			found = 0;
			
			System.out.println();
			System.out.print("Enter receipt ID > ");

			receiptID = input.next().toUpperCase(); //R1001
			
			//validation
			//check length & first char
			if( receiptID.length() == 5 && receiptID.charAt(0) == 'R' ) {
				for(int i=0; i<receiptList.size(); i++) {
					if( receiptID.equals( receiptList.get(i).getFullRecID() ) ) {
						valid = 1;
						found = 1;
						index = i;
					}
				}
			}
			
			if((valid == 0) || (found == 0)) {
				System.out.println("Invalid receipt ID ! Please try again.");
			}

		}while(valid == 0);
		
		return index;
	}
	
	
	public static void productDetails(Receipt receipt, ArrayList<Refund> refundList, Refund refund, BankAccount bankAcc, ArrayList<Product> productList) {
		Scanner refundInput = new Scanner(System.in);
		
		char otherProd = 'N';
		
		int refundQty = 0;
		double price = 0;
		int totalCount = 0;
		
		//selected prod
		int rfOption = 0;
		int inValidInput = 0;
		
		//enter qty
		double pricePerItem = 0;
		double afterDiscount = 0;
		int validQty = 0;
		int rQuantity = 0;
		double discount = 0;
		int found = 0;
		
		char confirm = 'N';
		Product prod=null;
		
		do {
			totalCount=0;
			rQuantity=0;
			otherProd = 'N';
			
			System.out.println();
			System.out.printf("%-10s------------------------------------------------------------------------------------------\n", "");
			System.out.printf("%-10s|                                                                                        |\n", "");
			System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-12s    |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price      ");
			System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-12s    |\n", "","----------", "------------", "------------------", "--------", "-----------");
			
			for(int i=0; i<receipt.getPayment().getCart().getNoOfProducts(); i++) {
				
				refundQty = 0;
				price = 0;
				
				for(int j =0; j<refundList.size(); j++) {
					
					if((receipt.getFullRecID().equals(refundList.get(j).getReceipt().getFullRecID()))&&refundList.get(j).getStatus().equals("approved")) {
						
						for(int k=0; k<refundList.get(j).getNoOfProducts(); k++) {
							
							if(receipt.getPayment().getCart().getProduct()[i].getProductID().equals(refundList.get(j).getRefundProduct()[k].getProductID())) {
								
								refundQty += refundList.get(j).getRefundProduct()[k].getQuantity();
								price += refundList.get(j).getPricePerItem()[k];
							}
						}
					}
				}
				
				
				for(int j=0; j<refund.getNoOfProducts(); j++) {
					if(receipt.getPayment().getCart().getProduct()[i].getProductID().equals(refund.getRefundProduct()[j].getProductID())) {
						
						refundQty += refund.getRefundProduct()[j].getQuantity();
						price += refund.getPricePerItem()[j];
					}
				}
				
				
				System.out.printf("%-5s%-5d|    %-15s%-17s%-23.2f%-13d%-12.2f    |\n", 
						"", 
						(i+1),
						receipt.getPayment().getCart().getProduct()[i].getProductID(), 
						receipt.getPayment().getCart().getProduct()[i].getProductName(), 
						receipt.getPayment().getCart().getProduct()[i].getPrice(), 
						( receipt.getPayment().getCart().getProduct()[i].getQuantity() - refundQty ), 
						( receipt.getPayment().getCart().getPricePerItem()[i] - price) );	
				
				totalCount += (receipt.getPayment().getCart().getProduct()[i].getQuantity() - refundQty);
			}
			
			
			System.out.printf("%-10s|                                                                                        |\n", "");
			System.out.printf("%-10s------------------------------------------------------------------------------------------\n", "");
			
			
			if(totalCount != 0) {
				do {
					inValidInput = 0;
					refundQty = 0;
					price = 0;
					System.out.println();
					System.out.printf("Please enter your choice for refund: ");
					
					try {
						rfOption = refundInput.nextInt();
						refundInput.nextLine();
					}


					catch(InputMismatchException e){
						inValidInput = 1;
						refundInput.nextLine();
					}
					
					if(rfOption > receipt.getPayment().getCart().getNoOfProducts() || rfOption < 0 || inValidInput == 1) {
						System.out.println("  Invalid Choice. Press Enter to try again");
						refundInput.nextLine();
						inValidInput = 1;
					}
					
					else {
						
						rfOption = rfOption - 1;
						
						
						System.out.println();
						System.out.printf("%-10s------------------------------------------------------------------------------------------\n", "");
						System.out.printf("%-10s|                                                                                        |\n", "");
						System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-12s    |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price      ");
						System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-12s    |\n", "","----------", "------------", "------------------", "--------", "-----------");
						
						for(int i=0; i<refundList.size(); i++) {
							
							if((receipt.getFullRecID().equals(refundList.get(i).getReceipt().getFullRecID()))&&refundList.get(i).getStatus().equals("approved")) {
								
								for(int j=0; j<refundList.get(i).getNoOfProducts(); j++) {
									
									if(receipt.getPayment().getCart().getProduct()[rfOption].getProductID().equals(refundList.get(i).getRefundProduct()[j].getProductID())) {
										refundQty += refundList.get(i).getRefundProduct()[j].getQuantity();
										price += refundList.get(i).getPricePerItem()[j];
									}
								}
							}
						}
						
						for(int i=0; i<refund.getNoOfProducts(); i++) {
							if(receipt.getPayment().getCart().getProduct()[rfOption].getProductID().equals(refund.getRefundProduct()[i].getProductID())) {
								
								refundQty += refund.getRefundProduct()[i].getQuantity();
								price += refund.getPricePerItem()[i];
							}
						}
						
						System.out.printf("%-5s%-5d|    %-15s%-17s%-23.2f%-13d%-12.2f    |\n", 
								"",
								rfOption + 1, 
								receipt.getPayment().getCart().getProduct()[rfOption].getProductID(), 
								receipt.getPayment().getCart().getProduct()[rfOption].getProductName(), 
								receipt.getPayment().getCart().getProduct()[rfOption].getPrice(), 
								(receipt.getPayment().getCart().getProduct()[rfOption].getQuantity() - refundQty), 
								(receipt.getPayment().getCart().getPricePerItem()[rfOption] - price));	
						
						System.out.printf("%-10s|                                                                                        |\n", "");
						System.out.printf("%-10s------------------------------------------------------------------------------------------\n", "");
					}
					
					
				}while(inValidInput == 1);
				
				
				
				do {
					validQty = 0;
					found=0;
					rQuantity=0;
					System.out.println();
					System.out.printf("Please enter the QUANTITY of product(refund): ");
					
					try {
						rQuantity = refundInput.nextInt();
						refundInput.nextLine();
					}
					
					catch(InputMismatchException e){
						validQty = 0;
						refundInput.nextLine();
					}
					
					
					
					if( (rQuantity <= (receipt.getPayment().getCart().getProduct()[rfOption].getQuantity() - refundQty) ) && (rQuantity > 0) ) {
						
						validQty = 1;
						
						pricePerItem = rQuantity * receipt.getPayment().getCart().getProduct()[rfOption].getPrice();
						discount = pricePerItem * receipt.getPayment().getDiscount();
						afterDiscount = pricePerItem - discount;
						
						
						//need check output display
						System.out.println();
						System.out.printf("%-10s-----------------------------------------------------------------------------------------------------------------------\n", "");
						System.out.printf("%-10s|                                                                                                                     |\n", "");
						System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-16s%-13s%-12s    |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Total Price", "Discount", "Refund Price");
						System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-16s%-13s%-12s    |\n", "","----------", "------------", "------------------", "--------", "-----------", "--------", "------------");
						
						System.out.printf("%-5s%-5d|    %-15s%-17s%-23.2f%-13d%-16.2f%-13.2f%-12.2f    |\n", 
								"",
								rfOption + 1, 
								receipt.getPayment().getCart().getProduct()[rfOption].getProductID(), 
								receipt.getPayment().getCart().getProduct()[rfOption].getProductName(), 
								receipt.getPayment().getCart().getProduct()[rfOption].getPrice(), 
								rQuantity, 
								pricePerItem,
								discount,
								afterDiscount); 
									
						System.out.printf("%-10s|                                                                                                                     |\n", "");
						System.out.printf("%-10s-----------------------------------------------------------------------------------------------------------------------\n", "");
						
						
						
						do {
							found=0;
							System.out.println();
							System.out.printf("Do you confirm to refund this product? (y=yes) ");
							
							confirm = refundInput.next().toUpperCase().charAt(0);
							refundInput.nextLine();
							
							
							if(confirm == 'Y') {
								
								prod=new Product();
								prod.setProductID(receipt.getPayment().getCart().getProduct()[rfOption].getProductID());
								prod.setProductName(receipt.getPayment().getCart().getProduct()[rfOption].getProductName());
								prod.setCategory(receipt.getPayment().getCart().getProduct()[rfOption].getCategory());
								prod.setType(receipt.getPayment().getCart().getProduct()[rfOption].getType());
								prod.setPrice(receipt.getPayment().getCart().getProduct()[rfOption].getPrice());
								prod.setQuantity(rQuantity);
								prod.setColor(receipt.getPayment().getCart().getProduct()[rfOption].getColor());
								
								for(int i=0;i<refund.getNoOfProducts();i++) {
									if(refund.getRefundProduct()[i].getProductID().equals(prod.getProductID())) {
										refund.modifyQty(i, (refund.getRefundProduct()[i].getQuantity() + rQuantity));
										found=1;
									}
								}
								
								
								if(found != 1) {
									refund.addRefund(prod, 
											pricePerItem, 
											refund.getStatus(), 
											refund.getReason());
								}
								totalCount -= rQuantity;
								
							}
							
							else if(confirm != 'N' && confirm !='Y'){
								System.out.println("Invalid input! Please try again.");

							}
							
						}while(confirm != 'N' && confirm !='Y');
						
					}
					
					else if(rQuantity > (receipt.getPayment().getCart().getProduct()[rfOption].getQuantity() - refundQty) ) {
						System.out.println(rQuantity + " had exceeded the available refund quantity " + (receipt.getPayment().getCart().getProduct()[rfOption].getQuantity()-refundQty) + ".");
						System.out.println("Please reenter the quantity.");
						System.out.println();
					}
					
					else {
						System.out.println("Invalid input! Please try again.");
					}
					
				}while(validQty == 0);
			}
			
			
			else {
				System.out.println("All items in the receipt have been refunded.");
			}
			
			if(totalCount != 0 || refund.getNoOfProducts() > 0) {
				do {
					
					System.out.println();
					System.out.print("Do you want to refund another product? (y=yes) ");
					otherProd = refundInput.next().toUpperCase().charAt(0);
					refundInput.nextLine();
					
					if(otherProd != 'Y' && otherProd != 'N') {
						System.out.println("Invalid choice ! Please try again.");
					}
					
					
				}while(otherProd != 'Y' && otherProd != 'N');
				
				if(otherProd == 'N') {
					rfReason(refund, bankAcc, refundList, productList);
					
					
					System.out.println();
					System.out.println(refund.toString());
				}

			}
			
		}while(otherProd == 'Y');


	}

	
	public static void rfReason(Refund refund,BankAccount bankAcc, ArrayList<Refund> refundList, ArrayList<Product> productList) {
		Scanner refundInput = new Scanner(System.in);
		
		int reasonOption = 0;
		int inValid = 0;
		
		//enter & verify reason 
		System.out.println();
		System.out.println();
		System.out.printf("%-10s-----------------------------------\n", "");
		System.out.printf("%-10s|          Refund Reason          |\n", "");
		System.out.printf("%-10s-----------------------------------\n", "");
		System.out.printf("%-10s|                                 |\n", "");
		System.out.printf("%-10s|    1. Broken / Damage           |\n", "");
		System.out.printf("%-10s|    2. Not function properly     |\n", "");
		System.out.printf("%-10s|    3. Buy too much              |\n", "");
		System.out.printf("%-10s|    0. Others                    |\n", "");
		System.out.printf("%-10s|                                 |\n", "");
		System.out.printf("%-10s-----------------------------------\n", ""); 
		

		do {
			inValid=0;
			System.out.printf("Please select the refund reason: ");
			
			try {
				reasonOption = refundInput.nextInt();
				refundInput.nextLine();
			}


			catch(InputMismatchException e){
				inValid = 1;
				refundInput.nextLine();
			}
			
			
			switch(reasonOption) {
			case 1:
				refund.setStatus("approved");
				refund.setReason("broken / damage");
				break;
				
			case 2:
				refund.setStatus("approved");
				refund.setReason("not function properly");
				break;
				
			case 3:
				refund.setStatus("approved");
				refund.setReason("buy too much");
				break;
				
			case 0:
				//go to verifyReason();
				verifyReason(refund);
				break;				
				
			default:
				inValid = 1;
				
				System.out.println("Invalid input! Please try again.");
				System.out.println();
				break;
			
			}
			
			if(refund.getStatus().equals("approved")) {
				
				bankAcc.addRefund(refund);
				
				for(int i=0;i<productList.size();i++) {
					for(int j=0; j<refund.getNoOfProducts();j++) {
						if(productList.get(i).getProductID().equals(refund.getRefundProduct()[j].getProductID())) {
							productList.get(i).setQuantity( productList.get(i).getQuantity() + refund.getRefundProduct()[j].getQuantity() );
						}
					}
				}
			}
			
			refundList.add(refund);
			
		}while(inValid == 1);
		
	}
	
	
	public static void verifyReason(Refund refund) {
		Scanner refundInput = new Scanner(System.in);
		
		String reason;

		System.out.println();
		System.out.printf("Please enter an valid refund reason : ");	
		
		reason = refundInput.nextLine();
		refundInput.nextLine();
		
		if( reason.indexOf("meet") >=0 && reason.indexOf("expectation")  >= 0 ){
			refund.setStatus("approved");
			refund.setReason("didn't meet customer's expectation");
		}
		
		else if( reason.indexOf("wrong") >=0 && reason.indexOf("product")  >= 0 ){
			refund.setStatus("approved");
			refund.setReason("wrong product");
		}
		
		else if( reason.indexOf("wrong") >=0 && reason.indexOf("size")  >= 0 ){
			refund.setStatus("approved");
			refund.setReason("wrong size");
		}
		
		else if( reason.indexOf("wrong") >=0 && reason.indexOf("quantity")  >= 0 ){
			refund.setStatus("approved");
			refund.setReason("wrong quantity");
		}
		
		else if(reason.indexOf("wardrobing")  >=0 ) {
			System.out.println("This is not a valid refund reason.");
			refund.setStatus("rejected");
			refund.setReason("wardrobing");
		}
		
		else {
			System.out.println("This is not a valid refund reason.");
			refund.setStatus("rejected");
			refund.setReason(reason);
		}
	}
	
	
	
	//search
	public static void searchMenu(ArrayList<Refund> refundList, ArrayList<Product> productList) {
		Scanner refundInput = new Scanner(System.in);
		
		int searchOption = 0;
		int inValid = 0;
		char sAgain = 'Y';
		
		do {
			inValid = 0;
			System.out.println();
			System.out.printf("%-10s------------------------------------\n", "");
			System.out.printf("%-10s|        Refund Search Menu        |\n", "");
			System.out.printf("%-10s------------------------------------\n", "");
			System.out.printf("%-10s|                                  |\n", "");
			System.out.printf("%-10s|    1. by refund ID               |\n", "");
			System.out.printf("%-10s|    2. by member ID               |\n", "");
			System.out.printf("%-10s|    3. by member IC num           |\n", "");
			System.out.printf("%-10s|    4. by member contact num      |\n", "");
			System.out.printf("%-10s|    0. Exit                       |\n", "");
			System.out.printf("%-10s|                                  |\n", "");
			System.out.printf("%-10s------------------------------------\n", ""); 
			
			System.out.print("Enter your choice: ");
			
			try {
				searchOption = refundInput.nextInt();
				refundInput.nextLine();
			}
			
			catch(InputMismatchException e){
				inValid = 1;
				refundInput.nextLine();
			}
			
			switch(searchOption) {
			case 1:
				searchID(refundList);
				break;
				
			case 2:
				searchMemID(refundList);
				break;
				
			case 3:
				searchIC(refundList);
				break;
				
			case 4:
				searchContactNum(refundList);
				break;
				
			case 0:
				break;
				
			default:
				System.out.println("Invalid choice! Please try again.");
				inValid = 1;
				break;
			}
			
			do {
				sAgain = 'Y';
				
				System.out.println();
				System.out.print("Do you want to search for another refund? (y=yes) ");
				sAgain = refundInput.next().toUpperCase().charAt(0);
				
				if(sAgain != 'Y' && sAgain != 'N') {
					System.out.println("Invalid input! Please try again.");
				}
				
				
			}while(sAgain != 'Y' && sAgain != 'N');
			
		}while(inValid == 1 || sAgain == 'Y');
		
	}
	
	

	
	public static void searchID(ArrayList<Refund> refundList) {
		Scanner refundInput = new Scanner(System.in);
		
		String rfId;
		
		boolean found =false;
		int index = 0;
		char sAgain = 'Y';
		
		do {
			found = false;
			
			System.out.println();
			System.out.print("Enter refund ID : ");

			rfId = refundInput.next().toUpperCase(); //F1001
			
			//validation
			//check length & first char
			if( rfId.length() == 5 && rfId.charAt(0) == 'F' ) {
				for(int i=0; i<refundList.size(); i++) {
					if(refundList.get(i).getFullRefundID().equals(rfId)) {
						found = true;
						index = i;
					}
				}
				
				if((found == false) ) {
					sAgain = 'Y';
					
					System.out.println("No refund record is found under this refund ID,  " +  rfId + ".");
					
					do {
						System.out.println();
						System.out.print("Do you want to search again? (y=yes) ");
						sAgain = refundInput.next().toUpperCase().charAt(0);
						
						if(sAgain != 'Y' && sAgain != 'N') {
							System.out.println("Invalid input! Please try again.");
						}
						
					}while(sAgain != 'Y' && sAgain != 'N');
				}
				
				if(found == true) {
					System.out.printf("%-10s--------------------------------------------------------------------\n", "");
					System.out.printf("%-10s|  Refund ID  :  %-6s %-43s|\n", "", refundList.get(index).getFullRefundID(),"");
					if(refundList.get(index).getReceipt().getPayment().getMember() != null) {
						System.out.printf("%-10s|  Member ID  :  %-50s|\n", "", refundList.get(index).getReceipt().getPayment().getMember().getFullMemID());
					}
					
					System.out.printf("%-10s|  Reason     :  %-50s|\n", "", refundList.get(index).getReason());
					System.out.printf("%-10s|  Status     :  %-50s|\n", "", refundList.get(index).getStatus().toUpperCase());
					System.out.printf("%-10s|                                                                  |\n", "");
					System.out.printf("%-10s--------------------------------------------------------------------\n", "");
					System.out.printf("%-10s|  Product ID  |  Product Name  |  Quantity   |  Refund Price(RM)  |\n", "");
					System.out.printf("%-10s--------------------------------------------------------------------\n", "");
					
					for(int i= 0; i<refundList.get(index).getNoOfProducts(); i++) {
						
						System.out.printf("%-10s|    %-10s|  %-14s|     %-7d |   %-4s %-11.2f |\n",
								"",
								refundList.get(index).getRefundProduct()[i].getProductID(),
								refundList.get(index).getRefundProduct()[i].getProductName(),
								refundList.get(index).getRefundProduct()[i].getQuantity(),
								"",
								refundList.get(index).getPricePerItem()[i]
								);
					}
					
					System.out.printf("%-10s--------------------------------------------------------------------\n", "");
					System.out.printf("%-10s|  %-28s Subtotal (RM) |   %-4s %-11.2f |\n", "", "", "", refundList.get(index).getRefundAmount());
					System.out.printf("%-10s--------------------------------------------------------------------\n", "");
					System.out.printf("%-10s|                                                                  |\n", "");
					System.out.printf("%-10s--------------------------------------------------------------------\n", "");
				}
			}
			
			if(rfId.length() != 5 || rfId.charAt(0) != 'F') {
				System.out.println("Invalid refund ID ! Please try again.");
			}

			
		}while(found == false && sAgain == 'Y');
	}
	
	
	
	public static void searchMemID(ArrayList<Refund> refundList) {
		Scanner refundInput = new Scanner(System.in);
		
		String memId;
		
		boolean found =false;
		int index = 0;
		char sAgain ='Y';
		
		do {
			found = false;
			sAgain = 'Y';
			
			System.out.println();
			System.out.print("Enter member ID : ");

			memId = refundInput.next().toUpperCase(); //M1001
			
			//validation
			//check length & first char
			if( memId.length() == 5 && memId.charAt(0) == 'M' ) {
				
				for(int i=0; i<refundList.size(); i++) {
					if(refundList.get(i).getReceipt().getPayment().getMember() != null) {
						if(refundList.get(i).getReceipt().getPayment().getMember().getFullMemID().equals(memId)) {
							
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|  Refund ID  :  %-6s %-43s|\n", "", refundList.get(i).getFullRefundID(),"");
							System.out.printf("%-10s|  Member ID  :  %-50s|\n", "", refundList.get(i).getReceipt().getPayment().getMember().getFullMemID(),"");
							System.out.printf("%-10s|  Reason     :  %-50s|\n", "", refundList.get(i).getReason(),"");
							System.out.printf("%-10s|  Status     :  %-50s|\n", "", refundList.get(i).getStatus().toUpperCase(),"");
							System.out.printf("%-10s|                                                                  |\n", "");
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|  Product ID  |  Product Name  |  Quantity   |  Refund Price(RM)  |\n", "");
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							
							for(int j =0; j<refundList.get(i).getNoOfProducts();j++) {
								System.out.printf("%-10s|    %-10s|  %-14s|     %-7d |   %-4s %-11.2f |\n",
										"",
										refundList.get(i).getRefundProduct()[j].getProductID(),
										refundList.get(i).getRefundProduct()[j].getProductName(),
										refundList.get(i).getRefundProduct()[j].getQuantity(),
										"",
										refundList.get(i).getPricePerItem()[j]
										);
							}
							
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|  %-28s Subtotal (RM) |   %-4s %-11.2f |\n", "", "", "", refundList.get(i).getRefundAmount());
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|                                                                  |\n", "");
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							
							found = true;
						}
					}

				}
				if(found == false) {
					sAgain = 'Y'; 
					
					System.out.println("No refund record is found under this member ID,  " +  memId + ".");
					
					do {
						System.out.println();
						System.out.print("Do you want to search again? (y=yes) ");
						sAgain = refundInput.next().toUpperCase().charAt(0);
						
						if(sAgain != 'Y' && sAgain != 'N') {
							System.out.println("Invalid input! Please try again.");
						}
						
					}while(sAgain != 'Y' && sAgain != 'N');

				}
			}
			
			if(memId.length() != 5 || memId.charAt(0) != 'M' ) {
				System.out.println("Invalid member ID ! Please try again.");
			}

			
		}while(found == false && sAgain == 'Y');
	}
	
	
	public static void searchIC(ArrayList<Refund> refundList) {
		Scanner refundInput = new Scanner(System.in);
		
		String ic;
		
		boolean found = false;
		char sAgain = 'Y'; 
		
		String[] icSplit;
		
		do {

			found = false;
			sAgain = 'Y';
			
			System.out.println();
			System.out.print("Enter IC number :  ");

			ic = refundInput.next().toUpperCase(); 
			
			if( ic.length() == 12 || ic.length() == 14 ) {
				if(ic.length() == 14) {
					icSplit = ic.split("-");
					ic = icSplit[0] + icSplit[1] + icSplit[2];
				}
					
				for(int i=0; i<refundList.size(); i++) {
					if(refundList.get(i).getReceipt().getPayment().getMember() != null) {
						if(refundList.get(i).getReceipt().getPayment().getMember().getIcNo().equals(ic)) {
							
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|  Refund ID  :  %-6s %-43s|\n", "", refundList.get(i).getFullRefundID(),"");
							System.out.printf("%-10s|  Member ID  :  %-50s|\n", "", refundList.get(i).getReceipt().getPayment().getMember().getFullMemID(),"");
							System.out.printf("%-10s|  Reason     :  %-50s|\n", "", refundList.get(i).getReason(),"");
							System.out.printf("%-10s|  Status     :  %-50s|\n", "", refundList.get(i).getStatus().toUpperCase(),"");
							System.out.printf("%-10s|                                                                  |\n", "");
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|  Product ID  |  Product Name  |  Quantity   |  Refund Price(RM)  |\n", "");
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							
							for(int j =0; j<refundList.get(i).getNoOfProducts();j++) {
								System.out.printf("%-10s|    %-10s|  %-14s|     %-7d |   %-4s %-11.2f |\n",
										"",
										refundList.get(i).getRefundProduct()[j].getProductID(),
										refundList.get(i).getRefundProduct()[j].getProductName(),
										refundList.get(i).getRefundProduct()[j].getQuantity(),
										"",
										refundList.get(i).getPricePerItem()[j]
										);
							}
							
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|  %-28s Subtotal (RM) |   %-4s %-11.2f |\n", "", "", "", refundList.get(i).getRefundAmount());
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|                                                                  |\n", "");
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							
							found = true;
						}
					}

				}
				if(found == false) {
					sAgain = 'Y'; 
					
					System.out.println("No refund record is found under this member ic num, " +  ic + ".");
					
					do {
						System.out.println();
						System.out.print("Do you want to search again? (y=yes) ");
						sAgain = refundInput.next().toUpperCase().charAt(0);
						
						if(sAgain != 'Y' && sAgain != 'N') {
							System.out.println("Invalid input! Please try again.");
						}
						
					}while(sAgain != 'Y' && sAgain != 'N');
				}
			}
			
			if(ic.length() != 12 && ic.length() != 14) {
				System.out.println("Invalid IC num! Please try again.");
			}

		}while(found == false && sAgain == 'Y');
	}
	
	
	public static void searchContactNum(ArrayList<Refund> refundList) {
		Scanner refundInput = new Scanner(System.in);
		
		String contactNum;
		
		boolean found = false;
		char sAgain = 'Y'; 
		
		String[] contactNumSplit;
		
		do {

			found = false;
			sAgain = 'Y';
			
			System.out.println();
			System.out.print("Enter contact number :  ");

			contactNum = refundInput.next().toUpperCase(); 
			
			if( contactNum.length() == 10 || contactNum.length() == 11 ) {
				if(contactNum.length() == 11) {
					contactNumSplit = contactNum.split("-");
					contactNum = contactNumSplit[0] + contactNumSplit[1];
				}
					
				for(int i=0; i<refundList.size(); i++) {
					if(refundList.get(i).getReceipt().getPayment().getMember() != null) {
						if(refundList.get(i).getReceipt().getPayment().getMember().getContactNum().equals(contactNum)) {
							
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|  Refund ID  :  %-6s %-43s|\n", "", refundList.get(i).getFullRefundID(),"");
							System.out.printf("%-10s|  Member ID  :  %-50s|\n", "", refundList.get(i).getReceipt().getPayment().getMember().getFullMemID(),"");
							System.out.printf("%-10s|  Reason     :  %-50s|\n", "", refundList.get(i).getReason(),"");
							System.out.printf("%-10s|  Status     :  %-50s|\n", "", refundList.get(i).getStatus().toUpperCase(),"");
							System.out.printf("%-10s|                                                                  |\n", "");
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|  Product ID  |  Product Name  |  Quantity   |  Refund Price(RM)  |\n", "");
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							
							for(int j =0; j<refundList.get(i).getNoOfProducts();j++) {
								System.out.printf("%-10s|    %-10s|  %-14s|     %-7d |   %-4s %-11.2f |\n",
										"",
										refundList.get(i).getRefundProduct()[j].getProductID(),
										refundList.get(i).getRefundProduct()[j].getProductName(),
										refundList.get(i).getRefundProduct()[j].getQuantity(),
										"",
										refundList.get(i).getPricePerItem()[j]
										);
							}
							
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|  %-28s Subtotal (RM) |   %-4s %-11.2f |\n", "", "", "", refundList.get(i).getRefundAmount());
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							System.out.printf("%-10s|                                                                  |\n", "");
							System.out.printf("%-10s--------------------------------------------------------------------\n", "");
							
							found = true;
						}
					}

				}
				if(found == false) {
					sAgain = 'Y'; 
					
					System.out.println("No refund record is found under this contact num, " +  contactNum + ".");
					
					do {
						System.out.println();
						System.out.print("Do you want to search again? (y=yes) ");
						sAgain = refundInput.next().toUpperCase().charAt(0);
						
						if(sAgain != 'Y' && sAgain != 'N') {
							System.out.println("Invalid input! Please try again.");
						}
						
					}while(sAgain != 'Y' && sAgain != 'N');
				}

			}
			
			if(contactNum.length() != 12 && contactNum.length() != 14) {
				System.out.println("Invalid contact num! Please try again.");
			}

		}while(found == false && sAgain == 'Y');
	}
	
	
	//report
	public static void rfReportMenu(ArrayList<Refund> refundList, ArrayList<Product> productList,  ArrayList<Receipt> receiptList, ArrayList<Payment> paymentList) {
		Scanner refundInput = new Scanner(System.in);
		
		int reportOption=0;
		int inValid=0;
		
		
		do {
			System.out.println();
			System.out.printf("%-10s------------------------------------\n", "");
			System.out.printf("%-10s|        Refund Report Menu        |\n", "");
			System.out.printf("%-10s------------------------------------\n", "");
			System.out.printf("%-10s|                                  |\n", "");
			System.out.printf("%-10s|    1. Refund Reason              |\n", "");
			System.out.printf("%-10s|    2. Total Lost                 |\n", "");
			System.out.printf("%-10s|    3. Iventory Report            |\n", "");
			System.out.printf("%-10s|    0. Exit                       |\n", "");
			System.out.printf("%-10s|                                  |\n", "");
			System.out.printf("%-10s------------------------------------\n", ""); 
			
			System.out.print("Enter your choice: ");
			try {
				reportOption = refundInput.nextInt();
				refundInput.nextLine();
			}
			
			catch(InputMismatchException e){
				inValid=1;
				refundInput.nextLine();
			}
			
			switch(reportOption) {
			case 1:
				ReasonReport(refundList, productList);
				break;
				
			case 2:
				totalLost(refundList, productList);
				break;
			
			case 3:
				inventoryReport(productList, receiptList, refundList, paymentList);
				break;
				
			case 0:
				break;
				
			default:
				System.out.println("Invalid choice! Please try again");
				inValid=1;
				break;
			}
		}while(inValid == 1);
		
	}
	
	
	public static void ReasonReport(ArrayList<Refund> refundList, ArrayList<Product> productList) {

		int op1Count = 0;
		int op2Count = 0;
		int op3Count = 0;
		int op0Approved1 = 0;
		int op0Approved2 = 0;
		int op0Approved3 = 0;
		int op0Approved4 = 0;
		int quantity = 0;
		
		int totalQty = 0;
		int total1 = 0;
		int total2 = 0;
		int total3 = 0;
		int total4 = 0;
		int total5 = 0;
		int total6 = 0;
		int total7 = 0;
		
		GetDate date = new GetDate();
		
		System.out.println();
		System.out.printf("%-10s---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s| %-80s Reasons of Approved Refund Request %-81s|\n","","","");
		System.out.printf("%-10s|  %-80s Generate on: %-30s %-72s|\n","","",date,"");
		System.out.printf("%-10s---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		
		System.out.printf("%-10s|  Product ID  |  Product Name  |  Quantity   |  Broken / Damage   |  Not Function Properly  |  Buy Too Much  |  Didn't Meet Customer's Expectation  |  Wrong Product  |  Wrong Size  |  Wrong Quantity |\n", "");
		System.out.printf("%-10s---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		
		

		for(int h=0; h<productList.size(); h++) {
			op1Count = 0;
			op2Count = 0;
			op3Count = 0;
			op0Approved1 = 0;
			op0Approved2 = 0;
			op0Approved3 = 0;
			op0Approved4 = 0;
			quantity = 0;

			for(int i=0; i<refundList.size(); i++) {
				
				for(int j=0; j<refundList.get(i).getNoOfProducts(); j++) {
					
					if(refundList.get(i).getStatus().equals("approved")) {
						
						if(productList.get(h).getProductID().equals(refundList.get(i).getRefundProduct()[j].getProductID()) ) {
							quantity += refundList.get(i).getRefundProduct()[j].getQuantity();
							
							if(refundList.get(i).getReason().equalsIgnoreCase("broken / damage")) {
								op1Count += refundList.get(i).getRefundProduct()[j].getQuantity();
							}
							else if(refundList.get(i).getReason().equalsIgnoreCase("not function properly") ) {
								op2Count += refundList.get(i).getRefundProduct()[j].getQuantity();
							}
							else if(refundList.get(i).getReason().equalsIgnoreCase("buy too much") ) {
								op3Count += refundList.get(i).getRefundProduct()[j].getQuantity();
							}
							else if( refundList.get(i).getReason().equalsIgnoreCase("didn't meet customer's expectation") ) {
								op0Approved1 += refundList.get(i).getRefundProduct()[j].getQuantity();
							}
							else if( refundList.get(i).getReason().equalsIgnoreCase("wrong product") ) {
								op0Approved2 += refundList.get(i).getRefundProduct()[j].getQuantity();
							}
							else if( refundList.get(i).getReason().equalsIgnoreCase("wrong size") ) {
								op0Approved3 += refundList.get(i).getRefundProduct()[j].getQuantity();
							}
							else if( refundList.get(i).getReason().equalsIgnoreCase("wrong quantity") ) {
								op0Approved4 += refundList.get(i).getRefundProduct()[j].getQuantity();
							}
						}
						
					}
					
				}
			}
			
			
			
			if(quantity != 0) {
				System.out.printf("%-10s|    %-10s|  %-14s|     %-7d |   %-6s %-9d |   %-10s %-10d |   %-4s %-7d |   %-21s %-12d |   %-5s %-7d |  %-3s %-7d |  %-6s %-7d |\n",
						"",
						productList.get(h).getProductID(),
						productList.get(h).getProductName(),
						quantity,
						"",
						op1Count,
						"",
						op2Count,
						"",
						op3Count,
						"",
						op0Approved1,
						"",
						op0Approved2,
						"",
						op0Approved3,
						"",
						op0Approved4
						);
						
				
				totalQty += quantity;
				total1 += op1Count;
				total2 += op2Count;
				total3 += op3Count;
				total4 += op0Approved1;
				total5 += op0Approved2;
				total6 += op0Approved3;
				total7 += op0Approved4;
			}
			

		}
		

		System.out.printf("%-10s|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n", "");
		System.out.printf("%-10s|                     Subtotal: |     %-7d |   %-6s %-9d |   %-10s %-10d |   %-4s %-7d |   %-21s %-12d |   %-5s %-7d |  %-3s %-7d |  %-6s %-7d |\n",
				"",
				totalQty,
				"",total1,
				"",total2,
				"",total3,
				"",total4,
				"",total5,
				"",total6,
				"",total7);
		System.out.printf("%-10s|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n", "");
		System.out.printf("%-10s|                                                                                                                                                                                                       |\n", "");
		System.out.printf("%-10s---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		
		
	}
	
	
	public static void totalLost(ArrayList<Refund> refundList, ArrayList<Product> productList) {
		
		int quantity = 0;
		double refundPrice = 0;
		double total = 0;
		
		GetDate date = new GetDate();
		
		System.out.println();
		System.out.printf("%-10s--------------------------------------------------------------------\n", "");
		System.out.printf("%-10s| %-13s Total Lost for All Refunded Products %-14s|\n", "", "", "");
		System.out.printf("%-10s| %-15s Generate on: %-30s %-5s|\n","","",date,"");

		System.out.printf("%-10s--------------------------------------------------------------------\n", "");
		System.out.printf("%-10s|  Product ID  |  Product Name  |  Quantity   |  Refund Price(RM)  |\n", "");
		System.out.printf("%-10s--------------------------------------------------------------------\n", "");
		
		
		for(int h=0; h<productList.size(); h++) {
			quantity = 0;
			refundPrice = 0;
			
			for(int i=0; i<refundList.size(); i++) {
				
				for(int j=0; j<refundList.get(i).getNoOfProducts(); j++) {
					
					if(refundList.get(i).getStatus().equals("approved")) {
						
						if(productList.get(h).getProductID().equals(refundList.get(i).getRefundProduct()[j].getProductID()) ) {
							
							quantity += refundList.get(i).getRefundProduct()[j].getQuantity();
							refundPrice += refundList.get(i).getAftDiscountP()[j];
							//refundPrice += (refundList.get(i).getPricePerItem()[j] * quantity);
							
						}
					}
				}
			}
			
			if(quantity != 0) {
				System.out.printf("%-10s|    %-10s|  %-14s|     %-7d |   %-4s %-11.2f |\n",
						"",
						productList.get(h).getProductID(),
						productList.get(h).getProductName(),
						quantity,
						"",
						refundPrice
						);
				
				total += refundPrice;
			}
			
			
		}
		
		System.out.printf("%-10s|------------------------------------------------------------------|\n", "");
		System.out.printf("%-10s|                                Subtotal(RM) |        %-10.2f  |\n", "", total);
		System.out.printf("%-10s|------------------------------------------------------------------|\n", "");
		System.out.printf("%-10s|                                                                  |\n", "");
		System.out.printf("%-10s--------------------------------------------------------------------\n\n", "");

	}
	
	
	public static void inventoryReport(ArrayList<Product> productList, ArrayList<Receipt> receiptList, ArrayList<Refund> refundList, ArrayList<Payment> paymentList) {
		int qtySold = 0;
		int onHoldQty = 0;
		
		GetDate date = new GetDate();
		
		System.out.println();
		System.out.printf("%-10s-----------------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s| %-36s Inventory Report %-38s|\n", "", "", "");
		System.out.printf("%-10s| %-28s Generate on: %-30s %-19s|\n","","",date,"");
		System.out.printf("%-10s-----------------------------------------------------------------------------------------------\n", "");
		//System.out.printf("%-10s|  Product ID     | Available Quantity | Quantity Sold | Quantity On-Hold | Original Quantity |\n", "");
		System.out.printf("%-10s|  Product ID     | Original Quantity | Quantity Sold | Quantity On-Hold | Available Quantity |\n", "");
		System.out.printf("%-10s-----------------------------------------------------------------------------------------------\n", "");
		
		for(int i=0; i<productList.size(); i++) {
			qtySold=0; 
			onHoldQty=0;
			
			for(int j=0; j<receiptList.size(); j++) {
				for(int k=0; k<receiptList.get(j).getPayment().getCart().getNoOfProducts(); k++) {
					if( productList.get(i).getProductID().equals( receiptList.get(j).getPayment().getCart().getProduct()[k].getProductID() ) ) {
						qtySold += receiptList.get(j).getPayment().getCart().getProduct()[k].getQuantity();
					}
				}
			}
			
			for(int j=0; j<refundList.size(); j++) {
				for(int k=0; k<refundList.get(j).getNoOfProducts(); k++) {
					if( refundList.get(j).getStatus().equalsIgnoreCase("approved") ) {
						if( productList.get(i).getProductID().equals( refundList.get(j).getRefundProduct()[k].getProductID() ) ) {
							qtySold -= refundList.get(j).getRefundProduct()[k].getQuantity();
						}
					}
				}
			}
			
			for(int j=0; j<paymentList.size(); j++) {
				for(int k=0; k<paymentList.get(j).getCart().getNoOfProducts(); k++) {
					if( paymentList.get(j).getStatus().equals("On-Hold") ) {
						if( productList.get(i).getProductID().equals( paymentList.get(j).getCart().getProduct()[k].getProductID() ) ) {
							onHoldQty += paymentList.get(j).getCart().getProduct()[k].getQuantity();
						}
					}
				}
			}
			
			System.out.printf("%-10s|     %-10s  |        %-5d      |       %-5d   |        %-5d     |         %-5d      |\n", "", 
					productList.get(i).getProductID(), 
					(productList.get(i).getQuantity()+qtySold+onHoldQty), 
					qtySold, 
					onHoldQty, 
					productList.get(i).getQuantity()
					);
		}
		
		System.out.printf("%-10s-----------------------------------------------------------------------------------------------\n", "");
	}
	
}


