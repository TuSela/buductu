package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.UserCreationRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }
    public User getUserById(String id)
    {
        return userRepository.findById(id).get();
    }
    public User userUpdate(String userID, UserUpdateRequest request)
    {
     User user = getUserById(userID);
     user.setFirstName(request.getFirstName());
     user.setLastName(request.getLastName());
     user.setDob(request.getDob());
     return userRepository.save(user);
    }
    public void  deleteUserById(String id)
    {
        userRepository.deleteById(id);
    }
}
