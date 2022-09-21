
public abstract class Person {
	private String name, icNo, birthDate, contactNum;
	
	public Person() {
		
	}
	
	public Person(String name, String icNo, String contactNum) {
		this.name = name;
		this.icNo = icNo;
		genBirthDate();
		this.contactNum=contactNum;
	}
	
	public Person(String name, String icNo, String birthDate, String contactNum) {
		this.name = name;
		this.icNo = icNo;
		this.birthDate = birthDate;
		this.contactNum = contactNum;
	}
	
	//getter & setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcNo() {
		return icNo;
	}
	public void setIcNo(String icNo) {
		this.icNo = icNo;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	
	//GENERATE BIRTH DATE
	public void genBirthDate() {
		String month = icNo.substring(2, 4);
		
		if(month.equals("01")) {
			month = "JAN";
		}
		else if(month.equals("02")) {
			month = "FEB";
		}
		else if(month.equals("03")) {
			month = "MAR";
		}
		else if(month.equals("04")) {
			month = "APR";
		}
		else if(month.equals("05")) {
			month = "MAY";
		}
		else if(month.equals("06")) {
			month = "JUN";
		}
		else if(month.equals("07")) {
			month = "JUL";
		}
		else if(month.equals("08")) {
			month = "AUG";
		}
		else if(month.equals("09")) {
			month ="SEP";
		}
		else if(month.equals("10")) {
			month = "OCT";
		}
		else if(month.equals("11")) {
			month = "NOV";
		}
		else if(month.equals("12")) {
			month = "DEC";
		}
		else {
			month = "XXX";
		}
		
		int year = Integer.parseInt(icNo.substring(0, 2));
		String newYear = "";
		
		if(year >= 0 && year <= 22) {
			newYear = "20" + icNo.substring(0, 2);
		}
		else {
			newYear = "19" + icNo.substring(0, 2);
		}
		
		
		birthDate = icNo.substring(4, 6) + "-" + month + "-" + newYear;
	}
	
	public abstract String toString();
	public abstract boolean equals(Object o);
}
