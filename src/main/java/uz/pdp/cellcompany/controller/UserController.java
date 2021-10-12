package uz.pdp.cellcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cellcompany.entity.User;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.UpdateUserDto;
import uz.pdp.cellcompany.payload.UserDto;
import uz.pdp.cellcompany.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/delete")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getUsers();
    }

    @PostMapping
    public ApiResponse addUser(UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PutMapping("{turnstile-id}")
    public ApiResponse updateUser(@PathVariable("turnstile-id") UUID turnstileId, @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(turnstileId, updateUserDto);
    }

}
