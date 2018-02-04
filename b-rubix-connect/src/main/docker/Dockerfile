FROM openjdk:8u141-jdk-slim
EXPOSE 8080

COPY launch.sh /com.brubix.reference.service/launch.sh
RUN chmod 755 /com.brubix.reference.service/launch.sh
COPY @project.build.finalName@.jar /com.brubix.reference.service/app.jar
RUN chmod 755 /com.brubix.reference.service/app.jar

ENTRYPOINT ["/com.brubix.reference.service/launch.sh"]
