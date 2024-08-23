-- Drop user first if they exist
DROP USER IF EXISTS creditcarduser@'%';

-- Now create user with prop privileges
CREATE USER 'creditcarduser'@'%' IDENTIFIED BY 'user1234';

GRANT ALL PRIVILEGES ON *.* TO 'creditcarduser'@'%';