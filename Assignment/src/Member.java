
public class Member extends Person{
	private String memberID, membership;
	private int idNo;
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
	
	public Member(String name, String icNo, String birthDate, String memberID, int idNo, String membership) {
		super(name, icNo, birthDate);
		setMemberID(memberID);
		setMembership(membership);
		setIdNo(idNo);
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
}
