package com.kat.farmshop.service;

import com.kat.farmshop.model.Apple;
import com.kat.farmshop.model.StockItem;
import com.kat.farmshop.model.StockItemType;
import com.kat.farmshop.model.Wine;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BillServiceTestUtil {

    public static Apple getApple(double price, int quantity, LocalDate bestBeforeDate) {
        StockItem stockItem = StockItem.builder()
                .name(StockItemType.APPLE.getCode())
                .price(BigDecimal.valueOf(price))
                .quantity(quantity)
                .build();
        return new Apple(stockItem, bestBeforeDate);
    }

    public static Wine getWine(double price, int quantity, long year) {
        StockItem stockItem = StockItem.builder()
                .name(StockItemType.WINE.getCode())
                .price(BigDecimal.valueOf(price))
                .quantity(quantity)
                .build();
        return new Wine(stockItem, year);
    }
}
