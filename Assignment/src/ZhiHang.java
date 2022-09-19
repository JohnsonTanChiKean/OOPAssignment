import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.*;


public class ZhiHang {
	public static void login(ArrayList<Staff> staffList, ArrayList<Product> productList, ArrayList<Member> memberList, ArrayList<Payment> paymentList, ArrayList<Receipt> receiptList, BankAccount bankAccount, ArrayList<Refund> refundList) {
		int countPassWrong=5;
		int found=0, loop=0;
		char choice;
		Scanner scanner=new Scanner(System.in);
		int idNo=0;
		Staff staff;
		String staffID;
		int getNumStaff=0;
		String password;
		
		do {
			System.out.println("Welcome to Connect POS System");
			
			do {
				System.out.print("Enter staff ID: ");
			 staffID=scanner.next();
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
				 password=scanner.next();
				scanner.nextLine();
				
				for(int i=0; i<staffList.size(); i++) {
					if(idNo==staffList.get(i).getIdNo()&&password.equals(staffList.get(i).getPassword())&&(!staffList.get(i).getStatus().equals("Deactivate")&&!staffList.get(i).getStatus().equals("Resign"))) {
						
						System.out.println("Successful login");
						System.out.println("Welcome "+staffList.get(i).getName());
						if(!staffList.get(i).getStatus().equals("Deactivate")&&idNo==staffList.get(i).getIdNo()&&password.equals(staffList.get(i).getPassword())) {
							countPassWrong=5;
						}
						mainMenu(staffList, productList, memberList, staffList.get(i), receiptList, paymentList, bankAccount, refundList);
					}
					if(idNo==staffList.get(i).getIdNo()&&password.equals(staffList.get(i).getPassword())&&!staffList.get(i).getStatus().equals("Resign")) {
						getNumStaff=i;
					}
					
					else {
			    if(idNo==staffList.get(i).getIdNo()) {
				countPassWrong--;
				getNumStaff=i;
				}
			
			}
				}
				if(!password.equals(staffList.get(getNumStaff).getPassword())) {
				System.out.println("StaffID/Password Invalid ");
				}
				if(countPassWrong==0&&!(staffList.get(getNumStaff).getName().equals("admin"))) {
					System.out.println("StaffID "+staffID+" is Deactivate");
					String deactivate="Deactivate";
					staffList.get(getNumStaff).setStatus(deactivate);
					
					
				}
				if(staffList.get(getNumStaff).getStatus().equals("Deactivate")) {
					System.out.println("5 Time Login Reached");
					System.out.println("StaffID "+staffID+" is Deactivate");
					System.out.println("Login Is Denied");
				}
			}
			if(staffList.get(getNumStaff).getStatus().equals("Resign")) {
				System.out.println("StaffID "+staffID+" is Resigned");
				System.out.println("Login Is Denied");
			}
			
			
		}while(loop==0);
		
	}
	
	
	
