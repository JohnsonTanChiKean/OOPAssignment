import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//no bank account yet
//On hold cart not done 
//Report
//ask Mr Choo about the access modifiers for the local functions
public class Main {

	public static void main(String[] args) {
		ArrayList<Product> productList=new ArrayList<Product>();
		ArrayList<Staff> staffList=new ArrayList<Staff>();
		ArrayList<Member> memberList=new ArrayList<Member>();
		ArrayList<Receipt> receiptList=new ArrayList<Receipt>();
		ArrayList<Cart> cartList=new ArrayList<Cart>();
		readProduct(productList);
		readStaff(staffList);
		readMember(memberList);
		
		GetDate date=new GetDate();
		
		System.out.println(date.toString());
		ZhiHang.login(staffList, productList, memberList, cartList, receiptList);
		

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
				Staff tempStaff=new Staff(row[0], row[1], row[2], row[3], Integer.parseInt(row[4]), row[5], row[6], Double.parseDouble(row[7]));
				staffList.add(tempStaff);
			}
		} catch (Exception e) {

		}
	}
	
	public static void readMember(ArrayList<Member> memberList) {
		String file="src\\member.txt";
		BufferedReader reader;
		String line="";
		
		try {
			reader=new BufferedReader(new FileReader(file));
			
				while((line=reader.readLine())!=null) {
					String[] row=line.split(",");
					Member tempMember=new Member(row[0], row[1], row[2], row[3], Integer.parseInt(row[4]), row[5]);
					memberList.add(tempMember);
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	

	
	
	
	
	
}