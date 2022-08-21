import java.util.ArrayList;
import java.util.Scanner;

public class ZhiHang {
	public static void login(ArrayList<Staff> staffList, ArrayList<Product> productList, ArrayList<Member> memberList, ArrayList<Cart> cartList, ArrayList<Receipt> receiptList) {

		int found=0;
		char choice;
		Scanner scanner=new Scanner(System.in);
		int idNo=0;
		Staff staff;
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
					
					menuList(staffList, productList, memberList, staffList.get(i), receiptList, cartList);
				}
			}
		}
	}
	
	public static void menuList(ArrayList<Staff> staffList, ArrayList<Product> productList, ArrayList<Member> memberList, Staff staff, ArrayList<Receipt> receiptList, ArrayList<Cart> cartList) {
		ChiKean.cart(staffList,productList,memberList, cartList, staff, receiptList);
	}
}
