import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	    String getConfirm;
	   
		
		//Birthday and generated add time and date
		//Enter Name
		
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
                Staff pass1=new Staff(staffPassword);
                Staff pass2=new Staff(valiPassword);
                System.out.println("Entered First Password :"+pass1);
                System.out.println("Entered Second Password :"+pass2);
                System.out.println(pass1.equals(pass2));
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
		    		try {
		    			Staff newStaff=new Staff(staffName,icNo,birthDate, staffID,idNo,valiPassword,staffPosition,salary,joinDate,status);
		    		//
		    			
		    			FileWriter writer=new FileWriter("src\\staff.txt");
		    			writer.append(newStaff.appendStaff());
		    		}
		    		catch(IOException e) {
		    			e.printStackTrace();
		    		}
		    	}
		    	else {
					System.out.print("Staff Is Not Added");
		    	}
				
			System.out.print("Do You Want To Continue Add Staff ?(Y=Yes) :");
			 getConfirm=scanner.nextLine();
			 getConfirm=addStaff.toUpperCase();
	    	
	    	
	    	System.out.println("The Number Of Staff Added is "+countEnter);
	    		
			}while(addStaff.equals(("Y")));
		
}


public static boolean valiName(String name) {
		int countName=0;
		boolean nameresult;
		for(int j=0;j<name.length();j++) {
			char seeChaName=name.charAt(j);
			if(Character.isDigit(name.charAt(j))) {
				countName++;
			}
			else if(!Character.isLetter(seeChaName)&&!Character.isWhitespace(seeChaName)){
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
	
}

