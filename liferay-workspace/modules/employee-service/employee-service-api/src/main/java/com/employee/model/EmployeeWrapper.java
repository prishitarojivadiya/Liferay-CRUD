/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.employee.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Employee}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Employee
 * @generated
 */
public class EmployeeWrapper
	extends BaseModelWrapper<Employee>
	implements Employee, ModelWrapper<Employee> {

	public EmployeeWrapper(Employee employee) {
		super(employee);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("employeeId", getEmployeeId());
		attributes.put("firstName", getFirstName());
		attributes.put("lastName", getLastName());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("mobileNumber", getMobileNumber());
		attributes.put("createdby", getCreatedby());
		attributes.put("modifiedby", getModifiedby());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long employeeId = (Long)attributes.get("employeeId");

		if (employeeId != null) {
			setEmployeeId(employeeId);
		}

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		String mobileNumber = (String)attributes.get("mobileNumber");

		if (mobileNumber != null) {
			setMobileNumber(mobileNumber);
		}

		Long createdby = (Long)attributes.get("createdby");

		if (createdby != null) {
			setCreatedby(createdby);
		}

		Long modifiedby = (Long)attributes.get("modifiedby");

		if (modifiedby != null) {
			setModifiedby(modifiedby);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public Employee cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the create date of this employee.
	 *
	 * @return the create date of this employee
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the createdby of this employee.
	 *
	 * @return the createdby of this employee
	 */
	@Override
	public long getCreatedby() {
		return model.getCreatedby();
	}

	/**
	 * Returns the email address of this employee.
	 *
	 * @return the email address of this employee
	 */
	@Override
	public String getEmailAddress() {
		return model.getEmailAddress();
	}

	/**
	 * Returns the employee ID of this employee.
	 *
	 * @return the employee ID of this employee
	 */
	@Override
	public long getEmployeeId() {
		return model.getEmployeeId();
	}

	/**
	 * Returns the first name of this employee.
	 *
	 * @return the first name of this employee
	 */
	@Override
	public String getFirstName() {
		return model.getFirstName();
	}

	/**
	 * Returns the last name of this employee.
	 *
	 * @return the last name of this employee
	 */
	@Override
	public String getLastName() {
		return model.getLastName();
	}

	/**
	 * Returns the mobile number of this employee.
	 *
	 * @return the mobile number of this employee
	 */
	@Override
	public String getMobileNumber() {
		return model.getMobileNumber();
	}

	/**
	 * Returns the modifiedby of this employee.
	 *
	 * @return the modifiedby of this employee
	 */
	@Override
	public long getModifiedby() {
		return model.getModifiedby();
	}

	/**
	 * Returns the modified date of this employee.
	 *
	 * @return the modified date of this employee
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this employee.
	 *
	 * @return the primary key of this employee
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the uuid of this employee.
	 *
	 * @return the uuid of this employee
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the create date of this employee.
	 *
	 * @param createDate the create date of this employee
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the createdby of this employee.
	 *
	 * @param createdby the createdby of this employee
	 */
	@Override
	public void setCreatedby(long createdby) {
		model.setCreatedby(createdby);
	}

	/**
	 * Sets the email address of this employee.
	 *
	 * @param emailAddress the email address of this employee
	 */
	@Override
	public void setEmailAddress(String emailAddress) {
		model.setEmailAddress(emailAddress);
	}

	/**
	 * Sets the employee ID of this employee.
	 *
	 * @param employeeId the employee ID of this employee
	 */
	@Override
	public void setEmployeeId(long employeeId) {
		model.setEmployeeId(employeeId);
	}

	/**
	 * Sets the first name of this employee.
	 *
	 * @param firstName the first name of this employee
	 */
	@Override
	public void setFirstName(String firstName) {
		model.setFirstName(firstName);
	}

	/**
	 * Sets the last name of this employee.
	 *
	 * @param lastName the last name of this employee
	 */
	@Override
	public void setLastName(String lastName) {
		model.setLastName(lastName);
	}

	/**
	 * Sets the mobile number of this employee.
	 *
	 * @param mobileNumber the mobile number of this employee
	 */
	@Override
	public void setMobileNumber(String mobileNumber) {
		model.setMobileNumber(mobileNumber);
	}

	/**
	 * Sets the modifiedby of this employee.
	 *
	 * @param modifiedby the modifiedby of this employee
	 */
	@Override
	public void setModifiedby(long modifiedby) {
		model.setModifiedby(modifiedby);
	}

	/**
	 * Sets the modified date of this employee.
	 *
	 * @param modifiedDate the modified date of this employee
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this employee.
	 *
	 * @param primaryKey the primary key of this employee
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uuid of this employee.
	 *
	 * @param uuid the uuid of this employee
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected EmployeeWrapper wrap(Employee employee) {
		return new EmployeeWrapper(employee);
	}

}