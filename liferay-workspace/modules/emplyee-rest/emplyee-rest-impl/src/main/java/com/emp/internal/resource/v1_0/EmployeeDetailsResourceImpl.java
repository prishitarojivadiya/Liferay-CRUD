package com.emp.internal.resource.v1_0;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import com.emp.dto.v1_0.EmployeeDetails;
import com.emp.dto.v1_0.Status;
import com.emp.portlet.constants.EmployeePortletKeys;
import com.emp.resource.v1_0.EmployeeDetailsResource;
import com.employee.model.Employee;
import com.employee.service.EmployeeLocalService;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.pagination.Page;

/**
 * @author ignek
 */
@Component(properties = "OSGI-INF/liferay/rest/v1_0/employee-details.properties", scope = ServiceScope.PROTOTYPE, service = EmployeeDetailsResource.class)
public class EmployeeDetailsResourceImpl extends BaseEmployeeDetailsResourceImpl {

	@Reference
	private EmployeeLocalService employeeLocalService;

	@Override
	public Page<EmployeeDetails> getEmployeeList() throws Exception {
		List<EmployeeDetails> employeeLists = new ArrayList<>();
		List<Employee> employeeList = employeeLocalService.getEmployees(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		for (Employee emp : employeeList) { // EmployeeDetails employee = getEmployee(emp);
			EmployeeDetails employee = new EmployeeDetails();
			BeanPropertiesUtil.copyProperties(emp, employee);
			System.out.println("employee------>" + employee);
			employeeLists.add(employee);
		}

		return Page.of(employeeLists);
	}

	@Override
	public EmployeeDetails getEmployeeById(@NotNull Long employeeId) throws Exception {
		EmployeeDetails employee = new EmployeeDetails();
		Employee employee2 = employeeLocalService.fetchEmployee(employeeId);
		BeanPropertiesUtil.copyProperties(employee2, employee);
		return employee;
	}

	@Override
	public Status insertEmployee(EmployeeDetails employeeDetails) throws Exception {
		Status status = new Status();
		Long empId = employeeDetails.getEmployeeId();
		try {
			employeeLocalService.updateEmployee(empId, employeeDetails.getFirstName(),employeeDetails.getLastName(), employeeDetails.getEmailAddress(), employeeDetails.getMobileNumber());
			status.setMessage(Validator.isNotNull(empId) ? EmployeePortletKeys.UPDATE_MESSAGE : EmployeePortletKeys.INSERT_MESSAGE);
			status.setStatusCode(Response.Status.OK.getStatusCode());
		} catch (Exception e) {
			status.setMessage(e.getMessage());
			status.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}

		return status;
	}

	@Override 
	public Status deleteEmployee(@NotNull Long employeeId) throws Exception 
	{
	 Status status = new Status();
	 try {
		 employeeLocalService.deleteEmployee(employeeId);
		 status.setStatusCode(Response.Status.OK.getStatusCode());
		 status.setMessage(EmployeePortletKeys.DELETE_MESSAGE);
		 
	 }catch (Exception e) {
		 status.setMessage(e.getMessage());
		 status.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
	 }
	 return status; 
	}
	 

}