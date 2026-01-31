package com.kleber.transcard.repository;

import com.kleber.transcard.entity.Card;
import com.kleber.transcard.repository.projection.CardWithUserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card, Long> {



    @Query(
            value = """
        SELECT 
            c.id AS id,
            c.card_number AS cardNumber,
            c.card_name AS cardName,
            c.status AS status,
            c.card_type AS typeCard,
            u.id AS userId,
            u.full_name AS userName,
            u.email AS userEmail
        FROM cards c
        LEFT JOIN users u ON c.user_id = u.id
        WHERE (:name IS NULL 
               OR REPLACE(LOWER(c.card_name), ' ', '') 
               LIKE CONCAT('%', LOWER(REPLACE(:name, ' ', '')), '%'))
          AND (:cardType IS NULL OR c.card_type = :cardType)
          AND (:status IS NULL OR c.status = :status)
        ORDER BY c.card_name
    """,
            countQuery = """
        SELECT COUNT(*)
        FROM cards c
        LEFT JOIN users u ON c.user_id = u.id
        WHERE (:name IS NULL 
               OR REPLACE(LOWER(c.card_name), ' ', '') 
               LIKE CONCAT('%', LOWER(REPLACE(:name, ' ', '')), '%'))
          AND (:cardType IS NULL OR c.card_type = :cardType)
          AND (:status IS NULL OR c.status = :status)
    """,
            nativeQuery = true
    )
    Page<CardWithUserProjection> findByFilters(
            @Param("name") String name,
            @Param("cardType") String cardType,
            @Param("status") Boolean status,
            Pageable pageable
    );


    boolean existsByCardNumber(Long cardNumber);


}
