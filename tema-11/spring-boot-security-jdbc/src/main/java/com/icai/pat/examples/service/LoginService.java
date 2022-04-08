package com.icai.pat.examples.service;

public interface LoginService {

    LoginServiceResult authenticate(String user, String password);

    APP_ROLES getRole(String accessToken);
}
