import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.*;


public class ZhiHang {
	public static void login(ArrayList<Staff> staffList, ArrayList<Product> productList, ArrayList<Member> memberList, ArrayList<Payment> paymentList, ArrayList<Receipt> receiptList, BankAccount bankAccount, ArrayList<Refund> refundList) {
		Scanner scanner=new Scanner(System.in);
		int found=0, loop=0;
		char choice='N';
		String staffID;
		String password;
		int cancel=1;
		int locked=0;
		int index=0;
		int wrongCount=0;
		int valid=0;
		
		do {
			cancel=0;
			wrongCount=0;
			loop=0;
			valid=0;
			index=0;
			Main.logo();
			System.out.println();
			
			System.out.printf("%-25sWelcome to Connect Point Of Sales System\n", "");
			
			do {
				locked=0;
				choice='N';
				System.out.printf("\n%-25sEnter staff ID (Press X to exit) : ", "");
				staffID=scanner.next();
				scanner.nextLine();
				staffID=staffID.toUpperCase();
				found=0;
				
				if((staffID.length()!=5)&&(!staffID.equals("X"))) {
					System.out.println();
					System.out.printf("%-22sInvalid Staff ID! Staff ID must have 5 digits\n\n", "");
					choice='Y';
				}
				else if(staffID.equals("X")) {
					cancel=1;
				}
				else {
					for(int i=0; i<staffList.size()&&found!=1; i++) {
						if(staffList.get(i).getFullStaffID().equals(staffID)) {
							found=1;
							index=i;
							if(staffList.get(i).getStatus().equals("Deactivate")) {
								System.out.println();
								System.out.printf("%-17sLogin Failed. Staff Account %s has been deactivated\n", "", staffList.get(i).getFullStaffID());
								System.out.println();
								locked=1;
							}
							else if(staffList.get(i).getStatus().equals("Resign")){
								System.out.println();
								System.out.printf("%-26sLogin Failed. Staff %s has resigned\n\n", "", staffList.get(i).getFullStaffID());
								locked =1;
							}
						}
					}
					
					if(found==0) {
						System.out.printf("\n%-24sLogin Failed. Staff Account does not exist\n\n", "");
					}
					
					if(found==0 || locked == 1) {
						System.out.printf("%-25sWould you like to try again? (Y=yes) ", "");
						choice=Character.toUpperCase(scanner.next().charAt(0));
						scanner.nextLine();
						if(choice!='Y') {
							cancel=1;
						}
					}
				}
				
				
			}while(choice=='Y');
			
			do {
				choice='N';
				if(found==1 && cancel != 1 && locked != 1) {
					System.out.printf("\n%-25sEnter Password(Press X to cancel): ", "");
					password=scanner.next().toUpperCase();
					scanner.nextLine();
					
					if(staffList.get(index).getFullStaffID().equals(staffID) && staffList.get(index).getPassword().equals(password)) {
						System.out.println();
						System.out.printf("%-40sSuccessful login\n\n", "");
						if(staffList.get(index).getName().length()>=10&&staffList.get(index).getName().length()<=15) {
							System.out.printf("%-38sWelcome %s\n\n", "", staffList.get(index).getName());
						}
						else if(staffList.get(index).getName().length()>15&&staffList.get(index).getName().length()<20) {
							System.out.printf("%-35sWelcome %s\n\n", "", staffList.get(index).getName());
						}
						else if(staffList.get(index).getName().length()>=20) {
							System.out.printf("%-33sWelcome %s\n\n", "", staffList.get(index).getName());
						}
						else {
							System.out.printf("%-38sWelcome %s\n\n", "", staffList.get(index).getName());
						}
						
						mainMenu(staffList, productList, memberList, staffList.get(index), receiptList, paymentList, bankAccount, refundList);
						valid=1;
						loop=1;
					}
					else if(password.equals("X")) {
						cancel=1;
						loop=1;
					}
					else {
						System.out.printf("\n%-25sAccess Denied. Password is incorrect.\n", "");
						wrongCount++;
					}
					
					if(wrongCount==5) {
						System.out.println("  You have entered your password incorrectly for 5 consecutive times. Account has been locked. Please contact your superior to deactivate your account");
						staffList.get(index).setStatus("Deactivate");
						loop=1;
					}
					else if (wrongCount<5&&valid!=1&&cancel!=1){
						System.out.printf("%-25sWould you like to try again? (Y=yes) ", "");
						choice=Character.toUpperCase(scanner.next().charAt(0));
						scanner.nextLine();
						if(choice!='Y') {
							System.out.println();
							System.out.println();
							loop=1;
							cancel=1;
						}
					}
				}
			}while(choice=='Y');
			
			
		}while(loop==1);
		
	}
	
