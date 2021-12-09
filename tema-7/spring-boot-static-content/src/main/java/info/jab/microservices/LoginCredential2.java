package info.jab.microservices;

import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class LoginCredential2 {

    @Pattern(message="max 5 words please" , regexp="^[a-zA-Z-.0-9]{1,5}$")
    private String user;

    @Pattern(message="max 5 words please" , regexp="^[a-zA-Z-.0-9]{1,5}$")
    private String password;
}