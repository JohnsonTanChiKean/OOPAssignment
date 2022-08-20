import java.util.Date;
import java.text.SimpleDateFormat;
public class GetDate {
	private String fullDateTime;
	private String fullDate;
	private String fullTime;
	private String strHours;
	private String strMinutes;
	private String strSeconds;
	private String strDay;
	private String strMonth;
	private String strYear;
	private int hours;
	private int minutes;
	private int seconds;
	private int day;
	private int month;
	private int year;
	
	
	public GetDate() {
		this.fullDateTime=setFullDateTime();
		this.fullDate=setfullDate();
		this.fullTime=setfullTime();
		this.hours=setHours();
		this.minutes=setMinutes();
		this.seconds=setSeconds();
		this.day=setDay();
		this.month=setMonth();
		this.year=setYear();
		this.strHours=setStrHours();
		this.strMinutes=setStrMinutes();
		this.strSeconds=setStrSeconds();
		this.strDay=setStrDay();
		this.strMonth=setStrMonth();
		this.strYear=setStrYear();
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
	
	private String setfullDate() {
		String date=fullDateTime.substring(0, 10);
		return date;
	}
	
	private String setfullTime() {
		String time=fullDateTime.substring(11, 19);
		return time;
	}
	
	private int setHours() {
		int hrs;
		hrs=Integer.parseInt(fullTime.substring(0, 2));
		return hrs;
	}
	
	private int setMinutes() {
		int mins;
		mins=Integer.parseInt(fullTime.substring(3, 5));
		return mins;
	}
	
	private int setSeconds() {
		int secs;
		secs=Integer.parseInt(fullTime.substring(6, 8));
		return secs;
	}
	
	private int setDay() {
		int days;
		days=Integer.parseInt(fullDate.substring(0, 2));
		return days;
	}
	
	private int setMonth() {
		int months;
		months=Integer.parseInt(fullDate.substring(3, 5));
		return months;
	}
	
	private int setYear() {
		int years;
		years=Integer.parseInt(fullDate.substring(6, 10));
		return years;
	}
	
	private String setStrHours() {
		if(hours<10) {
			return "0"+String.valueOf(hours);
		}
		else {
			return String.valueOf(hours);
		}
	}
	
	private String setStrMinutes() {
		if(minutes<10) {
			return "0"+String.valueOf(minutes);
		}
		else {
			return String.valueOf(minutes);
		}
	}
	
	private String setStrSeconds() {
		if(seconds<10) {
			return "0"+String.valueOf(seconds);
		}
		else {
			return String.valueOf(seconds);
		}
	}
	
	private String setStrDay() {
		if(day<10) {
			return "0"+String.valueOf(day);
		}
		else {
			return String.valueOf(day);
		}
	}
	
	private String setStrMonth() {
		if(month<10) {
			return "0"+String.valueOf(month);
		}
		else {
			return String.valueOf(month);
		}
	}
	
	private String setStrYear() {
		return String.valueOf(year);
	}
	
	public String getFullDateTime() {
		return fullDateTime;
	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
	public String getFullDate() {
		return fullDate;
	}

	public String getFullTime() {
		return fullTime;
	}
	
	public String toString() {
		String all="";
		all+=String.format(fullDateTime);
		return all;
	}
	
}
