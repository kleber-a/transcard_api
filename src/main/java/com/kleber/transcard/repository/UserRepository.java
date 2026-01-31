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
            AND u.role = 'USER'
        ORDER BY u.full_name
    """,
            countQuery = """
        SELECT COUNT(*)
        FROM users u
        WHERE (:name IS NULL 
               OR REPLACE(LOWER(u.full_name), ' ', '') 
               LIKE CONCAT('%', LOWER(REPLACE(:name, ' ', '')), '%'))
               AND u.role = 'USER'
    """,
            nativeQuery = true
    )
    Page<User> searchByNameNormalized(
            @Param("name") String name,
            Pageable pageable
    );

    boolean existsByEmail(String email);

    UserDetails findByEmail(String email);

}
