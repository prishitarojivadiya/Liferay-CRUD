package com.emp.common.methods;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.emp.common.constants.EmployeeCommonConstant;
import com.employee.model.Employee;
import com.employee.model.EmployeeTable;
import com.employee.service.EmployeeLocalService;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(immediate = true, service = EmployeeCommonService.class)
public class EmployeeCommonServiceImpl implements EmployeeCommonService {

	@Reference
	private EmployeeLocalService employeeLocalService;

	@Override
	public List<Employee> getEmployeeList(String searchString, Long userId) {

		DSLQuery dslQuery;
		if (Validator.isNotNull(searchString)) {
			String search = StringPool.PERCENT + searchString + StringPool.PERCENT;
			long empId=0L;
			try {
				empId = Long.parseLong(searchString);
			} catch (Exception e) {
				log.error(e);
			}
			Predicate userPredict = Predicate.withParentheses((EmployeeTable.INSTANCE.employeeId.eq(empId))
					.or(EmployeeTable.INSTANCE.firstName.like(search)).or(EmployeeTable.INSTANCE.lastName.like(search))
					.or(EmployeeTable.INSTANCE.mobileNumber.like(search))
					.or(EmployeeTable.INSTANCE.emailAddress.like(search)));
			dslQuery = DSLQueryFactoryUtil.select().from(EmployeeTable.INSTANCE)
					.where((EmployeeTable.INSTANCE.createdby.eq(userId)).and(userPredict));

		} else {
			dslQuery = DSLQueryFactoryUtil.select().from(EmployeeTable.INSTANCE)
					.where(EmployeeTable.INSTANCE.createdby.eq(userId));
		}
		return employeeLocalService.dslQuery(dslQuery);
	}

	@Override
	public Employee updateEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
		long userId = Long.parseLong(actionRequest.getRemoteUser());
		long employeeId = ParamUtil.getLong(actionRequest, EmployeeCommonConstant.EMPLOYEE_ID, GetterUtil.DEFAULT_LONG);
		String firstName = ParamUtil.getString(actionRequest, EmployeeCommonConstant.FIRST_NAME,
				GetterUtil.DEFAULT_STRING);
		String lastName = ParamUtil.getString(actionRequest, EmployeeCommonConstant.LAST_NAME,
				GetterUtil.DEFAULT_STRING);
		String emailAddress = ParamUtil.getString(actionRequest, EmployeeCommonConstant.EMAIL_ADDRESS,
				GetterUtil.DEFAULT_STRING);
		String mobileNumber = ParamUtil.getString(actionRequest, EmployeeCommonConstant.MOBILE_NUMBER,
				GetterUtil.DEFAULT_STRING);
		return employeeLocalService.updateEmployee(userId, employeeId, firstName, lastName, emailAddress, mobileNumber);
	}

	@Override
	public void deleteEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
		long employeeId = ParamUtil.getLong(actionRequest, EmployeeCommonConstant.EMPLOYEE_ID, GetterUtil.DEFAULT_LONG);
		try {
			employeeLocalService.deleteEmployee(employeeId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	private static final Log log = LogFactoryUtil.getLog(EmployeeCommonServiceImpl.class);

}
