package com.emp.portlet.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.emp.common.constants.EmployeeCommonConstant;
import com.emp.common.employee.EmployeeCommonService;
import com.emp.common.model.EmployeeDTO;
import com.emp.portlet.constants.EmployeePortletKeys;
import com.employee.service.EmployeeLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

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
	 * I have used dsl uery on button click because of that i have commented render
	 * method
	 * 
	 * @Override public void render(RenderRequest renderRequest, RenderResponse
	 * renderResponse) throws IOException, PortletException {
	 * CacheRegistryUtil.clear(); List<Employee> employeeList =
	 * employeeCommonService.getEmployeeList(StringPool.BLANK);
	 * renderRequest.setAttribute("employeeList", employeeList);
	 * log.info("render called"); super.render(renderRequest, renderResponse); }
	 */

	@ProcessAction(name = "addEmployee")
	public void updateEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
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
		String[] categories = ParamUtil.getStringValues(actionRequest, EmployeeCommonConstant.CATEGORY,
				GetterUtil.DEFAULT_STRING_VALUES);
		EmployeeDTO employee = new EmployeeDTO();
		employee.setEmployeeId(employeeId);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setEmailAddress(emailAddress);
		employee.setMobileNumber(mobileNumber);
		String category = StringPool.DOUBLE_QUOTE;
		for (String cat : categories) {
			category = (cat == categories[0]) ? cat : category + StringPool.COMMA + cat;
		}
		employee.setCategory(category);
		employeeCommonService.updateEmployee(employee, userId);
	}

	@ProcessAction(name = "deleteEmployee")
	public void deleteEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
		long employeeId = ParamUtil.getLong(actionRequest, EmployeeCommonConstant.EMPLOYEE_ID, GetterUtil.DEFAULT_LONG);
		employeeCommonService.deleteEmployee(employeeId);
	}

	@ProcessAction(name = "searchEmployee")
	public void fetchByEmailAddress(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException {
		String searchString = ParamUtil.getString(actionRequest, EmployeeCommonConstant.SEARCH);
		String[] filter = ParamUtil.getStringValues(actionRequest, EmployeeCommonConstant.FILTER);
		long companyId = PortalUtil.getCompanyId(actionRequest);
		long userId = Long.parseLong(actionRequest.getRemoteUser());
		try {

			Hits hits = employeeCommonService.getEmployeeListUsingElastic(companyId, userId, searchString, filter);
			Document[] documents = hits.getDocs();
			List<EmployeeDTO> employeeList = new ArrayList<>();
			for (Document document : documents) {
				long employeeId = Long.parseLong(document.get(EmployeeCommonConstant.EMPLOYEE_ID));
				String firstName = document.get(EmployeeCommonConstant.FIRST_NAME);
				String lastName = document.get(EmployeeCommonConstant.LAST_NAME);
				String emailAddress = document.get(EmployeeCommonConstant.EMAIL_ADDRESS);
				String mobileNumber = document.get(EmployeeCommonConstant.MOBILE_NUMBER);
				String category = document.get(EmployeeCommonConstant.CATEGORY);
				employeeList
						.add(new EmployeeDTO(employeeId, firstName, lastName, emailAddress, mobileNumber, category));
			}
			actionRequest.setAttribute("employeeList", employeeList);
		} catch (Exception e) {
			log.error(e);
		}

	}

	private static final Log log = LogFactoryUtil.getLog(EmployeePortlet.class);
}