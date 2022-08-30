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
			System.out.println("0. Logout");
			System.out.print("Select Action to perform: ");
			choice=scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1: ChiKean.cart(staffList,productList,memberList, paymentList, staff, receiptList, bankAccount); loop=1; break;
			case 2: ChiKean.onHoldPayment(paymentList, memberList, staff, productList, receiptList, bankAccount); loop=1; break;
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
	public static void StaffTable() {
		int chooseStaff=0;
		Scanner scanner=new Scanner(System.in);
		System.out.println("Welcome To Staff Section");
		System.out.printf("1)Add Staff\n2)Modify Staff Detail\n3)Sale Report\n4)Exit");
		System.out.print("Enter Selection 1-3");
		try {
		chooseStaff=scanner.nextInt();
		if(chooseStaff!=1||chooseStaff!=2||chooseStaff!=3||chooseStaff!=4) {
			throw new Exception("Option is Not Valid\nPlease Enter Again:");
		}
		}
		catch(Exception exe) {
			//Put in the method
			if(chooseStaff==1) {}
			else if(chooseStaff==2) {AddStaff();}
			else if(chooseStaff==3) {}
			else if(chooseStaff==4) {}
		}
		
	}
	
	public static void AddStaff() {
		/*private String staffID, position, password;
	private int idNo;
	private double salary;
	private GetDate joinDate;
	private String status;
	private static int staffCount;
	private static int genIDNo;*/
		Scanner scanner=new Scanner(System.in);
		String staffName,icNo,staffPosition;
		int staffID;
		String staffPassword,valiPassword;
		double salary;
		String ContinueAdd;
		int genIDNo;
		int countIC=0,countName=0;
		
		//Birthday and generated add time and date
		//Enter Name
		
		do {
			System.out.print("Enter Name :");
			staffName=scanner.nextLine();
			scanner.nextLine();
			countName=0;
			for(int j=0;j<staffName.length();j++) {
				char seeChaName=staffName.charAt(j);
				if(Character.isDigit(staffName.charAt(j))) {
					countName++;
				}
				else if(!Character.isLetter(seeChaName)&&!Character.isWhitespace(seeChaName)){
	         		countName++;
	         	}
			}
			if(countName!=0) {
				System.out.println("Name Format Invalid");
				System.out.println("Please Try Again");
			}
		}while(countName!=0);
		
		//Enter IC +Validation
		
		System.out.print("Enter IC No :");
		
		icNo=scanner.nextLine();
		countIC=0;
		if(icNo.length()==12) {
			for(int i=0;i<icNo.length();i++) {
	
				if(Character.isDigit(icNo.charAt(i))) {
				  if(!icNo.substring(2,3).equals("01")&&!icNo.substring(2,3).equals("02")&&!icNo.substring(2,3).equals("03")&&!icNo.substring(2,3).equals("04")
				  &&!icNo.substring(2,3).equals("05")&&!icNo.substring(2,3).equals("06")&&!icNo.substring(2,3).equals("07")&&!icNo.substring(2,3).equals("08")
				  &&!icNo.substring(2,3).equals("09")&&!icNo.substring(2,3).equals("10")&&!icNo.substring(2,3).equals("11")&&!icNo.substring(2,3).equals("12")){
				    	boolean getMonth=false; 	
				  }
				  countIC++;
				}	
			}
		
			if(countIC!=12) {
				System.out.println("IC Format Invalid");
				System.out.println("Please Try Again");
			}
		
			do {
				System.out.print("Enter Position:");
				staffPosition="";
				staffPosition=scanner.nextLine();
				staffPosition.toLowerCase();
				if(!staffPosition.equals("cashier")&&!staffPosition.equals("manager")&&!staffPosition.equals("executive")) { 
					System.out.println("Position is Invalid");
				    System.out.println("Please Enter Again");
				}
			}while(!staffPosition.equals("cashier")&&!staffPosition.equals("manager")&&!staffPosition.equals("executive"));
		        
		
			int countPassword=0;
			int getNumAlp=0;
			int getNumValue=0;
			int countPass=0;
			do {		
				getNumAlp=0;
				getNumValue=0;
				countPass=0;
				System.out.print("Enter Password :");
				staffPassword=scanner.nextLine();
				scanner.nextLine();
				System.out.print("Enter Password Again :");
				valiPassword=scanner.nextLine();

				if(valiPassword.equals(staffPassword)) {
					if(valiPassword.length()>6) {
						for(int i=0;i<valiPassword.length();i++){
							if(Character.isLetter(valiPassword.charAt(i))) {
								getNumAlp++;
							}
							else if(Character.isDigit(valiPassword.charAt(i))){
								getNumValue++;
							}
							else if(!Character.isLetter(valiPassword.charAt(i))&&!Character.isDigit(valiPassword.charAt(i))){
								countPass++;
							}		
							
							if(getNumAlp>=1&&getNumValue>=1&&countPass>=1){
								System.out.println("Valid Password");
							}
							else {
								System.out.println("Invalid Password");
								System.out.println("Please Ensure The Password Format Is CORRECT");
							}
						}
					}
				    else {
						System.out.println("Invalid Password");
						System.out.println("Please Ensure The Password is at least 7 characters long");
				    }
				}
				else {
					System.out.println("Invalid Password");
					System.out.println("Passwords Entered do not match");
				}
			
			}while((valiPassword.length()<=6)||(!valiPassword.equals(staffPassword))||(getNumAlp<1&&getNumValue<1&&countPass<1));
		}
}

	private static boolean parseInt(String substring) {
		// TODO Auto-generated method stub
		return false;
	}
		
		
		
		
		

	
	
}

