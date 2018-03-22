insert into Objects(parent_id, object_name) values(0, 'Customer');
insert into Objects(parent_id, object_name) values(0, 'Customer');
insert into Objects(parent_id, object_name) values(0, 'Employee');
insert into Objects(parent_id, object_name) values(0, 'Employee');
insert into Objects(parent_id, object_name) values(1, 'Project');
insert into Objects(parent_id, object_name) values(5, 'Sprint');
insert into Objects(parent_id, object_name) values(6, 'Task');
insert into Objects(parent_id, object_name) values(7, 'Task_Employee');
insert into Objects(parent_id, object_name) values(7, 'Task_Employee');

insert into Type(type_name) values('String');
insert into Type(type_name) values('Integer');
insert into Type(type_name) values('Data');

--Customer1
insert into Params(param_name, object_id, type_id) values('First_name', 1, 1);
insert into Params(param_name, object_id, type_id) values('Second_name', 1, 1);
insert into Params(param_name, object_id, type_id) values('Login', 1, 1);
insert into Params(param_name, object_id, type_id) values('Password', 1, 1);
insert into Params(param_name, object_id, type_id) values('Phone_number', 1, 1);
insert into Params(param_name, object_id, type_id) values('Status', 1, 1);

--Customer2
insert into Params(param_name, object_id, type_id) values('First_name', 2, 1);
insert into Params(param_name, object_id, type_id) values('Second_name', 2, 1);
insert into Params(param_name, object_id, type_id) values('Login', 2, 1);
insert into Params(param_name, object_id, type_id) values('Password', 2, 1);
insert into Params(param_name, object_id, type_id) values('Phone_number', 2, 1);
insert into Params(param_name, object_id, type_id) values('Status', 2, 1);

--Employee1
insert into Params(param_name, object_id, type_id) values('First_name', 3, 1);
insert into Params(param_name, object_id, type_id) values('Second_name', 3, 1);
insert into Params(param_name, object_id, type_id) values('Login', 3, 1);
insert into Params(param_name, object_id, type_id) values('Password', 3, 1);
insert into Params(param_name, object_id, type_id) values('Phone_number', 3, 1);
insert into Params(param_name, object_id, type_id) values('Status', 3, 1);
insert into Params(param_name, object_id, type_id) values('Position', 3, 1);

--Employee2
insert into Params(param_name, object_id, type_id) values('First_name', 4, 1);
insert into Params(param_name, object_id, type_id) values('Second_name', 4, 1);
insert into Params(param_name, object_id, type_id) values('Login', 4, 1);
insert into Params(param_name, object_id, type_id) values('Password', 4, 1);
insert into Params(param_name, object_id, type_id) values('Phone_number', 4, 1);
insert into Params(param_name, object_id, type_id) values('Status', 4, 1);
insert into Params(param_name, object_id, type_id) values('Position', 4, 1);

--Project1
insert into Params(param_name, object_id, type_id) values('Title', 5, 1);
insert into Params(param_name, object_id, type_id) values('Project_managerId', 5, 2);
insert into Params(param_name, object_id, type_id) values('Project_number', 5, 2);
insert into Params(param_name, object_id, type_id) values('First_data', 5, 3);
insert into Params(param_name, object_id, type_id) values('Second_data', 5, 3);

--Sprint1
insert into Params(param_name, object_id, type_id) values('Title', 6, 1);
insert into Params(param_name, object_id, type_id) values('ProjectId', 6, 1);
insert into Params(param_name, object_id, type_id) values('Status', 6, 1);

--Task1
insert into Params(param_name, object_id, type_id) values('Title', 7, 1);
insert into Params(param_name, object_id, type_id) values('SprintId', 7, 1);
insert into Params(param_name, object_id, type_id) values('First_data', 7, 3);
insert into Params(param_name, object_id, type_id) values('Second_data', 7, 3);
insert into Params(param_name, object_id, type_id) values('Status', 7, 1);

--TaskEmployee1
insert into Params(param_name, object_id, type_id) values('Task_Id', 8, 2);
insert into Params(param_name, object_id, type_id) values('Employee_Id', 8, 2);

--TaskEmployee1
insert into Params(param_name, object_id, type_id) values('Task_Id', 9, 2);
insert into Params(param_name, object_id, type_id) values('Employee_Id', 9, 2);

--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------

--Customer1(data)
insert into Param_data(param_data_content, param_id) values('Sergii',1);
insert into Param_data(param_data_content, param_id) values('Pritula',2);
insert into Param_data(param_data_content, param_id) values('pritula@gmail.com',3);
insert into Param_data(param_data_content, param_id) values('Qwerty12345678',4);
insert into Param_data(param_data_content, param_id) values('0993334455',5);
insert into Param_data(param_data_content, param_id) values('user',6);

--Customer2(data)
insert into Param_data(param_data_content, param_id) values('Nadiia',7);
insert into Param_data(param_data_content, param_id) values('Tiutiunyk',8);
insert into Param_data(param_data_content, param_id) values('admin@gmail.com',9);
insert into Param_data(param_data_content, param_id) values('Admin1234567',10);
insert into Param_data(param_data_content, param_id) values('0993334457',11);
insert into Param_data(param_data_content, param_id) values('admin',12);

--Employee1(data)
insert into Param_data(param_data_content, param_id) values('Ivan',13);
insert into Param_data(param_data_content, param_id) values('Ivanov',14);
insert into Param_data(param_data_content, param_id) values('ivanov@gmail.com',15);
insert into Param_data(param_data_content, param_id) values('Ivanov1234567',16);
insert into Param_data(param_data_content, param_id) values('0993334450',17);
insert into Param_data(param_data_content, param_id) values('employee',18);
insert into Param_data(param_data_content, param_id) values('Developer',19);

--Employee2(data)
insert into Param_data(param_data_content, param_id) values('Ben',20);
insert into Param_data(param_data_content, param_id) values('Aflek',21);
insert into Param_data(param_data_content, param_id) values('aflek@gmail.com',22);
insert into Param_data(param_data_content, param_id) values('Ben1234567',23);
insert into Param_data(param_data_content, param_id) values('0993334490',24);
insert into Param_data(param_data_content, param_id) values('employee',25);
insert into Param_data(param_data_content, param_id) values('Developer',26);

--Project1(data)
insert into Param_data(param_data_content, param_id) values('Project1',27);
insert into Param_data(param_data_content, param_id) values(3,28);
insert into Param_data(param_data_content, param_id) values(12345,29);
insert into Param_data(param_data_content, param_id) values('01.01.2018',30);
insert into Param_data(param_data_content, param_id) values('01.01.2019',31);

--Sprint1(data)
insert into Param_data(param_data_content, param_id) values('Sprint1',32);
insert into Param_data(param_data_content, param_id) values(5,33);
insert into Param_data(param_data_content, param_id) values('in progress',34);

--Task1(data)
insert into Param_data(param_data_content, param_id) values('Task1',35);
insert into Param_data(param_data_content, param_id) values(6,36);
insert into Param_data(param_data_content, param_id) values('01.01.2018',37);
insert into Param_data(param_data_content, param_id) values('01.01.2019',38);
insert into Param_data(param_data_content, param_id) values('in progress',39);

--Task_Employee1(data)
insert into Param_data(param_data_content, param_id) values(7,40);
insert into Param_data(param_data_content, param_id) values(4,41);

--Task_Employee2(data)
insert into Param_data(param_data_content, param_id) values(7,42);
insert into Param_data(param_data_content, param_id) values(3,43);
