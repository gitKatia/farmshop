package com.kat.farmshop.service;

import com.kat.farmshop.BillCalculator;
import com.kat.farmshop.model.StockItem;
import com.kat.farmshop.model.StockItemType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BillServiceImpl implements BillService {

    @Value("${cross.promotion}")
    private boolean crossPromotion;
    private List<StockItem> items = new ArrayList<>();

    private StockItemService stockItemService;
    private BillCalculator billCalculator;

    @Autowired
    public BillServiceImpl(StockItemService stockItemService, BillCalculator billCalculator) {
        this.stockItemService = stockItemService;
        this.billCalculator = billCalculator;
    }

    @Override
    public LocalDate getPurchaseDate() {
        return LocalDate.now();
    }

    public List<StockItem> getItems() {
        return items;
    }

    @Override
    public BigDecimal getTotal() {
        return billCalculator.calculateTotal(crossPromotion, getPurchaseDate(), items);
    }

    @Override
    public BigDecimal addItem(StockItem item) {
        long itemsNo = item.getQuantity();
        if (itemsNo > 0 && exists(item)) {
            items.add(item);
            stockItemService.decreaseStock(item);
        } else {
            log.error("Item not added to the bill because item {} is not available", item.getName());
        }
        return item.getPrice();
    }

    private boolean exists(StockItem item) {
        return item.getName().equals(StockItemType.APPLE.getCode())
                || item.getName().equals(StockItemType.WINE.getCode());
    }

}
