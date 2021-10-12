package uz.pdp.cellcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.cellcompany.entity.User;
import uz.pdp.cellcompany.payload.ApiResponse;
import uz.pdp.cellcompany.payload.LoginDto;
import uz.pdp.cellcompany.repository.RoleRepository;
import uz.pdp.cellcompany.repository.UserRepository;
import uz.pdp.cellcompany.security.JwtProvider;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;


    public void sendEmail(String sendingEmail, String emailCode) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("gm.khamza@gmail.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Account confirmation!");
            mailMessage.setText("<a href='http://localhost:8080/api/auth/verify?emailCode=" + emailCode + "&email=" + sendingEmail + "'>Confirm</a>");

            javaMailSender.send(mailMessage);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public ApiResponse login(LoginDto loginDto) {

        try {
            Authentication authenticate =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getUsername(), user.getRoles());
            return new ApiResponse(token, true);
        } catch (BadCredentialsException badCredentialsException) {
            return new ApiResponse("Username or password failed!", false);
        }
    }


//    public ApiResponse verifyEmail(String emailCode, String email, LoginDto loginDto) {
//        Optional<User> optionalUser = userRepository.findByEmailAndEmailCode(email, emailCode);
//        if (optionalUser.isPresent()) {
//            optionalUser.get().setEnabled(true);
//            optionalUser.get().setEmailCode(null);
//            optionalUser.get().setPassword(passwordEncoder.encode(loginDto.getPassword()));
//            userRepository.save(optionalUser.get());
//            return new ApiResponse("Account confirmed!", true);
//        }
//        return new ApiResponse("Account is already confirmed!", false);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
