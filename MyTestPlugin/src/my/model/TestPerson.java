package my.model;

public class TestPerson {
	private int empId;
	private String firstName;
	private String lastName;

	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public TestPerson(Integer iD, String firstName, String lastName) {
		empId = iD;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String toString() {
		return ""+empId+":"+firstName+":"+lastName;
	}
	
	
	
}
