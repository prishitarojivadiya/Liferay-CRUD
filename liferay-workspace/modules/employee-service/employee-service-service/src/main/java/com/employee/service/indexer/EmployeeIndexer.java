package com.employee.service.indexer;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.emp.common.constants.EmployeeCommonConstant;
import com.employee.model.Employee;
import com.employee.service.EmployeeLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;

@Component(immediate = true, service = Indexer.class)
public class EmployeeIndexer extends BaseIndexer<Employee> {

	@Reference
	private EmployeeLocalService employeeLocalService;

	@Reference
	private UserLocalService userLocalService;

	private static final String CLASS_NAME = Employee.class.getName();

	public EmployeeIndexer() {
		setDefaultSelectedFieldNames(Field.COMPANY_ID, EmployeeCommonConstant.USER_ID,
				EmployeeCommonConstant.EMPLOYEE_ID, EmployeeCommonConstant.FIRST_NAME, EmployeeCommonConstant.LAST_NAME,
				EmployeeCommonConstant.EMAIL_ADDRESS, EmployeeCommonConstant.MOBILE_NUMBER,
				EmployeeCommonConstant.CATEGORY);
		setFilterSearch(Boolean.TRUE);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Employee employee) throws Exception {
		deleteDocument(employee.getCompanyId(), employee.getEmployeeId());
	}

	@Override
	protected Document doGetDocument(Employee employee) throws Exception {
		long userId = employee.getCreatedby();
		long companyId = employee.getCompanyId();
		long employeeId = employee.getEmployeeId();
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String emailAddress = employee.getEmailAddress();
		String mobileNumber = employee.getMobileNumber();
		String category = employee.getCategory();
		Document document = getBaseModelDocument(CLASS_NAME, employee);
		document.addNumber(EmployeeCommonConstant.USER_ID, userId);
		document.addNumber(EmployeeCommonConstant.EMPLOYEE_ID, employeeId);
		document.addKeyword(EmployeeCommonConstant.FIRST_NAME, firstName);
		document.addKeyword(EmployeeCommonConstant.LAST_NAME, lastName);
		document.addKeyword(EmployeeCommonConstant.EMAIL_ADDRESS, emailAddress);
		document.addKeyword(EmployeeCommonConstant.MOBILE_NUMBER, mobileNumber);
		document.addKeyword(EmployeeCommonConstant.CATEGORY, category);
		document.addNumber(Field.COMPANY_ID, companyId);
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		Summary summary = createSummary(document);
		summary.setMaxContentLength(200);
		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Employee employee = employeeLocalService.fetchEmployee(classPK);
		doReindex(employee);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindex(companyId);
	}

	@Override
	protected void doReindex(Employee employee) throws Exception {
		Document document = doGetDocument(employee);
		IndexWriterHelperUtil.updateDocument(employee.getCompanyId(), document);
	}

	protected void reindex(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = employeeLocalService
				.getIndexableActionableDynamicQuery();
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod((ActionableDynamicQuery.PerformActionMethod<Employee>) employee -> {
					try {
						Document document = getDocument(employee);
						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						if (log.isWarnEnabled()) {
							log.warn("Unable to index " + employee.getEmployeeId(), pe);
						}
					}
				});
		indexableActionableDynamicQuery.performActions();
	}
	private static final Log log = LogFactoryUtil.getLog(EmployeeIndexer.class);
}