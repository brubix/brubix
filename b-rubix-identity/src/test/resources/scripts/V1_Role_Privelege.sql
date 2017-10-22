CREATE SCHEMA IF NOT EXISTS brubix;

USE BRUBIX;

INSERT INTO `role`
(`id`,`description`,`name`)
VALUES
    (1,'Can access teacher functionalities of a perticular school','TEACHER'),
    (2,'Can access student functionalities of a perticular school','STUDENT'),
    (3, 'Can access PARENT functionalities of a perticular school','PARENT'),
    (4, 'Can access GUARDIAN functionalities of a perticular school','GUARDIAN'),
    (5,'The administrator has access to all functionalities of perticular school','ADMINISTRATOR'),
    (6,'The super administrator has access to all functionalities for all schools','SUPER ADMINISTRATOR');

INSERT INTO `privilege`
(`id`,`description`,`name`)
VALUES
    (1,'All affiliations endpoints privelege','/affiliations'),
    (2,'All institutions endpoints privelege','/institutions'),
    (3,'All languages endpoints privelege','/languages'),
    (4,'All subjects endpoints privelege','/subjects'),
    (5,'All countries endpoints privelege','/countries'),
    (6,'All schools endpoints privelege','/schools'),
    (7,'All documents endpoints privelege','/documents');

INSERT INTO `roles_privileges`
(`role_id`,`privilege_id`)
VALUES
    (6,1),
    (6,2),
    (6,3),
    (6,4),
    (6,5),
    (6,6),
    (6,7),

    (5,1),
    (5,2),
    (5,3),
    (5,4),
    (5,5),
    (5,6),
    (5,7);


COMMIT;