package com.list.server.repositories;

import com.list.server.domain.entities.LogDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogDetailRepository extends JpaRepository<LogDetail, Long> {

    @Query("SELECT ld FROM LogDetail ld WHERE ld.loginId = :loginId ORDER BY ld.lastLog DESC")
    List<LogDetail> findAllByLoginIdOrderByLastLogDesc(@Param("loginId") Long loginId);

    Optional<LogDetail> findByLoginId(Long loginId);
    boolean existsByLoginId(Long loginId);
    void deleteByLoginId(Long loginId);
}
