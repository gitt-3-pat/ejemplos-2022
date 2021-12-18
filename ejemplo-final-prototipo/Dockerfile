FROM openjdk:11.0.12-jre-slim

RUN [ "groupadd", "--system", "spring-boot" ]
RUN [ "useradd", "--system", "--gid", "spring-boot", "spring-boot-user" ]

USER spring-boot-user

# Configuration
ENV JAVA_TOOL_OPTIONS="-XX:+PrintFlagsFinal \
                        -XX:+UseContainerSupport \
                        -XX:MaxRAMPercentage=60.0 \
                        -XX:+UseG1GC \
                        -XX:+HeapDumpOnOutOfMemoryError \
                        -Dhost.name=localhost \
                        -Dcom.sun.management.jmxremote \
                        -Dcom.sun.management.jmxremote.port=9004 \
                        -Dcom.sun.management.jmxremote.rmi.port=9004 \
                        -Dcom.sun.management.jmxremote.local.only=false \
                        -Dcom.sun.management.jmxremote.authenticate=false \
                        -Dcom.sun.management.jmxremote.ssl=false \
                        -Dfile.encoding=UTF8 \
                        -Djava.rmi.server.hostname=localhost"

# JMX Port and Application port
EXPOSE 9004 8080

COPY [ "target/demo.jar", "/app/" ]

ENTRYPOINT [ "java", "-jar" ]
CMD [ "/app/demo.jar" ]