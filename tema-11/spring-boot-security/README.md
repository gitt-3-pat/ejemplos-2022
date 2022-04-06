# Spring Boot Security example

In this example, we secure the app with Basic authentication and Form based authentication

Authentication Provider for the application is an InMemoryAuthentication with delegating password encoder. From spring-security 5 we need to provide a PasswordEncoder @Bean

#How to use App

Run the application, it will start at http://localhost:8080/

```
username: admin
password: admin
```

### Basic Authentication

Create HTTP request to endpoint http://localhost:8080/api/whoami with Authorization Basic header.

```
curl -i http://localhost:8080/
curl -i --user admin:admin \
  http://localhost:8080/
```

```
curl -i http://localhost:8080/
HTTP/1.1 401
Set-Cookie: JSESSIONID=DCFB335CCE0F6F7EE013CE8FB90C0D2A; Path=/; HttpOnly
WWW-Authenticate: Basic realm="Realm"
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: Wed, 06 Apr 2022 17:23:26 GMT
```

# References

- https://curl.se/
- https://docs.spring.io/spring-security/reference/index.html
- https://en.wikipedia.org/wiki/Basic_access_authentication
