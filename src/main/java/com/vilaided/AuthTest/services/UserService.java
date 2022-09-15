package com.vilaided.AuthTest.services;

import com.vilaided.AuthTest.exception.CustomException;
import com.vilaided.AuthTest.payload.response.ResponseCustom;
import com.vilaided.AuthTest.model.Role;
import com.vilaided.AuthTest.model.User;
import com.vilaided.AuthTest.payload.request.SignupRequest;
import com.vilaided.AuthTest.repository.RoleRepository;
import com.vilaided.AuthTest.repository.UserRepository;
import com.vilaided.AuthTest.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public ResponseCustom singin(String username, String password){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            String token = jwtTokenProvider.createToken(username);
            HashMap<String, String> tokenJson = new HashMap<String, String>();
            // Add keys and values (Country, City)
            tokenJson.put("token", token);
            return new ResponseCustom("OK","",tokenJson,HttpStatus.OK);
        }catch (AuthenticationException e){
            return new ResponseCustom("FAILED","user/password is invalid",HttpStatus.BAD_REQUEST);
        }
    }
    public User signup(SignupRequest req){
        if(!userRepository.existsByUserName(req.getUsername())){
            User user = new User();
            user.setUserName(req.getUsername());
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            Role role = roleRepository.findById(req.getRoleId()).get();
            user.setRole(role);
            System.out.println(user.getRole().getName() + " " + user.getRole().getId());
            userRepository.save(user);
            return user;
        }else{
            throw new CustomException("Username is already in use",HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    public ResponseCustom getUser(HttpServletRequest request){
        User user = userRepository.findByUserName(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        return new ResponseCustom("OK","",user,HttpStatus.OK);
    }
    public String refresh(String username) {
        return jwtTokenProvider.createToken(username);
    }

}