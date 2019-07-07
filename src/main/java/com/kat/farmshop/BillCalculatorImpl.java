package com.kat.farmshop;

import com.kat.farmshop.model.StockItem;
import com.kat.farmshop.model.StockItemType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class BillCalculatorImpl implements BillCalculator {

    @Override
    public BigDecimal calculateTotal(boolean crossPromotion, LocalDate purchaseDate, List<StockItem> items) {
        long appleBagsToExclude = 0;
        if (crossPromotion) {
            long numberOfWineBottles = numberOfWineBottles(items);
            if (numberOfWineBottles > 0) {
                long numberOfAppleBags = numberOfAppleBags(items);
                appleBagsToExclude = Math.min(numberOfWineBottles, numberOfAppleBags);
            }
        }

        BigDecimal applesTotal = calculateAppleBagsTotal(appleBagsToExclude, purchaseDate, items);
        BigDecimal wineBottlesTotal = calculateWineBottlesTotal(purchaseDate, items);
        return applesTotal.add(wineBottlesTotal);
    }

    private long numberOfWineBottles(List<StockItem> items) {
        return items.stream().filter(item -> item.getName().equals(StockItemType.WINE.getCode())).count();
    }

    private long numberOfAppleBags(List<StockItem> items) {
        return items.stream().filter(item -> item.getName().equals(StockItemType.APPLE.getCode())).count();
    }

    private BigDecimal calculateAppleBagsTotal(long appleBagsToExclude, LocalDate purchaseDate, List<StockItem> items) {
        return items.stream()
                .filter(item -> item.getName().equals(StockItemType.APPLE.getCode()))
                .map(item -> item.getPriceAt(purchaseDate))
                .sorted(Comparator.naturalOrder())
                .skip(appleBagsToExclude)
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateWineBottlesTotal(LocalDate purchaseDate, List<StockItem> items) {
        return items.stream()
                .filter(item -> item.getName().equals(StockItemType.WINE.getCode()))
                .map(item -> item.getPriceAt(purchaseDate))
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }
}
