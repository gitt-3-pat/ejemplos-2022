# My first Join

![](./sql_join.jpg)

```
mvn clean test -pl tema-10/my-first-join
mvn spring-boot:run -pl tema-10/my-first-join

http://localhost:8080/h2/
```

## Domains

**Customers:**

```
http://localhost:8080/api/v1/customers
http://localhost:8080/api/v1/customers/with-orders
```

**Orders:**

```
http://localhost:8080/api/v1/orders
http://localhost:8080/api/v1/orders/with-customer
http://localhost:8080/api/v1/orders/all
```
