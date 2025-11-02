package com.devteria.identity_service.Controller;

import com.devteria.identity_service.dto.request.ApiResponse;
import com.devteria.identity_service.dto.request.UserCreationRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse <User> createUser(@RequestBody @Valid UserCreationRequest request)
    {
        ApiResponse <User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }
    @GetMapping()
    public ApiResponse<List<User>>  getUsers()
    {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUsers());
        return apiResponse;
    }
    @GetMapping("/{userId}")
    public ApiResponse<User> getUser(@PathVariable String userId)
    {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUserById(userId));
        return  apiResponse;
    }
    @PutMapping("/{userId}")
    public ApiResponse<User> upDateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.userUpdate(userId, request));
        return apiResponse;
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId)
    {
    userService.deleteUserById(userId);
    return "success";
    }
}
