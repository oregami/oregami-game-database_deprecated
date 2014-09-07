oregami-server
============================
open source game database

This is the REST server written in Java.
It uses dropwizard (https://dropwizard.github.io/dropwizard/), JPA and Hibernate.

How to start the Server:
Run the class "org.oregami.dropwizard.OregamiApplication" and pass the parameters "server oregami.yml" as arguments.
Open your web browser at localhost:8080/games

More information about the growing REST API can be found [here](http://wiki.oregami.org/display/OR/Oregami+REST+API).

If you have installed [docker](http://docker.io/), you can start the oregami server with these commands (using the Dockerfile included in docker/):

        docker build -t oregami-server .
        docker run -p 8080:8080 -d -t oregami-server
