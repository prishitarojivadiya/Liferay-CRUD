create table Employee_Employee (
	uuid_ VARCHAR(75) null,
	employeeId LONG not null primary key,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	emailAddress VARCHAR(75) null,
	mobileNumber VARCHAR(75) null,
	createdby LONG,
	modifiedby LONG,
	createDate DATE null,
	modifiedDate DATE null
);