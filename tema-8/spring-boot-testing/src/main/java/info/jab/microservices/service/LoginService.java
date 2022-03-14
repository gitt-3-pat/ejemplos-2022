package info.jab.microservices.service;

public interface LoginService {

    boolean authenticate(String user, String password);
}
