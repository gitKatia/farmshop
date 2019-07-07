package com.kat.farmshop.service;

import com.kat.farmshop.model.Apple;
import com.kat.farmshop.model.StockItem;
import com.kat.farmshop.model.StockItemType;
import com.kat.farmshop.model.Wine;
import com.kat.farmshop.repository.StockItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class StockItemServiceImpl implements StockItemService {

    private StockItemRepository stockItemRepository;

    @Autowired
    public StockItemServiceImpl(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }

    @Override
    public void decreaseStock(StockItem stockItem) {
        int quantity = stockItem.getQuantity() - 1;
        if (stockItem.getName().equals(StockItemType.APPLE.getCode())) {
            LocalDate bestBeforeDate = ((Apple) stockItem).getBestBeforeDate();
            stockItemRepository.updateStockItemQuantity(quantity, bestBeforeDate);
        } else if (stockItem.getName().equals(StockItemType.WINE.getCode())) {
            long year = ((Wine) stockItem).getYear();
            stockItemRepository.updateStockItemQuantity(quantity, year);
        } else {
            log.error("Unknown Stock Item Type {}", stockItem.getName());
        }
    }

    @Override
    public long countByName(String name) {
        return stockItemRepository.countByName(name);
    }
}
