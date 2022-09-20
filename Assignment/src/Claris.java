
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class Claris {
	//USER OPTION
	public static void menu(ArrayList<Member> memberList, Staff staff, ArrayList<Receipt> receiptList, ArrayList<Refund> refundList) {
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
					displayReceipt(receiptList);
					break;
				case 0:
					System.out.println("  You are properly quit!");
					break;
				default:
					System.out.print("  Invalid Choice. Kindly re-enter again (0-4): ");
					userChoice = scanner.nextInt();
					break;
				}
			}while(valid != 1);
			
			System.out.print("  Move forward to another function (Y = Yes)? ");
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
			System.out.printf("%-10s|                            |\n", "");
			System.out.printf("%-10s|1.       Add Member         |\n", "");
			System.out.printf("%-10s|2.  Modify Member Details   |\n", "");
			System.out.printf("%-10s|3.      Search Member       |\n", "");
			System.out.printf("%-10s|4.     View all Member      |\n", "");
			System.out.printf("%-10s|5.     Display Receipt      |\n", "");
			System.out.printf("%-10s|0.          Quit            |\n", "");
			System.out.printf("%-10s|                            |\n", "");
			System.out.printf("%-10s------------------------------\n", "");
			
			System.out.print("  Enter your Choice >> ");
			
			invalidInput = 0;
			
			try {
				menuChoice = scanner.nextInt();
				scanner.nextLine();
			}
			catch(InputMismatchException e) {
				invalidInput = -1;
				scanner.nextLine();
			}
			
			if(menuChoice < 0 || menuChoice > 5) {
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
	public static void addMember(ArrayList<Member> memberList, Staff staff) {
		Scanner scanner = new Scanner(System.in);
		
		int cancel = 0;
		char addYesNo = 'N';
		char contAdd = 'N';
		String newMbrIcNo = "";
		String newMbrName = "";
		String newContactNum = "";
		String newMbrshipType = "", newActivePeriod = "";
		
		GetDate getDate = new GetDate();
		boolean validName = false;
		boolean validIc = false;
		
		do {
			contAdd = 'N';
			cancel = 0;
			
			System.out.println("  Register at (date and time): " + getDate);
			
			System.out.print("  Enter Member's Name (Enter -1 to cancel): ");
			newMbrName = scanner.nextLine();
			if(!newMbrName.equals("-1")) {
				validName = ZhiHang.valiName(newMbrName);
				while(validName == false) {
					System.out.print("  Invalid Name. Please ensure that your name only has letters (Enter -1 to cancel): ");
					newMbrName = scanner.nextLine();
					if(!newMbrName.equals("-1")) {
						validName = ZhiHang.valiName(newMbrName);
					}
					else {
						cancel = -1;
						validName = true;
					}
				}
			}
			else {
				cancel = -1;
			}
			
			if(cancel != -1) {
				System.out.print("\n  Enter Member's IC Number (Enter -1 to cancel): ");
				newMbrIcNo = scanner.next();
				if(!newMbrIcNo.equals("-1")) {
					validIc = ZhiHang.checkIC(newMbrIcNo);
					scanner.nextLine();
					while(validIc == false) {
						System.out.println("  Invalid IC. Please ensure that you IC fulfilled conditions below:");
						System.out.println("  ================================");
						System.out.println("  1. Only has numbers.");
						System.out.println("  2. Has length of 12 digits.");
						System.out.println("  2. Has valid Day, Month and Year.");
						System.out.print("\n   Please Try Again (Enter -1 to cancel): ");
						newMbrIcNo = scanner.nextLine();
						if(!newMbrIcNo.equals("-1")) {
							validIc = ZhiHang.checkIC(newMbrIcNo);
						}
						else {
							cancel = -1;
							validIc = true;
						}
					}
				}
				else {
					cancel = -1;
				}
			}
//			newMbrIcNo = icNoValidation(newMbrIcNo);
			
			if(cancel != -1) {
				System.out.print("\n  Enter Member's Contact Number (Enter -1 to cancel): ");
				newContactNum = scanner.next();
				if(!newContactNum.equals("-1")) {
					newContactNum = contactNoValidation(newContactNum);
				}
				else {
					cancel = -1;
				}
			}
			
			if(cancel != -1) {
				newMbrshipType = membershipType();
				if(newMbrshipType.equals("-1")) {
					cancel = -1;
				}
			}
			
			if(cancel != -1) {
				newActivePeriod = mbrActivePeriod(newMbrshipType);
			}
			
			if(cancel != -1) {
				System.out.print("\n  Do you want to add this new member (Y = Yes)? ");
				addYesNo = scanner.next().toUpperCase().charAt(0);
				scanner.nextLine();
				
				if(addYesNo == 'Y') {
					//String name, String icNo, String birthDate, String contactNum, String memberID, int idNo, String membership, String activePeriod, String mbrStatus, Staff registeredBy, String registrationDate
					Member m = new Member(newMbrName, newMbrIcNo, newContactNum, newMbrshipType, newActivePeriod, staff);
					scanner.nextLine();
					memberList.add(m);
					textFile(memberList);
				}
			}
			
			System.out.print("  Do you want to add more new member (Y = Yes)? ");
			contAdd = scanner.next().toUpperCase().charAt(0);
			scanner.nextLine();
		} while(Character.toUpperCase(contAdd) == 'Y');
	}
	
	//2. MODIFY MEMBER DETAILS
	//name, membership
	public static void modifyMember(ArrayList<Member> memberList) {
		Scanner scanner =  new Scanner(System.in);
		
		int cancel = 0;
		int searchChoice = 0;
		char contModify = 'N';
		int choice;
		int found = 0;
		int invalid = 0;
		int index = -1;
		char modify = 'N';
		boolean validName = false;
		
		String tempName, tempContactNum, tempMbrshipType, tempStatus;
		String tempMbrType; //to compare with tempMbrshipType
		
		do {
			do {
				cancel = 0;
				searchChoice = 0;
				contModify = 'N';
				invalid = 0;
				index = -1;
				
				System.out.printf("%-10s-----------------\n", "");
				System.out.printf("%-10s| Search Member |\n", "");
				System.out.printf("%-10s-----------------\n", "");
				System.out.printf("%-10s|1. Member ID   |\n", "");
				System.out.printf("%-10s|2. Member IC   |\n", "");
				System.out.printf("%-10s-----------------\n", "");
				
				System.out.print("  Enter your Choice (Enter -1 to cancel): ");
				try {
					searchChoice = scanner.nextInt();
					scanner.nextLine();
				} catch(InputMismatchException e) {
					invalid = 1;
					scanner.nextLine();
				}
				
				if((searchChoice >= 1 && searchChoice <= 2) && invalid == 0) {
					switch(searchChoice) {
					case 1:
						System.out.print("  Enter Member ID for modification: ");
						String mbrId = scanner.nextLine().toUpperCase();
						
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
						break;
					case 2:
						index = searchIc(memberList);
						break;
					}
				}
				else if(searchChoice == -1) {
					cancel = -1;
				}
				
				if(index == -1) {
					System.out.println("  Member Not Found. Press Enter To Try Again.");
					scanner.nextLine();
				}
				
			}while(invalid == -1 || index == -1);
			
			if(invalid != -1 && index != -1 && cancel != -1) {
				System.out.printf("%-10s----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
				System.out.printf("%-10s|                                                                                                                                                                                                  |\n", "");
				System.out.printf("%-10s|   %-17s%-30s%-25s%-15s%-18s%-13s%-17s%-17s%-17s%-19s   |\n", "", "Member ID", "Name", "Identification Number", "Birth Date", "Contact Number", "Membership", "Active Period", "Member Status", "Registered By", "Registration Date");
				System.out.printf("%-10s|   %-17s%-30s%-25s%-15s%-18s%-13s%-17s%-17s%-17s%-19s   |\n", "", "---------", "----", "---------------------", "----------", "--------------", "----------", "-------------", "-------------", "-------------", "-----------------");
				System.out.printf("%-10s|   %-17s%-30s%-25s%-15s%-18s%-13s%-17s%-17s%-17s%-19s   |\n", "", memberList.get(index).getFullMemID(), memberList.get(index).getName(), memberList.get(index).getIcNo(), memberList.get(index).getBirthDate(),
						memberList.get(index).getContactNum(), memberList.get(index).getMembership(), memberList.get(index).getActivePeriod(), memberList.get(index).getMbrStatus(), memberList.get(index).getRegisteredBy().getFullStaffID(), memberList.get(index).getRegistrationDate());
				System.out.printf("%-10s|                                                                                                                                                                                                  |\n", "");
				System.out.printf("%-10s----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
				tempName = memberList.get(index).getName();
				tempContactNum = memberList.get(index).getContactNum();
				tempMbrshipType = memberList.get(index).getMembership();
				tempMbrType = memberList.get(index).getMembership();
				
				choice = modifyDetailMenuM();
				
				switch(choice) {
				case 1:
					System.out.print("  Enter Member NEW Name (Enter -1 to cancel): ");
					tempName = scanner.nextLine();
					if(!tempName.equals("-1")) {
						validName = ZhiHang.valiName(tempName);
						while(validName == false) {
							System.out.print("  Invalid Name. Please ensure that your name only has letters (Enter -1 to cancel): ");
							tempName = scanner.nextLine();
							if(!tempName.equals("-1")) {
								validName = ZhiHang.valiName(tempName);
							}
							else {
								cancel = -1;
								validName = true;
							}
						}
					}
					else {
						cancel = -1;
					}
					break;
				case 2:
					System.out.print("  Enter Member NEW Contact Number (Enter -1 to cancel): ");
					tempContactNum = scanner.nextLine();
					if(!tempContactNum.equals("-1")) {
						tempContactNum = contactNoValidation(tempContactNum);
					}
					else {
						cancel = -1;
					}
					break;
				case 3:
					tempMbrType = membershipType();
					if(tempMbrType.equals("-1")) {
						cancel = -1;
					}
					break;
				case 4:
					System.out.print("  Enter Member NEW Name (Enter -1 to cancel): ");
					tempName = scanner.nextLine();
					if(!tempName.equals("-1")) {
						validName = ZhiHang.valiName(tempName);
						while(validName == false) {
							System.out.print("  Invalid Name. Please ensure that your name only has letters (Enter -1 to cancel): ");
							tempName = scanner.nextLine();
							if(!tempName.equals("-1")) {
								validName = ZhiHang.valiName(tempName);
							}
							else {
								cancel = -1;
								validName = true;
							}
						}
					}
					else {
						cancel = -1;
					}
					
					if(cancel != -1) {
						System.out.print("  Enter Member NEW Contact Number (Enter -1 to cancel): ");
						tempContactNum = scanner.nextLine();
						if(!tempContactNum.equals("-1")) {
							tempContactNum = contactNoValidation(tempContactNum);
						}
						else {
							cancel = -1;
						}
					}
					
					if(cancel != -1) {
						tempMbrType = membershipType();
						if(tempMbrType.equals("-1")) {
							cancel = -1;
						}
					}
					break;
				case 0:
					System.out.println("  You Are Quit.");
					break;
				}
				
				if(choice != 0 && cancel != -1) {
					if(!tempMbrshipType.equals(tempMbrType)) {
						tempStatus = "Active";
					}
					else {
						tempStatus = memberList.get(index).getMbrStatus();
					}
					
					System.out.printf("%-10s----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
					System.out.printf("%-10s|                                                                                                                                                                                                  |\n", "");
					System.out.printf("%-10s|   %-17s%-30s%-25s%-15s%-18s%-13s%-17s%-17s%-17s%-19s   |\n", "", "Member ID", "Name", "Identification Number", "Birth Date", "Contact Number", "Membership", "Active Period", "Member Status", "Registered By", "Registration Date");
					System.out.printf("%-10s|   %-17s%-30s%-25s%-15s%-18s%-13s%-17s%-17s%-17s%-19s   |\n", "", "---------", "----", "---------------------", "----------", "--------------", "----------", "-------------", "-------------", "-------------", "-----------------");
					System.out.printf("%-10s|   %-17s%-30s%-25s%-15s%-18s%-13s%-17s%-17s%-17s%-19s   |\n", "", memberList.get(index).getFullMemID(), tempName, memberList.get(index).getIcNo(), memberList.get(index).getBirthDate(),
							tempContactNum, tempMbrType, mbrActivePeriod(tempMbrType), tempStatus, memberList.get(index).getRegisteredBy().getFullStaffID(), memberList.get(index).getRegistrationDate());
					System.out.printf("%-10s|                                                                                                                                                                                                  |\n", "");
					System.out.printf("%-10s----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
					System.out.print("  Are you sure to Modify? (Y = Yes): ");
					modify = Character.toUpperCase(scanner.next().charAt(0));
					scanner.nextLine();
					
					if(modify == 'Y') {
						memberList.get(index).setName(tempName);
						memberList.get(index).setContactNum(tempContactNum);
						if(!tempMbrshipType.equals(tempMbrType)) {
							memberList.get(index).setMembership(tempMbrType);
							memberList.get(index).setActivePeriod(mbrActivePeriod(tempMbrType));
							memberList.get(index).setMbrStatus("Active");
						}
						textFile(memberList);
					}
					else {
						System.out.println("  Modification Cancelled.");
					}
				}
			}
			
			if(invalid == -1) {
				System.out.print("  Invalid Member ID.");
			}
			
			System.out.print("  Would you like to modify another record (Y = Yes)?: ");
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
		boolean validName = false;
		boolean validIc = false;
		boolean found = false;
		
		do {
			contSearch = 'N';
			 do {
				 invalidInput = 0;
				 found = false;
					
					System.out.printf("%-10s------------------------------\n", "");
					System.out.printf("%-10s|     Search Member Menu     |\n", "");
					System.out.printf("%-10s------------------------------\n", "");
					System.out.printf("%-10s|                            |\n", "");
					System.out.printf("%-10s|1. Member ID                |\n", "");
					System.out.printf("%-10s|2. Name                     |\n", "");
					System.out.printf("%-10s|3. Identification Number    |\n", "");
					System.out.printf("%-10s|4. Contact Number           |\n", "");
					System.out.printf("%-10s|5. Membership Type          |\n", "");
					System.out.printf("%-10s|0. Cancel                   |\n", "");
					System.out.printf("%-10s|                            |\n", "");
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
					
					if(selectionS < 0 || selectionS > 5) {
						invalidInput = -1;
					}
					
					if(invalidInput == -1) {
						System.out.println("  Invalid Choice. Please Try Again.");
					}
			 }while(invalidInput == -1);
			
			switch(selectionS) {
			case 1:
				System.out.print("  Enter Member ID to Search: ");
				String mbrID = scanner.next().toUpperCase();
				for(int i = 0; i < memberList.size(); i++) {
					if(memberList.get(i).getFullMemID().equals(mbrID)) {
						System.out.println(memberList.get(i));
						found = true;
					}
				}
				break;
			case 2:
				System.out.print("  Enter Name to Search: ");
				String mbrName = scanner.nextLine();
				for(int i = 0; i < memberList.size(); i++) {
					if(memberList.get(i).getName().equalsIgnoreCase(mbrName)) {
						System.out.println(memberList.get(i));
						found = true;
					}
				}
				break;
			case 3:
				System.out.print("  Enter Identification Number to Search: ");
				String mbrIcNo = scanner.next();
				for(int i = 0; i < memberList.size(); i++) {
					if(memberList.get(i).getIcNo().equals(mbrIcNo)) {
						System.out.println(memberList.get(i));
						found = true;
					}
				}
				break;
			case 4:
				System.out.print("  Enter Contact Number to Search: ");
				String mbrContactNo = scanner.next();
				for(int i = 0; i < memberList.size(); i++) {
					if(memberList.get(i).getContactNum().equals(mbrContactNo)) {
						System.out.println(memberList.get(i));
						found = true;
					}
				}
				break;
			case 5:
				int selectMbrShip;
				String mbrShipType = "";
				int valid = 1;
				
				do {
					System.out.printf("%-10s------------------------------\n", "");
					System.out.printf("%-10s|       Membership Type      |\n", "");
					System.out.printf("%-10s------------------------------\n", "");
					System.out.printf("%-10s|                            |\n", "");
					System.out.printf("%-10s|1. Silver                   |\n", "");
					System.out.printf("%-10s|2. Gold                     |\n", "");
					System.out.printf("%-10s|3. Platinum                 |\n", "");
					System.out.printf("%-10s|                            |\n", "");
					System.out.printf("%-10s------------------------------\n", "");
					System.out.printf("  Enter Membership Type to Search: ");
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
						System.out.println("  Invalid Membership Type. Press Enter to Try Again.");
						scanner.nextLine();
						valid = 0;
					}
					
				} while(valid == 0);
				
				System.out.printf("%-10s|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n", "");
				System.out.printf("%-10s| %-10s|             %-17s| %-17s |    %-15s| %-15s|  %-12s| %-13s |  %-15s|  %-15s|  %-18s |\n", "", "Member ID", "Name", "Identification Number", "Birth Dates", "Contact Number", "Membership", "Active Period", "Member Status", "Registered By", "Registration Date");
				System.out.printf("%-10s|-----------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n", "");
				for(int i = 0; i < memberList.size(); i++) {
					if(memberList.get(i).getMembership().equalsIgnoreCase(mbrShipType)) {
						System.out.printf("%-10s|   %-8s| %-29s|     %-18s|    %-15s|   %-13s|   %-11s|  %-13s|      %-11s|      %-11s| %-15s |\n",
								"", memberList.get(i).getFullMemID(), memberList.get(i).getName(), memberList.get(i).getIcNo(),
								memberList.get(i).getBirthDate(), memberList.get(i).getContactNum(), memberList.get(i).getMembership(),
								memberList.get(i).getActivePeriod(), memberList.get(i).getMbrStatus(),
								memberList.get(i).getRegisteredBy().getFullStaffID(), memberList.get(i).getRegistrationDate().toString());
						found = true;
					}
				}
				System.out.printf("%-10s|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n", "");
				break;
			case 0:
				System.out.println("  Searching Cancelled.");
			}
			
			if(found == false) {
				System.out.println("  Record Doesn't Exist.");
			}
			
			System.out.print("  Would you like to search for another record (Y = Yes)? ");
			contSearch = scanner.next().toUpperCase().charAt(0);
		} while(contSearch == 'Y' && selectionS != 0);
	}
	
	//SEARCH IC
	public static int searchIc(ArrayList<Member> memberList) {
		Scanner scanner = new Scanner(System.in);
		int index = -1;
		
		System.out.printf("  Enter IC: ");
		String ic = scanner.next();
		
		for(int i = 0; i < memberList.size(); i++) {
			if(memberList.get(i).getIcNo().equals(ic)) {
				index = i;
			}
		}
		
		return index;
	}
	
	//4. VIEW ALL MEMBER
	public static void viewMember(ArrayList<Member> memberList) {
		System.out.printf("%-10s--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s|                                                                                                          CURRENT MEMBERS                                                                       |\n", "");
		System.out.printf("%-10s|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n", "");
		System.out.printf("%-10s| %-10s|             %-17s| %-17s |    %-15s| %-15s|  %-12s| %-13s |  %-15s|  %-15s|  %-18s |\n", "", "Member ID", "Name", "Identification Number", "Birth Dates", "Contact Number", "Membership", "Active Period", "Member Status", "Registered By", "Registration Date");
		System.out.printf("%-10s|-----------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n", "");
		
		for(int i = 0; i < memberList.size(); i++) {
			System.out.printf("%-10s|   %-8s| %-29s|     %-18s|    %-15s|   %-13s|   %-11s|   %-12s|     %-12s|      %-11s| %-15s |\n",
					"", memberList.get(i).getFullMemID(), memberList.get(i).getName(), memberList.get(i).getIcNo(),
					memberList.get(i).getBirthDate(), memberList.get(i).getContactNum(), memberList.get(i).getMembership(),
					memberList.get(i).getActivePeriod(), memberList.get(i).getMbrStatus(),
					memberList.get(i).getRegisteredBy().getFullStaffID(), memberList.get(i).getRegistrationDate().toString());
		}
		
		System.out.printf("%-10s--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
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
			valid = 1;
			
			System.out.println();
			System.out.printf("%-10s------------------------------------------------------\n", "");
			System.out.printf("%-10s|       Membership Type      |    Discounts Given    |\n", "");
			System.out.printf("%-10s------------------------------------------------------\n", "");
			System.out.printf("%-10s|                            |                       |\n", "");
			System.out.printf("%-10s|1. Silver                   |         1. 2%%         |\n", "");
			System.out.printf("%-10s|2. Gold                     |         2. 3%%         |\n", "");
			System.out.printf("%-10s|3. Platinum                 |         3. 5%%         |\n", "");
			System.out.printf("%-10s|                            |                       |\n", "");
			System.out.printf("%-10s------------------------------------------------------\n", "");
			System.out.print("  Enter Membership Type (Enter -1 to cancel): ");
			selectMbrShip = scanner.nextInt();
			System.out.println();
			
			if(selectMbrShip == 1) {
				mbrShipType = "Silver";
			}
			else if(selectMbrShip == 2){
				mbrShipType = "Gold";
			}
			else if(selectMbrShip == 3) {
				mbrShipType = "Platinum";
			}
			else if(selectMbrShip == -1) {
				mbrShipType = "-1";
			}
			else {
				System.out.println();
				System.out.println("  Invalid Membership Type. Press Enter to Try Again.");
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

	//MODIFY MEMBER DETAIL(S) MENU
	public static int modifyDetailMenuM() {
		Scanner scanner = new Scanner(System.in);
		
		int selectionM = -1;
		int invalidInput = 0;
		
		do {
			invalidInput = 0;
			
			System.out.printf("%-10s--------------------------------\n", "");
			System.out.printf("%-10s| Modify Member Detail(s) Menu |\n", "");
			System.out.printf("%-10s--------------------------------\n", "");
			System.out.printf("%-10s|                              |\n", "");
			System.out.printf("%-10s| 1. Name                      |\n", "");
			System.out.printf("%-10s| 2. Contact Number            |\n", "");
			System.out.printf("%-10s| 3. Membership                |\n", "");
			System.out.printf("%-10s| 4. All Details               |\n", "");
			System.out.printf("%-10s| 0. Quit                      |\n", "");
			System.out.printf("%-10s|                              |\n", "");
			System.out.printf("%-10s--------------------------------\n", "");
			
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
				System.out.println("  Invalid Choice. Please Try Again.");
			}
		}while(invalidInput == -1);
		
		return selectionM;
	}
	
	//DISPLAY REPORT
	public static void displayReport(ArrayList<Member> memberList, ArrayList<Receipt> receiptList, ArrayList<Refund> refundList) {
		//total order made by each customer
		int totalOrder = 0;
		//total points collected
		int totalPoint = 0;
		//total order made
		int finalOrder = 0;
		
		System.out.printf("%-10s--------------------------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s|                                                                                                      |\n", "");
		System.out.printf("%-10s|                                            MEMBERS REPORT                                            |\n", "");
		System.out.printf("%-10s|                                                                                                      |\n", "");
		System.out.printf("%-10s|------------------------------------------------------------------------------------------------------|\n", "");
		System.out.printf("%-10s| Membership Type: Silver                                                                              |\n", "");
		System.out.printf("%-10s|------------------------------------------------------------------------------------------------------|\n", "");
		System.out.printf("%-10s|                Member Name                | Total Number of Order Made |    Total Points Collected   |\n", "");
		System.out.printf("%-10s|------------------------------------------------------------------------------------------------------|\n", "");
		
		for(int i = 0; i < memberList.size(); i++) {
			totalOrder = 0;
			totalPoint = 0;
			for(int j = 0; j < receiptList.size(); j++) {
				if(memberList.get(i).getFullMemID().equals(receiptList.get(j).getPayment().getMember().getFullMemID())) {
					if(receiptList.get(j).getPayment().getDiscount() == 0.02) {
						totalOrder++;
						totalPoint += (int)receiptList.get(j).getPayment().getPaymentAmount();
					}
				}
			}
			for(int j = 0; j < refundList.size(); j++) {
				if(memberList.get(i).getFullMemID().equals(refundList.get(j).getReceipt().getPayment().getMember().getFullMemID())) {
					if(refundList.get(j).getReceipt().getPayment().getDiscount() == 0.02) {
						totalPoint -= (int)refundList.get(j).getRefundAmount();
					}
				}
			}
			if(totalOrder != 0) {
				System.out.printf("%-10s|    %-30s         |              %-13d |             %-15d |", "", memberList.get(i).getName(), totalOrder, totalPoint);
			}
			finalOrder += totalOrder;
		}
		
		if(finalOrder == 0) {
			System.out.printf("%-10s|                                There is no record to show.                                           |\n", "");
		}
		else {
			System.out.printf("%-10s--------------------------------------------------------------------------------------------------------\n", "");
			System.out.printf("%-10s|                                     Total:               %-5d                                       |\n", "", finalOrder);	
		}
		System.out.printf("%-10s--------------------------------------------------------------------------------------------------------\n\n", "");
		
		finalOrder = 0;
		System.out.printf("%-10s--------------------------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s| Membership Type: Gold                                                                                |\n", "");
		System.out.printf("%-10s|------------------------------------------------------------------------------------------------------|\n", "");
		System.out.printf("%-10s|                Member Name                | Total Number of Order Made |    Total Points Collected   |\n", "");
		System.out.printf("%-10s|------------------------------------------------------------------------------------------------------|\n", "");
		
		for(int i = 0; i < memberList.size(); i++) {
			totalOrder = 0;
			totalPoint = 0;
			for(int j = 0; j < receiptList.size(); j++) {
				if(memberList.get(i).getFullMemID().equals(receiptList.get(j).getPayment().getMember().getFullMemID())) {
					if(receiptList.get(j).getPayment().getDiscount() == 0.03) {
						totalOrder++;
						totalPoint += (int)receiptList.get(j).getPayment().getPaymentAmount();
					}
				}
			}
			for(int j = 0; j < refundList.size(); j++) {
				if(memberList.get(i).getFullMemID().equals(refundList.get(j).getReceipt().getPayment().getMember().getFullMemID())) {
					if(refundList.get(j).getReceipt().getPayment().getDiscount() == 0.03) {
						totalPoint -= (int)refundList.get(j).getRefundAmount();
					}
				}
			}
			if(totalOrder != 0) {
				System.out.printf("%-10s|    %-30s         |              %-13d |             %-15d |\n", "", memberList.get(i).getName(), totalOrder, totalPoint);
			}
			finalOrder += totalOrder;
		}
		
		if(finalOrder == 0) {
			System.out.printf("%-10s|                                There is no record to show.                                           |\n", "");
		}
		else {
			System.out.printf("%-10s--------------------------------------------------------------------------------------------------------\n", "");
			System.out.printf("%-10s|                                     Total:               %-5d                                       |\n", "", finalOrder);	
		}
		System.out.printf("%-10s--------------------------------------------------------------------------------------------------------\n\n", "");
		
		finalOrder = 0;
		System.out.printf("%-10s--------------------------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s| Membership Type: Platinum                                                                            |\n", "");
		System.out.printf("%-10s|------------------------------------------------------------------------------------------------------|\n", "");
		System.out.printf("%-10s|                Member Name                | Total Number of Order Made |    Total Points Collected   |\n", "");
		System.out.printf("%-10s|------------------------------------------------------------------------------------------------------|\n", "");
		
		for(int i = 0; i < memberList.size(); i++) {
			totalOrder = 0;
			totalPoint = 0;
			for(int j = 0; j < receiptList.size(); j++) {
				if(memberList.get(i).getFullMemID().equals(receiptList.get(j).getPayment().getMember().getFullMemID())) {
					if(receiptList.get(j).getPayment().getDiscount() == 0.05) {
						totalOrder++;
						totalPoint += (int)receiptList.get(j).getPayment().getPaymentAmount();
					}
				}
			}
			for(int j = 0; j < refundList.size(); j++) {
				if(memberList.get(i).getFullMemID().equals(refundList.get(j).getReceipt().getPayment().getMember().getFullMemID())) {
					if(refundList.get(j).getReceipt().getPayment().getDiscount() == 0.05) {
						totalPoint -= (int)refundList.get(j).getRefundAmount();
					}
				}
			}
			if(totalOrder != 0) {
				System.out.printf("%-10s|    %-30s         |              %-13d |             %-15d |\n", "", memberList.get(i).getName(), totalOrder, totalPoint);
			}
			finalOrder += totalOrder;
		}
		
		if(finalOrder == 0) {
			System.out.printf("%-10s|                                There is no record to show.                                           |\n", "");
		}
		else {
			System.out.printf("%-10s--------------------------------------------------------------------------------------------------------\n", "");
			System.out.printf("%-10s|                                     Total:               %-5d                                       |\n", "", finalOrder);	
		}
		System.out.printf("%-10s--------------------------------------------------------------------------------------------------------\n\n", "");
	}
	
	//5. DISPLAY RECEIPT
	public static void displayReceipt(ArrayList<Receipt> receiptList) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Receipt> tempReceipt = new ArrayList<Receipt>();
		
		int index = 0;
		int choice = 0;
		boolean loop = false;
		int invalid = 0;
		
		System.out.print("  Enter Member ID: ");
		String memberId = scanner.next().toUpperCase();
		
		for(int i = 0; i < receiptList.size(); i++) {
			if(receiptList.get(i).getPayment().getMember() != null) {
				if(receiptList.get(i).getPayment().getMember().getFullMemID().equals(memberId)) {
					tempReceipt.add(receiptList.get(i));
				}
			}
		}
		
		if(tempReceipt.size() > 0) {
			do {
				loop = false;
				invalid = 0;
				
				System.out.println(tempReceipt.get(index).toString());
				if(index == 0 && tempReceipt.size() > 1) {
					do {
						invalid = 0;
						
						System.out.printf("%-10s-------------------\n", "");
						System.out.printf("%-10s| 1. Next Receipt |\n", "");
						System.out.printf("%-10s| 0. Exit         |\n", "");
						System.out.printf("%-10s-------------------\n", "");
						System.out.print("  Enter your Choice: ");

						try {
							choice = scanner.nextInt();
							scanner.nextLine();
						} catch(InputMismatchException e) {
							scanner.nextLine();
							invalid = 1;
						}
						
						if(invalid != 1) {
							switch(choice) {
							case 1:
								index += 1;
								loop = true;
								break;
							}
						}
					}while(invalid == 1);
				}
				else if(index >= 1 && index < tempReceipt.size() - 1) {
					do {
						invalid = 0;
						
						System.out.printf("%-10s-----------------------\n", "");
						System.out.printf("%-10s| 1. Next Receipt     |\n", "");
						System.out.printf("%-10s| 2. Previous Receipt |\n", "");
						System.out.printf("%-10s| 0. Exit             |\n", "");
						System.out.printf("%-10s-----------------------\n", "");
						System.out.print("  Enter your Choice: ");

						try {
							choice = scanner.nextInt();
							scanner.nextLine();
						} catch(InputMismatchException e) {
							scanner.nextLine();
							invalid = 1;
						}
						
						if(invalid != 1) {
							switch(choice) {
							case 1:
								index += 1;
								loop = true;
								break;
							case 2:
								index -= 1;
								loop = true;
								break;
							}
						}
					}while(invalid == 1);
				}
				else if(index == tempReceipt.size() - 1 && tempReceipt.size() > 1) {
					do {
						invalid = 0;
						
						System.out.printf("%-10s-----------------------\n", "");
						System.out.printf("%-10s| 1. Previous Receipt |\n", "");
						System.out.printf("%-10s| 0. Exit             |\n", "");
						System.out.printf("%-10s-----------------------\n", "");
						System.out.print("  Enter your Choice: ");
						
						try {
							choice = scanner.nextInt();
							scanner.nextLine();
						} catch(InputMismatchException e) {
							scanner.nextLine();
							invalid = 1;
						}
						
						if(invalid != 1) {
							switch(choice) {
							case 1:
								index -= 1;
								loop = true;
								break;
							}
						}
					}while(invalid == 1);
				}
				else {
					System.out.println("  Press Enter to Exit.");
					scanner.nextLine();
				}
			}while(loop == true);
		}
		else {
			System.out.println("  This Member Does Not Have Any Purchases.");
		}
	}
	
	//VALIDATION
	//CONTACT NUMBER VALIDATION
	public static String contactNoValidation(String contactNo) {
		Scanner scanner = new Scanner(System.in);
//		Pattern pattern = Pattern.compile("(0)?[1-9][0-9]{10,11}");
//		List<String> values = new ArrayList<String>();
//		values.add(contactNo);
//		for(String value : values) {
//			Matcher matcher = pattern.matcher(value);
//			System.out.println(matcher.matches());
//		}
		int invalid = 0;
		
		if(contactNo.substring(0, 3).equals("011") || contactNo.substring(0, 3).equals("015")) {
			if(contactNo.length() != 11) {
				invalid = 1;
			}
		}
		else {
			if(contactNo.length() != 10) {
				invalid = 1;
			}
		}
		
		for(int i = 0; i < contactNo.length(); i++) {
			if(Character.isLetter(contactNo.charAt(i))) {
				invalid = 1;
			}
			else if(!Character.isDigit(contactNo.charAt(i)) && !Character.isLetter(contactNo.charAt(i))) {
				invalid = 1;
			}
		}
		
		while(invalid == 1) {
			invalid = 0;
			
			System.out.println("  Invalid Contact Number.");
			System.out.print("  Kindly press enter to re-try (only 10/11 digits are allowed): ");
			contactNo = scanner.nextLine();
			
			if(contactNo.substring(0, 3).equals("011") || contactNo.substring(0, 3).equals("015")) {
				if(contactNo.length() != 11) {
					invalid = 1;
				}
			}
			else {
				if(contactNo.length() != 10) {
					invalid = 1;
				}
			}
			
			for(int i = 0; i < contactNo.length(); i++) {
				if(Character.isLetter(contactNo.charAt(i))) {
					invalid = 1;
				}
				else if(!Character.isDigit(contactNo.charAt(i)) && !Character.isLetter(contactNo.charAt(i))) {
					invalid = 1;
				}
			}
		}
		
		return contactNo;
	}
	
	//WRITE TO TEXT FILE
	public static void textFile(ArrayList<Member> memberList){
		File file = new File("src\\member.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			for(int i = 0; i < memberList.size(); i++) {
				fw.write(memberList.get(i).getName() + "," + memberList.get(i).getIcNo() + "," + memberList.get(i).getBirthDate()
						+ "," + memberList.get(i).getContactNum() + "," + memberList.get(i).getMemberID()+ ","
						+ memberList.get(i).getIdNo()+ "," + memberList.get(i).getMembership()+ "," + memberList.get(i).getActivePeriod()
						+ "," + memberList.get(i).getMbrStatus()+ "," + memberList.get(i).getRegisteredBy().getFullStaffID()
						+ "," + memberList.get(i).getRegistrationDate().toString()+","+memberList.get(i).getMbrshipStartDate().toString()+"\n");
			}
			fw.close();
		} catch (IOException e) {
			System.out.println("An error occurred. Unable to Write member.txt File.");
			e.printStackTrace();
		}
	}

}
