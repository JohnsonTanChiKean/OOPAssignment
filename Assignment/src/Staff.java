
public class Staff extends Person{
	private String staffID, position, password;
	private int idNo;
	private double salary;
	private static int staffCount;
	
	public Staff() {
		
	}
	
	public Staff(String name, String staffID, int idNo) {
		super.setName(name);
		setStaffID(staffID);
		setIdNo(idNo);
		staffCount++;
	}
	
	public Staff(String name, String icNo, String birthDate, String staffID, int idNo, String password, String position, double salary) {
		super(name, icNo, birthDate);
		setStaffID(staffID);
		setIdNo(idNo);
		setPosition(position);
		setSalary(salary);
		setPassword(password);
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

}
