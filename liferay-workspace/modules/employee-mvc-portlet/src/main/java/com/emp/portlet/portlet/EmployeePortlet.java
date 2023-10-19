package com.emp.portlet.portlet;

import com.emp.portlet.constants.EmployeePortletKeys;
import com.employee.model.Employee;
import com.employee.service.EmployeeLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author ignek
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.employee",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Employee", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp", "javax.portlet.name=" + EmployeePortletKeys.EMPLOYEE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class EmployeePortlet extends MVCPortlet {
	
	@Reference
	private EmployeeLocalService employeeLocalService;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		List<Employee> employeeList = employeeLocalService.getEmployees(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		renderRequest.setAttribute("employeeList", employeeList);
		super.render(renderRequest, renderResponse);
	}

	@ProcessAction(name = "addEmployee")
	public void updateOrInsertEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
		long employeeId = ParamUtil.getLong(actionRequest, EmployeePortletKeys.EMPLOYEE_ID, GetterUtil.DEFAULT_LONG);
		System.out.print("\n employeeId  ---> " + ParamUtil.getLong(actionRequest, "employeeId", GetterUtil.DEFAULT_LONG) + "\n");
		String firstName = ParamUtil.getString(actionRequest, EmployeePortletKeys.FIRST_NAME , GetterUtil.DEFAULT_STRING);
		String lastName = ParamUtil.getString(actionRequest, EmployeePortletKeys.LAST_NAME, GetterUtil.DEFAULT_STRING);
		String emailAddress = ParamUtil.getString(actionRequest, EmployeePortletKeys.EMAIL_ADDRESS, GetterUtil.DEFAULT_STRING);
		String mobileNumber = ParamUtil.getString(actionRequest, EmployeePortletKeys.MOBILE_NUMBER, GetterUtil.DEFAULT_STRING);
		employeeLocalService.updateEmployee(employeeId, firstName, lastName, emailAddress, mobileNumber);
	}

	@ProcessAction(name = "deleteEmployee")
	public void deleteEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
		long employeeId = ParamUtil.getLong(actionRequest, "employeeId", GetterUtil.DEFAULT_LONG);
		try {
			employeeLocalService.deleteEmployee(employeeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}