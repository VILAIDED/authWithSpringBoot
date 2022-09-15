package com.vilaided.AuthTest.controller;

import com.vilaided.AuthTest.payload.response.ResponseCustom;
import com.vilaided.AuthTest.model.User;
import com.vilaided.AuthTest.payload.request.SignupRequest;
import com.vilaided.AuthTest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/signin")
    public ResponseEntity getUser(@RequestBody @Valid User user){
        ResponseCustom res = userService.singin(user.getUserName(),user.getPassword());
        return new ResponseEntity(res,res.getStatusCode());
    }
    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody @Valid SignupRequest req){
        return ResponseEntity.ok(userService.signup(req));
    }
    @GetMapping("/admin")
    public ResponseEntity admin() {
        return new ResponseEntity(new ResponseCustom<>("OK", "Hello admin", HttpStatus.OK), HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity user(){
        return new ResponseEntity(new ResponseCustom<>("OK", "Hello client", HttpStatus.OK), HttpStatus.OK);

    }
    public ResponseEntity hello(HttpServletRequest req){
        ResponseCustom res = userService.getUser(req);
        return new ResponseEntity(res,res.getStatusCode());
    }
}

