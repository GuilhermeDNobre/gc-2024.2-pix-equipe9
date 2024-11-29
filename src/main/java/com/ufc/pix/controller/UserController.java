package com.ufc.pix.controller;

import com.ufc.pix.dto.CreateUserDto;
import com.ufc.pix.dto.LoginDto;
import com.ufc.pix.dto.Token;
import com.ufc.pix.model.User;
import com.ufc.pix.service.AuthorizationService;
import com.ufc.pix.service.TokenService;
import com.ufc.pix.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping
    @CrossOrigin
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

    @PostMapping("login")
    @CrossOrigin
    public ResponseEntity<Token> login(
            @RequestBody LoginDto loginDto
    ) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken( (User) auth.getPrincipal() );
        return ResponseEntity.status(HttpStatus.OK).body(new Token(token));
    }

}
