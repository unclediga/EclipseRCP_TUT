package my.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TestPerson{
	private int empId;
	private String firstName;
	private String lastName;
	
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
    
    public void removePropertyChangeListener(PropertyChangeListener lst) {
		propertyChangeSupport.removePropertyChangeListener(lst);
	}

    public void addPropertyChangeListener(String propertyName,PropertyChangeListener lst) {
    	propertyChangeSupport.addPropertyChangeListener(propertyName,lst);
    }
    
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		propertyChangeSupport.firePropertyChange("empId", this.empId, this.empId = empId);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		propertyChangeSupport.firePropertyChange("firstName", this.firstName, this.firstName = firstName);
		
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		propertyChangeSupport.firePropertyChange("lastName", this.lastName, this.lastName = lastName);

	}
	public TestPerson(Integer iD, String firstName, String lastName) {
		empId = iD;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public TestPerson() {
	}
	
	public String toString() {
		return ""+empId+":"+firstName+":"+lastName;
	}
	
	
	
}
