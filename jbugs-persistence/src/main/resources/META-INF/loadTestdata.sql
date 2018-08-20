-- User test data
INSERT INTO USERS (firstname, lastname, phoneNumber, email, username, password, isActive) VALUES ('Dorel1', '1', '07414141', 'dorel1@a.com','doreld', 'ëØ[SŽL›@@í+<nS·', 1);
INSERT INTO USERS (firstname, lastname, phoneNumber, email, username, password, isActive) VALUES ('Dore2', '1', '07414141', 'dorel2@a.com','doreld1', 'ëØ[SŽL›@@í+<nS·', 1 );
INSERT INTO USERS (firstname, lastname, phoneNumber, email, username, password, isActive) VALUES ('Dore3', '1', '07414141', 'dore3@a.com','ddorel', 'ëØ[SŽL›@@í+<nS·', 1);


INSERT INTO ROLES (type) VALUES ('Administrator');
INSERT INTO ROLES (type) VALUES ('Project manager');
INSERT INTO ROLES (type) VALUES ('Test manager');
INSERT INTO ROLES (type) VALUES ('Developer');
INSERT INTO ROLES (type) VALUES ('Tester');


INSERT INTO PERMISSIONS (type,description) values ('PERMISSION_MANAGEMENT','descriere');
INSERT INTO PERMISSIONS (type,description) values ('USER_MANAGEMENT','descriere');
INSERT INTO PERMISSIONS (type,description) values ('BUG_MANAGEMENT','descriere');
INSERT INTO PERMISSIONS (type,description) values ('BUG_CLOSE','descriere');
INSERT INTO PERMISSIONS (type,description) values ('BUG_EXPORT_PDF','descriere');
INSERT INTO PERMISSIONS (type,description) values ('ADDRESED_USER','descriere');
INSERT INTO PERMISSIONS (type,description) values ('REPORT_MANAGEMENT','descriere');



INSERT INTO roles_permissions (id_role,id_permission) values ('1','1');
INSERT INTO roles_permissions (id_role,id_permission) values ('1','2');
INSERT INTO roles_permissions (id_role,id_permission) values ('1','3');
INSERT INTO roles_permissions (id_role,id_permission) values ('2','2');
INSERT INTO roles_permissions (id_role,id_permission) values ('2','3');

insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'critical', 'new', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'high', 'new', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'medium', 'new', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'low', 'new', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);

insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 1,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 2,1);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 2,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 1,3);
-- other tables TODO