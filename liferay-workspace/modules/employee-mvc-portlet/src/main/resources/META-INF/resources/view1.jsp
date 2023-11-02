<%@ include file="init.jsp"%>

<%
String requestURL = themeDisplay.getPortalURL() + "/o/graphql";
%>


<script type="text/javascript">

 async function doAjax(){
	 return $.ajax({
			     url: "<%=requestURL%>",
				 type :'POST',
				 headers: {
					    'Content-Type': 'application/json',
				 },
				 data:JSON.stringify({ "query": "query{employeeList{items{employeeId firstName lastName emailAddress mobileNumber}}}" }),
				 success : function(employees){
					 console.log("employees",employees);
					 var employeeList = employees.data.employeeList.items;
					 document.getElementById("parentnode").appendChild(
							 <c:forEach items="${employeeList}" var="employee">
						    	<portlet:renderURL var="updateEmployeeRenderURL">
						            <portlet:param name="mvcPath" value="/addEmployee.jsp"/>
						            <portlet:param name="employeeId" value="${employee.employeeId}"/>
						            <portlet:param name="firstName" value="${employee.firstName}"/>
						            <portlet:param name="lastName" value="${employee.lastName}"/>
						            <portlet:param name="emailAddress" value="${employee.emailAddress}"/>
						            <portlet:param name="mobileNumber" value="${employee.mobileNumber}"/>
						        </portlet:renderURL>
						        <portlet:actionURL name="deleteEmployee" var="deleteEmployeeActionURL">
						            <portlet:param name="employeeId" value="${employee.employeeId}"/>
						        </portlet:actionURL>
							    <tr>
						            <td>${employee.employeeId}</td>
						            <td>${employee.firstName}</td>
						            <td>${employee.lastName}</td>
						            <td>${employee.emailAddress}</td>
						            <td>${employee.mobileNumber}</td>
						             <td class="text-center" style="width: 50px">
						                <a href="<%=updateEmployeeRenderURL%>" class="btn  btn-primary btn-default btn-sm px-2 py-1" >
						                <i class ="glyphicon glyphicon-edit">Edit</i>
						                </a>
						            </td>
						            <td class="text-center" style="width:50px">
						                <a href="<%=deleteEmployeeActionURL%>"
						                    class="btn  btn-primary btn-default btn-sm px-2 py-1">
						                    <i class ="glyphicon glyphicon-remove">Delete</i>
						                </a>
						            </td>
						         </tr>
						    </c:forEach> 		 
					 
					 
					 )
			     }
	});
}
 

 async function getList(){
		    	var employeelist = await doAjax();
		   	    console.log("employeeList" , employeelist.data.employeeList.items);
		   	    var employeeList = employeelist.data.employeeList.items;
		   	
			   	return employeeList;
		    
	   }
 
$(document).ready(function() {
	  $("#button").click(async function(e) {
		console.log("hello");
		var employeeList = await getList();
		let employeeSize = employeeList.length;
		console.log(employeeList[0].employeeId);
	    console.log(employeeSize);
	    });

 });
	   


</script>



<h1>Employee Details</h1>
<button id="button"  name="button">button</button>
<%-- <portlet:renderURL var="addEmployeeRenderURL">
    <portlet:param name="mvcPath" value="/addEmployee.jsp"/>
</portlet:renderURL>

<div class="mb-5">
    <a href="<%=addEmployeeRenderURL %>" class="btn  btn-primary btn-default">
       Add Employee
    </a>
</div> --%>
<table class="table table-striped">
    <tr >
        <th>Employee Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email Address</th>
        <th>Mobile Number</th>
        <th colspan="2" style="width: 100px">Action</th>
    </tr>
<div id="parentnode">  
<%-- <c:forEach items="${test}" var="employee">
    	<portlet:renderURL var="updateEmployeeRenderURL">
            <portlet:param name="mvcPath" value="/addEmployee.jsp"/>
            <portlet:param name="employeeId" value="${employee.employeeId}"/>
            <portlet:param name="firstName" value="${employee.firstName}"/>
            <portlet:param name="lastName" value="${employee.lastName}"/>
            <portlet:param name="emailAddress" value="${employee.emailAddress}"/>
            <portlet:param name="mobileNumber" value="${employee.mobileNumber}"/>
        </portlet:renderURL>
        <portlet:actionURL name="deleteEmployee" var="deleteEmployeeActionURL">
            <portlet:param name="employeeId" value="${employee.employeeId}"/>
        </portlet:actionURL>
	    <tr>
            <td>${employee.employeeId}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.emailAddress}</td>
            <td>${employee.mobileNumber}</td>
             <td class="text-center" style="width: 50px">
                <a href="<%=updateEmployeeRenderURL%>" class="btn  btn-primary btn-default btn-sm px-2 py-1" >
                <i class ="glyphicon glyphicon-edit">Edit</i>
                </a>
            </td>
            <td class="text-center" style="width:50px">
                <a href="<%=deleteEmployeeActionURL%>"
                    class="btn  btn-primary btn-default btn-sm px-2 py-1">
                    <i class ="glyphicon glyphicon-remove">Delete</i>
                </a>
            </td>
         </tr>
    </c:forEach> --%>
 </div>
  </table>



