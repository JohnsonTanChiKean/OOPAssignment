import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Image;


//if payment=cancelled, quantity of all items in the cart must be added back to their respective arraylist counterparts
// report should show original quantity, current quantity, quantity on-hold and quantity sold
//onhold list -1 not working
//work on the toString of BankAccount
//report try to do bar chart
public class ChiKean {
	public static String selectProduct(ArrayList<Product> productList) {
		int prodChoice=0, invalidInput=0;
		Scanner scanner=new Scanner(System.in);
		int foundProd=0;
		String tempProdType="";
		ArrayList<String> prodSelectList=new ArrayList<String>();
		prodSelectList.add("null");
		for(int i=0; i<productList.size(); i++) {
			foundProd=0;
			if(productList.get(i).getQuantity()>0) {
				tempProdType=productList.get(i).getType();
				for(int j=0; j<prodSelectList.size(); j++) {
					if(tempProdType.equals(prodSelectList.get(j))) {
						foundProd=1;
					}
				}
				if(foundProd==0) {
					prodSelectList.remove("null");
					prodSelectList.add(tempProdType);
				}
			}
		}
		
		do{
			invalidInput=0;
			System.out.printf("%-10s---------------------------\n", "");
			System.out.printf("%-10s| Available Product Types |\n", "");
			System.out.printf("%-10s| ======================= |\n", "");
			System.out.printf("%-10s|                         |\n", "");
			for(int i =0; i<prodSelectList.size(); i++) {
				
				System.out.printf("%-10s|     %d%-3s%-15s |\n", "", (i+1), ".", prodSelectList.get(i));
				
			}
			System.out.printf("%-10s|                         |\n", "");
			System.out.printf("%-10s---------------------------\n\n", "");
			System.out.printf("%-2sSelect product type(Enter -1 to quit): ", "");
			try {
				prodChoice=scanner.nextInt();
				scanner.nextLine();
			}
			catch(InputMismatchException e) {
				invalidInput=1;
				scanner.nextLine();
			}
			if((prodChoice>prodSelectList.size())||(prodChoice==0)||(prodChoice<-1)||(invalidInput==1)) {
				System.out.printf("%-10sInvalid choice.\n","");
				System.out.printf("%-10sPress enter to try again\n","");
				scanner.nextLine();
			}
		}while((prodChoice>prodSelectList.size())||(prodChoice==0)||(prodChoice<-1)||(invalidInput==1));
		
		if(prodChoice==-1) {
			return "Cancel";
		}
		else {
			return prodSelectList.get(prodChoice-1);
		}
		
	}
	
