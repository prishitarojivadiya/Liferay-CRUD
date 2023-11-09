<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="init.jsp"%>
<h1>add Employee</h1>
<portlet:defineObjects />

<portlet:actionURL name="addEmployee" var="addEmployeeActionURL" />
<aui:form action="<%=addEmployeeActionURL%>" name="employeeDetails"
	method="POST">
	<div class="d-none">
		<aui:input id="employeeId" name="employeeId" />
	</div>
	<aui:input id="firstName" name="firstName" />
	<aui:input id="lastName" name="lastName" />
	<aui:input id="emailAddress" name="emailAddress" />
	<aui:input id="mobileNumber" name="mobileNumber" />
	<%
	String category = renderRequest.getParameter(EmployeeCommonConstant.CATEGORY);
	List<String> categoryList = new ArrayList<>();

	if (Validator.isNotNull(category)) {
		String[] categories = category.split(StringPool.COMMA);

		for (String s : categories) {
			categoryList.add(s);
		}
	%>
	<aui:select id="category" name="category" multiple="true">
		<option id="back-end" value="backend"
			<%=categoryList.contains("backend") ? "selected" : ""%>>back-end
			developer</option>
		<option id="front-end" value="frontend"
			<%=categoryList.contains("frontend") ? "selected" : ""%>>front-end
			developer</option>
		<option id="liferay" value="liferay"
			<%=categoryList.contains("liferay") ? "selected" : ""%>>Liferay
			developer</option>
		<option id="graphic" value="graphic"
			<%=categoryList.contains("graphic") ? "selected" : ""%>>graphic
			designer</option>
		<option id="BDE" value="BDE"
			<%=categoryList.contains("BDE") ? "selected" : ""%>>Bussiness
			Developement Executive</option>
		<option id="HR" value="HR" <%=categoryList.contains("HR") ? "selected" : ""%>>Human
			Resource Executive</option>
	</aui:select>
	<aui:button type="submit" value="Submit" />
	<%
	} else {
	%>
	<aui:select id="category" name="category" multiple="true">
		<option id="back-end" value="backend">back-end developer</option>
		<option id="front-end" value="frontend">front-end developer</option>
		<option id="liferay" value="liferay">Liferay developer</option>
		<option id="graphic" value="graphic">graphic designer</option>
		<option id="BDE" value="BDE">Bussiness Developement Executive</option>
		<option id="HR" value="HR">Human Resource Executive</option>
	</aui:select>
	<aui:button type="submit" value="Submit" />
	<%
	}
	%>
</aui:form>
