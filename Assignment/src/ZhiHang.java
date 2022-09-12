import java.util.ArrayList;
import java.util.Scanner;

public class ZhiHang {
	public static void login(ArrayList<Staff> staffList, ArrayList<Product> productList, ArrayList<Member> memberList, ArrayList<Payment> paymentList, ArrayList<Receipt> receiptList, BankAccount bankAccount, ArrayList<Refund> refundList) {

		int found=0, loop=0;
		char choice;
		Scanner scanner=new Scanner(System.in);
		int idNo=0;
		Staff staff;
		
		do {
			System.out.println("Welcome to Connect POS System");
			
			do {
				System.out.print("Enter staff ID: ");
				String staffID=scanner.next();
				scanner.nextLine();
				staffID=staffID.toUpperCase();
				if(staffID.length()!=5) {
					System.out.println("Invalid Staff ID! Staff ID must have 5 digits");
				}
				else {
					idNo=Integer.parseInt(staffID.replace("S", ""));
					for(Staff i: staffList) {
						if(idNo==i.getIdNo()) {
							found=1;
						}
					}
				}
				if(found==0) {
					System.out.println("Staff ID not found. Please try again");
					System.out.print("Would you like to try again? (Y=yes) ");
					choice=Character.toUpperCase(scanner.next().charAt(0));
					scanner.nextLine();
				}
				
			}while(found==0);
			
			if(found==1) {
				System.out.print("Enter Password: ");
				String password=scanner.next();
				scanner.nextLine();
				for(int i=0; i<staffList.size(); i++) {
					if(idNo==staffList.get(i).getIdNo()&&password.equals(staffList.get(i).getPassword())) {
						System.out.println("Successful login");
						System.out.println("Welcome "+staffList.get(i).getName());
						
						mainMenu(staffList, productList, memberList, staffList.get(i), receiptList, paymentList, bankAccount, refundList);
					}
				}
			}
		}while(loop==0);
		
	}
	
	public static void mainMenu(ArrayList<Staff> staffList, ArrayList<Product> productList, ArrayList<Member> memberList, Staff staff, ArrayList<Receipt> receiptList, ArrayList<Payment> paymentList, BankAccount bankAccount, ArrayList<Refund> refundList) {
		Scanner scanner=new Scanner(System.in);
		int choice=0, loop=0;
		char quitChoice;
		
		do {
			choice=0;
			loop=0;
			System.out.println("Main Menu");
			System.out.println("1. Place Order");
			System.out.println("2. On-Hold Payment List");
			System.out.println("3. Report");
			System.out.println("0. Logout");
			System.out.print("Select Action to perform: ");
			choice=scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1: ChiKean.placeOrder(staffList,productList,memberList, paymentList, staff, receiptList, bankAccount); loop=1; break;
			case 2: ChiKean.onHoldPayment(paymentList, memberList, staff, productList, receiptList, bankAccount); loop=1; break;
			case 3: ChiKean.report(receiptList); loop=1; break;
			case 0: break;
			}
			
			if(loop==0) {
				System.out.print("Are you sure you want to logout? (Y=yes)");
				quitChoice=Character.toUpperCase(scanner.next().charAt(0));
				scanner.nextLine();
				
				if(quitChoice!='Y') {
					loop=1;
				}
			}
		}while(loop==1);
		
		
		
	}
	
	
}
