package com.list.server.repositories;

import com.list.server.domain.entities.LogDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogDetailRepository extends JpaRepository<LogDetail, Long> {

    @Query("SELECT ld FROM LogDetail ld WHERE ld.loginId = :loginId ORDER BY ld.lastLog DESC")
    Optional<LogDetail> findFirstByLoginIdOrderByLastLogDesc(Long loginId);
}
