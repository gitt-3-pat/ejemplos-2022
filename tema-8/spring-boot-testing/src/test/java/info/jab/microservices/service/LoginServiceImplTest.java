package info.jab.microservices.service;

import info.jab.microservices.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class LoginServiceImplTest {

    final LoginService loginService = new LoginServiceImpl();

    @Test
    public void given_loginServices_when_authenticate_with_good_credential_then_Ok() {

        //Given
        String user = "DEMO";
        String password = "DEMO";

        //When
        Boolean result = loginService.authenticate(user, password);

        //Then
        then(result).isTrue();
    }

    @Test
    public void given_loginServices_when_authenticate_with_bad_credential_then_Ko() {

        //Given
        String user = "DEMO";
        String password = "DEMOS";

        //When
        Boolean result = loginService.authenticate(user, password);

        //Then
        then(result).isFalse();
    }

}