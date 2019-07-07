package com.kat.farmshop;

import com.kat.farmshop.model.StockItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.kat.farmshop.BillCalculatorTestUtil.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.time.LocalDate.now;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BillCalculatorImplTest {

    private BillCalculatorImpl billCalculatorImpl = new BillCalculatorImpl();

    private List<StockItem> items;
    private Boolean crossPromotion;
    private BigDecimal total;

    public BillCalculatorImplTest(List<StockItem> items, Boolean crossPromotion, BigDecimal total) {
        this.items = items;
        this.crossPromotion = crossPromotion;
        this.total = total;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Collections.emptyList(), FALSE, new BigDecimal("0.00")},
                {appleBags(), FALSE, new BigDecimal("20.00")},
                {wineBottles(), FALSE, new BigDecimal("34.48")},
                {wineBottlesAndAppleBags(), FALSE, new BigDecimal("33.72")},
                {wineBottlesAndAppleBags(), TRUE, new BigDecimal("25.22")},
                {appleBagsAndWineBottles(), TRUE, new BigDecimal("12.50")},
                {appleBags(), TRUE, new BigDecimal("20.00")},
                {wineBottles(), TRUE, new BigDecimal("34.48")}
        });
    }

    @Test
    public void shouldReturnCorrectTotal() {
        LocalDate purchaseDate = now();
        BigDecimal result = billCalculatorImpl.calculateTotal(crossPromotion, purchaseDate, items);
        assertEquals(total, result);
    }
}