	public static void mainMenu(ArrayList<Staff> staffList, ArrayList<Product> productList, ArrayList<Member> memberList, Staff staff, ArrayList<Receipt> receiptList, ArrayList<Payment> paymentList, BankAccount bankAccount, ArrayList<Refund> refundList) {
		Scanner scanner=new Scanner(System.in);
		int choice=0, loop=0, invalid = 0;
		char quitChoice;
	
		if(staff.getPosition().equalsIgnoreCase("cashier")) {
			do {
			
				choice=0;
				loop=0;
				invalid=0;
				System.out.printf("%-27s-------------------------------------\n", "");
				System.out.printf("%-27s|              Main Menu            |\n", "");
				System.out.printf("%-27s-------------------------------------\n", "");
				System.out.printf("%-27s|    1. Order and Payment Module    |\n", "");
				System.out.printf("%-27s|    2. Member Management Module    |\n", "");
				System.out.printf("%-27s|    3.       Refund Module         |\n", "");	
				System.out.printf("%-27s|    4.      Change Password        |\n", "");
				System.out.printf("%-27s|    5.          Report             |\n", "");
				System.out.printf("%-27s|    0.          Logout             |\n", "");
				System.out.printf("%-27s-------------------------------------\n\n", "");
	
				System.out.printf("%-32sSelect Action to perform: ", "");
				try {
					choice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e) {
					scanner.nextLine();
					invalid=1;
				}
				
				if(choice<0||choice>5||invalid==1) {
					System.out.printf("%-25sInvalid Choice. Press Enter to try again\n","");
					scanner.nextLine();
					loop=1;
				}
				else {
					switch(choice) {
					case 1: ChiKean.orderMenu(staffList, productList, memberList, paymentList, staff, receiptList, bankAccount); loop=1; break;
					case 2: Claris.menu(memberList, staff, receiptList, refundList); loop=1; break;
					case 3: JiaHui.refundMenu(receiptList, productList, bankAccount, refundList, paymentList); loop=1; break;
					case 4:	editPassword(staffList,staff); loop=1; break;
					case 5: dailyReport(receiptList,staff);break;
					case 0: break;
					}
				}
				
				if(loop==0) {
					System.out.printf("\n%-24sAre you sure you want to logout? (Y=yes) ", "");
					quitChoice=Character.toUpperCase(scanner.next().charAt(0));
					scanner.nextLine();
					
					if(quitChoice!='Y') {
						loop=1;
					}
				}
			}while(loop==1);
		
		}
		
		else if(staff.getPosition().equalsIgnoreCase("admin")||staff.getPosition().equalsIgnoreCase("executive")||staff.getPosition().equalsIgnoreCase("manager")) {
			do {
				
				choice=0;
				loop=0;
				invalid=0;
				System.out.printf("%-27s-------------------------------------\n", "");
				System.out.printf("%-27s|              Main Menu            |\n", "");
				System.out.printf("%-27s-------------------------------------\n", "");
				System.out.printf("%-27s|    1. Order and Payment Module    |\n", "");
				System.out.printf("%-27s|    2. Member Management Module    |\n", "");
				System.out.printf("%-27s|    3.      Refund Module          |\n", "");	
				System.out.printf("%-27s|    4. Staff Management Module     |\n", "");	
				System.out.printf("%-27s|    5.      View Reports           |\n", "");
				System.out.printf("%-27s|    0.         Logout              |\n", "");
				System.out.printf("%-27s-------------------------------------\n\n", "");

				System.out.printf("%-32sSelect Action to perform: ", "");
				
				try {
					choice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e) {
					scanner.nextLine();
					invalid=1;
				}
				
				if(choice<0||choice>5||invalid==1) {
					System.out.printf("%-25sInvalid Choice. Press Enter to try again\n","");
					scanner.nextLine();
					loop=1;
				}
				else {
					switch(choice) {
					case 1: ChiKean.orderMenu(staffList, productList, memberList, paymentList, staff, receiptList, bankAccount); loop=1; break;
					case 2: Claris.menu(memberList, staff, receiptList, refundList); loop=1; break;
					case 3: JiaHui.refundMenu(receiptList, productList, bankAccount, refundList, paymentList); loop=1; break;
					case 4: staffMenu(staffList,staff,receiptList); loop=1; break;
					case 5: reportMenu(productList, receiptList, refundList, paymentList, memberList, staffList); loop=1; break;
					case 0: break;
					}
				}
				
				
				if(loop==0) {
					System.out.printf("\n%-24sAre you sure you want to logout? (Y=yes) ", "");
					quitChoice=Character.toUpperCase(scanner.next().charAt(0));
					scanner.nextLine();
					
					if(quitChoice!='Y') {
						loop=1;
					}
				}
			}while(loop==1);
			
			}
	}
	
