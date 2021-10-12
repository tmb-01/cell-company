package uz.pdp.cellcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.cellcompany.entity.Role;
import uz.pdp.cellcompany.entity.Turnstile;
import uz.pdp.cellcompany.entity.User;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.UpdateUserDto;
import uz.pdp.cellcompany.payload.UserDto;
import uz.pdp.cellcompany.repository.RoleRepository;
import uz.pdp.cellcompany.repository.TurnstileRepository;
import uz.pdp.cellcompany.repository.UserRepository;

import java.util.*;

@Service
public class UserService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TurnstileRepository turnstileRepository;

    public ApiResponse addUser(UserDto userDto) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role roleDirector = roleRepository.findById(6L).get();

        if (principal.getRoles().contains(roleDirector)) {

            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setRoles(userDto.getRoles());
            user.setBranch(userDto.getBranch());
            user.setEnabled(true);
            userRepository.save(user);

            return new ApiResponse("user saved", true);
        }
        return new ApiResponse("you can't add user", false);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public ApiResponse updateUser(UUID turnstileId, UpdateUserDto updateUserDto) {

        Optional<Turnstile> optionalTurnstile = turnstileRepository.findById(turnstileId);

        if (optionalTurnstile.isPresent()) {
            Optional<User> optionalUser = userRepository.findById(optionalTurnstile.get().getUser().getId());
            if (optionalUser.isPresent()) {
                User userData = optionalUser.get();
                userData.setFirstName(updateUserDto.getFirstName());
                userData.setLastName(userData.getLastName());
                userData.setPassword(updateUserDto.getPassword());
                userData.setUsername(updateUserDto.getUsername());
                userRepository.save(userData);
                return new ApiResponse("updated", true);
            }
            return new ApiResponse("user not found", false);
        }
        return new ApiResponse("turnstile not found", false);
    }

}
