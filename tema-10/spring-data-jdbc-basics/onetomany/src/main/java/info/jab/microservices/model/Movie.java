package info.jab.microservices.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
public class Movie {

    @Id
    private Long id;
    private String title;
    private String description;
    private Set<Rental> rental;
}
