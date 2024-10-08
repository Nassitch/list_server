package com.list.server.repositories;

import com.list.server.domain.entities.LogDetail;
import com.list.server.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByFirstName(String firstName);
    Optional<User> findByLoginId(Long loginId);
    boolean existsByLoginId(Long loginId);
    void deleteByLoginId(Long loginId);
}
