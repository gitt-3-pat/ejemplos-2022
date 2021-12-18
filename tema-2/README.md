# Tema 2: Protocolo HTTP

## Herramientas

- Curl
- Httpie
- Postman
- Navegadores

### Curl basics

```bash
curl -o myfile.css https://cdn.keycdn.com/css/animate.min.css
curl -O https://cdn.keycdn.com/css/animate.min.css

curl -H "X-Header: value" https://www.keycdn.com

curl -I https://www.youtube.com/ -v
curl -I --http2 https://www.tmall.com/

curl -I --request GET https://www.keycdn.com/
curl -I --request POST https://www.keycdn.com/
curl -I --request DELETE https://www.keycdn.com/
curl -I --request PUT https://www.keycdn.com/

curl -X POST https://yourwebsite.com/login/ \
    -d 'username=yourusername&password=yourpassword'

curl --data name=curl http://url1.example.com http://url2.example.com

```

## Servicios

### SOAP

- http://www.chemspider.com/MassSpecAPI.asmx
- http://www.dneonline.com/calculator.asmx

```
curl --request GET http://www.chemspider.com/MassSpecAPI.asmx/GetDatabases
curl -I --request GET http://www.chemspider.com/MassSpecAPI.asmx/GetDatabases

```

### JSON

- https://api.run.pivotal.io/v2/info

```
curl --request GET https://api.run.pivotal.io/v2/info
curl -I --request GET https://api.run.pivotal.io/v2/info
```

### http/2

- https://www.tmall.com/

```
curl -I --request GET https://www.tmall.com/
```

### http/3

- https://www.google.com/
- https://www.youtube.com/

```
curl https://www.google.com/
curl -I  https://www.google.com/

curl https://www.youtube.com/
curl -I https://www.youtube.com/
```

## Referencias

- https://curl.haxx.se/
- https://curl.haxx.se/docs/manpage.html
- https://curl.haxx.se/docs/httpscripting.html
- https://ec.haxx.se/http/http-http2
- https://http2-explained.haxx.se/en/part11
- https://httpie.org/
- https://www.postman.com/