	public static void phone(ArrayList<Product> productList, SmartPhone product) {
		Scanner scanner=new Scanner(System.in);
		int storageChoice=0, ramChoice=0, colorChoice=0, foundStorage, foundRam, foundColor, tempStorage=0, tempRam=0, quantity=0, loopQuantity=0, cancel=0, invalidInput=0;
		String tempColor="";
		ArrayList<SmartPhone> phoneList=new ArrayList<SmartPhone>();
		ArrayList<Integer> storage=new ArrayList<Integer>();
		ArrayList<Integer> ram=new ArrayList<Integer>();
		ArrayList<String> color=new ArrayList<String>();
		storage.add(0);
		ram.add(0);
		color.add("null");
		for(int i=0; i<productList.size(); i++) {
			if((productList.get(i).getType().equals("Smart Phone"))&&(productList.get(i).getQuantity()>0)) {
				phoneList.add((SmartPhone) productList.get(i));
			}
		}
		
		for(int i=0; i<phoneList.size(); i++) {
			foundStorage=0;
			if(phoneList.get(i).getQuantity()>0) {
				tempStorage=phoneList.get(i).getStorageCapacity();
				for(int j=0; j<storage.size(); j++) {
					if(tempStorage==storage.get(j)) {
						foundStorage=1;
					}
				}

				if(foundStorage==0) {
					storage.remove(Integer.valueOf(0));
					storage.add(tempStorage);
				}
			}
		}
		
		if(storage.size()==1) {
			storageChoice=1;
		}
		else {
			do {
				invalidInput=0;
				System.out.println();
				System.out.printf("%-10s--------------------\n", "");
				System.out.printf("%-10s| Storage Capacity |\n", "");
				System.out.printf("%-10s| ================ |\n", "");
				System.out.printf("%-10s|                  |\n", "");
				for(int i=0; i<storage.size(); i++) {
					System.out.printf("%-10s| %4d%-3s%d%-6s |\n", "", (i+1), ".", storage.get(i), "GB");
				}
				System.out.printf("%-10s|                  |\n", "");
				System.out.printf("%-10s--------------------\n\n", "");
			
				System.out.printf("%-2sSelect Storage Capacity(Enter -1 to cancel): ", "");
				try{
					storageChoice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){
					invalidInput=1;
					scanner.nextLine();
				}
				
				
				if((storageChoice>storage.size())||(storageChoice==0)||(storageChoice<-1)||(invalidInput==1)) {
					System.out.printf("\n%-10sInvalid Choice.\n", "");
					System.out.printf("%-10sPress enter to try again", "");
					scanner.nextLine();
					System.out.println();
				}
				else if(storageChoice==-1) {
					cancel=-1;
				}
			}while((storageChoice>storage.size())||(storageChoice==0)||(storageChoice<-1)||(invalidInput==1));	
		}
		
		if(cancel!=-1) {
			for(int i=0; i<phoneList.size(); i++) {
				foundRam=0;
				if((storage.get(storageChoice-1)==phoneList.get(i).getStorageCapacity())&&(phoneList.get(i).getQuantity()>0)) {
					tempRam=phoneList.get(i).getSizeOfRam();
					for(int j=0; j<ram.size(); j++) {
						if(tempRam==ram.get(j)) {
							foundRam=1;
						}
					}
					if(foundRam==0) {
						ram.remove(Integer.valueOf(0));
						ram.add(tempRam);
					}
				}
				
			}
			
			if(ram.size()==1) {
				ramChoice=1;
			}
			else {
				do {
					invalidInput=0;
					System.out.println();
					System.out.printf("%-10s----------------\n", "");
					System.out.printf("%-10s| RAM capacity |\n", "");
					System.out.printf("%-10s| ============ |\n", "");
					System.out.printf("%-10s|              |\n", "");
					for(int i=0; i<ram.size(); i++) {
						System.out.printf("%-10s| %3d%-3s%d%-5s |\n", "", (i+1), ".", ram.get(i), "GB");
					}
					System.out.printf("%-10s|              |\n", "");	
					System.out.printf("%-10s----------------\n\n", "");
				
					System.out.printf("%-2sSelect RAM capacity(Enter -1 to cancel): ", "");
					try{
						ramChoice=scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e){
						invalidInput=1;
						scanner.nextLine();
					}
					
					
					if((ramChoice>ram.size())||(ramChoice==0)||(ramChoice<-1)||(invalidInput==1)) {
						System.out.printf("\n%-10sInvalid Choice.\n", "");
						System.out.printf("%-10sPress enter to try again", "");
						scanner.nextLine();
						System.out.println();
					}
					else if(ramChoice==-1) {
						cancel=-1;
					}
				}while((ramChoice>ram.size())||(ramChoice==0)||(ramChoice<-1)||(invalidInput==1));
			}
		}
		
		
		if(cancel!=-1) {
			for(int i=0; i<phoneList.size(); i++) {
				foundColor=0;
				if((storage.get(storageChoice-1)==phoneList.get(i).getStorageCapacity())&&(ram.get(ramChoice-1)==phoneList.get(i).getSizeOfRam())&&(phoneList.get(i).getQuantity()>0)) {
					tempColor=phoneList.get(i).getColor();
					for(int j=0; j<color.size(); j++) {
						if(tempColor.equals(color.get(j))) {
							foundColor=1;
						}
					}
					if(foundColor==0) {
						color.remove("null");
						color.add(tempColor);
					}
				}
				
			}
			
			if(color.size()==1) {
				colorChoice=1;
			}
			else {
				do {
					invalidInput=0;
					System.out.println();
					System.out.printf("%-10s------------------------\n", "");
					System.out.printf("%-10s|         Color        |\n", "");
					System.out.printf("%-10s|         =====        |\n", "");
					System.out.printf("%-10s|                      |\n", "");
					for(int i=0; i<color.size(); i++) {
						System.out.printf("%-10s| %3d%-3s%-14s |\n", "", (i+1), ".", color.get(i));
					}
					System.out.printf("%-10s|                      |\n", "");
					System.out.printf("%-10s------------------------\n\n", "");
				
					System.out.printf("%-2sSelect Color(Enter -1 to cancel): ", "");
					try{
						colorChoice=scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e){
						invalidInput=1;
						scanner.nextLine();
					}
					
					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1)) {
						System.out.printf("\n%-10sInvalid Choice.\n", "");
						System.out.printf("%-10sPress enter to try again", "");
						scanner.nextLine();
						System.out.println();
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1));
			}
		}
		
		if(cancel!=-1) {
			for(int i=0; i<phoneList.size(); i++) {
				if((storage.get(storageChoice-1)==phoneList.get(i).getStorageCapacity())&&(ram.get(ramChoice-1)==phoneList.get(i).getSizeOfRam())&&(color.get(colorChoice-1).equals(phoneList.get(i).getColor()))) {
					System.out.println();
					do {		
						invalidInput=0;
						loopQuantity=0;
						System.out.println(phoneList.get(i).toString());
						System.out.println("  Quantity Available: "+phoneList.get(i).getQuantity());
						System.out.printf("%-2sEnter Quantity(Enter -1 to cancel): ", "");
						try{
							quantity=scanner.nextInt();
							scanner.nextLine();
						}
						catch(InputMismatchException e){
							invalidInput=1;
							scanner.nextLine();
						}
						
						if(quantity>phoneList.get(i).getQuantity()) {
							System.out.println("  Entered Quantity exceeds Quantity Available. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1) {
							System.out.println("  Entered Quantity is below 0. Please try again");
							loopQuantity=1;
						}
						else if((quantity==0)&&(invalidInput!=1)){
							System.out.println("  Quantity entered is 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==-1) {
							cancel=-1;
						}
						else if(invalidInput==1){
							System.out.println("  Quantity entered is not valid. Please try again");
							loopQuantity=1;
						}
					}while(loopQuantity==1);
					
					if(quantity>0) {
						product.setProductID(phoneList.get(i).getProductID());
						product.setProductName(phoneList.get(i).getProductName());
						product.setPrice(phoneList.get(i).getPrice());
						product.setCategory(phoneList.get(i).getCategory());
						product.setType(phoneList.get(i).getType());
						product.setQuantity(quantity);
						product.setStorageCapacity(phoneList.get(i).getStorageCapacity());
						product.setSizeOfRam(phoneList.get(i).getSizeOfRam());
						product.setScreenSize(phoneList.get(i).getScreenSize());
						product.setColor(phoneList.get(i).getColor());
					}
					
				}
			}
		}
		
		
	}
	
	public static void earphone(ArrayList<Product> productList, Earphone product) {
		Scanner scanner=new Scanner(System.in);
		int genChoice=0, colorChoice=0, foundGen, foundColor, quantity=0, loopQuantity, cancel=0, invalidInput=0;
		String tempGen="", tempColor="";
		ArrayList<Earphone> earphoneList=new ArrayList<Earphone>();
		ArrayList<String> generation=new ArrayList<String>();
		ArrayList<String> color=new ArrayList<String>();
		color.add("null");
		generation.add("null");
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getType().equals("Earphone")&&(productList.get(i).getQuantity()>0)) {
				earphoneList.add((Earphone) productList.get(i));
			}
		}
		
		for(int i=0; i<earphoneList.size(); i++) {
			foundGen=0;
			if(earphoneList.get(i).getQuantity()>0) {
				tempGen=earphoneList.get(i).getGeneration();
				for(int j=0; j<generation.size(); j++) {
					if(tempGen.equals(generation.get(j))) {
						foundGen=1;
					}
				}
				if(foundGen==0) {
					generation.remove("null");
					generation.add(tempGen);
				}
			}
		}
		
		if(generation.size()==1) {
			genChoice=1;
		}
		else {
			do {
				invalidInput=0;
				System.out.println();
				System.out.printf("%-10s----------------------------\n", "");
				System.out.printf("%-10s|        Generation        |\n", "");
				System.out.printf("%-10s|        ==========        |\n", "");
				System.out.printf("%-10s|                          |\n", "");
				for(int i=0; i<generation.size(); i++) {
					System.out.printf("%-10s| %4d%-3s%-17s |\n", "", (i+1), ".", generation.get(i));
				}
				System.out.printf("%-10s|                          |\n", "");
				System.out.printf("%-10s----------------------------\n\n", "");
			
				System.out.print("  Select Generation(Enter -1 to cancel): ");
				try{
					genChoice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){
					invalidInput=1;
					scanner.nextLine();
				}
				
				
				if((genChoice>generation.size())||(genChoice==0)||(genChoice<-1)||(invalidInput==1)) {
					System.out.printf("\n%-10sInvalid Choice.\n", "");
					System.out.printf("%-10sPress enter to try again", "");
					scanner.nextLine();
					System.out.println();
				}
				else if(genChoice==-1) {
					cancel=-1;
				}
			}while((genChoice>generation.size())||(genChoice==0)||(genChoice<-1)||(invalidInput==1));
		}
		
		if(cancel!=-1) {
			for(int i=0; i<earphoneList.size(); i++) {
				foundColor=0;
				if((earphoneList.get(i).getGeneration().equals(generation.get(genChoice-1)))&&(earphoneList.get(i).getQuantity()>0)) {
					tempColor=earphoneList.get(i).getColor();
					for(int j=0; j<color.size(); j++) {
						if(tempColor.equals(color.get(j))) {
							foundColor=1;
						}
					}
					if(foundColor==0) {
						color.remove("null");
						color.add(tempColor);
					}
				}
			}
			
			if(color.size()==1) {
				colorChoice=1;
			}
			else {
				do {
					invalidInput=0;
					System.out.println();
					System.out.printf("%-10s----------------------\n", "");
					System.out.printf("%-10s|        Color       |\n", "");
					System.out.printf("%-10s|        =====       |\n", "");
					System.out.printf("%-10s|                    |\n", "");
					for(int i=0; i<color.size(); i++) {
						System.out.printf("%-10s| %4d%-3s%-11s |\n", "", (i+1), ".", color.get(i));
					}
					System.out.printf("%-10s|                    |\n", "");
					System.out.printf("%-10s----------------------\n\n", "");
					
					System.out.print("  Select Color(Enter -1 to cancel): ");
					try{
						colorChoice=scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e){
						invalidInput=1;
						scanner.nextLine();
					}
					
					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1)) {
						System.out.printf("\n%-10sInvalid Choice.\n", "");
						System.out.printf("%-10sPress enter to try again", "");
						scanner.nextLine();
						System.out.println();
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1));
			}
		}
		
		
		if(cancel!=-1) {
			for(int i=0; i<earphoneList.size(); i++) {
				if((earphoneList.get(i).getGeneration().equals(generation.get(genChoice-1)))&&(earphoneList.get(i).getColor().equals(color.get(colorChoice-1)))) {
					System.out.println();
					do {
						invalidInput=0;
						loopQuantity=0;
						System.out.println(earphoneList.get(i).toString());
						System.out.println("  Available Quantity: "+earphoneList.get(i).getQuantity());
						System.out.print("  Enter Quantity(Enter -1 to cancel): ");
						try{
							quantity=scanner.nextInt();
							scanner.nextLine();
						}
						catch(InputMismatchException e){
							invalidInput=1;
							scanner.nextLine();
						}
						
						if(quantity>earphoneList.get(i).getQuantity()) {
							System.out.println("  Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1) {
							System.out.println("  Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if((quantity==0)&&(invalidInput==0)) {
							System.out.println("  Entered Quantity is 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==-1) {
							cancel=-1;
						}
						else if(invalidInput==1){
							System.out.println("  Quantity entered is not valid. Please try again");
							loopQuantity=1;
						}
					}while(loopQuantity==1);
					
					if(quantity>0) {
						product.setProductID(earphoneList.get(i).getProductID());
						product.setProductName(earphoneList.get(i).getProductName());
						product.setPrice(earphoneList.get(i).getPrice());
						product.setCategory(earphoneList.get(i).getCategory());
						product.setType(earphoneList.get(i).getType());
						product.setQuantity(quantity);
						product.setGeneration(earphoneList.get(i).getGeneration());
						product.setColor(earphoneList.get(i).getColor());
					}
				}
			}
		}
		
	}
	
	public static void tablet(ArrayList<Product> productList, Tablet product) {
		Scanner scanner=new Scanner(System.in);
		int ramChoice=0, colorChoice=0, foundRam, foundColor, tempRam, quantity=0, loopQuantity=0, cancel=0, invalidInput=0;
		String tempColor="";
		ArrayList<Tablet> tabletList=new ArrayList<Tablet>();
		ArrayList<Integer> ram=new ArrayList<Integer>();
		ArrayList<String> color=new ArrayList<String>();
		ram.add(0);
		color.add("null");
		
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getType().equals("Tablet")&&(productList.get(i).getQuantity()>0)) {
				tabletList.add((Tablet)productList.get(i));
			}
		}
		
		for(int i=0; i<tabletList.size(); i++) {
			foundRam=0;
			if(tabletList.get(i).getQuantity()>0) {
				tempRam=tabletList.get(i).getSizeOfRam();
				for(int j=0; j<ram.size(); j++) {
					if(tempRam==ram.get(j)) {
						foundRam=1;
					}
				}
				if(foundRam==0) {
					ram.remove(Integer.valueOf(0));
					ram.add(tempRam);
				}
			}
		}
		
		
		if(ram.size()==1) {
			ramChoice=1;
		}
		else {
			do {
				invalidInput=0;
				System.out.println();
				System.out.printf("%-10s----------------\n", "");
				System.out.printf("%-10s| RAM capacity |\n", "");
				System.out.printf("%-10s| ============ |\n", "");
				System.out.printf("%-10s|              |\n", "");
				for(int i=0; i<ram.size(); i++) {
					System.out.printf("%-10s| %3d%-3s%2d%-4s |\n", "", (i+1), ".", ram.get(i), "GB");
				}
				System.out.printf("%-10s|              |\n", "");	
				System.out.printf("%-10s----------------\n\n", "");
			
			
				System.out.print("  Select RAM Capacity(Enter -1 to cancel): ");
				try{
					ramChoice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){
					invalidInput=1;
					scanner.nextLine();
				}
				
				
				if((ramChoice>ram.size())||(ramChoice==0)||(ramChoice<-1)||(invalidInput==1)) {
					System.out.printf("\n%-10sInvalid Choice.\n", "");
					System.out.printf("%-10sPress enter to try again", "");
					scanner.nextLine();
					System.out.println();
				}
				else if(ramChoice==-1) {
					cancel=-1;
				}
			}while((ramChoice>ram.size())||(ramChoice==0)||(ramChoice<-1)||(invalidInput==1));
		}
		
		if(cancel!=-1) {
			for(int i=0; i<tabletList.size(); i++) {
				foundColor=0;
				if((ram.get(ramChoice-1)==tabletList.get(i).getSizeOfRam())&&(tabletList.get(i).getQuantity()>0)) {
					tempColor=tabletList.get(i).getColor();
					for(int j=0; j<color.size(); j++) {
						if(tempColor.equals(color.get(j))) {
							foundColor=1;
						}
					}
					if(foundColor==0) {
						color.remove("null");
						color.add(tempColor);
					}
				}
			}
			
			if(color.size()==1) {
				colorChoice=1;
			}
			else {
				do {
					invalidInput=0;
					System.out.println();
					System.out.printf("%-10s-----------------------\n", "");
					System.out.printf("%-10s|        Color        |\n", "");
					System.out.printf("%-10s|        =====        |\n", "");
					System.out.printf("%-10s|                     |\n", "");
					for(int i=0; i<color.size();i++) {
						System.out.printf("%-10s| %3d%-3s%-13s |\n", "", (i+1), ".", color.get(i));
					}
					System.out.printf("%-10s|                     |\n", "");
					System.out.printf("%-10s-----------------------\n\n", "");
				
					System.out.print("  Select Color(Enter -1 to cancel): ");
					try{
						colorChoice=scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e){
						invalidInput=1;
						scanner.nextLine();
					}
					
					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1)) {
						System.out.printf("\n%-10sInvalid Choice.\n", "");
						System.out.printf("%-10sPress enter to try again", "");
						scanner.nextLine();
						System.out.println();
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1));
			}
		}
		
		
		if(cancel!=-1) {
			for(int i=0; i<tabletList.size(); i++) {
				if((ram.get(ramChoice-1)==tabletList.get(i).getSizeOfRam())&&(color.get(colorChoice-1).equals(tabletList.get(i).getColor()))) {
					System.out.println();
					do {
						invalidInput=0;
						loopQuantity=0;
						System.out.println(tabletList.get(i).toString());
						System.out.println("  Available Quantity: "+tabletList.get(i).getQuantity());
						System.out.print("  Enter Quantity(Enter -1 to cancel): ");
						quantity=scanner.nextInt();
						scanner.nextLine();
						
						if(quantity>tabletList.get(i).getQuantity()) {
							System.out.println("  Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("  Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if((quantity==0)&&(invalidInput!=1)) {
							System.out.println("  Entered Quantity is 0. Please try again");
							loopQuantity=1;
						}
						else if(invalidInput==1) {
							System.out.println("  Quantity entered is not valid. Please try again");
							loopQuantity=1;
						}
						
						if(quantity==-1) {
							cancel=-1;
						}
					}while(loopQuantity==1);
					
					if(quantity>0) {
						product.setProductID(tabletList.get(i).getProductID());
						product.setProductName(tabletList.get(i).getProductName());
						product.setPrice(quantity*tabletList.get(i).getPrice());
						product.setCategory(tabletList.get(i).getCategory());
						product.setType(tabletList.get(i).getType());
						product.setQuantity(quantity);
						product.setDisplaySize(tabletList.get(i).getDisplaySize());
						product.setSizeOfRam(tabletList.get(i).getSizeOfRam());
						product.setColor(tabletList.get(i).getColor());
					}
				}
			}
		}
		
	}
	
	public static void refrigerator(ArrayList<Product> productList, Refrigerator product) {
		Scanner scanner=new Scanner(System.in);
		double tempHPower, tempCapacity;
		int hpNCChoice=0, colorChoice=0, foundHPower, foundCap, foundColor, quantity=0, loopQuantity, cancel=0, invalidInput=0;;
		String tempColor="";
		ArrayList<Refrigerator> fridgeList=new ArrayList<Refrigerator>();
		ArrayList<Double> horsePower=new ArrayList<Double>();
		ArrayList<Double> capacity=new ArrayList<Double>();
		ArrayList<String> color=new ArrayList<String>();
		horsePower.add(0.0);
		capacity.add(0.0);
		color.add("null");
		
		
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getType().equals("Refrigerator")&&(productList.get(i).getQuantity()>0)) {
				fridgeList.add((Refrigerator)productList.get(i));
			}
		}
		
		for(int i=0; i<fridgeList.size(); i++) {
			foundHPower=0;
			foundCap=0;
			if(fridgeList.get(i).getQuantity()>0) {
				tempHPower=fridgeList.get(i).getHorsePower();
				tempCapacity=fridgeList.get(i).getCapacity();
				for(int j=0; j<horsePower.size(); j++) {
					if(tempHPower==horsePower.get(j)) {
						foundHPower=1;
					}
				}
				for(int j=0; j<capacity.size(); j++) {
					if(tempCapacity==capacity.get(j)) {
						foundCap=1;
					}
				}
				if(foundHPower==0){
					horsePower.remove(Double.valueOf(0.0));
					horsePower.add(tempHPower);
				}
				if(foundCap==0) {
					capacity.remove(Double.valueOf(0.0));
					capacity.add(tempCapacity);
				}
			}
		}
		
		if(horsePower.size()==1) {
			hpNCChoice=1;
		}
		else {
			do {
				invalidInput=0;
				System.out.println();
				System.out.printf("%-10s-------------------------------\n", "");
				System.out.printf("%-10s|    | Horse Power | Capacity |\n", "");
				for(int i=0; i<horsePower.size(); i++) {
					System.out.printf("%-10s|----+-------------+----------|\n", "");
					System.out.printf("%-10s| %d%s | %8.2f%-3s | %2s%-6.0f |\n", "", (i+1), ".", horsePower.get(i), "W", "", capacity.get(i));
				}
				System.out.printf("%-10s-------------------------------\n\n", "");
			
				System.out.print("  Select Horse Power(Enter -1 to cancel): ");
				try{
					hpNCChoice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){
					invalidInput=1;
					scanner.nextLine();
				}
				
				
				if((hpNCChoice>horsePower.size())||(hpNCChoice==0)||(hpNCChoice<-1)||(invalidInput==1)) {
					System.out.printf("\n%-10sInvalid Choice.\n", "");
					System.out.printf("%-10sPress enter to try again", "");
					scanner.nextLine();
					System.out.println();
				}
				else if(hpNCChoice==-1) {
					cancel=-1;
				}
			}while((hpNCChoice>horsePower.size())||(hpNCChoice==0)||(hpNCChoice<-1)||(invalidInput==1));
		}
		
		if(cancel!=-1) {
			for(int i=0; i<fridgeList.size(); i++) {
				foundColor=0;
				if((horsePower.get(hpNCChoice-1)==fridgeList.get(i).getHorsePower())&&(capacity.get(hpNCChoice-1)==fridgeList.get(i).getCapacity())&&(fridgeList.get(i).getQuantity()>0)) {
					tempColor=fridgeList.get(i).getColor();
					for(int j=0; j<color.size(); j++) {
						if(tempColor.equals(color.get(j))) {
							foundColor=1;
						}
					}
					if(foundColor==0) {
						color.remove("null");
						color.add(tempColor);
					}
				}
			}
			
			if(color.size()==1) {
				colorChoice=1;
			}
			else {
				do {
					invalidInput=0;
					System.out.println();
					System.out.printf("%-10s----------------------\n", "");
					System.out.printf("%-10s|        Color       |\n", "");
					System.out.printf("%-10s|        =====       |\n", "");
					System.out.printf("%-10s|                    |\n", "");
					for(int i=0; i<color.size(); i++) {
						System.out.printf("%-10s| %3d%-3s%-12s |\n", "", (i+1), ".", color.get(i));
					}
					System.out.printf("%-10s|                    |\n", "");
					System.out.printf("%-10s----------------------\n\n", "");
				
					System.out.print("  Select Color(Enter -1 to cancel): ");
					try{
						colorChoice=scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e){
						invalidInput=1;
						scanner.nextLine();
					}
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1)) {
						System.out.printf("\n%-10sInvalid Choice.\n", "");
						System.out.printf("%-10sPress enter to try again", "");
						scanner.nextLine();
						System.out.println();
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1));
			}
			
		}
		
		if(cancel!=-1) {
			for(int i=0; i<fridgeList.size(); i++) {
				if((horsePower.get(hpNCChoice-1)==fridgeList.get(i).getHorsePower())&&(capacity.get(hpNCChoice-1)==fridgeList.get(i).getCapacity())&&(color.get(colorChoice-1).equals(fridgeList.get(i).getColor()))) {
					System.out.println();
					do {
						invalidInput=0;
						loopQuantity=0;
						System.out.println(fridgeList.get(i).toString());
						System.out.println("  Available Quantity: "+fridgeList.get(i).getQuantity());
						System.out.print("  Enter Quantity(Enter -1 to cancel): ");
						try{
							quantity=scanner.nextInt();
							scanner.nextLine();
						}
						catch(InputMismatchException e){
							invalidInput=1;
							scanner.nextLine();
						}
						
						
						if(quantity>fridgeList.get(i).getQuantity()) {
							System.out.println("  Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("  Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if((quantity==0)&&(invalidInput!=1)) {
							System.out.println("  Entered Quantity is 0. Please try again");
							loopQuantity=1;
						}
						else if(invalidInput==1) {
							System.out.println("  Quantity entered is not valid. Please try again");
							loopQuantity=1;
						}
						
						if(quantity==-1) {
							cancel=-1;
						}
					}while(loopQuantity==1);
					
					if(quantity>0) {
						product.setProductID(fridgeList.get(i).getProductID());
						product.setProductName(fridgeList.get(i).getProductName());
						product.setPrice(fridgeList.get(i).getPrice());
						product.setCategory(fridgeList.get(i).getCategory());
						product.setType(fridgeList.get(i).getType());
						product.setQuantity(quantity);
						product.setHorsePower(fridgeList.get(i).getHorsePower());
						product.setCapacity(fridgeList.get(i).getCapacity());
						product.setColor(fridgeList.get(i).getColor());
					}
					
				}
			}
		}
		
	}
	
	public static void printer(ArrayList<Product> productList, Printer product) {
		Scanner scanner=new Scanner(System.in);
		int seriesChoice=0, resChoice=0, typeChoice=0, duplexChoice=0, colorChoice=0, foundSeries, foundRes, foundType, foundDuplex, foundColor, quantity=0, loopQuantity, cancel=0, invalidInput=0;;
		String tempSeries, tempRes, tempType, tempDuplex, tempColor;
		ArrayList<Printer> printerList=new ArrayList<Printer>();
		ArrayList<String> series=new ArrayList<String>();
		ArrayList<String> resolution=new ArrayList<String>();
		ArrayList<String> printerType=new ArrayList<String>();
		ArrayList<String> duplexCapability=new ArrayList<String>();
		ArrayList<String> color=new ArrayList<String>();
		series.add("null");
		resolution.add("null");
		printerType.add("null");
		duplexCapability.add("null");
		color.add("null");
		
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getType().equals("Printer")&&(productList.get(i).getQuantity()>0)) {
				printerList.add((Printer)productList.get(i));
			}
		}
		
		for(int i=0; i<printerList.size(); i++) {
			foundType=0;
			if((productList.get(i).getQuantity()>0)) {
				tempType=printerList.get(i).getPrinterType();
				for(int j=0; j<printerType.size(); j++) {
					if(tempType.equals(printerType.get(j))) {
						foundType=1;
					}
				}
				if(foundType==0) {
					printerType.remove("null");
					printerType.add(tempType);
				}
			}
		}
		
		if(printerType.size()==1) {
			typeChoice=1;
		}
		else {
			do {
				invalidInput=0;
				System.out.println();
				System.out.printf("%-10s------------------------------\n", "");
				System.out.printf("%-10s|        Printer Type        |\n", "");
				System.out.printf("%-10s|        ============        |\n", "");
				System.out.printf("%-10s|                            |\n", "");
				for(int i=0; i<printerType.size(); i++) {
					System.out.printf("%-10s| %3d%-3s%-20s |\n", "", (i+1), ".", printerType.get(i));
				}
				System.out.printf("%-10s|                            |\n", "");
				System.out.printf("%-10s------------------------------\n\n", "");
				System.out.print("  Select Printer Type(Enter -1 to cancel): ");
				try{
					typeChoice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){
					invalidInput=1;
					scanner.nextLine();
				}
				
				
				if((typeChoice>printerType.size())||(typeChoice==0)||(typeChoice<-1)||(invalidInput==1)) {
					System.out.printf("\n%-10sInvalid Choice.\n", "");
					System.out.printf("%-10sPress enter to try again", "");
					scanner.nextLine();
					System.out.println();
				}
				else if(typeChoice==-1) {
					cancel=-1;
				}
			}while((typeChoice>printerType.size())||(typeChoice==0)||(typeChoice<-1)||(invalidInput==1));
		}
		
		if(cancel!=-1) {
			for(int i=0; i<printerList.size(); i++) {
				foundRes=0;
				if((printerType.get(typeChoice-1).equals(printerList.get(i).getPrinterType()))&&(productList.get(i).getQuantity()>0)) {
					tempRes=printerList.get(i).getResolution();
					for(int j=0; j<resolution.size(); j++) {
						if(tempRes.equals(resolution.get(j))) {
							foundRes=1;
						}
					}
					if(foundRes==0) {
						resolution.remove("null");
						resolution.add(tempRes);
					}
				}
			}
			
			if(resolution.size()==1) {
				resChoice=1;
			}
			else {
				do {
					invalidInput=0;
					System.out.println();
					System.out.printf("%-10s------------------\n", "");
					System.out.printf("%-10s|   Resolution   |\n", "");
					System.out.printf("%-10s|   ==========   |\n", "");
					System.out.printf("%-10s|                |\n", "");
					for(int i=0; i<resolution.size(); i++) {
						System.out.printf("%-10s| %3d%-3s%s%-5s |\n", "", (i+1), ".", resolution.get(i), "dpi");
					}
					System.out.printf("%-10s|                |\n", "");
					System.out.printf("%-10s------------------\n\n", "");
					System.out.print("  Select Resolution(Enter -1 to cancel): ");
					try{
						resChoice=scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e){
						invalidInput=1;
						scanner.nextLine();
					}

					
					if((resChoice>resolution.size())||(resChoice==0)||(resChoice<-1)||(invalidInput==1)) {
						System.out.printf("\n%-10sInvalid Choice.\n", "");
						System.out.printf("%-10sPress enter to try again", "");
						scanner.nextLine();
						System.out.println();
					}
					else if(resChoice==-1) {
						cancel=-1;
					}
				}while((resChoice>resolution.size())||(resChoice==0)||(resChoice<-1)||(invalidInput==1));
			}
		}
		
		if(cancel!=-1) {
			for(int i=0; i<printerList.size(); i++) {
				foundDuplex=0;
				if((resolution.get(resChoice-1).equals(printerList.get(i).getResolution()))&&(printerType.get(typeChoice-1).equals(printerList.get(i).getPrinterType()))&&(productList.get(i).getQuantity()>0)) {
					tempDuplex=printerList.get(i).getDuplexCapability();
					for(int j=0; j<duplexCapability.size(); j++) {
						if(tempDuplex.equals(duplexCapability.get(j))) {
							foundDuplex=1;
						}
					}
					if(foundDuplex==0) {
						duplexCapability.remove("null");
						duplexCapability.add(tempDuplex);
					}
				}
			}
			
			if(duplexCapability.size()==1) {
				duplexChoice=1;
			}
		}
		
		
		if(cancel!=-1) {
			for(int i=0; i<printerList.size(); i++) {
				foundSeries=0;
				if((resolution.get(resChoice-1).equals(printerList.get(i).getResolution()))&&(printerType.get(typeChoice-1).equals(printerList.get(i).getPrinterType()))&&(duplexCapability.get(duplexChoice-1).equals(printerList.get(i).getDuplexCapability()))&&(printerList.get(i).getQuantity()>0)) {
					tempSeries=printerList.get(i).getSeries();
					for(int j=0; j<series.size(); j++) {
						if(tempSeries.equals(series.get(j))) {
							foundSeries=1;
						}
					}
					if(foundSeries==0) {
						series.remove("null");
						series.add(tempSeries);
					}
				}
			}
			
			if(series.size()==1) {
				seriesChoice=1;
			}
		}
		
		if(cancel!=-1) {
			for(int i=0; i<printerList.size(); i++) {
				foundColor=0;
				if((resolution.get(resChoice-1).equals(printerList.get(i).getResolution()))&&(printerType.get(typeChoice-1).equals(printerList.get(i).getPrinterType()))&&(duplexCapability.get(duplexChoice-1).equals(printerList.get(i).getDuplexCapability()))&&(series.get(seriesChoice-1).equals(printerList.get(i).getSeries()))&&(printerList.get(i).getQuantity()>0)) {
					tempColor=printerList.get(i).getColor();
					for(int j=0; j<color.size(); j++) {
						if(tempColor.equals(color.get(j))) {
							foundColor=1;
						}
					}
					if(foundColor==0) {
						color.remove("null");
						color.add(tempColor);
					}
				}
			}
			
			if(color.size()==1) {
				colorChoice=1;
			}
			else {
				do {
					invalidInput=0;
					System.out.println();
					System.out.printf("%-10s-----------------\n", "");
					System.out.printf("%-10s|     Color     |\n", "");
					System.out.printf("%-10s|     =====     |\n", "");
					System.out.printf("%-10s|               |\n", "");
					for(int i=0; i<color.size(); i++) {
						System.out.printf("%-10s| %3d%-3s%-7s |\n", "", (i+1), ".", color.get(i));
					}
					System.out.printf("%-10s|               |\n", "");
					System.out.printf("%-10s-----------------\n\n", "");
					System.out.print("  Select Color(Enter -1 to cancel): ");
					try{
						colorChoice=scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e){
						invalidInput=1;
						scanner.nextLine();
					}

					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1)) {
						System.out.printf("\n%-10sInvalid Choice.\n", "");
						System.out.printf("%-10sPress enter to try again", "");
						scanner.nextLine();
						System.out.println();
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1));
			}
			
		}

		if(cancel!=-1) {
			for(int i=0; i<printerList.size(); i++) {
				if((resolution.get(resChoice-1).equals(printerList.get(i).getResolution()))&&(printerType.get(typeChoice-1).equals(printerList.get(i).getPrinterType()))&&(duplexCapability.get(duplexChoice-1).equals(printerList.get(i).getDuplexCapability()))&&(series.get(seriesChoice-1).equals(printerList.get(i).getSeries()))&&(color.get(colorChoice-1).equals(printerList.get(i).getColor()))) {
					System.out.println();
					do {
						invalidInput=0;
						loopQuantity=0;
						System.out.println(printerList.get(i).toString());
						System.out.println("  Available Quantity: "+printerList.get(i).getQuantity());
						System.out.print("  Enter Quantity(Enter -1 to cancel): ");
						try{
							quantity=scanner.nextInt();
							scanner.nextLine();
						}
						catch(InputMismatchException e){
							invalidInput=1;
							scanner.nextLine();
						}

						
						if(quantity>printerList.get(i).getQuantity()) {
							System.out.println("  Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("  Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if((quantity==0)&&(invalidInput!=1)) {
							System.out.println("  Entered Quantity is 0. Please try again");
							loopQuantity=1;
						}
						else if(invalidInput==1) {
							System.out.println("  Quantity entered is not valid. Please try again");
							loopQuantity=1;
						}
						
						if(quantity==-1) {
							cancel=-1;
						}
					}while(loopQuantity==1);
					
					if(quantity>0) {
						product.setProductID(printerList.get(i).getProductID());
						product.setProductName(printerList.get(i).getProductName());
						product.setPrice(printerList.get(i).getPrice());
						product.setCategory(printerList.get(i).getCategory());
						product.setType(printerList.get(i).getType());
						product.setQuantity(quantity);
						product.setSeries(printerList.get(i).getSeries());
						product.setResolution(printerList.get(i).getResolution());
						product.setPrinterType(printerList.get(i).getPrinterType());
						product.setDuplexCapability(printerList.get(i).getDuplexCapability());
						product.setColor(printerList.get(i).getColor());
					}
				}
			}
		}
		
		
	}
	
	public static void scanners(ArrayList<Product> productList, Scanners product) {
		Scanner scanner=new Scanner(System.in);
		int seriesChoice=0, foundSeries, quantity=0, loopQuantity, cancel=0, invalidInput=0;;
		String tempSeries;
		ArrayList<Scanners> scannerList=new ArrayList<Scanners>();
		ArrayList<String> series=new ArrayList<String>();
		series.add("null");
		
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getType().equals("Scanners")&&(productList.get(i).getQuantity()>0)) {
				scannerList.add((Scanners)productList.get(i));
			}
		}
		
		for(int i=0; i<scannerList.size(); i++) {
			foundSeries=0;
			if(scannerList.get(i).getQuantity()>0) {
				tempSeries=scannerList.get(i).getSeries();
				for(int j=0; j<series.size(); j++) {
					if(tempSeries.equals(series.get(j))) {
						foundSeries=1;
					}
				}
				if(foundSeries==0) {
					series.remove("null");
					series.add(tempSeries);
				}
			}
		}
		
		if(series.size()==1) {
			seriesChoice=1;
		}
		else {
			do {
				invalidInput=0;
				System.out.printf("%-10s----------------------------\n", "");
				System.out.printf("%-10s|          Series          |\n", "");
				System.out.printf("%-10s|          ======          |\n", "");
				System.out.printf("%-10s|                          |\n", "");
				for(int i=0; i<series.size(); i++) {
					System.out.printf("%-10s| %3d%-3s%-18s |\n", "", (i+1), ".", series.get(i));
				}
				System.out.printf("%-10s|                          |\n", "");
				System.out.printf("%-10s----------------------------\n\n", "");
				System.out.print("  Select Series(Enter -1 to cancel): ");
				try{
					seriesChoice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){
					invalidInput=1;
					scanner.nextLine();
				}

				if((seriesChoice>series.size())||(seriesChoice==0)||(seriesChoice<-1)||(invalidInput==1)) {
					System.out.printf("\n%-10sInvalid Choice.\n", "");
					System.out.printf("%-10sPress enter to try again", "");
					scanner.nextLine();
					System.out.println();
				}
				else if(seriesChoice==-1) {
					cancel=-1;
				}
			}while((seriesChoice>series.size())||(seriesChoice==0)||(seriesChoice<-1)||(invalidInput==1));
		}
		
		if(cancel!=-1) {
			for(int i=0; i<scannerList.size(); i++) {
				if(series.get(seriesChoice-1).equals(scannerList.get(i).getSeries())) {
					System.out.println();
					do {
						invalidInput=0;
						loopQuantity=0;
						System.out.println(scannerList.get(i).toString());
						System.out.println("  Available Quantity: "+scannerList.get(i).getQuantity());
						System.out.print("  Enter Quantity(Enter -1 to cancel): ");
						quantity=scanner.nextInt();
						scanner.nextLine();
						
						if(quantity>scannerList.get(i).getQuantity()) {
							System.out.println("  Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("  Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if((quantity==0)&&(invalidInput!=1)) {
							System.out.println("  Entered Quantity is 0. Please try again");
							loopQuantity=1;
						}
						else if(invalidInput==1) {
							System.out.println("  Quantity entered is not valid. Please try again");
							loopQuantity=1;
						}
						else if(quantity==-1) {
							cancel=-1;
						}
					}while(loopQuantity==1);
					
					if(quantity>0) {
						product.setProductID(scannerList.get(i).getProductID());
						product.setProductName(scannerList.get(i).getProductName());
						product.setPrice(scannerList.get(i).getPrice());
						product.setCategory(scannerList.get(i).getCategory());
						product.setType(scannerList.get(i).getType());
						product.setQuantity(quantity);
						product.setSeries(scannerList.get(i).getSeries());
						product.setResolution(scannerList.get(i).getResolution());
						product.setColor(scannerList.get(i).getColor());
					}
				}
			}
		}
		
		
	}
	
	public static void microwave(ArrayList<Product> productList, Microwave product) {
		Scanner scanner=new Scanner(System.in);
		int sizeNCapChoice=0, colorChoice=0, foundSize, foundCap, foundColor, quantity=0, loopQuantity, cancel=0, invalidInput=0;;
		double tempCap;
		String tempSize, tempColor;
		ArrayList<Microwave> microwaveList= new ArrayList<Microwave>();
		ArrayList<Double> capacity=new ArrayList<Double>();
		ArrayList<String> size=new ArrayList<String>();
		ArrayList<String> color=new ArrayList<String>();
		capacity.add(0.0);
		size.add("null");
		color.add("null");
		
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getType().equals("Microwave")&&(productList.get(i).getQuantity()>0)) {
				microwaveList.add((Microwave)productList.get(i));
			}
		}
		
		for(int i=0; i<microwaveList.size(); i++) {
			foundSize=0;
			foundCap=0;
			if(microwaveList.get(i).getQuantity()>0) {
				tempSize=microwaveList.get(i).getSize();
				tempCap=microwaveList.get(i).getCapacity();
				for(int j=0; j<size.size(); j++) {
					if(tempSize.equals(size.get(j))) {
						foundSize=1;
					}
				}
				for(int j=0; j<capacity.size(); j++) {
					if(tempCap==capacity.get(j)) {
						foundCap=1;
					}
				}
				if(foundSize==0) {
					size.remove("null");
					size.add(tempSize);
				}
				if(foundCap==0) {
					capacity.remove(Double.valueOf(0.0));
					capacity.add(tempCap);
				}
			}
		}
		
		if(size.size()==1) {
			sizeNCapChoice=1;
		}
		else {
			do {
				invalidInput=0;
				System.out.println();
				System.out.printf("%-10s------------------------------\n", "");
				System.out.printf("%-10s|    |    Size    | Capacity |\n", "");
				
				for(int i=0; i<size.size(); i++) {
					System.out.printf("%-10s|----+------------+----------|\n", "");
					System.out.printf("%-10s| %d%s | %-10s | %3s%-5.0f |\n", "", (i+1), ".", size.get(i), "", capacity.get(i));
				}
				System.out.printf("%-10s------------------------------\n\n", "");
			
				System.out.print("  Select Size(Enter -1 to cancel): ");
				try{
					sizeNCapChoice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){
					invalidInput=1;
					scanner.nextLine();
				}

				
				if((sizeNCapChoice>size.size())||(sizeNCapChoice==0)||(sizeNCapChoice<-1)||(invalidInput==1)) {
					System.out.printf("\n%-10sInvalid Choice.\n", "");
					System.out.printf("%-10sPress enter to try again", "");
					scanner.nextLine();
					System.out.println();
				}
				else if(sizeNCapChoice==-1) {
					cancel=-1;
				}
			}while((sizeNCapChoice>size.size())||(sizeNCapChoice==0)||(sizeNCapChoice<-1)||(invalidInput==1));
		}
		
		
		if(cancel!=-1) {
			for(int i=0; i<microwaveList.size(); i++) {
				foundColor=0;
				if((size.get(sizeNCapChoice-1).equals(microwaveList.get(i).getSize()))&&(capacity.get(sizeNCapChoice-1)==microwaveList.get(i).getCapacity())&&(microwaveList.get(i).getQuantity()>0)) {
					tempColor=microwaveList.get(i).getColor();
					for(int j=0; j<color.size(); j++) {
						if(tempColor.equals(color.get(j))) {
							foundColor=1;
						}
					}
					if(foundColor==0) {
						color.remove("null");
						color.add(tempColor);
					}
				}
			}
			
			
			if(color.size()==1) {
				colorChoice=1;
			}
			else {
				do {
					invalidInput=0;
					System.out.println();
					System.out.printf("%-10s-----------------\n", "");
					System.out.printf("%-10s|     Color     |\n", "");
					System.out.printf("%-10s|     =====     |\n", "");
					System.out.printf("%-10s|               |\n", "");
					for(int i=0; i<color.size(); i++) {
						System.out.printf("%-10s| %3d%-3s%-7s |\n", "", (i+1), ".", color.get(i));
					}
					System.out.printf("%-10s|               |\n", "");
					System.out.printf("%-10s-----------------\n\n", "");
				
					System.out.print("  Select Color(Enter -1 to cancel): ");
					try{
						colorChoice=scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e){
						invalidInput=1;
						scanner.nextLine();
					}

					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1)) {
						System.out.printf("\n%-10sInvalid Choice.\n", "");
						System.out.printf("%-10sPress enter to try again", "");
						scanner.nextLine();
						System.out.println();
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)||(invalidInput==1));
			}
		}
		
		if(cancel!=-1) {
			for(int i=0; i<microwaveList.size(); i++) {
				if((size.get(sizeNCapChoice-1).equals(microwaveList.get(i).getSize()))&&(capacity.get(sizeNCapChoice-1)==microwaveList.get(i).getCapacity())&&(color.get(colorChoice-1).equals(microwaveList.get(i).getColor()))) {
					do {
						invalidInput=0;
						loopQuantity=0;
						System.out.println(microwaveList.get(i).toString());
						System.out.println("  Available Quantity: "+microwaveList.get(i).getQuantity());
						System.out.print("  Enter Quantity(Enter -1 to cancel): ");
						try{
							quantity=scanner.nextInt();
							scanner.nextLine();
						}
						catch(InputMismatchException e){
							invalidInput=1;
							scanner.nextLine();
						}

						
						if(quantity>microwaveList.get(i).getQuantity()) {
							System.out.println("  Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("  Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if((quantity==0)&&(invalidInput!=1)) {
							System.out.println("  Entered Quantity is 0. Please try again");
							loopQuantity=1;
						}
						else if(invalidInput==1) {
							System.out.println("  Quantity entered is not valid. Please try again");
							loopQuantity=1;
						}
						else if(quantity==-1) {
							cancel=-1;
						}
					}while(loopQuantity==1);
					
					if(quantity>0) {
						product.setProductID(microwaveList.get(i).getProductID());
						product.setProductName(microwaveList.get(i).getProductName());
						product.setPrice(microwaveList.get(i).getPrice());
						product.setCategory(microwaveList.get(i).getCategory());
						product.setType(microwaveList.get(i).getType());
						product.setQuantity(quantity);
						product.setSize(microwaveList.get(i).getSize());
						product.setMicrowaveType(microwaveList.get(i).getMicrowaveType());
						product.setCapacity(microwaveList.get(i).getCapacity());
						product.setColor(microwaveList.get(i).getColor());
					}
				}
			}
		}
		
		
	}
	
	public static void smartwatch(ArrayList<Product> productList, SmartWatch product) {
		Scanner scanner=new Scanner(System.in);
		int scrSizeChoice=0, resChoice=0, colorChoice=0, foundScrSize, foundRes, foundColor, quantity=0, loopQuantity, cancel=0, invalidInput=0;;
		double tempScrSize;
		String tempRes, tempColor;
		ArrayList<SmartWatch> watchList=new ArrayList<SmartWatch>();
		ArrayList<Double> scrSize=new ArrayList<Double>();
		ArrayList<String> resolution=new ArrayList<String>();
		ArrayList<String> color=new ArrayList<String>();
		scrSize.add(0.0);
		resolution.add("null");
		color.add("null");
		
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).getType().equals("Smart Watch")&&(productList.get(i).getQuantity()>0)) {
				watchList.add((SmartWatch)productList.get(i));
			}
		}
		
		for(int i=0; i<watchList.size(); i++) {
			foundScrSize=0;
			if(watchList.get(i).getQuantity()>0) {
				tempScrSize=watchList.get(i).getScreenSize();
				for(int j=0; j<scrSize.size(); j++) {
					if(tempScrSize==scrSize.get(j)) {
						foundScrSize=1;
					}
				}
				if(foundScrSize==0) {
					scrSize.remove(Double.valueOf(0.0));
					scrSize.add(tempScrSize);
				}
			}
		}
		
		
		if(scrSize.size()==1) {
			scrSizeChoice=1;
		}
		else {
			do {
				invalidInput=0;
				System.out.println();
				System.out.printf("%-10s----------------\n", "");
				System.out.printf("%-10s|  Screen Size |\n", "");
				System.out.printf("%-10s|  =========== |\n", "");
				System.out.printf("%-10s|              |\n", "");
				for(int i=0; i<scrSize.size(); i++) {
					System.out.printf("%-10s| %3d%-3s%.0f%-4s |\n", "", (i+1), ".", scrSize.get(i), "mm");
				}
				System.out.printf("%-10s|              |\n", "");
				System.out.printf("%-10s----------------\n", "");
				System.out.print("  Select Screen Size(Enter -1 to cancel): ");
				try{
					scrSizeChoice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){
					invalidInput=1;
					scanner.nextLine();
				}

				if((scrSizeChoice>scrSize.size())||(scrSizeChoice==0)||(scrSizeChoice<-1)||(invalidInput==1)) {
					System.out.printf("\n%-10sInvalid Choice.\n", "");
					System.out.printf("%-10sPress enter to try again", "");
					scanner.nextLine();
					System.out.println();
				}
				else if(scrSizeChoice==-1) {
					cancel=-1;
				}
			}while((scrSizeChoice>scrSize.size())||(scrSizeChoice==0)||(scrSizeChoice<-1)||(invalidInput==1));
		}
		
		if(cancel!=-1) {
			for(int i=0; i<watchList.size(); i++) {
				foundRes=0;
				if((scrSize.get(scrSizeChoice-1)==watchList.get(i).getScreenSize())&&(watchList.get(i).getQuantity()>0)) {
					tempRes=watchList.get(i).getResolution();
					for(int j=0; j<resolution.size(); j++) {
						if(tempRes.equals(resolution.get(j))) {
							foundRes=1;
						}
					}
					if(foundRes==0) {
						resolution.remove("null");
						resolution.add(tempRes);
					}
				}
			}
			
			
			if(resolution.size()==1) {
				resChoice=1;
			}
			else {
				do {
					invalidInput=0;
					System.out.println();
					System.out.printf("%-10s------------------\n", "");
					System.out.printf("%-10s|   Resolution   |\n", "");
					System.out.printf("%-10s|   ==========   |\n", "");
					System.out.printf("%-10s|                |\n", "");
					for(int i=0; i<resolution.size(); i++) {
						System.out.printf("%-10s| %3d%-2s%-9s |\n", "", (i+1), ".", resolution.get(i));
					}
					System.out.printf("%-10s|                |\n", "");
					System.out.printf("%-10s------------------\n\n", "");
					System.out.print("  Select Resolution(Enter -1 to cancel): ");
					try{
						resChoice=scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e){
						invalidInput=1;
						scanner.nextLine();
					}
					
					if((resChoice>resolution.size())||(resChoice==0)||(resChoice<-1)||(invalidInput==1)) {
						System.out.printf("\n%-10sInvalid Choice.\n", "");
						System.out.printf("%-10sPress enter to try again", "");
						scanner.nextLine();
						System.out.println();
					}	
					else if(resChoice==-1) {
						cancel=-1;
					}
				}while((resChoice>resolution.size())||(resChoice==0)||(resChoice<-1)||(invalidInput==1));
			}
		}
		
		if(cancel!=-1) {
			for(int i=0; i<watchList.size(); i++) {
				foundColor=0;
				if((scrSize.get(scrSizeChoice-1)==watchList.get(i).getScreenSize())&&(resolution.get(resChoice-1).equals(watchList.get(i).getResolution()))&&(watchList.get(i).getQuantity()>0)) {
					tempColor=watchList.get(i).getColor();
					for(int j=0; j<color.size(); j++) {
						if(tempColor.equals(color.get(j))) {
							foundColor=1;
						}
					}
					if(foundColor==0) {
						color.remove("null");
						color.add(tempColor);
					}
				}
			}
			
			if(color.size()==1){
				colorChoice=1;
			}
		}
		
		if(cancel!=-1) {
			for(int i=0; i<watchList.size(); i++) {
				if((scrSize.get(scrSizeChoice-1)==watchList.get(i).getScreenSize())&&(resolution.get(resChoice-1).equals(watchList.get(i).getResolution()))&&(color.get(colorChoice-1).equals(watchList.get(i).getColor()))) {
					do {
						invalidInput=0;
						loopQuantity=0;
						System.out.println(watchList.get(i).toString());
						System.out.println("  Available Quantity: "+watchList.get(i).getQuantity());
						System.out.print("  Enter Quantity(Enter -1 to cancel): ");
						try{
							quantity=scanner.nextInt();
							scanner.nextLine();
						}
						catch(InputMismatchException e){
							invalidInput=1;
							scanner.nextLine();
						}

						
						if(quantity>watchList.get(i).getQuantity()) {
							System.out.println("  Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("  Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if((quantity==0)&&(invalidInput!=1)) {
							System.out.println("  Entered Quantity is 0. Please try again");
							loopQuantity=1;
						}
						else if(invalidInput==1) {
							System.out.println("  Quantity entered is not valid. Please try again");
							loopQuantity=1;
						}
						else if(quantity==-1) {
							cancel=-1;
						}
					}while(loopQuantity==1);
					
					if(quantity>0) {
						product.setProductID(watchList.get(i).getProductID());
						product.setProductName(watchList.get(i).getProductName());
						product.setPrice(watchList.get(i).getPrice());
						product.setCategory(watchList.get(i).getCategory());
						product.setType(watchList.get(i).getType());
						product.setQuantity(quantity);
						product.setScreenSize(watchList.get(i).getScreenSize());
						product.setResolution(watchList.get(i).getResolution());
						product.setSeries(watchList.get(i).getSeries());
						product.setBatteryLife(watchList.get(i).getBatteryLife());
						product.setWaterResistance(watchList.get(i).getWaterResistance());
						product.setColor(watchList.get(i).getColor());
					}
				}
			}
		}
		
		
	}
	
	public static void editCart(ArrayList<Product> productList, Cart cart) {
		Scanner scanner=new Scanner(System.in);
		int choice=0, cancel=0, invalidInput=0;;
		char editChoice='N';
		
		
		do {
			editChoice='N';
			if(cart.getNoOfProducts()>0) {
				System.out.println(cart.toString());
				
				do {
					invalidInput=0;
					System.out.printf("%-10s---------------------------------------------\n","");
					System.out.printf("%-10s| Which function would you like to perform? |\n", "");
					System.out.printf("%-10s| ========================================= |\n", "");
					System.out.printf("%-10s|                                           |\n", "");
					System.out.printf("%-10s|         1. Edit Quantity of Item          |\n", "");
					System.out.printf("%-10s|         2. Remove Item from Cart          |\n", "");
					System.out.printf("%-10s|                                           |\n", "");
					System.out.printf("%-10s---------------------------------------------\n\n","");
					System.out.print("  Please enter your choice(Enter -1 to cancel): ");
					try{
						choice=scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e){
						invalidInput=1;
						scanner.nextLine();
					}
					
					if((choice>2)||(choice==0)||(choice<-1)||(invalidInput==1)) {
						System.out.printf("\n%-10sInvalid Choice.\n", "");
						System.out.printf("%-10sPress enter to try again", "");
						scanner.nextLine();
						System.out.println();
					}
					else if(choice==-1) {
						cancel=-1;
					}
				}while((choice>2)||(choice==0)||(choice<-1)||(invalidInput==1));
				
				if(cancel!=-1) {
					switch(choice) {
					case 1: editQuantity(productList, cart); break;
					case 2: removeItem(productList, cart); break;
					}
					
					if(cart.getNoOfProducts()>0) {
						System.out.print("  Would you like to make anymore modifications to the cart? (Y=yes)");
						editChoice=Character.toUpperCase(scanner.next().charAt(0));
						scanner.nextLine();
					}
					
				}
			}
		}while(editChoice=='Y');
		
	}
	
	public static void editQuantity(ArrayList<Product> productList, Cart cart) {
		Scanner scanner = new Scanner(System.in);
		int itemChoice=0, qty=0, loopQty, difference, cancel=0, invalidInput=0;
		char editConfirm;
		
		
		do {
			invalidInput=0;
			System.out.printf("%-10s-------------------------------------------------------------------------------------\n", "");
			System.out.printf("%-10s|                                                                                   |\n", "");
			System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
			System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","----------", "------------", "------------------", "--------", "-----");
			
			for(int i=0; i<cart.getNoOfProducts(); i++) {
					System.out.printf("%-5s%-5d|    %-15s%-17s%-23.2f%-13d%-10.2f |\n", "", (i+1),cart.getProduct()[i].getProductID(), cart.getProduct()[i].getProductName(), cart.getProduct()[i].getPrice(), cart.getProduct()[i].getQuantity(), cart.getPricePerItem()[i]);	
			}
			System.out.printf("%-10s|                                                                                   |\n", "");
			System.out.printf("%-10s-------------------------------------------------------------------------------------\n\n", "");
			System.out.print("  Select item to edit(Enter -1 to cancel): ");
			try{
				itemChoice=scanner.nextInt();
				scanner.nextLine();
			}
			catch(InputMismatchException e){
				invalidInput=1;
				scanner.nextLine();
			}
			
			if((itemChoice>cart.getNoOfProducts())||(itemChoice==0)||(itemChoice<-1)||(invalidInput==1)) {
				System.out.printf("\n%-10sInvalid Choice.\n", "");
				System.out.printf("%-10sPress enter to try again", "");
				scanner.nextLine();
				System.out.println();
			}
			else if(itemChoice==-1) {
				cancel=-1;
			}
		}while((itemChoice>cart.getNoOfProducts())||(itemChoice==0)||(itemChoice<-1)||(invalidInput==1));
		
		if(cancel!=-1) {
			for(int i=0; i<productList.size(); i++) {
				if(cart.getProduct()[itemChoice-1].getProductID().equals(productList.get(i).getProductID())) {
					do {
						invalidInput=0;
						loopQty=0;
						System.out.println("  Available Quantity: "+(productList.get(i).getQuantity()+cart.getProduct()[itemChoice-1].getQuantity()));
						System.out.print("  Enter Quantity(Enter -1 to cancel): ");
						try{
							qty=scanner.nextInt();
							scanner.nextLine();
						}
						catch(InputMismatchException e){
							invalidInput=1;
							scanner.nextLine();
						}
						
						if(qty>(productList.get(i).getQuantity()+cart.getProduct()[itemChoice-1].getQuantity())) {
							System.out.println("  Entered Quantity exceeds Available Quantity. Please try again");
							loopQty=1;
						}
						else if(qty<-1){
							System.out.println("  Entered Quantity is less than 0. Please try again");
							loopQty=1;
						}
						else if((qty==0)&&(invalidInput!=1)) {
							System.out.println("  Entered Quantity is 0. Please try again");
							loopQty=1;
						}
						else if(invalidInput==1) {
							System.out.println("  Quantity entered is not valid. Please try again");
							loopQty=1;
						}
						else if(qty==-1) {
							cancel=-1;
						}
					}while(loopQty==1);
					
					if(cancel!=-1) {
						System.out.print("  Are you sure you want to change the quantity of this item? (Y=yes) ");
						editConfirm=Character.toUpperCase(scanner.next().charAt(0));
						scanner.nextLine();
						
						if(editConfirm=='Y') {
							if(qty>cart.getProduct()[itemChoice-1].getQuantity()) {
								difference=qty-cart.getProduct()[itemChoice-1].getQuantity();
								productList.get(i).setQuantity(productList.get(i).getQuantity()-difference);
							}
							else if(qty<cart.getProduct()[itemChoice-1].getQuantity()) {
								difference=cart.getProduct()[itemChoice-1].getQuantity()-qty;
								productList.get(i).setQuantity(productList.get(i).getQuantity()+difference);
							}
							else {
								System.out.println("  Quantity was not changed.");
							}
							
							if(qty!=cart.getProduct()[itemChoice-1].getQuantity()) {
								cart.modifyQuantity(itemChoice-1, qty);
							}
							
						}
						else {
							System.out.println(" Quantity was not changed.");
						}
					}
				}
			}
		}
		
	}
	
	public static void removeItem(ArrayList<Product> productList, Cart cart) {
		Scanner scanner=new Scanner(System.in);
		int itemChoice=0, cancel=0, invalidInput=0;
		char removeConfirm='N';
		
		do {
			invalidInput=0;
			System.out.printf("%-10s-------------------------------------------------------------------------------------\n", "");
			System.out.printf("%-10s|                                                                                   |\n", "");
			System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
			System.out.printf("%-10s|    %-15s%-17s%-23s%-13s%-10s |\n", "","----------", "------------", "------------------", "--------", "-----");
			for(int i=0; i<cart.getNoOfProducts(); i++) {
				System.out.printf("%-5s%-5d|    %-15s%-17s%-23.2f%-13d%-10.2f |\n", "", (i+1),cart.getProduct()[i].getProductID(), cart.getProduct()[i].getProductName(), cart.getProduct()[i].getPrice(), cart.getProduct()[i].getQuantity(), cart.getPricePerItem()[i]);	
			}
			System.out.printf("%-10s|                                                                                   |\n", "");
			System.out.printf("%-10s-------------------------------------------------------------------------------------\n", "");
			
			System.out.print("  Select item to remove(Enter -1 to cancel): ");
			try{
				itemChoice=scanner.nextInt();
				scanner.nextLine();
			}
			catch(InputMismatchException e){
				invalidInput=1;
				scanner.nextLine();
			}
			
			
			if((itemChoice>cart.getNoOfProducts())||(itemChoice==0)||(itemChoice<-1)||(invalidInput==1)) {
				System.out.printf("\n%-10sInvalid Choice.\n", "");
				System.out.printf("%-10sPress enter to try again", "");
				scanner.nextLine();
				System.out.println();
			}
			else if(itemChoice==-1) {
				cancel=-1;
			}
		}while((itemChoice>cart.getNoOfProducts())||(itemChoice==0)||(itemChoice<-1)||(invalidInput==1));
		
		if(cancel!=-1) {
			System.out.print("  Are you sure you want to remove this item? (Y=yes) ");
			removeConfirm=Character.toUpperCase(scanner.next().charAt(0));
			scanner.nextLine();
			
			if(removeConfirm=='Y') {
				for(int i=0; i<productList.size(); i++) {
					if(cart.getProduct()[itemChoice-1].getProductID().equals(productList.get(i).getProductID())) {
						productList.get(i).setQuantity(productList.get(i).getQuantity()+cart.getProduct()[itemChoice-1].getQuantity());
					}
				}
				cart.removeItem(itemChoice-1);
			}
			else {
				System.out.println("  Item not removed.");
			}
		}
		
		
	}
	
	public static void onHoldPayment(ArrayList<Payment> paymentList, ArrayList<Member> memberList, Staff staff, ArrayList<Product> productList, ArrayList<Receipt> receiptList, BankAccount bankAccount) {
		ArrayList<Payment> tempPayment=new ArrayList<Payment>();
		Scanner scanner=new Scanner(System.in);
		int choice=0, cancel=0, funcChoice=0, onHold=0, cancel1=0, invalidInput=0;;
		char paymentChoice='N';
		Receipt receipt=null;
		
		for(int i=0; i<paymentList.size(); i++) {
			if(paymentList.get(i).getStatus().equals("On-Hold")) {
				tempPayment.add(paymentList.get(i));
			}
		}
		
		do {
			cancel=0;
			onHold=0;
			if(tempPayment.size()==0) {
				System.out.printf("%-30sThere are no On-Hold Payments\n", "");
			}
			else {
				do {
					invalidInput=0;
					onHold=1;
					System.out.printf("%-40sList of On-Hold Payments\n", "");
					System.out.printf("%-40s========================\n", "");
					for(int i=0; i<tempPayment.size(); i++) {
						System.out.printf("%-5s%-5d","", (i+1));
						System.out.printf("-----------------------------------------------------------------------------------\n");
						System.out.printf("%-10s| %-15s%-17s%-23s%-10s%14s |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
						System.out.printf("%-10s| %-15s%-17s%-23s%-10s%s |\n", "", "----------", "------------", "------------------", "--------", "--------------");
						for(int j=0; j<tempPayment.get(i).getCart().getNoOfProducts(); j++) {
							System.out.printf("%-10s| %-15s%-17s%-23.2f%-8d%16.2f |\n", "", tempPayment.get(i).getCart().getProduct()[j].getProductID(), tempPayment.get(i).getCart().getProduct()[j].getProductName(), tempPayment.get(i).getCart().getProduct()[j].getPrice(), tempPayment.get(i).getCart().getProduct()[j].getQuantity(), tempPayment.get(i).getCart().getPricePerItem()[j]);
						}
						System.out.printf("%-10s| ------------------------------------------------------------------------------- |\n", "");
						System.out.printf("%-10s| %65s%14.2f |\n", "", "Subtotal(RM): ", tempPayment.get(i).getCart().getTotalPrice());
						System.out.printf("%-10s-----------------------------------------------------------------------------------\n\n", "");
					}
					System.out.print("  Select Choice(Enter -1 to cancel): ");
					try{
						choice=scanner.nextInt();
						scanner.nextLine();
					}
					catch(InputMismatchException e){
						invalidInput=1;
						scanner.nextLine();
					}
					
					if((choice>tempPayment.size())||(choice==0)||(choice<-1)||(invalidInput==1)) {
						System.out.printf("\n%-10sInvalid Choice.\n", "");
						System.out.printf("%-10sPress enter to try again", "");
						scanner.nextLine();
						System.out.println();
					}
					else if(choice==-1) {
						cancel1=-1;
						cancel=-1;
						break;
					}
				}while((choice>tempPayment.size())||(choice==0)||(choice<-1)||(invalidInput==1));
				
				if(cancel1!=-1) {
					System.out.printf("%-10s-----------------------------------------------------------------------------------\n", "");
					System.out.printf("%-10s| %-15s%-17s%-23s%-10s%14s |\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
					System.out.printf("%-10s| %-15s%-17s%-23s%-10s%s |\n", "", "----------", "------------", "------------------", "--------", "--------------");
					for(int j=0; j<tempPayment.get(choice-1).getCart().getNoOfProducts(); j++) {
						System.out.printf("%-10s| %-15s%-17s%-23.2f%-8d%16.2f |\n", "", tempPayment.get(choice-1).getCart().getProduct()[j].getProductID(), tempPayment.get(choice-1).getCart().getProduct()[j].getProductName(), tempPayment.get(choice-1).getCart().getProduct()[j].getPrice(), tempPayment.get(choice-1).getCart().getProduct()[j].getQuantity(), tempPayment.get(choice-1).getCart().getPricePerItem()[j]);
					}
					System.out.printf("%-10s| ------------------------------------------------------------------------------- |\n", "");
					System.out.printf("%-10s| %65s%14.2f |\n", "", "Subtotal(RM): ", tempPayment.get(choice-1).getCart().getTotalPrice());
					System.out.printf("%-10s-----------------------------------------------------------------------------------\n\n", "");
					
				}
					
					if(cancel!=-1) {
						
						do {
							invalidInput=0;
							System.out.printf("%-10s-------------------------------------------\n", "");
							System.out.printf("%-10s| Which action would you like to perform? |\n", "");
							System.out.printf("%-10s| ======================================= |\n", "");
							System.out.printf("%-10s|                                         |\n", "");
							System.out.printf("%-10s|        1. Edit items in cart            |\n", "");
							System.out.printf("%-10s|        2. Proceed with payment          |\n", "");
							System.out.printf("%-10s|                                         |\n", "");
							System.out.printf("%-10s-------------------------------------------\n\n", "");
							System.out.printf("%-10sSelect choice(Enter -1 to cancel): ", "");
							try{
								funcChoice=scanner.nextInt();
								scanner.nextLine();
							}
							catch(InputMismatchException e){
								invalidInput=1;
								scanner.nextLine();
							}
							
							
							if((funcChoice>2)||(funcChoice==0)||(funcChoice<-1)||(invalidInput==1)) {
								System.out.printf("\n%-10sInvalid Choice.\n", "");
								System.out.printf("%-10sPress enter to try again", "");
								scanner.nextLine();
								System.out.println();
							}
							else if(funcChoice==-1) {
								cancel=-1;
							}
						}while((funcChoice>2)||(funcChoice==0)||(funcChoice<-1)||(invalidInput==1));
						
					}
					
					if(cancel!=-1) {
						switch(funcChoice) {
						case 1: editCart(productList, paymentList.get(choice-1).getCart()); break;
						case 2: paymentFunc(tempPayment.get(choice-1), memberList, staff, bankAccount, receiptList, productList); break;
						}
						
						if(funcChoice==1) {
							if(tempPayment.get(choice-1).getCart().getNoOfProducts()==0) {
								paymentFunc(tempPayment.get(choice-1), memberList, staff, bankAccount, receiptList, productList);
								
							}
							else {
								System.out.print("  Would you like to proceed with payment? (Y=yes) ");
								paymentChoice=Character.toUpperCase(scanner.next().charAt(0));
								scanner.nextLine();
								
								if(paymentChoice=='Y') {
									paymentFunc(tempPayment.get(choice-1), memberList, staff, bankAccount, receiptList, productList);
								}
							}
						}
						
						if((tempPayment.get(choice-1).getStatus().equals("Completed"))||(tempPayment.get(choice-1).getStatus().equals("Cancelled"))) {
							tempPayment.remove(choice-1);
						}
					}
			}
		}while((onHold==1)&&(cancel1!=-1));
		
		

	}
	
	public static void displayQR() {
		ImageIcon image=new ImageIcon("src\\TNG.jpg");
		Image tngImage=image.getImage();
		Image modifiedImage=tngImage.getScaledInstance(500, 700, Image.SCALE_SMOOTH);
		image=new ImageIcon(modifiedImage);
		
		JLabel label=new JLabel();
		label.setIcon(image);
		
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(500, 700);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(label);
		frame.setAlwaysOnTop(true);
	}
	
	public static void returnQuantity(ArrayList<Product> productList, Cart cart) {
		for(int i=0; i<productList.size(); i++) {
			for(int j=0; j<cart.getNoOfProducts(); j++) {
				if(productList.get(i).getProductID().equals(cart.getProduct()[j].getProductID())) {
					productList.get(i).setQuantity(productList.get(i).getQuantity()+cart.getProduct()[j].getQuantity());
				}
			}
		}
	}
	
	public static void paymentFunc(Payment payment, ArrayList<Member> memberList, Staff staff, BankAccount bankAccount, ArrayList<Receipt> receiptList, ArrayList<Product> productList) {
		Scanner scanner=new Scanner(System.in);
		String memID, creditCardNo, paymentMethod = "";
		int memIDNo, checkMemID, paymentMChoice=0, paymentChoice=0, foundMem=0, retryPayment, paymentError=0, cancel=0, invalidInput=0, charCount=0;
		double receivedAmount = 0, balance;
		Receipt receipt=null;
		payment.setStaff(staff);
		if(payment.getCart().getNoOfProducts()>0) {
			System.out.println("  Cart Details");
			System.out.println(payment.getCart().toString());
		}
		
		
		if(payment.getCart().getNoOfProducts()==0) {
			payment.setPaymentDetails(0, "null", "Cancelled");
			System.out.println("  Cart is empty. Payment has been cancelled");
		}
		else {
			do {
				invalidInput=0;
				System.out.printf("%-10s-------------------------------------------\n", "");
				System.out.printf("%-10s| Which action would you like to perform? |\n", "");
				System.out.printf("%-10s| ======================================= |\n", "");
				System.out.printf("%-10s|                                         |\n", "");
				System.out.printf("%-10s|         1. Complete payment             |\n", "");
				System.out.printf("%-10s|         2. Cancel payment               |\n", "");
				System.out.printf("%-10s|                                         |\n", "");
				System.out.printf("%-10s-------------------------------------------\n", "");
				System.out.print("  Select Choice(Press -1 to exit): ");
				try{
					paymentChoice=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){
					invalidInput=1;
					scanner.nextLine();
				}

				if((paymentChoice>2)||(paymentChoice==0)||(paymentChoice<-1)||(invalidInput==1)) {
					System.out.printf("\n%-10sInvalid Choice.\n", "");
					System.out.printf("%-10sPress enter to try again", "");
					scanner.nextLine();
					System.out.println();
				}
				else if(paymentChoice==-1) {
					cancel=-1;
				}
			}while((paymentChoice>2)||(paymentChoice==0)||(paymentChoice<-1)||(invalidInput==1));
			
			if(cancel!=-1) {
				if(paymentChoice==2) {
					payment.setPaymentDetails(0, "null", "Cancelled");
					returnQuantity(productList, payment.getCart());
				}
				else {
					do { 
						cancel=0;
						checkMemID=0;
						System.out.print("  Enter Member ID(Enter X if there is no memberID): ");
						memID=scanner.next();
						scanner.nextLine();
						if(memID.toUpperCase().equals("X")) {
							Member member=null;
							payment.memberDiscount(member);
						}
						else if((memID.charAt(0)=='M')&&(memID.length()==5)){
							memID=memID.replace("M", "");
							memIDNo=Integer.parseInt(memID);
							checkMemID=Claris.checkMember(memberList, memIDNo);
							if(checkMemID==-1) {
								System.out.printf("\n%-10sMember does not exist.\n", "");
								System.out.printf("%-10sPress enter to try again", "");
								scanner.nextLine();
								System.out.println();
							}
							else{
								payment.memberDiscount(memberList.get(checkMemID));
							}
						}
						else if(memID.equals("-1")) {
							cancel=-1;
						}
						else {
							checkMemID=-1;
							System.out.printf("\n%-10sInvalid Choice.\n", "");
							System.out.printf("%-10sPress enter to try again", "");
							scanner.nextLine();
							System.out.println();
						}
						
						
					}while(checkMemID==-1);
					
					if(cancel!=-1) {
						System.out.printf("  Final Payment Amount(RM): %.2f\n", payment.getPaymentAmount());
						do {
							do {
								invalidInput=0;
								paymentMChoice=0;
								paymentError=0;
								retryPayment=0;
								System.out.printf("%-10s-----------------------------------\n","");
								System.out.printf("%-10s|                                 |\n","");
								System.out.printf("%-10s|         Payment Methods         |\n","");
								System.out.printf("%-10s|         ===============         |\n","");
								System.out.printf("%-10s|                                 |\n","");
								System.out.printf("%-10s|     1. Cash                     |\n","");
								System.out.printf("%-10s|     2. Credit Card              |\n","");
								System.out.printf("%-10s|     3. Touch N Go E-Wallet      |\n","");
								System.out.printf("%-10s|                                 |\n","");
								System.out.printf("%-10s-----------------------------------\n","");
								System.out.print("  Select Payment Method(Enter -1 to cancel): ");
								try{
									paymentMChoice=scanner.nextInt();
									scanner.nextLine();
								}
								catch(InputMismatchException e){
									invalidInput=1;
									scanner.nextLine();
								}
								
								
								if((paymentMChoice>3)||(paymentMChoice==0)||(paymentMChoice<-1)||(invalidInput==1)) {
									System.out.printf("\n%-10sInvalid Choice.\n", "");
									System.out.printf("%-10sPress enter to try again", "");
									scanner.nextLine();
									System.out.println();
								}
								else if(paymentMChoice==-1) {
									cancel=-1;
								}
							}while((paymentMChoice>3)||(paymentMChoice==0)||(paymentMChoice<-1)||(invalidInput==1));
							
							if(cancel!=-1) {
								do {
									charCount=0;
									invalidInput=0;
									paymentError=0;
									retryPayment=0;
									if(paymentMChoice==1) {
										
										System.out.print("  Enter Amount received(Enter -1 to cancel): ");
										try{
											receivedAmount=scanner.nextDouble();
											scanner.nextLine();
										}
										catch(InputMismatchException e){
											invalidInput=1;
											scanner.nextLine();
										}
										
										
										if((receivedAmount>-1&&receivedAmount<payment.getPaymentAmount())||(receivedAmount<-1)) {
											System.out.println("  Insufficient Amount");
											paymentError=1;
										}
										else if(receivedAmount==-1) {
											cancel=-1;
										}
										else if(invalidInput==1) {
											System.out.println("  Invalid Amount entered");
											paymentError=1;
										}
										else {
											paymentMethod="Cash";
										}
									}
									else if(paymentMChoice==2){
										System.out.print("  Enter Credit Card Number(Enter -1 to cancel): ");
										creditCardNo=scanner.next();
										scanner.nextLine();
										for(int i=0; i<creditCardNo.length(); i++) {
											if(Character.isLetter(creditCardNo.charAt(i))) {
												charCount++;
											}
											else if(!Character.isLetter(creditCardNo.charAt(i))&&!Character.isDigit(creditCardNo.charAt(i))) {
												charCount++;
											}
										}
										if(((creditCardNo.length()!=16)||(charCount>=1))&&(creditCardNo.equals("-1")==false)) {
											System.out.println("  Invalid Credit Card Number.");
											paymentError=1;
										}
										else if(creditCardNo.equals("-1")) {
											cancel=-1;
										}
										else {
											receivedAmount=payment.getPaymentAmount();
											paymentMethod="Credit Card";
										}
									}
									else if(paymentMChoice==3) {
										displayQR();
										scanner.nextLine();
										receivedAmount=payment.getPaymentAmount();
										paymentMethod="Touch N Go E-Wallet";
										
									}
									else if(paymentMChoice==-1) {
										cancel=-1;
									}
									
									if(cancel!=-1) {
										if(paymentError==1) {
											System.out.println("  Please try again or change payment method");
											do {
												invalidInput=0;
												System.out.printf("%-10s----------------------------\n", "");
												System.out.printf("%-10s|                          |\n", "");
												System.out.printf("%-10s| 1. Retry                 |\n", "");
												System.out.printf("%-10s| 2. Change Payment Method |\n", "");
												System.out.printf("%-10s|                          |\n", "");
												System.out.printf("%-10s----------------------------\n", "");
												System.out.print("  Enter Choice(Enter -1 to cancel): ");
												try{
													retryPayment=scanner.nextInt();
													scanner.nextLine();
												}
												catch(InputMismatchException e){
													invalidInput=1;
													scanner.nextLine();
												}
												
												
												if((retryPayment>2)||(retryPayment==0)||(retryPayment<-1)||(invalidInput==1)) {
													System.out.printf("\n%-10sInvalid Choice.\n", "");
													System.out.printf("%-10sPress enter to try again", "");
													scanner.nextLine();
													System.out.println();
												}
												else if(retryPayment==-1) {
													cancel=-1;
												}
											}while((retryPayment>2)||(retryPayment==0)||(retryPayment<-1)||(invalidInput==1));
										}
									}
									
								}while(retryPayment==1);
							}
							
							
						}while(retryPayment==2);
					}
					
					
					
					
					
					if(cancel!=-1) {
						payment.setPaymentDetails(receivedAmount, paymentMethod, "Completed");
						System.out.println(payment.toString());
						bankAccount.addPayment(payment);
						genReceipt(receiptList, receipt, payment);
					}
				}
				
			}
		}
		
		if(cancel==-1) {
			System.out.println(" Payment Process has been cancelled\n");
		}
		
	}
	
	public static void genReceipt(ArrayList<Receipt> receiptList, Receipt receipt, Payment payment) {
		receipt=new Receipt(payment);
		System.out.println(receipt.toString());
		receiptList.add(receipt);
	}
	
	public static void placeOrder(ArrayList<Staff> staffList, ArrayList<Product> productList, ArrayList<Member> memberList, ArrayList<Payment> paymentList, Staff staff, ArrayList<Receipt> receiptList, BankAccount bankAccount) {
		int newQty, memIDNo, checkMemID=0, cancel=0;
		char choice='N', addProdChoice='N', editChoice='N', paymentChoice='N', orderChoice='N';
		String memID;
		Scanner scanner=new Scanner(System.in);
		Receipt receipt=null;
		Product product=null;
		Payment payment=null;
		System.out.println("Staff Name: "+ staff.getName());
		
		
		do {
			Cart cart=new Cart();
			do {
				choice='N';
				addProdChoice='N';
				cancel=0;
				String prodChoice=selectProduct(productList);
				if(prodChoice.equals("Smart Phone")) {
					product=new SmartPhone();
					phone(productList, (SmartPhone)product);
				}
				
				else if(prodChoice.equals("Earphone")) {
					product=new Earphone();
					earphone(productList, (Earphone)product);
				}
				
				else if(prodChoice.equals("Tablet")) {
					product=new Tablet();
					tablet(productList, (Tablet)product);
				}
				else if(prodChoice.equals("Refrigerator")) {
					product=new Refrigerator();
					refrigerator(productList, (Refrigerator)product);
				}
				else if(prodChoice.equals("Printer")) {
					product=new Printer();
					printer(productList, (Printer)product);
				}
				else if(prodChoice.equals("Scanners")) {
					product=new Scanners();
					scanners(productList, (Scanners)product);
				}
				else if(prodChoice.equals("Microwave")) {
					product=new Microwave();
					microwave(productList, (Microwave)product);
				}
				else if(prodChoice.equals("Smart Watch")) {
					product=new SmartWatch();
					smartwatch(productList, (SmartWatch)product);
				}
				else if(prodChoice.equals("Cancel")) {
					cancel=-1;
				}
				else {
					product=new Product();
				}
				if(cancel!=-1) {
					if(product.getQuantity()>0) {
						System.out.print("  Would you like to add this product to cart?(Y=yes) ");
						addProdChoice=Character.toUpperCase(scanner.next().charAt(0));
						scanner.nextLine();
						
						if(addProdChoice=='Y') {
							for(int i=0; i<productList.size(); i++) {
								if(product.getProductID().equals(productList.get(i).getProductID())) {
									newQty=productList.get(i).getQuantity()-product.getQuantity();
									productList.get(i).setQuantity(newQty);
								}
							}
							cart.addProduct(product);
							System.out.println("  Product has been added to cart.");
						}
					}
					else {
						System.out.println("  Product not added to cart");
					}
					
					
					System.out.print("  Would you like to add another product?(Y=yes) ");
					choice=Character.toUpperCase(scanner.next().charAt(0));
					scanner.nextLine();
				}
				
			}while(choice=='Y');
			if(cancel!=-1) {
				if(cart.getNoOfProducts()>0) {
					System.out.println(cart.toString());
					
					System.out.print("  Would you like to edit the items in the cart? (Y=yes) ");
					editChoice=Character.toUpperCase(scanner.next().charAt(0));
					scanner.nextLine();
					if(editChoice=='Y') {
						editCart(productList, cart);
					}
					else {
						System.out.println("  Items in cart are not edited.");
					}
					
					if(cart.getNoOfProducts()>0) {
						System.out.print("  Would you like to proceed with payment? (Y=yes) ");
						paymentChoice=Character.toUpperCase(scanner.next().charAt(0));
						scanner.nextLine();
						payment=new Payment(cart, "On-Hold");
						if(paymentChoice=='Y') {
							paymentFunc(payment, memberList, staff, bankAccount, receiptList, productList);
						}
						else {
							System.out.println("  Payment has been put on-hold.");
						}
						paymentList.add(payment);
					}
					else {
						System.out.println("  Cart is empty");
					}
				}
				else {
					System.out.println("  Cart is empty");
				}
			}
			
			System.out.println();
			System.out.print("  Would you like to place another order? (Y=yes) ");
			orderChoice=Character.toUpperCase(scanner.next().charAt(0));
			scanner.nextLine();
		}while(orderChoice=='Y');	
	}

	public static void report(ArrayList<Receipt> receiptList) {
		ArrayList<Integer> quantity=new ArrayList<Integer>();
		int hpQty=0, epQty=0, tabQty=0, friQty=0, priQty=0, scaQty=0, mwQty=0, swQty=0;
		for(int i=0; i<receiptList.size(); i++) {
			for(int j=0; j<receiptList.get(i).getPayment().getCart().getNoOfProducts(); j++) {
				if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof SmartPhone) {
					hpQty+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
				}
				else if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Earphone) {
					epQty+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
				}
				else if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Tablet) {
					tabQty+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
				}
				else if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Refrigerator) {
					friQty+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
				}
				else if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Printer) {
					priQty+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
				}
				else if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Scanners) {
					scaQty+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
				}
				else if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Microwave) {
					mwQty+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
				}
				else if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof SmartWatch) {
					swQty+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
				}
			}
		}
		

		
		tableReport(receiptList);
		
	}
	
	public static void tableReport(ArrayList<Receipt> receiptList) {
		int qtyCount=0, discount2=0, discount3=0, discount5=0;
		System.out.println("  Total quantity sold of each product.");
		System.out.printf("%-10s--------------------------------------------------------------------------------------\n", "");
		System.out.printf("%-10s|  Product Name   |  Quantity Sold   |  2%% discount  |  3%% discount  |  5%% discount  |\n", "");
		System.out.printf("%-10s|-----------------+------------------+---------------+---------------+----------------\n", "");
		for(int i=0; i<receiptList.size(); i++) {
			for(int j=0; j<receiptList.get(i).getPayment().getCart().getNoOfProducts(); j++) {
				if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof SmartPhone) {
					qtyCount+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					if(receiptList.get(i).getPayment().getDiscount()==0.02) {
						discount2+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.03) {
						discount3+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.05) {
						discount5+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
				}
			}
		}
		
		System.out.printf("%-10s|   Meta Phone    |       %-5d      |       %-5d   |       %-5d   |       %-5d   |\n", "", qtyCount, discount2, discount3, discount5);
		qtyCount=0; discount2=0; discount3=0; discount5=0;
		for(int i=0; i<receiptList.size(); i++) {
			for(int j=0; j<receiptList.get(i).getPayment().getCart().getNoOfProducts(); j++) {
				if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Earphone) {
					qtyCount+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					if(receiptList.get(i).getPayment().getDiscount()==0.02) {
						discount2+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.03) {
						discount3+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.05) {
						discount5+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
				}
			}
		}
		System.out.printf("%-10s|    Meta Pod     |       %-5d      |       %-5d      |       %-5d      |       %-5d      |\n", "", qtyCount, discount2, discount3, discount5);
		
		qtyCount=0; discount2=0; discount3=0; discount5=0;
		for(int i=0; i<receiptList.size(); i++) {
			for(int j=0; j<receiptList.get(i).getPayment().getCart().getNoOfProducts(); j++) {
				if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Tablet) {
					qtyCount+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					if(receiptList.get(i).getPayment().getDiscount()==0.02) {
						discount2+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.03) {
						discount3+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.05) {
						discount5+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
				}
			}
		}
		
		System.out.printf("%-10s|   Meta Tablet   |       %-5d      |       %-5d      |       %-5d      |       %-5d      |\n", "", qtyCount, discount2, discount3, discount5);
		qtyCount=0; discount2=0; discount3=0; discount5=0;
		for(int i=0; i<receiptList.size(); i++) {
			for(int j=0; j<receiptList.get(i).getPayment().getCart().getNoOfProducts(); j++) {
				if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Refrigerator) {
					qtyCount+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					if(receiptList.get(i).getPayment().getDiscount()==0.02) {
						discount2+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.03) {
						discount3+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.05) {
						discount5+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
				}
			}
		}
		
		System.out.printf("%-10s|   Meta Fridge   |       %-5d      |       %-5d      |       %-5d      |       %-5d      |\n", "", qtyCount, discount2, discount3, discount5);
		qtyCount=0; discount2=0; discount3=0; discount5=0;
		for(int i=0; i<receiptList.size(); i++) {
			for(int j=0; j<receiptList.get(i).getPayment().getCart().getNoOfProducts(); j++) {
				if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Printer) {
					qtyCount+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					if(receiptList.get(i).getPayment().getDiscount()==0.02) {
						discount2+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.03) {
						discount3+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.05) {
						discount5+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
				}
			}
		}
		
		System.out.printf("%-10s|   Meta Printer  |       %-5d      |       %-5d      |       %-5d      |       %-5d      |\n", "", qtyCount, discount2, discount3, discount5);
		qtyCount=0; discount2=0; discount3=0; discount5=0;
		for(int i=0; i<receiptList.size(); i++) {
			for(int j=0; j<receiptList.get(i).getPayment().getCart().getNoOfProducts(); j++) {
				if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Scanners) {
					qtyCount+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					if(receiptList.get(i).getPayment().getDiscount()==0.02) {
						discount2+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.03) {
						discount3+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.05) {
						discount5+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
				}
			}
		}
		
		System.out.printf("%-10s|   Meta Scanner  |       %-5d      |       %-5d      |       %-5d      |       %-5d      |\n", "", qtyCount, discount2, discount3, discount5);
		qtyCount=0; discount2=0; discount3=0; discount5=0;
		for(int i=0; i<receiptList.size(); i++) {
			for(int j=0; j<receiptList.get(i).getPayment().getCart().getNoOfProducts(); j++) {
				if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof Microwave) {
					qtyCount+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					if(receiptList.get(i).getPayment().getDiscount()==0.02) {
						discount2+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.03) {
						discount3+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.05) {
						discount5+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
				}
			}
		}
		
		System.out.printf("%-10s|   Meta Wave     |       %-5d      |       %-5d      |       %-5d      |       %-5d      |\n", "", qtyCount, discount2, discount3, discount5);
		qtyCount=0; discount2=0; discount3=0; discount5=0;
		for(int i=0; i<receiptList.size(); i++) {
			for(int j=0; j<receiptList.get(i).getPayment().getCart().getNoOfProducts(); j++) {
				if(receiptList.get(i).getPayment().getCart().getProduct()[j] instanceof SmartWatch) {
					qtyCount+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					if(receiptList.get(i).getPayment().getDiscount()==0.02) {
						discount2+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.03) {
						discount3+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
					else if(receiptList.get(i).getPayment().getDiscount()==0.05) {
						discount5+=receiptList.get(i).getPayment().getCart().getProduct()[j].getQuantity();
					}
				}
			}
		}
		System.out.printf("%-10s|   Meta Watch    |       %-5d      |       %-5d      |       %-5d      |       %-5d      |\n", "", qtyCount, discount2, discount3, discount5);
		System.out.printf("%-10s----------------------------------------------------------------------------------------\n", "");
	}
	
	public static void graphReport() {
		System.out.println("            /|\\");
		System.out.println("             |");
		//Print phone graph(start)
		  System.out.print("             |");
		  printUnderscore(calculation(10));
		System.out.println();
		  System.out.print("Meta Phone   |");
		  printUnderscore(calculation(10));
		
		System.out.println("|");
		//print phone graph end
		//print earphone graph start
		  System.out.print("             |");
		  printUnderscore(calculation(100));
		  System.out.println();
		  System.out.print("Meta Pod     |");
		  printUnderscore(calculation(100));
		  System.out.println("|");
		//print earphone graph end
		//print tablet graph start
		  System.out.print("             |");
		  printUnderscore(calculation(499));
		  System.out.println();
		  System.out.print("Meta Tablet  |");
		  printUnderscore(calculation(499));
		  System.out.println("|");
		//print tablet graph end
		//print fridge graph start
		  System.out.print("             |");
		  printUnderscore(calculation(10));
		  System.out.println();
		  System.out.print("Meta Fridge  |");
		  printUnderscore(calculation(10));
		  System.out.println("|");
		//print fridge graph end
		//print printer graph start
		  System.out.print("             |");
		  printUnderscore(calculation(10));
		  System.out.println();
		  System.out.print("Meta Printer |");
		  printUnderscore(calculation(10));
		  System.out.println("|");
		//print printer graph end
		//print scanner graph start
		  System.out.print("             |");
		  printUnderscore(calculation(10));
		  System.out.println();
		  System.out.print("Meta Scanner |");
		  printUnderscore(calculation(10));
		  System.out.println("|");
		//print scanner graph end
		//print microwave graph start
		  System.out.print("             |");
		  printUnderscore(calculation(10));
		  System.out.println();
		  System.out.print("Meta Wave    |");
		  printUnderscore(calculation(10));
		  System.out.println("|");
		//print microwave graph end
		//print smartwatch graph start
		  System.out.print("             |");
		  printUnderscore(calculation(20));
		  System.out.println();
		  System.out.print("Meta Watch   |");
		  printUnderscore(calculation(20));
		  System.out.println("|");
		//print smartwatch graph end
		  System.out.println("             |");
		  System.out.print("             |");
		  printXAxis();
	}
	
	public static int calculation(int value) {
		int count=0;
		if(value%10!=0) {
			count=(value/10);
		}
		else if(value%10==0) {
			count=(value/10)-1;
		}
		return count;
	}
	
	public static void printUnderscore(int count) {
		for(int i=0; i<count; i++) {
			 System.out.print("_");
		}
	}
	
	public static void printSpace(int count) {
		for(int i=0; i<count; i++) {
			 System.out.print(" ");
		}
	}
	
	public static void printXAxis() {
		
		for(int i=0; i<10; i++) {
			printUnderscore(9);
			System.out.print("|");
		}
		System.out.println("_\\");
		System.out.print("              ");
		for(int i=0; i<10; i++) {
			printSpace(9);
			System.out.print("|");
		}
		
		System.out.println(" /");
		System.out.print("               ");
		for(int i=100; i<=1000; i+=100) {
			printSpace(7);
			System.out.print(i);
		}
	}
}
