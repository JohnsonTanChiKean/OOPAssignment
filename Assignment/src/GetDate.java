import java.util.Date;
import java.text.SimpleDateFormat;
public class GetDate {
	private String fullDateTime;
	
	public GetDate() {
		this.fullDateTime=setFullDateTime();
	}
	
	public GetDate(String fullDateTime) {
		this.fullDateTime=fullDateTime;
	}
	
	
	private String setFullDateTime() {
		Date date=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
		String strDate=dateFormat.format(date);
		if(strDate.substring(11, 13).equals("24")) {
			String tempDate;
			tempDate=strDate.substring(0, 11)+"00"+strDate.substring(13, strDate.length());
			strDate=tempDate;
		}
		return strDate;
	}
	
	public String toString() {
		return fullDateTime;
	}
	
	public boolean equals(Object o) {
		if(o instanceof GetDate) {
			if(((GetDate)o).fullDateTime.equals(fullDateTime)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
