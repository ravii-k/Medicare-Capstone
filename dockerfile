From openjdk:8
Expose 8686
Add target/phase-3-project-0.0.1-SNAPSHOT.jar phase-3-project-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/phase-3-project-0.0.1-SNAPSHOT.jar"]