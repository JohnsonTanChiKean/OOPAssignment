
public class Staff extends Person{
	private String staffID, position, password;
	private int idNo;
	private double salary;
	private GetDate joinDate;
	private String status;
	private static int staffCount;
	private static int genIDNo;
	
	public Staff() {
		
	}
	
	public Staff(String name, String staffID, int idNo) {
		super.setName(name);
		setStaffID(staffID);
		setIdNo(idNo);
		staffCount++;
	}
	public Staff(String password) {
		this.password=password;
	}
	
	public Staff(String name, String icNo, String birthDate, String staffID, int idNo, String password, String position, double salary, String joinDate, String status) {
		super(name, icNo, birthDate);
		setStaffID(staffID);
		setIdNo(idNo);
		setPosition(position);
		setSalary(salary);
		setPassword(password);
		this.joinDate=new GetDate(joinDate);
		this.status=status;
		this.genIDNo=idNo;
		this.genIDNo++;
		staffCount++;
	}
	
	public String getFullStaffID() {
		return staffID+idNo;
	}
	
	
	public String getStaffID() {
		return staffID;
	}
	
	public void setStaffID(String staffID) {
		this.staffID = staffID;
		if(staffID.equals("manager")) {
			salary=2500;
		}
		else if(staffID.equals("executive")) {
			salary=2000;
		}
		else if(staffID.equals("cashier")) {
			salary=1700;
		}
	}
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public int getIdNo() {
		return idNo;
	}
	
	public void setIdNo(int idNo) {
		this.idNo = idNo;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		String staffDetails="";
		staffDetails+=String.format("%s", getFullStaffID());
		return staffDetails;
	}
	public String appendStaff() {
		String staffFile="";
		staffFile+=String.format("%s,%s,%s,S,%s,%d,%s,%s,%.2f,%s,%s\n",getName(),getIcNo(),getBirthDate(),staffID,idNo,password,position,salary,joinDate,status);
		return staffFile;
	}
	public boolean equals(Object o) {
		Staff acc=(Staff) o;
		return acc.equals(acc.getPassword());
	}
}
