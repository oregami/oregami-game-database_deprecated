FROM java:openjdk-8-jdk-alpine

ENV MAVEN_VERSION 3.3.9

RUN mkdir -p /opt/maven
WORKDIR /opt/maven
RUN wget http://apache.crihan.fr/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
    -O maven.tgz
RUN tar xzf maven.tgz && rm -f maven.tgz

RUN apk update && apk upgrade && \
    apk add --no-cache bash git openssh

ENV M2_HOME /opt/maven/apache-maven-$MAVEN_VERSION
ENV M2_BIN ${M2_HOME}/bin
ENV PATH ${M2_BIN}:${PATH}


RUN	mkdir /data && cd /data && git clone -v https://github.com/oregami/oregami-game-database.git

WORKDIR /data/oregami-game-database

RUN	cd /data/oregami-game-database && mvn clean install
#cd

WORKDIR /data/oregami-game-database

# Define default command.
#CMD ["bash"]


CMD ["java", "-jar", "target/oregami-0.0.1-SNAPSHOT.jar"]