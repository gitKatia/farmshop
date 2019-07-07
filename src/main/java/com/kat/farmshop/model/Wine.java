package com.kat.farmshop.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "WINE")
@DiscriminatorValue("WINE")
@Slf4j
public class Wine extends StockItem implements Aged {

    @Transient
    @Value("${wine.age.multiplier}")
    private double ageMultiplier;
    @Column(unique=true)
    private long year;

    public Wine(StockItem stockItem, long year) {
        super(stockItem.getName(), stockItem.getPrice(), stockItem.getQuantity());
        this.year = year;
    }

    @Override
    public long getYear() {
        return year;
    }

    @Override
    public double getAgeMultiplier() {
        return ageMultiplier;
    }

    public void setAgeMultiplier(double ageMultiplier) {
        this.ageMultiplier = ageMultiplier;
    }

    @Override
    public BigDecimal getPriceAt(LocalDate purchaseDate) {
        if (purchaseDate.getYear() > this.getYear()) {
            long age = purchaseDate.getYear() - this.getYear();
            return this.getPrice().multiply(BigDecimal.valueOf(Math.pow(ageMultiplier, age)));
        } else if (purchaseDate.getYear() == this.getYear()) {
            return this.getPrice();
        } else {
            log.error("purchase date year {} cannot be before production year {}", purchaseDate.getYear(), this.getYear());
            throw new RuntimeException("purchase date year " + purchaseDate.getYear() + " cannot be before production year " + this.getYear());
        }
    }

}
