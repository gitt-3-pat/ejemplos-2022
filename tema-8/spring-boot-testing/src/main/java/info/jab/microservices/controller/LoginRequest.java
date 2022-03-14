package info.jab.microservices.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NonNull
    @NotEmpty
    @Pattern(message="max 5 words please" , regexp="^[a-zA-Z-.0-9]{1,5}$")
    private String user;

    @NotNull
    @NotEmpty
    @Pattern(message="max 5 words please" , regexp="^[a-zA-Z-.0-9]{1,5}$")
    private String password;
}