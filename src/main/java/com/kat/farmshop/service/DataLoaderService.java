package com.kat.farmshop.service;

import com.kat.farmshop.model.Apple;
import com.kat.farmshop.model.StockItem;
import com.kat.farmshop.model.StockItemType;
import com.kat.farmshop.repository.StockItemRepository;
import com.kat.farmshop.model.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DataLoaderService {

    @Value("${apple.total.refill}")
    private int appleTotalRefill;
    @Value("${wine.total.refill}")
    private int wineTotalRefill;
    @Value("${wine.up.to.age}")
    private int wineUpToAge;

    private StockItemRepository stockItemRepository;

    @Autowired
    public DataLoaderService(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        loadApplesWith(LocalDate.now().minusDays(1));
        loadApplesWith(LocalDate.now().minusDays(2));
        loadApplesWith(LocalDate.now());
        loadApplesWith(LocalDate.now().plusDays(1));

        for (int k = -(wineUpToAge - 1); k < 1; k++) {
            loadWineWith(LocalDate.now().getYear() + k);
        }
    }

    private void loadApplesWith(LocalDate bestBeforeDate) {

        StockItem stockItem = StockItem.builder()
                .name(StockItemType.APPLE.getCode())
                .price(BigDecimal.valueOf(3))
                .quantity(appleTotalRefill / 4)
                .build();
        Apple apple = new Apple(stockItem, bestBeforeDate);
        stockItemRepository.save(apple);
    }

    private void loadWineWith(int year) {
        StockItem stockItem = StockItem.builder()
                .name(StockItemType.WINE.getCode())
                .price(BigDecimal.valueOf(9))
                .quantity(wineTotalRefill / wineUpToAge)
                .build();
        Wine wine = new Wine(stockItem, year);
        stockItemRepository.save(wine);
    }

}
