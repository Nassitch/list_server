package com.list.server.repositories;

import com.list.server.domain.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByUserId(Long userId);
    boolean existsByShopId(Long shopId);
    List<Invoice> findByShopId(Long shopId);

    @Query("SELECT inv FROM Invoice inv JOIN inv.shop s JOIN s.items i WHERE i.id = :itemId")
    List<Invoice> findInvoicesContainingItem(@Param("itemId") Long itemId);
}
