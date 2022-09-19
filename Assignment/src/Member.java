
public class Member extends Person{
	private String memberID = "M", membership, activePeriod, mbrStatus;
	private int idNo;
	private Staff registeredBy;
	private GetDate registrationDate;
	private static int genIDNo;
	private static int memberCount;
	
	public Member() {
		memberID = "null";
		membership = "null";
		idNo = 0;
	}
	
	public Member(String name, String icNo, String contactNum, String membership, String activePeriod, Staff staff) {
		super(name, icNo, contactNum);
		this.membership = membership;
		this.activePeriod=activePeriod;
		mbrStatus="active";
		this.registeredBy = staff;
		idNo = genIDNo;
		genIDNo++;
		memberCount++;
		registrationDate = new GetDate();
	}
	
	public Member(String name, String memberID, int idNo, String membership) {
		super.setName(name);
		setMemberID(memberID);
		setMembership(membership);
		setIdNo(idNo);
		memberCount++;
	}
	
	public Member(String name, String icNo, String birthDate, String contactNum, String memberID, int idNo, String membership, String activePeriod, String mbrStatus, Staff registeredBy, String registrationDate) {
		super(name, icNo, birthDate, contactNum);
		setMemberID(memberID);
		setMembership(membership);
		setActivePeriod(activePeriod);
		this.mbrStatus = mbrStatus;
		setIdNo(idNo);
		this.registeredBy = registeredBy;
		this.registrationDate = new GetDate(registrationDate);
		this.genIDNo = idNo;
		genIDNo++;
		memberCount++;
	}
	
	//getter & setter
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMembership() {
		return membership;
	}
	public void setMembership(String membership) {
		this.membership = membership;
	}
	public String getActivePeriod() {
		return activePeriod;
	}
	public void setActivePeriod(String activePeriod) {
		this.activePeriod = activePeriod;
	}
	public String getMbrStatus() {
		return mbrStatus;
	}
	public void setMbrStatus(String mbrStatus) {
		this.mbrStatus = mbrStatus;
	}
	public int getIdNo() {
		return idNo;
	}
	public void setIdNo(int idNo) {
		this.idNo = idNo;
	}
	public String getFullMemID() {
		return memberID + idNo;
	}
	public Staff getRegisteredBy() {
		return registeredBy;
	}
	public GetDate getRegistrationDate() {
		return registrationDate;
	}
	
	public String toString() {
		String memberDetails = "";
		memberDetails += String.format("%-10s----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		memberDetails += String.format("%-10s|                                                                                                                                                                                                  |\n", "");
		memberDetails += String.format("%-10s|   %-17s%-30s%-25s%-15s%-18s%-13s%-17s%-17s%-17s%-19s   |\n", "", "Member ID", "Name", "Identification Number", "Birth Date", "Contact Number", "Membership", "Active Period", "Member Status", "Registered By", "Registration Date");
		memberDetails += String.format("%-10s|   %-17s%-30s%-25s%-15s%-18s%-13s%-17s%-17s%-17s%-19s   |\n", "", "---------", "----", "---------------------", "----------", "--------------", "----------", "-------------", "-------------", "-------------", "-----------------");
		memberDetails += String.format("%-10s|   %-17s%-30s%-25s%-15s%-18s%-13s%-17s%-17s%-17s%-19s   |\n", "", getFullMemID(), super.getName(), super.getIcNo(), super.getBirthDate(), getContactNum(), getMembership(), getActivePeriod(), getMbrStatus(), registeredBy.getFullStaffID(), registrationDate);
		memberDetails += String.format("%-10s|                                                                                                                                                                                                  |\n", "");
		memberDetails += String.format("%-10s----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n", "");
		return memberDetails;
	}
}
