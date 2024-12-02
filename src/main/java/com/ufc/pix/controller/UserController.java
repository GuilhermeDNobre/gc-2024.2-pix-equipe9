package com.ufc.pix.controller;

import com.ufc.pix.dto.CreateUserDto;
import com.ufc.pix.dto.LoginDto;
import com.ufc.pix.dto.Token;
import com.ufc.pix.dto.UpdateUserDto;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<Object> getUserById(@PathVariable("userId") String id) {
        try {
            User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UUID userId = UUID.fromString(id);

            if (!authenticatedUser.getId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }

            var user = userService.getUserById(userId);

            if (user.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(user.get());
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user ID format.");
        }
    }

    @PutMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<Object> updateUser(@PathVariable("userId") String id, @RequestBody @Valid UpdateUserDto userDto) {
        try {
            User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UUID userId = UUID.fromString(id);

            if (!authenticatedUser.getId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }

            var user = userService.getUserById(userId);

            if (user.isPresent()) {
                userService.updateUser(userId, userDto);
                return ResponseEntity.status(HttpStatus.OK).body("User updated .");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user ID format.");
        }
    }

    @DeleteMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") String id) {
        try {
            User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UUID userId = UUID.fromString(id);

            if (!authenticatedUser.getId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }

            var user = userService.getUserById(userId);

            if (user.isPresent()) {
                userService.deleteUser(userId);
                return ResponseEntity.status(HttpStatus.OK).body("User deleted.");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user ID format.");
        }
    }
}
