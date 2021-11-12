FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD /target/timesheet-0.0.10-SNAPSHOT.war timesheet-0.0.10-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/timesheet-0.0.9-SNAPSHOT.war"]
