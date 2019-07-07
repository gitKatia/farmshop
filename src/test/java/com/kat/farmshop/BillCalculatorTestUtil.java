package com.kat.farmshop;

import com.kat.farmshop.DayMultiplierProps;
import com.kat.farmshop.model.Apple;
import com.kat.farmshop.model.StockItem;
import com.kat.farmshop.model.StockItemType;
import com.kat.farmshop.model.Wine;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillCalculatorTestUtil {

    private static DayMultiplierProps props;
    private static double ageMultiplier;

    static {
        props = new DayMultiplierProps();
        props.setMinusOneDayMultiplier(0.9);
        props.setOnDayMultiplier(0.8);
        props.setPlusOneDayMultiplier(0.7);
        props.setPlusTwoDaysMultiplier(0.6);
        ageMultiplier = 1.05;
    }

    public static List<StockItem> appleBags() {
        List<StockItem> items = new ArrayList<>();

        StockItem item0 = new StockItem(StockItemType.APPLE.getCode(), BigDecimal.valueOf(5), 100);
        StockItem item1 = new StockItem(StockItemType.APPLE.getCode(), BigDecimal.valueOf(5), 100);
        StockItem item2 = new StockItem(StockItemType.APPLE.getCode(), BigDecimal.valueOf(5), 100);
        StockItem item3 = new StockItem(StockItemType.APPLE.getCode(), BigDecimal.valueOf(5), 100);
        StockItem item4 = new StockItem(StockItemType.APPLE.getCode(), BigDecimal.valueOf(5), 100);

        Apple apple0 = new Apple(item0, LocalDate.now().minusDays(2));
        Apple apple1 = new Apple(item1, LocalDate.now().minusDays(1));
        Apple apple2 = new Apple(item2, LocalDate.now());
        Apple apple3 = new Apple(item3, LocalDate.now().plusDays(1));
        Apple apple4 = new Apple(item4, LocalDate.now().plusDays(2));

        items.addAll(Arrays.asList(apple0, apple1, apple2, apple3, apple4));
        items.forEach(item -> {
            ((Apple) item).setDayMultiplierProps(props);
        });

        return items;
    }

    public static List<StockItem> wineBottles() {
        List<StockItem> items = new ArrayList<>();

        StockItem item1 = new StockItem(StockItemType.WINE.getCode(), BigDecimal.valueOf(8), 100);
        StockItem item2 = new StockItem(StockItemType.WINE.getCode(), BigDecimal.valueOf(8), 100);
        StockItem item3 = new StockItem(StockItemType.WINE.getCode(), BigDecimal.valueOf(8), 100);
        StockItem item4 = new StockItem(StockItemType.WINE.getCode(), BigDecimal.valueOf(8), 100);

        Wine wine1 = new Wine(item1, LocalDate.now().getYear() - 3);
        Wine wine2 = new Wine(item2, LocalDate.now().getYear() - 2);
        Wine wine3 = new Wine(item3, LocalDate.now().getYear() - 1);
        Wine wine4 = new Wine(item4, LocalDate.now().getYear());

        items.addAll(Arrays.asList(wine1, wine2, wine3, wine4));
        items.forEach(item -> {
            ((Wine) item).setAgeMultiplier(1.05);
        });

        return items;
    }

    public static List<StockItem> appleBagsAndWineBottles() {
        List<StockItem> items = new ArrayList<>();

        StockItem item1 = new StockItem(StockItemType.APPLE.getCode(), BigDecimal.valueOf(5), 100);
        StockItem item2 = new StockItem(StockItemType.APPLE.getCode(), BigDecimal.valueOf(5), 100);
        StockItem item3 = new StockItem(StockItemType.WINE.getCode(), BigDecimal.valueOf(8), 100);

        Apple apple1 = new Apple(item1, LocalDate.now().plusDays(1));
        Apple apple2 = new Apple(item2, LocalDate.now());
        Wine wine3 = new Wine(item3, LocalDate.now().getYear());

        apple1.setDayMultiplierProps(props);
        apple2.setDayMultiplierProps(props);
        wine3.setAgeMultiplier(ageMultiplier);

        items.addAll(Arrays.asList(apple1, apple2));
        items.addAll(Arrays.asList(wine3));

        return items;
    }

    public static List<StockItem> wineBottlesAndAppleBags() {
        List<StockItem> items = new ArrayList<>();

        StockItem item1 = new StockItem(StockItemType.APPLE.getCode(), BigDecimal.valueOf(5), 100);
        StockItem item2 = new StockItem(StockItemType.APPLE.getCode(), BigDecimal.valueOf(5), 100);
        StockItem item3 = new StockItem(StockItemType.WINE.getCode(), BigDecimal.valueOf(8), 100);
        StockItem item4 = new StockItem(StockItemType.WINE.getCode(), BigDecimal.valueOf(8), 100);
        StockItem item5 = new StockItem(StockItemType.WINE.getCode(), BigDecimal.valueOf(8), 100);

        Apple apple1 = new Apple(item1, LocalDate.now().plusDays(1));
        Apple apple2 = new Apple(item2, LocalDate.now());
        Wine wine3 = new Wine(item3, LocalDate.now().getYear());
        Wine wine4 = new Wine(item4, LocalDate.now().getYear() - 1);
        Wine wine5 = new Wine(item5, LocalDate.now().getYear() - 2);

        apple1.setDayMultiplierProps(props);
        apple2.setDayMultiplierProps(props);

        items.addAll(Arrays.asList(wine3, wine4, wine5));
        items.forEach(item -> {
            ((Wine) item).setAgeMultiplier(ageMultiplier);
        });

        items.addAll(Arrays.asList(apple1, apple2));

        return items;
    }

}
