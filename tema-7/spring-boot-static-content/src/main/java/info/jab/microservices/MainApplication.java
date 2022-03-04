package info.jab.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    public static Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        logger.info("Estoy usando Logging");
        SpringApplication.run(MainApplication.class, args);
    }

}




