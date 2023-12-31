<%@ include file="init.jsp"%>
<h1>Employee Details</h1>

<portlet:renderURL var="addEmployeeRenderURL">
	<portlet:param name="mvcPath" value="/addEmployee.jsp" />
</portlet:renderURL>
<div class="mb-5">
	<a href="<%=addEmployeeRenderURL%>"
		class="btn  btn-primary btn-default"> <i
		class="glyphicon glyphicon-plus"></i> Add Employee
	</a>
	<portlet:defineObjects />

	<portlet:actionURL name="searchEmployee" var="searchEmployeeActionUrl" />


	<aui:form action="<%=searchEmployeeActionUrl%>" method="post">
		<aui:input id="emailAddress" name="Search" />
		<aui:select id="filter" name="filter" multiple="true">
			<option id="back-end" value="backend">back-end developer</option>
			<option id="front-end" value="frontend">front-end developer</option>
			<option id="liferay" value="liferay">Liferay developer</option>
			<option id="graphic" value="graphic">graphic designer</option>
			<option id="BDE" value="BDE">Bussiness Developement
				Executive</option>
			<option id="HR" value="HR">Human Resource Executive</option>
		</aui:select>
		<button type="submit" id="button" value="Submit">Submit</button>
	</aui:form>



</div>
<table class="table table-striped">
	<tr>
		<th>Employee Id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Email Address</th>
		<th>Mobile Number</th>
		<th>Employee Category</th>
		<th colspan="2" style="width: 100px">Action</th>
	</tr>
	<c:forEach items="${employeeList}" var="employee">
		<portlet:renderURL var="updateEmployeeRenderURL">
			<portlet:param name="mvcPath" value="/addEmployee.jsp" />
			<portlet:param name="employeeId" value="${employee.employeeId}" />
			<portlet:param name="firstName" value="${employee.firstName}" />
			<portlet:param name="lastName" value="${employee.lastName}" />
			<portlet:param name="emailAddress" value="${employee.emailAddress}" />
			<portlet:param name="mobileNumber" value="${employee.mobileNumber}" />
			<portlet:param name="category" value="${employee.category}" />
		</portlet:renderURL>
		<portlet:actionURL name="deleteEmployee" var="deleteEmployeeActionURL">
			<portlet:param name="employeeId" value="${employee.employeeId}" />
		</portlet:actionURL>
		<tr>
			<td>${employee.employeeId}</td>
			<td>${employee.firstName}</td>
			<td>${employee.lastName}</td>
			<td>${employee.emailAddress}</td>
			<td>${employee.mobileNumber}</td>
			<td>${employee.category}</td>
			<td class="text-center" style="width: 50px"><a
				href="<%=updateEmployeeRenderURL%>"
				class="btn  btn-primary btn-default btn-sm px-2 py-1"> <i
					class="glyphicon glyphicon-edit">Edit</i>
			</a></td>
			<td class="text-center" style="width: 50px"><a
				href="<%=deleteEmployeeActionURL%>"
				class="btn  btn-primary btn-default btn-sm px-2 py-1"> <i
					class="glyphicon glyphicon-remove">Delete</i>
			</a></td>
		</tr>
	</c:forEach>
</table>


