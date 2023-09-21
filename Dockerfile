FROM maven:3.3-jdk-8

#copying src from framework
COPY src /home/SeleniumDocker/src

#copying POM from framework
COPY pom.xml /home/SeleniumDocker

#copying testng from framework
COPY testng.xml /home/SeleniumDocker

#copying src from framework
RUN mvn -f /home/SeleniumDocker/pom.xml clean install