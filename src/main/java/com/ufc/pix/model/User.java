package com.ufc.pix.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Table(name="Users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private LocalDate birthDate;
    @Column(length = 10,name = "access",nullable = false)

    private String access = "USER";

    public User(String name, String email, String password, String cpf, LocalDate birthDate){
        setName(name);
        setCpf(cpf);
        setEmail(email);
        setPassword(password);
        setBirthDate(birthDate);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
