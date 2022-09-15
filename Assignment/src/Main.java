import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		ArrayList<Product> productList=new ArrayList<Product>();
		ArrayList<Staff> staffList=new ArrayList<Staff>();
		ArrayList<Member> memberList=new ArrayList<Member>();
		ArrayList<Receipt> receiptList=new ArrayList<Receipt>();
		ArrayList<Payment> paymentList=new ArrayList<Payment>();
		ArrayList<Refund> refundList=new ArrayList<Refund>();
		BankAccount bankAccount=new BankAccount();
		readProduct(productList);
		readStaff(staffList);
		readMember(memberList, staffList);
		
		GetDate date=new GetDate();
		
		logo();
		System.out.println(date.toString());
		ZhiHang.login(staffList, productList, memberList, paymentList, receiptList, bankAccount, refundList);
		
	}
	
	public static void logo() {
		System.out.println("      ___________       ____         ____        _______________________");
		System.out.println("     /          |      |     \\      |    |      |                       |");
		System.out.println("    /    _______|      |      \\     |    |      |                       |");
		System.out.println("   /    /              |       \\    |    |      |________       ________|");
		System.out.println("  /    /               |        \\   |    |               |     |");
		System.out.println(" /    /                |    |\\   \\  |    |               |     |");
		System.out.println("/    /                 |    | \\   \\ |    |               |     |");
		System.out.println("\\    \\                 |    |  \\   \\|    |               |     |");
		System.out.println(" \\    \\                |    |   \\        |               |     |");
		System.out.println("  \\    \\               |    |    \\       |               |     |");
		System.out.println("   \\    \\_______       |    |     \\      |               |     |");
		System.out.println("    \\           |      |    |      \\     |               |     |");
		System.out.println("     \\__________|      |____|       \\____|               |_____|");
	}
	
	public static void readProduct(ArrayList<Product> productList) {
		String file="src\\product.txt";
		BufferedReader reader=null;
		String line="";
		Product tempProduct;
		try {
			reader=new BufferedReader(new FileReader(file));
			while((line=reader.readLine())!=null) {
				String[] row=line.split(",");
				if(row[4].equals("Smart Phone")) {
					tempProduct=new SmartPhone(row[0], row[1], Double.parseDouble(row[2]), row[3], row[4], Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]), Double.parseDouble(row[8]), row[9]);
					productList.add(tempProduct);
				}
				else if(row[4].equals("Earphone")) {
					tempProduct=new Earphone(row[0], row[1], Double.parseDouble(row[2]), row[3], row[4], Integer.parseInt(row[5]), row[6], row[7]);
					productList.add(tempProduct);
				}
				else if(row[4].equals("Tablet")) {
					tempProduct=new Tablet(row[0], row[1], Double.parseDouble(row[2]), row[3], row[4], Integer.parseInt(row[5]), Double.parseDouble(row[6]), Integer.parseInt(row[7]), row[8]);
					productList.add(tempProduct);
				}
				else if(row[4].equals("Refrigerator")) {
					tempProduct=new Refrigerator(row[0], row[1], Double.parseDouble(row[2]), row[3], row[4], Integer.parseInt(row[5]), Double.parseDouble(row[6]), Double.parseDouble(row[7]), row[8]);
					productList.add(tempProduct);
				}
				else if(row[4].equals("Printer")) {
					tempProduct=new Printer(row[0], row[1], Double.parseDouble(row[2]), row[3], row[4], Integer.parseInt(row[5]), row[6], row[7], row[8], row[9], row[10]);
					productList.add(tempProduct);
				}
				else if(row[4].equals("Scanners")) {
					tempProduct=new Scanners(row[0], row[1], Double.parseDouble(row[2]), row[3], row[4], Integer.parseInt(row[5]), row[6], row[7], row[8]);
					productList.add(tempProduct);
				}
				else if(row[4].equals("Microwave")) {
					tempProduct=new Microwave(row[0], row[1], Double.parseDouble(row[2]), row[3], row[4], Integer.parseInt(row[5]), row[6], row[7], Double.parseDouble(row[8]), row[9]);
					productList.add(tempProduct);
				}
				else if(row[4].equals("Smart Watch")) {
					tempProduct=new SmartWatch(row[0], row[1], Double.parseDouble(row[2]), row[3], row[4], Integer.parseInt(row[5]), Double.parseDouble(row[6]), row[7], Integer.parseInt(row[8]), row[9], Integer.parseInt(row[10]), row[11]);
					productList.add(tempProduct);
				}
			}
		} catch (Exception e) {
			
		}
	}
	
	public static void readStaff(ArrayList<Staff> staffList) {
		String file="src\\staff.txt";
		BufferedReader reader=null;
		String line="";
		
		try {
			reader=new BufferedReader(new FileReader(file));
			while((line=reader.readLine())!=null) {
				String[] row=line.split(",");
				Staff tempStaff=new Staff(row[0], row[1], row[2], row[3], row[4], Integer.parseInt(row[5]), row[6], row[7], Double.parseDouble(row[8]), row[9], row[10]);
				staffList.add(tempStaff);
			}
		} catch (Exception e) {

		}
	}
	
	public static void readMember(ArrayList<Member> memberList, ArrayList<Staff> staffList) {
		String file="src\\member.txt";
		BufferedReader reader;
		String line="";
		
		try {
			reader=new BufferedReader(new FileReader(file));
			
				while((line=reader.readLine())!=null) {
					String[] row=line.split(",");
					for(int i=0; i<staffList.size(); i++) {
						if(staffList.get(i).getFullStaffID().equals(row[9])) {
							Member tempMember=new Member(row[0], row[1], row[2], row[3], row[4], Integer.parseInt(row[5]), row[6], row[7], row[8], staffList.get(i), row[10]);
							memberList.add(tempMember);
						}
					}
					
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
	
	
	
}