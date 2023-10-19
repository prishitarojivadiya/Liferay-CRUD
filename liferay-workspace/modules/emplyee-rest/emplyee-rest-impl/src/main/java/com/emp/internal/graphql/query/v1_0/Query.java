package com.emp.internal.graphql.query.v1_0;

import com.emp.dto.v1_0.EmployeeDetails;
import com.emp.resource.v1_0.EmployeeDetailsResource;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author ignek
 * @generated
 */
@Generated("")
public class Query {

	public static void setEmployeeDetailsResourceComponentServiceObjects(
		ComponentServiceObjects<EmployeeDetailsResource>
			employeeDetailsResourceComponentServiceObjects) {

		_employeeDetailsResourceComponentServiceObjects =
			employeeDetailsResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {employeeList{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public EmployeeDetailsPage employeeList() throws Exception {
		return _applyComponentServiceObjects(
			_employeeDetailsResourceComponentServiceObjects,
			this::_populateResourceContext,
			employeeDetailsResource -> new EmployeeDetailsPage(
				employeeDetailsResource.getEmployeeList()));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {employeeById(employeeId: ___){employeeId, firstName, lastName, emailAddress, mobileNumber}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public EmployeeDetails employeeById(
			@GraphQLName("employeeId") Long employeeId)
		throws Exception {

		return _applyComponentServiceObjects(
			_employeeDetailsResourceComponentServiceObjects,
			this::_populateResourceContext,
			employeeDetailsResource -> employeeDetailsResource.getEmployeeById(
				employeeId));
	}

	@GraphQLName("EmployeeDetailsPage")
	public class EmployeeDetailsPage {

		public EmployeeDetailsPage(Page employeeDetailsPage) {
			actions = employeeDetailsPage.getActions();

			items = employeeDetailsPage.getItems();
			lastPage = employeeDetailsPage.getLastPage();
			page = employeeDetailsPage.getPage();
			pageSize = employeeDetailsPage.getPageSize();
			totalCount = employeeDetailsPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<EmployeeDetails> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(
			EmployeeDetailsResource employeeDetailsResource)
		throws Exception {

		employeeDetailsResource.setContextAcceptLanguage(_acceptLanguage);
		employeeDetailsResource.setContextCompany(_company);
		employeeDetailsResource.setContextHttpServletRequest(
			_httpServletRequest);
		employeeDetailsResource.setContextHttpServletResponse(
			_httpServletResponse);
		employeeDetailsResource.setContextUriInfo(_uriInfo);
		employeeDetailsResource.setContextUser(_user);
		employeeDetailsResource.setGroupLocalService(_groupLocalService);
		employeeDetailsResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<EmployeeDetailsResource>
		_employeeDetailsResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}