CREATE SCHEMA IF NOT EXISTS brubix;

USE brubix;


INSERT INTO `address`
(`id`,`first_line`,`pin_code`,`second_line`,`third_line`,`country_id`,`state_id`,`school_id`,`user_id`)
VALUES
    (1,'HSR 5th sector','560103','BDA complex',null,1,1,1,null),
    (1,'Texas city 2','765014',null,'Texas',2,3,1,null);


INSERT INTO `social` (`id`, `facebook`, `gplus`, `linkedin`, `twitter`)
VALUES
    (1,'http://facebook.com/sanjeev.blr','http://googleplus.com/sanjeev.blr','http://linkedin.com/sanjeev.blr','http://twitter.com/sanjeev.blr');


INSERT INTO `institution`
(`id`,`created_at`,`created_by`,`deleted_at`,`deleted_by`,`updated_at`,`updated_by`,`school_code`,`school_name`,`institution_type_id`,`logo_document_id`,`social_id`)
VALUES
    ('1','2017-10-22','1',null,null,null,null,'SCHL120171022','XYZ institution',null,null,1);

COMMIT;