	public static void staffMenu(ArrayList<Staff> staffList,Staff staff,ArrayList<Receipt> receiptList) {
		if(staff.getPosition().equalsIgnoreCase("manager")||staff.getPosition().equalsIgnoreCase("admin")) {
			int choice=0, loop=0, invalid=0;
			char quitChoice;
			Scanner scanner=new Scanner(System.in);
			do {
				invalid=0;
				choice=0;
				loop=0;
				System.out.printf("%-10s==================================\n", "");
				System.out.printf("%-10sWELCOME TO STAFF MANAGEMENT MODULE\n", "");
				System.out.printf("%-10s==================================\n\n", "");
				
				System.out.printf("%-10s------------------------------\n", "");
				System.out.printf("%-10s|Staff Management Module Menu|\n", "");
				System.out.printf("%-10s------------------------------\n", "");
				System.out.printf("%-10s|  1.      Add Staff         |\n", "");
				System.out.printf("%-10s|  2. Modify Staff Details   |\n", "");
				System.out.printf("%-10s|  3.    View All Staff      |\n", "");
				System.out.printf("%-10s|  0.         Exit           |\n", "");
				System.out.printf("%-10s------------------------------\n", "");
				System.out.print("Select Action to perform: ");
				
				try {
					choice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e) {
					scanner.nextLine();
					invalid=1;
				}
				
				
		
				switch(choice) {
				case 1:addStaff(staffList); loop=1; break;
				case 2: modifyStaffMenu(staffList,staff,receiptList); loop=1; break;
				case 3:viewStaff(staffList); loop=1; break;
				case 0: break;
				default: loop=1; break;
				}
				
			}while(loop==1);
		}
		
			
	}
	
