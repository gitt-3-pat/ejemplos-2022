package info.jab.microservices.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Movie {

    @Id
    private Long id;
    private String title;
    private String description;
    private Rental rental;
}
