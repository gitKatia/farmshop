package com.kat.farmshop.model;

import com.kat.farmshop.DayMultiplierProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("APPLE")
@Slf4j
public class Apple extends BBStockItem {

    @Transient
    private DayMultiplierProps dayMultiplierProps;

    public Apple(StockItem stockItem, LocalDate bestBeforeDate) {
        super(stockItem, bestBeforeDate);
    }

    @Autowired
    public void setDayMultiplierProps(DayMultiplierProps dayMultiplierProps) {
        this.dayMultiplierProps = dayMultiplierProps;
    }

    @Override
    public BigDecimal getPriceAt(LocalDate purchaseDate) {

        LocalDate bestBeforeDate = getBestBeforeDate();

        if (bestBeforeDate.isEqual(purchaseDate)) {
            return getPriceFor(dayMultiplierProps.getOnDayMultiplier());
        } else if (bestBeforeDate.isEqual(purchaseDate.minusDays(1))) {
            return getPriceFor(dayMultiplierProps.getPlusOneDayMultiplier());
        } else if (bestBeforeDate.isEqual(purchaseDate.minusDays(2))) {
            return getPriceFor(dayMultiplierProps.getPlusTwoDaysMultiplier());
        } else if (bestBeforeDate.isBefore(purchaseDate.minusDays(2))) {
            log.error("Spoiled apples should be removed from stock");
            return BigDecimal.ZERO;
        } else if (bestBeforeDate.isEqual(purchaseDate.plusDays(1))) {
            return getPriceFor(dayMultiplierProps.getMinusOneDayMultiplier());
        } else {
            return getPrice();
        }

    }

    private BigDecimal getPriceFor(double multiplier) {
        return this.getPrice().multiply(BigDecimal.valueOf(multiplier));
    }

}
