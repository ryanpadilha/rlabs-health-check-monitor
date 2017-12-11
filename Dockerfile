#
# docker-container for Java 8 Application
# version 0.1
#

# base image CentOS 7
FROM centos:latest

MAINTAINER Ryan Padilha <ryan.padilha@wplex.com.br>

# install Oracle Java 8
RUN \
  yum -y upgrade && \
  yum -y install wget && \
  wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-linux-x64.rpm && \
  yum -y install jdk-8u151-linux-x64.rpm && \
  rm -rf jdk-8u151-linux-x64.rpm

# define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ENV APP_DIR /var/wplex/apps/vulcano-health-check-monitor
ENV APP_VERSION 0.0.1

# define working directory
RUN mkdir -p /var/wplex/apps
WORKDIR /var/wplex/apps
ADD target/vulcano-health-check-monitor-0.0.1-dev.tar.gz .

EXPOSE 8080
ENTRYPOINT java -Xms64m -Xmx256m -Djava.security.egd=file:/dev/./urandom -Dspring.config.location=$APP_DIR/config/application.properties -jar $APP_DIR/lib/vulcano-health-check-monitor-$APP_VERSION.jar

#ENTRYPOINT ["/usr/bin/java", "-Xms64m", "-Xmx256m", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.config.location=/var/wplex/apps/vulcano-health-check-monitor/config/application.properties", "-jar", "/var/wplex/apps/vulcano-health-check-monitor/lib/vulcano-health-check-monitor-0.0.1.jar"]
