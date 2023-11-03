package com.emp.common.methods;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.employee.model.Employee;
import com.liferay.petra.sql.dsl.query.DSLQuery;

public interface EmployeeCommonService {

	List<Employee> getEmployeeList(String emailAddress, Long userId);

	Employee updateEmployee(ActionRequest actionRequest, ActionResponse actionResponse);

	void deleteEmployee(ActionRequest actionRequest, ActionResponse actionResponse);

}