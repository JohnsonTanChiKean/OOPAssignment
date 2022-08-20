
public class Person {
	private String name, icNo, birthDate;
	
	public Person() {
		
	}
	
	public Person(String name, String icNo, String birthDate) {
		setName(name);
		setIcNo(icNo);
		setBirthDate(birthDate);
	}
	
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
	
	
}
