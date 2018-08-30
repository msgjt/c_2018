-- User test data
INSERT INTO USERS (firstname, lastname, phoneNumber, email, username, password, isActive) VALUES ('Dorel1', '1', '07414141', 'dorel1@msggroup.com','doreld', 'ëØ[SŽL›@@í+<nS·', 1);
INSERT INTO USERS (firstname, lastname, phoneNumber, email, username, password, isActive) VALUES ('Dore2', '1', '07414141', 'dorel2@msggroup.com','doreld1', 'ëØ[SŽL›@@í+<nS·', 1 );
INSERT INTO USERS (firstname, lastname, phoneNumber, email, username, password, isActive) VALUES ('Dore3', '1', '07414141', 'dore3@msggroup.com','ddorel', 'ëØ[SŽL›@@í+<nS·', 0);
INSERT INTO USERS (firstname, lastname, phoneNumber, email, username, password, isActive) VALUES ('Dorel1', '4', '0741423141', 'dored3@msggroup.com','doreld2', 'ëØ[SŽL›@@í+<nS·', 1);

INSERT INTO ROLES (type) VALUES ('ADMINISTRATOR');
INSERT INTO ROLES (type) VALUES ('PROJECT_MANAGER');
INSERT INTO ROLES (type) VALUES ('TEST_MANAGER');
INSERT INTO ROLES (type) VALUES ('DEVELOPER');
INSERT INTO ROLES (type) VALUES ('TESTER');


INSERT INTO PERMISSIONS (type,description) values ('PERMISSION_MANAGEMENT','descriere');
INSERT INTO PERMISSIONS (type,description) values ('USER_MANAGEMENT','descriere');
INSERT INTO PERMISSIONS (type,description) values ('BUG_MANAGEMENT','descriere');
INSERT INTO PERMISSIONS (type,description) values ('BUG_CLOSE','descriere');
INSERT INTO PERMISSIONS (type,description) values ('BUG_EXPORT_PDF','descriere');
INSERT INTO PERMISSIONS (type,description) values ('ADDRESED_USER','descriere');
INSERT INTO PERMISSIONS (type,description) values ('REPORT_MANAGEMENT','descriere');



INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('1','1');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('1','2');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('1','3');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('2','2');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('2','3');

insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description descriptiondescr ipt ionde scrip tiondes cri ptiondescriptiondescriptiondescriptiondescriptiondescription', '2.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('other description', '3.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('other description', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);



insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 1,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 2,1);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 2,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 3,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 1,3);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 4,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 5,1);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 6,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 7,3);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 8,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 9,1);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 10,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 11,3);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 12,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 13,1);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 14,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 15,3);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 16,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 17,1);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some comment', 18,2);
insert into comments(date, text, idBug, idUser) values (CURRENT_TIMESTAMP , 'some other comment', 19,3);

insert into users_roles(User_IDUSER,roles_IDROLE) values (1,1);
insert into users_roles(User_IDUSER,roles_IDROLE) values (2,2);

INSERT INTO attachments(idBug,file,extension,fileName) values (1,LOAD_FILE('C:/Users/maneab/Desktop/11.txt'),'TXT','11.txt');
-- INSERT INTO attachments(idBug,file,extension,fileName) values (1,LOAD_FILE('C:/Users/giurgi/Documents/JBugs/jbugs-persistence/src/main/resources/attachments/blabla.txt'),'TXT','blabla');
-- INSERT INTO attachments(idBug,file,extension,fileName) values (2,LOAD_FILE('C:/Users/giurgi/Documents/JBugs/jbugs-persistence/src/main/resources/attachments/blabla.txt'),'TXT','blabla');
-- INSERT INTO attachments(idBug,file,extension,fileName) values (2,LOAD_FILE('C:/Users/giurgi/Documents/JBugs/jbugs-persistence/src/main/resources/attachments/blabla.txt'),'TXT','blabla');
-- INSERT INTO attachments(idBug,file,extension,fileName) values (2,LOAD_FILE('C:/Users/giurgi/Documents/JBugs/jbugs-persistence/src/main/resources/attachments/blabla.txt'),'TXT','blabla');
-- INSERT INTO attachments(idBug,file,extension,fileName) values (2,LOAD_FILE('C:/Users/giurgi/Documents/JBugs/jbugs-persistence/src/main/resources/attachments/blabla.txt'),'TXT','blabla');
-- INSERT INTO attachments(idBug,file,extension,fileName) values (1,LOAD_FILE('C:/Users/giurgi/Documents/JBugs/jbugs-persistence/src/main/resources/attachments/blabla.txt'),'TXT','blabla');
-- INSERT INTO attachments(idBug,file,extension,fileName) values (1,LOAD_FILE('C:/Users/giurgi/Documents/JBugs/jbugs-persistence/src/main/resources/attachments/blabla.txt'),'TXT','blabla');
-- INSERT INTO attachments(idBug,file,extension,fileName) values (1,LOAD_FILE('C:/Users/giurgi/Documents/JBugs/jbugs-persistence/src/main/resources/attachments/blabla.txt'),'TXT','blabla');
-- INSERT INTO attachments(idBug,file,extension,fileName) values (1,LOAD_FILE('C:/Users/giurgi/Documents/JBugs/jbugs-persistence/src/main/resources/attachments/blabla.txt'),'TXT','blabla');
-- INSERT INTO attachments(idBug,file,extension,fileName) values (1,LOAD_FILE('C:/Users/giurgi/Documents/JBugs/jbugs-persistence/src/main/resources/attachments/blabla.txt'),'TXT','blabla');
-- ToDo decomment attachments 
-- other tables TODO