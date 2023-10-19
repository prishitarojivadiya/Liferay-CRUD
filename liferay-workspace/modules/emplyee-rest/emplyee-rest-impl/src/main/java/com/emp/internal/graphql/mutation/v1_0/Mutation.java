package com.emp.internal.graphql.mutation.v1_0;

import com.emp.dto.v1_0.EmployeeDetails;
import com.emp.dto.v1_0.Status;
import com.emp.resource.v1_0.EmployeeDetailsResource;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

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
public class Mutation {

	public static void setEmployeeDetailsResourceComponentServiceObjects(
		ComponentServiceObjects<EmployeeDetailsResource>
			employeeDetailsResourceComponentServiceObjects) {

		_employeeDetailsResourceComponentServiceObjects =
			employeeDetailsResourceComponentServiceObjects;
	}

	@GraphQLField
	public Status insertEmployee(
			@GraphQLName("employeeDetails") EmployeeDetails employeeDetails)
		throws Exception {

		return _applyComponentServiceObjects(
			_employeeDetailsResourceComponentServiceObjects,
			this::_populateResourceContext,
			employeeDetailsResource -> employeeDetailsResource.insertEmployee(
				employeeDetails));
	}

	@GraphQLField
	public Status deleteEmployee(@GraphQLName("employeeId") Long employeeId)
		throws Exception {

		return _applyComponentServiceObjects(
			_employeeDetailsResourceComponentServiceObjects,
			this::_populateResourceContext,
			employeeDetailsResource -> employeeDetailsResource.deleteEmployee(
				employeeId));
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
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

		employeeDetailsResource.setVulcanBatchEngineImportTaskResource(
			_vulcanBatchEngineImportTaskResource);
	}

	private static ComponentServiceObjects<EmployeeDetailsResource>
		_employeeDetailsResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;
	private VulcanBatchEngineImportTaskResource
		_vulcanBatchEngineImportTaskResource;

}