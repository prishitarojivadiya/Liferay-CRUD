package com.emp.common.model;

public class EmployeeDTO {

	long employeeId;
	String firstName;
	String lastName;
	String emailAddress;
	String mobileNumber;
	String category;
	

	public EmployeeDTO(long employeeId,String firstName,String lastName,String emailAddress,String mobileNumber,String category){
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.mobileNumber = mobileNumber;
		this.category = category;
	}
	
	public EmployeeDTO(long employeeId,String firstName,String lastName,String emailAddress,String mobileNumber){
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.mobileNumber = mobileNumber;
	}
	
	public EmployeeDTO() {
		
	}
	
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
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
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
}
