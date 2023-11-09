package com.emp.client.dto.v1_0;

import com.emp.client.function.UnsafeSupplier;
import com.emp.client.serdes.v1_0.EmployeeDetailsSerDes;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author ignek
 * @generated
 */
@Generated("")
public class EmployeeDetails implements Cloneable, Serializable {

	public static EmployeeDetails toDTO(String json) {
		return EmployeeDetailsSerDes.toDTO(json);
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setEmailAddress(
		UnsafeSupplier<String, Exception> emailAddressUnsafeSupplier) {

		try {
			emailAddress = emailAddressUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String emailAddress;

	public String[] getEmployeeCategory() {
		return employeeCategory;
	}

	public void setEmployeeCategory(String[] employeeCategory) {
		this.employeeCategory = employeeCategory;
	}

	public void setEmployeeCategory(
		UnsafeSupplier<String[], Exception> employeeCategoryUnsafeSupplier) {

		try {
			employeeCategory = employeeCategoryUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String[] employeeCategory;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeId(
		UnsafeSupplier<Long, Exception> employeeIdUnsafeSupplier) {

		try {
			employeeId = employeeIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long employeeId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setFirstName(
		UnsafeSupplier<String, Exception> firstNameUnsafeSupplier) {

		try {
			firstName = firstNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String firstName;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLastName(
		UnsafeSupplier<String, Exception> lastNameUnsafeSupplier) {

		try {
			lastName = lastNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String lastName;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setMobileNumber(
		UnsafeSupplier<String, Exception> mobileNumberUnsafeSupplier) {

		try {
			mobileNumber = mobileNumberUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String mobileNumber;

	@Override
	public EmployeeDetails clone() throws CloneNotSupportedException {
		return (EmployeeDetails)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmployeeDetails)) {
			return false;
		}

		EmployeeDetails employeeDetails = (EmployeeDetails)object;

		return Objects.equals(toString(), employeeDetails.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return EmployeeDetailsSerDes.toJSON(this);
	}

}