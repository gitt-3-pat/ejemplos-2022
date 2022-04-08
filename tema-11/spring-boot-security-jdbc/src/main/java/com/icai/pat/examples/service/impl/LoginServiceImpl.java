package com.icai.pat.examples.service.impl;

import com.icai.pat.examples.model.User;
import com.icai.pat.examples.repository.UserRepository;
import com.icai.pat.examples.service.APP_ROLES;
import com.icai.pat.examples.service.LoginService;
import com.icai.pat.examples.service.LoginServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class LoginServiceImpl implements LoginService {

    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginServiceResult authenticate(String user, String password) {

        User user1 = userRepository.findByEmail(user);

        //logger.info("User : {}", user1.toString());

        if(password.equals(user1.getPassword())) {
            String value = user1.getEmail() + ":" + user1.getPassword();
            String access_token = Base64.getEncoder().encodeToString(value.getBytes());
            return new LoginServiceResult(true, access_token);
        } else {
            return new LoginServiceResult(false);
        }
    }

    @Override
    public APP_ROLES getRole(String accessToken) {
        String access_token_raw = accessToken.replace("Bearer ", "");
        String access_token = new String(Base64.getDecoder().decode(access_token_raw));
        logger.info("Access token raw: " + access_token_raw);
        String[] parts = access_token.split(":");

        User user1 = userRepository.findByEmail(parts[0]);
        return APP_ROLES.get(user1.getRole());
    }
}
