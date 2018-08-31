-- User test data
INSERT INTO USERS (firstname, lastname, phoneNumber, email, username, password, isActive) VALUES ('Dorel', 'Pop', '0741414179', 'dorelp@msggroup.com','dorelp', 'œÕ˜² «y<†ý0;', 1);
INSERT INTO USERS (firstname, lastname, phoneNumber, email, username, password, isActive) VALUES ('Viorica', 'Dan', '0741280913', 'viorid@msggroup.com','viorid', 'œÕ˜² «y<†ý0;', 1 );
INSERT INTO USERS (firstname, lastname, phoneNumber, email, username, password, isActive) VALUES ('Florin', 'Baciu', '0755295126', 'baciuf@msggroup.com','florib', 'œÕ˜² «y<†ý0;', 1);
INSERT INTO USERS (firstname, lastname, phoneNumber, email, username, password, isActive) VALUES ('Iacob', 'Giurgi', '0749012854', 'iacobg@msggroup.com','iacobi', 'œÕ˜² «y<†ý0;', 1);

INSERT INTO ROLES (type) VALUES ('ADMINISTRATOR');
INSERT INTO ROLES (type) VALUES ('PROJECT_MANAGER');
INSERT INTO ROLES (type) VALUES ('TEST_MANAGER');
INSERT INTO ROLES (type) VALUES ('DEVELOPER');
INSERT INTO ROLES (type) VALUES ('TESTER');


INSERT INTO PERMISSIONS (type,description) values ('PERMISSION_MANAGEMENT','Dreptul de a modifica statusul permisiunilor asignate rolurilor');
INSERT INTO PERMISSIONS (type,description) values ('USER_MANAGEMENT','Dreptul de a modifica informatiile despre un user');
INSERT INTO PERMISSIONS (type,description) values ('BUG_MANAGEMENT','Dreptul de a modifica statusul unui bug');
INSERT INTO PERMISSIONS (type,description) values ('BUG_CLOSE','Dreptul de a pune un defect in stadiul de INCHIS');
INSERT INTO PERMISSIONS (type,description) values ('BUG_EXPORT_PDF','Dreptul de a exporta un bug in format pdf');
INSERT INTO PERMISSIONS (type,description) values ('ADDRESSED_USER','Dreptul de a ii fi adresate notificari');
INSERT INTO PERMISSIONS (type,description) values ('REPORT_MANAGEMENT','Dreptul de a vedea rapoarte');



INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('1','1');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('1','6');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('1','2');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('1','3');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('2','1');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('2','3');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('2','6');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('3','6');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('3','3');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('3','4');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('3','5');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('4','6');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('5','6');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('5','3');
INSERT INTO roles_permissions (Role_IDROLE,permissions_IDPERMISSION) values ('5','5');

insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description for the first bug', '2.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('other description', '3.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('this is the third bug', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('other description for another bug', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 3, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'CLOSED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'FIXED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'FIXED', '2018-03-01 00:00:00' , 'low bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 2, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.4', 'MEDIUM', 'REJECTED', '2018-09-01 00:00:00', 'medium bug', '1.1', 1, 3);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'LOW', 'FIXED', '2018-03-01 00:00:00' , 'low bug', '1.1', 3, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.2', 'CRITICAL', 'OPEN', '2018-01-01 00:00:00', 'critical bug', '1.1', 1, 2);
insert into bugs (description, fixedVersion, severity, status, targetDate, title, version, assignedTo,createdByUser) values ('description', '1.3', 'LOW', 'FIXED', '2018-11-02 00:00:00', 'high bug', '1.1', 3, 3);
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
insert into users_roles(User_IDUSER,roles_IDROLE) values (1,3);
insert into users_roles(User_IDUSER,roles_IDROLE) values (3,1);
insert into users_roles(User_IDUSER,roles_IDROLE) values (3,2);
insert into users_roles(User_IDUSER,roles_IDROLE) values (3,3);
insert into users_roles(User_IDUSER,roles_IDROLE) values (3,4);
insert into users_roles(User_IDUSER,roles_IDROLE) values (3,5);







-- INSERT INTO attachments(idBug,file,extension,fileName) values (1,LOAD_FILE('C:/Users/giurgi/Documents/JBugs/jbugs-persistence/src/main/resources/attachments/blabla.txt'),'TXT','blabla');


INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');
INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');
INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');
INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');
INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');
INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');
INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');
INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');
INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');
INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');
INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');
INSERT INTO history(idBug,modifiedDate,afterStatus,beforeStatus,modifiedBy) values ('1','2018-08-30 00:00:00','OPEN','CLOSED','1');

-- other tables TODO