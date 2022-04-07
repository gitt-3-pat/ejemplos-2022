package com.icai.pat.examples.service.impl;

import com.icai.pat.examples.model.User;
import com.icai.pat.examples.repository.UserRepository;
import com.icai.pat.examples.service.LoginService;
import com.icai.pat.examples.service.LoginServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginServiceResult authenticate(String user, String password) {

        User user1 = userRepository.findByEmail(user);

        System.out.println(user1);

        if(password.equals(user1.getPassword())) {
            String value = user1.getEmail() + ":" + user1.getPassword();
            String access_token = Base64.getEncoder().encodeToString(value.getBytes());
            return new LoginServiceResult(true, access_token);
        } else {
            return new LoginServiceResult(false);
        }

        /*
        if ((user.equals("DEMO")) && (password.equals("DEMO"))) {
            return true;
        } else {
            return false;
        }
        */
    }
}
