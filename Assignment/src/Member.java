
public class Member extends Person{
	private String memberID, membership;
	private int idNo;
	private Staff registeredBy;
	private GetDate registrationDate;
	private static int genIDNo;
	private static int memberCount;
	
	public Member() {
		memberID="null";
		membership="null";
		idNo=0;
	}
	
	public Member(String name, String memberID, int idNo, String membership) {
		super.setName(name);
		setMemberID(memberID);
		setMembership(membership);
		setIdNo(idNo);
		memberCount++;
	}
	
	public Member(String name, String icNo, String birthDate, String memberID, int idNo, String membership, Staff registeredBy, String registrationDate) {
		super(name, icNo, birthDate);
		setMemberID(memberID);
		setMembership(membership);
		setIdNo(idNo);
		this.registeredBy=registeredBy;
		this.registrationDate=new GetDate(registrationDate);
		this.genIDNo=idNo;
		genIDNo++;
		memberCount++;
	}
	
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
	public int getIdNo() {
		return idNo;
	}
	public void setIdNo(int idNo) {
		this.idNo = idNo;
	}
	
	public String getFullMemID() {
		return memberID+idNo;
	}
	
	
	public String toString() {
		String memberDetails="";
		memberDetails+=String.format("%s", getFullMemID());
		return memberDetails;
	}
}
