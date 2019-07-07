package com.kat.farmshop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="apple")
public class DayMultiplierProps {

    private double minusOneDayMultiplier;
    private double onDayMultiplier;
    private double plusOneDayMultiplier;
    private double plusTwoDaysMultiplier;

    public double getMinusOneDayMultiplier() {
        return minusOneDayMultiplier;
    }

    public double getOnDayMultiplier() {
        return onDayMultiplier;
    }

    public double getPlusOneDayMultiplier() {
        return plusOneDayMultiplier;
    }

    public double getPlusTwoDaysMultiplier() {
        return plusTwoDaysMultiplier;
    }

    public void setMinusOneDayMultiplier(double minusOneDayMultiplier) {
        this.minusOneDayMultiplier = minusOneDayMultiplier;
    }

    public void setOnDayMultiplier(double onDayMultiplier) {
        this.onDayMultiplier = onDayMultiplier;
    }

    public void setPlusOneDayMultiplier(double plusOneDayMultiplier) {
        this.plusOneDayMultiplier = plusOneDayMultiplier;
    }

    public void setPlusTwoDaysMultiplier(double plusTwoDaysMultiplier) {
        this.plusTwoDaysMultiplier = plusTwoDaysMultiplier;
    }
}
