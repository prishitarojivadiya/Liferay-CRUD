package com.emp.common.employee;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.emp.common.model.EmployeeDTO;
import com.employee.model.Employee;
import com.liferay.portal.kernel.search.Hits;

public interface EmployeeCommonService {

	List<Employee> getEmployeeList(String emailAddress, long userId);
	
	Hits getEmployeeListUsingElastic(long complanyid, long userId, String searchString, String[] filter) throws Exception ;

	Employee updateEmployee(EmployeeDTO employee,long userId);

	void deleteEmployee(long employeeId);
}