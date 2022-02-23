package edu.icai.gittpat.spring.jdbc.h2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SpringFoxConfig {

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.OAS_30)  
          .select()          
          .apis(RequestHandlerSelectors.any())   
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());                                           
    }
	
	private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Example API REST", "Example gitt-pat API-REST", "API TOS", "Terms of service", new Contact("Universidad Comillas ICAI", "", ""), "License of API", "API license URL", Collections.emptyList());
        return apiInfo;
    }
}
