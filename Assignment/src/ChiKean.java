import java.util.ArrayList;
import java.util.Scanner;


//if payment=cancelled, quantity of all items in the cart must be added back to their respective arraylist counterparts
// report should show original quantity, current quantity, quantity on-hold and quantity sold
public class ChiKean {
	public static String selectProduct(ArrayList<Product> productList) {
		int prodChoice=0;
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
			System.out.printf("%-10s---------------------------\n", "");
			System.out.printf("%-10s| Available Product Types |\n", "");
			System.out.printf("%-10s| ======================= |\n", "");
			System.out.printf("%-10s|                         |\n", "");
			for(int i =0; i<prodSelectList.size(); i++) {
				
				System.out.printf("%-10s|     %d%-3s%-15s |\n", "", (i+1), ".", prodSelectList.get(i));
				
			}
			System.out.printf("%-10s|                         |\n", "");
			System.out.printf("%-10s---------------------------\n\n", "");
			System.out.printf("Select product type(Enter -1 to quit): ");
			prodChoice=scanner.nextInt();
			scanner.nextLine();
			
			if((prodChoice>prodSelectList.size())||(prodChoice==0)||(prodChoice<-1)) {
				System.out.printf("%-10sInvalid choice.\n","");
				System.out.printf("%-10sPress enter to try again\n","");
				scanner.nextLine();
			}
		}while((prodChoice>prodSelectList.size())||(prodChoice==0)||(prodChoice<-1));
		
