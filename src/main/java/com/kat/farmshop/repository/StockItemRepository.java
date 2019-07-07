package com.kat.farmshop.repository;

import com.kat.farmshop.model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {
    long countByName(String name);

    @Modifying
    @Query("update StockItem item set item.quantity = :quantity where item.bestBeforeDate = :bestBeforeDate")
    int updateStockItemQuantity(@Param("quantity") Integer quantity, @Param("bestBeforeDate") LocalDate bestBeforeDate);

    @Modifying
    @Query("update StockItem item set item.quantity = :quantity where item.year = :year")
    int updateStockItemQuantity(@Param("quantity") Integer quantity, @Param("year") long year);
}
