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
-- other tables TODO