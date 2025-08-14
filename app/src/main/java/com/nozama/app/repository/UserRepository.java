package com.nozama.app.repository;
import com.nozama.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query(value = """
    SELECT * FROM users u
    WHERE (:search IS NULL OR LOWER(u.name) LIKE LOWER('%' || :search || '%') OR LOWER(u.email) LIKE LOWER('%' || :search || '%'))
      AND (:role IS NULL OR u.role = CAST(:role AS varchar))
    """,
                countQuery = """
    SELECT COUNT(*) FROM users u
    WHERE (:search IS NULL OR LOWER(u.name) LIKE LOWER('%' || :search || '%') OR LOWER(u.email) LIKE LOWER('%' || :search || '%'))
      AND (:role IS NULL OR u.role = CAST(:role AS varchar))
    """,
                nativeQuery = true)
        Page<User> findByFilters(
                @Param("search") String search,
                @Param("role") String role,
                Pageable pageable
        );
}
