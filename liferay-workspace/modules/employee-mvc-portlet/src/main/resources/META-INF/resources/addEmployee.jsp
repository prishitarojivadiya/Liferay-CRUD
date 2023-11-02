<%@ include file="init.jsp"%>
<h1>add Employee</h1>
<portlet:defineObjects />
<portlet:actionURL name="addEmployee" var="addEmployeeActionURL" />

<aui:form action="<%= addEmployeeActionURL%>" name="employeeDetails"
	method="POST">
	<div class="d-none">
		<aui:input id="employeeId" name="employeeId" />
	</div>
	<aui:input id="firstName" name="firstName" />
	<aui:input id="lastName" name="lastName" />
	<aui:input id="emailAddress" name="emailAddress" />
	<aui:input id="mobileNumber" name="mobileNumber" />
	<aui:button type="submit" value="Submit" />
</aui:form>
