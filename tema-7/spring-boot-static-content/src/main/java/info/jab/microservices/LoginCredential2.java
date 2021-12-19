package info.jab.microservices;

import javax.validation.constraints.Pattern;


record LoginCredential2(

    @Pattern(message="max 5 words please" , regexp="^[a-zA-Z-.0-9]{1,5}$")
    String user,

    @Pattern(message="max 5 words please" , regexp="^[a-zA-Z-.0-9]{1,5}$")
    String password) {}

