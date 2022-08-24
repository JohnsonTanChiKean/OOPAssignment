import java.util.ArrayList;

public class Claris {
	public static int checkMember(ArrayList<Member> memberList, int memIDNo) {
		int foundMember=0, index=0;
		for(int i=0; i<memberList.size(); i++) {
			if(memIDNo==memberList.get(i).getIdNo()) {
				foundMember=1;
				index=i;
			}
		}
		if(foundMember==1) {
			return index;
		}
		else {
			return -1;
		}
	}
	
	public void testing() {
		
	}
}
