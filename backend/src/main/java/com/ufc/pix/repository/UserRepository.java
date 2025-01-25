package com.ufc.pix.repository;

import com.ufc.pix.enumeration.UserAccess;
import com.ufc.pix.enumeration.UserStatus;
import com.ufc.pix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {


    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);

    @Query("""
            select u from User u
            where (:id      is null or  u.id = :id ) 
            and ( :name     is null or  u.name like concat('%', :name, '%') )
            and ( :email    is null or  u.email = :email) 
            and ( :cpf      is null or  u.cpf = :cpf) 
            and ( :birthDate is null or u.birthDate = :birthDate) 
            and ( :status   is null or  u.status = :status ) 
            and ( :access   is null or  u.access = :access )
            order by u.name
            """)
    List<User> list(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("email") String email,
            @Param("cpf") String cpf,
            @Param("birthDate") LocalDate birthDate,
            @Param("status") UserStatus status,
            @Param("access") UserAccess access
    );


}
