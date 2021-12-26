# Ejemplos para la asignarura Git Patt 3

## Distribución

### Primera parte:

- Tema 1: La web en la industria
- [Tema 2: Protocolo HTTP](tema-2/README.md)
- [Tema 3: HTML](tema-3/README.md)
- [Tema 4: CSS](tema-4/README.md)
- [Tema 5: Javascript](tema-5/README.md)
- Tema 6: Estándares Jakarta EE
- [Tema 7: Spring Boot](tema-7/README.md)

### Segunda parte:

- [Tema 8: Testing](tema-8/README.md)
- Tema 9: Introducción a la persistencia de datos
- [Tema 10: Acceso a base de datos relacionales (JDBC)](tema-10/README.md)
- [Tema 11: Seguridad](tema-17/README.md)
- Tema 12: Marketing Digital

## ¿Como probar en el cloud?

[![](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/gitt-3-pat/ejemplos-2021-2022)

## ¿Como probar en local?

````
./mvnw clean verify
./mvnw clean spring-boot:run -pl tema-3
./mvnw dependency:tree -pl tema-3 -Dscope=compile
./mvnw dependency:tree -pl tema-3 -Dscope=test
./mvnw versions:display-dependency-updates -pl tema-3
./mvnw versions:display-plugin-updates -pl tema-3
./mvnw clean spring-boot:run -pl tema-4
./mvnw clean spring-boot:run -pl tema-5
./mvnw clean spring-boot:run -pl tema-6
./mvnw clean spring-boot:run -pl tema-7/spring-boot-core
./mvnw clean spring-boot:run -pl tema-7/spring-boot-static-content
./mvnw clean spring-boot:run -pl tema-7/spring-boot-actuator
````
