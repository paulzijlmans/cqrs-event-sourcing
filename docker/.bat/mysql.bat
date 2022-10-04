@ECHO OFF
ECHO mysql
docker run -it -d --name mysql-container ^
-p 3306:3306 --network springbankNet ^
-e MYSQL_ROOT_PASSWORD=springbankRootPsw ^
--restart always ^
-v mysql_data_container:/var/lib/mysql  ^
mysql:latest

::Client Tools
docker run -it -d --name adminer ^
-p 8080:8080 --network springbankNet ^
 -e ADMINER_DEFAULT_SERVER=mysql-container ^
--restart always adminer:latest