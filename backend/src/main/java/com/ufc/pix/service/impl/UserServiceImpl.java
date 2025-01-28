package com.ufc.pix.service.impl;

import com.ufc.pix.dto.*;
import com.ufc.pix.enumeration.AccountStatus;
import com.ufc.pix.enumeration.UserStatus;
import com.ufc.pix.exception.BusinessException;
import com.ufc.pix.repository.AccountRepository;
import com.ufc.pix.repository.UserRepository;
import com.ufc.pix.model.User;
import com.ufc.pix.service.TokenService;
import com.ufc.pix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public User create(CreateUserDto userDto) {

        var user = this.userRepository.findByEmail(userDto.getEmail());
        if (user.isPresent()) {
            if (((User) user.get()).getStatus() != UserStatus.ACTIVE){
                throw new BusinessException("There is an inactive user with this email");
            }
            throw new BusinessException("There is already a user with this email");
        }
        var userByCpf = this.userRepository.findByCpf(userDto.getCpf());
        if (userByCpf.isPresent()){
            if (userByCpf.get().getStatus() != UserStatus.ACTIVE){
                throw new BusinessException("There is an inactive user with this cpf");
            }
            throw new BusinessException("There is already a user with this cpf");
        }

        var userToSave = new User();
        userToSave.setName(userDto.getName());
        userToSave.setAccess(userDto.getAccess());
        userToSave.setStatus(UserStatus.ACTIVE);
        userToSave.setCpf(userDto.getCpf());
        userToSave.setEmail(userDto.getEmail());
        userToSave.setBirthDate(userDto.getBirthDate());
        userToSave.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        userToSave.setAccount(null);
        return userRepository.save(userToSave);
    }

    private UserDetails findByEmail(String email) {

        return this.userRepository.findByEmail(email).orElseThrow(()->
            new BusinessException("User not found",HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Optional<ViewUserDto> findById(UUID userId) {
        return this.userRepository.findById(userId)
                .map(User::toView);
    }


    @Override
    public void update(UUID userId, UpdateUserDto userDto) {
        var user = this.userRepository.findById(userId).orElseThrow(
                () -> new BusinessException("User not found", HttpStatus.NOT_FOUND)
        );


        if (userDto.name() != null) {
            user.setName(userDto.name());
        }

        if (userDto.email() != null) {
            user.setEmail(userDto.email());
        }

        if (userDto.cpf() != null) {
            user.setCpf(userDto.cpf());
        }

        if (userDto.birthDate() != null) {
            user.setBirthDate(userDto.birthDate());
        }

        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void delete(UUID userId) {
        var user = this.userRepository.findById(userId).orElseThrow(() -> new BusinessException("User not found", HttpStatus.NOT_FOUND));

        var account = this.accountRepository.findByUserId(userId);

        if (account.isPresent()){
            account.get().setStatus(AccountStatus.DELETED);
            this.accountRepository.save(account.get());
        }
        user.setStatus(UserStatus.DELETED);
        this.userRepository.save(user);
    }

    @Override
    public List<User> list(SearchUserDto dto) {
        if (dto.getName() == null) dto.setName("");
        if (dto.getEmail() == null) dto.setEmail("");
        if (dto.getCpf() == null) dto.setCpf("");

        return this.userRepository.list(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getCpf(),
                dto.getBirthDate(),
                dto.getStatus(),
                dto.getAccess());
    }

    @Override
    public Token login(LoginDto loginDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        var user = (User) this.authenticationManager.authenticate(usernamePassword).getPrincipal();

        if (!user.getStatus().equals(UserStatus.ACTIVE)){
            throw new BusinessException("User is "+user.getStatus());
        }
        var tokenKey = this.tokenService.generateToken(user);
        return new Token(tokenKey);
    }

}
