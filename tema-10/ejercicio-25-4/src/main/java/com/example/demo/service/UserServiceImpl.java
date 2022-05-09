package com.example.demo.service;

import com.example.demo.model.UserDocument;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDocument> getUserDocuments() {
        return userRepository.myQuery();
    }
}
