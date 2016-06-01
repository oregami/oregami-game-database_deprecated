oregami
============================
open source game database

This is the REST server written in Java.
It uses Spring Boot, JPA and Hibernate.

How to start the Server:
Run the class "org.oregami.OregamiApplication".
Open your web browser at localhost:8080


You must have MySQL running and these lines should be executet:

```sql
CREATE DATABASE `oregami2` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE USER 'oregami2'@'localhost' IDENTIFIED BY 'oregami2';
GRANT ALL PRIVILEGES ON `oregami2`.* TO 'oregami2'@'localhost' WITH GRANT OPTION;
```