		if(prodChoice==-1) {
			return "Cancel";
		}
		else {
			return prodSelectList.get(prodChoice-1);
		}
		
	}
	
	
	public static void phone(ArrayList<Product> productList, SmartPhone product) {
		Scanner scanner=new Scanner(System.in);
		int storageChoice=0, ramChoice=0, colorChoice=0, foundStorage, foundRam, foundColor, tempStorage=0, tempRam=0, quantity, loopQuantity=0, cancel=0;
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
			System.out.println("Storage Capacity");
			for(int i=0; i<storage.size(); i++) {
				System.out.println((i+1)+". "+storage.get(i)+"GB");
			}
			do {
				System.out.print("Select Storage Capacity(Enter -1 to cancel): ");
				storageChoice=scanner.nextInt();
				scanner.nextLine();
				
				if((storageChoice>storage.size())||(storageChoice==0)||(storageChoice<-1)) {
					System.out.println("Invalid Choice. Please try again");
				}
				else if(storageChoice==-1) {
					cancel=-1;
				}
			}while((storageChoice>storage.size())||(storageChoice==0)||(storageChoice<-1));	
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
				System.out.println("RAM capacity");
				for(int i=0; i<ram.size(); i++) {
					System.out.println((i+1)+". "+ram.get(i)+"GB");
				}
					
				do {
					System.out.print("Select RAM capacity(Enter -1 to cancel): ");
					ramChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((ramChoice>ram.size())||(ramChoice==0)||(ramChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}
					else if(ramChoice==-1) {
						cancel=-1;
					}
				}while((ramChoice>ram.size())||(ramChoice==0)||(ramChoice<-1));
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
				System.out.println("Color");
				for(int i=0; i<color.size(); i++) {
					System.out.println((i+1)+". "+color.get(i));
				}
				
				do {
					System.out.print("Select Color(Enter -1 to cancel): ");
					colorChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1));
			}
		}
		
		if(cancel!=-1) {
			for(int i=0; i<phoneList.size(); i++) {
				if((storage.get(storageChoice-1)==phoneList.get(i).getStorageCapacity())&&(ram.get(ramChoice-1)==phoneList.get(i).getSizeOfRam())&&(color.get(colorChoice-1).equals(phoneList.get(i).getColor()))) {
					System.out.println(phoneList.get(i).toString());
					do {
						loopQuantity=0;
						System.out.println("Quantity Available: "+phoneList.get(i).getQuantity());
						System.out.print("Enter Quantity(Enter -1 to cancel): ");
						quantity=scanner.nextInt();
						scanner.nextLine();
						if(quantity>phoneList.get(i).getQuantity()) {
							System.out.println("Entered Quantity exceeds Quantity Available. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1) {
							System.out.println("Entered Quantity is below 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==0){
							System.out.println("Quantity entered is 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==-1) {
							cancel=-1;
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
		int genChoice=0, colorChoice=0, foundGen, foundColor, quantity, loopQuantity, cancel=0;
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
			System.out.println("Generation");
			for(int i=0; i<generation.size(); i++) {
				System.out.println((i+1)+". "+generation.get(i));
			}
			
			do {
				System.out.print("Select Generation(Enter -1 to cancel): ");
				genChoice=scanner.nextInt();
				scanner.nextLine();
				
				if((genChoice>generation.size())||(genChoice==0)||(genChoice<-1)) {
					System.out.println("Invalid choice. Please try again");
				}
				else if(genChoice==-1) {
					cancel=-1;
				}
			}while((genChoice>generation.size())||(genChoice==0)||(genChoice<-1));
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
				System.out.println("Color");
				for(int i=0; i<color.size(); i++) {
					System.out.println((i+1)+". "+color.get(i));
				}
				
				do {
					System.out.print("Select Color(Enter -1 to cancel): ");
					colorChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1));
			}
		}
		
		
		if(cancel!=-1) {
			for(int i=0; i<earphoneList.size(); i++) {
				if((earphoneList.get(i).getGeneration().equals(generation.get(genChoice-1)))&&(earphoneList.get(i).getColor().equals(color.get(colorChoice-1)))) {
					do {
						System.out.println(earphoneList.get(i).toString());
						loopQuantity=0;
						System.out.println("Available Quantity: "+earphoneList.get(i).getQuantity());
						System.out.print("Enter Quantity(Enter -1 to cancel): ");
						quantity=scanner.nextInt();
						scanner.nextLine();
						if(quantity>earphoneList.get(i).getQuantity()) {
							System.out.println("Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1) {
							System.out.println("Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==0) {
							System.out.println("Entered Quantity is 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==-1) {
							cancel=-1;
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
		int ramChoice=0, colorChoice=0, foundRam, foundColor, tempRam, quantity, loopQuantity=0, cancel=0;
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
			System.out.println("RAM Capacity");
			for(int i=0; i<ram.size(); i++) {
				System.out.println((i+1)+". "+ram.get(i)+"GB");
			}
			
			do {
				System.out.print("Select RAM Capacity(Enter -1 to cancel): ");
				ramChoice=scanner.nextInt();
				scanner.nextLine();
				
				if((ramChoice>ram.size())||(ramChoice==0)||(ramChoice<-1)) {
					System.out.println("Invalid Choice. Please try again");
				}
				else if(ramChoice==-1) {
					cancel=-1;
				}
			}while((ramChoice>ram.size())||(ramChoice==0)||(ramChoice<-1));
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
				System.out.println("Color");
				for(int i=0; i<color.size();i++) {
					System.out.println((i+1)+". "+color.get(i));
				}
				
				do {
					System.out.print("Select Color(Enter -1 to cancel): ");
					colorChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1));
			}
		}
		
		
		if(cancel!=-1) {
			for(int i=0; i<tabletList.size(); i++) {
				if((ram.get(ramChoice-1)==tabletList.get(i).getSizeOfRam())&&(color.get(colorChoice-1).equals(tabletList.get(i).getColor()))) {
					do {
						System.out.println(tabletList.get(i).toString());
						loopQuantity=0;
						System.out.println("Available Quantity: "+tabletList.get(i).getQuantity());
						System.out.print("Enter Quantity(Enter -1 to cancel): ");
						quantity=scanner.nextInt();
						scanner.nextLine();
						
						if(quantity>tabletList.get(i).getQuantity()) {
							System.out.println("Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==0) {
							System.out.println("Entered Quantity is 0. Please try again");
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
		int hPowerChoice=0, capChoice=0, colorChoice=0, foundHPower, foundCap, foundColor, quantity, loopQuantity, cancel=0;
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
			if(fridgeList.get(i).getQuantity()>0) {
				tempHPower=fridgeList.get(i).getHorsePower();
				for(int j=0; j<horsePower.size(); j++) {
					if(tempHPower==horsePower.get(j)) {
						foundHPower=1;
					}
				}
				if(foundHPower==0){
					horsePower.remove(Double.valueOf(0.0));
					horsePower.add(tempHPower);
				}
			}
		}
		
		if(horsePower.size()==1) {
			hPowerChoice=1;
		}
		else {
			System.out.println("Horse Power");
			for(int i=0; i<horsePower.size(); i++) {
				System.out.println((i+1)+". "+horsePower.get(i)+"W");
			}
			
			do {
				System.out.print("Select Horse Power(Enter -1 to cancel): ");
				hPowerChoice=scanner.nextInt();
				scanner.nextLine();
				
				if((hPowerChoice>horsePower.size())||(hPowerChoice==0)||(hPowerChoice<-1)) {
					System.out.println("Invalid Choice. Please try again.");
				}
				else if(hPowerChoice==-1) {
					cancel=-1;
				}
			}while((hPowerChoice>horsePower.size())||(hPowerChoice==0)||(hPowerChoice<-1));
		}
		
		if(cancel!=-1) {
			for(int i=0; i<fridgeList.size(); i++) {
				foundCap=0;
				if((horsePower.get(hPowerChoice-1)==fridgeList.get(i).getHorsePower())&&(fridgeList.get(i).getQuantity()>0)) {
					tempCapacity=fridgeList.get(i).getCapacity();
					for(int j=0; j<capacity.size(); j++) {
						if(tempCapacity==capacity.get(j)) {
							foundCap=1;
						}
					}
					if(foundCap==0) {
						capacity.remove(Double.valueOf(0.0));
						capacity.add(tempCapacity);
					}
				}
			}
			
			if(capacity.size()==1) {
				capChoice=1;
			}
			else {
				System.out.println("Capacity");
				for(int i=0; i<capacity.size(); i++) {
					System.out.println((i+1)+". "+capacity.get(i));
				}
				
				do {
					System.out.print("Select Capacity(Enter -1 to cancel): ");
					capChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((capChoice>capacity.size())||(capChoice==0)||(capChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}
					else if(capChoice==-1) {
						cancel=-1;
					}
				}while((capChoice>capacity.size())||(capChoice==0)||(capChoice<-1));
			}
		}
		
		
		if(cancel!=-1) {
			for(int i=0; i<fridgeList.size(); i++) {
				foundColor=0;
				if((horsePower.get(hPowerChoice-1)==fridgeList.get(i).getHorsePower())&&(capacity.get(capChoice-1)==fridgeList.get(i).getCapacity())&&(fridgeList.get(i).getQuantity()>0)) {
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
				System.out.println("Color");
				for(int i=0; i<color.size(); i++) {
					System.out.println((i+1)+". "+color.get(i));
				}
				
				do {
					System.out.print("Select Color(Enter -1 to cancel): ");
					colorChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)) {
						System.out.println("Invalid Choice. Please try again.");
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1));
			}
			
		}
		
		if(cancel!=-1) {
			for(int i=0; i<fridgeList.size(); i++) {
				if((horsePower.get(hPowerChoice-1)==fridgeList.get(i).getHorsePower())&&(capacity.get(capChoice-1)==fridgeList.get(i).getCapacity())&&(color.get(colorChoice-1).equals(fridgeList.get(i).getColor()))) {
					do {
						System.out.println(fridgeList.get(i).toString());
						loopQuantity=0;
						System.out.println("Available Quantity: "+fridgeList.get(i).getQuantity());
						System.out.print("Enter Quantity(Enter -1 to cancel): ");
						quantity=scanner.nextInt();
						scanner.nextLine();
						
						if(quantity>fridgeList.get(i).getQuantity()) {
							System.out.println("Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==0) {
							System.out.println("Entered Quantity is 0. Please try again");
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
		int seriesChoice=0, resChoice=0, typeChoice=0, duplexChoice=0, colorChoice=0, foundSeries, foundRes, foundType, foundDuplex, foundColor, quantity, loopQuantity, cancel=0;
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
			foundRes=0;
			if(productList.get(i).getQuantity()>0) {
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
			System.out.println("Resolution");
			for(int i=0; i<resolution.size(); i++) {
				System.out.println((i+1)+". "+resolution.get(i)+"dpi");
			}
			
			do {
				System.out.print("Select Resolution(Enter -1 to cancel): ");
				resChoice=scanner.nextInt();
				scanner.nextLine();
				
				if((resChoice>resolution.size())||(resChoice==0)||(resChoice<-1)) {
					System.out.println("Invalid Choice. Please try again");
				}
				else if(resChoice==-1) {
					cancel=-1;
				}
			}while((resChoice>resolution.size())||(resChoice==0)||(resChoice<-1));
		}
		
		if(cancel!=-1) {
			for(int i=0; i<printerList.size(); i++) {
				foundType=0;
				if((resolution.get(resChoice-1).equals(printerList.get(i).getResolution()))&&(productList.get(i).getQuantity()>0)) {
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
				System.out.println("Printer Type");
				for(int i=0; i<printerType.size(); i++) {
					System.out.println((i+1)+". "+printerType.get(i));
				}
				
				do {
					System.out.print("Select Printer Type(Enter -1 to cancel): ");
					typeChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((typeChoice>printerType.size())||(typeChoice==0)||(typeChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}
					else if(typeChoice==-1) {
						cancel=-1;
					}
				}while((typeChoice>printerType.size())||(typeChoice==0)||(typeChoice<-1));
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
			else {
				System.out.println("Duplex Capability");
				for(int i=0; i<duplexCapability.size();i++) {
					System.out.println((i+1)+". "+duplexCapability.get(i));
				}
				
				do {
					System.out.print("Select Duplex Capability(Enter -1 to exit): ");
					duplexChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((duplexChoice>duplexCapability.size())||(duplexChoice==0)||(duplexChoice<-1)){
						System.out.println("Invalid Choice. Please try again");
					}
					else if(duplexChoice==-1) {
						cancel=-1;
					}
				}while((duplexChoice>duplexCapability.size())||(duplexChoice==0)||(duplexChoice<-1));
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
			else {
				System.out.println("Series");
				for(int i=0; i<series.size(); i++) {
					System.out.println((i+1)+". "+series.get(i));
				}
				
				do {
					System.out.print("Select Series(Enter -1 to cancel): ");
					seriesChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((seriesChoice>series.size())||(seriesChoice==0)||(seriesChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}
					else if(seriesChoice==-1) {
						cancel=-1;
					}
				}while((seriesChoice>series.size())||(seriesChoice==0)||(seriesChoice<-1));
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
				System.out.println("Color");
				for(int i=0; i<color.size(); i++) {
					System.out.println((i+1)+". "+color.get(i));
				}
				
				do {
					System.out.print("Select Color(Enter -1 to cancel): ");
					colorChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1));
			}
			
		}

		if(cancel!=-1) {
			for(int i=0; i<printerList.size(); i++) {
				if((resolution.get(resChoice-1).equals(printerList.get(i).getResolution()))&&(printerType.get(typeChoice-1).equals(printerList.get(i).getPrinterType()))&&(duplexCapability.get(duplexChoice-1).equals(printerList.get(i).getDuplexCapability()))&&(series.get(seriesChoice-1).equals(printerList.get(i).getSeries()))&&(color.get(colorChoice-1).equals(printerList.get(i).getColor()))) {
					do {
						System.out.println(printerList.get(i).toString());
						loopQuantity=0;
						
						System.out.println("Available Quantity: "+printerList.get(i).getQuantity());
						System.out.print("Enter Quantity(Enter -1 to cancel): ");
						quantity=scanner.nextInt();
						scanner.nextLine();
						
						if(quantity>printerList.get(i).getQuantity()) {
							System.out.println("Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==0) {
							System.out.println("Entered Quantity is 0. Please try again");
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
		int seriesChoice=0, foundSeries, quantity, loopQuantity, cancel=0;
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
			System.out.println("Series");
			for(int i=0; i<series.size(); i++) {
				System.out.println((i+1)+". "+series.get(i));
			}
			
			do {
				System.out.print("Select Series(Enter -1 to cancel): ");
				seriesChoice=scanner.nextInt();
				scanner.nextLine();
				
				if((seriesChoice>series.size())||(seriesChoice==0)||(seriesChoice<-1)) {
					System.out.println("Invalid Choice. Please try again");
				}
				else if(seriesChoice==-1) {
					cancel=-1;
				}
			}while((seriesChoice>series.size())||(seriesChoice==0)||(seriesChoice<-1));
		}
		
		if(cancel!=-1) {
			for(int i=0; i<scannerList.size(); i++) {
				if(series.get(seriesChoice-1).equals(scannerList.get(i).getSeries())) {
					do {
						System.out.println(scannerList.get(i).toString());
						loopQuantity=0;
						
						System.out.println("Available Quantity: "+scannerList.get(i).getQuantity());
						System.out.print("Enter Quantity(Enter -1 to cancel): ");
						quantity=scanner.nextInt();
						scanner.nextLine();
						
						if(quantity>scannerList.get(i).getQuantity()) {
							System.out.println("Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==0) {
							System.out.println("Entered Quantity is 0. Please try again");
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
		int sizeChoice=0, capChoice=0, colorChoice=0, foundSize, foundCap, foundColor, quantity, loopQuantity, cancel=0;
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
			if(microwaveList.get(i).getQuantity()>0) {
				tempSize=microwaveList.get(i).getSize();
				for(int j=0; j<size.size(); j++) {
					if(tempSize.equals(size.get(j))) {
						foundSize=1;
					}
				}
				if(foundSize==0) {
					size.remove("null");
					size.add(tempSize);
				}
			}
		}
		
		if(size.size()==1) {
			sizeChoice=1;
		}
		else {
			System.out.println("Size");
			for(int i=0; i<size.size(); i++) {
				System.out.println((i+1)+". "+size.get(i));
			}
			
			do {
				System.out.print("Select Size(Enter -1 to cancel): ");
				sizeChoice=scanner.nextInt();
				scanner.nextLine();
				
				if((sizeChoice>size.size())||(sizeChoice==0)||(sizeChoice<-1)) {
					System.out.println("Invalid Choice. Please try again");
				}
				else if(sizeChoice==-1) {
					cancel=-1;
				}
			}while((sizeChoice>size.size())||(sizeChoice==0)||(sizeChoice<-1));
		}
		
		if(cancel!=-1) {
			for(int i=0; i<microwaveList.size(); i++) {
				foundCap=0;
				if((size.get(sizeChoice-1).equals(microwaveList.get(i).getSize()))&&microwaveList.get(i).getQuantity()>0) {
					tempCap=microwaveList.get(i).getCapacity();
					for(int j=0; j<capacity.size(); j++) {
						if(tempCap==capacity.get(j)) {
							foundCap=1;
						}
					}
					if(foundCap==0) {
						capacity.remove(Double.valueOf(0.0));
						capacity.add(tempCap);
					}
				}
			}
			
			
			if(capacity.size()==1) {
				capChoice=1;
			}
			else {
				System.out.println("Capacity");
				for(int i=0; i<capacity.size(); i++) {
					System.out.println((i+1)+". "+capacity.get(i));
				}
				
				do {
					System.out.print("Select Capacity(Enter -1 to cancel): ");
					capChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((capChoice>size.size())||(capChoice==0)||(capChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}
					else if(capChoice==-1) {
						cancel=-1;
					}
				}while((capChoice>size.size())||(capChoice==0)||(capChoice<-1));
			}
		}
		
		if(cancel!=-1) {
			for(int i=0; i<microwaveList.size(); i++) {
				foundColor=0;
				if((size.get(sizeChoice-1).equals(microwaveList.get(i).getSize()))&&(capacity.get(capChoice-1)==microwaveList.get(i).getCapacity())&&(microwaveList.get(i).getQuantity()>0)) {
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
				System.out.println("Color");
				for(int i=0; i<color.size(); i++) {
					System.out.println((i+1)+". "+color.get(i));
				}
				
				do {
					System.out.print("Select Color(Enter -1 to cancel): ");
					colorChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1));
			}
		}
		
		if(cancel!=-1) {
			for(int i=0; i<microwaveList.size(); i++) {
				if((size.get(sizeChoice-1).equals(microwaveList.get(i).getSize()))&&(capacity.get(capChoice-1)==microwaveList.get(i).getCapacity())&&(color.get(colorChoice-1).equals(microwaveList.get(i).getColor()))) {
					do {
						System.out.println(microwaveList.get(i).toString());
						
						loopQuantity=0;
						
						System.out.println("Available Quantity: "+microwaveList.get(i).getQuantity());
						System.out.print("Enter Quantity(Enter -1 to cancel): ");
						quantity=scanner.nextInt();
						scanner.nextLine();
						
						if(quantity>microwaveList.get(i).getQuantity()) {
							System.out.println("Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==0) {
							System.out.println("Entered Quantity is 0. Please try again");
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
		int scrSizeChoice=0, resChoice=0, colorChoice=0, foundScrSize, foundRes, foundColor, quantity, loopQuantity, cancel=0;
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
			System.out.println("Screen Size");
			for(int i=0; i<scrSize.size(); i++) {
				System.out.println((i+1)+". "+scrSize.get(i));
			}
			
			do {
				System.out.print("Select Screen Size(Enter -1 to cancel): ");
				scrSizeChoice=scanner.nextInt();
				scanner.nextLine();
				
				if((scrSizeChoice>scrSize.size())||(scrSizeChoice==0)||(scrSizeChoice<-1)) {
					System.out.println("Invalid Choice. Please try again");
				}
				else if(scrSizeChoice==-1) {
					cancel=-1;
				}
			}while((scrSizeChoice>scrSize.size())||(scrSizeChoice==0)||(scrSizeChoice<-1));
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
				System.out.println("Resolution");
				for(int i=0; i<resolution.size(); i++) {
					System.out.println((i+1)+". "+resolution.get(i));
				}
				
				do {
					System.out.print("Select Resolution(Enter -1 to cancel): ");
					resChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((resChoice>resolution.size())||(resChoice==0)||(resChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}	
					else if(resChoice==-1) {
						cancel=-1;
					}
				}while((resChoice>resolution.size())||(resChoice==0)||(resChoice<-1));
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
			else {
				System.out.println("Color");
				for(int i=0; i<color.size(); i++) {
					System.out.println((i+1)+". "+color.get(i));
				}
				
				do {
					System.out.print("Select Color(Enter -1 to cancel): ");
					colorChoice=scanner.nextInt();
					scanner.nextLine();
					
					if((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1)) {
						System.out.println("Invalid Choice. Please try again");
					}
					else if(colorChoice==-1) {
						cancel=-1;
					}
				}while((colorChoice>color.size())||(colorChoice==0)||(colorChoice<-1));
			}
		}
		
		if(cancel!=-1) {
			for(int i=0; i<watchList.size(); i++) {
				if((scrSize.get(scrSizeChoice-1)==watchList.get(i).getScreenSize())&&(resolution.get(resChoice-1).equals(watchList.get(i).getResolution()))&&(color.get(colorChoice-1).equals(watchList.get(i).getColor()))) {
					do {
						System.out.println(watchList.get(i).toString());
						
						loopQuantity=0;
						
						System.out.println("Available Quantity: "+watchList.get(i).getQuantity());
						System.out.print("Enter Quantity(Enter -1 to cancel): ");
						quantity=scanner.nextInt();
						scanner.nextLine();
						
						if(quantity>watchList.get(i).getQuantity()) {
							System.out.println("Entered Quantity exceeds Available Quantity. Please try again");
							loopQuantity=1;
						}
						else if(quantity<-1){
							System.out.println("Entered Quantity is less than 0. Please try again");
							loopQuantity=1;
						}
						else if(quantity==0) {
							System.out.println("Entered Quantity is 0. Please try again");
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
		int choice, cancel=0;
		char editChoice='N';
		
		
		do {
			editChoice='N';
			if(cart.getNoOfProducts()>0) {
				System.out.printf("%-15s%-17s%-23s%-13s%s\n", "Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
				System.out.printf("%-15s%-17s%-23s%-13s%s\n", "----------", "------------", "------------------", "--------", "-----");
				
				for(int i=0; i<cart.getNoOfProducts(); i++) {
						System.out.printf("%-15s%-17s%-23.2f%-13d%.2f\n", cart.getProduct()[i].getProductID(), cart.getProduct()[i].getProductName(), cart.getProduct()[i].getPrice(), cart.getProduct()[i].getQuantity(), cart.getPricePerItem()[i]);	
				}
				
				do {
					System.out.println("Which function would you like to perform?");
					System.out.println("1. Edit Quantity of Item");
					System.out.println("2. Remove Item from Cart");
					System.out.print("Please enter your choice(Enter -1 to cancel): ");
					choice=scanner.nextInt();
					scanner.nextLine();
					if((choice>2)||(choice==0)||(choice<-1)) {
						System.out.println("Invalid choice. Please try again.");
					}
					else if(choice==-1) {
						cancel=-1;
					}
				}while((choice>2)||(choice==0)||(choice<-1));
				
				if(cancel!=-1) {
					switch(choice) {
					case 1: editQuantity(productList, cart); break;
					case 2: removeItem(productList, cart); break;
					}
					
					if(cart.getNoOfProducts()>0) {
						System.out.print("Would you like to make anymore modifications to the cart? (Y=yes)");
						editChoice=Character.toUpperCase(scanner.next().charAt(0));
						scanner.nextLine();
					}
					
				}
			}
		}while(editChoice=='Y');
		
	}
	
	public static void editQuantity(ArrayList<Product> productList, Cart cart) {
		Scanner scanner = new Scanner(System.in);
		int itemChoice=0, qty, loopQty, difference, cancel=0;
		char editConfirm;
		System.out.printf("%-6s%-15s%-17s%-23s%-13s%s\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
		System.out.printf("%-6s%-15s%-17s%-23s%-13s%s\n", "","----------", "------------", "------------------", "--------", "-----");
		
		for(int i=0; i<cart.getNoOfProducts(); i++) {
				System.out.printf("%-6d%-15s%-17s%-23.2f%-13d%.2f\n", (i+1),cart.getProduct()[i].getProductID(), cart.getProduct()[i].getProductName(), cart.getProduct()[i].getPrice(), cart.getProduct()[i].getQuantity(), cart.getPricePerItem()[i]);	
		}
		
		do {
			System.out.print("Select item to edit(Enter -1 to cancel): ");
			itemChoice=scanner.nextInt();
			scanner.nextLine();
			
			if((itemChoice>cart.getNoOfProducts())||(itemChoice==0)||(itemChoice<-1)) {
				System.out.println("Invalid Choice. Please try again");
			}
			else if(itemChoice==-1) {
				cancel=-1;
			}
		}while((itemChoice>cart.getNoOfProducts())||(itemChoice==0)||(itemChoice<-1));
		
		if(cancel!=-1) {
			for(int i=0; i<productList.size(); i++) {
				if(cart.getProduct()[itemChoice-1].getProductID().equals(productList.get(i).getProductID())) {
					do {
						loopQty=0;
						System.out.println("Available Quantity: "+(productList.get(i).getQuantity()+cart.getProduct()[itemChoice-1].getQuantity()));
						System.out.print("Enter Quantity(Enter -1 to cancel): ");
						qty=scanner.nextInt();
						scanner.nextLine();
						

						if(qty>(productList.get(i).getQuantity()+cart.getProduct()[itemChoice-1].getQuantity())) {
							System.out.println("Entered Quantity exceeds Available Quantity. Please try again");
							loopQty=1;
						}
						else if(qty<-1){
							System.out.println("Entered Quantity is less than 0. Please try again");
							loopQty=1;
						}
						else if(qty==0) {
							System.out.println("Entered Quantity is 0. Please try again");
							loopQty=1;
						}
						else if(qty==-1) {
							cancel=-1;
						}
					}while(loopQty==1);
					
					if(cancel!=-1) {
						System.out.print("Are you sure you want to change the quantity of this item? (Y=yes)");
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
								System.out.println("Quantity was not changed.");
							}
							
							if(qty!=cart.getProduct()[itemChoice-1].getQuantity()) {
								cart.modifyQuantity(itemChoice-1, qty);
							}
							
						}
						else {
							System.out.println("Quantity was not changed.");
						}
					}
					
					
					
				}
			}
		}
		
	}
	
	public static void removeItem(ArrayList<Product> productList, Cart cart) {
		Scanner scanner=new Scanner(System.in);
		int itemChoice=0, cancel=0;
		char removeConfirm='N';
		System.out.printf("%-6s%-15s%-17s%-23s%-13s%s\n", "","Product ID", "Product Name", "Price Per Quantity", "Quantity", "Price");
		System.out.printf("%-6s%-15s%-17s%-23s%-13s%s\n", "","----------", "------------", "------------------", "--------", "-----");
		
		for(int i=0; i<cart.getNoOfProducts(); i++) {
				System.out.printf("%-6d%-15s%-17s%-23.2f%-13d%.2f\n", (i+1),cart.getProduct()[i].getProductID(), cart.getProduct()[i].getProductName(), cart.getProduct()[i].getPrice(), cart.getProduct()[i].getQuantity(), cart.getPricePerItem()[i]);	
		}
		
		
		do {
			System.out.print("Select item to remove(Enter -1 to cancel): ");
			itemChoice=scanner.nextInt();
			scanner.nextLine();
			
			if((itemChoice>cart.getNoOfProducts())||(itemChoice==0)||(itemChoice<-1)) {
				System.out.println("Invalid Choice. Please try again");
			}
			else if(itemChoice==-1) {
				cancel=-1;
			}
		}while((itemChoice>cart.getNoOfProducts())||(itemChoice==0)||(itemChoice<-1));
		
		if(cancel!=-1) {
			System.out.print("Are you sure you want to remove this item? (Y=yes) ");
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
				System.out.println("Item not removed.");
			}
		}
		
		
	}
	
	public static void onHoldPayment(ArrayList<Payment> paymentList, ArrayList<Member> memberList, Staff staff, ArrayList<Product> productList, ArrayList<Receipt> receiptList, BankAccount bankAccount) {
		ArrayList<Payment> tempPayment=new ArrayList<Payment>();
		Scanner scanner=new Scanner(System.in);
		int choice=0, cancel=0, funcChoice=0, onHold=0;
		char paymentChoice='N';
		Receipt receipt=null;
		
		for(int i=0; i<paymentList.size(); i++) {
			if(paymentList.get(i).getStatus().equals("On-Hold")) {
				tempPayment.add(paymentList.get(i));
			}
		}
		
		do {
			onHold=0;
			if(tempPayment.size()==0) {
				System.out.printf("%-30sThere no On-Hold Payments\n", "");
			}
			else {
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
				
				
				do {
					System.out.printf("%-10sSelect Choice(Enter -1 to cancel): ", "");
					choice=scanner.nextInt();
					scanner.nextLine();
					
					if((choice>tempPayment.size())||(choice==0)||(choice<-1)) {
						System.out.printf("%-10sInvalid Choice. Please try again", "");
					}
					else if(choice==-1) {
						cancel=-1;
					}
				}while((choice>tempPayment.size())||(choice==0)||(choice<-1));
				
				if(cancel!=-1) {
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
						System.out.printf("%-30s-------------------------------------------\n", "");
						System.out.printf("%-30s| Which action would you like to perform? |\n", "");
						System.out.printf("%-30s| ======================================= |\n", "");
						System.out.printf("%-30s|                                         |\n", "");
						System.out.printf("%-30s|        1. Edit items in cart            |\n", "");
						System.out.printf("%-30s|        2. Proceed with payment          |\n", "");
						System.out.printf("%-30s|                                         |\n", "");
						System.out.printf("%-30s-------------------------------------------\n\n", "");
						
						do {
							System.out.printf("%-30sSelect choice(Enter -1 to cancel): ", "");
							funcChoice=scanner.nextInt();
							scanner.nextLine();
							
							if((funcChoice>2)||(funcChoice==0)||(funcChoice<-1)) {
								System.out.printf("%-10sInvalid Choice. Please try again", "");
							}
							else if(funcChoice==-1) {
								cancel=-1;
							}
						}while((funcChoice>2)||(funcChoice==0)||(funcChoice<-1));
						
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
								System.out.printf("%-10sWould you like to proceed with payment? (Y=yes) ");
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
		}while(onHold==1);
		
		

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
		int memIDNo, checkMemID, paymentMChoice=0, paymentChoice=0, foundMem=0, retryPayment, paymentError=0, cancel=0;
		double receivedAmount = 0, balance;
		Receipt receipt=null;
		payment.setStaff(staff);
		if(payment.getCart().getNoOfProducts()>0) {
			System.out.println("Cart Details");
			System.out.println(payment.getCart().toString());
		}
		
		
		if(payment.getCart().getNoOfProducts()==0) {
			payment.setPaymentDetails(0, "null", "Cancelled");
			System.out.println("Cart is empty. Payment has been cancelled");
		}
		else {
			do {
				System.out.println("Which action would you like to perform?");
				System.out.println("1. Complete payment");
				System.out.println("2. Cancel payment");
				System.out.print("Select Choice(Press -1 to exit): ");
				paymentChoice=scanner.nextInt();
				scanner.nextLine();
				
				if((paymentChoice>2)||(paymentChoice==0)|| (paymentChoice<-1)) {
					System.out.println("Invalid Choice. Please try again");
				}
				else if(paymentChoice==-1) {
					cancel=-1;
				}
			}while((paymentChoice>2)||(paymentChoice==0)|| (paymentChoice<-1));
			
			if(cancel!=-1) {
				if(paymentChoice==2) {
					payment.setPaymentDetails(0, "null", "Cancelled");
					returnQuantity(productList, payment.getCart());
				}
				else {
					do {
						checkMemID=0;
						System.out.print("Enter Member ID(Enter X if there is no memberID): ");
						memID=scanner.next();
						if(memID.toUpperCase().equals("X")) {
							Member member=new Member();
							payment.memberDiscount(member);
						}
						else if((memID.charAt(0)=='M')&&(memID.length()==5)){
							memID=memID.replace("M", "");
							memIDNo=Integer.parseInt(memID);
							checkMemID=Claris.checkMember(memberList, memIDNo);
							if(checkMemID==-1) {
								System.out.println("Invalid member ID. Please try again");
							}
							else{
								payment.memberDiscount(memberList.get(checkMemID));
							}
						}
						else {
							System.out.println("Invalid input. Please try again");
							checkMemID=-1;
						}
						
						
					}while(checkMemID==-1);
					
					System.out.printf("Final Payment Amount(RM): %.2f\n", payment.getPaymentAmount());
					
					do {
						do {
							paymentMChoice=0;
							paymentError=0;
							retryPayment=0;
							System.out.println("Payment Methods");
							System.out.println("1. Cash");
							System.out.println("2. Credit Card");
							System.out.print("Select Payment Method(Enter -1 to cancel): ");
							paymentMChoice=scanner.nextInt();
							scanner.nextLine();
							
							if((paymentMChoice>2)||(paymentMChoice==0)||(paymentMChoice<-1)) {
								System.out.println("Invalid Choice. Please try again");
							}
							else if(paymentMChoice==-1) {
								cancel=-1;
							}
						}while((paymentMChoice>2)||(paymentMChoice==0)||(paymentMChoice<-1));
						
						if(cancel!=-1) {
							do {
								paymentError=0;
								retryPayment=0;
								if(paymentMChoice==1) {
									
									System.out.print("Enter Amount received(Enter -1 to cancel): ");
									receivedAmount=scanner.nextDouble();
									scanner.nextLine();
									
									if((receivedAmount>-1&&receivedAmount<payment.getPaymentAmount())||(receivedAmount<-1)) {
										System.out.println("Insufficient Amount");
										paymentError=1;
									}
									else if(receivedAmount==-1) {
										cancel=-1;
									}
									else {
										paymentMethod="Cash";
									}
								}
								else if(paymentMChoice==2){
									System.out.print("Enter Credit Card Number(Enter -1 to cancel): ");
									creditCardNo=scanner.next();
									scanner.nextLine();
									if((creditCardNo.length()!=16)&&(creditCardNo.equals("-1")==false)) {
										System.out.println("Invalid Credit Card Number.");
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
								else if(paymentMChoice==-1) {
									cancel=-1;
								}
								
								if(cancel!=-1) {
									if(paymentError==1) {
										System.out.println("Please try again or change payment method");
										do {
											System.out.println("1. Retry");
											System.out.println("2. Change Payment Method");
											System.out.print("Enter Choice(Enter -1 to cancel): ");
											retryPayment=scanner.nextInt();
											scanner.nextLine();
											
											if((retryPayment>2)||(retryPayment==0)||(retryPayment<-1)) {
												System.out.println("Invalid Choice. Please try again");
											}
											else if(retryPayment==-1) {
												cancel=-1;
											}
										}while((retryPayment>2)||(retryPayment==0)||(retryPayment<-1));
									}
								}
								
							}while(retryPayment==1);
						}
						
						
					}while(retryPayment==2);
					
					if(cancel!=-1) {
						payment.setPaymentDetails(receivedAmount, paymentMethod, "Completed");
						System.out.println(payment.toString());
						bankAccount.addPayment(payment);
						System.out.println(bankAccount.getRevenue());
						System.out.println("Generate receipt");
						genReceipt(receiptList, receipt, payment);
					}
				}
				
			}
		}
		
		if(cancel==-1) {
			System.out.printf("%-10sPayment Process has been cancelled\n", "");
		}
		
	}
	
	
	public static void genReceipt(ArrayList<Receipt> receiptList, Receipt receipt, Payment payment) {
		receipt=new Receipt(payment);
		System.out.println(receipt.toString());
		receiptList.add(receipt);
	}
	
	public static void cart(ArrayList<Staff> staffList, ArrayList<Product> productList, ArrayList<Member> memberList, ArrayList<Payment> paymentList, Staff staff, ArrayList<Receipt> receiptList, BankAccount bankAccount) {
		int newQty, memIDNo, checkMemID=0, cancel=0;;
		char choice='N', addProdChoice='N', editChoice='N', paymentChoice='N', orderChoice='N';
		String memID;
		Scanner scanner=new Scanner(System.in);
		Receipt receipt=null;
		Product product=null;
		Payment payment=null;
		Product[] prodArray=new Product[100];
		System.out.println("Staff Name: "+ staff.getName());
		
		
		do {
			Cart cart=new Cart();
			do {
				choice='N';
				addProdChoice='N';
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
						System.out.print("Would you like to add this product to cart?(Y=yes) ");
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
							System.out.println("Product has been added to cart.");
						}
					}
					else {
						System.out.println("Product not added to cart");
					}
					
					
					System.out.print("Would you like to add another product?(Y=yes) ");
					choice=Character.toUpperCase(scanner.next().charAt(0));
					scanner.nextLine();
				}
				
			}while(choice=='Y');
			if(cancel!=-1) {
				if(cart.getNoOfProducts()>0) {
					System.out.println(cart.toString());
					
					System.out.print("Would you like to edit the items in the cart? (Y=yes) ");
					editChoice=Character.toUpperCase(scanner.next().charAt(0));
					scanner.nextLine();
					if(editChoice=='Y') {
						editCart(productList, cart);
					}
					else {
						System.out.println("Items in cart are not edited.");
					}
					
					if(cart.getNoOfProducts()>0) {
						System.out.print("Would you like to proceed with payment? (Y=yes)");
						paymentChoice=Character.toUpperCase(scanner.next().charAt(0));
						scanner.nextLine();
						payment=new Payment(cart, "On-Hold");
						if(paymentChoice=='Y') {
							paymentFunc(payment, memberList, staff, bankAccount, receiptList, productList);
						}
						else {
							System.out.println("Payment has been put on-hold.");
						}
						paymentList.add(payment);
					}
					else {
						System.out.println("Cart is empty");
					}
				}
				else {
					System.out.println("Cart is empty");
				}
			}
			
			System.out.println();
			System.out.print("Would you like to place another order? (Y=yes) ");
			orderChoice=Character.toUpperCase(scanner.next().charAt(0));
			scanner.nextLine();
		}while(orderChoice=='Y');	
	}
}
