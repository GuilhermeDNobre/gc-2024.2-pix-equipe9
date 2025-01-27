package com.ufc.pix.controller;

import com.ufc.pix.doc.UserDoc;
import com.ufc.pix.dto.*;
import com.ufc.pix.enumeration.UserAccess;
import com.ufc.pix.enumeration.UserStatus;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.model.User;
import com.ufc.pix.service.TokenService;
import com.ufc.pix.service.impl.AuthorizationService;
import com.ufc.pix.service.impl.TokenServiceImpl;
import com.ufc.pix.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController implements UserDoc {

    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private UserService userService;

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUserDto userDto) {
        userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    @CrossOrigin
    public ResponseEntity<Token> login(
            @RequestBody LoginDto loginDto
    ) {

        return ResponseEntity.status(HttpStatus.OK).body(this.userService.login(loginDto));
    }

    @GetMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<ViewUserDto> getById(@PathVariable("userId") UUID userId) {

        return ResponseEntity.ok(this.userService.findById(userId).get());
    }

    @GetMapping()
    @CrossOrigin
    public ResponseEntity<List<ViewUserDto>> list(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) LocalDate birthDate,
            @RequestParam(required = false) UserStatus status,
            @RequestParam(required = false) UserAccess access
    )

    {
        var list = this.userService.list(new SearchUserDto(id,name,email,cpf,birthDate,status,access));
        return ResponseEntity.ok(list.stream().map(User::toView).toList());
    }

    @PutMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<Void> update(@PathVariable("userId") UUID id, @RequestBody @Valid UpdateUserDto userDto) {
        this.userService.update(id, userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<Void> delete(@PathVariable("userId") UUID id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/block/{id}")
    @CrossOrigin
    public ResponseEntity<Void> block(@PathVariable UUID id,@RequestHeader("Authorization") String authorizationHeader) {
        this.userService.block(id, authorizationHeader);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/unblock/{id}")
    @CrossOrigin
    public ResponseEntity<Void> unblock(@PathVariable UUID id) {
        this.userService.unblock(id);
        return ResponseEntity.noContent().build();
    }

}
