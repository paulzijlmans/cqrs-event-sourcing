@ECHO OFF
ECHO docker network
set dockerNetwork=springbankNet
(docker network inspect %dockerNetwork% > NUL) || ^
(docker network create --attachable -d bridge %dockerNetwork%)