	public static void modifyStaffMenu(ArrayList<Staff> staffList,Staff staff,ArrayList<Receipt> receiptList) {
		int choice=0, loop=0, invalid=0;;
		char quitChoice;
		Scanner scanner=new Scanner(System.in);
		do {
			
			choice=0;
			loop=0;
			invalid=0;
		
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s| Modify Staff Details Menu  |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s|1. Edit Password            |\n", "");
			System.out.printf("%-10s|2. Change Contact Number    |\n", "");
			System.out.printf("%-10s|3. Change Position          |\n", "");
		    System.out.printf("%-10s|4. Change Name              |\n", "");
		    System.out.printf("%-10s|5. Change Status            |\n", "");
			System.out.printf("%-10s|0. Exit                     |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			System.out.print("Select Action to perform: ");
			try {
				choice=scanner.nextInt();
				scanner.nextLine();
			}
			catch(InputMismatchException e) {
				scanner.nextLine();
				invalid=1;
			}
			switch(choice) {
			case 1:editPassword(staffList,staff); loop=1;break;
			case 2:editContactNumber(staffList);loop=1;break;
			case 3:changePosition(staffList);loop=1;break;
			case 4:editName(staffList);loop=1;break;
			case 5:changeStatus(staffList,staff);loop=1;break;
			case 0: break;
			}
			
		}while(loop==1);
	
	}
	
	public static void reportMenu(ArrayList<Product> productList, ArrayList<Receipt> receiptList, ArrayList<Refund> refundList, ArrayList<Payment> paymentList, ArrayList<Member> memberList, ArrayList<Staff> staffList) {
		Scanner scanner=new Scanner(System.in);
		int choice=0;
		int invalid=0;
		int loop=0;
		do {
			choice=0;
			invalid=0;
			loop=0;
			System.out.println();
			System.out.printf("%-30s          ===========\n", "");
			System.out.printf("%-30s          REPORT MENU\n", "");
			System.out.printf("%-30s          ===========\n\n", "");
			
			System.out.printf("%-30s-------------------------------\n", "");
			System.out.printf("%-30s|         Report Menu         |\n", "");
			System.out.printf("%-30s-------------------------------\n", "");
			System.out.printf("%-30s|    1.    Sales Report       |\n", "");
			System.out.printf("%-30s|    2.    Staff Report       |\n", "");
			System.out.printf("%-30s|    3.    Member Report      |\n", "");
			System.out.printf("%-30s|    4.    Refund Report      |\n", "");
			System.out.printf("%-30s|    0.   Exit Report Menu    |\n", "");
			System.out.printf("%-30s-------------------------------\n\n", "");
			System.out.printf("%-30s     Select your choice: ", "");
			try {
				choice=scanner.nextInt();
				scanner.nextLine();
			}
			catch(InputMismatchException e) {
				invalid=1;
				scanner.nextLine();
			}
			
			if((choice>=0&&choice<=4)&&invalid==0) {
				switch(choice) {
				case 1: ChiKean.reportMenu(productList, receiptList, refundList);; loop=1; break;
				case 2: staffReport(receiptList, staffList); loop=1; break;
				case 3: Claris.displayReport(memberList, receiptList, refundList); loop=1; break;
				case 4: JiaHui.rfReportMenu(refundList, productList, receiptList, paymentList); loop=1; break;
				case 0: break;
				}
			}
			else {
				System.out.println("  Invalid Choice. Press Enter to try again");
				scanner.nextLine();
			}
		}while(choice<0||choice>4||invalid==1||loop==1);
	}
	
	public static void dailyReport( ArrayList<Receipt> receiptList, Staff staff) {
		int countTransaction=0;
		System.out.printf("%-10s-------------------------------------------------------\n", "");
		System.out.printf("%-10s|                  Daily  Report                      |\n", "");
		System.out.printf("%-10s-------------------------------------------------------\n", "");
		System.out.printf("%-10s|Staff Name:%-12s                              |\n","",staff.getName());
		for(int i=0;i<receiptList.size();i++) {
			if(receiptList.get(i).getPayment().getStaff().getFullStaffID().equals(staff.getFullStaffID())) {
				countTransaction++;
			}
			
		}
		System.out.printf("%-10s|You Had Completed %d Order !                          |\n","",countTransaction);                                                            
		System.out.printf("%-10s|Your Next Target is %d Order !                        |\n","",countTransaction+5);
		System.out.printf("%-10s|Thank You For Your Constribution !                   |\n","");                     
		System.out.printf("%-10s------------------------------------------------ -----\n", "");
	}
	public static void staffReport( ArrayList<Receipt> receiptList, ArrayList<Staff> staffList) {
		Scanner scanner=new Scanner(System.in);
		String getID;
		int getidNo;
		
			String getReenter;
			for(int i=0;i<staffList.size();i++) {
				
					System.out.println("Staff Name:"+staffList.get(i).getName());
					System.out.println("Staff ID:"+staffList.get(i).getIdNo());
					System.out.println("Report:");
					int countTransaction=0;
					System.out.printf("%-10s--------------------------------------------------\n", "");
					System.out.printf("%-10s|               Staff Daily  Report              |\n", "");
					System.out.printf("%-10s--------------------------------------------------\n", "");
					System.out.printf("%-10s| Name                  Total Transaction Done   |\n", "");
					System.out.printf("%-10s--------------------------------------------------\n", "");

				for(int k=0;k<staffList.size();k++) {
					countTransaction=0;
					
						for(int j=0;j<receiptList.size();j++) {
							if(receiptList.get(i).getPayment().getStaff().getFullStaffID().equals(staffList.get(j).getFullStaffID())) {
								countTransaction++;
							}
							
					}		System.out.printf("%-10s|%-20s            %-2d              |\n","",staffList.get(k).getName(),countTransaction);

						
					}
					
				}
					
					System.out.printf("%-10s|         Thank You For Their Constribution !    |\n","");                     
					System.out.printf("%-10s-------------------------------------------------\n", "");
					
				
				}

	public static void viewStaff(ArrayList<Staff>staffList) {
		System.out.printf("%-10s------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s|                               Staff List                                                                                                                       |\n", "");
		System.out.printf("%-10s------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s|Name                                    Phone Number                         Birthday            Position                 Salary            Status              |\n", "");
		System.out.printf("%-10s------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		for(int i=0;i<staffList.size();i++) {
	   System.out.printf("%-10s|%-30s           %-20s                 %-10s        %-10s            %9.2f           %-10s           |\n","",staffList.get(i).getName(),staffList.get(i).getContactNum(),staffList.get(i).getBirthDate(),staffList.get(i).getPosition(),staffList.get(i).getSalary(),staffList.get(i).getStatus());
		}
		System.out.printf("%-10s------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		
	}
		
	
	public static void editName(ArrayList<Staff>staffList) {
		char getChoice;
		String tryAgain="N";
	    String getID;
	    String getFirstS;
	    int getidNo;
	    boolean foundStatus;
	    boolean checkName;
	    Scanner scanner=new Scanner(System.in);
	    String staffName;
		 String getConfirm;
	    do {
	    	foundStatus=true;
	    	System.out.print("Please Enter Staff ID To Be Edit:");
	    	getID=scanner.nextLine();
			scanner.nextLine();
			getID=getID.toUpperCase();
			if(getID.length()!=5) {
				System.out.println("Invalid Staff ID Format! Staff ID must have 5 digits SXXXX");
			}
			else {
				getidNo=Integer.parseInt(getID.replace("S", ""));
				String getReenter;
				for(int i=0;i<staffList.size();i++) {
					if(staffList.get(i).getIdNo()==getidNo) {
						System.out.println("Staff Name:"+staffList.get(i).getName());
						System.out.println("Staff ID:"+staffList.get(i).getIdNo());
						foundStatus=true;
						String newName;
						do { 
							
							System.out.print("Enter New Name :");
							staffName=scanner.nextLine();
							scanner.nextLine();
							checkName=valiName(staffName);
							if(checkName==false) {
								System.out.println("Name Format Invalid");
								System.out.println("Please Try Again");
							}
						}while(checkName==false);
						if(checkName==true) {
						System.out.print("Do You Sure To Modify This Name?:");
						getReenter=scanner.nextLine();
						getReenter=getReenter.toUpperCase();
						if(getReenter.equals("Y")) {
						staffList.get(i).setName(staffName);
						writeTextFile(staffList);
						System.out.println("Name Modified");
						}
						else if(!getReenter.equals("N")||!getReenter.equals("Y")) {
							System.out.println("Invalid");
						}
						else {
							System.out.println("Name Not Modified And Will Back To Staff Management Page");						
						}
					  }
					}
				}
			}
			if(foundStatus==false) {
				System.out.println("Staff ID not found. Please try again");
				System.out.print("Would you like to try again? (Y=yes) ");
				getChoice=Character.toUpperCase(scanner.next().charAt(0));
				scanner.nextLine();
			}
			
		}while(foundStatus==false);
		
	}
	public static void editContactNumber(ArrayList<Staff> staffList) {
		char getChoice;
		boolean checkPassword=true;
		String tryAgain="N";
	    String getID;
	    String getFirstS;
	    int getidNo;
	    boolean foundStatus;
	    Scanner scanner=new Scanner(System.in);
	    
	    do {
	    	foundStatus=true;
	    	System.out.print("Please Enter Staff ID To Be Edit:");
	    	getID=scanner.nextLine();
			scanner.nextLine();
			getID=getID.toUpperCase();
			if(getID.length()!=5) {
				System.out.println("Invalid Staff ID Format! Staff ID must have 5 digits SXXXX");
			}
			else {
				getidNo=Integer.parseInt(getID.replace("S", ""));
				String getReenter;
				for(int i=0;i<staffList.size();i++) {
					if(staffList.get(i).getIdNo()==getidNo) {
						System.out.println("Staff Name:"+staffList.get(i).getName());
						System.out.println("Staff ID:"+staffList.get(i).getIdNo());
						foundStatus=true;
						String ModiphoneNum,newPhoneNum;
						System.out.print("Enter New Phone Number:");
						ModiphoneNum=scanner.nextLine();
						newPhoneNum= contactNoValidation(ModiphoneNum);
						System.out.print("Do You Sure To Modify This Phone Number?:");
						getReenter=scanner.nextLine();
						getReenter=getReenter.toUpperCase();
						if(getReenter.equals("Y")) {
						staffList.get(i).setContactNum(newPhoneNum);
						writeTextFile(staffList);
						System.out.println("Phone Number Modified");
						foundStatus=true;
						}
						else if(!getReenter.equals("N")&&!getReenter.equals("Y")) {
							System.out.println("Invalid");
						}
						else {
							System.out.println("Phone Number Not Modified And Will Back To Staff Management Page");						
						}
					}
				}
			}
			if(foundStatus==false) {
				System.out.println("Staff ID not found. Please try again");
				System.out.print("Would you like to try again? (Y=yes) ");
				getChoice=Character.toUpperCase(scanner.next().charAt(0));
				scanner.nextLine();
			}
			
		}while(foundStatus==false);
		
	   
	}
	

	public static void addStaff(ArrayList<Staff> staffList) {
		    String getConfirm;
		  String addStaff="";
		  boolean getStaffname = false;
		do {
		 Scanner scanner=new Scanner(System.in);
		 String staffName,icNo,staffPosition;
		 String staffID = null;
		 String birthDate = null;
		 String staffPassword,valiPassword;	
		 String ContinueAdd;
		 String joinDate = null;
		 String status = null;
		 double salary = 0;
         int genIDNo;
		 int countIC=0,countName=0;
	     int countEnter;
	     int idNo = 0;
	   boolean checkname;
	
	    getConfirm="N";
		
		//Birthday and generated add time and date
		//Enter Name
		System.out.print("Welcome to Staff Add Section\n");
		do { 
			
			System.out.print("Enter Name :");
			staffName=scanner.nextLine();
			scanner.nextLine();
			checkname=valiName(staffName);
			if(checkname==false) {
				System.out.println("Name Format Invalid");
				System.out.println("Please Try Again");
			}
		}while(checkname==false);
		
	String knowBirthDate;
		boolean checkIC;
		//Enter IC +Validation
		do {	
		
			System.out.print("Enter IC No :");
			
			icNo=scanner.nextLine();
			checkIC=checkIC(icNo);
			
			if(checkIC==false) {
					System.out.println("IC Format Invalid");
					System.out.println("Please Try Again");
				
				}
			
		}while(checkIC==false);
		String phoneNum,newPhoneNum;
		System.out.print("Enter Phone Number:");
		phoneNum=scanner.nextLine();
		newPhoneNum= contactNoValidation(phoneNum);
		
		
		boolean getPosition;
			do {
				System.out.print("Enter Position:");
				staffPosition="";
				staffPosition=scanner.nextLine();
				staffPosition=staffPosition.toLowerCase();
				
				if(!staffPosition.equals("cashier")&&!staffPosition.equals("manager")&&!staffPosition.equals("executive")) { 
					System.out.println("Position is Invalid");
				    System.out.println("Please Enter Again");
				}
			}while(!staffPosition.equals("cashier")&&!staffPosition.equals("manager")&&!staffPosition.equals("executive"));
		        
		
		
			boolean checkPassword=false;
			do {		
		
				System.out.print("Enter Password :");
				staffPassword=scanner.nextLine();
				scanner.nextLine();
				System.out.print("Enter Password Again :");
				valiPassword=scanner.nextLine();
        
          
				if(valiPassword.equals(staffPassword)) {
				
					checkPassword=checkPassword(valiPassword);
					
						if(checkPassword==true){
								System.out.println("Valid Password");
							}
							else {
								System.out.println("Invalid Password");
								System.out.println("Please Check Through These Critiral");
								System.out.println("1)Please Ensure The Password Format Is CORRECT");
								System.out.println("2)Please Ensure The Password is at least 7 characters long");
							}
				}
				else {
					System.out.println("Invalid Password");
					System.out.println("Passwords Entered do not match");
				}
			
			}while(checkPassword==false);
			countEnter=0;
			 getConfirm="";
				System.out.print("Do You Want To Add This Staff ?(Y=Yes) :");
				addStaff=scanner.nextLine();
		    	addStaff=addStaff.toUpperCase();
		    	if(addStaff.equals("Y")) {
		    		Staff newStaff=new Staff(staffName,icNo,newPhoneNum,valiPassword,staffPosition);
		    		staffList.add(newStaff);
		    		countEnter++;
		    		
		    		writeTextFile(staffList);
		    	}
		    	else {
					System.out.println("Staff Is Not Added");
		    	}
		   
			System.out.print("Do You Want To Continue Add Staff ?(Y=Yes) :");
			 getConfirm=scanner.nextLine();
			 getConfirm=getConfirm.toUpperCase();
	    	
	    	
	    	System.out.println("The Number Of Staff Added is "+countEnter);
	    		
			}while(getConfirm.equals(("Y")));
		
}

	public static void editPassword(ArrayList<Staff> staffList,Staff staff) {
		String getChoice;
		boolean checkPassword=true;
		String tryAgain="N";
	    String getAddPass;
	    String getID;
	    String getFirstS;
	    Scanner scanner=new Scanner(System.in);
	    if(staff.getPosition().equals("cashier")) {
	    	do {
				System.out.print("Enter New Password :");
				String newPassword=scanner.nextLine();
				scanner.nextLine();
				System.out.print("Enter Password Again :");
				String newEnterPassword=scanner.nextLine();
	    
	      
					if(newEnterPassword.equals(newPassword)) {
				
					 checkPassword=checkPassword(newEnterPassword);
					
						if(checkPassword==true){
								System.out.println("Valid Password");
								System.out.println(" Password is "+newEnterPassword);
								staff.setPassword(newEnterPassword);
								writeTextFile(staffList);
								tryAgain.equals("N");
							}
							else {
								System.out.println("Invalid Password");
								System.out.println("Please Check Through These Critiral");
								System.out.println("1)Please Ensure The Password Format Is CORRECT");
								System.out.println("2)Please Ensure The Password is at least 7 characters long");
							}
				}
					else {
					System.out.println("Invalid Password");
					System.out.println("Passwords Entered do not match");
					System.out.println("Do You Want To Try Again?(Press Y)");
					tryAgain=scanner.nextLine();
					tryAgain=tryAgain.toUpperCase();
				}
					
				}while(tryAgain.equals("Y"));	
					
	    }
	
	    if(!staff.getPosition().equals("cashier")) {
		do {
	
		System.out.print("Please Enter The ID of Staff:");
		getID=scanner.nextLine();
		 getFirstS=getID.substring(0,1);
		getFirstS=getFirstS.toUpperCase();
		if(getFirstS.equals("S")) {
		int getNum=Integer.parseInt(getID.substring(1,5));
		
		for(int i=0;i<staffList.size();i++) {
			if((staffList.get(i).getIdNo()==getNum&&staffList.get(i).getPosition().equals("cashier"))||(staffList.get(i).getIdNo()==getNum&&staffList.get(i).getPosition().equals("cashier"))) {
			
		}
			if(staffList.get(i).getIdNo()==getNum) {
				do {
				System.out.print("Enter New Password :");
				String newPassword=scanner.nextLine();
				scanner.nextLine();
				System.out.print("Enter Password Again :");
				String newEnterPassword=scanner.nextLine();
	    
	      
					if(newEnterPassword.equals(newPassword)) {
				
					 checkPassword=checkPassword(newEnterPassword);
					
						if(checkPassword==true){
								System.out.println("Valid Password");
								System.out.println(" Password is "+newEnterPassword);
								staffList.get(i).setPassword(newEnterPassword);
								
								tryAgain.equals("N");
							}
							else {
								System.out.println("Invalid Password");
								System.out.println("Please Check Through These Critiral");
								System.out.println("1)Please Ensure The Password Format Is CORRECT");
								System.out.println("2)Please Ensure The Password is at least 7 characters long");
							}
				}
					else {
					System.out.println("Invalid Password");
					System.out.println("Passwords Entered do not match");
					System.out.println("Do You Want To Try Again?(Press Y)");
					tryAgain=scanner.nextLine();
					tryAgain=tryAgain.toUpperCase();
					if(checkPassword==true) {
						tryAgain="N";
					}
				}
					
				}while(tryAgain.equals("Y"));	
					
			
				}
			}
		}
		
		        
		else {
			System.out.println("Format Invalid");
		}
		System.out.print("Do You Want To Modify Password Again?: ");
		 getChoice=scanner.nextLine();
		 getChoice=getChoice.toUpperCase();
		 }while(getChoice.equals("Y"));
		
	    }
	
	}

	public static boolean checkPassword(String password){
		int getNumAlp=0;
		int getNumValue=0;
		int countPass=0;
		if(password.length()>6) {
			for(int i=0;i<password.length();i++){
				if(Character.isLetter(password.charAt(i))) {
					getNumAlp++;
				}
				else if(Character.isDigit(password.charAt(i))){
					getNumValue++;
				}
				else if(!Character.isLetter(password.charAt(i))&&!Character.isDigit(password.charAt(i))){
					countPass++;
				}		
			}
			
			if(getNumAlp>=1&&getNumValue>=1&&countPass>=1){
				return true;
			}
			else {
				return false;
			}
		}
	    else {
			return false;
	    }
	}
	
	public static void changeStatus(ArrayList<Staff> staffList,Staff staff) {
		String getChoice;
		String getID;
		String getFirstS;
		int getNum;
		String tryAgain;
		int countStaff=0;
		int invalid=0;
		Scanner scanner=new Scanner(System.in);
	
		
		do {
			invalid=0;
			System.out.print("Please Enter The ID of Staff:");
			getID=scanner.nextLine();
			getFirstS=getID.substring(0,1);
			getFirstS=getFirstS.toUpperCase();
			if(getFirstS.equals("S")) {
				getNum=Integer.parseInt(getID.substring(1,5));
				for(int i=0;i<staffList.size();i++) {
				
					if(staffList.get(i).getIdNo()==getNum) {
					
						System.out.println("Staff Name:"+staffList.get(i).getName());
						System.out.println("Staff ID:"+staffList.get(i).getIdNo());
						System.out.println("Staff Status:"+staffList.get(i).getStatus());
					
						if(staffList.get(i).getStatus().equals("Active")) {
							System.out.printf("-------------------------------------------\n","");
							System.out.printf("              Status Selection             \n","");
							System.out.printf("-------------------------------------------\n","");
							System.out.println("Change The Status To Resign (Press Y)?:");
							getChoice=scanner.nextLine();
							getChoice=getChoice.toUpperCase();
						
							if(getChoice.equals("Y")) {
								staffList.get(i).setStatus("Y");
								System.out.println("Status:"+staffList.get(i).getStatus());
								writeTextFile(staffList);
							}
						
						
						}
						else if(staffList.get(i).getStatus().equals("Deactivate")) {
							int selection=0;
							do {
								invalid=0;
								System.out.printf("%-10s-----------------------------------\n","");
								System.out.printf("%-10s      Status Selection             \n","");
								System.out.printf("%-10s-----------------------------------\n","");
								System.out.printf("%-10s|1).Active                        |\n","");
								System.out.printf("%-10s|2).Resign                        |\n","");
								System.out.printf("%-10s|0).Quit                          |\n","");
								System.out.printf("%-10s-----------------------------------\n","");
								System.out.print("Enter Choice:");
								try {
									selection=scanner.nextInt();
							        scanner.nextLine();
								}
								catch(InputMismatchException e) {
									scanner.nextLine();
									invalid=1;
								}
						        
						        
								if(selection==1) {
									staffList.get(i).setStatus("Activate");
									System.out.println("Status:"+staffList.get(i).getStatus());
									writeTextFile(staffList);
								}
								if(selection==2) {
									staffList.get(i).setStatus("Resign");
									System.out.println("Status:"+staffList.get(i).getStatus());
									writeTextFile(staffList);
								}
								else if(selection==0){
									System.out.println("You have quit");
								}
								else {
									invalid=1;
									System.out.println("Invalid Choice. Please try again");
								}
							}while(invalid==1);
							
						
						}
					}
					else {
						countStaff++;
					}
			
				}
			}
			else {
				System.out.println("Format Invalid");
			}
			if(countStaff!=staffList.size()-1) {
				System.out.println("Staff ID Not Found");
			}
			System.out.print("Do You Want To Modify Status Again?: ");
			getChoice=scanner.nextLine();
			scanner.nextLine();
			getChoice=getChoice.toUpperCase();
			if(getChoice.equals("Y"))
			{		
				countStaff=0;
			}
		}while(getChoice.equals("Y"));
	}
	
	public static void changePosition(ArrayList<Staff> staffList) {
		Scanner scanner=new Scanner(System.in);
		String getID;
		String getFirstS;
		String getChoice;
		int getNum;
		int countStaff=0;
		do {
		System.out.print("Please Enter The ID of Staff:");
		getID=scanner.nextLine();
		 getFirstS=getID.substring(0,1);
		getFirstS=getFirstS.toUpperCase();
		
		if(getFirstS.equals("S")) {
			 getNum=Integer.parseInt(getID.substring(1,5));
			 for(int i=0;i<staffList.size();i++) {
					
					if(staffList.get(i).getIdNo()==getNum) {
						System.out.println("Staff Name:"+staffList.get(i).getName());
						System.out.println("Staff ID:"+staffList.get(i).getIdNo());
						System.out.println("Staff Position:"+staffList.get(i).getPosition());
						System.out.println();
						System.out.println("Change Position");
						System.out.println("Manager Position:(Press X)");
						System.out.println("Executive Position:(Press Y)");
						System.out.println("Cashier Position:(Press Z)");
						
						System.out.print("Change The Position:");
						getChoice=scanner.nextLine();
						getChoice=getChoice.toUpperCase();
						
						if(getChoice.equals("X")||getChoice.equals("Y")||getChoice.equals("Z")) {
							
							staffList.get(i).setPosition(getChoice);
							System.out.println("Status:"+staffList.get(i).getPosition());
							writeTextFile(staffList);
						}
						
						
					}
					
					else {
				 countStaff++;
					 }
					
				}
				 
				 	
				
		}
			else {
					System.out.println("Format Invalid");
			}
				if(countStaff!=staffList.size()-1) {
					System.out.println("Staff ID Not Found");
				}
				System.out.print("Do You Want To Modify Position Again?: ");
				 getChoice=scanner.nextLine();
				 getChoice=getChoice.toUpperCase();
				if(getChoice.equals("Y"))
				{		 countStaff=0;
				}
				}while(getChoice.equals("Y"));
	
	}
	
	public static void managerReport(ArrayList<Receipt> receiptList) {
		Member getName;
		String getStatus;
		int totalCountCash=0;
		int totalCountCreditCard=0;
		int totalCountTnG=0;
		System.out.printf("------------------------------------------------------------------------\n");
		System.out.printf("|                       Payment Method Report                          |\n");
		System.out.printf("------------------------------------------------------------------------\n");
		for(int i=0;i<receiptList.size();i++) {
		
		if(receiptList.get(i).getPayment().getPaymentMethod().equalsIgnoreCase("Cash")) {
			totalCountCash++;
		
		}
		else if(receiptList.get(i).getPayment().getPaymentMethod().equalsIgnoreCase("Credit Card")) {
			totalCountCreditCard++;
		}
		else if(receiptList.get(i).getPayment().getPaymentMethod().equalsIgnoreCase("Touch N Go E-Wallet")) {
			totalCountTnG++;
		}
		
		}
		System.out.printf("|Total Payment By Cash:%d                                               |\n",totalCountCash);
		System.out.printf("|                                                                      |\n");
		System.out.printf("|Total Payment By Credit Card:%d                                        |\n",totalCountCreditCard);
		System.out.printf("|                                                                      |\n");	
		System.out.printf("|Total Payment By TnG:%d                                                |\n",totalCountTnG);
		System.out.println("------------------------------------------------------------------------");		
	}
	
	public static boolean valiName(String name) {
		int countName=0;
		boolean nameresult;	
	
		for(int j=0;j<name.length();j++) {
		
		
			if(Character.isDigit(name.charAt(j))) {
				countName++;
			}
			else if(!Character.isLetter(name.charAt(j))&&!Character.isWhitespace(name.charAt(j))){
         		countName++;
         	}
		}
		if(countName!=0) {
			
			return false;
		}
		else {
			
			return true;
		}
	}

	public static boolean checkIC(String ic) {
		int countIC=0;
		int receiveMonth=0;
		int receiveDay=0;
		int receiveYear=0;
		boolean getMonth=true;
		boolean getDay=true;
		boolean getYear=true;
	
		if(ic.length()==12) {

					receiveMonth=Integer.parseInt(ic.substring(2,4));
					receiveDay=Integer.parseInt(ic.substring(4,6));
					receiveYear=Integer.parseInt(ic.substring(0,2));
					  if(receiveMonth>12){
						 
					     getMonth=false; 	
					     return false;
				  }
					  else if((receiveMonth==1||receiveMonth==3||receiveMonth==5||receiveMonth==7||receiveMonth==8||receiveMonth==10||receiveMonth==12)&&(receiveDay>31)) {	//day
						  getDay=false;
						 return false;
					  }
					  else if((receiveMonth==4||receiveMonth==6||receiveMonth==9||receiveMonth==11)&&receiveDay>30) {
						  getDay=false; 
						  return false;
					  }
					  else if((receiveMonth==2)&&receiveYear%4==0&&receiveDay>29) {
						  getDay=false; 
						  return false;
					  }
					  else if((receiveMonth==2)&&receiveYear%4!=0&&receiveDay>28) {
						  getDay=false; 
						  return false;
					  }
					
					  else {
						  getMonth=true;
						  getDay=true;
						  return true;
					  }
				
				}	
		else {
			return false;
		}
		
		
	}
	//CONTACT NUMBER VALIDATION
	public static String contactNoValidation(String contactNo) {
			Scanner scanner = new Scanner(System.in);
			//Pattern pattern = Pattern.compile("(0)?[7-9][0-9]{10/11}");
			
			int sumOfDigit = 0, sumOfLetter = 0;
			
			for(int i = 0; i < contactNo.length(); i++) {
				if(Character.isDigit(i)) {
					sumOfDigit++;
				}
				else if(Character.isLetter(i)) {
					sumOfLetter++;
				}
				else {
					
				}
			}
			
			while(sumOfDigit != 10 && sumOfDigit != 11 && sumOfLetter > 0) {
				System.out.println("Invalid Contact Number.");
				System.out.print("Kindly re-enter (only digits are allowed): ");
				contactNo = scanner.nextLine();
				scanner.nextLine();
			}
			
			return contactNo;
		}

	public static void writeTextFile(ArrayList<Staff> staffList) {
		File file=new File("src\\staff.txt");
		FileWriter fw=null;
		try {
			fw=new FileWriter(file);
			for(int i=0;i<staffList.size();i++) {
				fw.write(staffList.get(i).getName() + "," + staffList.get(i).getIcNo() + "," + staffList.get(i).getBirthDate() + "," + staffList.get(i).getContactNum() + "," + staffList.get(i).getStaffID() + "," + staffList.get(i).getIdNo() + "," + staffList.get(i).getPassword() + "," + staffList.get(i).getPosition() + "," + staffList.get(i).getSalary() + "," + staffList.get(i).getJoinDate().toString() + "," + staffList.get(i).getStatus() + "\n");
			
			}
			fw.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}



