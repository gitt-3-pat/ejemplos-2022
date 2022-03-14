package info.jab.microservices.service.impl;

import info.jab.microservices.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public boolean authenticate(String user, String password) {
        if ((user.equals("DEMO")) && (password.equals("DEMO"))) {
            return true;
        } else {
            return false;
        }
    }
}
