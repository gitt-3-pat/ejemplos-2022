package info.jab.microservices;

import java.util.List;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/citiesex")
    public List<String> getCitiesException() throws MyException {
        throw new MyException("Error");
    }

    @ExceptionHandler(MyException.class)
    public String handleError(MyException e) {
        return "Sorry";
    }

    @GetMapping("/citiesex2")
    public List<String> getCitiesException2() {
        throw new RuntimeException("Error");
    }

    @GetMapping("/citiesex3")
    public List<String> getCitiesException3() throws MyException2 {
        throw new MyException2("Error");
    }

}
