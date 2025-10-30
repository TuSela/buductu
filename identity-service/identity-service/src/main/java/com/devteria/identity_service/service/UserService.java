package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.UserCreationRequest;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createRequest(UserCreationRequest request){
        User users = new User();

        users.setUsername(request.getUsername());
        users.setPassword(request.getPassword());
        users.setFirstName(request.getFirstName());
        users.setLastName(request.getLastName());
        users.setDob(request.getDob());

       return userRepository.save(users);
    }
}
