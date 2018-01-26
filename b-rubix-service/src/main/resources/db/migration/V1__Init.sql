CREATE SCHEMA IF NOT EXISTS brubix;

USE brubix;

INSERT INTO `country` (`id`, `code`, `currency`, `description`)
VALUES
  (1, 'IND', 'INR', 'India'),
  (2, 'USA', 'USA', 'United States of Ame');

INSERT INTO `state` (`id`, `code`, `description`, `country_id`)
VALUES
  (1, 'KAR', 'Karnataka', 1),
  (2, 'AP', 'Andra Pradesh', 1),
  (3, 'TEL', 'Telangana', 1),
  (4, 'TN', 'TamilNadu', 1),
  (5, 'TXS', 'Texas', 2),
  (6, 'WDC', 'Wasington DC', 2)
;

INSERT INTO `city` (`id`, `code`, `description`, `state_id`)
VALUES
  (1, 'MYS', 'Mysore', 1),
  (2, 'BNG', 'Bangalore', 1),
  (3, 'HUB', 'Hubli', 1);

INSERT INTO `subject` (`id`, `description`, `name`)
VALUES
  (1, 'Mathematics', 'Maths'),
  (2, 'Physics', 'Physics'),
  (3, 'Chemistry', 'Chemistry'),
  (4, 'Biology', 'Biology')
;


INSERT INTO `institution_type` (`id`, `description`, `type`)
VALUES
  (1, 'Play School', 'Play'),
  (2, 'Primary School', 'Primary'),
  (3, 'Middle School', 'Middle'),
  (4, 'Secondary School', 'Secondary'),
  (5, 'Senior Secondary School', 'Senior Secondary'),
  (6, 'College', 'College'),
  (7, 'University', 'University'),
  (8, 'Satellite Institutes', 'Satellite Institutes')
;

INSERT INTO `language` (`id`, `description`, `language`)
VALUES
  (1, 'English', 'English'),
  (2, 'Hindi', 'Hindi'),
  (3, 'Tamil', 'Tamil'),
  (4, 'Telugu', 'Telugu')
;

INSERT INTO `institution_affiliation` (`id`, `affiliation`, `description`)
VALUES
  (1, 'CBSE', 'Central Board of Secondary Education'),
  (2, 'ICSE', 'International Counsil of Secondary Education');



