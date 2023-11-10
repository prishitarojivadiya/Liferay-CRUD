package com.emp.common.employee;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.emp.common.constants.EmployeeCommonConstant;
import com.emp.common.methods.CommonUtil;
import com.emp.common.model.EmployeeDTO;
import com.employee.model.Employee;
import com.employee.model.EmployeeTable;
import com.employee.service.EmployeeLocalService;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(immediate = true, service = EmployeeCommonService.class)
public class EmployeeCommonServiceImpl implements EmployeeCommonService {

	@Reference
	private EmployeeLocalService employeeLocalService;

	@Override
	public List<Employee> getEmployeeList(String searchString, long userId) {

		DSLQuery dslQuery;
		if (Validator.isNotNull(searchString)) {
			String search = StringPool.PERCENT + searchString + StringPool.PERCENT;
			long empId = GetterUtil.DEFAULT_LONG;
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
	public Hits getEmployeeListUsingElastic(long companyId, long userId, String searchString, String[] filter)
			throws Exception {

		SearchContext context = CommonUtil.getSearchContext(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		BooleanQuery mainQuery = new BooleanQueryImpl();

		mainQuery = CommonUtil.addParameterInQuery(Field.ENTRY_CLASS_NAME, Employee.class.getName(), mainQuery);
		mainQuery = CommonUtil.addParameterInQuery(Field.COMPANY_ID, String.valueOf(companyId), mainQuery);
		mainQuery = CommonUtil.addParameterInQuery(EmployeeCommonConstant.USER_ID, String.valueOf(userId), mainQuery);

		if (ArrayUtil.isNotEmpty(filter)) {
			BooleanQuery filterQuery = new BooleanQueryImpl();

			for (String category : filter) {
				filterQuery = CommonUtil.addParameterInQuery(EmployeeCommonConstant.CATEGORY, category, filterQuery,
						BooleanClauseOccur.SHOULD);
			}

			mainQuery = CommonUtil.addBooleanQuery(mainQuery, filterQuery);
		}

		if (Validator.isNotNull(searchString)) {
			BooleanQuery searchQuery = new BooleanQueryImpl();
			searchQuery = CommonUtil.addParameterInQuery(EmployeeCommonConstant.EMPLOYEE_ID, searchString, searchQuery,
					BooleanClauseOccur.SHOULD);
			searchQuery = CommonUtil.addParameterInQuery(EmployeeCommonConstant.FIRST_NAME, searchString, searchQuery,
					BooleanClauseOccur.SHOULD);
			searchQuery = CommonUtil.addParameterInQuery(EmployeeCommonConstant.LAST_NAME, searchString, searchQuery,
					BooleanClauseOccur.SHOULD);
			searchQuery = CommonUtil.addParameterInQuery(EmployeeCommonConstant.EMAIL_ADDRESS, searchString,
					searchQuery, BooleanClauseOccur.SHOULD);
			searchQuery = CommonUtil.addParameterInQuery(EmployeeCommonConstant.MOBILE_NUMBER, searchString,
					searchQuery, BooleanClauseOccur.SHOULD);
			searchQuery.add(new WildcardQueryImpl(EmployeeCommonConstant.FIRST_NAME,
					StringPool.STAR + searchString + StringPool.STAR), BooleanClauseOccur.SHOULD);
			searchQuery.add(new WildcardQueryImpl(EmployeeCommonConstant.LAST_NAME,
					StringPool.STAR + searchString + StringPool.STAR), BooleanClauseOccur.SHOULD);
			searchQuery.add(new WildcardQueryImpl(EmployeeCommonConstant.EMAIL_ADDRESS,
					StringPool.STAR + searchString + StringPool.STAR), BooleanClauseOccur.SHOULD);
			searchQuery.add(new WildcardQueryImpl(EmployeeCommonConstant.MOBILE_NUMBER, searchString + StringPool.STAR),
					BooleanClauseOccur.SHOULD);
			mainQuery = CommonUtil.addBooleanQuery(mainQuery, searchQuery);

		}

		return CommonUtil.getHits(context, mainQuery);
	}

	@Override
	public Employee updateEmployee(EmployeeDTO employee, long userId) {

		long employeeId = employee.getEmployeeId();
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String emailAddress = employee.getEmailAddress();
		String mobileNumber = employee.getMobileNumber();
		String category = employee.getCategory();
		return employeeLocalService.updateEmployee(userId, employeeId, firstName, lastName, emailAddress, mobileNumber,
				category);

	}

	@Override
	public void deleteEmployee(long employeeId) {
		try {
			employeeLocalService.deleteEmployee(employeeId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	private static final Log log = LogFactoryUtil.getLog(EmployeeCommonServiceImpl.class);

}
