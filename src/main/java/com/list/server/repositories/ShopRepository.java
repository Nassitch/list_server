package com.list.server.repositories;

import com.list.server.domain.entities.Shop;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByUserId(Long userId);

    @Query("SELECT s FROM Shop s JOIN s.items i WHERE i.id = :itemId")
    List<Shop> findShopsContainingItem(@Param("itemId") Long itemId);
}
