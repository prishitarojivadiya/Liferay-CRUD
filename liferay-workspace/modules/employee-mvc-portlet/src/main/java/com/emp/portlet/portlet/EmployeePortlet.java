package com.emp.portlet.portlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.emp.common.methods.EmployeeCommonService;
import com.emp.portlet.constants.EmployeePortletKeys;
import com.employee.model.Employee;
import com.employee.service.EmployeeLocalService;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

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
	
	@Reference
	private EmployeeCommonService employeeCommonService;

	
	/*
	 * @Override public void render(RenderRequest renderRequest, RenderResponse
	 * renderResponse) throws IOException, PortletException {
	 * CacheRegistryUtil.clear(); List<Employee> employeeList =
	 * employeeCommonService.getEmployeeList(StringPool.BLANK);
	 * renderRequest.setAttribute("employeeList", employeeList);
	 * log.info("render called"); super.render(renderRequest, renderResponse); }
	 */

	@ProcessAction(name = "addEmployee")
	public void updateEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
		employeeCommonService.updateEmployee(actionRequest, actionResponse);
	}

	@ProcessAction(name = "deleteEmployee")
	public void deleteEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
		employeeCommonService.deleteEmployee(actionRequest, actionResponse);
	}
	
	@ProcessAction(name = "searchEmployee")
	public void fetchByEmailAddress(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {
		String searchString = ParamUtil.getString(actionRequest, "Search");
		long userId = Long.parseLong(actionRequest.getRemoteUser());
		CacheRegistryUtil.clear();
		List<Employee> employeeList = employeeCommonService.getEmployeeList(searchString,userId);
		actionRequest.setAttribute("employeeList", employeeList);
	}
	
	private static final Log log = LogFactoryUtil.getLog(EmployeePortlet.class);
}