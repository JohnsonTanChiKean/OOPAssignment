
public class Staff extends Person{
	private String staffID="S", position, password;
	private int idNo;
	private double salary;
	private GetDate joinDate;
	private String status;
	private static int staffCount;
	private static int genIDNo;
	
	public Staff() {
		
	}
	

	public Staff(String name,String icNo,String contactNum,String password,String position) {
		super(name, icNo,contactNum);
	
		this.password=password;
		this.position=position;
		idNo=genIDNo;
		genIDNo++;
		staffCount++;
		status="Active";
		joinDate=new GetDate();
		setSalary(position);
		
		
		}
	
	public Staff(String name, String icNo, String birthDate,String contactNum, String staffID, int idNo, String password, String position, double salary, String joinDate, String status) {
		
		super(name,icNo,birthDate,contactNum);
		setStaffID(staffID);
		setIdNo(idNo);
		this.position=position;
		this.salary=salary;
		setPassword(password);
		this.joinDate=new GetDate(joinDate);
		this.status=status;
		this.genIDNo=idNo;
		this.genIDNo++;
		staffCount++;
	}

	public GetDate getJoinDate() {
		return joinDate;
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
		if(position.equals("X")) {
			this.position = "manager";
		}
		else if(position.equals("Y")) {
			this.position = "executive";
		}
		else if(position.equals("Z")) {
		this.position ="cashier";
		}
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
	
	public void setSalary(String position) {
		
		if(position.equals("manager")) {
			salary=2500;
		}
		else if(position.equals("executive")) {
			salary=2000;
		}
		else if(position.equals("cashier")) {
			salary=1700;
		}
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStatus(String reply) {
		if(reply.equals("Y")&&status.equals("Active")) {
			status="Resign";
		}
		else if(reply.equals("Resign")&&status.equals("Resign")){
			status="Active";
		}
		else if(reply.equals("Deactivate")) {
			status="Deactivate";
		}
		else if(reply.equals("Activate")) {
			status="Active";
		}
	}
	public String getStatus() {
		return status;
	}
	public String toString() {
		String staffDetails="";
		staffDetails+=String.format("%s", getFullStaffID());
		return staffDetails;
	}

	public boolean equals(Object o) {
		Staff acc=(Staff) o;
		if(o instanceof Staff) {
		return acc.equals(acc.getPassword());
		}
		else {
			return false;
		}
	}
}
