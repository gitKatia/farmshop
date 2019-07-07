package com.kat.farmshop;

import com.kat.farmshop.model.StockItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BillCalculator {

    BigDecimal calculateTotal(boolean crossPromotion, LocalDate purchaseDate, List<StockItem> items);
}
