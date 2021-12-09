package info.jab.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServiceImpl implements ExternalService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${param1}")
    private String param;

    @Autowired
    private GlobalConfiguration globalConfiguration;

    @Override
    public String getData() {

        System.out.println(param);
        globalConfiguration.getHosts().stream()
                .map(GlobalConfiguration.Host::getAddress)
                .forEach(System.out::println);

        String fooResourceUrl = "http://localhost:8080/status";
        ResponseEntity<String> response = restTemplate
                .getForEntity(fooResourceUrl, String.class);
        return response.getBody();
    }

    //http://www.jsonschema2pojo.org/
    @Override
    public Example getData2() {
        String fooResourceUrl = "http://localhost:8080/status";
        ResponseEntity<Example> response = restTemplate.getForEntity(fooResourceUrl, Example.class);
        return response.getBody();
    }

}
