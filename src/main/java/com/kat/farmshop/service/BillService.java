package com.kat.farmshop.service;

import com.kat.farmshop.model.StockItem;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BillService {

    LocalDate getPurchaseDate();
    BigDecimal getTotal();
    BigDecimal addItem(StockItem item);
}
