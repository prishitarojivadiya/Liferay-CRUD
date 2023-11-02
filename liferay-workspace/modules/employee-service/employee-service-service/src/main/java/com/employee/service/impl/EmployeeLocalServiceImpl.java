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

package com.employee.service.impl;

import com.employee.model.Employee;
import com.employee.service.base.EmployeeLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(property = "model.class.name=com.employee.model.Employee", service = AopService.class)
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {

	public List<Employee> fetchByEmailAddress(String emailAddress) {
		return employeePersistence.findByEmailAddress(emailAddress);
	}

	public Employee updateEmployee(long employeeId, String firstName, String lastName, String emailAddress,
			String mobileNumber) {
		Employee employee = employeePersistence.fetchByPrimaryKey(employeeId);

		if (Validator.isNull(employee)) {
			employee = employeePersistence.create(counterLocalService.increment());
		}

		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setEmailAddress(emailAddress);
		employee.setMobileNumber(mobileNumber);

		return employeePersistence.update(employee);
	}
}