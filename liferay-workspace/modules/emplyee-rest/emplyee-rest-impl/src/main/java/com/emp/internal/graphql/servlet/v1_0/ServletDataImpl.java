package com.emp.internal.graphql.servlet.v1_0;

import com.emp.internal.graphql.mutation.v1_0.Mutation;
import com.emp.internal.graphql.query.v1_0.Query;
import com.emp.internal.resource.v1_0.EmployeeDetailsResourceImpl;
import com.emp.resource.v1_0.EmployeeDetailsResource;

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author ignek
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setEmployeeDetailsResourceComponentServiceObjects(
			_employeeDetailsResourceComponentServiceObjects);

		Query.setEmployeeDetailsResourceComponentServiceObjects(
			_employeeDetailsResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "EmplyeeRest";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/emplyee-rest-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#insertEmployee",
						new ObjectValuePair<>(
							EmployeeDetailsResourceImpl.class,
							"insertEmployee"));
					put(
						"mutation#deleteEmployee",
						new ObjectValuePair<>(
							EmployeeDetailsResourceImpl.class,
							"deleteEmployee"));

					put(
						"query#employeeList",
						new ObjectValuePair<>(
							EmployeeDetailsResourceImpl.class,
							"getEmployeeList"));
					put(
						"query#employeeById",
						new ObjectValuePair<>(
							EmployeeDetailsResourceImpl.class,
							"getEmployeeById"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<EmployeeDetailsResource>
		_employeeDetailsResourceComponentServiceObjects;

}