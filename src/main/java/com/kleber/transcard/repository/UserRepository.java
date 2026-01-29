package com.kleber.transcard.repository;

import com.kleber.transcard.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = """
        SELECT *
        FROM users u
        WHERE (:name IS NULL 
               OR REPLACE(LOWER(u.full_name), ' ', '') 
               LIKE CONCAT('%', LOWER(REPLACE(:name, ' ', '')), '%'))
        ORDER BY u.full_name
    """,
            countQuery = """
        SELECT COUNT(*)
        FROM users u
        WHERE (:name IS NULL 
               OR REPLACE(LOWER(u.full_name), ' ', '') 
               LIKE CONCAT('%', LOWER(REPLACE(:name, ' ', '')), '%'))
    """,
            nativeQuery = true
    )
    Page<User> searchByNameNormalized(
            @Param("name") String name,
            Pageable pageable
    );


//    @Modifying
//    @Query(value = "UPDATE users SET full_name = :name, email = :email, password = :password WHERE id = :id", nativeQuery = true)
//    void updateUser(
//            @Param("id") Long id,
//            @Param("name") String name,
//            @Param("email") String email,
//            @Param("password") String password
//    );



    boolean existsByEmail(String email);

    UserDetails findByEmail(String email);

}
