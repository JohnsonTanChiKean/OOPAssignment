import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.*;

public class Claris {
	//USER OPTION
	public static void menu(ArrayList<Member> memberList, Staff staff) throws IOException{
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("%-10s===================================\n", "");
		System.out.printf("%-10sWELCOME TO MEMBER MANAGEMENT MODULE\n", "");
		System.out.printf("%-10s===================================\n", "");
		
		int valid = 1;
		char yesNo = 'Y';
		
		do {
			do {
				int userChoice = displayMenu();
				switch(userChoice) {
				case 1:
					addMember(memberList, staff);
					break;
				case 2:
					modifyMember(memberList);
					break;
				case 3:
					searchMember(memberList);
					break;
				case 4:	
					viewMember(memberList);
					break;
				case 5:
					displayReport();
				case 0:
					System.out.println("You are properly quit!");
					break;
				default:
					System.out.print("Invalid Choice. Kindly re-enter again (0-4): ");
					userChoice = scanner.nextInt();
					break;
				}
			}while(valid != 1);
			
			System.out.println("Move forward to another function (Y = Yes)? ");
			yesNo = scanner.next().charAt(0);
			
		}while(Character.toUpperCase(yesNo) == 'Y');
	}
	
	//DISPLAY MAIN MENU
	public static int displayMenu() {
		Scanner scanner = new Scanner(System.in);
		
		int menuChoice = -1;
		int invalidInput = 0;
		
		do {
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s|      Member Main Menu      |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s|1.       Add Member         |\n", "");
			System.out.printf("%-10s|2.  Modify Member Details   |\n", "");
			System.out.printf("%-10s|3.      Search Member       |\n", "");
			System.out.printf("%-10s|4.     View all Member      |\n", "");
			System.out.printf("%-10s|0.          Quit            |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			
			System.out.print("Enter your choice >> ");
			
			invalidInput = 0;
			
			try {
				menuChoice = scanner.nextInt();
				scanner.nextLine();
			}
			catch(InputMismatchException e) {
				invalidInput = -1;
				scanner.nextLine();
			}
			
			if(menuChoice < 0 || menuChoice > 4) {
				invalidInput = -1;
			}
			
			if(invalidInput == -1) {
				System.out.println("Invalid Choice. Please Try Again.");
			}
		}while(invalidInput == -1);
		
		return menuChoice;
	}
	
	//1. ADD MEMBER
	//name, icNo, birthDate
	//memberID --> auto-generated
	//idNo, membership, registrationDate --> GetDate, memberCount
	public static void addMember(ArrayList<Member> memberList, Staff staff) throws IOException{
		Scanner scanner = new Scanner(System.in);
		
		char addYesNo = 'N';
		char contAdd = 'N';
		String newMbrIcNo = "";
		GetDate getDate = new GetDate();
		
		File file = new File("member.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		
		do {
			contAdd = 'N';
			
			System.out.println("Register at (date and time): " + getDate);
			
			System.out.print("Enter Member's Name: ");
			String newMbrName = scanner.nextLine();
			scanner.nextLine();
			newMbrName = nameValidation(newMbrName);
			
			System.out.print("Enter Member's IC Number: ");
			newMbrIcNo = scanner.nextLine();
			scanner.nextLine();
			newMbrIcNo = icNoValidation(newMbrIcNo);
			
			System.out.print("Enter Member's Contact Number: ");
			String newContactNum = scanner.nextLine();
			scanner.nextLine();
			newContactNum = contactNoValidation(newContactNum);
			
			String newMbrshipType = membershipType();
			
			String newActivePeriod = mbrActivePeriod(newMbrshipType);
			
			System.out.print("Do you want to add this new member (Y = Yes/N = No)?: ");
			addYesNo = scanner.next().toUpperCase().charAt(0);
			scanner.nextLine();
			
			if(addYesNo == 'Y') {
				//String name, String icNo, String birthDate, String contactNum, String memberID, int idNo, String membership, String activePeriod, String mbrStatus, Staff registeredBy, String registrationDate
				Member m = new Member(newMbrName, newMbrIcNo, newMbrshipType, staff);
				scanner.nextLine();
				memberList.add(m);
				pw.println(m);
			}
			
			System.out.print("Do you want to add more new member (Y = Yes/N = No)?: ");
			contAdd = scanner.next().toUpperCase().charAt(0);
			scanner.nextLine();
		} while(Character.toUpperCase(contAdd) == 'Y');
	}
	
	//2. MODIFY MEMBER DETAILS
	//name, membership
	public static void modifyMember(ArrayList<Member> memberList) {
		Scanner scanner =  new Scanner(System.in);
		
		char contModify = 'N';
		int choice;
		int found = 0;
		int invalid = 0;
		int index = -1;
		char modify = 'N';
		
		String tempName, tempContactNum, tempMbrshipType;
		
		do {
			contModify = 'N';
			invalid = 0;
			
			System.out.print("Enter Member ID for modification: ");
			String mbrId = scanner.nextLine().toUpperCase();
			scanner.nextLine();
			
			if(mbrId.charAt(0) != 'M') {
				invalid = -1;
			}
			else if(mbrId.length() != 5) {
				invalid = -1;
			}
			
			if(invalid != -1) {
				mbrId = mbrId.substring(1, 5);
				for(int i = 0; i < mbrId.length(); i++) {
					if(!Character.isDigit(mbrId.charAt(i))) {
						invalid = -1;
					}
				}
			}
			
			if(invalid != -1) {
				index = checkMember(memberList, Integer.parseInt(mbrId));
			}
			
			if(invalid != -1 && index != -1) {
				tempName = memberList.get(index).getName();
				tempMbrshipType = memberList.get(index).getMembership();
				
				choice = modifyDetailMenuM();
				scanner.nextLine();
				
				switch(choice) {
				case 1:
					System.out.print("Enter Member NEW Name: ");
					tempName = scanner.nextLine();
					scanner.nextLine();
					tempName = nameValidation(tempName);
					break;
				case 2:
					tempMbrshipType = membershipType();
					break;
				case 3:
					System.out.print("Enter Member NEW Contact Number: ");
					tempContactNum = scanner.nextLine();
					scanner.nextLine();
					tempContactNum = contactNoValidation(tempContactNum);
					break;
				case 4:
					System.out.print("Enter Member NEW Name: ");
					tempName = scanner.nextLine();
					scanner.nextLine();
					tempName = nameValidation(tempName);
					
					System.out.print("Enter Member NEW Contact Number: ");
					tempContactNum = scanner.nextLine();
					scanner.nextLine();
					tempContactNum = contactNoValidation(tempContactNum);
					
					tempMbrshipType = membershipType();
					break;
				case 0:
					System.out.println("You Are Quit.");
					break;
				}
				
				if(choice != 0) {
					System.out.print("Are you sure to Modify? (Y = Yes): ");
					modify = Character.toUpperCase(scanner.next().charAt(0));
					scanner.nextLine();
					
					if(modify == 'Y') {
						memberList.get(index).setName(tempName);
						memberList.get(index).setMembership(tempMbrshipType);
					}
					else {
						System.out.println("Modification Cancelled.");
					}
				}
			}
			
			if(invalid == -1) {
				System.out.print("Invalid Member ID.");
			}
			
			System.out.println("Would you like to modify another record (Y = Yes)?: ");
			contModify = scanner.next().charAt(0);
			
		} while(Character.toUpperCase(contModify) == 'Y');
	}
	
	//3. SEARCH MEMBER
	//name, memberID, membership
	public static void searchMember(ArrayList<Member> memberList) {
		Scanner scanner = new Scanner(System.in);
		
		char contSearch = 'N';
		int selectionS = -1;
		int invalidInput = 0;
		
		do {
			contSearch = 'N';
			 do {
				 invalidInput = 0;
					
					System.out.printf("%-10s------------------------------\n", "");
					System.out.printf("%-10s|     Search Member Menu     |\n", "");
					System.out.printf("%-10s------------------------------\n", "");
					System.out.printf("%-10s|1. Member ID                |\n", "");
					System.out.printf("%-10s|2. Name                     |\n", "");
					System.out.printf("%-10s|3. Identification Number    |\n", "");
					System.out.printf("%-10s|4. Contact Number           |\n", "");
					System.out.printf("%-10s|5. Membership Type          |\n", "");
					System.out.printf("%-10s|0. Cancel                   |\n", "");
					System.out.printf("%-10s------------------------------\n", "");
					
					System.out.printf("  Please enter your choice: ");
					
					try {
						selectionS = scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e) {
						invalidInput = -1;
						scanner.nextLine();
					}
					
					if(selectionS < 0 || selectionS > 4) {
						invalidInput = -1;
					}
					
					if(invalidInput == -1) {
						System.out.println("Invalid Choice. Please Try Again.");
					}
			 }while(invalidInput == -1);
			
			switch(selectionS) {
			case 1:
				System.out.print("Enter Member ID to Search: ");
				String mbrID = scanner.nextLine();
				break;
			case 2:
				System.out.print("Enter Name to Search: ");
				String mbrName = scanner.nextLine();
				break;
			case 3:
				System.out.print("Enter Identification Number to Search: ");
				String mbrIcNo = scanner.nextLine();
				break;
			case 4:
				System.out.print("Enter Contact Number to Search: ");
				String mbrContactNo = scanner.nextLine();
				break;
			case 5:
				System.out.print("Enter Membership Type to Search: ");
				String mbrshipType = scanner.nextLine();
				break;
			case 0:
				System.out.println("Searching Cancelled.");
			}
		} while(contSearch == 'Y' && selectionS != 0);
	}
	
	//4. VIEW ALL MEMBER
	public static void viewMember(ArrayList<Member> memberList) {
		System.out.printf("%-10s-------------------------------------------------------------------------------------------\n");
		System.out.printf("%-10s|                                     CURRENT MEMBERS                                     |\n");
		System.out.printf("%-10s|-----------------------------------------------------------------------------------------|\n");
		
		for(int i = 0; i < memberList.size(); i++) {
			System.out.printf("%-10s| %-17s%-15s%-17s%-15s%-15s%-21s%-13s%-15s%-15s%-15s%-15s \n",
					memberList.get(i).getFullMemID(), memberList.get(i).getName(), memberList.get(i).getIcNo(),
					memberList.get(i).getBirthDate(), memberList.get(i).getContactNum(), memberList.get(i).getMembership(),
					memberList.get(i).getActivePeriod(), memberList.get(i).getMbrStatus(), memberList.get(i),
					memberList.get(i).getRegisteredBy(), memberList.get(i).getRegisteredBy().toString() );
		}
		
		System.out.printf("%-10s-------------------------------------------------------------------------------------------\n");
	}
	
	//CHECK MEMBER
	public static int checkMember(ArrayList<Member> memberList, int memIDNo) {
		
		int foundMember = 0, index = 0;
		
		for(int i = 0; i < memberList.size(); i++) {
			if(memIDNo == memberList.get(i).getIdNo()) {
				foundMember = 1;
				index = i;
			}
		}
		
		if(foundMember == 1) {
			return index;
		}
		else {
			return -1;
		}
	}
	
	//MEMBERSHIP TYPE
	public static String membershipType() {
		Scanner scanner = new Scanner(System.in);
		
		int selectMbrShip;
		String mbrShipType = "";
		int valid = 1;
		
		do {
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s|       Membership Type      |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s|1. Silver                   |\n", "");
			System.out.printf("%-10s|2. Gold                     |\n", "");
			System.out.printf("%-10s|3. Platinum                 |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("  Enter Membership Type: ");
			selectMbrShip = scanner.nextInt();
			scanner.nextLine();
			
			if(selectMbrShip == 1) {
				mbrShipType = "Silver";
			}
			else if(selectMbrShip == 2){
				mbrShipType = "Gold";
			}
			else if(selectMbrShip == 3) {
				mbrShipType = "Platinum";
			}
			else {
				System.out.println();
				System.out.println("Invalid Membership Type. Press Enter to Try Again.");
				scanner.nextLine();
				valid = 0;
			}
			
		} while(valid == 0);
		
		return mbrShipType;
	}
	
	//MEMBERSHIP ACTIVE PERIOD
	public static String mbrActivePeriod(String mbrShipType) {
		String activePeriod;
		
		if(mbrShipType == "Silver") {
			activePeriod = "3 months";
		}
		else if(mbrShipType == "Gold") {
			activePeriod = "6 months";
		}
		else {
			activePeriod = "12 months";
		}
		
		return activePeriod;
	}
	
	//MEMBERSHIP ACTIVE PERIOD
	public static void mbrStatus() {
		
	}
	
	//MODIFY MEMBER DETAIL(S) MENU
	public static int modifyDetailMenuM() {
		Scanner scanner = new Scanner(System.in);
		
		int selectionM = -1;
		int invalidInput = 0;
		
		do {
			invalidInput = 0;
			
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s|Modify Member Detail(s) Menu|\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			System.out.printf("%-10s|1. Name                     |\n", "");
			System.out.printf("%-10s|2. Contact Number           |\n", "");
			System.out.printf("%-10s|3. Membership               |\n", "");
			System.out.printf("%-10s|4. All Details              |\n", "");
			System.out.printf("%-10s|0. Quit                     |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			
			System.out.printf("  Please enter field to modify: ");
			
			try {
				selectionM = scanner.nextInt();
				scanner.nextLine();
			}
			catch(InputMismatchException e) {
				invalidInput = -1;
				scanner.nextLine();
			}
			
			if(selectionM < 0 || selectionM > 4) {
				invalidInput = -1;
			}
			
			if(invalidInput == -1) {
				System.out.println("Invalid Choice. Please Try Again.");
			}
		}while(invalidInput == -1);
		
		return selectionM;
	}
	
	//5. DISPLAY REPORT
	public static void displayReport() {
		
	}
	
	//VALIDATIONS
	//NAME VALIDATION
	//ZhiHang.java
	public static String nameValidation(String name) {
		Scanner scanner = new Scanner(System.in);
		
		int digit = 0;
		
		for(int i = 0; i < name.length(); i++) {
			if(Character.isDigit(i)) {
				digit++;
			}
			else {
				System.out.println("Invalid Character.");
			}
		}
		
		while(digit > 0) {
			System.out.println("Invalid Name.");
			System.out.print("Kindly re-enter (only letters are allowed): ");
			name = scanner.nextLine();
			scanner.nextLine();
		}
		
		return name;
	}
	
	//IDENTIFICATION NUMBER VALIDATION
	//ZhiHang.java
	public static String icNoValidation(String icNo) {
		Scanner scanner = new Scanner(System.in);
		
		int sumOfDigit = 0, sumOfLetter = 0;
		
		for(int i = 0; i < icNo.length(); i++) {
			if(Character.isDigit(i)) {
				sumOfDigit++;
			}
			else if(Character.isLetter(i)) {
				sumOfLetter++;
			}
			else {
				System.out.println("Invalid Character.");
			}
		}
		
		while(sumOfDigit != 12 || sumOfLetter > 0) {
			System.out.println("Invalid Identification Number.");
			System.out.print("Kindly re-enter (12 digits): ");
			icNo = scanner.nextLine();
			scanner.nextLine();
		}
		
		return icNo;
	}
	
	//CONTACT NUMBER VALIDATION
	public static String contactNoValidation(String contactNo) {
		Scanner scanner = new Scanner(System.in);
		Pattern pattern = Pattern.compile("(0)?[7-9][0-9]{10/11}");
		
		int sumOfDigit = 0, sumOfLetter = 0;
		
		for(int i = 0; i < contactNo.length(); i++) {
			if(Character.isDigit(i)) {
				sumOfDigit++;
			}
			else if(Character.isLetter(i)) {
				sumOfLetter++;
			}
			else {
				System.out.println("Invalid Character.");
			}
		}
		
		while(sumOfDigit != 10 || sumOfDigit != 11 || sumOfLetter > 0) {
			System.out.println("Invalid Contact Number.");
			System.out.print("Kindly re-enter (only digits are allowed): ");
			contactNo = scanner.nextLine();
			scanner.nextLine();
		}
		
		return contactNo;
	}

}
