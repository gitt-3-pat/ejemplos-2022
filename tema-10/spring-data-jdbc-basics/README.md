# Spring Data JDBC Basics

Spring Data JDBC offers a repository abstraction based on JDBC.

- The central interface in the Spring Data repository abstraction is Repository

```
/h2
jdbc:h2:mem:testdb
```

## Datatypes

```
Integer. A whole number without a fractional part. Eg. 1, 156, 2012412
Decimal. A number with a fractional part. Eg. 3.14, 3.141592654, 961.1241250
Boolean. A binary value. It can be either TRUE or FALSE.
Date. Speaks for itself. You can also choose the format. Eg. 2017-12-31
Time. You can decide the format of this, as well. Eg. 23:59:59
Timestamp. The date and the time together. Eg. 2017-12-31 23:59:59
Text. This is the most general data type. But it can be alphabetical letters only, or a mix of letters and numbers and any other characters. Eg. hello, R2D2, Tomi, 124.56.128.41
```

- https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html
- https://www.postgresql.org/docs/current/datatype.html

## References

- https://spring.io/projects/spring-data#learn
- https://docs.spring.io/spring-data/commons/docs/current/reference/html/
- https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/
- https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#common-application-properties-data
- https://spring.io/blog/2018/09/17/introducing-spring-data-jdbc
- https://spring.io/blog/2018/09/24/spring-data-jdbc-references-and-aggregates
