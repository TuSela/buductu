package com.devteria.identity_service.Controller;

import com.devteria.identity_service.dto.request.UserCreationRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public User createUser(@RequestBody UserCreationRequest request)
    {
        return userService.createRequest(request);
    }
    @GetMapping()
    public List<User> getUsers()
    {
    return userService.getUsers();
    }
    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId)
    {
        return  userService.getUserById(userId);
    }
    @PutMapping("/{userId}")
    public User upDateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.userUpdate(userId, request);
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId)
    {
    userService.deleteUserById(userId);
    return "success";
    }
}
