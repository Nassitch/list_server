package com.list.server.repositories;

import com.list.server.domain.entities.LogDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogDetailRepository extends JpaRepository<LogDetail, Long> {
}