	public static void mainMenu(ArrayList<Staff> staffList, ArrayList<Product> productList, ArrayList<Member> memberList, Staff staff, ArrayList<Receipt> receiptList, ArrayList<Payment> paymentList, BankAccount bankAccount, ArrayList<Refund> refundList) {
		Scanner scanner=new Scanner(System.in);
		int choice=0, loop=0;
		char quitChoice;
		System.out.println(staff.getPosition());
	
		if(staff.getPosition().equals("cashier")) {
		do {
		
			choice=0;
			loop=0;
			System.out.printf("%-10s-------------------------------------\n", "");
			System.out.printf("%-10s|              Main Menu            |\n", "");
			System.out.printf("%-10s-------------------------------------\n", "");
			System.out.printf("%-10s|    1. Order and Payment Module    |\n", "");
			System.out.printf("%-10s|    2. Member Management Module    |\n", "");
			System.out.printf("%-10s|    3.      Refund Module          |\n", "");
			System.out.printf("%-10s|    0.          Logout             |\n", "");
			System.out.printf("%-10s-------------------------------------\n", "");

			System.out.print("Select Action to perform: ");
			choice=scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1: ChiKean.orderMenu(staffList,productList,memberList, paymentList, staff, receiptList, bankAccount); loop=1; break;
			case 2: ChiKean.report(productList, receiptList, refundList); loop=1; break;
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
		else if(staff.getPosition().equals("executive")) {
			do{
			choice=0;
			loop=0;
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s|          Main Menu         |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s|1. Place Order              |\n", "");
			System.out.printf("%-10s|2. On-Hold Payment List     |\n", "");
			System.out.printf("%-10s|3. Edit Password            |\n", "");
			System.out.printf("%-10s|4. Add Staff                |\n", "");
			System.out.printf("%-10s|5. Change Position          |\n", "");
			System.out.printf("%-10s|6. Report                   |\n", "");
			System.out.printf("%-10s|7. Change Name              |\n", "");
			System.out.printf("%-10s|8. Change Contact Number    |\n", "");
			System.out.printf("%-10s|0. Logout                   |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			
			System.out.print("Select Action to perform: ");
			choice=scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1: ChiKean.placeOrder(staffList,productList,memberList, paymentList, staff, receiptList, bankAccount); loop=1; break;
			case 2: ChiKean.onHoldPayment(paymentList, memberList, staff, productList, receiptList, bankAccount); loop=1; break;
			case 3: EditPassword( staffList,staff);break;
			case 4: AddStaff( staffList);break;
			case 5: ChangeStatus(staffList);break;
			case 6: ChangePosition(staffList);break;
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
		else if(staff.getPosition().equals("manager")||staff.getName().equals("admin")) {
			do{
			choice=0;
			loop=0;
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s|          Main Menu         |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s|1. Place Order              |\n", "");
			System.out.printf("%-10s|2. On-Hold Payment List     |\n", "");
			System.out.printf("%-10s|3. Edit Password            |\n", "");
			System.out.printf("%-10s|4. Add Staff                |\n", "");
			System.out.printf("%-10s|5. Change Position          |\n", "");
			System.out.printf("%-10s|6. Report                   |\n", "");
			System.out.printf("%-10s|7. Change Name              |\n", "");
			System.out.printf("%-10s|8. Change Contact Number    |\n", "");
			System.out.printf("%-10s|9. Change Status            |\n", "");
			System.out.printf("%-10s|0. Logout                   |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			
			System.out.print("Select Action to perform: ");
			choice=scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1: ChiKean.placeOrder(staffList,productList,memberList, paymentList, staff, receiptList, bankAccount); loop=1; break;
			case 2: ChiKean.onHoldPayment(paymentList, memberList, staff, productList, receiptList, bankAccount); loop=1; break;
			case 3: EditPassword( staffList,staff);break;
			case 4: AddStaff( staffList);break;
			case 5: ChangePosition(staffList);break;
			case 6: ManagerReport(paymentList);break;
			case 7:	break;
			case 8:break;
			case 9:ChangeStatus(staffList);break;
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
	
	public static void staffMenu() {
		Scanner scanner=new Scanner(System.in);
		System.out.printf("%-10s==================================\n", "");
		System.out.printf("%-10sWELCOME TO STAFF MANAGEMENT MODULE\n", "");
		System.out.printf("%-10s==================================\n\n", "");
		
		System.out.printf("%-10s------------------------------\n", "");
		System.out.printf("%-10s|Staff Management Module Menu|\n", "");
		System.out.printf("%-10s------------------------------\n", "");
		System.out.printf("%-10s|1. Place Order              |\n", "");
		System.out.printf("%-10s|2. On-Hold Payment List     |\n", "");
		System.out.printf("%-10s|3. Edit Password            |\n", "");
		System.out.printf("%-10s|4. Add Staff                |\n", "");
		System.out.printf("%-10s|5. Change Position          |\n", "");
		System.out.printf("%-10s|6. Report                   |\n", "");
		System.out.printf("%-10s|7. Change Name              |\n", "");
		System.out.printf("%-10s|8. Change Contact Number    |\n", "");
		System.out.printf("%-10s|9. Change Status            |\n", "");
		System.out.printf("%-10s|0. Logout                   |\n", "");
		System.out.printf("%-10s------------------------------\n", "");
	}
	/*public static void StaffTable(ArrayList<Staff> staffList,Staff staff) {
		int chooseStaff=2;
		Scanner scanner=new Scanner(System.in);
		do {
		System.out.println("Welcome To Staff Section");
		System.out.printf("1)Add Staff\n2)Modify Staff Detail\n3)Sale Report\n4)Exit\n");
		System.out.print("Enter Selection 1-3:");
		try {
		chooseStaff=scanner.nextInt();
		if(chooseStaff!=1||chooseStaff!=2||chooseStaff!=3||chooseStaff!=4) {
			throw new Exception("Option is Not Valid\nPlease Enter Again:");
		}
		}
		catch(Exception exe) {
			//Put in the method
			if(chooseStaff==1) {AddStaff(staffList);}
			else if(chooseStaff==2) {StaffModification(staffList,staff);}
			else if(chooseStaff==3) {}
			else if(chooseStaff==4) {}
		}
		if(staff.getPosition().equals("cashier")&&(chooseStaff!=2)) {
			System.out.print("Cashier Can Only Modify Details");
		}
	}while(staff.getPosition().equals("cashier")&&(chooseStaff!=2));
		
	}*/
	public static void AddStaff(ArrayList<Staff> staffList) {
		
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
		    		Staff newStaff=new Staff(staffName,icNo,valiPassword,staffPosition);
		    		staffList.add(newStaff);
		    		countEnter++;
		    		for(int i=0;i<staffList.size();i++) {
		    			System.out.println(staffList.get(i).getName());
		    			System.out.println(staffList.get(i).getBirthDate());
		    			
		    		}
		    		/* try {
		    			
		    		//
		    			appendStaff(Staff newStaff);
		    			FileWriter writer=new FileWriter("src\\staff.txt");
		    			writer.append(newStaff.appendStaff());
		    		}
		    		catch(IOException e) {
		    			e.printStackTrace();
		    		}  */
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
/*
public String appendStaff() {
String staffFile="";
		staffFile=String.format("%s,%s,%s,S,%s,%d,%s,%s,%.2f,%s,%s\n",getName(),getIcNo(),getBirthDate(),staffID,idNo,password,position,salary,joinDate,status);
		return staffFile;
	}
	*/

	public static void EditPassword(ArrayList<Staff> staffList,Staff staff) {

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
	
	public static void ChangeStatus(ArrayList<Staff> staffList) {
		String getChoice;
		String getID;
		String getFirstS;
		int getNum;
		String tryAgain;
		int countStaff=0;
		Scanner scanner=new Scanner(System.in);
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
					System.out.println("Staff Status:"+staffList.get(i).getStatus());
					System.out.println("Change The Status (Press Y)?:");
					getChoice=scanner.nextLine();
					getChoice=getChoice.toUpperCase();
					
					if(getChoice.equals("Y")) {
						staffList.get(i).setStatus("Y");
						System.out.println("Status:"+staffList.get(i).getStatus());
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
		 getChoice=getChoice.toUpperCase();
		if(getChoice.equals("Y"))
		{		 countStaff=0;
		}
		}while(getChoice.equals("Y"));
}
	
	public static void ChangePosition(ArrayList<Staff> staffList) {
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
	
	public static void ManagerReport(ArrayList<Payment> paymentList) {
		Member getName;
		String getStatus;
		int totalCountCash=0;
		int totalCountCreditCard=0;
		int totalCountTnG=0;
		System.out.printf("------------------------------------------------------------------------\n");
		System.out.printf("|                       Payment Method Report                          |\n");
		System.out.printf("------------------------------------------------------------------------\n");
		for(int i=0;i<paymentList.size();i++) {
		
		if(paymentList.get(i).getStatus().equals("Cash")) {
			totalCountCash++;
		
		}
		else if(paymentList.get(i).getStatus().equals("Credit Card")) {
			totalCountCreditCard++;
		}
		else if(paymentList.get(i).getStatus().equals("Touch N Go E-Wallet")) {
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

}



