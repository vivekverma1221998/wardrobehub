package com.wardrobehub.controller;

import com.wardrobehub.config.JwtProvider;
import com.wardrobehub.exception.UserException;
import com.wardrobehub.model.User;
import com.wardrobehub.repository.UserRepository;
import com.wardrobehub.request.LoginRequest;
import com.wardrobehub.response.AuthResponse;
import com.wardrobehub.service.CustomerUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserRepository userRepository;

    private JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder;

    private CustomerUserServiceImpl customerUserService;


    public AuthController(UserRepository userRepository , CustomerUserServiceImpl customerUserService,
                          PasswordEncoder passwordEncoder, JwtProvider jwtProvider)
    {
        this.userRepository = userRepository;
        this.customerUserService = customerUserService;

        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }


    @PostMapping(path = "/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{

        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();


        User isEmailExist = userRepository.findByEmail(email);

        if(isEmailExist!= null){
            throw new UserException("Email is already used with another account");
        }

        User createdUser = new User();

        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail() , savedUser.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);



        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("signup success");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserhandler(@RequestBody LoginRequest loginRequest){
        String userName = loginRequest.getEmail();

        String password = loginRequest.getPassword();


        Authentication authentication = authenticate(userName , password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token , "Signin Successfully");

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);


    }


    private Authentication authenticate(String userName , String password){
        UserDetails userDetails = customerUserService.loadUserByUsername(userName);

        if(userDetails == null){
           throw new BadCredentialsException("invalid username");
        }

        if(!passwordEncoder.matches(password , userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
    }


}
