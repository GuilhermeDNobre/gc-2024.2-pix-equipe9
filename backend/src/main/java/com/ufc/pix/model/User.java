package com.ufc.pix.model;

import com.ufc.pix.dto.ViewUserDto;
import com.ufc.pix.enumeration.UserAccess;
import com.ufc.pix.enumeration.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Table(name="users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String cpf;
    private String email;
    private String password;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserAccess access;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public User(String name, String email, String password, String cpf, LocalDate birthDate){
        setName(name);
        setCpf(cpf);
        setEmail(email);
        setPassword(password);
        setBirthDate(birthDate);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.access.equals(UserAccess.USER)){ return List.of(new SimpleGrantedAuthority("ROLE_USER"));}
        else if (this.access.equals(UserAccess.ADMIN)){return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));}

        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public ViewUserDto toView(){
        return new ViewUserDto(
                getId(),
                getName(),
                getEmail(),
                getCpf(),
                getBirthDate(),
                getAccess().getDescription(),
                getStatus().getDescription()
        );
    }
}