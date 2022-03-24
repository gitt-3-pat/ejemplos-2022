package info.jab.microservices.model;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.time.Duration;

@Data
public class Rental {

    private Integer duration;
    private Integer price;

}
