FROM	dockerfile/java:oracle-java8

RUN		apt-get update
RUN		apt-get install -y maven
RUN		git clone https://github.com/oregami/oregami-server.git
RUN		cd oregami-server && mvn clean install


# Define working directory.
WORKDIR /data/oregami-server
# Define default command.
#CMD ["bash"]


CMD ["java", "-jar", "target/oregami-dropwizard-0.0.1-SNAPSHOT.jar", "server", "oregami.yml"]